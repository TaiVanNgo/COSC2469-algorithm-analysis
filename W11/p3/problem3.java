package W11.p3;

import W3.ArrayStack;

public class problem3 {

  public static void main(String[] args) {
    longestSequence(new int[] { 5, 2, 3, 9, 6, 4, 19, 2, 5, 7, 8 });
  }

  static void longestSequence(int[] arr) {
    // *********** SETUP PHASE ************
    int size = arr.length;

    // the maxlength contain the max length of the current element (every element
    // have the minimum maxlength is one)
    int[] maxLength = new int[size];
    for (int i = 0; i < size; i++) {
      maxLength[i] = 1;
    }

    // the previous keep track the previous element of the current element
    int[] previous = new int[size];
    for (int i = 0; i < size; i++) {
      previous[i] = -1;
    }

    // *********** MAIN PHASE ************
    for (int i = 1; i < size; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[i] > arr[j]) {
          // if the current value at i is larger than value j
          // we compare the maxLength
          if (maxLength[i] < maxLength[j] + 1) {
            // if the maxLength[i] is currently smaller than the maxLenggh j ++, we
            // update the maxlength at i
            maxLength[i] = maxLength[j] + 1;
            previous[i] = j;// previous of i is j
          }
        }
      }
    }

    // after the while loop, we will have the maximum value that we can take
    // ensuring that they are in ascending order

    // we find the max value in the maxLength table
    int maxValue = 0;
    int maxNode = 0;// keep track the maxNode (use value)
    for (int i = 0; i < size; i++) {
      if (maxLength[i] > maxLength[maxValue]) {
        // update the new max value
        maxValue = maxLength[i];
        // update the max node
        maxNode = i;
      }
    }

    // after this for loop, we will have the current max value
    ArrayStack<Integer> stack = new ArrayStack<>();
    do {

      stack.push(arr[maxNode]);
      maxNode = previous[maxNode];

    } while (maxNode != -1);

    // take from the stack
    while (!stack.isEmpty()) {
      System.out.print(stack.peek() + ", ");
      stack.pop();
    }
  }
}