package SortAlgo;

import java.util.Arrays;

public class MergeSort {

    public static void split(int[] inputArr, int startIndex, int endIndex){
        if (startIndex == endIndex){
            return;
        }
        int midIndex = (startIndex + endIndex) / 2;

        split(inputArr, startIndex, midIndex);
        split(inputArr, midIndex+1, endIndex);
        mergeArrs(inputArr, startIndex, midIndex, endIndex);
    }

    public static void mergeArrs(int[] inputArr, int startIndex, int endIndex, int midIndex){
        // create a new arr
        int newArrSize = endIndex - startIndex + 1;
        int[] tempArr = new int[newArrSize];

        int inputArrPointer = startIndex;
        int inputArrSecHalfPt = midIndex;
        int tempArrPt = 0;

        while (inputArrPointer <= midIndex && inputArrSecHalfPt < endIndex){
            if (inputArr[inputArrPointer] < inputArr[inputArrSecHalfPt]){
                tempArr[tempArrPt] = inputArr[inputArrPointer];
                inputArrPointer+=1;
            }else{
                tempArr[tempArrPt] = inputArr[inputArrSecHalfPt];
                inputArrSecHalfPt+=1;
            }
            tempArrPt+=1;
        }

        while(inputArrPointer <= midIndex ){
            tempArr[tempArrPt] = inputArr[inputArrPointer];
            inputArrPointer +=1;
            tempArrPt+=1;
        }

        while(inputArrSecHalfPt <= endIndex){
            tempArr[tempArrPt] = inputArr[inputArrSecHalfPt];
            inputArrSecHalfPt+=1;
            tempArrPt+=1;
        }
        // copy the elements back to the original arr
        for (int i=startIndex; i<=endIndex; i++){
            inputArr[i] = tempArr[i-startIndex];
        }

    }

    public static void optimizedApproach(int[] inputArr, int start, int end){
        if (start < end){
            int mid = (start + end) / 2;
            optimizedApproach(inputArr, start, mid);
            optimizedApproach(inputArr, mid+1, end);
            merge(inputArr, start, mid, end);
        }
    }

    public static void merge(int[] inputArr, int start, int mid, int end){
        // create temp arrays
        int[] temp = new int[end-start+1];

        int i = start, j = mid+1, k = 0;

        while (i <= mid && j <= end){
            if (inputArr[i] <= inputArr[j]){
                temp[k] = inputArr[i];
                i += 1;
            }else{
                temp[k] = inputArr[j];
                j +=1;
            }
            k += 1;
        }

        // add elements left in the first interval
        while (i <= mid){
            temp[k] = inputArr[i];
            k+=1;
            i+=1;
        }

        while(j <= end){
            temp[k] = inputArr[j];
            k+=1;
            j+=1;
        }

        // copy temp to original interval
        for(i= start; i<=end; i++){
            inputArr[i] = temp[i - start];
        }
    }


    public static void main(String[] args) {
        int[] arr = { 12, 11, 13, 5, 6, 7 };
        optimizedApproach(arr, 0, arr.length - 1 );
        System.out.println(Arrays.toString(arr));
    }
}
