#stream

>     java.util.Stream 表示能应用在一组元素上一次执行的操作序列。
>      Stream 操作分为中间操作 和 最终操作， 中间操作返回Stream本身， 
>      而最终操作则返回 一定类型的计算结果，这样就可以将多个操作依次串起来(链式编程) 
>     Stream的创建需要制定一个数据源，比如java.util.Collection 的子类 ，List 或Set集合，但是map不支持  
>     Stream 流的操作可以串行执行或并行执行。
>     Stream 作为java8 的一大亮点，它与java.io 中的inputStream， outputStream不同
>     Java8 中的Stream是对集合对象功能的增强
>     Stream能够对集合进行高效的聚和操作，大批量数据操作，并且十分便利
>     
>     Stream API 借助于同样新出现的Lambda表达式，极大的提高编程效率和可读性
>     同时它有串行 和 并行 两种操作      