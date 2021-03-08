package grammar;

/**
 * <p> 浮点数比较 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/7/6 下午9:00
 * @Version V1.0
 */
public class FloatEquals {

    public static void main(String[] args) {
        float f1 = 1.0f - 0.9f;
        float f2 = 0.9f - 0.8f;
        System.out.println(f1 == 2);

        Float x = Float.valueOf(f1);
        Float y = Float.valueOf(f2);
        System.out.println(x.equals(y));

        int i = 1;
        test1(i);
        System.out.println("a".hashCode());
    }

    public static void test1(long i){
        System.out.println(".......");
    }

    public static void test1(Integer i){
        System.out.println(">>>>>");
    }
}
