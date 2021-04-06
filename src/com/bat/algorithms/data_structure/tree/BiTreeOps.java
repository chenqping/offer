package bat.algorithms.data_structure.tree;

import bat.algorithms.data_structure.linked_list.ListNode;
import com.sun.source.tree.Tree;

import java.util.*;

/**
 * 二叉树基本操作
 */

public class BiTreeOps {

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7};
        TreeNode root = CreateBiTree(a);
        List<Integer> preList = RecursivePreOrderTraverse(root);
        List<Integer> preList2 = NonRecursivePreOrderTraverse(root);
        List<Integer> inList = RecursiveInOrderTraverse(root);
        List<Integer> inList2 = NonRecursiveInOrderTraverse(root);
        List<Integer> postList = RecursivePostOrderTraverse(root);
        List<Integer> postList2 = NonRecursivePostOrderTraverse(root);
        List<Integer> seqList = SeqOrderTraverse(root);
        int height = Height(root);
        System.out.println("recursive pre-order Traverse: " + preList);
        System.out.println("non-recursive pre-order Traverse: " + preList2);
        System.out.println("recursive in-order Traverse: " + inList);
        System.out.println("non-recursive in-order Traverse: " + inList2);
        System.out.println("recursive post-order traverse: " + postList);
        System.out.println("non-recursive post-order traverse: " + postList2);
        System.out.println("seq-order traverse: "+ seqList);
        System.out.println("height: " + height);
    }

    public static TreeNode CreateBiTree(int[] array) {
        List<TreeNode> nodeList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            nodeList.add(new TreeNode(array[i]));
        }
        for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {
            nodeList.get(parentIndex).left = nodeList.get(parentIndex * 2 + 1);
            nodeList.get(parentIndex).right = nodeList.get(parentIndex * 2 + 2);
        }
        int lastParentIndex = array.length / 2 - 1;
        nodeList.get(lastParentIndex).left = nodeList.get(lastParentIndex * 2 + 1);
        if (array.length % 2 == 1) {
            nodeList.get(lastParentIndex).right = nodeList.get(lastParentIndex * 2 + 2);
        }
        return nodeList.get(0);
    }

    /*
     *  前序遍历是先访问根节点，再前序遍历左子树，最后前序遍历右子树。
     * */
    /*
     *  递归实现
     * */
    public static List<Integer> RecursivePreOrderTraverse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            list.add(root.val);
            list.addAll(RecursivePreOrderTraverse(root.left));
            list.addAll(RecursivePreOrderTraverse(root.right));
        }
        return list;
    }

    /*
     *  非递归实现：从根节点沿着左路一直往下到最左边的节点，沿途记录节点的值，这里相当于访问了树根及所有内层左子树
     *  的根节点，虽然根节点已经访问过，但仍需要保留，因为这里只访问了根和左，还要从最左边的节点往上遍历其兄弟树，
     *  所以借助于栈先进后出的特性来记录遍历左分支过程中经过的根节点。
     * */
    public static List<Integer> NonRecursivePreOrderTraverse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tn = root;
        /*
         *  while循环条件的几种情况说明：
         *  1. tn和stack都为空：遍历完毕
         *  2. tn不为空，stack为空：初始状态，从根节点开始；
         *  3. tn为空，stack不为空： 遇到了左或右子节点为空；
         *  4. tn和stack都不为空：在沿着最左分支往下访问
         * */
        while (tn != null || !stack.isEmpty()) {
            if (tn != null) {
                list.add(tn.val);
                stack.push(tn);
                tn = tn.left;
            } else {
                tn = stack.pop();
                tn = tn.right;
            }
        }
/*        while( tn != null || !stack.isEmpty()){
            while(tn != null){
                list.add(tn.val);
                stack.push(tn);
                tn = tn.left;
            }
            while(!stack.isEmpty() && tn == null){
                tn = stack.pop().right;
            }
        }*/
        /*
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode tn;
            while((tn = stack.peek()) != null){
                list.add(tn.val);
                stack.push(tn.left);
            }
            stack.pop();
            if(!stack.isEmpty()){
                tn = stack.pop();
                stack.push(tn.right);
            }
        }*/
        return list;
    }

    public static List<Integer> RecursiveInOrderTraverse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            list.addAll(RecursiveInOrderTraverse(root.left));
            list.add(root.val);
            list.addAll(RecursiveInOrderTraverse(root.right));
        }
        return list;
    }

    public static List<Integer> NonRecursiveInOrderTraverse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tn = root;
        while (tn != null || !stack.isEmpty()) {
            if (tn != null) {
                stack.push(tn);
                tn = tn.left;
            } else {
                tn = stack.pop();
                list.add(tn.val);
                tn = tn.right;
            }
        }
/*        while( tn != null || !stack.isEmpty()){
            while(tn != null){
                stack.push(tn);
                tn = tn.left;
            }
            while(!stack.isEmpty() && tn == null){
                tn = stack.pop();
                list.add(tn.val);
                tn = tn.right;
            }
        }*/
/*        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode tn;
            while((tn = stack.peek()) != null){
                stack.push(tn.left);
            }
            stack.pop();
            if(!stack.isEmpty()){
                tn = stack.pop();
                list.add(tn.val);
                stack.push(tn.right);
            }
        }*/
        return list;
    }

    public static List<Integer> RecursivePostOrderTraverse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            list.addAll(RecursivePostOrderTraverse(root.left));
            list.addAll(RecursivePostOrderTraverse(root.right));
            list.add(root.val);
        }
        return list;
    }

    /**
     * 后序遍历麻烦的地方是什么时候访问节点，在访问节点右子节点之前不能访问根节点
     * 一种是访问叶子节点，即左右孩子为空；
     * 当前访问节点的右子节点是上一次访问的节点
     */
    public static List<Integer> NonRecursivePostOrderTraverse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null, cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right != null && pre != cur.right) {
                cur = cur.right;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                pre = cur;
                cur = null;
            }
        }
        return list;
    }

    /*
     *   层序遍历
     * */
    public static List<Integer> SeqOrderTraverse(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count > 0){
                TreeNode tn = queue.poll();
                list.add(tn.val);
                if (tn.left != null) {
                    queue.offer(tn.left);
                }
                if (tn.right != null) {
                    queue.offer(tn.right);
                }
                count --;
            }
        }
        return list;
    }

    /*
    *  根据前序序列和中序序列重建二叉树
    * */
    public static TreeNode RecreateBiTree(int[] pre, int[] in, int i, int j, int m, int n){
        return null;
    }

    /*
    *  求二叉树的高度
    * */
    public  static int Height(TreeNode root){
        if(root == null){
            return 0;
        }else{
            int lh = Height(root.left);
            int rh = Height(root.right);
            return 1 + (lh > rh ? lh : rh);
        }
    }

    /*
    *  求给定二叉树的镜像
    * */
    public static TreeNode Mirror(TreeNode root){
        return null;
    }

    /*
    *  对称的二叉树
    * */

}
