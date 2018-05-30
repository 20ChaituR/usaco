/*
 * Created by cravuri on 5/29/18
 */

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class MinQueue {

    Queue<Integer> original = new ArrayDeque<>();
    Stack<Integer> minLeft = new Stack<>();
    Stack<Integer> minRight = new Stack<>();

    void add(int x) {
        if (minRight.isEmpty() || x < minRight.peek()) {
            minRight.push(x);
        }
        original.add(x);
    }

    void remove() {
        if (minLeft.isEmpty()) {
            while (!minRight.isEmpty()) {
                minLeft.add(minRight.pop());
            }
        }
        int a = original.remove();
        if (minLeft.peek() == a) {
            minLeft.pop();
        }
    }

    int peek() {
        return original.peek();
    }

}
