package SortAlgo;

import java.util.Arrays;

public class InsertionSort {
    public static int[] intuitiveApproach(int[] inputArr){
        for(int i=1; i<inputArr.length; i++){
            int currentPt = i;
            while(currentPt>=1 &&  (inputArr[currentPt-1] > inputArr[currentPt] ) ){
                int prevPt = currentPt-1;
                int tempVal = inputArr[currentPt];
                inputArr[currentPt] =  inputArr[prevPt];
                inputArr[prevPt] = tempVal;
                currentPt-=1;
            }
            }
        return inputArr;
    }
    /* Complexity:
        Time: O(N^2)
            Access: is O(1)
            Swap: takes <index> times / number
                n: input array size
                0 swap + 1 swap + 2 swaps + ... + (n-2) swaps + (n-1) swaps
                = n*(n-1)/2 => O(N^2)
           total: O(N^2) + O(1) => O(N^2)
        Space: O(1) no data structure related to length

     */

    public static void main(String[] args) {
        int[] arr = new int[]{7,6,3,2,1};
        int[] output = intuitiveApproach(arr);
        System.out.println(Arrays.toString(output));
    }
}
