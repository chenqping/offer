package bat.algorithms.data_structure.linked_list;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 链表基本操作
 */

public class LinkedListOps {

    public static void main(String[] args) {
        ListNode l1 = createLinkedList();
        List<Integer> list = traverseList(l1);
        System.out.println(list);
        list = reverseTraverseListRecursively(l1);
        System.out.println(list);
        list = reverseTraverseListNonrecursively(l1);
        System.out.println(list);
    }

    public static ListNode createLinkedList() {
        ListNode pHead = null, pNode = null;
        boolean isHead = true;
        Scanner scanner = new Scanner(System.in);
        //输入完毕后回车并Ctrl+D结束输入，一定要回车，不然前一个回车之后的输入都将丢失!
        while (scanner.hasNextInt()) {
            int value = scanner.nextInt();
            ListNode node = new ListNode(value);
            if (isHead) {
                pNode = pHead = node;
                isHead = false;
            }else{
                pNode.next = node;
                pNode = pNode.next;
            }
        }
        scanner.close();
        return pHead;
    }

    public static void print(ListNode pHead) {
        ListNode node = pHead;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static List<Integer> traverseList(ListNode pHead) {
        List<Integer> list = new ArrayList<>();
        ListNode pNode = pHead;
        while (pNode != null){
            list.add(pNode.val);
            pNode = pNode.next;
        }
        return list;
    }

    public static List<Integer> reverseTraverseListRecursively(ListNode pHead) {
        List<Integer> list = new ArrayList<>();
        if(pHead != null){
            list.addAll(reverseTraverseListNonrecursively(pHead.next));
            list.add(pHead.val);
        }
        return list;
    }

    public static List<Integer> reverseTraverseListNonrecursively(ListNode pHead) {
        List<Integer> list = new ArrayList<>();
        ListNode pTail = null;
        while (pTail != pHead){
            ListNode pNode = pHead;
            while (pNode.next != pTail){
                pNode = pNode.next;
            }
            pTail = pNode;
            list.add(pTail.val);
        }
        return list;
    }
}
