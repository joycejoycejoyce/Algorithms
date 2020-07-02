package graph;

public class FindRepeat {


    public static int findDuplicate(int[] intArray){
        /* 1. Purpose: How to identify the duplicate?
        *   After observation of examples, we know that the index that has more than 1 incoming arraws,
        *       Proof:
        *           position = value;
        *           index = position - 1;
        *           index = value - 1;   if a value is duplicated, the next pointer is duplicated.
        *
        *
        *   The last index will never have any incoming pointer.
        *       Proof:
        *           length: n;
        *           value range: (1, n-1);
        *           MAX_value = n-1;
        *           MAX_position = n-1;
        *           LAST_position = n;
        *           MAX_position < LAST_position;
        *
        *   There is always a circle and the first node of the circle is ALWAYS a duplicate value
        *
        *  2. Find the first node of the circle. Because we already know that this position is the duplicate value
        *       Stick theory:
        *            put a stick to the list to find the first node of a circle.
        *            stick.length == circle.length;
        * 3. Find circle length
        *       To find the circle length, we have to enter the circle first
        * 4. Enter the circle of the list
        *       The worst case is to enter the circle from the first node
        *           So for the worst case we need to move length-1 steps
        *
        *
        * To code it up, we move from bottom-up
        *
        * */

        // 4. enter the circle of the list
        int headIndex = intArray.length-1;
            // we need to move to the end of the list
        int currVal = intArray[intArray.length -1];
        for (int step = 0; step < intArray.length-1; step++){
            int nextIndex = currVal - 1;
            currVal = intArray[nextIndex];
        }

        // currVal is the value of a node inside the circle
        // 3. now we need to calculate the length of the circle;
        int startVal = currVal;
        int detectVal = intArray[currVal - 1];
        int circleLength = 1;
        while (startVal != detectVal){
            int nextIndex = detectVal -1;
            detectVal = intArray[nextIndex];
            circleLength +=1;
        }
        // when we out of the loop, we get the length of the circle
        // 2. It is time to use the stick approach to find the first node in the circle
            // firstly, we need a tailNode
        int tailNode = intArray[intArray.length - 1];
            // and a headNode
        int headNodeNextPosVal = intArray[headIndex];
            // the headNode needs to move circle_length steps
        for (int i=0; i<circleLength; i++){
            int nextIndex = headNodeNextPosVal - 1;
            headNodeNextPosVal = intArray[nextIndex];
        }
        // now headnode is in the right pos with the right value to go to the next node
        // we need to move both of the nodes until they hold the same value
        while (tailNode != headNodeNextPosVal){
            int nextIndexForTail = tailNode -1;
            tailNode = intArray[nextIndexForTail];

            int nextIndexForHead = headNodeNextPosVal - 1;
            headNodeNextPosVal = intArray[nextIndexForHead];
        }

        // since we already determined the value of the first node is a duplicate
            // we return the value of the first node
        return headNodeNextPosVal;
    }



    public static void main(String[] args) {

            int output = Integer.MIN_VALUE;

            FindRepeat findRepeat=new FindRepeat();

            int[] arrList = {3,1,2,2};

            output = findRepeat.findDuplicate(arrList);
            System.out.println("output="+output);
    }
}
