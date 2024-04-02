package W4.p4;

import W4.BST;
import W4.BinaryTreeNode;
// Given a binary search tree and a value X, 
// return the node containing the minimum value Y in the tree such that Y >= X.
//  If no such value exists, return null.

public class problem4 {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<Integer>();

        tree.add(8);
        tree.add(5);
        tree.add(16);
        tree.add(2);
        tree.add(7);
        tree.add(12);
        tree.add(18);

        System.out.println(findLargerEqualValue(tree, 2));
        System.out.println(findLargerEqualValue(tree, 4));
        System.out.println(findLargerEqualValue(tree, 5));
        System.out.println(findLargerEqualValue(tree, 6));
        System.out.println(findLargerEqualValue(tree, 7));
        System.out.println(findLargerEqualValue(tree, 8));
        System.out.println(findLargerEqualValue(tree, 10));
        System.out.println(findLargerEqualValue(tree, 12));
        System.out.println(findLargerEqualValue(tree, 15));
        System.out.println(findLargerEqualValue(tree, 16));
        System.out.println(findLargerEqualValue(tree, 17));
        System.out.println(findLargerEqualValue(tree, 18));
        System.out.println(findLargerEqualValue(tree, 20));



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
