/*
 * Created by cravuri on 5/29/18
 */

import java.util.Stack;

public class MinStack {

    Stack<Integer> original = new Stack<>();
    Stack<Integer> min = new Stack<>();

    void push(int x) {
        if (min.isEmpty() || x < min.peek()) {
            min.push(x);
        }
        original.push(x);
    }

    void pop() {
        int a = original.pop();
        if (min.peek() == a) {
            min.pop();
        }
    }

    int peek() {
        return original.peek();
    }

}
