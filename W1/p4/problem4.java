package p4;
//Describe an efficient algorithm to answer range queries,

// but we need the sum of a range this time. More specifically,
// given an array A[0..N-1], implement an algorithm to return the sum of the sub-array A[L, R],
// L >= 0 and R <= N-1.

// Example:

// A = [6, 2, 9, 8, 5, 4, 3] 

// rangeSum(0, 0) => 6

// rangeSum(6, 6) => 3

// rangeSum(0, 6) => 37

// rangeSum(3, 4) => 13

// Assumption: the input array is static.

public class problem4 {
    // define sum[i] = A[0] + A[1] + ... + A[i]
    // we have:
    // sum[R] = A[0] + A[1] + ... + A[L-1] + A[L] + ... + A[R]
    // and:
    // sum[L-1] = A[0] + A[1] + ... + A[L-1]
    // as a result:
    // sum[R] - sum[L-1] = A[L] + A[L+1] + ... + A[R]
    // sum[R] - sum[L-1] = sum(L, R);//sum from L to R
    // the expression on the RHS above is the sum of the range (L -> R)

    // also, we have:
    // sum[i] = A[0] + A[1] + ... + A[i-2] + A[i-1] + A[i]
    // => sum[i] = sum[i-1] + A[i]
    // so, we can calculate all sum[i] based on sum[i-1]

    // pre-calculation step
    // sum[0] = A[0]
    // for i = 1 to N-1
    // sum[i] = sum[i-1] + A[i]
    public static int[] calculateSumOfArray(int[] A) {
        int sumLength = A.length;
        int[] sum = new int[sumLength];

        sum[0] = A[0]; // The first element of sum and A are identical
        for (int i = 1; i < A.length; i++) {
            sum[i] = sum[i - 1] + A[i];
        }

        return sum;
    }

    public static int[] rangeSum(int[] A, int[] L, int[] R) {
        // pre-calculation the array
        int[] sum = calculateSumOfArray(A);

        // the idea: first, we loop the integer[] A from 0 to the length of L or R
        int resultLength = L.length;// The result will contain the L.length element
        int[] results = new int[resultLength];// the array of result

        for (int i = 0; i < resultLength; i++) {
            // sum[R] - sum[L-1] = sum(L, R);//sum from L to R
            // we have this calculatetion
            if (L[i] == R[i]) {
                results[i] = A[L[i]];
            } else if (L[i] == 0) {
                results[i] = sum[R[i]];
            } else {
                results[i] = sum[R[i]] - sum[L[i] - 1];
            }
        }

        return results;
    }

    public static void main(String[] args) {
        // A = [6, 2, 9, 8, 5, 4, 3]
        int[] A = { 6, 2, 9, 8, 5, 4, 3 };
        // rangeSum(0, 0) => 6
        // rangeSum(6, 6) => 3
        // rangeSum(0, 6) => 37
        // rangeSum(3, 4) => 13
        int[] L = { 0, 6, 0, 3 };
        int[] R = { 0, 6, 6, 4 };

        int[] results = rangeSum(A, L, R);

        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }
    }
}
