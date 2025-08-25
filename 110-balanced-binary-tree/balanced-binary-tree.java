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
    public boolean isBalanced(TreeNode root) {
       if(height(root)== -1){
        return false;
       }
       return true;
        
    }

    public int height(TreeNode root){
        if(root == null){
            return 0;
        }

        int leftht = height(root.left);
        //we take this case because root can be negative and acc to formula we want lh - rh >=1
        if(leftht == -1){
            return -1;
        }

         int rightht = height(root.right);
        //we take this case because root can be negative and acc to formula we want lh - rh >=1
        if(rightht == -1){
            return -1;
        }

        if(Math.abs(leftht - rightht) > 1){
            return -1;
        }

       return Math.max(leftht, rightht)+1;
    }
}