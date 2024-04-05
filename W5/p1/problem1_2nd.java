package W5.p1;

public class problem1_2nd {
    static int[] generateRandomArray(int size, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max + 1);
        }

        return arr;
    }

    // static int[] coutingSort(int[] arr, int max){
    //     //first create the frequency array to store the frequency of the element inside the array
    //     int[] freqArr = new int[max];
    //     //the size of the freqArr is the maximum value of the array
    //     for(int i = 1; i < freqArr.length; i++){
    //         freqArr[arr[i]] ++;
    //     }

    //     //after this we got the frequency array for the array
    //     //we generate the cumulative array from the freArray (we use the freqArr to advoid using much data )
    //     for(int i = 1; i < freqArr.length; i++){
    //         freqArr[i] = freqArr[i-1] + freqArr[i];
    //     }

    //     return freqArr;
    // }

    static void displayArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        System.out.println("TEST 1");
        int[] test1 = generateRandomArray(10, 100);
        coutingSort(test1, 10);
        System.out.println("Array before testing: ");
        displayArray(test1);

    }
}
