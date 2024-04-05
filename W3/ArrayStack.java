package W3;

// Array-based implementation of stack
public class ArrayStack<T> {
    private int size;
    // Assume the number of elements in the queue will never exceed CAPACITY
    private static int CAPACITY = 100;
    private T[] items;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        size = 0;
        items = (T[]) new Object[CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean push(T item) {
        // make sure the stack still have empty slot
        if (size < CAPACITY) {
            items[size] = item;
            size++;
            return true;
        }
        return false;
    }

    public boolean pop() {
        // make sure the stack is not empty
        if (isEmpty()) {
            return false;
        }
        size--;
        return true;
    }

    public T peek() {
        // make sure the stack is not empty
        if (isEmpty()) {
            return null;
        }
        return items[size - 1];
    }
}
