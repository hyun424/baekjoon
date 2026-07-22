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

    static class State {
        TreeNode node;
        int sum;

        State(TreeNode node, int sum) {
            this.node = node;
            this.sum = sum;
        }
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        Queue<State> q = new ArrayDeque<>();
        q.offer(new State(root, root.val));

        while (!q.isEmpty()) {
            State state = q.poll();

            TreeNode cur = state.node;
            int sum = state.sum;

            // 리프 노드에 도착했을 때만 합을 검사
            if (cur.left == null && cur.right == null && sum == targetSum) {
                return true;
            }

            if (cur.left != null) {
                q.offer(new State(cur.left, sum + cur.left.val));
            }

            if (cur.right != null) {
                q.offer(new State(cur.right, sum + cur.right.val));
            }
        }

        return false;
    }
}