package com.xxp.algorithms.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 阻塞队列 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/19 下午10:04
 * @Version V1.0
 */
public class BlockQueue<T> {

    private List<T> list = new ArrayList<>();

    public synchronized T pop() throws InterruptedException {
        // 这里可不可以用if条件？不能用if，线程收到通知需要再执行，必须再while循环体内
        while (list.size() == 0) {
            this.wait();
        }
        if (list.size() > 0) {
            return list.remove(0);
        } else {
            return null;
        }
    }

    public synchronized void put(T t) {
        list.add(t);
        this.notify();
    }

    public static void main(String[] args) {
        byte[] bytes = "j".getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
        }
        System.out.println("");
    }
}
