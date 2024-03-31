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

        public boolean removeNode(Node remove_node) {
            if (size == 0) {
                return false;
            }

            Node temp = head;// use this to move
            while (temp.next != remove_node) {
                temp = temp.next;
            }
            // at this time, we at the previous position of the Node that we want to remove
            // ex: we want remove 3. we are currently at 2
            if (remove_node == head) {
                head = remove_node.next;
                tail.next = head;
            } else if (remove_node == tail) {
                tail = temp;
                temp.next = head;
            } else {
                temp.next = temp.next.next;
            }

            size--;
            return true;
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
        int n = 41;
        int m = 3;

        for (int i = 1; i <= n; i++) {
            circularList.insertNode(i);
        }

        System.out.println("Current circular list:");
        circularList.displayNode();
        System.out.println();

        // for example we have n = 10, m = 3, the 1st person kill 3rd person
        Node pointer = circularList.head;
        while (circularList.size != 1) {
            for (int i = 0; i < m - 1; i++) {
                pointer = pointer.next;
            }
            // after the loop, we are currently at the node that we want to remove
            System.out.println("Kill at: " + pointer.data);
            circularList.removeNode(pointer);
            pointer = pointer.next;// after kill the current position, move to the next
        }

        System.out.println("Survivor: " + circularList.head.data);

        // System.out.println(pointer.data);

    }

}
