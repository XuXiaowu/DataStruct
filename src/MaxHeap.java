import java.util.PriorityQueue;
import java.util.Random;

/**
 * 最大堆
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        //heapify
        for (int i = parent(data.getSize() - 1); i >= 0; i--) {
            siftDown(i);
        }
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
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 上浮操作
     * 把index位置的元素和它的父节点比，如果比父节点的元素大就上浮，一直上浮到树的根节点为止
     */
    private void siftUp(int index) {
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    public E findMax() {
        if (data.getSize() == 0) throw new IllegalArgumentException("Can not findMax when heap is empty");
        return data.get(0);
    }

    /**
     * 取出堆中最大元素
     */
    public E extraMax() {
        E ret = findMax(); //找到最大元素
        data.swap(0, data.getSize() - 1); //把最大元素和最小元素位置互换
        data.removeLast(); //删除最小元素
        siftDown(0); //现在树根是最小元素，把最小元素下沉
        return ret;
    }

    /**
     * 下沉操作
     * 把index位置的元素和的左右孩子节点里最大的元素相比，如果比最大孩子大，就和这个孩子交互位置，一直下沉到叶子结点为止
     */
    private void siftDown(int index) {
        while (leftChild(index) < getSize()) {
            int leftChild = leftChild(index); //左孩子的索引
            int rightChild = rightChild(index); //右孩子的索引
            int maxChild = leftChild; //最大孩子默认是左孩子
            if (rightChild < data.getSize() && data.get(rightChild).compareTo(data.get(leftChild)) > 0) {
                //如果右孩子大于左孩子，最大孩子就是右孩子
                maxChild = rightChild;
            }

            if (data.get(index).compareTo(data.get(maxChild)) > 0) {
                //如果当前的元素大于最大的孩子,则下沉完毕
                break;
            }

            //当前元素比最大孩子小，则互换位置
            data.swap(index, maxChild);
            index = maxChild;
        }
    }

    /**
     * 把元素e放入堆中并返回堆中最大元素
     */
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e); //把元素直接放在堆顶(树根)
        siftUp(0);  //元素e可能比其他元素小，所以要执行一次下沉操作以维护树的结构
        return ret;
    }

    public static void main(String[] args) {
        int n = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = maxHeap.extraMax();
        }

        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] < array[i]) throw new IllegalArgumentException("error");
        }

        PriorityQueue
        System.out.println("test MaxHeap completed");
    }

}
