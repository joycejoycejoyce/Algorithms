package Stack;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class ReverseArray {

    public int[] reverseUsingStackAndQueue(int [] intArr){
        // create a stack
        Deque<Integer> stack = new ArrayDeque<>();
        // create a queue
        Deque<Integer> queue = new ArrayDeque<>();
        // create an output arr
        int[] output = new int[intArr.length];
        // put all elements to the queue
        for (int element : intArr){
            queue.addLast(element);
        }

        // stuff all elements from queue to the stack
        while(!queue.isEmpty()){
            stack.push(queue.pop());
        }
        // pop elements out from stack
        int index = 0;
        while (!stack.isEmpty()){
            output[index] = stack.pop();
            index++;
        }
        System.out.println(Arrays.toString(output));
        return output;
    }

    public static void main(String[] args) {
        ReverseArray reverseArray = new ReverseArray();


        int[] inputArr = {1,2,3,4};
        reverseArray.reverseUsingStackAndQueue(inputArr);
    }
}
