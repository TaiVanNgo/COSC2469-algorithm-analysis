package W8.p2;

public class ConvertSortedArrToTree {
    public static void main(String[] args) {
        ArrayBinaryTree tree = new ArrayBinaryTree();
        int[] sortedArr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        tree.arrayToBinaryTree(sortedArr);
        System.out.println(tree.root.balanced);
    }
}

class ArrayBinaryTree {
    TreeNode2 root;
    int size;

    ArrayBinaryTree() {
        this.root = null;
        this.size = 0;
    }

    public TreeNode2 arrayToBinaryTree(int[] arr) {
        this.root = arrayToBinaryTree(arr, 0, arr.length - 1);
        return this.root;
    }

    private TreeNode2 arrayToBinaryTree(int[] arr, int left, int right) {
        // since the arary is sorted, we don't need to sort
        if (left > right) {
            return null;
        }

        // calculate the middle of the aray
        int mid = (left + right) / 2;
        // create the root
        TreeNode2 parent = new TreeNode2(arr[mid]);

        // set the left and right for the array
        parent.left = arrayToBinaryTree(arr, left, mid - 1);
        parent.right = arrayToBinaryTree(arr, mid + 1, right);

        return parent;
    }

}

class TreeNode2 {
    int data;
    TreeNode2 left;
    TreeNode2 right;
    boolean balanced = true;

    TreeNode2(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public int height() {
        this.balanced = true;
        return heightRecursive(this);
    }

    public boolean isBalanced() {
        height(); // just in case this method has not been called
        return this.balanced;
    }

    private int heightRecursive(TreeNode2 node) {
        if (node == null) {
            return 0;
        }
        int hl = heightRecursive(node.left);
        int hr = heightRecursive(node.right);
        if (Math.abs(hl - hr) > 1) {
            this.balanced = false;
        } else {
            // for a tree to be balanced, the sub-trees must be balanced, too
            this.balanced = node.left.balanced && node.right.balanced;
        }
        return 1 + Math.max(hl, hr);
    }

    // in-order traversal
    public void inOrder() {
        System.out.println("In-order traversal");
        inOrderRecursive(this);
    }

    private void inOrderRecursive(TreeNode2 node) {
        if (node == null) {
            return;
        }
        // traverse left-subtree
        inOrderRecursive(node.left);

        // process root
        System.out.println(node.data);

        // traverse right-subtree
        inOrderRecursive(node.right);
    }
}
