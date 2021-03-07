package grammar;

/**
 * <p> switch NPE问题 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/7/6 下午8:55
 * @Version V1.0
 */
public class SwitchNPE {

    public static void main(String[] args) {
        switchNPETest(null);
    }

    private static void switchNPETest(String s){
        switch (s){
            case "1":
                System.out.println(">>>");
                break;
            case "2":
                System.out.println("...");
                break;
            default:
                System.out.println("???");
        }
    }
}
