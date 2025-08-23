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
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        //By Recusrion we first count leftsubtree nodes then rifght then add 1 1 is for root
        int leftSubTree = countNodes(root.left);
        int rightSubTree = countNodes(root.right);

        return leftSubTree+rightSubTree+1;
    }
}