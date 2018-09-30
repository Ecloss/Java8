package supplier;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author 余修文
 * @date 2018/9/30 8:39
 */
public class SupplierDemo01 {

    public static void main(String[] args) {
        /**
         * string 是要返回的类型， 该函数没有任何参数
         * 此代码是为了返回一个随机的字符串，长度为8
         */
        Supplier<String> supplier = () -> {
            String str = "abcdefghijklmnopqrstuvwxyz0123456789";
            Random random = new Random();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < 8; i++) {
                int num = random.nextInt(str.length());
                stringBuffer.append(str.charAt(num));
            }
            return  stringBuffer.toString();
        };
        // Supplier 的 get 方法返回String这一个结果集
        System.out.println(supplier.get());
    }

}
