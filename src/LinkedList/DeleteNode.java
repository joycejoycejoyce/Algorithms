package LinkedList;

public class DeleteNode {

    public static class LinkedListNode {
        public int value;
        public LinkedListNode next;

        public LinkedListNode(int value){
            this.value = value;
        }
    }
    public static void optimizedSolution(LinkedListNode nodeToDelete){
        LinkedListNode nextNode = nodeToDelete.next;

        if (nextNode == null){
            /* if I want to set the value to null, I need to change LinkedListNode value to be Integer type*/
            throw new IllegalArgumentException("Can't delete the last node from the list");
        }

        nodeToDelete.value = nextNode.value;
        nodeToDelete.next = nextNode.next;
    }

    public static void main(String[] args) {
        
    }
}
