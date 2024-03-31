package W4.p2;

import org.w3c.dom.Node;

import W4.BST;
import W4.BinaryTreeNode;

//Given the root of a binary tree, check if the tree is a binary search tree.
public class problem2 {
    static int[] arr = new int[20];// this use to check the tree is bst or not
    static int index = 0;// use this to specify the index of the arr when adding to array

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

        // System.out.println("First case check: ");
        // checkBST(tree.root);

        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(null, 7);

        BinaryTreeNode<Integer> L = new BinaryTreeNode<Integer>(root, 3);
        BinaryTreeNode<Integer> R = new BinaryTreeNode<Integer>(root, 11);

        root.left = L;
        root.right = R;
        


        BinaryTreeNode<Integer> LL = new BinaryTreeNode<Integer>(L, 1);
        BinaryTreeNode<Integer> LR = new BinaryTreeNode<Integer>(L, 5);
        BinaryTreeNode<Integer> LRR = new BinaryTreeNode<Integer>(LR, 2);
        BinaryTreeNode<Integer> RL = new BinaryTreeNode<Integer>(R, 9);
        BinaryTreeNode<Integer> RR = new BinaryTreeNode<Integer>(R, 13);

        System.out.println("Second case check: ");
        checkBST(root);

    }

    public static int[] inOrderTraversalCheckingBST(BinaryTreeNode<Integer> node) {
        if (node != null) {// run ultil it's null
            inOrderTraversalCheckingBST(node.left);
            arr[index] = node.data;
            index++;
            inOrderTraversalCheckingBST(node.right);
        }

        return arr;
    }

    public static boolean checkBST(BinaryTreeNode<Integer> root) {
        int[] array = inOrderTraversalCheckingBST(root);
        boolean isBST = true;
        for (int i = 0; i < 12; i++) {
            if (array[i] > array[i + 1] && array[i + 1] != 0) {
                isBST = false;
                break;
            }
        }

        if (isBST) {
            System.out.println("This is BST");
        } else {
            System.out.println("This is NOT BST!");
        }
        for(int i : array){
            System.out.println(i);
        }

        return isBST;
    }

    // public static boolean checkBST(BinaryTreeNode<Integer> travelNode,
    // BinaryTreeNode<Integer> leftNode,
    // BinaryTreeNode<Integer> rightNode) {
    // // we use this travelNode to travel through the tree

    // // The min is the left of node, the max is the right of node
    // if (travelNode == null) {
    // // if it null, it is true, --> leaf of tree (base case)
    // return true;
    // }

    // if ((leftNode != null && travelNode.data <= leftNode.data)
    // || (rightNode != null && travelNode.data >= rightNode.data)) {
    // return false;// if it go out of the range
    // }

    // // the left subtree:current node is the new max, keep the current
    // min(leftNode)
    // // the right subtree:current node is the new min, keep the current
    // max(rightNode)

    // return checkBST(travelNode.left, leftNode, travelNode)
    // && checkBST(travelNode.right, travelNode, rightNode);

    // }
}
