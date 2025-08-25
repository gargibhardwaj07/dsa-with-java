/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }

        //diameter of leftsubtree
        int d1 = diameterOfBinaryTree(root.left);
          //diameter of rightsubtree
        int d2 = diameterOfBinaryTree(root.right);
          //diameter d3 is calculate height od right subtree, leftsubtree and then math.max these all three diameter
        int d3 = height(root.left) + height(root.right);

        int ans = Math.max(d1,d2);
        return Math.max(ans, d3);
        
    }

    public int height(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);

        return Math.max(left, right)+1;
    }
}