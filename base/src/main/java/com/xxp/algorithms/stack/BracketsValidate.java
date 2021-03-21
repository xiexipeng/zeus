package com.xxp.algorithms.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * <p> 括号合理性校验 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/7/23 下午7:12
 * @Version V1.0
 */
public class BracketsValidate {

    public void testIsValid() {
        String s = "{[]}";
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
                continue;
            }
            Character a = stack.peek();
            if ((a.equals('(') && c.equals(')')) || (a.equals('[') && c.equals(']')) || (a.equals('{') && c.equals('}'))) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        System.out.println(stack.isEmpty());
    }

    public void testIsValid2() {
        String s = "{[]}";
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            Character a = s.charAt(i);
            if (!map.containsKey(a)){
                stack.push(a);
            }else if (stack.isEmpty()||!map.get(a).equals(stack.pop())){
                System.out.println(false);
            }
        }
        System.out.println(stack.isEmpty());
    }
}
