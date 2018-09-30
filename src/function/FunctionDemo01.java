package function;

import org.junit.Test;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author 余修文
 * @date 2018/9/29 19:49
 */
public interface FunctionDemo01<T, R> {
    R apply(T t);

    /**
     * 返回一个先执行before函数对象apply方法再执行当前函数对象apply方法的函数对象
     */
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * 返回一个先执行当前函数对象apply方法再执行after函数对象apply方法的函数对象
     */
    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }


}
