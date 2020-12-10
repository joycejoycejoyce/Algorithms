package Array;

public class SubarraySort {
    /* find the subarray that needs to be in placed to make the whole array sorted
     */

    public static int[] subarraySort(int[] array) {
        // create vars to record min and max unsorted
        int minOutOfPlaceVal = Integer.MAX_VALUE;
        int maxOutOfPlaceVal = Integer.MIN_VALUE;

        // check if the current value has < or > value , O(n)
        for(int i=0; i<array.length; i++){
            if (isOutOfPlace(i, array) ){
                minOutOfPlaceVal = Math.min(minOutOfPlaceVal, array[i]);
                maxOutOfPlaceVal = Math.max(maxOutOfPlaceVal, array[i]);
            }
        }

        // if the array is in place already
        if (minOutOfPlaceVal == Integer.MAX_VALUE){
            return new int[]{-1, -1};
        }

        // find the position for minOutOfPlace value
        // worst case [1,2,3,5,4] 3/5
        int leftPos = 0;
        while(leftPos <array.length && array[leftPos] <= minOutOfPlaceVal){
            leftPos++;
        }

        // find position for maxOutOfPlace value
        // worst case [1,2,3,5,4]
        int rightPos = array.length - 1;
        while(rightPos >=0 && array[rightPos] >= maxOutOfPlaceVal){
            rightPos --;
        }


        // if the array is out of place
        return new int[]{leftPos, rightPos};

    }

    /* Complexity:
            Time: O(n)
            Space: O(1)
     */


    public static boolean isOutOfPlace(int index, int[] array){
        if (index == 0){
            return array[index+1] < array[index];
        }
        if (index == (array.length-1) ){
            return array[index - 1] > array[index];
        }
        return array[index-1] > array[index] || array[index+1] < array[index];
    }
}
