package DynamicProgramming;

public class ClimbingStairs {


    // recursion with memorization

    public int recursionWithMemorizationApproach(int n){
        int memo[] = new int[ n+1];
        return recursionWithMemorizationApproach(n, n, memo);
    }

    public int recursionWithMemorizationApproach(int i, int n, int memo[]){
        if (i > 0 && i <3 ){
            return i;
        }

        if (memo[i] > 0){
            return memo[i];
        }

        memo[i] = recursionWithMemorizationApproach(i-1, n, memo) +
                    recursionWithMemorizationApproach(i-2, n, memo);
        return memo[i];
    }
    /*  Complexity:
            Time complexity: O(n)
            Space complexity: O(n): 其实是 2n, 因为这个 call stack 有 n 的高度，并且
                                    有一个 n 长度的array.
     */


    // dynamic programming
    public int dynamicProgrammingApproach(int totalStairs){
        // edge cases
        // if n < 2
        if (totalStairs == 1){
            return totalStairs;
        }

        // create an array
        int[] stairsArr = new int[ totalStairs + 1];
        stairsArr[0] = 1;
        stairsArr[1] = 1;
        stairsArr[2] = 2;

        int currentStair = 3;

        while (currentStair <= totalStairs){
            stairsArr[currentStair] = stairsArr[currentStair-1]+stairsArr[currentStair-2];
            currentStair+=1;
        }

        return stairsArr[totalStairs];
    }
    /*  Complexity:
            Time complexity: O(n)
            Space complexity: O(n)

     */



    // optimized dynamic programming
    public int optimizedApproach(int totalStairs){
        // edge case: if totalStairs = 1
        if (totalStairs <=2){
            return totalStairs;
        }

        int prev = 1;
        int curr = 2;

        for (int i=3; i<= totalStairs; i++){
            int temp = curr;
            curr = curr + prev;
            prev = temp;
        }

        return curr;
    }

    /*  Complexity:
            Time complexity: O(n)
            Space complexity: O(1)

     */




    public static void main(String[] args) {
        int stairs = 6;
        ClimbingStairs climbingStairs = new ClimbingStairs();

        int output = climbingStairs.optimizedApproach(stairs);
        System.out.println(output);

    }
}
