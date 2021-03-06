package question0572_subtree_of_another_tree;

/**
 * 递归实现。
 *
 * 时间复杂度是O(mn)，其中m为s中的节点个数，n为t中的节点个数。
 *
 * 执行用时：17ms，击败43.43%。消耗内存：42.1MB，击败75.68%。
 */
public class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (null == s && null != t) {   //虽然题目规定了输入的两棵二叉树均为非空二叉树，但在递归调用的过程中可能会出现s为null的情况
            return false;
        }
        return isSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    /**
     * 判断两棵二叉树是否完全相同。
     */
    public boolean isSame(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        return node1 != null && node2 != null && node1.val == node2.val && isSame(node1.left, node2.left)
                && isSame(node1.right, node2.right);
    }
}