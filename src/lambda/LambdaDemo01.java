package lambda;

import org.junit.experimental.theories.internal.SpecificDataPointsSupplier;

import java.util.*;
import java.util.function.Function;

/**
 * @author 余修文
 * @date 2018/9/29 17:03
 */
public class LambdaDemo01 {

    public static void main(String[] args) {

        // 一个list的集合需要排序
        List<String> list = Arrays.asList("hello", "tom", "apple", "bbc");
        // 老写法
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        // lambda表达式
        Collections.sort(list, (String s1, String  s2) -> {
            return s2.compareTo(s1);
        });

        /**
         * 更加简洁的写法
         * 大括号里只有一句代码时，那么可以省略大括号
         * 编译器可以自动推导出参数类型
         */
        Collections.sort(list, (s1, s2) -> s2.compareTo(s1));

        Random random = new Random();
        // 创建一个list用来接收0-100的随机数
        List<Integer> list1 = new ArrayList<Integer>();
        for(int i = 0; i < 20; i++) {
            list1.add(random.nextInt(100));
        }
        System.out.println(list1.toString());
        Collections.sort(list1 , (a, b) -> b.compareTo(a));
        System.out.println(list1.toString());

        System.out.println(list);

    }

}
