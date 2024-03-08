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
    public static int[] rangeSum(int[] A, int[] L, int[] R) {
        // the idea: first, we loop the integer[] A from 0 to the length of L or R

        int resultLength = L.length;// The result will contain the L.length element
        int[] results = new int[resultLength];// the array of result

        for (int i = 0; i < resultLength; i++) {
            results[i] = 0;// first assume the result at the current index is 0;

            for (int j = L[i]; j <= R[i]; j++) {
                // the j is run from the value at index i of L to the value at index i of R
                // for ex: L = {1, 3}, R = {2, 5}
                // --> if i = 0
                // for(int i = 1; j <= 2; j++) --> so we can calculate the
                // result from 1 to 2.

                // the result at i is the sum of the value of A at index j
                results[i] += A[j];
            }
        }

        return results;
    }

    public static void main(String[] args) {
        // A = [6, 2, 9, 8, 5, 4, 3] 
        int[] A = {6, 2, 9, 8, 5, 4, 3};
        // rangeSum(0, 0) => 6
        // rangeSum(6, 6) => 3
        // rangeSum(0, 6) => 37
        // rangeSum(3, 4) => 13
        int[] L = {0, 6, 0, 3};
        int[] R = {0, 6, 6, 4};

        int[] results = rangeSum(A, L, R);
        
        for(int i = 0; i < results.length; i++){
            System.out.println(results[i]);
        }
    }
}
