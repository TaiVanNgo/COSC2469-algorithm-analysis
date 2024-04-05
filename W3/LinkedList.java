package W3;

public class LinkedList<T> implements List<T> {
    public static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public int size;
    private Node<T> pointer;
    public Node<T> head;

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

    public boolean createLoop(int index, T newValue) {
        Node<T> newNode = new Node<>(newValue);
        pointer = head;
        while (index > 0 && pointer != null) {
            pointer = pointer.next;
            index--;
        }

        newNode.next = pointer;

        while (pointer.next != null) {
            pointer = pointer.next;
        }

        pointer.next = newNode;
        size ++;//increase the size of the array

        return true;
    }

    public void showList() {
        pointer = head;
        while (pointer != null) {
            System.out.print(pointer.data + " --> ");
            pointer = pointer.next;
        }
    }

    public void showListUpToTwenty(){
        int count = 0;
        pointer = head;
        while (pointer != null && count != 20) {
            System.out.print(pointer.data + " --> ");
            pointer = pointer.next;
            count++;
        }
    }

    public int getIndexBasedOnData(T data){
        pointer = head;
        int index = 0;
        while(pointer.data != data){
            pointer = pointer.next;
            index++;
        }

        return index;
    }
}
