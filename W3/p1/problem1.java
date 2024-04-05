package W3.p1;

import W3.LinkedList;
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

        linkedList.createLoop(3, 100);
        linkedList.showListUpToTwenty();

        // we have the loop linkedlist, we need to remove it

        // create the fast and slow to detect the loop first
        Node<Integer> fast = linkedList.head;
        Node<Integer> slow = linkedList.head;
        while (fast.next != null && fast.next.next != null) {
            // fast go 2 steps, slow go 1 step
            // If the fast go to null --> end the loopp --> there are no loop inside the
            // linkedlist, in constrast, if the fast and slow catches each other --> there
            // will be
            // a loop inside the linkedlist
            fast = fast.next.next;
            slow = slow.next;
            if (fast.data == slow.data) {
                System.out.println();
                System.out.println("Detect the loop!");
                break;
            }
        }
        // when we detect the loop successfully
        // we count the number of the step inside the loop?
        // at this time slow and fast is in the same point

        int loopCount = 0;
        do {
            fast = fast.next;
            loopCount++;
        } while (fast.data != slow.data);
        System.out.println("Loop count: " + loopCount);

        // set the slow to head again
        slow = fast = linkedList.head;

        // set the position of the fast be "loop count" away from slow
        while (loopCount != 0) {
            fast = fast.next;
            loopCount--;
        }

        System.out.println("Fast's data after adjust the position away from slow: " + fast.data);
        while (true) {
            // run until the next of fast and next of slow are identical
            if (fast.next == slow.next) {
                fast.next = null;
                break;
            }
            fast = fast.next;
            slow = slow.next;
        }

        // if the code reach this step, we cut the loop of the linkedList sucessfully!
        System.out.println("Linked List after cut the loop!");
        linkedList.showList();

    }
}
