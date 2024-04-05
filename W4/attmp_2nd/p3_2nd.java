package W4.attmp_2nd;

import W4.BST;
import W4.BinaryTreeNode;

public class p3_2nd {
    public static void main(String[] args) {
        // Given a node in a binary tree, calculate its level (assume the root level is
        // zero). Apply this result to calculate the distance between two nodes (i.e.,
        // the distance between nodes A and B is the minimum number of edges to go from
        // A to B).
        TreeWithLevel<Integer> tree = new TreeWithLevel<>();
        tree.add(10);
        tree.add(6);
        tree.add(16);
        tree.add(2);
        tree.add(9);                    
        tree.add(12);
        tree.add(18);
        tree.add(3);
        tree.add(4);

        // System.out.println("Level Tests");
        // System.out.println(tree.nodeLevel(tree.search(10))); // 0
        // System.out.println(tree.nodeLevel(tree.search(6))); // 1
        // System.out.println(tree.nodeLevel(tree.search(2))); // 2

        System.out.println(tree.distance(tree.search(3), tree.search(12))); // 0
        System.out.println(tree.distance(tree.search(3), tree.search(2))); // 0

    }

}

class TreeWithLevel<T extends Comparable<T>> extends BST<T> {
    public int nodeLevel(BinaryTreeNode<T> node) {
        if (node == this.root) {
            return 0;
        }

        return nodeLevel(node.parent) + 1;
    }

    public int distance(BinaryTreeNode<T> node1, BinaryTreeNode<T> node2) {
        int level1 = nodeLevel(node1);
        int level2 = nodeLevel(node2);

        int minLevel = Math.min(level1, level2);

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

        while (node1 != node2) {
            node1 = node1.parent;
            node2 = node2.parent;
            minLevel--;
        }

        return (level1 - minLevel) + (level2 - minLevel);
    }
}