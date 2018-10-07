package function;

import java.util.function.Function;

/**
 * @author 余修文
 * @date 2018/10/3 12:19
 */
public class FunctionDemo03 {

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
    public interface FunctionStudent<Student> {
        public FunctionDemo03.Student create(String name);
    }

    public static void main(String[] args) {

        // 用FunctionStudent 创造一个Student对象
        // 1. 创造一个
        String name = "Tom";
        FunctionStudent student = Student::new;
        Student student1 = student.create(name);
        System.out.println(student1.getName());

        // 创建一个Function函数式对象
        Function<String, Student> function = s -> new Student(s);
        // function的特点是，传入string参数，返回一个student类型的Function对象
        // apply方法会返回一个Student的对象
        Student student2 = function.apply(name);
        System.out.println("function = " + student2.getName());

    }

}
