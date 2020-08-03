package Tree;

import com.sun.source.tree.Tree;

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


    int ans;
    public int recursiveApproach(TreeNode root, int L, int R){
        ans = 0;
        dfs(root, L, R);
        return ans;
    }

    public void dfs(TreeNode node, int L, int R){
        if (node != null){
            if (L <= node.val && node.val <=R){
                ans+=node.val;
            }
            if (L < node.val){
                dfs(node.left, L, R);
            }
            if (node.val < R){
                dfs(node.right, L, R);
            }
        }
    }

    public int iterativeApproach(TreeNode root, int L, int R){
        int ans = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            if (node != null){
                if (L <= node.val && node.val < R){
                    ans += node.val;
                }
                if (L < node.val){
                    stack.push(node.left);
                }
                if (node.val < R){
                    stack.push(node.right);
                }
            }
        }
        return ans;
    }
}
