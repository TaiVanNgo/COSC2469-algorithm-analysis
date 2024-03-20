package W3.p1;

import W3.LinkedList;
import W3.List;
// Implement an algorithm to remove a loop from a Singly Linked List.

public class problem1 {
    public static void main(String[] args) {
        List<Integer> linkedList_notInLoop = new LinkedList<>();

        linkedList_notInLoop.insertAt(0, 3);
        linkedList_notInLoop.insertAt(1, 5);
        linkedList_notInLoop.insertAt(2, 7);
        linkedList_notInLoop.insertAt(3, 15);
        linkedList_notInLoop.insertAt(4, 20);

    }
}
