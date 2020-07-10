package Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class GetMin {
    public class Solution1{
        private Deque<Integer> stackData;
        private Deque<Integer> stackMin;

        public Solution1(){
            this.stackData = new ArrayDeque<Integer>();
            this.stackMin = new ArrayDeque<Integer>();
        }

        public void push(int newNum){

            this.stackData.push(newNum);

            if (this.stackMin.isEmpty()){
                this.stackMin.push(newNum);
            } else if (newNum <= this.getMin() ){
                this.stackMin.push(newNum);
            }

        }

        public int pop(){
            if (this.stackData.isEmpty()){
                throw new RuntimeException("Your stack is empty.");
            }
            int value = this.stackData.pop();
            if (value == this.getMin()){
                this.stackMin.pop();
            }
            return value;
        }

        public int getMin(){
            if (this.stackMin.isEmpty()){
                throw new RuntimeException("Your stack is empty");
            }
            return this.stackMin.peek();
        }
    }


    /*      Complexity:
                Time complexity: every operation is O(1)
                Space complexity: O(n) in total because in the worst case, we keep push in
                              new minima numbers to the two stacks.
                              n + n => O(2n)

     */

    public class Solution2{
        Deque<Integer> stackData;
        Deque<Integer> stackMin;

        public Solution2(){
            stackData = new ArrayDeque<>();
            stackMin = new ArrayDeque<>();
        }

        public void push(int newNum){
            if (this.stackMin.isEmpty() || newNum < this.getMin() ){
                this.stackMin.push(newNum);
            }else{
                int value = this.getMin();
                this.stackMin.push(value);
            }
            this.stackMin.push(newNum);
        }

        public int getMin(){
            return this.stackMin.peek();
        }
    }

    /*  Complexity:
            Time complexity: every operation is O(1)
            Space complexity: O(n) in total because no matter what the new input is, we keep push in
                              new minima numbers to the stackMin data structure.
                              n + n => O(2n)
     */


    public static void main(String[] args) {

    }
}
