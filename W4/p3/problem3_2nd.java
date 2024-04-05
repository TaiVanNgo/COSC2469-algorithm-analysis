package W4.p3;

import W4.BST;
import W4.BinaryTreeNode;

class problem3_2nd_attmp {
    public static void main(String[] args) {
        // Given a node in a binary tree, calculate its level (assume the root level is
        // zero).
        // Apply this result to calculate the distance between two nodes
        // (i.e., the distance between nodes A and B is the minimum number of edges to
        // go from A to B).

        TreeWithLevel<Integer> tree = new TreeWithLevel<Integer>();

        tree.add(10);
        tree.add(6);
        tree.add(16);
        tree.add(2);
        tree.add(9);
        tree.add(12);
        tree.add(18);
    /*
     * Create this tree to test
     *           10
     *        /      \
     *      6          16
     *    /   \      /    \
     *   2     9    12     18
     */
        System.out.println(tree.nodeLevel(tree.search(9))); // return 2

        System.out.println("The distance from 2 to 10: ");
        System.out.println(tree.distantNode(tree.search(2), tree.search(10)));
        System.out.println("The distance from 2 to 2: ");
        System.out.println(tree.distantNode(tree.search(2), tree.search(2)));
        System.out.println("The distance from 9 to 18: ");
        System.out.println(tree.distantNode(tree.search(9), tree.search(18)));

    }
}

class TreeWithLevel<T extends Comparable<T>> extends BST<T> {
    // extend BST:
    // public BinaryTreeNode<T> root;
    // protected int size;

    public int nodeLevel(BinaryTreeNode<T> node) {
        // return the level of the node
        if (node == this.root) {
            return 0;
        }

        return nodeLevel(node.parent) + 1;
        // go up to the parent
    }

    public int distantNode(BinaryTreeNode<T> node1, BinaryTreeNode<T> node2) {
        // first we determine the level of each node
        int level1 = nodeLevel(node1);
        int level2 = nodeLevel(node2);
        // then we find the minimum level of the 2 node
        int minLevel = Math.min(level1, level2);

        // with the min, we use each node to go up until, the level is == the min
        int tmp = level1;
        while (minLevel < tmp) {
            node1 = node1.parent;
            tmp--;
        }

        tmp = level2;
        while (minLevel < tmp) {
            node2 = node2.parent;
            tmp--;
        }

        // after this step, it go to the same position, or at the node that has the same
        // parent
        while (node1 != node2) {
            node1 = node1.parent;
            node2 = node2.parent;

            minLevel--;
        }

        return (level1 - minLevel) + (level2 - minLevel);
    }
}