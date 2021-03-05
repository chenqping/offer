package bat.algorithms.data_structure.linked_list;

/*
*    实现链表的逆序输出
*    边界条件：
*    1. 功能测试：
*    2. 特殊输入测试，如空链表
* */

public class PrintLinkReverse {
    static class Node {
        int val;
        Node pNext;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node pHead1 = constructLinkList(5);
        printLinkInNormalOrder(pHead1.pNext);
        printLinkInReverseOrder(pHead1.pNext);

        Node pHead2 = constructLinkList(0);
        printLinkInNormalOrder(pHead2.pNext);
        printLinkInReverseOrder(pHead2.pNext);
    }

    public static Node constructLinkList(int num) {
        Node pHead = new Node(num);
        Node pNode = pHead;
        for (int i = 0; i < num; i++) {
            Node node = new Node(i);
            pNode.pNext = node;
            pNode = node;
        }
        return pHead;
    }

    public static void printLinkInNormalOrder(Node pHead) {
        Node pNode = pHead;
        while (pNode != null) {
            System.out.println(pNode.val);
            pNode = pNode.pNext;
        }
    }

    public static void printLinkInReverseOrder(Node pHead) {
        if (pHead != null) {
            printLinkInReverseOrder(pHead.pNext);
            System.out.println(pHead.val);
        }
    }
}
