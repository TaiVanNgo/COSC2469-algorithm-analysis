package W8.p2;

import W4.BST;
import W4.BinaryTreeNode;

public class problem2 {

    public BinaryTreeNode<Integer> buildTree(int[] arr, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        BinaryTreeNode<Integer> parent = new BinaryTreeNode<Integer>(null, arr[mid]);
        parent.left = buildTree(arr, left, mid - 1);
        parent.right = buildTree(arr, mid + 1, right);
        return parent;
    }

    public TreeNode buildTree(DataNode begin, DataNode end) {
        if (begin == end) {
            return null;
        }

        DataNode slow = begin;
        DataNode fast = begin;

        // when fast reaches the end, slow is in the middle
        while (fast != end && fast.next != end) {
            fast = fast.next.next;
            slow = slow.next;
        }

        TreeNode parent = new TreeNode(slow.data);
        parent.left = buildTree(begin, slow);
        parent.right = buildTree(slow.next, end);
        return parent;
    }

}
