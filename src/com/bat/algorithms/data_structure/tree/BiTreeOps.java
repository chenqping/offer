package bat.algorithms.data_structure.tree;

import java.util.*;

/**
 * 二叉树基本操作
 */

public class BiTreeOps {

    public static void main(String[] args) {
//        int[] a = {0, 1, 2, 3, 4, 5, 6, 7};
//        TreeNode root = CreateBiTree(a);
        Scanner scanner = new Scanner(System.in);
        TreeNode root = CreateBiTree(scanner);
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
        System.out.println("seq-order traverse: " + seqList);
        System.out.println("height: " + height);
    }

    /*
     *  构造的是完全二叉树
     * */
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
     *  依前序递归创建二叉树，如要创建如下二叉树
     *              1
     *             / \
     *            2   3
     *           / \
     *          4   5
     *  输入的序列为：1 2 4 # # 5 # # 3 # #
     * */
    public static TreeNode CreateBiTree(Scanner scanner) {
        TreeNode root;
        String data = scanner.next();
        if ("#".equals(data)) {  //输入#代表为空节点
            root = null;
        } else {
            try {
                int value = Integer.parseInt(data);
                root = new TreeNode(value);
                root.left = CreateBiTree(scanner);
                root.right = CreateBiTree(scanner);
            } catch (NumberFormatException e) {
                root = null;
            }
        }
        return root;
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
     * 后序遍历先访问左右子树，最后访问根节点，如下树
     * A
     * / \
     * B   C
     * \
     * D
     * 先将左路A,B依次入栈，从最左边的节点B开始，B的左子树为空，右子树非空，所以B暂时不能访问，需要先访
     * 问右子树D，同样先将D入栈，D由于没有左右孩子(相当于此时左右子树已经访问完毕)，可以访问D节点了，D出
     * 栈，记录D的值，此时还需要记录pre，再次回到B时，B虽然有右孩子，但是右孩子已经访问过，可以访问B的值。
     * 因此后序遍历非递归的实现需要设置一个pre记录前次访问的节点，以便于判断右孩子是否已经访问完毕。
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
            while (count > 0) {
                TreeNode tn = queue.poll();
                list.add(tn.val);
                if (tn.left != null) {
                    queue.offer(tn.left);
                }
                if (tn.right != null) {
                    queue.offer(tn.right);
                }
                count--;
            }
        }
        return list;
    }

    /*
     *  根据前序序列和中序序列重建二叉树
     * */
    public static TreeNode RecreateBiTree(int[] pre, int[] in, int i, int j, int m, int n) {
        return null;
    }

    /*
     *  寻找树中的节点，假设节点值不重复
     * */
    public static TreeNode FindNode(TreeNode root, int n) {
        if (root == null) {
            return null;
        }
        if (root.val == n) {
            return root;
        }
        TreeNode left = FindNode(root.left, n);
        TreeNode right = FindNode(root.right, n);
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }

    /*
     *  求二叉树的高度
     * */
    public static int Height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int lh = Height(root.left);
            int rh = Height(root.right);
            return 1 + (lh > rh ? lh : rh);
        }
    }

    /*
     *  求给定二叉树的镜像
     * */
    public static TreeNode Mirror(TreeNode root) {
        return null;
    }

    /*
     *  对称的二叉树
     * */

}
