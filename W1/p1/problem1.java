package p1;

public class problem1 {
    // 1.1/ Using pseudocode or Java, describe an algorithm to find the largest number in an array 
    //- without sorting the array

    // Example:

    // A = [7, 6, 9, 3, 2, 5] => return 9

    public static int findLargest(int[] A){
        int max = A[0];//Assume that the first element is the largest number in the array
        
        for(int i = 0; i < A.length; i++){
            max = A[i] > max ? A[i] : max;
            //max is equal A[i] if the A[i] > max. max = max if A[i] not larger than max
        }

        System.out.println("The largest element is: " + max);
        return max;
    }

    // 1.2/ Using pseudocode or Java, describe an algorithm to find the 
    //second largest number in an array - without sorting the array

    // A = [7, 6, 9, 3, 2, 5] => return 7

    public static int findSecondLargest(int[] A){
        int max = A[0];
        int secondMax = 0;

        for(int i = 0; i < A.length; i++){
            if(A[i] > max){
                //if we find the element that larger than the current max
                //We set the secondMax become max, and the max become the current element
                secondMax = max;
                max = A[i];
            } else if(A[i]> secondMax){
                //if we find the element that larger than the secondMax we re-assign the secondMax
                secondMax = A[i];
            }
        }

        System.out.println(secondMax);
        return secondMax;
    }

    public static void main(String[] args) {
        int[] A = {7, 6, 9, 3, 2, 5};
        //1.1
        findLargest(A);
        
        findSecondLargest(A);
        
    }
}



