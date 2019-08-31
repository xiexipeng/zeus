package jvm.gc;

/**
 * @author: xiexipeng@u51.com
 * @create: 2019/08/31 22:22:13
 * @description: 堆内存GC测试
 * @Version V1.0
 **/
public class TestHeapGC {

    public static void main(String[] args) {
        byte[] b1 = new byte[1024 * 1024 / 2];
        byte[] b2 = new byte[1024 * 1024 * 8];
        b2 = null;
        b2 = new byte[1024 * 1024 * 8];
//        System.gc();
    }
}
