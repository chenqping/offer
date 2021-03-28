package bat.algorithms.data_structure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *   二叉树基本操作
 */

public class BiTreeOps {

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7};
        TreeNode root = CreateBiTree(a);
        List<Integer> preList =  RecursivePreOrderTraverse(root);
        List<Integer> inList =  RecursiveInOrderTraverse(root);
        List<Integer> postList = RecursivePostOrderTraverse(root);
        System.out.println("PreOrderTraverse: " + preList);
        System.out.println("InOrderTraverse: " + inList);
        System.out.println("PostOrderTraverse: " + postList);
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

    public static List<Integer> RecursivePreOrderTraverse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            list.add(root.val);
            list.addAll(RecursivePreOrderTraverse(root.left));
            list.addAll(RecursivePreOrderTraverse(root.right));
        }
        return list;
    }

    public static List<Integer> NonRecursivePreOrderTraverse(TreeNode root) {
        return null;
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
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node != null){
                stack.push(node.right);
            }
        }
        return null;
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

    public static List<Integer> NonRecursivePostOrderTraverse(TreeNode root) {
        return null;
    }

    public static List<Integer> LevelOrderTraverse(TreeNode t) {
        return null;
    }
}
