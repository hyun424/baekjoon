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
    public int minDepth(TreeNode root) {
        int answer = 0;
        Queue <TreeNode> q = new ArrayDeque<>();
        if (root == null) return answer;
        q.offer(root);
        while(!q.isEmpty()){
            answer++;
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode cur = q.poll();
                if (cur.left == null && cur.right == null) return answer;
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            
            
        }
        return answer;
    }
}