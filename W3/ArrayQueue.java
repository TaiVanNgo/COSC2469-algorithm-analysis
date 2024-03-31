package W3;

// array-based implementation of queue
public class ArrayQueue<T> {
  private int size;
  private int front, rear;

  // Assume the number of elements in the queue will never exceed CAPACITY
  private static int CAPACITY = 100;
  private T[] items;

  public ArrayQueue() {
    size = 0;
    front = rear = 0;
    items = (T[])new Object[CAPACITY];
  }

  public int size() {
    return size;
  }

  public Object[] items(){
    return items;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean enQueue(T item) {
    // make sure the queue still have empty slot
    if (size < CAPACITY) {
      items[rear] = item;
      rear = (rear + 1) % CAPACITY;
      size++;
      return true;
    }
    return false;
  }

  public boolean deQueue() {
    // make sure the queue is not empty
    if (isEmpty()) {
      return false;
    }
    front = (front + 1) % CAPACITY;
    size--;
    return true;
  }

  public T peekFront() {
    // make sure the queue is not empty
    if (isEmpty()) {
      return null;
    }
    return items[front];
  }


}