package Combinatorics;

import java.util.Random;

public class Simulate7SidedDie {
    public static int rand7Table(){
        int[][] results = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 1, 2, 3},
                {4, 5, 6, 7, 1},
                {2, 3, 4, 5, 6},
                {7, 0, 0, 0, 0},
        };

        while (true) {

            // do our die rolls
            int row = rand5() - 1;
            int column = rand5() - 1;

            // case: we hit an extraneous outcome
            // so we need to re-roll
            if (row == 4 && column > 0) {
                continue;
            }

            // our outcome was fine. return it!
            return results[row][column];
        }
    }


    private static final Random rnd = new Random();

    private static int rand5() {
        return rnd.nextInt(5) + 1;
    }
}
