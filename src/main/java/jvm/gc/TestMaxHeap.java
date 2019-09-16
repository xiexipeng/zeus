package jvm.gc;

import java.util.Vector;

/**
 * @author: xiexipeng@u51.com
 * @create: 2019/09/10 23:24:36
 * @description: 最大堆内存: -Xmx5M ,只循环了3次便退出了，还有1M内存在哪？
 * @Version
 **/
public class TestMaxHeap {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Max memory:" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
        System.out.println("Total memory:" + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
        Vector<byte[]> list = new Vector<>();
        for (int i = 1; i < 10; i++) {
            byte[] b = new byte[1024 * 1024];// 分配1M内存
            list.add(b);
            System.out.println(i + "M is allocated");
            System.out.println("Free memory:" + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        }

    }
}
