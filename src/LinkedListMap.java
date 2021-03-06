public class LinkedListMap<K, V> implements Map<K, V> {

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else {
            node.value = value;
        }
    }

    @Override
    public V remove(K ket) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(ket)) {
                break;
            }

            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }

        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (node == null) throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = value;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }

            cur = cur.next;
        }

        return null;
    }

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node() {
            this(null, null, null);
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
