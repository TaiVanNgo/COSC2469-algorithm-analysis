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

    public BinaryTreeNode<T> search(T data) {
        BinaryTreeNode<T> node = this.root;

        while (node != null) {
            if (data.compareTo(node.data) == 0) {
                // if the data is match we return the node
                return node;
            }

            if (data.compareTo(node.data) < 0) {
                // if the data is smaller that the node's data
                // go to left
                node = node.left;
            } else if (data.compareTo(node.data) > 0) {
                node = node.right;
            }

        }
        return node;
    }

    public BinaryTreeNode<T> remove(T value) {
        // Empty tree
        if (size == 0) {
            return null;
        }

        // We need to go to the position of the node that we want
        // to remove
        BinaryTreeNode<T> node = this.root;
        while (node != null) {
            if (value.compareTo(node.data) == 0) {
                // if the 2 values is matched, we break the loop
                break;
            }

            if (value.compareTo(node.data) > 0) {
                // if the value > node data
                node = node.right;
            } else if (value.compareTo(node.data) < 0) {
                // if the value < node data
                node = node.left;
            }
        }

        // we get out of the loop, we are currently at the node that we want to remove
        if (node == null)
            return null;
        // if the node is null after the loop --> we can not find the matched value
        // in the tree.

        // If the node we remove has no children
        if (node.left == null && node.right == null) {
            // we must identify the removed node is in the right or left of its parent.
            if (node == root) {
                root = null;
                size = 0;
                return null;
            }

            if (node == node.parent.left) {
                node.parent.left = null;
            } else if (node == node.parent.right) {
                node.parent.right = null;
            }
            size--;
            return node.parent;
        }

        // If the node has one children
        if ((node.left != null && node.right == null)
                || (node.left == null && node.right != null)) {

            // we just need to use it children to replace itself
            // first we find the the correct child
            BinaryTreeNode<T> correctChild;
            if (node.left != null) {
                correctChild = node.left;
            } else {// the correct child is in left or right of the node we want to remove
                correctChild = node.right;
            }

            // we identify the removed node
            if (node.parent.left == node) {
                node.parent.left = correctChild;
                correctChild.parent = node.parent;
            } else if (node.parent.right == node) {
                node.parent.right = correctChild;
                correctChild.parent = node.parent;
            }
            size--;
            return node.parent;
        }

        // step 2c: node to be removed has two childrens
        // we get the left-most on the right tree
        // orget the right-most on the right;

        BinaryTreeNode<T> replacedNode = node.right;// we go to the right, then go full to the left
        while (replacedNode.left != null) {
            replacedNode = replacedNode.left;
        }

        // exhange the value
        T tmp = replacedNode.data;
        replacedNode.data = node.data;
        node.data = tmp;
        // now, remove replaceNode, which is similar to step 2A and step 2B
        // it is better if you create seperate methods for those operations
        // for simplicity, I just put everything in the same place

        return null;
    }
}