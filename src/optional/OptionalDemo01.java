package optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Optional 不是接口而是一个类，这是个用来防止NullPointerException异常的辅助类型
 * Optional 被定义为一个简单的容器，其值可能是null或者不是null
 * 在Java8之前一般某个函数应该返回非空对象但是偶尔却可能返回了null，而在Java 8中，不推荐你返回null而是返回Optional
 * 这是一个可以为null的容器对象
 * 如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象
 *
 * @author 余修文
 * @date 2018/9/30 9:58
 */
public class OptionalDemo01 {

    /**
     * requireNonNull(T obj, Supplier<String> messageSupplier), 是否为空，可以自己定义一个Lambda类， Supplier<String>
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("------------of方法----------------");
        /**
         * of方法 为非null的值创建一个Optional类
         * 需要注意的是，创建对象时传入的参数不能为Null
         * 如果传入的参数为null， 那么抛出异常NullPointerException
         */
        Optional<String> op1 = Optional.of("Hello");

        /**
         * 传入一个为null的值
         * 抛出异常：java.lang.NullPointerException
         */
        //Optional<String> op2 = Optional.of(null);

        /**
         * 如果加入一个空集合会怎么样
         */
        List<Integer> list = new ArrayList<Integer>();
        /**
         * 不会报任何异常
         */
        Optional<List<Integer>> opList1 = Optional.of(list);
        // 返回结果：Optional[[]]
        System.out.println(opList1.toString());
        System.out.println("---------------------");
        /**
         * add几个值进去，
         * 返回结果：Optional[[1]]
         */
        list.add(1);
        Optional<List<Integer>> opList2 = Optional.of(list);
        System.out.println(opList2.toString());
        System.out.println("---------------------");

        /**
         * ofNullable方法 为指定的值创建一个Optional，如果值为null，则返回一个空的Optional
         * 如果不为null，那么就可以给指定的值类型创建一个空的Optional对象
         * ofNullable方法 与of方法类似，唯一的区别是可以接受参数为null的情况
         */
        Optional<String> op2 = Optional.ofNullable("abc");
        System.out.println(op2.toString());
        System.out.println("---------------------");
        /**
         * 接下来返回一个为null的optional
         * 返回结果：Optional.empty
         * ofNullable与of的区别就是：ofNullable可以接收为空的值，并且返回值  Optional.empty
         */
        Optional<String> op3 = Optional.ofNullable(null);
        System.out.println(op3.toString());
        System.out.println("---------------------");

        /**
         * ifPresent：如果存在
         * 如果Optional 存在值，则为其调用consumer，否则不做处理
         * 如果存在，那么可以该参数进行操作
         */
        // 1. 创建一个不为空的集合，一个为空的集合
        List<Integer> list1 = new ArrayList<Integer>();
        for(int i = 0; i < 8; i++) {
            list1.add(i);
        }
        List<Integer> list2 = new ArrayList<Integer>();
        // 2. 创建一个consumer对象
        Consumer<List<Integer>> conList1 = list3 -> System.out.println(list3.toString());
        // 3. 创建一个Optional对象
        Optional<List<Integer>> opList03 = Optional.ofNullable(list1);
        opList03.ifPresent(conList1);
        System.out.println("--------测试完带参数的后--------------");
        Optional<List<Integer>> opList04 = Optional.ofNullable(list2);
        opList04.ifPresent(conList1);

        System.out.println("----------测试orElse方法-----------------");

        /**
         * orElse方法
         * 源码：public T orElse(T other) {
         *                  return value != null ? value : other;
         *           }
         * 如果不为null，返回value的值， 如果为null，返回其他
         */
        // 1, 定义两个String，一个为空，一个不为空
        Optional<String> optional5 = Optional.ofNullable("Hello");
        Optional<String> optional6 = Optional.ofNullable(null);
        String exceStr = "Str字符串为空，并且产生了异常";
        // 2. orElse 方法很累是  empty str ? other : str;
        // 返回结果： Hello
        System.out.println(optional5.orElse(exceStr));
        // 返回结果：Str字符串为空，并且产生了异常
        System.out.println(optional6.orElse(exceStr));

    }

}
