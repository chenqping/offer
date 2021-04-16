package bat.algorithms.data_structure.queue;

import java.util.LinkedList;
import java.util.Queue;

/*
*   使用两个队列实现栈
* */

public class QueueForStack {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public QueueForStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    //queue1除了push操作的时候均为空
    public void Push(int x) {
        queue1.offer(x);
        while (!queue2.isEmpty()) {
            queue1.offer(queue2.poll());
        }
        Queue<Integer> temp = queue2;
        queue2 = queue1;
        queue1 = temp;
    }

    public int Pop() {
        return queue2.poll();
    }

    public int GetTop() {
        return queue2.peek();
    }

    public boolean QueueEmpty() {
        return queue2.isEmpty();
    }

    public static void main(String[] args) {
        QueueForStack qfs = new QueueForStack();
        qfs.Push(1);
        qfs.Push(2);
        qfs.Push(3);
        qfs.Push(4);
        qfs.Push(5);
        while (!qfs.QueueEmpty()) {
            System.out.println(qfs.Pop());
        }
    }

}
