package W4.p2;

import W4.BinaryTreeNode;

public class problem2_2nd_attmp {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> bstTree = new BinaryTreeNode<Integer>(null, 10);
        BinaryTreeNode<Integer> leftChild = new BinaryTreeNode<Integer>(bstTree, 5);
        bstTree.left = leftChild;
        BinaryTreeNode<Integer> leftLeftChild = new BinaryTreeNode<Integer>(leftChild, 3);
        leftChild.left = leftLeftChild;
        BinaryTreeNode<Integer> rightLeftChild = new BinaryTreeNode<Integer>(leftChild, 8);
        leftChild.right = rightLeftChild;
        BinaryTreeNode<Integer> rightChild = new BinaryTreeNode<Integer>(bstTree, 16);
        bstTree.right = rightChild;
        BinaryTreeNode<Integer> leftRightChild = new BinaryTreeNode<Integer>(rightChild, 12);
        rightChild.left = leftRightChild;
        BinaryTreeNode<Integer> rightRightChild = new BinaryTreeNode<Integer>(rightChild, 20);
        rightChild.right = rightRightChild;

        // Apply in-order traversal on this tree
        System.out.println("Result 1:");
        inOrderDisplay(bstTree);

        int[] arr = new int[20];
        inOrderArray(bstTree, arr, 0);
        System.out.println();
        boolean isBST = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1] && arr[i + 1] != 0) {
                isBST = false;
            }
        }
        if (isBST) {
            System.out.println("This is a BST!");
        } else {
            System.out.println("This is NOT a BST");
        }

        System.out.println();
        System.out.println("***************************");
        bstTree.left = rightChild;
        bstTree.right = leftChild;

        System.out.println("Result 2: ");
        inOrderDisplay(bstTree);
        int[] arr2 = new int[20];
        inOrderArray(bstTree, arr2, 0);
        System.out.println();
        boolean isBST2 = true;
        for (int i = 0; i < arr2.length - 1; i++) {
            if (arr2[i] > arr2[i + 1] && arr2[i + 1] != 0) {
                isBST2 = false;
            }
        }
        if (isBST2) {
            System.out.println("This is a BST!");
        } else {
            System.out.println("This is NOT a BST");
        }

    }

    // public static boolean checkBST(BinaryTreeNode<Integer> root, int[] arr) {
    // // we take all nodes the binary tree to the array
    // // if the array is sorted --> this tree is binary tree

    // }

    public static int inOrderArray(BinaryTreeNode<Integer> node, int[] arr, int i) {
        if (node != null) {
            i = inOrderArray(node.left, arr, i);
            arr[i++] = node.data;
            i = inOrderArray(node.right, arr, i);
        }
        return i;
    }

    static void inOrderDisplay(BinaryTreeNode<Integer> node) {
        if (node != null) {
            inOrderDisplay(node.left);
            System.out.print(" " + node.data);
            inOrderDisplay(node.right);
        }
    }
}