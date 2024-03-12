package W2.p4;
import java.lang.Math;
// Given an array of numbers (array size >= 2), find two in the array whose sum is closest to zero.

// What is the complexity of your algorithm?

// Example 1: [2, 3, 9, 6] => 2 and 3

// Example 2: [-100, 50, -52, 99] => -100 and 99
public class problem4 {
    public static void main(String[] args) {
        // int[] A = { 2, 3, 9, 6 };
        int[] A = { -100, 50, -52, 99};

        int[] minArr = new int[2];
        int min = 1000;

        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (Math.abs(A[i] + A[j]) < Math.abs(min)) {
                    //try to use if < 0 compare the another way, >0 compare another way
                    min = A[i] + A[j];
                    minArr[0] = A[i];
                    minArr[1] = A[j];
                }
            }
        }

        System.out.println(minArr[0] + " and " + minArr[1]);
    }
}
