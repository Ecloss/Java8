package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 余修文
 * @date 2018/10/1 20:15
 */
public class StreamDemo02 {

    public static void main(String[] args) {

        /**
         * Stream操作：当把数据接口包装成stream后，就可以对stream进行各种操作了
         *
         * Intermediate: 中间操作
         * map(mapToInt, faltMap), filter, distinct(去除重复列), sorted(排序), peek, limit, skip, parallel, sequential, unordered
         * Short-circuiting:  短路操作
         * anyMatch, allMatch, noneMatch, findFirst, findAny, limit
         *
         */

        // map, flatMap映射：把stream的元素映射成另一个元素。
        /**
         * 1. 将字符串数组中所有的字符 转成大写
         */
        String[] str2 = new String[]{"abc", "hello", "keep", "Time"};
        Stream<String> stream2 = Stream.of(str2);
        // map是映射操作，collect是对这些数据进行收集，并且返回相应的集合
        List<String> list3 = stream2.
                map(String::toUpperCase).
                collect(Collectors.toList());
        System.out.println(list3.toString());

        System.out.println("-------for-Each循环-------");
        // 对于stream流，可以对其进行forEach循环操作
        // 此处我犯了一个错误，一个Stream流只能用一次，因此要重新创建一个stream流
        Stream<String> stream = Stream.of(str2);
        stream.
                map(s -> s.toUpperCase()).
                collect(Collectors.toList()).
                forEach(s -> System.out.println(s));
        // 如果对stream进行操作一般不会创建一个stream对象，而是使用List.stream();   集合
        // 2. 对Integer的集合进行平方操作
        Integer[] init = new Integer[]{1, 2, 3, 4, 5};
        List<Integer> list = Arrays.asList(init);
        list.stream().
                map(i -> i*i).
                forEach(i -> System.out.println(i));

        System.out.println("--------------flatMap的用法----------------");
        /**
         * 3. map生成的是1:1关系，而flatMap 对应的是1:N 关系
         * map：  <R> Stream<R> map(Function<? super T, ? extends R> mapper);
         * flatMap： <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
         */
        Stream<List<Integer>> stream1 = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        Stream<Integer> stream3 = stream1.flatMap(e -> e.stream());
        stream3.forEach(i -> System.out.println(i + 1));

        /**
         * forEach 方法：带入参数，然后不返回任何参数。
         */
        System.out.println("------测试flatMap----------");
        // 将字符串 tom.Li lucy.Liu 用'.' 作为分割，返回4个字符串
        Stream<String> stream4 = Stream.of("tom.Li", "lucy.Liu");
        Stream<String> stream5 = stream4.flatMap(e -> Stream.of(e.split("[.]")));
        stream5.forEach(System.out::println);

        /**
         * filter: 对Stream进行筛选，筛选下来的元素生成一个新的Stream
         *     通过一个predicate接口来过滤并只保留符合条件的元素，该操作属于中间操作，
         *     所以我们可以在过滤后的结果来应用其他Stream操作（比如forEach， 比如collect操作）。
         *     forEach需要一个函数来对过滤后的元素依次执行。
         *     forEach是一个最终操作，所以我们不能在forEach之后来执行其他Stream操作
         */
        System.out.println("---------filter方法的使用-------------------");
        List<String> list1 = Arrays.asList("Hi", "Hello", "Sea", "Sun", "TomAndSally");
        Stream<String> stream6 = list1.stream().filter(s -> s.length() > 3);
        // 这里我筛选出字符串长度 > 3 的数据，返回一个集合
        List<String> list2 = list1.stream().filter(s -> s.length() > 3).collect(Collectors.toList());
        System.out.println("List2 = " + list2.toString());
        stream6.forEach(System.out::println);
        System.out.println("-------------peek的用法---------------");
        /**
         * 发现了一个规律，peek和filter的筛选不是
         */
        List<String> list4 = list1.stream().
                filter(e -> e.length() > 2).
                peek(s -> System.out.println("长度大于2 的字符串为：" + s)).
                filter(e -> e.length() > 3).
                peek(s -> System.out.println("长度大于3的字符串为：" + s)).
                collect(Collectors.toList());
        System.out.println("peek的用法： " + list4.toString());

        /**
         * findFirst:  返回stream流中的第一个元素,返回值类型Optional
         */
        System.out.println("---------findFirst的用法-----------------");
        List<String> list5 = Arrays.asList("ab", "cde");
        Optional<String> first = list5.stream().findFirst();
        System.out.println(first.orElse("该集合为空"));

        /**
         * sort排序：排序是一个中间操作，返回的是排序好后的stream操作。如果你不能制定一个自定义的排序，那么就会使用默认排序
         * 它比数组的排序更强的作用是，你在排序前对Stream进行 map， flatMap， filter， limit， distinct， skip来减少元素数量
         * 筛选后进行排序能够大大的缩短执行时间
         */
        System.out.println("---------sorted排序----------------");
        List<String> list6 = Arrays.asList("test","hello","world","java","tom","C","javascript");
        // 1. 按照默认进行排序
        // 默认排序是按照首字母大小从小往大排序
        Stream<String> stream7 = list6.stream().sorted();
        stream7.forEach(System.out::println);
        // 2. 按照字符串长度进行排序
        // sorted调用的是compare函数，compare返回一个int类型的值
        // 如果长度相同，就按照集合中原先的顺序来
        System.out.println("--------------------------");
        list6.stream().sorted((s1, s2) -> s1.length() - s2.length()).forEach(System.out::println);
        // 3. 先筛选出字符串中有t的，然后按照长度大小排序
        System.out.println("-----sorted3---------------");
        list6.stream().filter(s -> s.startsWith("t")).sorted((s1, s2) -> s1.length() - s2.length()).forEach(System.out::println);

        /**
         * Match 匹配:match 提供了多种匹配操作,   allMatch  所有的元素都通过， anyMatch  只要有一个通过,  noneMatch 一个都不通过
         *  所有的匹配都是最终操作，并且返回一个Boolean类型的值。
         */
        System.out.println("----------Match匹配--------------");
        // 如果list的集合所有的string字符串都有t， 那么返回true
        boolean isAllmatch = list6.stream().allMatch(s -> s.startsWith("t"));
        System.out.println("isAllmatch = " + isAllmatch);

        // 2. anyMatch：只要有一个数据成立，就返回true
        boolean anyMatch = list6.stream().anyMatch(s -> s.length() > 5);
        System.out.println("anyMatch = " + anyMatch);

        // 3. noneMatch： 全都不成立，就返回true
        boolean noneMatch = list6.stream().noneMatch(s -> s.isEmpty());
        System.out.println("noneMatch = " + noneMatch);

        /**
         * count：计数是一个最终操作，返回Stream中元素的个数。返回值类型是long
         */
        // 返回list6集合中，字符串长度大于3的字符串个数
        System.out.println("----------count------------");
        long count = list6.stream().filter(s -> s.length() > 3).count();
        System.out.println(count);

    }

}
