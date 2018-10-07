package object;

/**
 * @author 余修文
 * @date 2018/10/7 9:56
 */
class Address {
    String detail;
    public Address (String detail) {
        this.detail = detail;
    }
}

class User implements Cloneable {
    int age;
    Address address;

    public User(int age, Address address) {
        this.age = age;
        address = new Address("江西鄱阳");
    }

    public User clone() throws CloneNotSupportedException {
        return (User)super.clone();
    }
}

public class ObjectDemo {

    public static void main(String[] args) {
        Object o1 = new Object();

    }

}
