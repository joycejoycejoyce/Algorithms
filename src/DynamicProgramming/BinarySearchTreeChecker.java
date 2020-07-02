package DynamicProgramming;

public class BinarySearchTreeChecker {

    private class BinaryTreeNode {

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

    public boolean checkTree(BinaryTreeNode treeRoot){
        /* Summary:
            The way we need to check a tree is to check if the value is
            inside a specific range.

            As long as we move to diff nodes, the range for the next node
            will be more narrowed

         */

        return false;
    }

    public static void main(String[] args) {

    }
}
