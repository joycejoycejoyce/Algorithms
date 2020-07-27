package SortAlgo;

import java.util.Arrays;

public class QuickSort {

    /* This function takes last element as pivot,
        places the pivot element at its correct position in sorted array
        and places all smaller to left of pivot and all greater elements to right of pivot
     */
    int partition(int arr[], int low, int high){
        int pivot = arr[high];
        int endBound = low;

        for(int pt=low; pt< high; pt++){
            if (arr[pt] < pivot){
                // swap pt and endBound
                int tempVal = arr[endBound];
                arr[endBound] = arr[pt];
                arr[pt] = tempVal;

                endBound+=1;
            }
        }

        int tempVal = arr[high];
        arr[high] = arr[endBound];
        arr[endBound] = tempVal;

        return endBound;
    }


    /* The main function that implements QuickSort()
        arr[] --> array to be sorted,
        low   --> Starting index,
        high  --> Ending index
     */
    void sort(int arr[], int low, int high){
        if (low < high){
            int pivot = partition(arr, low, high);

            sort(arr, low, pivot - 1);
            sort(arr, pivot+1, high);

        }
    }

    public static void main(String[] args) {

        int[] arr = {10, 4, 7, 3, 9, 1, 5, 8, 2,};

        QuickSort quickSort = new QuickSort();
        quickSort.sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
