package gc;

import java.util.Vector;

/**
 * @author: xiexipeng@u51.com
 * @create: 2019/09/11 00:01:30
 * @description: 测试最小堆内存: -Xmx11m -Xms4m -Xmn2 -verbose:gc -XX:+PrintGCDetails
 * @Version
 **/
public class TestXmsHeap {

    public static void main(String[] args) {
        Vector vector = new Vector();
        for (int i = 0; i < 10; i++) {
            byte[] b = new byte[1024*1024];
            vector.add(b);
            if (vector.size()==3){
                vector.clear();
            }
        }
    }
}
