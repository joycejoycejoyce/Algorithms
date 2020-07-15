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

    static ListNode twoPointersApproach(ListNode headA, ListNode headB){
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
