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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int flag = 0; // 0 = left to right, 1 = right to left

        while (!q.isEmpty()) {
            int size = q.size(); // number of nodes in this level
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                // if flag = 0 → L to R
                if (flag == 0) {
                    level.add(node.val);
                } 
                // if flag = 1 → R to L
                else {
                    level.add(0, node.val); // insert at front
                }

                // add children to queue
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }

            result.add(level);
            // flip flag (0 → 1, 1 → 0)
            flag = 1 - flag;
        }

        return result; 
    }
}