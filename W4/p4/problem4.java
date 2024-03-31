package W4.p4;

import W4.BST;
import W4.BinaryTreeNode;
// Given a binary search tree and a value X, 
// return the node containing the minimum value Y in the tree such that Y >= X.
//  If no such value exists, return null.

public class problem4 {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<Integer>();

        tree.add(7);
        tree.add(3);
        tree.add(1);
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.add(11);
        tree.add(9);
        tree.add(8);
        tree.add(13);
        tree.add(12);
        tree.add(14);

        System.out.println(findLargerEqualValue(tree, 2));
        System.out.println(findLargerEqualValue(tree, 7));
        System.out.println(findLargerEqualValue(tree, 10));
        System.out.println(findLargerEqualValue(tree, 15));

    }

    public static Integer findLargerEqualValue(BST<Integer> tree, int value) {
        BinaryTreeNode<Integer> node = tree.root;// use this node to travel through
        Integer result = null;
        // the tree

        while (node != null) {
            if (node.data == value)
                return node.data;

            if (value < node.data) {
                node = node.left;// go to left if value < current node
                if (node != null && node.data > value) {
                    result = node.data;
                    // if the node in the tree larger than the value
                    // we update the result
                }
            } else if (value > node.data) {
                node = node.right;// go to right if the value > current node

                if (node != null && node.data > value) {
                    result = node.data;
                }
            }
        }

        return result;
    }
}
