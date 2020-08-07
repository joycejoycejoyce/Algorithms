package Tree;

import com.sun.source.tree.Tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class RangeSumOfBST {

    public class TreeNode {
        int val;
      TreeNode left;
       TreeNode right;
      TreeNode() {}
       TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
          this.left = left;
          this.right = right;
      }
    }


    // recursion
    public int recursiveApproach(TreeNode root, int L, int R) {
        return bfs(root, L, R);
    }

    public int bfs(TreeNode node, int L, int R){
        int sum = 0;
        if (node != null){
            if (L <=node.val && node.val <=R ){
                sum += node.val;
            }
            if (L<node.val){
                sum += bfs(node.left, L, R);
            }
            if (node.val < R){
                sum += bfs(node.right, L, R);
            }
        }
        return sum;
    }

    /* Complexity:
        T: span of L and R.

        Time: O(N). In the worst case, L is the value of leftmost node. R is the value of the rightmost node
        Space: O(H). stack takes H spaces.
     */

    public int iterativeApproach(TreeNode root, int L, int R) {
        // create a stack
        Deque<TreeNode> stack = new ArrayDeque<>(); // Null value cannot be inserted to ArrayDeque;
        stack.push(root);

        int sum = 0;
        while(!stack.isEmpty()){
            TreeNode currentNode = stack.pop();

            if (L <= currentNode.val && currentNode.val <=R){
                sum += currentNode.val;
            }
            if (L < currentNode.val && currentNode.left != null){
                stack.push(currentNode.left);
            }
            if (currentNode.val < R && currentNode.right != null){
                stack.push(currentNode.right);
            }
        }

        return sum;
    }

    /* Complexity:
        Time: O(N)
        Space: O(H) stack data structure. For the worst case, R is the right most node.
                    The left side subtree of every layer is stored in the stack.
     */
}
