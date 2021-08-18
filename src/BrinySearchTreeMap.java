public class BrinySearchTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Node root;
    private int size;

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    @Override
    public V remove(K ket) {
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = value;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回以node为根的二分搜索树中key所在的节点
     */
    private Node getNode(Node node, K key) {
        if (node == null) return null;

        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        }

        return null;
    }

    /**
     * 向以node为根的二分搜索树体中插入元素（key,value），递归算法
     *
     * @return 插入新节点后二分搜索树的根
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        } else {
            if (key.compareTo(node.key) < 0) {
                node.left = add(node.left, key, value);
            } else if (key.compareTo(node.key) > 0) {
                node.right = add(node.right, key, value);
            } else {
                //key == node.key
                node.value = value;
            }
        }

        return node;
    }

    /**
     * 删除以node为根二分搜索树中建为key的节点，递归算法
     *
     * @return 节点删除后新的二分搜索树的根
     */
    private Node remove(Node node, K key) {
        if (node == null) return null;
        if (key.compareTo(node.key) < 0) {
            //要删除的元素比当前节点的元素小，继续往左子树找
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            //要删除的元素比当前节点的元素大，继续往右子树找
            node.right = remove(node.right, key);
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

    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }
}
