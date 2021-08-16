import java.util.Random;

public class BinarySearchTree<E extends Comparable<E>> {
    private Node root;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
//        if (root == null) {
//            root = new Node(e);
//            size++;
//        } else {
//            add(root, e);
//        }

        root = add(root, e);
    }

//    public void add(Node parentNode, E e) {
//        if (e.equals(parentNode.e)) {
//            return;
//        } else if (e.compareTo(parentNode.e) > 0 && parentNode.right == null) {
//            parentNode.right = new Node(e);
//            size++;
//            return;
//        } else if (e.compareTo(parentNode.e) < 0 && parentNode.right == null) {
//            parentNode.left = new Node(e);
//            size++;
//            return;
//        }
//
//        if (e.compareTo(parentNode.e) > 0) {
//            add(parentNode.right, e);
//        } else {
//            add(parentNode.left, e);
//        }
//    }

    /**
     * 插入新节点后返回二分搜索树的根
     */
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    private boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 看以node为根的二分搜索书是否包含元素e
     */
    private boolean contains(Node node, E e) {
        if (node == null) return false;
        if (e.equals(node.e)) return true;

        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }


    /**
     * 前序遍历以node为根的二分搜索书
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 前序遍历以node为根的二分搜索书
     * 递归实现
     */
    private void preOrder(Node node) {
        if (node == null) return;

        System.out.print(node.e + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 前序遍历以node为根的二分搜索书
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 中序遍历以node为根的二叉树
     * 递归实现
     */
    private void inOrder(Node node) {
        if (node == null) return;

        inOrder(node.left);
        System.out.print(node.e + " ");
        inOrder(node.right);
    }

    /**
     * 中序遍历以node为根的二叉树
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 后序遍历以node为根的二叉树
     * 递归实现
     */
    public void postOrder(Node node) {
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e + " ");
    }

    /**
     * 前序遍历二叉树
     * 非递归实现
     */
    public void preOrderNR() {
        Stack<Node> stack = new ArrayStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.e + " ");
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
    }

    /**
     * 层序遍历
     */
    public void levelOrder() {
        Queue<Node> queue = new ArrayQueue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node cur = queue.dequeue();
            System.out.print(cur.e + " ");
            if (cur.left != null) queue.enqueue(cur.left);
            if (cur.right != null) queue.enqueue(cur.right);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        generateBSTString(root, 0, stringBuilder);
        return stringBuilder.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) return;
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("--".repeat(Math.max(0, depth)));
        return stringBuilder.toString();
    }

    /**
     * 获取二分搜索树的最小值
     */
    public E minimum() {
        if (isEmpty()) throw new IllegalArgumentException("BinarySearchTree is empty");

        return minimum(root).e;
    }

    /**
     * 获取以node为根的二分搜索树的最小值所在节点
     *
     * @return 最小元素坐在节点
     */
    private Node minimum(Node node) {
        if (node.left == null) return node;

        return minimum(node.left);
    }

    /**
     * 获取二分搜索树的最大元素
     */
    public E maximum() {
        if (isEmpty()) throw new IllegalArgumentException("BinarySearchTree is empty");

        return maximum(root).e;
    }

    /**
     * 获取以node为根的二分搜索树的最大元素所在节点
     *
     * @return 最大元素坐在节点
     */
    private Node maximum(Node node) {
        if (node.right == null) return node;

        return maximum(node.right);
    }

    /**
     * 从二分搜索树中删除最小值所在的节点
     *
     * @return 最小值
     */
    public E removeMin() {
        E ret = minimum();
        removeMin(root);
        return ret;
    }

    /**
     * 删除以node为根的二分搜索树中最小的节点
     *
     * @return 删除节点后新的二分搜索树根
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        return removeMin(node.left);
    }

    /**
     * 从二分搜索树中删除最大值所在的节点
     *
     * @return 最大值
     */
    public E removeMax() {
        E ret = maximum();
        removeMax(root);
        return ret;
    }

    /**
     * 删除以node为根的二分搜索树中最大的节点
     *
     * @return 删除节点后新的二分搜索树根
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        return removeMax(node.right);
    }

    /**
     * 从二分搜索树中删除元素为e的节点
     */
    public void remove(E e) {
        remove(root, e);
    }

    /**
     * 删除以node为根二分搜索树中值为e的节点，递归算法
     *
     * @return 节点删除后新的二分搜索树的根
     */
    private Node remove(Node node, E e) {
        if (node == null) return null;
        if (e.compareTo(node.e) < 0) {
            //要删除的元素比当前节点的元素小，继续往左子树找
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            //要删除的元素比当前节点的元素大，继续往右子树找
            node.right = remove(node.right, e);
            return node;
        } else {
            //要删除的元素等于当前节点元素，则找到了要删除的元素，将其删除
            if (node.left == null) {
                //要删除的元素左子树为空，返回节点的右子树
                Node rightNode = node.right;
                node.right = null; //断开节点的右子树指针
                size--;
                return rightNode;
            } else if (node.right == null) {
                //要删除的元素右子树为空，返回节点的左子树
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else {
                //要删除元素的左右节点都不为空
                Node successor = minimum(node.right); //先找到此节点的后继
                successor.left = node.left; //左节点等于当前节点的左子树
                successor.right = removeMin(node.right); //右节点等于删除最小元素后的新树
                node.left = node.right = null; //断开当前节点的左右子树指针
                size--;
                return successor;
            }
        }
    }

    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args) {
        //二分搜索树测试用例
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        int[] array = new int[]{8, 6, 9, 4, 7, 10};
        for (int i : array) {
            binarySearchTree.add(i);
        }

//        System.out.println(binarySearchTree);
        binarySearchTree.preOrder();
        System.out.println();
        binarySearchTree.preOrderNR();
        System.out.println();
        binarySearchTree.inOrder();
        System.out.println();
        binarySearchTree.postOrder();
        System.out.println();
        binarySearchTree.levelOrder();
        System.out.println();
        binarySearchTree.remove(6); //删除一个元素
        binarySearchTree.inOrder();
        System.out.println();
        System.out.println("minimum: " + binarySearchTree.minimum());
        System.out.println("maximum: " + binarySearchTree.maximum());
    }

}
