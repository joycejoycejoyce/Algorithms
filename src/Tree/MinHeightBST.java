package Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinHeightBST {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        Integer[] values = {1, 2, 5, 7, 10, 13, 14, 15, 22};
        Collections.addAll(arr, values);
        BST balancedTree = minHeightBst(arr);
        System.out.println(balancedTree);
    }
    /*Method 1 Complexity:
    *   Time: O(n*logn)
    *   Space: O(n)
    * */
    public static BST minHeightBst(List<Integer> array) {
        return constructTree(array, null, 0, array.size() - 1);
    }

    public static BST constructTree( List<Integer> arr, BST tree, int startIdx, int endIdx){
        if (startIdx > endIdx){
            return null;
        }

        // midIdx
        int midIdx = (startIdx + endIdx) / 2;
        int midVal = arr.get(midIdx);

        if (tree == null){
            tree = new BST(midVal);
        }else {
            tree.insert(midVal);
        }
        constructTree(arr, tree, startIdx, midIdx - 1);
        constructTree(arr, tree, midIdx+1, endIdx);

        return tree;
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
            left = null;
            right = null;
        }

        public void insert(int value) {
            if (value < this.value) {
                if (left == null) {
                    left = new BST(value);
                } else {
                    left.insert(value);
                }
            } else {
                if (right == null) {
                    right = new BST(value);
                } else {
                    right.insert(value);
                }
            }
        }




    }
}
