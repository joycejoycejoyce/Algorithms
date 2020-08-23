package LinkedList;

public class ConvertBinaryNumberinaLinkedList {
    public int mathApproach(ListNode head){
        int sum = 0;

        ListNode pointer = head;

        while(pointer != null){
            sum = sum * 2 + pointer.val;

            pointer = pointer.next;
        }

        return sum;
    }

    /* Complexity:
            Time: O(N)
            Space: O(1)
     */
}
