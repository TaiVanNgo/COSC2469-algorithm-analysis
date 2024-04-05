package W3.p1;

public class problem1_2nd {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {

        // 1 -> 2 -> 3 -> 4 -> 5 -> 2 -> 3 ->...
        Node n5 = new Node(5);
        Node n4 = new Node(4, n5);
        Node n3 = new Node(3, n4);
        Node n2 = new Node(2, n3);
        Node n1 = new Node(1, n2);
        n5.next = n2; // create the loop

        System.out.println("The loop linked list:");
        int cnt = 0;
        Node pointer = n1;
        while (cnt != 10 && pointer != null) {
            System.out.print(pointer.data + " -> ");
            pointer = pointer.next;
            cnt++;
        }

        // FIrst we need to detect whether the linked list is in the loop or not
        Node fast = n1, slow = n1;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast.data == slow.data) {
                System.out.println();
                System.out.println("Detect the loop, fast catches slow at position: " + fast.data);
                break;
            }
        }

        // after catches each other, we measure the length of the loop
        int loopLength = 0;
        do {
            fast = fast.next;
            loopLength++;
        } while (fast.data != slow.data);
        System.out.println("The length of the loop is: " + loopLength);

        // after got the position of the length, we set the slow to the head, the fast
        // is far
        // away loopLength step
        fast = slow = n1;

        while (loopLength != 0) {
            fast = fast.next;
            loopLength--;
        }

        System.out.println("Fast's position: " + fast.data);

        while (fast.next != slow.next) {
            fast = fast.next;
            slow = slow.next;
        }

        // after this while loop, the fast and slow at the position that
        // has the same next --> fast is the tail
        // we cut the fast.next;
        fast.next = null;

        System.out.println("Linked list after cutting the loop: ");
        pointer = n1;
        while (pointer != null) {
            System.out.print(pointer.data + " -> ");
            pointer = pointer.next;
        }
    }

}
