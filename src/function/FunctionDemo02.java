package function;

import java.util.function.Function;

/**
 * @author 余修文
 * @date 2018/9/29 19:55
 */
public class FunctionDemo02 {

    // 静态内部类
    private static class Student {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Student(String name) {
            this.name = name;
        }

        public Student() {
        }
    }

    @FunctionalInterface
    public interface StudentFactory<Student> {
        Student create(String name);
    }

    public static void main(String[] args) {
        String name = "Tom";
        // 这种方式是用一个构造器创造一个新的对象
        StudentFactory studentFactory = Student::new;
        Student stu1 = (Student) studentFactory.create(name);
        System.out.println(stu1.getName());
        // 创建一个函数式对象
        Function<String, Student> f1 = s -> new Student(s);
        Student stu2 = f1.apply(name);
        System.out.println(stu2.getName());
        Function<String, Student> f2 = s -> new Student(s);
        // Function 作用是，前面是参数，后面是返回。
        Function<String, String> before = (s) -> "HiHi" + s;
        System.out.println("-------------------------------");
        Student stu3 = f2.compose( before).apply(name);
        System.out.println(stu3.getName());

        // andThen 是在apply方法后作用的
        // andThen 的返回类型是Integer的话，apply的返回类型也会改变成Integer
        Function<Student, Integer> after = stu -> stu.getName().length();
        int len = f2.andThen(after).apply(name);
        System.out.println("--------------------------------");
        System.out.println(len);

        // andThen例子2：
        Function<Student, String> after1 = stu -> stu.getName();
        String stu4 = f1.andThen(after1).apply(name);
        System.out.println("--------------------------------");
        System.out.println(stu4);

        // 创建一个空的函数式编程
        Function<String, Student> f3 = (s) -> new Student();
    }

}
