package consumer;

import java.util.function.Consumer;

/**
 * Consumber   消费者，只需要带入参数，
 * 不需要返回任何类型
 * andThen 方法就是 在accept使用后，在次用原来的参数进行操作
 *
 * @author 余修文
 * @date 2018/9/30 20:18
 */
public class ConsumerDemo02 {

    public static void main(String[] args) {
        // 将accept方法传入，那么可以输出ax+1
        Consumer<Integer> consumer = ax -> {
            System.out.println(ax + 1);
        };
        consumer.accept(1);
        consumer.andThen(i -> System.out.println(i + 10)).accept(1);
    }

}
