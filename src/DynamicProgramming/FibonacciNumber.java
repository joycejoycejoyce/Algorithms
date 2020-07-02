package DynamicProgramming;

import java.util.HashMap;

public class FibonacciNumber {

    /*
    * time complexity: O(2^n)
    * space complexity: O(N) because of the call stack need to maximum remember is the recursion depth
    *                        depth is proportional to N
    * */
    public int recursiveMethodToFindFibonacciNumber(int position){
        /*
        * when pos is 0 -> return 0
        * when pos is 1 -> return 1
        * */
        if (position <=1){
            return position;
        }
        // if position > 1
        return recursiveMethodToFindFibonacciNumber(position-1) + recursiveMethodToFindFibonacciNumber(position-2);
    }

    public int memorizationFib(int position, HashMap<Integer, Integer> memo){
        if (position <= 1){
            return position;
        }

        if (!memo.containsKey(position)){
            int value = memorizationFib(position-1, memo) + memorizationFib(position-2, memo);
            memo.put(position, value);
        }

        return memo.get(position);
    };

    public int bottomUpFib(int position){
        // if the arr < 2
        if (position < 2){
            return position;
        }

        // bottom up is a for loop
        // create an arr the same size as position
        int[] arr = new int[position+1];
        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i < arr.length; i++){
            arr[i] = arr[i-1] + arr[i-2];
        }

        return arr[position];
    }

    public int bottomUpWithVar(int position){
        // edge case
        if (position <2){
            return position;
        }

        int posFirst = 0;
        int posSec = 1;

        for(int i=2; i<=position; i++){
            int temp = posSec + posFirst;
            posFirst = posSec;
            posSec = temp;
        }
        return posSec;
    }

    public static void main(String[] args) {
        FibonacciNumber fibonacciNumber = new FibonacciNumber();

        int output;
        int position = 4;


        output = fibonacciNumber.recursiveMethodToFindFibonacciNumber(position);
        //System.out.println(output); // 3

        // memorization
            // create a hashmap
        HashMap<Integer, Integer> memo = new HashMap<>();
        output = fibonacciNumber.memorizationFib(position, memo);
     //   System.out.println(output); // 3

        // bottom-up approach

        output = fibonacciNumber.bottomUpFib(0);
        //System.out.println(output);

        // bottom-up with constant space cost
        output = fibonacciNumber.bottomUpWithVar(position);
        System.out.println(output);

    }
}
