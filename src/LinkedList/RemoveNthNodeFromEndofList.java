package LinkedList;

public class RemoveNthNodeFromEndofList {
    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(){
        }

        public ListNode(int val){
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public static ListNode twoPasses(ListNode head, int n){
        // create a dummy head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // get the length of the list
        int L = -1;
        ListNode pt = dummy;

        while(pt != null){
            L+=1;
            pt=pt.next;
        }

        // jump to the node before target node
        pt = dummy;
        int jumpTimes = L - n;
        for(int i=0; i<jumpTimes; i++){
            pt = pt.next;
        }

        // point the node.next = node.next.next
        pt.next = pt.next.next;

        return dummy.next;
    }

    /* Complexity:
            L : the length of the linked list
            Time: O(L). The algorithm makes 2 traversal of the list, first to calculate list length L
                and second to find the (L-n)th node. There are 2L - n operations and the time complexity is O(L)
            Space: O(1)
     */

    public ListNode onePassApproach(ListNode head, int n) {
        /* one pass */
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // create two pointers
        ListNode first = dummy, second = dummy;

        // keep the distance as n
        for(int i=0; i<n+1; i++){
            first = first.next;
        }

        while(first != null){
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;

        return dummy.next;
    }

    /* Complexity:
        Time: O(L)
        Space: O(1)
     */

}
