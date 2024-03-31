package W4;

import W3.ArrayQueue;

public class BST<T extends Comparable<T>> {
    public BinaryTreeNode<T> root;
    protected int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return this.size;
    }

    // add new element in node
    public BinaryTreeNode<T> add(T value) {
        // if doens't have root yet
        if (this.root == null) {
            this.root = new BinaryTreeNode<T>(root, value);
            size++;
            return root;
        }

        // another case,
        // create the node that go from the root
        BinaryTreeNode<T> tempNode = this.root;
        while (tempNode != null) {
            // run until tempNOde = null;
            if (value.compareTo(tempNode.data) < 0) {
                // If the value we want to add smaller than the value of the temp node. we go to
                // left
                if (tempNode.left == null) {
                    // if the left node of tempNode is null, we add to it
                    // create the new node and add to the left of the tempNode
                    BinaryTreeNode<T> newNode = new BinaryTreeNode<T>(tempNode, value);
                    // temp node is the parent of the newNOde, since we want to add under the temp
                    // node
                    tempNode.left = newNode;
                    size++;// increase the size
                    return newNode;
                }

                // if the left of tempNode is not null, we move to left
                tempNode = tempNode.left;
            } else if (value.compareTo(tempNode.data) > 0) {
                // this case we go to right
                if (tempNode.right == null) {
                    // if the right of tmep node is null, we add to it
                    BinaryTreeNode<T> newNode = new BinaryTreeNode<T>(tempNode, value);

                    tempNode.right = newNode;
                    size++;
                    return newNode;
                }

                // we move to the right
                tempNode = tempNode.right;
            } else {
                return null;// If the value we want to add is equal the temp node --> duplicated node.
                // retun null
            }

        }

        return null;
    }

    private void inOrderTraversal(BinaryTreeNode<T> node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.printf(" %s ", node.data.toString());
            inOrderTraversal(node.right);
        }
    }

    public void displayInOrder() {
        inOrderTraversal(root);
    }

    private void preOrderTraversal(BinaryTreeNode<T> node) {
        if (node != null) {
            System.out.printf(" %s ", node.data.toString());
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    public void displayPreOrder() {
        preOrderTraversal(root);
    }

    private void postOrderTraversal(BinaryTreeNode<T> node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.printf(" %s ", node.data.toString());
        }
    }

    public void displayPostOrder() {
        postOrderTraversal(root);
    }

    private void breadthFirstTraversal(BinaryTreeNode<T> node) {
        ;
        ArrayQueue<BinaryTreeNode<T>> queue = new ArrayQueue<>();
        // first we take the root to the queue first
        if (node != null) {// imagine that the parameter "node" is the root
            queue.enQueue(root);
        }

        // run until the queue is empty
        while (!queue.isEmpty()) {
            // print the current node. then go to left and right
            BinaryTreeNode<T> travelNode = queue.peekFront();
            System.out.printf(" %s ", travelNode.data.toString());
            queue.deQueue();

            if (travelNode.left != null) {
                queue.enQueue(travelNode.left);
            }
            if (travelNode.right != null) {
                queue.enQueue(travelNode.right);
            }
        }
    }

    public void displayBreadthFirst() {
        breadthFirstTraversal(this.root);
    }

    

}