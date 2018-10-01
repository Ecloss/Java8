package stream;

import java.util.Arrays;
import java.util.List;
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

        /**
         * 3. map生成的是1:1关系，而flatMap 对应的是1:N 关系
         * map：  <R> Stream<R> map(Function<? super T, ? extends R> mapper);
         * flatMap： <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
         */
        

    }

}
