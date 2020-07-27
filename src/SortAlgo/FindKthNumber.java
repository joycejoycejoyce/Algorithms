package SortAlgo;

import java.util.Random;

public class FindKthNumber {
    Random random = new Random();

/*Method 1: Quick Selection */

    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int partition(int[] arr, int left, int right){
        int pivot = arr[right];
        int endBound = left - 1;
        for (int pt=left; pt<right; pt++){
            if (arr[pt] < pivot){
                swap(arr, ++endBound, pt);
            }
        }
        swap(arr, endBound + 1, right);
        return endBound+1;
    }

    int randomPartition(int[] arr, int leftIdx, int rightIdx){
        int i = random.nextInt(rightIdx - leftIdx + 1) + leftIdx;
        swap(arr, i, rightIdx);
        return partition(arr, leftIdx, rightIdx);
    }

    public int quickSelection(int[] arr, int leftIdx, int rightIdx, int targetIdx){
        int pivotIdx = randomPartition(arr, leftIdx, rightIdx);
        if (pivotIdx == targetIdx){
            return arr[pivotIdx];
        }else{
            if (pivotIdx > targetIdx ){
               return quickSelection(arr, leftIdx, pivotIdx - 1,  targetIdx);
            }else{
                return quickSelection(arr, pivotIdx+1, rightIdx, targetIdx);
            }
        }
    }

    public int findKthLargest(int[] nums, int k){
        return quickSelection(nums, 0, nums.length - 1, nums.length - k);
    }

    /* Complexity:
        Time: O(n)
        Space: O(klogn);

    * */


    /*Heap Sort*/

    public void heapify(int[] arr, int parentIdx, int arrSize){
        int leftChildIdx = parentIdx * 2 + 1;
        int rightChildIdx = parentIdx * 2 + 2;
        int largestIdx = parentIdx;

        if (leftChildIdx < arrSize && arr[parentIdx] < arr[leftChildIdx]){
            largestIdx = leftChildIdx;
        }
        if (rightChildIdx < arrSize && arr[parentIdx] < arr[rightChildIdx]){
            largestIdx = rightChildIdx;
        }

        if (largestIdx != parentIdx){
            swap(arr, largestIdx, parentIdx);
            heapify(arr, largestIdx, arrSize);
        }
    }

    public void buildMaxHeap(int[] arr, int arrSize){
        for(int pt = arrSize/2; pt>0; pt--){
            heapify(arr, pt, arrSize);
        }
    }


    public int findKthLargestHeap(int[] nums, int k){

        buildMaxHeap(nums, nums.length);

        int heapBound = nums.length;

        for(int maxPt = nums.length - 1; maxPt<nums.length - k +1; maxPt-- ){
            heapBound-=1;
            swap(nums, 0, maxPt);
            heapify(nums, 0, heapBound);
        }
        return nums[0];
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 4, 9, 3};
        FindKthNumber findKthNumber = new FindKthNumber();
        int output = findKthNumber.findKthLargest(arr, 2); // 8
        System.out.println(output);
    }
}
