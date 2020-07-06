package LinkedList;

public class ReverseALinkedList {

    public static class LinkedListNode {
        public int value;
        public LinkedListNode next;

        public LinkedListNode(int value){
            this.value = value;
        }
    }

    public static LinkedListNode intuitiveApproach(LinkedListNode currentNode){
        /*
        *    1 -> 2 -> 3 -> 4 -> null
        *                   *
        *    1 -> 2 ->  NULL<- 3 <- 4
        *              *
        *    1 -> 2 <- 3 <- 4
        *         *
        *   recursive, go to the last node, and set the pointer to the previous node
        *
        *
        * */
        if (currentNode == null){
            throw new  IllegalArgumentException("The input LL is null");
        }

        if (currentNode.next == null){
            return currentNode;
        }

        LinkedListNode reversedHead = intuitiveApproach(currentNode.next);
        currentNode.next.next = currentNode;
        currentNode.next = null;
        return reversedHead;
    }

    /* Complexity:
            This is a recursive way to do it;
            We walked through all the nodes,
            Time complexity: O(N)
            Space complexity: O(N)
                In recursive way, the call stack accumulates
     */



    public static LinkedListNode improvedApproach(LinkedListNode headOfList){
        /* usually want can be done recursively can be done in a while loop
        *                           f
        *   Null<-1 <- 2 <- 3  4 -> NULL
        *              h       s
        *   reversedHead
        *   s.next = null
        *   s=f
        *   f=f.next
        *    s.next = h
        *
        *   缺一个 reversedListHead
        * */


        // edge case
        if (headOfList == null || headOfList.next ==null){
            return headOfList;
        }


        // 1. set three pointers, one is the fast pointer, one is the slower pointer,
            // one is to record the head of the reversedList


        LinkedListNode reversedListHead = null;
        LinkedListNode slowNode = headOfList;
        LinkedListNode fastNode = headOfList.next;

        while (slowNode != null){
            slowNode.next = reversedListHead;
            reversedListHead = slowNode;
            slowNode = fastNode;
            if(fastNode != null) {
                fastNode = fastNode.next;
            }
        }

        return reversedListHead;
    }




    public static LinkedListNode optimizedApproach(LinkedListNode headOfList){
        LinkedListNode previous = null;
        LinkedListNode current = headOfList;
        LinkedListNode next = null;

        /*
        *   1 <- 2 <- 3 <- 4 -> NULL
        *                  p     c
        *
        *   edge case
        *       []
        *
        *       [1]
        * */
        while (current != null){
            // next node == temp
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    /*  Complexity:
            In the optimized solution, we iterate through every node
            Time complexity: O(N)
            Space complexity: O(1)
                Compare to the recursive way, iteration saves more space
     */







    static LinkedListNode[] valuesToLinkedListNodes(int[] values) {
        final LinkedListNode[] nodes = new LinkedListNode[values.length];
        for (int i = 0; i < values.length; i++) {
            nodes[i] = new LinkedListNode(values[i]);
            if (i > 0) {
                nodes[i - 1].next = nodes[i];
            }
        }
        return nodes;
    }

    private static boolean isListReversed(LinkedListNode list, LinkedListNode[] originalNodes) {
        int i = originalNodes.length - 1;
        while (list != null && i >= 0) {
            if (originalNodes[i] != list) {
                return false;
            }
            list = list.next;
            i--;
        }
        return list == null && i == -1;
    }

    public static void main(String[] args) {
        boolean testResult;

        // test 1
        final LinkedListNode[] nodes1 = valuesToLinkedListNodes(new int[] {1, 2});
        final LinkedListNode result1 = improvedApproach(nodes1[0]);

        testResult = isListReversed(result1, nodes1);
        System.out.println(testResult); // true


        // test 2

        final LinkedListNode[] nodes2 = valuesToLinkedListNodes(new int[] {1, 2, 3, 4, 5, 6});
        final LinkedListNode result2 = improvedApproach(nodes2[0]);
        testResult = isListReversed(result2, nodes2);
        System.out.println(testResult); // true


    }
}
