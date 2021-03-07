package algorithms;

import java.util.Random;

/**
 * <p> 跳跃链表 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/6/12 上午12:45
 * @Version V1.0
 */
public class SkipList {

    private SkipListNode header;
    private SkipListNode tail;

    private int size;
    private int level;

    private final Random random = new Random();

    static class SkipListNode {
        final String key;
        Integer value;

        SkipListNode pre, next, up, down;


        private static final String HEADER_KEY = String.valueOf(Integer.MIN_VALUE);

        private static final String TAIL_KEY = String.valueOf(Integer.MAX_VALUE);

        public SkipListNode(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

    }

    public SkipList() {
        this.header = new SkipListNode(SkipListNode.HEADER_KEY, null);
        this.tail = new SkipListNode(SkipListNode.TAIL_KEY, null);

        header.next = tail;
        tail.pre = header;
    }

    public Integer put(String key, Integer value) {
        SkipListNode p;
        int i = 0;
        p = findNode(key);
        if (p.key.equals(key)) {
            Integer oldValue = p.value;
            p.value = oldValue;
            return oldValue;
        }
        SkipListNode node = new SkipListNode(key, value);
        node.pre = p;
        node.next = p.next;
        p.next.pre = node;
        p.next = node;
        while (random.nextDouble() < 0.5) {
            if (i >= level) {
                addEmptyLevel();
            }
            while (p.up == null) {
                p = p.pre;
            }
            p = p.up;
            SkipListNode s = new SkipListNode(key, null);
            s.pre = p;
            s.next = p.next;
            p.next.pre = s;
            p.next = s;

            node.up = s;
            s.down = node;

            node = s;

            i++;
        }
        size++;
        return null;
    }

    public Integer get(String key) {
        SkipListNode p;
        p = findNode(key);
        if (p.key.equals(key)) {
            return p.value;
        }
        return null;
    }


    public Integer remove(String key) {
        SkipListNode p;
        p = findNode(key);
        if (!p.key.equals(key)) {
            return null;
        }
        Integer oldValue = p.value;
        while (p != null) {
            p.pre.next = p.next;
            p.next.pre = p.pre;
            p.pre = null;
            p.next =null;
            p = p.up;
        }
        return oldValue;
    }

    private SkipListNode findNode(String key) {
        SkipListNode node;
        node = header;
        while (true) {
            while (node.next.key != SkipListNode.HEADER_KEY
                    && node.next.key.compareTo(key) <= 0) {
                node = node.next;
            }

            if (node.down != null) {
                node = node.down;
            } else {
                break;
            }
        }
        return node;
    }

    private void addEmptyLevel() {
        SkipListNode p1 = new SkipListNode(SkipListNode.HEADER_KEY, null);
        SkipListNode p2 = new SkipListNode(SkipListNode.TAIL_KEY, null);

        p1.next = p2;
        p1.down = header;

        p2.pre = p1;
        p2.down = tail;

        header.up = p1;
        tail.up = p2;

        header = p1;
        tail = p2;

        level++;

    }

    public int getSize() {
        return size;
    }

    public int getLevel() {
        return level;
    }
}
