/**
 * <p> String对象内存测试类 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/6/27 上午9:50
 * @Version V1.0
 */
public class StringTest {

    private String val = "a";

    public static void main(String[] args){
        StringTest stringTest = new StringTest();
        String a = "a";
        String b = "a";
        int i = 1;
        String c = new String("a");
        stringTest.foo(a);
    }

    public void foo(String s){
        s = "qq";
    }
}
