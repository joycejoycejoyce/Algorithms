package SortAlgo;

import java.util.Arrays;

public class BubbleSort {


    public static int[] intuitiveApproach(int[] inputArr){
        for(int i=0; i<inputArr.length-1; i++){
            for(int j=0; j<inputArr.length - 1-i; j++){
                int prev = j;
                int current = j+1;

                if (inputArr[prev] > inputArr[current]){
                    int temp = inputArr[prev];
                    inputArr[prev] = inputArr[current];
                    inputArr[current] = temp;
                }
            }
        }
        return inputArr;
    }
    /*  Complexity:
            Time complexity: O(n^2)
            Space complexity: O(1)
     */

    public static void main(String[] args) {
        int arr[] = {64, 34, 25, 12, 22, 11, 90};
        int[] output = intuitiveApproach(arr);
        System.out.println(Arrays.toString(output));
    }
}
