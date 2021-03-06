package optional;

import java.util.*;
import java.util.Optional;
import java.util.function.*;

import java.util.*;
import java.util.Optional;
import java.util.function.*;
import java.util.*;
import java.util.Optional;
import java.util.function.*;

import java.util.*;
import java.util.Optional;
import java.util.function.*;

import java.util.*;
import java.util.Optional;
import java.util.function.*;

/**
 * Optional：供应商的意思
 * Optional 不是接口而是一个静态内部类，这是个用来防止NullPointerException异常的辅助类型
 * Optional 被定义为一个简单的容器，其值可能是null或者不是null
 * 在Java8之前一般某个函数应该返回非空对象但是偶尔却可能返回了null，而在Java 8中，不推荐你返回null而是返回Optional
 * 这是一个可以为null的容器对象
 * 如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象
 * 所谓的函数式接口：要注意三点
 * 1. 带入的参数
 * 2. 返回的类型
 * 3. 函数式接口需要做的操作
 *
 * @author 余修文
 * @date 2018/9/30 9:58
 */
public class OptionalDemo01 {

    /**
     * requireNonNull(T obj, Supplier<String> messageSupplier), 是否为空，可以自己定义一个Lambda类， Supplier<String>
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("------------of方法----------------");
        /**
         * of方法 为非null的值创建一个Optional类
         * 需要注意的是，创建对象时传入的参数不能为Null
         * 如果传入的参数为null， 那么抛出异常NullPointerException
         */
        Optional<String> op1 = Optional.of("Hello123");
        System.out.println(op1.get());
        Optional<String> op8 = Optional.ofNullable(null);
        //Optional<String> op9 = Optional.of(null);

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
        for (int i = 0; i < 8; i++) {
            list1.add(i);
        }
        List<Integer> list2 = new ArrayList<Integer>();
        // 2. 创建一个consumer对象
        Consumer<List<Integer>> conList1 = list3 -> System.out.println(list3.toString());
        // 3. 创建一个Optional对象
        Optional<List<Integer>> opList03 = Optional.ofNullable(list1);
        opList03.ifPresent(conList1);
        System.out.println("----------测试完带参数的后--------------");
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
        System.out.println("--------------orElseGet()用法------------");

        /**
         * orElseGet() 方法, 与orElse 方法不同，如果为空，会返回一个supplier的函数式接口
         * orElse返回的是与Optional同类型的值
         * 而orElseGet()  方法返回的是一个Supplier, 但是Supplier()里的对象必须是参数的类本身，或是相应的子类
         */
        // 输入一个相应的字符串， 如果输入的为null， 那么随机生成一个8的字符串
        Scanner in = new Scanner(System.in);
        String str2 = "Hello World, Keep Coding";
        String str3 = null;
        //System.out.println("请输入字符串str4：");
        // 输入一个字符串
        //String str4 = in.nextLine();
        // 1. 创建一个Optional对象
        Optional opStr1 = Optional.ofNullable(str2);

        // 2. 创建一个supplier对象
        Supplier<String> supplier = () -> {

            return "This is null";
        };
        // 3. 使用orElseGet方法
        /**
         * 输入：HelloWorld
         * 返回：HelloWorld
         */
        System.out.println(opStr1.orElseGet(supplier));

        // 创建一个为空的字符串
        Optional opStr2 = Optional.ofNullable(str3);
        // 调用orElseGet方法
        // 返回结果：This is null
        System.out.println(opStr2.orElseGet(supplier));

        // 换一种更简洁的写法
        System.out.println(opStr2.orElseGet(() -> "Hello Wo.....null null"));

        System.out.println("----------orElseThrow方法------------");
        /**
         * orElseThrow用法：如果Optional不为空，返回Optional对象，如果为空就抛出异常
         * 源码中，也是调用supplier对象
         */
        String str5 = null;
        String str6 = "More Null";
        // 1. 创建一个String类型的Optional对象
        Optional<String> optStr4 = Optional.ofNullable(str5);
        Optional<String> opStr3 = Optional.ofNullable(str6);
        //System.out.println(optStr4.orElseThrow(() -> {return new NullPointerException();}));
        try {
            // 测试参数为str6的Optional
            // 返回结果：More null
            System.out.println(opStr3.orElseThrow(Exception::new));
            // 测试参数为str5 的对象
            //System.out.println(optStr4.orElseThrow(() -> new NullPointerException()));
        } catch (Exception e) {
            if (str5.isEmpty()) {
                System.out.println("str5 为空");
            }
            e.printStackTrace();
        }

        System.out.println("------------------map用法-------------------");
        /**
         * T 是传入参数， U是返回类型
            public<U> Optional<U> map(Function<? super T, ? extends U> mapper) {
         *      判断mapper是否为空
         *      Objects.requireNonNull(mapper);
         *      判断本类是否不存在，如果不存在，返回empty
         *       if (!isPresent())
         *           return empty();
         *       else {
         *       返回 的类型与U的类型一直，value为T的参数
         *           return Optional.ofNullable(mapper.apply(value));
         *       }
         *   }
         */
        /**
         * map方法，如果有值，则执行Function的mapper函数，得到返回值与Optional一致
         * map方法本身就是对原来的Optional进行操作筛选，并且返回同样的Optional对象
         */
        String str = "abcde";
        Optional<String> op = Optional.ofNullable(str);
        // 如果用这种写法的话，s就是一个object类型
        Optional<Integer> map = op.map(s -> 1);
        System.out.println("map = " + map.get());
        // 写的稍微复杂一些
        System.out.println("---------Function------------------");
        Function<String, Integer> f1 = s -> s.charAt(4) - s.charAt(0);
        System.out.println(f1.apply("abcde").toString());
        Optional<Integer> map1 = op.map(
                s -> str.charAt(4) - str.charAt(0)
        );
        /**
         * 这种嵌套写法，会导致s -> 的s是Object类型，因此要进行强转换才行
         */
        Optional<Integer> map3 = op.map(s -> {
            String st1 = (String)s;
            return st1.charAt(4) - st1.charAt(0);
        });
        System.out.println("001 -- map3 = " + map3.get());
        System.out.println("map1 = " + map1.get());
        System.out.println("002 -- map4 = " + op.map(s -> s + "today is 10/01").get());
        // 先创建一个Function
        Function<String, Integer> function = s -> str.charAt(2) - str.charAt(0);
        System.out.println("map2 = " + op.map(function).get());

        /**
         * flatMap： 与map的用法相似，但是唯一不同的是，flatMap 的 mapper返回值必须是Optional
         * 调用结束时，flatMap不会对结果进行封装，如果返回的不是Optional(U) 的类型，就强制类型转换
         */
        String  str1 = "abcde";
        Optional<String> op4 = Optional.ofNullable(str1);
        // flatMap 的用法
        System.out.println("flatMap的返回结果：" +  op4.flatMap(s -> Optional.ofNullable(s + "_briup")).get());
        // map 的用法
        System.out.println("map的返回结果：" + op4.map(s -> s + "briup").get());
        System.out.println("map是否是String字符串："+ op4.map(s -> s + "map op4"));

        System.out.println("----------filter的用法----------------");

        /**
         * filter方法: 就相当于一个筛选条件，
         * filter 调用 Predicate， 会传入一个test类型的值，
         * 然后在返回同样的Optional(U) 的类型
         *
         */
        Predicate<String> predicate = s -> s.length() > 10;
        String str4 = "abcdefg";
        Optional<String> op5 = Optional.ofNullable(str4);
        Optional<String> op6 = op5.filter(predicate);
        System.out.println(op6.orElse("str长度不超过10，所以跳到这边来了"));
        // 用更加简洁的写法来做
        Optional<String> op7 = op5.filter(s -> s.length() < 10);
        System.out.println(op7.orElse("str长度大于10 ， 所以跳到这儿来了"));

        /**
         * Stream流对象
         */

    }

}

/**
 * 以单例模式创建一个Optional对象
 */
class OptionalSingleton{
    private static Optional<Integer> optional = Optional.ofNullable(null);

    public OptionalSingleton() {

    }

    private static Optional<Integer> getInstance() {
        if (optional == null) {
            optional = Optional.ofNullable(0);
        }
        return  optional;
    }
}
