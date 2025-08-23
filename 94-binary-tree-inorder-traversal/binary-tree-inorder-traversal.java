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
    public List<Integer> inorderTraversal(TreeNode root) {
       List<Integer> ans = new ArrayList<>();
        helperInOrder(root, ans);
        return ans;
    }

    //helper Method
    public static void helperInOrder(TreeNode root, List<Integer> ans){
        if(root == null){
            return;
        }
        helperInOrder(root.left, ans);
        ans.add(root.val);
        helperInOrder(root.right, ans);
    }
}