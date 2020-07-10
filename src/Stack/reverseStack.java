package Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class reverseStack {
    public static int getAndRemoveLastElement(Deque<Integer> stack){
        int removedNumber = stack.pop();

        if (stack.isEmpty()){
            return removedNumber;
        }

        int lastElement = getAndRemoveLastElement(stack);
        stack.push(removedNumber);
        return lastElement;
    }

    public static void reverse(Deque<Integer> stack){
       // base case
        if (stack.isEmpty()){
            return;
        }

        int i = getAndRemoveLastElement(stack);
       reverse(stack);
       stack.push(i);
    }

    public static void main(String[] args) {
        Deque<Integer> initialStack = new ArrayDeque<>();
        initialStack.push(1);
        initialStack.push(2);
        initialStack.push(3);
        initialStack.push(5);

        System.out.println(initialStack.toString());
        reverse(initialStack);
        System.out.println(initialStack.toString());
    }
}
