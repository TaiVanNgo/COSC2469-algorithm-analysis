public class TaiBSTWithSize {
    BinaryTreeNode root;
    int size;

    TaiBSTWithSize() {
        this.root = null;
        this.size = 0;
    }

    // complexity: The time complexity is based on the depth of the input node
    // O(N) --> N is the length of the input node, not the whole tree
    int subTreeSize(BinaryTreeNode node) {
        // return size based on the inOrderTraversal method
        return inOrderTraversal(node, 0);
    }

    int newSubTreeSize(BinaryTreeNode node) {
        // return size based on the inOrderTraversal method
        return node.subtreeNodes;
    }

    BinaryTreeNode search(int value) {
        BinaryTreeNode node = this.root;
        while (node != null) {
            if (value == node.data) {
                return node;
            }
            if (value > node.data) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    void updateSubtreeNode() {
        BinaryTreeNode tempNode = root;
        // go left
        while (tempNode != null) {
            tempNode.subtreeNodes++;
            tempNode = tempNode.left;
        }

        // go right
        while (tempNode != null) {
            tempNode.subtreeNodes++;
            tempNode = tempNode.right;
        }
    }

    BinaryTreeNode add(int value) {
        if (this.root == null) {// if root is null
            this.root = new BinaryTreeNode(null, value);
            size++;
            updateSubtreeNode();
            return root;
        }

        // normal case
        BinaryTreeNode tempNode = this.root;// use this node to go through the tree
        while (tempNode != null) {
            if (value < tempNode.data) {
                // go left
                if (tempNode.left == null) {
                    BinaryTreeNode newNode = new BinaryTreeNode(tempNode, value);
                    tempNode.left = newNode;
                    size++;
                    updateSubtreeNode();

                    return newNode;
                }
                tempNode = tempNode.left;
            } else {
                // go right
                if (tempNode.right == null) {
                    BinaryTreeNode newNode = new BinaryTreeNode(tempNode, value);
                    tempNode.right = newNode;
                    size++;
                    updateSubtreeNode();

                    return newNode;
                }
                tempNode = tempNode.right;
            }
        }

        return null;
    }

    int inOrderTraversal(BinaryTreeNode node, int size) {
        if (node != null) {
            size = inOrderTraversal(node.left, size);
            // System.out.print(node.data + " ");
            size++;
            size = inOrderTraversal(node.right, size);
        }
        return size;
    }

    public static void main(String[] args) {
        TaiBSTWithSize tree = new TaiBSTWithSize();

        tree.add(15);
        tree.add(10);
        tree.add(20);
        tree.add(8);
        tree.add(12);
        tree.add(16);
        tree.add(25);

        System.out.println("OLD METHOD");
        System.out.println(tree.subTreeSize(tree.search(15)));
        System.out.println(tree.subTreeSize(tree.search(10)));

        System.out.println("NEW METHOD");
        System.out.println(tree.newSubTreeSize(tree.search(15)));

        System.out.println();

    }

}

class BinaryTreeNode {
    int data;
    int subtreeNodes;// store the height of subtree node

    BinaryTreeNode parent = null;
    BinaryTreeNode left = null;
    BinaryTreeNode right = null;

    BinaryTreeNode(BinaryTreeNode parent, int data) {
        this.parent = parent;
        this.data = data;
        subtreeNodes = 0;
    }
}