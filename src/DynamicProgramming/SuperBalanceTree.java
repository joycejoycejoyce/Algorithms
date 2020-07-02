package DynamicProgramming;

import java.util.*;

public class SuperBalanceTree {

    public static class BinaryTreeNode {

        public int value;
        public BinaryTreeNode left;
        public BinaryTreeNode right;

        public BinaryTreeNode(int value) {
            this.value = value;
        }

        public BinaryTreeNode insertLeft(int leftValue) {
            this.left = new BinaryTreeNode(leftValue);
            return this.left;
        }

        public BinaryTreeNode insertRight(int rightValue) {
            this.right = new BinaryTreeNode(rightValue);
            return this.right;
        }
    }



    public boolean testBalanceTree(BinaryTreeNode treeRoot){

        // edge case: when treeRoot is null
        if (treeRoot == null){
            return true;
        }
        /*
        if a tree has three diff lengths
        or two diff lengths but the l1-l2 >1   => not balanced tree


        use depth first search
                while + stack
         */

        Deque<NodeWithHeight> stack = new ArrayDeque<>();
        stack.push(new NodeWithHeight(treeRoot, 0));

        // an arr to record all the lengths
        ArrayList<Integer> heights = new ArrayList<>(3);
        while (!stack.isEmpty()){
            // get the top one in the stack
            NodeWithHeight nodeWithHeight = stack.pop();
            // get the node and the height
            BinaryTreeNode currNode = nodeWithHeight.node;
            int currHeight = nodeWithHeight.height;

            // if the node is a leaf node
            if (currNode.left == null && currNode.right == null){
                // check if we have the height already
                if ( !heights.contains(currHeight) ){
                    // add the height to the set
                    heights.add(currHeight);

                    // check if currently all heights are valid
                    if(heights.size() > 2){
                        return false;
                    }
                    if (heights.size() == 2 && Math.abs(heights.get(0) - heights.get(1)) > 1){
                        return false;
                    }
                }
                // if the current node is not a leaf node
            }else{
                // if the curr node has left child, add left child to the stack to check
                if (currNode.left != null){
                    stack.add(new NodeWithHeight(currNode.left, currHeight+1));
                }
                // if the curr node has right child, add right child to the stack to check
                if (currNode.right != null){
                    stack.add(new NodeWithHeight(currNode.right, currHeight+1));
                }
            }
        }

        // if the tree is been looped through and has no error
        return true;
    }


    /*Analysis:
    *  time complexity: O(n)
    *       If it is a balanced tree -> we need to walk through every node in the tree, which takes O(n) time
    *       If it is not a balanced tree -> we need to walk through parts of the tree, which takes less than n nodes < O(n) time
    *
    * space complexity: O(n)
    *       1. created a stack : hold nodes
    *           Everytime, we process the right node first, and hold the left node of each level
    *
    *           For the worst case:
    *                   Node1
    *                  L2     Node2
    *                      L3     Node3
    *                          L4     Node4
    *              Stack L2, L3, L4,
    *                   O(depth); => at most approximately N/2 => O(N)
    *
     *       2. created a heights arrayList: hold height.
    *           Regardless of what the input is, we will always create an arraylist with length 3.
    *           So it can be considered constant as O(1)
    *
    *
    * */
    private class NodeWithHeight{
        BinaryTreeNode node;
        int height;
        public NodeWithHeight(BinaryTreeNode node, int height){
            this.node = node;
            this.height = height;
        }
    }


    public static void main(String[] args) {
            SuperBalanceTree superBalanceTree = new SuperBalanceTree();

            final BinaryTreeNode root = new BinaryTreeNode(6);
            root.insertLeft(1);
            root.insertRight(0).insertRight(7);

            //superBalanceTree.testBalanceTree(root);

            ArrayList<Integer> arrayList = new ArrayList<>(3);
            arrayList.add(0, 1);
            arrayList.add(2,2);

        //System.out.println("arrlist index 0" + arrayList.get(0));
    }
}
