package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestStack {


    // create a maxStack, to keep track of the maximas of the stack
    Deque<Integer> maxStack = new ArrayDeque<>();

    // create a stack to record all the number pushed inside
    Deque<Integer> stack = new ArrayDeque<>();


    // when push an item, if the number is large than the topmost item in the maxStack,
        // we push the item to the maxStack
    public void push(int item){
        // push the item to stack
        stack.push(item);


        // if the maxStack is empty
        // if the maxStack is not empty
        // we compare the current value with the current maxima


        if (maxStack.isEmpty() || maxStack.peek() <= item){
            maxStack.push(item);
        }

    }

    public int pop(){
        // if the popped value is the current maxima, also pop out the value from the maxStack
        int item = stack.pop();
        if (item == maxStack.peek()){
            maxStack.pop();
        }
        return item;
    }

    public int getMax(){
        return maxStack.peek();
    }

    /* Complexity:
        Time complexity: O(1) for push(), pop() and getMax()
        Space complexity: O(m) is the number of operations performed on the stack.
                            More specifically, # of elements in stack 1 + # of elements in stack 2
    */


    public static void main(String[] args) {
        LargestStack maxstack = new LargestStack();
        maxstack.push(5);
        System.out.println(maxstack.getMax()); // 5

        maxstack.push(4);
        maxstack.push(7);
        maxstack.push(7);
        maxstack.push(8);

        System.out.println(maxstack.getMax()); //8

        maxstack.pop();

        System.out.println(maxstack.getMax()); //7

        maxstack.pop();

        System.out.println(maxstack.getMax()); //7

        maxstack.pop();

        System.out.println(maxstack.getMax()); //5

        maxstack.pop();

        System.out.println(maxstack.getMax()); //5

    }
}
