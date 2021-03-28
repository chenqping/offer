package bat.algorithms.data_structure.linked_list;

/**
 * 链表基本操作
* */

public class LinkedListOps {

    public static void main(String[] args) {
        ListNode pHead1 = constructLinkList(5);
        printLinkInNormalOrder(pHead1.next);
        printLinkInReverseOrder(pHead1.next);

        ListNode pHead2 = constructLinkList(0);
        printLinkInNormalOrder(pHead2.next);
        printLinkInReverseOrder(pHead2.next);
    }

    public static ListNode constructLinkList(int num) {
        ListNode pHead = new ListNode(num);
        ListNode pNode = pHead;
        for (int i = 0; i < num; i++) {
            ListNode node = new ListNode(i);
            pNode.next = node;
            pNode = node;
        }
        return pHead;
    }

    public static void printLinkInNormalOrder(ListNode pHead) {
        ListNode pNode = pHead;
        while (pNode != null) {
            System.out.println(pNode.val);
            pNode = pNode.next;
        }
    }

    /*
     *    实现链表的逆序输出
     *    边界条件：
     *    1. 功能测试：
     *    2. 特殊输入测试，如空链表
     * */
    public static void printLinkInReverseOrder(ListNode pHead) {
        if (pHead != null) {
            printLinkInReverseOrder(pHead.next);
            System.out.println(pHead.val);
        }
    }
}
