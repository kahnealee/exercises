package lc;

import java.util.*;

/**
 * Created by kpan on 9/13/16.
 */
public class L236LowestCommonAncestorofaBinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<TreeNode, TreeNode>();
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        parent.put(root, null);
        stack.push(root);

        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }
        Set<TreeNode> ancestors = new HashSet<TreeNode>();
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        while (!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }








    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p== null || q==null) {
            return null;
        }
        if (root == p && containsNode(root, q)) {
            return root;
        }
        if (root == q && containsNode(root, p)){
            return root;
        }
        return findLowestCommonAncester(root, p, q);
    }

    public TreeNode findLowestCommonAncester(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }
        if (root == null) {
            return null;
        }
        TreeNode left = findLowestCommonAncester(root.left, p, q);
        TreeNode right = findLowestCommonAncester(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        else if (left == null && right != null) {
            return right;
        }
        else if (left != null && right == null) {
            return left;
        }
        else if (left == null && right == null) {
            return null;
        } else {
            return null;
        }
    }

    public boolean containsNode(TreeNode root, TreeNode p) {
        if (root == null) {
            return false;
        }
        if (root == p) {
            return true;
        }
        return containsNode(root.left, p) || containsNode(root.right, p);
    }
}
