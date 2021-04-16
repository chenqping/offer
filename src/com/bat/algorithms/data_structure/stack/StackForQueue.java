package bat.algorithms.data_structure.stack;

import java.util.Stack;

/*
*   使用两个栈实现队列
* */

public class StackForQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public StackForQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void EnQueue(int x) {
        stack1.push(x);
    }

    public int DeQueue() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public int GetHead() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    public boolean QueueEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public static void main(String[] args) {
        StackForQueue sfq = new StackForQueue();
        sfq.EnQueue(1);
        sfq.EnQueue(2);
        sfq.EnQueue(3);
        sfq.EnQueue(4);
        sfq.EnQueue(5);
        while (!sfq.QueueEmpty()) {
            System.out.println(sfq.DeQueue());
        }
    }
}
