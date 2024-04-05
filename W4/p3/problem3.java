package W4.p3;
// Given a node in a binary tree, calculate its level (assume the root level is zero).

//  Apply this result to calculate the distance between two nodes
// (i.e., the distance between nodes A and B is the minimum number of edges to go from A to B).

import W4.BST;
import W4.BinaryTreeNode;

public class problem3 {
    public static void main(String[] args) {
        // First: find the common parent of two nodes
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(null, 7);

        // LEFT OF THE ROOT
        BinaryTreeNode<Integer> node3 = new BinaryTreeNode<Integer>(root, 3);
        root.left = node3;

        BinaryTreeNode<Integer> node1 = new BinaryTreeNode<Integer>(node3, 1);
        BinaryTreeNode<Integer> node5 = new BinaryTreeNode<Integer>(node3, 5);
        node3.left = node1;
        node3.right = node5;

        BinaryTreeNode<Integer> node4 = new BinaryTreeNode<Integer>(node5, 4);
        BinaryTreeNode<Integer> node6 = new BinaryTreeNode<Integer>(node5, 6);
        node5.left = node4;
        node5.right = node6;

        // RIGHT OF THE ROOT
        BinaryTreeNode<Integer> node11 = new BinaryTreeNode<Integer>(root, 11);
        root.right = node11;

        BinaryTreeNode<Integer> node9 = new BinaryTreeNode<Integer>(node11, 9);
        BinaryTreeNode<Integer> node13 = new BinaryTreeNode<Integer>(node11, 13);
        node11.left = node9;
        node11.right = node13;

        BinaryTreeNode<Integer> node8 = new BinaryTreeNode<Integer>(node9, 8);
        node9.left = node8;

        BinaryTreeNode<Integer> node12 = new BinaryTreeNode<Integer>(node13, 12);
        BinaryTreeNode<Integer> node14 = new BinaryTreeNode<Integer>(node13, 14);
        node13.left = node12;
        node13.right = node14;



        findCommonParent(node1, node6);
    }

    public static void findCommonParent(BinaryTreeNode<Integer> node1, BinaryTreeNode<Integer> node2) {
        BinaryTreeNode<Integer> node1travel = node1;
        BinaryTreeNode<Integer> node2travel = node2;
        while (node1travel.parent != node2travel.parent) {
            if (node1travel != null) {
                node1travel = node1travel.parent;
            }
            if (node2travel != null) {
                node2travel = node1travel.parent;
            }

        }

        System.out.println(node1travel.parent.data);
        System.out.println(node2travel.parent.data);
    }
}
