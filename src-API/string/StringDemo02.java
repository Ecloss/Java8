package string;

/**
 * @author 余修文
 * @date 2018/10/7 11:10
 */
public class StringDemo02 {

    public static void main(String[] args) {
        String str = "abcd";
        String str1 = new String("abcd");
        // String 的equals方法  和==  方法
        // 答案是false，必须是两个String的hash码相同才行，比较两个String的地址是否相同
        System.out.println(str == str1);
        // String 方法的equal:  equal方法代表两个String的值是否相同：比较
        System.out.println(str.equals(str1));

        // 在常量池中给str分配了一个空间给  “abcd”

        String str2 = "abc";
        String str3 = "abc";
        // 返回true，这是因为str3 在常量池中可以找到“abc”  这三个范围
        System.out.println(str2 == str3);
    }

}
