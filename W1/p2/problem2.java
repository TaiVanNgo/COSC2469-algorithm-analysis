package p2;

public class problem2 {
    // An array A of length N contains unique integers between zero and N.
    // Because there are (N + 1) different values but only N elements,
    // there is one value missing.
    // Implement an algorithm using pseudocode or Java to find the missing value.

    // Examples (N = 5 in the below examples):

    // 1/ A = [0, 3, 2, 4, 1] => return 5

    // 2/ A = [1, 5, 2, 4, 3] => return 0

    // 3/ A = [4, 0, 1, 5, 2] => return 3

    public static int findMissingValue(int[] A) {
        // the idea: we ultilize the fact that the array between 0 and N
        // it is (N+1) so it is increasing element
        // for the 3 examples above, we see that, the 5 elemtns has the same sum (if it
        // doens't lack the missing value)

        // First we find the sum that if there are no missing value

        int element = A.length; // The number of elements in the array A
        int sum = element * (element + 1) / 2;

        //after find the sum, we calculate the real sum 
        //which is the actual sum of the array
        int missingSum = 0;
        for(int i = 0; i < element; i++){
            missingSum += A[i];
        }


        //The element missing is the difference between real sum and the missing sum
        return sum - missingSum;
    }

    public static void main(String[] args) {

        int[] A = {0, 3, 2, 4, 1};
        int[] B = {1, 5, 2, 4, 3};
        int[] C = {4, 0, 1, 5, 2};
        System.out.println(findMissingValue(A));
        System.out.println(findMissingValue(B));
        System.out.println(findMissingValue(C));

    }
}
