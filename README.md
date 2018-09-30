# Java8新特性

Java8新特性

---


> Function<T, R> 接口 R apply(T t); 有参数有返回值\
> Supplier<T> 接口 T get();         没参数有返回值\
> Consumer<T> 接口 void accept(T t); 有参数没返回值\

> 另外需要注意的接口: 其用法和上面介绍的接口使用方式类同\
BinaryOperator<T>接口 T apply(T t, T t) 将两个T作为输入，返回一个T作为输出\
BiFunction<T, U, R>接口 R apply(T t, U u) 将一个T和一个U输入，返回一个R作为输出\
BinaryOperator接口继承了BiFunction接口\
public interface BinaryOperator<T> extends BiFunction<T,T,T>\

 > BiConsumer<T, U>接口 void accept(T t, U u) 将俩个参数传入，没有返回值\

| 学习路线 |  知识点  |
| :----: | :----: |
| 1 | lambda  |
| 2 |  function |
| 3 |  supplier |
| 4 |  consumer |
| 5 |  optional |
| 6 |   |
| 7 |   |
