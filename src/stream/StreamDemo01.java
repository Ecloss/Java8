package stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream 流的概念
 *
 * @author 余修文
 * @date 2018/10/1 17:06
 */
public class StreamDemo01 {

    public static void main(String[] args) {
        System.out.println("-------------Stream对象的构建----------------");
        /**
         * 1. Stream对象的构建
         */
        // 1. Stream的构建
        // 看底层源码， Stream.of 用的是 Arrays.stream(values); 来构建，因此可以用Arrays进行构建
        Stream<String> stream01 = Stream.of("a", "b", "c");
        // 2. Arrays 用数组构建
        String[] strArr = new String[]{"a", "b", "c"};
        Stream<String> stream03 = Stream.of(strArr);
        Stream<String> stream02 = Arrays.stream(strArr);
        // 3. 利用集合进行构建, 先创建一个集合，在从集合转换成Stream
        // Arrays.asList  就是把数据变成集合
        List<String> list = Arrays.asList(strArr);
        Stream<String> stream04 = list.stream();

        /**
         * 对于基本数值类型，有以下几种Stream流：Stream， IntStream, LongStream, DoubleStream
         * 当然我们也可以用Stream<Integer> Stream<Long>  Stream<Double>
         * 但是自动装箱拆箱会很耗时，所以特别为这三种Stream提供了特定的Stream
         * Java8 中还没提供其他数据类型的Stream
         */

        System.out.println("-------------Stream的基本类型对象的构建----------------");
        /**
         * 2. 数值Stream的创建
         */
        // 1. 使用IntStream.of
        IntStream intStream = IntStream.of(new int[]{1, 2, 3});
        // 2. 使用数组创建
        IntStream intStream1 = Arrays.stream(new int[]{1, 2, 3});
        // 3. 使用rang方法创建， [1, 3)
        IntStream intStream2 = IntStream.range(1, 3);
        // 4. 使用rangClose 创建 [1, 3]
        IntStream intStream3 = IntStream.rangeClosed(1, 3);

        System.out.println("-------------Stream对象转其他类型----------------");
        /**
         * 3. Stream 转其他类型
         */
        Stream<Integer> stream = Stream.of(new Integer[]{1, 2, 3});
        // 1. stream 转数组
        Integer[] intArr = stream.toArray(Integer[]::new);
        // 2. stream 转集合
        List<Integer> list1 = stream.collect(Collectors.toList());
        List<Integer> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
        Set<Integer> set = stream.collect(Collectors.toSet());
        Set<Integer> set1 = stream.collect(Collectors.toCollection(HashSet::new));
        // 3. stream 转为joining
        Stream<String> stream1 = Stream.of(new String[]{"1", "b", "c"});
        String str1 = stream1.collect(Collectors.joining()).toString();

        /**
         * 注意：一个Stream只可以被抛出一次，而上面的代码为了简洁重复了多次
             * 上面的代码直接运行会抛出异常：java.lang.IllegalStateException   违反规定异常
         */

    }

}
