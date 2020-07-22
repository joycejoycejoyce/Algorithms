package SortAlgo;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class HeapSort {

    int[] maxHeap;
    int capacity;
    int size;

    public HeapSort(int capacity){
        maxHeap = new int[capacity+1];
        this.capacity = capacity;
        this.size = 0;
    }

    void add(int value){
        int currentIndex = size + 1;
        maxHeap[currentIndex] = value;
        size +=1;
    }

    int removeMax(){
        int currentMax = maxHeap[1];

        // bring the last node up


        return currentMax;
    }



    int peek(){
        return maxHeap[1];
    }


    static void swap(int[] maxHeap, int fpos, int spos){
        int tempVal = maxHeap[fpos];
        maxHeap[fpos] = maxHeap[spos];
        maxHeap[spos] = tempVal;
    }

    int parentIndex(int pos){
        return pos/2;
    }

    int leftChildIndex(int pos){
        return pos*2;
    }

    int rightChildIndex(int pos){
        return pos*2+1;
    }

    boolean isLeaf(int pos){
        if ( pos <= capacity / 2 ){
            return false;
        }
        return true;
    }



    static void sortArr(int[] inputArr){
        int size = inputArr.length;
        // build the max heap in place
        for(int i= size/2-1; i>=0; i--){
            // if the arr[i] < children, then swap
            heapify(inputArr, size, i);
        }

        // sort arr
        for(int j=size-1; j>0; j--){
            swap(inputArr,0, j);
            heapify(inputArr, j, 0);
        }
    }


    static void heapify(int arr[], int heapSize, int i){

        // flag to record the index of the largest value
        int largestValIndex = i;

        // get the leftChild and the rightChild
        int leftChildIdx = 2*i+1;
        int rightChildIdx = 2*i+2;

        // compare
        if (leftChildIdx < heapSize && arr[leftChildIdx] > arr[largestValIndex]){
            largestValIndex = leftChildIdx;
        }
        if (rightChildIdx < heapSize && arr[rightChildIdx] > arr[largestValIndex]){
            largestValIndex = rightChildIdx;
        }

        // if the largest val index is not the original one
        // it means it needs to swap
        if (largestValIndex != i){
            swap(arr, i, largestValIndex);

            // since it is not in order && not sure if it reaches the deepest
            heapify(arr, heapSize, largestValIndex);
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{10, 8, 9, 7, 6};
        sortArr(arr);
        System.out.println(Arrays.toString(arr));
    }
}
