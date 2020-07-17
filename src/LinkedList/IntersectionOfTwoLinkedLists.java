package LinkedList;

import javafx.scene.effect.SepiaTone;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IntersectionOfTwoLinkedLists {
    public static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }
    public static ListNode intuitiveApproach(ListNode l1, ListNode l2){
        /*
            5-> 4 -> 1
                        -> 8 -> 4 -> 5
           7  -> 1
         */

        /*
       store all the nodes l1 and l2 walked through
       {
       5
       7
       4
       1
       1
       8
       8--> has it

       }
            if they have no common node when l1 / l2 is exhaust
            -> no common element at all


         */

        /*
        edge case:
        1) one of the list is null
        took care of
        2) two of the lists are null
        took care of

         */

        Set<ListNode> walkedNodes = new HashSet<>();

        ListNode l1CurrNode = l1;
        ListNode l2CurrNode = l2;
        while(l1CurrNode != null && l2CurrNode != null){
            if (!walkedNodes.add(l1CurrNode) ){
                return l1CurrNode;
            }else{
                walkedNodes.add(l1CurrNode);
                l1CurrNode = l1CurrNode.next;
            }

            if (!walkedNodes.add(l2CurrNode)){
                return l2CurrNode;
            }else{
                walkedNodes.add(l2CurrNode);
                l2CurrNode = l2CurrNode.next;
            }
        }

        return null;
    }
    /* Complexity: first list length m, second list length n
        Time complexity: O(m+n)
                        if there is no intersection, check all nodes in l1 and l2
        Space complexity: O(m) or O(n)
     */

    static ListNode twoPointersApproach(ListNode headA, ListNode headB){
         /*
                1 -> 2 ->
                         3 -> 4 -> null
                     5 ->
            intersection length = l1Length - prevIntersection_length1 = 2
                                = l2length - prevIntersection_length2 = 3 - 1 = 2
            l1Length + prevIntersection_length2 == l2length+prevIntersection_length1
        then the two pts can land on the same node. If we jump

        over the first list (l1length - 1), then we jump (prevIntersection_length2+1), we will just land on the node (4) after the intersection node(3).
        ummm...
        so we have to jump l1length times. We can let the pt land on null!

        */
        ListNode a = headA, b = headB;
        while (a != b){
            if (a == null){
                a = headB;
            }else {
                a = a.next;
            }

            if (b == null){
                b = headA;
            }
            else{
                b = b.next;
            }
        }
        return a;
    }

    /*  Complexity:
            Time complexity: O(m+n) if there is no intersection
            Space complexity: O(1) no data structure stores anything.
     */


    public static ListNode createList( int[] vals){
        ListNode startNode = new ListNode(vals[0], null);
        ListNode nextNode = startNode;
        for(int i = 1; i<vals.length; i++){
            nextNode.next = new ListNode(vals[i], null);
            nextNode = nextNode.next;
        }
        return startNode;
    }

    public static void main(String[] args) {
        // test case 1
        ListNode l1Head =  createList( new int[]{1,2,3,4});
        ListNode l2Head = createList(new int[]{5,6});
        ListNode intersectionNode = twoPointersApproach(l1Head, l2Head);
        System.out.println(intersectionNode);
    }




}
