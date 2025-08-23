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
    public List<List<Integer>> levelOrder(TreeNode root) {
        //in below we take allar list to store all level values like([[1][2,3]])
        List<List<Integer>> result = new ArrayList<>();

        //if root null h to return kr denge abhi tk jo h ans basically empty list[]
        if(root == null){
            return result;

        }

        //now take queue for bfs 
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root); // in 1st we add root in queue now 

        while(!q.isEmpty()){
            //we take size of queue for next level and traverse only on that
            int size = q.size();
            List<Integer> level = new ArrayList<>(); // for add level wise value

            for(int i =0;i<size;i++){
            TreeNode currvalue = q.remove();
            level.add(currvalue.val);

            if(currvalue.left != null){
                q.add(currvalue.left);
            }

             if(currvalue.right != null){
                q.add(currvalue.right);
            }
          
        }
          result.add(level);
        }
        return result;
        
    }

  
}