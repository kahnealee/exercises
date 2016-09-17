package lc;

import java.util.Stack;

/**
 * Created by kpan on 9/16/16.
 */
public class L155MinStack {
    Stack<Integer> s = new Stack<Integer>();
    Stack<Integer> mins = new Stack<Integer>();
    public void push(int x) {
        s.push(x);
        if (mins.isEmpty() || x <= mins.peek()) {
            mins.push(x);
        }
    }

    public void pop() {
        if (!s.isEmpty()) {
            int x = s.pop();
            if (x <= mins.peek()) {
                mins.pop();
            }
        }
    }

    public int top() {
        if (s.isEmpty()) {
            return 0;
        }
        return s.peek();
    }

    public int getMin() {
        if (mins.isEmpty()) {
            return 0;
        }
        return mins.peek();
    }
}
