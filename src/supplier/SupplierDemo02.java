package supplier;

import org.junit.Test;

import java.util.Random;
import java.util.function.Supplier;

/**
 * 有一个错误：Java的Junit单元测试，不能和函数式编程一起使用
 * 我使用的Supplier
 *
 * @author 余修文
 * @date 2018/9/30 20:04
 */
public class SupplierDemo02 {

    public static void main(String[] args) {
        /**
         * 用Supplier获得0-100的5个随机数的和
         */
        Supplier<Integer> supplier = () -> {
            Random random = new Random();
            Integer num = 0;
            for(int i = 0; i < 5; i++) {
                num += random.nextInt(100);
            }
            return num;
        };
        System.out.println(supplier.get());
    }

}
