package grammar.unmutable;

/**
 * <p> 不可变类测试 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/12 下午10:03
 * @Version V1.0
 */
public class FinalTest {

    private final String s;

    public String getS() {
        return s;
    }

    public FinalTest(String s) {
        this.s = s;
    }

    public static void main(String[] args) {
        FinalTest finalTest = new FinalTest("s");
//        finalTest.s = "aa";
    }
}
