package stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
                map(i -> i * i).
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
        List<String> list6 = Arrays.asList("test", "hello", "world", "java", "tom", "C", "javascript");
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

        /**
         * 规约合并reduce
         * 这是一个最终操作，允许通过制定的函数来将stream中的多个元素规约合并成一个元素
         * 它提供了一个起始值，然后按照运算规则，和前面的Stream的第一个，第二个，第n个元素组合
         * 常用的方法有average，sum，min，max，and，count，返回单个的结果值
         * 并且reduce操作每处理一个元素总是创建一个新值
         */
        System.out.println("-----------------reduce----------------");
        // 比如stream的sum就相当于
        // [1, 10)
        IntStream intStream = IntStream.range(1, 10);
        IntStream intStream1 = IntStream.range(1, 10);
        // 转换成，
        Integer sum1 = intStream.reduce(5, (a, b) -> (a + b));
        // 或者
        Integer sum2 = intStream1.reduce(1, Integer::sum);
        // 起始值：start + a + b
        System.out.println("返回结果为起始值为5sum1 = " + sum1 + "；或者起始值1sum2 = " + sum2);

        // 也有没有起始值的情况，这时候会把stream的前面两个元素组合起来，返回的是optional， 比如min， max
        IntStream intStream2 = IntStream.range(1, 10);
        OptionalInt optionalInt = intStream2.reduce((a, b) -> a < b ? a : b);
        // max
        IntStream intStream3 = IntStream.range(1, 10);
        OptionalInt optionalInt1 = intStream3.reduce((a, b) -> a > b ? a : b);

        // 对于reduce来说，有起始值会返回相应的对应类型，如果没有起始值，会返回一个Optional<U> 的类型
        // 比如：contact字符串连接
        String str = Stream.of("a", "b", "c", "d").reduce("", String::concat);
        System.out.println(str);
        Optional<String> optionalS = Stream.of("a", "b", "c", "d").reduce(String::concat);

        // 写一个reduce的模板
        Optional<String> optionalS1 = list6.stream().
                sorted((s1, s2) -> s1.length() - s2.length()).
                filter(s -> s.startsWith("t")).
                map(s -> s + "perper").
                reduce((s1, s2) -> s1 + " | " + s2);
        /**
         * 1. 先创建一个steam流对象
         * 2. 然后将流对象中筛选出首字母为 t 的
         * 3. 将筛选出来的stream，每个数据在字母最后加上 + "perper"
         * 4. 将所有的数据，用 | 规约合并起来
         */
        System.out.println(optionalS1.get());

        /**
         * limit, skip
         * limit：得到前n个元素
         * skip：跳过前n个元素
         */
        List<String> list7 = Arrays.asList("test","javap","hello","world","java","tom","C","javascript");
        // limit 选择前五个元素    "test","javap","hello","world","java"
        Stream<String> stream8 = list7.stream().limit(5);
        // skip 跳过前五个元素     "tom","C","javascript"
        Stream<String> stream9 = list7.stream().skip(5);

        /**
         * Collector 收集元素, 辅助进行各类有用的操作
         * 例如把stream转变为Collection， 或者把Stream 元素进行分组
         */
        List<String> list8 = Arrays.asList("test","hello","world","java","tom","C","javascript");
        List<String> result = list8.stream().filter(s -> s.startsWith("t")).collect(Collectors.toList());

        System.out.println("------按照字符串长度进行分组------------");
        // 按照字符串的长度进行分组
        Map<Integer, List<String>> collect = list8.stream().collect(Collectors.groupingBy(s -> s.length()));
        System.out.println(collect.toString());

        /**
         * 并行和串行stream
         */
        // 生成100万个不同的字符串放到集合中去
        int max = 1000000;
        List<String> values = new ArrayList<String>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        System.out.println("-----查看并行时间------------");
        // 1纳秒 * 10^9 = 1秒
        long t0 = System.nanoTime();
        // 串行stream     串行时间：832311645
        //long count1 = values.stream().sorted().count();
        // 并行stream     并行时间：474983692      时间减少了近2倍
        long count2 = values.parallelStream().sorted().count();

        // 结束时间：t1
        long t1 = System.nanoTime();

        long time = t1 - t0;
        System.out.println(count2);
        System.out.println(time);

    }

}
