package LinkedList;

import java.util.ArrayList;
import java.util.HashMap;

public class KthToLastNode {

    public class LinkedListNode{
        public String value;
        public LinkedListNode next;

        public LinkedListNode(String value){
            this.value = value;
        }
    }


    public static LinkedListNode bruteForceApproach(int k, LinkedListNode head){
        // create a hashmap
        ArrayList<LinkedListNode> storedNodes = new ArrayList<>();

        LinkedListNode currentNode = head;

        while (currentNode != null){
            storedNodes.add(currentNode);
            currentNode = currentNode.next;
        }


        return storedNodes.get(storedNodes.size() - k);
    }


    public static LinkedListNode improvedApproach(int k, LinkedListNode head){
        /*
        * we can think of the k as the string between the last node and the k-th element
        *
        * */

        // firstly we make the string
        // create two pointer
        LinkedListNode kthElement = head;
        LinkedListNode endElement = head;

        for(int i=0; i<k-1; i++){
            endElement = endElement.next;
        }

        // now we keep push the two elements till we hit the end of the list
        while (endElement.next != null){
            kthElement = kthElement.next;
            endElement = endElement.next;
        }
        return kthElement;
    }

    public static LinkedListNode optimizedApproach1(int k, LinkedListNode head){
        // Step 1: walk through the list once, and calculate the length of the list
        int listLength = 0;
        LinkedListNode currentNode = head;

        while (currentNode != null){
            listLength +=1;
            currentNode = currentNode.next;
        }

        // Step 2: calculate how many steps it needs to take to go to the target node
        /*      1 2 3 4 5 6
        *               k=2
        *           steps required = length - kth
        * */
        int steps = listLength - k;

        currentNode = head;
        for(int i=0; i<steps; i++){
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    /*  Complexity:

            Time complexity: O(N)
                            cost model is array access
            Space complexity: O(1)
     */

    public static LinkedListNode optimizedApproach2(int k, LinkedListNode head){
        if (k < 1){
            throw new IllegalArgumentException("Impossible to find less than first to last node");
        }
        LinkedListNode leftNode = head;
        LinkedListNode rightNode = head;

        // move rightNode to the kth node
        for(int i=0; i<k-1; i++){
            // but along the way, if a rightNode doesn't have a next
            // then k is greater than the length of the list and there
            // can't be a kth-to-last node. We'll raise an error
            if (rightNode.next == null){
                throw new IllegalArgumentException("k is larger than the length of the linked list");
            }
            rightNode = rightNode.next;
        }

        while (rightNode != null){
            leftNode = leftNode.next;
            rightNode = rightNode.next;
        }
        return leftNode;
    }

    /*  Complexity:
            Time complexity:
                O(n) 因为这个虽然只循环了一遍但是这次有两个pointer, 所以每一次有 *2 的 list access
            Space complexity:
                O(1)
     */

    public static void main(String[] args) {

    }
}
