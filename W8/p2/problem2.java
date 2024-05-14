package W8.p2;

public class problem2 {
    public static void main(String[] args) {

    }
}

class ArrayToBST {
    TreeNode build(int[] arr) {
        TreeNode root = buildTree(arr, 0, arr.length - 1);

        return root;
    }

    TreeNode buildTree(int[] arr, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (right - left) / 2;
        TreeNode parent = new TreeNode(arr[mid]);
        parent.left = buildTree(arr, left, mid - 1);
        parent.left = buildTree(arr, mid + 1, right);

        return parent;
    }
}

class TreeNode {
    int data;
    TreeNode parent;
    TreeNode left;
    TreeNode right;

    TreeNode(int data) {
        this.data = data;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

}
