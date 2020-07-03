package Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class ImplementAQueueWithTwoStacks {
    public static class QueueTwoStacks {
        private Deque<Integer> inStack = new ArrayDeque<>();
        private Deque<Integer> outStack = new ArrayDeque<>();

        public void enqueue(int item){
            // put items to the inStack data structure
            inStack.push(item);
        }

        public int dequeue(){
            // if there is no element inside outStack
                // we need to move elements from inStack
            if (outStack.isEmpty()){
                while (!inStack.isEmpty()){
                    outStack.push(inStack.pop());
                }
            }

            // edge case
                // there is no more element
            if (outStack.isEmpty()){
                throw new NoSuchElementException("No enough element");
            }


            // pop the topmost element
            return outStack.pop();
        }
    }


    public static void main(String[] args) {
        // test case 1
        QueueTwoStacks test1 = new QueueTwoStacks();
        test1.enqueue(1);
        test1.enqueue(2);
        test1.enqueue(3);

        System.out.println(test1.dequeue()); // 1
        System.out.println(test1.dequeue()); // 2

        test1.enqueue(4);

        System.out.println(test1.dequeue()); // 3
        System.out.println(test1.dequeue()); // 4


        // test case 2
        QueueTwoStacks test2 = new QueueTwoStacks();
        test2.dequeue();


        // test case 3
        final QueueTwoStacks test3 = new QueueTwoStacks();
        test3.enqueue(1);
        test3.enqueue(2);
        System.out.println( test3.dequeue());
        System.out.println( test3.dequeue());
        System.out.println( test3.dequeue());

    }
}
