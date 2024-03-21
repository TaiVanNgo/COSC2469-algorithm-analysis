package W3.p1;

import W3.LinkedList;
import W3.List;
// Implement an algorithm to remove a loop from a Singly Linked List.
import W3.LinkedList.Node;

public class problem1 {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.insertAt(0, 1);
        linkedList.insertAt(1, 2);
        linkedList.insertAt(2, 3);
        linkedList.insertAt(3, 4);
        linkedList.insertAt(4, 5);
        linkedList.insertAt(5, 6);

        linkedList.showList();

        linkedList.createLoop(1, 100);
        linkedList.showList();


    }
}
