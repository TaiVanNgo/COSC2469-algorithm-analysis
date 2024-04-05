package W3.p2;

import W3.LinkedList;
import W3.LinkedList.Node;

//Implement the Circular Linked List data structure and use it to solve the Josephus problem
public class problem2 {
    public static void main(String[] args) {
        LinkedList<Integer> circularList = new LinkedList<>();

        int people = 41; // number of people
        int m = 1; // number of interval

        for (int i = 0; i < people; i++) {
            circularList.insertAt(i, i + 1);
        }

        circularList.createLoop(0, 6);

        System.out.println("Circular Linked List:");
        circularList.showListUpToTwenty();
        System.out.println();

        Node<Integer> currentNode = circularList.head;
        Node<Integer> previousNode = null;

        int count = 0;

        while (circularList.size != 1) {// Run util the next of the current is current
            for (int i = 0; i < m; i++) {
                // go to the interval to kill node
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
            // For ex: we have the list of 1->2->3->4 -> 1...
            // m = 2 ==> so we need to kill 3
            // after the for loop, the curent at the number 3.

            System.out.println("Kill at: " + currentNode.data);
            int index = circularList.getIndexBasedOnData(currentNode.data); // get the index based on the current node
                                                                            // data
            circularList.removeAt(index);
            currentNode = currentNode.next;// go to the next step,
            // Because for ex: 1->2->3->4->5->6, we kill 3, 3 must be disappear,
            // if we do not move one node to 4, it start to count form 3.
            // the next is 5 --> it would be wrong.

            // with the example, after this step, the linked list will be 1->2->4 ->...
        }

        System.out.println("Survivor: " + currentNode.data);
    }
}
