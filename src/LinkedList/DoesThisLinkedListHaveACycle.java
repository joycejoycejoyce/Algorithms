package LinkedList;

import java.util.HashSet;
import java.util.Set;

public class DoesThisLinkedListHaveACycle {



    public static class LinkedListNode{
        public int value;
        public LinkedListNode next;

        public LinkedListNode(int value){
            this.value = value;
        }
    }

    private static LinkedListNode[] valuesToLinkedListNodes(int[] values) {
        final LinkedListNode[] nodes = new LinkedListNode[values.length];
        for (int i = 0; i < values.length; ++i) {
            nodes[i] = new LinkedListNode(values[i]);
            if (i > 0) {
                nodes [i - 1].next = nodes[i];
            }
        }
        return nodes;
    }

    public boolean intuitiveApproach(LinkedListNode firstNode){

        Set<LinkedListNode> walkedNodes = new HashSet<>();

        LinkedListNode currentNode = firstNode;

        while (currentNode != null){
            if (walkedNodes.contains(currentNode)){
                return true;
            }

            walkedNodes.add(currentNode);
            currentNode = currentNode.next;
        }

        return false;

    }

    /* Complexity:
        Time complexity: O(N)
        Space complexity: O(N)
     */

    public boolean improvedApproach(LinkedListNode firstNode){
        // set two runners
        // fast runner runs quicker
        LinkedListNode fastRunner = firstNode;
        // slower runner runs slower
        LinkedListNode slowerRunner = firstNode;

        while (fastRunner != null){
            if (fastRunner.next != null){
                fastRunner = fastRunner.next.next;
                slowerRunner = slowerRunner.next;
                if (fastRunner == slowerRunner){
                    return true;
                }
            }else{
                break;
            }
        }
        // if the fast runner hits the end, it means the SLL doesn't have a loop
        return false;
    }

    public boolean optimizedApproach(LinkedListNode firstNode){
        LinkedListNode fastRunner = firstNode, slowerRunner = firstNode;

        while (fastRunner != null && fastRunner.next != null){
            slowerRunner = slowerRunner.next;
            fastRunner = fastRunner.next.next;

            if(fastRunner == slowerRunner){
                return true;
            }
        }

        return false;
    }

    /*  Complexity:
            Time complexity: O(N)
                    contradiction proof -> fastRunner cannot skip slowerRunner
                    cost model: list access;

            space complexity: O(1)
                    Because there is no data structure that contains the full list
                    statements are considered constant

     */

    public static void main(String[] args) {

        // test 1
        DoesThisLinkedListHaveACycle test1 = new DoesThisLinkedListHaveACycle();

        final LinkedListNode[] test1Nodes = valuesToLinkedListNodes(new int[] {1, 2, 3, 4});
        boolean result1 = test1.intuitiveApproach(test1Nodes[0]);
        System.out.println(result1); // false

        boolean result1Improved = test1.improvedApproach(test1Nodes[0]);
        System.out.println(result1Improved); // false




        // test 2
        DoesThisLinkedListHaveACycle test2 = new DoesThisLinkedListHaveACycle();

        final LinkedListNode[] test2Nodes = valuesToLinkedListNodes(new int[] {1, 2, 3, 4});
        test2Nodes[3].next = test2Nodes[0];

        final boolean result2 = test2.intuitiveApproach(test2Nodes[0]);
        System.out.println(result2); // true

        final boolean result2Improved = test2.improvedApproach(test2Nodes[0]);
        System.out.println(result2Improved); // true



        // test 3
        DoesThisLinkedListHaveACycle test3 = new DoesThisLinkedListHaveACycle();

        final LinkedListNode[] test3Nodes = valuesToLinkedListNodes(new int[] {1, 2, 3, 4, 5});
        test3Nodes[4].next = test3Nodes[2];

        final boolean result3 = test3.intuitiveApproach(test3Nodes[0]);
        System.out.println(result3); // true

        final boolean result3Improved = test3.improvedApproach(test3Nodes[0]);
        System.out.println(result3Improved); // true



        // test 4
        DoesThisLinkedListHaveACycle test4 = new DoesThisLinkedListHaveACycle();

        final LinkedListNode[] test4Nodes = valuesToLinkedListNodes(new int[] {1, 2, 3, 4, 5});
        test4Nodes[4].next = test4Nodes[3];

        final boolean result4 = test4.intuitiveApproach(test4Nodes[0]);
        System.out.println(result4); // true

        final boolean result4Improved = test4.improvedApproach(test4Nodes[0]);
        System.out.println(result4Improved); // true




        // test 5
        DoesThisLinkedListHaveACycle test5 = new DoesThisLinkedListHaveACycle();

        final boolean result5 = test5.intuitiveApproach(null);
        System.out.println(result5); // false

        final boolean result5Improved = test5.improvedApproach(null);
        System.out.println(result5Improved); // false





        // test 6
        DoesThisLinkedListHaveACycle test6 = new DoesThisLinkedListHaveACycle();

        final LinkedListNode test6Node = new LinkedListNode(1);

        final boolean test6Result = test6.intuitiveApproach(test6Node);
        System.out.println("test 6"+test6Result); // false

        final boolean test6ResultImproved = test6.improvedApproach(test6Node);
        System.out.println("test 6"+test6ResultImproved); // false



        // test 7
        DoesThisLinkedListHaveACycle test7 = new DoesThisLinkedListHaveACycle();

        final LinkedListNode test7Node = new LinkedListNode(1);
        test7Node.next = test7Node;

        final boolean test7Result = test7.intuitiveApproach(test7Node);
        System.out.println(test7Result); // true

        final boolean test7ResultImproved = test7.improvedApproach(test7Node);
        System.out.println(test7ResultImproved); // true



    }
}
