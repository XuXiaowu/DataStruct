import java.util.Random;

public class Main {

    public static void main(String[] args) {
//        stackTest();
//        queueTest();

//        int optTime = 100000;
//        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
//        double time1 = queueTest(arrayQueue, optTime);
//        LoopQueue<Integer> loopQueue = new LoopQueue<>();
//        double time2 = queueTest(loopQueue, optTime);
//        System.out.println("ArrayQueue time: " + time1);
//        System.out.println("LoopQueue time: " + time2);

        int optTime = 10000000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = stackTest(arrayStack, optTime);
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = stackTest(linkedListStack, optTime);
        System.out.println("ArrayStack time: " + time1);
        System.out.println("LinkedListStack time: " + time2);
    }

    private void arrayTest() {
        Array<Integer> array = new Array<>(20);
        for (int i = 0; i < 11; i++) {
            array.addLast(i);
        }
        System.out.println(array);

        array.add(1, 100);
        System.out.println(array);

        array.addFirst(-1);
        System.out.println(array);

        array.remove(2);
        System.out.println(array);

        array.removeElement(4);
        System.out.println(array);
    }

    private static void stackTest() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
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

    private static double stackTest(Stack<Integer> stack, int optCount) {
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

}