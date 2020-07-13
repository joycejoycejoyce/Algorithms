package Combinatorics;

import java.util.Random;

public class Simulate5slidedDie {

    private static final Random rnd = new Random();

    public static int rand5(){
        int result = 7;
        while (result > 5){
            result = rand7();
        }
        return result;
    }
    /* Complexity:
        Time complexity: O(infinity)
        Space: O(1)

     */

    private static int rand7() {
        return rnd.nextInt(7) + 1;
    }
}
