package W3;

import org.w3c.dom.Node;

public class LinkedList<T> implements List<T> {
    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private int size;
    private Node<T> pointer;
    private Node<T> head;

    public LinkedList() {
        size = 0;
        head = null;
        pointer = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void reset() {
        pointer = head;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node<T> p = head;
        while (index > 0) {
            p = p.next;
            index--;
        }
        return p.data;
    }

    @Override
    public boolean hasNext() {
        return (pointer != null);
    }

    @Override
    public T next() {
        T data = pointer.data;
        pointer = pointer.next;
        return data;
    }

    @Override
    public boolean contains(T value) {
        Node<T> p = head;
        while (p != null) {
            if (p.data.equals(value)) {
                return true;
            }
            p = p.next;
        }
        return false;
    }

    private boolean insertAtHead(T value) {
        Node<T> n = new Node<T>(value);
        n.next = head;
        head = n;
        size++;
        return true;
    }

    private boolean removeAtHead() {
        if (head == null) {
            return false;
        }
        head = head.next;
        size--;
        return true;
    }

    @Override
    public boolean insertAt(int index, T value) {
        if (index > size) {
            return false;
        }
        if (index == 0) {
            return insertAtHead(value);
        }
        Node<T> current = head;
        Node<T> previous = null;
        while (index > 0) {
            previous = current;
            current = current.next;
            index--;
        }
        Node<T> node = new Node<T>(value);
        node.next = current;
        previous.next = node;
        size++;
        return true;
    }

    @Override
    public boolean insertBefore(T searchValue, T value) {
        if (head == null) {
            return false;
        }
        if (head.data.equals(searchValue)) {
            return insertAtHead(value);
        }
        Node<T> current = head;
        Node<T> previous = null;
        while (current != null) {
            if (current.data.equals(searchValue)) {
                Node<T> node = new Node<T>(value);
                node.next = current;
                previous.next = node;
                size++;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean insertAfter(T searchValue, T value) {
        if (head == null) {
            return false;
        }
        Node<T> current = head;
        Node<T> previous = null;
        while (current != null) {
            if (current.data.equals(searchValue)) {
                previous = current;
                current = current.next;
                Node<T> node = new Node<T>(value);
                node.next = current;
                previous.next = node;
                size++;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        if (index >= size) {
            return false;
        }
        if (index == 0) {
            return removeAtHead();
        }
        Node<T> current = head;
        while (--index > 0) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
        return true;
    }

    @Override
    public boolean remove(T value) {
        if (head == null) {
            return false;
        }
        if (head.data.equals(value)) {
            return removeAtHead();
        }
        Node<T> current = head.next;
        Node<T> previous = head;
        while (current != null) {
            if (current.data.equals(value)) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean detectLoop(){
        Node<T> slow = head;
        Node<T> fast = head;
        //slow go 1 step, fast go 2 step

        while(slow != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow.data == fast.data){
                //If the slow and fast catch each other --> there's a loop
                fast = fast.next;
                int count = 1;

                while(fast.next != slow){
                    fast = fast.next;
                    count++;
                    //This count to count the step of the loop
                }

                //position fast "count" steps before slow
                //also use the two pointers technique
                fast = slow = head;
                Node<T> prev = null;

                while(count > 0){
                    prev = fast;
                    fast = fast.next;
                    count--;
                    //advance fast and slow with the same speed
                }
                while(fast != slow){
                    prev = fast; 
                    fast = fast.next;
                    slow = slow.next;
                }

                prev.next = null;
            }
        }

        return false;
    }

    // public void removeLoop() {
        
    // }
}
