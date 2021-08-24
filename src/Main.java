import utils.FileOperation;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

//        testQueuePerformance();
//        testStackPerformance();
//        testBinarySearchTreeSet();
//        testLinkedListSet();
//        testSetPerformance();
//        testLinkedListMap();
//        testBrinySearchTreeMap();
        testHeapifyPerformance();
    }


    private static double queueTest(Queue<Integer> queue, int optCount) {
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < optCount; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0; i < optCount; i++) {
            queue.dequeue();
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    private static double testStack(Stack<Integer> stack, int optCount) {
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < optCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0; i < optCount; i++) {
            stack.pop();
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    private static void testQueuePerformance() {
        int optTime = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = queueTest(arrayQueue, optTime);
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = queueTest(loopQueue, optTime);
        System.out.println("ArrayQueue time: " + time1);
        System.out.println("LoopQueue time: " + time2);
    }

    /**
     * 栈的性能测试
     */
    private static void testStackPerformance() {
        int optTime = 10000000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack, optTime);
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = testStack(linkedListStack, optTime);
        System.out.println("ArrayStack time: " + time1);
        System.out.println("LinkedListStack time: " + time2);
    }

    /**
     * 二分搜索树实现的set的测试
     */
    private static void testBinarySearchTreeSet() {
        ArrayList<String> wordList = new ArrayList<>();
        FileOperation.readFile("F:/JavaProjects/DataStruct/src/utils/pride-and-prejudice.txt", wordList);
        Set<String> set1 = new BinarySearchTreeSet<>();
        for (String word : wordList) {
            set1.add(word);
        }

        System.out.println("Total different words: " + set1.getSize());
    }

    /**
     * 链表实现的set的测试
     */
    private static void testLinkedListSet() {
        ArrayList<String> wordList = new ArrayList<>();
        FileOperation.readFile("F:/JavaProjects/DataStruct/src/utils/pride-and-prejudice.txt", wordList);
        Set<String> set1 = new LinkedListSet<>();
        for (String word : wordList) {
            set1.add(word);
        }

        System.out.println("Total different words: " + set1.getSize());
    }

    private static double testSet(Set<String> set, String filepath) {
        long startTime = System.nanoTime();

        ArrayList<String> wordList = new ArrayList<>();
        FileOperation.readFile(filepath, wordList);
        for (String word : wordList) {
            set.add(word);
        }

        System.out.println("Total different words: " + set.getSize());
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    /**
     * set性能测试
     */
    private static void testSetPerformance() {
        String filepath = "F:/JavaProjects/DataStruct/src/utils/pride-and-prejudice.txt";
        BinarySearchTreeSet<String> binarySearchTreeSet = new BinarySearchTreeSet<>();
        double time1 = testSet(binarySearchTreeSet, filepath);
        System.out.println("BinarySearchTreeSet: " + time1 + " s");
        System.out.println();

        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        double time2 = testSet(linkedListSet, filepath);
        System.out.println("LinkedListSet: " + time2 + " s");
    }

    private static void testLinkedListMap() {
        String filepath = "F:/JavaProjects/DataStruct/src/utils/pride-and-prejudice.txt";
        ArrayList<String> wordList = new ArrayList<>();
        Map<String, Integer> ma = new LinkedListMap<>();
        FileOperation.readFile(filepath, wordList);
        System.out.println("Total words: " + wordList.size());
        for (String word : wordList) {
            if (ma.contains(word)) {
                ma.set(word, ma.get(word) + 1);
            } else {
                ma.add(word, 1);
            }
        }

        System.out.println("Total different words: " + ma.getSize());
        System.out.println("Frequency of pride: " + ma.get("pride"));
    }

    private static void testBrinySearchTreeMap() {
        String filepath = "F:/JavaProjects/DataStruct/src/utils/pride-and-prejudice.txt";
        ArrayList<String> wordList = new ArrayList<>();
        Map<String, Integer> map = new BrinySearchTreeMap<>();
        FileOperation.readFile(filepath, wordList);
        System.out.println("Total words: " + wordList.size());
        for (String word : wordList) {
            if (map.contains(word)) {
                map.set(word, map.get(word) + 1);
            } else {
                map.add(word, 1);
            }
        }

        System.out.println("Total different words: " + map.getSize());
        System.out.println("Frequency of pride: " + map.get("pride"));
    }

    /**
     * Heapify性能测试
     */
    private static double testHeapifyPerformance(Integer[] testData, boolean isHeapify) {
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(testData);
        } else {
            maxHeap = new MaxHeap<>();
            for (Integer testDatum : testData) {
                maxHeap.add(testDatum);
            }
        }

        int[] array = new int[maxHeap.getSize()];
        for (int i = 0; i < maxHeap.getSize(); i++) {
            array[i] = maxHeap.extraMax();
        }

        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] < array[i]) throw new IllegalArgumentException("error");
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    private static void testHeapifyPerformance() {
        int n = 1000000;
        Integer[] data = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            data[i] = random.nextInt(Integer.MAX_VALUE);
        }

        double time1 = testHeapifyPerformance(data, false);
        double time2 = testHeapifyPerformance(data, true);
        System.out.println("without heapify: " + time1);
        System.out.println("with heapify: " + time2);
    }

}
