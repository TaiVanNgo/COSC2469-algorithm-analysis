package W4.p1;

import W4.BST;

//Implement the pre-order, post-order, in-order, and breadth-first traversals for a binary search tree.
public class problem1 {
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

        System.out.println("Display in order:");
        tree.displayInOrder();
        System.out.println();
        System.out.println("Display pre-order: ");
        tree.displayPreOrder();
        System.out.println();
        System.out.println("Dipslay post-order: ");
        tree.displayPostOrder();
        System.out.println();
        System.out.println("Display Breath-first travel: ");
        tree.displayBreadthFirst();
    }
}
