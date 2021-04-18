package bat.algorithms.data_structure.tree;

import java.util.*;

/**
 * 二叉树基本操作
 */

public class BiTreeOps {

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7};
        TreeNode root = createBiTree(a);
        /*
        *   生成一棵如下的完全二叉树：
        *            0
        *           / \
        *          1   2
        *         / \ / \
        *        3  4 5  6
        *       /
        *      7
        * */
//        Scanner scanner = new Scanner(System.in);
//        TreeNode root = CreateBiTree(scanner);
        List<Integer> list = recursivePreOrderTraverse(root);
        System.out.println("recursive pre-order traverse: " + list);
        list = nonRecursivePreOrderTraverse(root);
        System.out.println("non-recursive pre-order traverse: " + list);
        list = recursiveInOrderTraverse(root);
        System.out.println("recursive in-order traverse: " + list);
        list = nonRecursiveInOrderTraverse(root);
        System.out.println("non-recursive in-order traverse: " + list);
        list = recursivePostOrderTraverse(root);
        System.out.println("recursive post-order traverse: " + list);
        list = nonRecursivePostOrderTraverse(root);
        System.out.println("non-recursive post-order traverse: " + list);
        list = seqOrderTraverse(root);
        System.out.println("seq-order traverse: " + list);
        list = depthFirstSearch(root);
        System.out.println("depth first search: " + list);
        list = breadthFirstSearch(root);
        System.out.println("breadth first search: " + list);
        int height = height(root);
        System.out.println("height: " + height);
        int distance = findDistance(root, 7, 5);
        System.out.println("find distance between 3 and 5 is: " + distance);
    }

    /*
     *  构造的是完全二叉树
     * */
    public static TreeNode createBiTree(int[] array) {
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

    /**
     *  依前序递归创建二叉树，#代表分支结束，如要创建如下二叉树
     *              1
     *             / \
     *            2   3
     *           / \
     *          4   5
     *  输入的序列为：1 2 4 # # 5 # # 3 # #
     * */
    public static TreeNode createBiTree(Scanner scanner) {
        TreeNode root;
        String data = scanner.next();
        if ("#".equals(data)) {
            root = null;
        } else {
            try {
                int value = Integer.parseInt(data);
                root = new TreeNode(value);
                root.left = createBiTree(scanner);
                root.right = createBiTree(scanner);
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
    public static List<Integer> recursivePreOrderTraverse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            list.add(root.val);
            list.addAll(recursivePreOrderTraverse(root.left));
            list.addAll(recursivePreOrderTraverse(root.right));
        }
        return list;
    }

    /**
     *  非递归实现：从根节点沿着左路一直往下到最左边的节点，沿途记录节点的值，这里相当于访问了树根及所有内层左子树
     *  的根节点，虽然根节点已经访问过，但仍需要保留，因为这里只访问了根和左，还要从最左边的节点往上遍历其兄弟树，
     *  所以借助于栈先进后出的特性来记录遍历左分支过程中经过的根节点。
     * */
    public static List<Integer> nonRecursivePreOrderTraverse(TreeNode root) {
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

    public static List<Integer> recursiveInOrderTraverse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            list.addAll(recursiveInOrderTraverse(root.left));
            list.add(root.val);
            list.addAll(recursiveInOrderTraverse(root.right));
        }
        return list;
    }

    public static List<Integer> nonRecursiveInOrderTraverse(TreeNode root) {
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

    public static List<Integer> recursivePostOrderTraverse(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            list.addAll(recursivePostOrderTraverse(root.left));
            list.addAll(recursivePostOrderTraverse(root.right));
            list.add(root.val);
        }
        return list;
    }

    /**
     * 后序遍历先访问左右子树，最后访问根节点，如下树
     *        A
     *       / \
     *      B  C
     *       \
     *        D
     * 先将左路A,B依次入栈，从最左边的节点B开始，B的左子树为空，右子树非空，所以B暂时不能访问，需要先访
     * 问右子树D，同样先将D入栈，D由于没有左右孩子(相当于此时左右子树已经访问完毕)，可以访问D节点了，D出
     * 栈，记录D的值，此时还需要记录pre，再次回到B时，B虽然有右孩子，但是右孩子已经访问过，可以访问B的值。
     * 因此后序遍历非递归的实现需要设置一个pre记录前次访问的节点，以便于判断右孩子是否已经访问完毕。
     */
    public static List<Integer> nonRecursivePostOrderTraverse(TreeNode root) {
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
    public static List<Integer> seqOrderTraverse(TreeNode root) {
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
    *   深度优先遍历，实际上前序，中序和后序都属于深度优先遍历，以下给出的与前序遍历结果相同
    * */
    public static List<Integer> depthFirstSearch(TreeNode root){
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }

    /*
    *   广度优先遍历，基本与层序相同
    * */
    public static List<Integer> breadthFirstSearch(TreeNode root){
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node.val);
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        return list;
    }

    /*
     *  根据先序序列和中序序列唯一建造一棵二叉树，返回二叉树的根
     * */
    public static TreeNode preAndInCreateTree(int[] pre, int[] in, int i, int j, int m, int n) {
        //数组pre存储先序序列，i,j分别表示pre的上标和下标
        //in：中序序列，m，n分别表示in的上标和下标
        //函数返回先序序列和中序序列构成的树的根
        int k;
        TreeNode p=null;
        if(n<0)
            return null;
        p = new TreeNode(pre[i]);
        k = m;
        //在中序中找根
        while((k<=n)&&in[k]!=pre[i])
            k++;
        p.left = preAndInCreateTree(pre,in,i+1,i+k-m,m,k-1);
        p.right = preAndInCreateTree(pre,in,i+k-m+1,j,k+1,n);
        return p;
    }



    /*
     *  寻找树中两个节点之间的距离
     * */
    public static int findDistance(TreeNode root, int p, int q){
        root = findNode(root, p, q);
        Distance distance = new Distance();
        findDist(root, p, distance);
        findDist(root, q, distance);
        return distance.value();
    }

    /*
     *  1. 寻找两个节点最近的公共祖先
     * */
    public static TreeNode findNode(TreeNode root, int p, int q) {
        if (root == null) {
            return null;
        }
        if (root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = findNode(root.left, p, q);
        TreeNode right = findNode(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if(left != null ){
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }

    /*
    *   2. 求根节点到给定节点值的距离
    * */
    // 定义距离类型，通过对象属性改变参数
    static class Distance{
        private int distance;
        public void inc(){
            distance++;
        }
        public void dec(){
            distance--;
        }
        public int value(){
            return distance;
        }
    }

    public static boolean findDist(TreeNode root, int v, Distance distance){
        if(root == null){
            return  false;
        }
        if(root.val == v){
            return true;
        }
        distance.inc();
        if(findDist(root.left, v, distance) || findDist(root.right, v, distance)){
            return true;
        }
        distance.dec();
        return false;
    }

    /*
     *  求二叉树的高度
     * */
    public static int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int lh = height(root.left);
            int rh = height(root.right);
            return 1 + (lh > rh ? lh : rh);
        }
    }

    /*
     *  求给定二叉树的镜像
     * */
    public static TreeNode mirror(TreeNode root) {
        return null;
    }

    /*
     *  对称的二叉树
     * */

}
