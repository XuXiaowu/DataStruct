public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 获取完全二叉树的数组表示中，一个索引所表示元素父节点的索引
     */
    private int parent(int index) {
        if (index == 0) throw new IllegalArgumentException("index 0 doesn't have parent");

        return (index - 1) / 2;
    }

    /**
     * 获取完全二叉树的数组表示中，一个索引所表示元素左孩子节点的索引
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 获取完全二叉树的数组表示中，一个索引所表示元素右孩子节点的索引
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素
     */
    private void add(E e) {
        data.addLast(e);
    }

    private void siftUp(int index) {
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

}
