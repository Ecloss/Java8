package consumer;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * Consumer接口不返回任何的值:
 * Consumer接口的抽象方法是accept， 该接口需要传入参数，不返回任何值
 * consumer：中文是消费者，所以也可以理解为，只带钱进入这个商场，不能赚到钱，会消费这个钱(对象)
 * andThen 也是同理
 *
 * @author 余修文
 * @date 2018/9/30 8:57
 */
public class ConsumerDemo01 {

    private static class Student1{
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Student1(String name) {
            this.name = name;
        }

        public Student1() {
        }
    }

    public static void main(String[] args) {
        Student1 stu = new Student1();
        stu.setName("Salary");
        Student1 stu2 = new Student1("Yueel");

        Consumer<Student1>  consumer = student1 -> System.out.println("Hi everybody, I am " + student1.getName());
        consumer.accept(stu);
        System.out.println("----------------------------------");

        //Consumer<String> stu2 = str -> {return new Student1(str);};
        // 所谓的andThen方法就是，在accept执行后做操作, 而且andThen会带入参数，与accept的参数一致，但是不会返回任何值
        Consumer<Student1> after = stu1  -> System.out.println("Hi, I am after the Sayary, I am " + stu2.getName());
        consumer.andThen(after).accept(stu);
    }

}
