package SortAlgo;

import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

// use PriorityQueue class
public class MaxHeap {
    PriorityQueue<Integer> maxHeap;

    public MaxHeap(){
        // by default Min Heap is implemented by PriorityQueue class
        // to make max heap, we need the method Collections.reverseOrder()
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void add(int value){
        maxHeap.add(value);
    }

    public int peek(){
        return maxHeap.peek();
    }

    public int remove(){
        return maxHeap.poll();
    }

    public void printHeap(){
        Iterator<Integer> iterator = maxHeap.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {

        MaxHeap maxHeap = new MaxHeap();

        maxHeap.add(10);
        maxHeap.add(30);
        maxHeap.add(20);
        maxHeap.add(400);

        maxHeap.printHeap();

        System.out.println("max value " + maxHeap.peek()); // 400

        System.out.println("remove max value "+ maxHeap.remove()); // 400

        maxHeap.printHeap();
    }

}
