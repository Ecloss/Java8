package lambda;

import java.util.*;
import java.util.stream.Collector;

/**
 * @author 余修文
 * @date 2018/10/3 12:01
 */
public class LambdaDemo02 {

    public static void main(String[] args) {

        // 如果我要给一个集合排序： 从小到大排序
        Integer[] a = new Integer[]{1, 3, 5, 4, 2};
        List<Integer> list = Arrays.asList(a);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return  o1.compareTo(o2);
            }
        });
        System.out.println(list.toString());

        // 新方发：Lambda表达式:   让集合从大到小进行排序
        Collections.sort(list, (Integer i1, Integer i2) -> i2.compareTo(i1));
        System.out.println(list.toString());

        /**
         * 得到从1-100的20个随机数字，并且保存到集合中，
         * 并且按照顺序排序
         */
        List<Integer> list1 = new ArrayList<Integer>();
        Random random = new Random();
        for(int i = 0; i < 20; i++) {
            list1.add(random.nextInt(100));
        }
        // 先得到乱序的集合
        System.out.println(list1.toString());
        // 接下来按照从大到小顺序排序
        Collections.sort(list1, (Integer i1, Integer i2) -> i2.compareTo(i1));
        System.out.println(list1.toString());

    }

}
