package Tree;

import com.sun.source.tree.Tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class SymmetricTree {

    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val){
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean intuitiveApproach(TreeNode root) {
        // create two stacks to process the left subtree and the right subtree
        Deque<TreeNode> leftSubtreeStack = new ArrayDeque<>();
        Deque<TreeNode> rightSubtreeStack = new ArrayDeque<>();

        // put the root to the two stacks
        leftSubtreeStack.push(root.left);
        rightSubtreeStack.push(root.right);

        // loop through the tree together exhaust / find the unbalanced node
        while (!leftSubtreeStack.isEmpty() && !rightSubtreeStack.isEmpty()){
            // get the left value
            TreeNode leftNode = leftSubtreeStack.pop();
            int leftNodeVal = leftNode.val;

            TreeNode rightNode = rightSubtreeStack.pop();
            int rightNodeVal = rightNode.val;

            if (leftNodeVal == rightNodeVal){
                // if the left child exist and the right child exist
                if (leftNode.left == null && rightNode.right != null){
                    leftSubtreeStack.push(leftNode.left);
                    rightSubtreeStack.push(rightNode.right);
                }

                // if the right child exist and the left child exist
                if (leftNode.right != null && rightNode.left != null){
                    leftSubtreeStack.push(leftNode.right);
                    rightSubtreeStack.push(rightNode.left);
                }
            }else{
                System.out.println("not equal value");
                return false;
            }

        }

        if (leftSubtreeStack.isEmpty() && leftSubtreeStack.isEmpty()){
            return true;
        }
        return false;
    }


    public static boolean iterativeApproach(TreeNode root){
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.push(root);
        queue.push(root);

        while (!queue.isEmpty()){
            TreeNode left = queue.pop();
            TreeNode right = queue.pop();

            if (left == null && right == null){
                continue;
            }

            if (left == null || right == null){
                return false;
            }
            if (left.val != right.val){
                return false;
            }

            queue.add(left.left);
            queue.add(right.right);

            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }


    public static boolean isParentNode(int index, int length ){
        if ( index < (length-1) / 2 ){
            return true;
        }
        return false;
    };


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2,null,new TreeNode(3));
        root.right = new TreeNode(2, null, new TreeNode(3));


        intuitiveApproach(root);

    }
}
