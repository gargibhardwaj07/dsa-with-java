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
     //here we take min valridable
        int maxi;
    public int maxPathSum(TreeNode root) {
        //here we take min valridable
     maxi = Integer.MIN_VALUE;
        pathSumHelper(root);
        return maxi;
        
    }

    public int pathSumHelper(TreeNode root){
        if(root == null){
            return 0;
        }
      ///we calculate in borth side sum
        int leftSum = Math.max(0, pathSumHelper(root.left));   // ignore negatives
        int rightSum = Math.max(0, pathSumHelper(root.right)); // ignore negatives

        //now check is maximum value 
        maxi = Math.max(maxi, (leftSum+rightSum+root.val));
        return Math.max(leftSum, rightSum) + root.val;
    }
}