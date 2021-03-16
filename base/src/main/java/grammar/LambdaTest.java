package grammar;

import java.util.*;

/**
 * <p> lambda表达式语法测试 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/7/30 下午3:24
 * @Version V1.0
 */
public class LambdaTest {

    public static void main(String[] args) {
        testOptional();
    }

    private static void testOptional(){
        Integer integer = null;
//        System.out.println(Optional.of(integer).orElse(new Integer(2)));

        System.out.println(String.format("Object 1",Optional.ofNullable(integer).orElse(new Integer(2))));

        Integer integer1 = new Integer(1);
        System.out.println(String.format("Object 2",Optional.ofNullable(integer1).orElse(new Integer(2))));

        List<Long> list = Arrays.asList(1L);
        System.out.println(String.format("Object 3",list.stream().min(Comparator.comparing(Long::longValue)).orElse(2L)));

        System.out.println(String.format("Object 4",list.stream().filter(l -> l.equals(1L)).findFirst().orElse(4L)));

        System.out.println(String.format("Object 5",list.stream().filter(l -> l.equals(3L)).findFirst().orElse(4L)));

        System.out.println(String.format("Object 6",list.stream().filter(l -> l.equals(3L)).findFirst().get()));

    }
}
