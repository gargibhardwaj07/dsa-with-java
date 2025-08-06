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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans =new ArrayList<>();
        helperPreOrder(root, ans);
        return ans;

       
    }

    ///helper 
    public static void helperPreOrder(TreeNode root, List<Integer> ans){
      if(root == null){
            return ;
        }
       ans.add(root.val);
      helperPreOrder(root.left, ans);
       helperPreOrder(root.right, ans);
    }
     
    
}