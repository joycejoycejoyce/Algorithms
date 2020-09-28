package Array;

public class MonotonicArray {
    /* 1 solution: the direction variable is too complicated
           # O(n) time | O(1) space
    */
    public static boolean isMonotonic(int[] array){
        if (array.length <=2){
            return true;
        }

        var direction = array[1] - array[2];

        for (int i=2; i<array.length; i++){
            if (direction == 0){
                direction = array[i] - array[i-1];
                continue;
            }

            if (breaksDirection(direction, array[i-1], array[i])){
                return false;
            }
        }
        return true;
    }

    public static boolean breaksDirection(int direction, int prev, int curr){
        int diff = curr - prev;
        if (direction > 0){
            return diff <0;
        }
        return diff > 0;
    }

    /* 2 solution: more clean
    *      # O(n) time | O(1) space
    * */
    public static boolean isMonotonic2(int[] array){
        boolean isNonDecreasing = true;
        boolean isNonIncreasing = true;

        for (int i=1; i<array.length; i++){
            if (array[i]-array[i-1] < 0){
                isNonDecreasing = false;
            }else if (array[i] - array[i-1] >0){
                isNonIncreasing = false;
            }
        }

        return isNonDecreasing || isNonIncreasing;
    }
}
