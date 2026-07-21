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
import java.util.*;

class Solution {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root.left);
        q.offer(root.right);

        while (!q.isEmpty()) {
            TreeNode left = q.poll();
            TreeNode right = q.poll();

            // 둘 다 null이면 현재 위치는 대칭
            if (left == null && right == null) {
                continue;
            }

            // 한쪽만 null이면 비대칭
            if (left == null || right == null) {
                return false;
            }

            // 값이 다르면 비대칭
            if (left.val != right.val) {
                return false;
            }

            // 거울 방향끼리 비교하도록 삽입
            q.offer(left.left);
            q.offer(right.right);

            q.offer(left.right);
            q.offer(right.left);
        }

        return true;
    }
}