package W3.p2;

public class problem2_2nd {
    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Node))
                return false;
            return data == ((Node) other).data;
        }
    }

    static class CircularList {
        Node head, tail;// we need head and tail in the list
        int size;// define the size

        public CircularList() {
            head = tail = null;
            size = 0;
        }

        public int getSize() {
            return this.size;
        }

        public boolean insertNode(int data) {
            Node newNode = new Node(data);
            if (size == 0) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
            }

            newNode.next = head;
            tail = newNode;
            size++;
            return true;
        }

        public void removeNode(int index) {
            // 1->2->3->4->1
            index--;// since we use the beginning is 1. we minus 1 to essy, when we
            // use the index of 3 it go to the node has the data of 3
            Node pointer = head;
            if (index == 0) {
                // the tail point to the next of the head
                tail.next = head.next;
                head = head.next; // update the new head

                size--;// update new size
                return;
            }

            while (index != 1) {
                pointer = pointer.next;
                index--;
            }

            if (pointer.next == tail) {
                // if the next of pointer is the tail?
                // we currently want to remove tail
                tail = pointer;// update the new tail(the previous of the tail that we want to remove)
                pointer.next = head;// update the next of the new tail

                size--;// update new size
                return;
            }

            // after the wihle loop, the pointer go to the position of the previous data
            // that we wnat to remove
            // if we want to remove 3, after the loop we currently the prev of 3 -> it is 2.
            pointer.next = pointer.next.next;
            size--;// update new size

        }

        public void displayNode() {
            int cnt = 0;
            Node pointer = head;
            while (cnt != 20) {
                System.out.print(pointer.data + " -> ");
                pointer = pointer.next;
                cnt++;
            }
        }

    }

    public static void main(String[] args) {
        CircularList circularList = new CircularList();

        circularList.insertNode(1);
        circularList.insertNode(2);
        circularList.insertNode(3);
        circularList.insertNode(4);
        circularList.insertNode(5);
        circularList.insertNode(6);

        circularList.displayNode();

        circularList.removeNode(6);
        System.out.println();
        circularList.displayNode();
    }

}
