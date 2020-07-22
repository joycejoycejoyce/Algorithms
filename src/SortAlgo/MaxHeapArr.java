package SortAlgo;

import java.util.Arrays;

// implemented using array
public class MaxHeapArr {

    private int[] heap;
    private int size;
    private int capacity;

    public MaxHeapArr(int capacity){
        this.capacity = capacity;
        this.size = 0;
        heap = new int[this.capacity + 1];
        heap[0] = Integer.MAX_VALUE;
    }

    // returns position of parent
    private int findParentIndex(int pos){
        return pos/2;
    }

    private int leftChild(int pos){
        return (2*pos);
    }

    private int rightChild(int pos){
        return (2*pos + 1);
    }

    private boolean isLeaf(int pos){
        if (pos >= (size / 2) && pos <= size ){
            return true;
        }
        return false;
    }

    void swap(int fpos, int spos){
        int tmp;
        tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    void insert(int value){
        int currentIndex = size + 1;
        heap[currentIndex] = value;

        int parentIndex = findParentIndex(currentIndex);

        int childIndex = currentIndex;

        while (heap[parentIndex] < heap[childIndex] && childIndex > 1){
            swap(parentIndex, childIndex);

            childIndex = parentIndex;
            parentIndex = findParentIndex(parentIndex);

        }
        size+=1;
    }

    public int extractMax(){
        int currMax = heap[1];
        // put the bottom one to pos 0
        heap[1] = heap[size];
        heap[size] = 0;

        sinkIteration(1);

        // minus 1 of the arr size
        size -=1;

        return currMax;
    }


    void sinkRecursion(int pos){
        if (isLeaf(pos)){
            return;
        }
        if (heap[pos] < heap[leftChild(pos)] || heap[pos] < heap[rightChild(pos)]){
            if (heap[leftChild(pos)] > heap[rightChild(pos)]){
                swap(pos, leftChild(pos));
                sinkRecursion(leftChild(pos));
            }else{
                swap(pos, rightChild(pos));
                sinkRecursion(rightChild(pos));
            }
        }
    }

    void sinkIteration(int pos){
        if (isLeaf(pos)){
            return;
        }
        int currentIndex = pos;
        while(heap[currentIndex] < heap[leftChild(currentIndex)] || heap[currentIndex] < heap[rightChild(currentIndex)]){
            if (heap[rightChild(currentIndex)] < heap[leftChild(currentIndex)]) {
                swap(currentIndex, leftChild(currentIndex));
                currentIndex = leftChild(currentIndex);
            }else{
                swap(currentIndex, rightChild(currentIndex));
                currentIndex = rightChild(currentIndex);
            }
        }
    }

    public String printHeap(int[] inputHeap){
        return Arrays.toString(inputHeap);
    }

    public static void main(String[] args) {
        MaxHeapArr maxHeap = new MaxHeapArr(7);
        maxHeap.insert(2);
        System.out.println(maxHeap.printHeap(maxHeap.heap));  // [0, 2, 0, 0, 0, 0, 0, 0]

        maxHeap.insert(4);
        System.out.println(maxHeap.printHeap(maxHeap.heap));  // [0, 4, 2, 0, 0, 0, 0, 0]

        maxHeap.insert(10);
        System.out.println(maxHeap.printHeap(maxHeap.heap));  // [0, 10, 2, 4, 0, 0, 0, 0]

        maxHeap.insert(1);
        System.out.println(maxHeap.printHeap(maxHeap.heap));  // [0, 10, 2, 4, 1, 0, 0, 0]

        maxHeap.insert(8);
        System.out.println(maxHeap.printHeap(maxHeap.heap));  // [0, 10, 8, 4, 1, 2, 0, 0]

        maxHeap.insert(19);
        System.out.println(maxHeap.printHeap(maxHeap.heap));  // [0, 19, 2, 10, 1, 4, 8, 0]

        maxHeap.insert(6);
        System.out.println(maxHeap.printHeap(maxHeap.heap));  // [0, 19, 8, 10, 1, 2, 4, 6]

        int removedMax = maxHeap.extractMax();
        System.out.println("removed max "+removedMax);
        System.out.println(maxHeap.printHeap(maxHeap.heap));  // [0, 10, 8, 6, 1, 2, 4, 0]

    }
}


