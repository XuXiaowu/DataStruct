/**
 * 链表实现的队列
 */
public class LinkedListQueue<E> implements Queue<E> {

    private Node head;
    private Node tail;
    private int size;

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }

        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new IllegalArgumentException("Cannot dequeue from empty queue");
        Node retNode = head;
        head = head.next;
        if (head.next == null) {
            tail = null;
        }

        return retNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) throw new IllegalArgumentException("Queue is empty");
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return tail == null;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front ");

        Node cur = head;
        while (cur!=null){
            res.append(cur+"->");
            cur=cur.next;
        }

        res.append("NULL tail");
        return res.toString();
    }

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> arrayQueue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            arrayQueue.enqueue(i);
            System.out.println(arrayQueue);

            if (i % 3 == 2) {
                arrayQueue.dequeue();
                System.out.println(arrayQueue);
            }
        }
    }

}
