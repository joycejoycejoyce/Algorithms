package SortAlgo;

import java.util.Arrays;

public class SelectionSort {

    public static class MinValPosPair{
        private int value;
        private int position;
        public MinValPosPair(int value, int position){
            this.value = value;
            this.position = position;
        }

        public int getValue() {
            return value;
        }

        public int getPosition() {
            return position;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }

    public static int[] intuitiveApproach(int[] inputArr){

        for(int i=0; i<inputArr.length; i++){

            MinValPosPair pair = new MinValPosPair(inputArr[i], i);
            for(int j=i; j<inputArr.length-1; j++){
                if ( pair.getValue() > inputArr[j] ){
                    pair.setValue(inputArr[j]);
                    pair.setPosition(j);
                }
            }
            if (pair.getPosition() != i){
                int tempVal = pair.getValue();
                inputArr[pair.getPosition()] = inputArr[i];
                inputArr[i] = tempVal;
            }
        }

        return inputArr;
    }

    public static int[] optimizedApproach(int[] inputArr){
        for(int i=0; i<inputArr.length-1; i++){
            // record the index of the min val
            int minIndex = i;
            for (int j=i+1; j < inputArr.length; j++){
                if ( inputArr[minIndex] > inputArr[j]){
                    minIndex = j;
                }
            }
            int minVal = inputArr[minIndex];
            inputArr[minIndex] = inputArr[i];
            inputArr[i] = minVal;
        }
        return inputArr;
    }
    /* Complexity:
        Time complexity: O(n^2)
                         for example, you have an arr of size N
                         N + (N-1)+... + 3 + 2
                         -> 2+3+...(N-1) + N => N(N-1)/2 - 1 => O(N^2)
        Space complexity: O(1)

     */

    public static void main(String[] args) {
        int arr[] = {64,25,12,22,11};
        int[] arr1 = {10,34,2,56,7,67,88,42};
        int[] output = optimizedApproach(arr1);
        System.out.println(Arrays.toString(output));
    }
}
