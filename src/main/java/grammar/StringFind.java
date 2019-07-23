package grammar;

import java.util.Arrays;
import java.util.Stack;

/**
 * <p>  </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/7/16 下午9:40
 * @Version
 */
public class StringFind {

    // 1.1.1，判断字符串中所有字符是否全部不同
    public boolean isChartEqual(String s) {
        char[] a = s.toCharArray();
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] == a[j])
                    return false;
            }
        }
        return true;
    }

    // 1.1.2，判断字符串中所有字符是否全部不同
    public boolean isChartEqual2(String s) {
        if (s.length() > 256)
            return false;
        // 初始化boolean类型是false
        boolean[] char_set = new boolean[256];
        for (int i = 0; i < s.length(); i++) {
            // 得到该字符的ASCII码（在ASCII码中的位置）
            int val = s.charAt(i);
            if (char_set[val])
                return false;
            // 置为true
            char_set[val] = true;
        }
        return true;
    }

    // 1.2.1，翻转一个null结尾的字符串
    public String exchangeChar(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }
        char[] s = new char[str.length()];
        int j = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            s[j] = str.charAt(i);
            j++;
        }
        return new String(s);
    }

    // 1.2.2，翻转一个null结尾的字符串(使用堆栈先进后出的思想)
    public String reverse5(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }
        String result = "";
        char[] arr = str.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        for (char a : arr) {
            stack.push(a);
        }
        int length = stack.size();
        // 注意这个地方必须要先把length暂存起来，因为在遍历的过程中，pop()堆的时候，会改变堆的大小。
        for (int i = 0; i < length; i++) {
            result += stack.pop();
        }
        return result;
    }

    // 1.3.1判断一个字符串的字符重新排列后，能否变成另一个字符串
    public boolean exchangeCharIsEqual(String excStr, String str) {
        if (excStr.length() != str.length())
            return false;
        int[] excCharSet = new int[256];
        int[] charSet = new int[256];
        for (int i = 0; i < excStr.length(); i++) {
            int val = excStr.charAt(i);
            excCharSet[val]++;
            for (int j = i + 1; j < excStr.length(); j++) {
                if (excStr.charAt(i) == excStr.charAt(j)) {
                    excCharSet[val]++;
                }
            }
        }
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            charSet[val]++;
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    charSet[val]++;
                }
            }
        }
        if (Arrays.equals(excCharSet, charSet)) {
            return true;
        }
        return false;
    }

    // 1.3.2判断一个字符串的字符重新排列后，能否变成另一个字符串
    public boolean exchangeCharIsEqual2(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] charSet = new int[256];
        char[] sArray = s.toCharArray();
        for (char c : sArray) {
            charSet[c]++;
        }
        for (int i = 0; i < t.length(); i++) {
            int c = t.charAt(i);
            if (--charSet[c] < 0) {
                return false;
            }
        }
        return true;
    }

    // 1.3.3判断一个字符串的字符重新排列后，能否变成另一个字符串
    public boolean exchangeCharIsEqual3(String s, String t) {
        if (s.length() != t.length())
            return false;
        char[] char1 = s.toCharArray();
        char[] char2 = t.toCharArray();
        Arrays.sort(char1);
        Arrays.sort(char2);
        return new String(char1).equals(new String(char2));
    }

    // 1.4.1将字符串中的空格替换为“%20”
    public String changeSpace(String str) {
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                j++;
            }
        }
        String[] s = new String[j * 2 + 1];

        for (int i = 0; i < str.length(); i++) {
            int k = 0;
            int l = 0;
            if (str.charAt(i) == ' ') {
                if (i == 0) {
                    s[l++] = "%20";
                    k = i;
                } else {
                    s[l] = str.substring(k + 1, i);
                    s[++l] = "%20";
                    k = i;
                    l++;
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length; i++) {
            sb.append(s[i]);
        }
        return sb.toString();
    }

    // 1.4.2将字符串中的空格替换为“%20”
    public String changeSpace2(char[] str, int length) {
        int j = 0, newLength;
        for (int i = 0; i < length; i++) {
            if (str[i] == ' ') {
                j++;
            }
        }
        newLength = length + j * 2;
        char[] t = new char[newLength];
        // t[newLength] = '\0';
        for (int i = length - 1; i > 0; i--) {
            if (str[i] == ' ') {
                t[newLength - 1] = '0';
                t[newLength - 2] = '2';
                t[newLength - 3] = '%';
                newLength = newLength - 3;
            } else {
                t[newLength - 1] = str[i];
                newLength--;
            }
        }
        return new String(t);
    }

    public String compressBad(String str) {
        if (str == null || str.length() < 1) {
            return str;
        }
        String newString = "";
        int count = 1;
        char last = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == last) {
                count++;
            } else {
                newString += last + "" + count;
                last = str.charAt(i);
                count = 1;
            }
        }
        return newString + last + "" + count;
    }

    public static void main(String[] args) {
        StringFind sf = new StringFind();
        System.out.println(sf.isChartEqual("advil"));
        System.out.println(sf.isChartEqual2("advil"));
        System.out.println(sf.exchangeChar("abcd"));
        System.out.println(sf.exchangeCharIsEqual("abcda", "abdcc"));
        System.out.println(sf.changeSpace2("my name is xiexipeng".toCharArray(), "my name is xiexipeng".length()));
        System.out.println(sf.compressBad("aabbbc"));
    }
}