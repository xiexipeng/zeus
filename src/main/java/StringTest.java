import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> String对象内存测试类 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/6/27 上午9:50
 * @Version V1.0
 */
public class StringTest {

    private String val = "a";

    private static List<String> handle = new ArrayList<>();

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        StringTest stringTest = new StringTest();
        String a = "a";
        String b = "a";
        String c = new String("a");
        stringTest.isImmulitable();
        stringTest.foo(a);

        BigString bigString = new BigString();
        for (int i = 0; i < 1000; i++) {
            handle.add(bigString.getSubString(1,5));
        }

        String s = "a;b";
        s.split("[;]");
        System.out.println(s);

    }

    public void foo(String s) {
        s = "qq";
    }

    /**
     * String 类不变性分析，是否真的不变？
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private void isImmulitable() throws NoSuchFieldException, IllegalAccessException {
        String test = "Hello World_";
        System.out.println("init string: " + test);
        Field valueField = String.class.getDeclaredField("value");
        valueField.setAccessible(true);
        char[] chars = (char[]) valueField.get(test);
        chars[11] = '!';
        System.out.println("new string: " + test);
    }

    static class BigString {
        private String s = new String(new char[100000]);

        public String getSubString(int begin, int end) {
            return s.substring(begin, end);
        }
    }

    static class ImproveBigString {
        private String s = new String(new char[100000]);

        public String getSubString(int begin, int end) {
            return new String(s.substring(begin, end));
        }
    }
}
