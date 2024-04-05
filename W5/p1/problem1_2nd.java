package W5.p1;

public class problem1_2nd {
    static int[] generateRandomArray(int size, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max + 1);
        }
        return arr;
    }

    static int[] countingSort(int[] arr, int max) {
        // first create the frequency array to store the frequency of the element inside
        // the array
        int[] freqArr = new int[max + 1];
        // the size of the freqArr is the maximum value of the array
        for (int i = 0; i < arr.length; i++) {
            freqArr[arr[i]]++;
        }

        // after this we got the frequency array for the array
        // we generate the cumulative array from the freArray (we use the freqArr to
        // advoid using much data )
        for (int i = 1; i <= max; i++) {
            freqArr[i] = freqArr[i - 1] + freqArr[i];
        }

        // after we have the Accumulative array --> we can create the array for result
        int[] sortedArr = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            sortedArr[freqArr[arr[i]] - 1] = arr[i];
            freqArr[arr[i]]--;
        }

        return sortedArr;
    }

    static void displayArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        System.out.println("TEST 1");
        int[] test1 = generateRandomArray(10, 5);
        System.out.println("Array before sorting: ");
        displayArray(test1);
        System.out.println("\nArray after sorting");
        int[] result = countingSort(test1, 5);
        for (int i : result) {
            System.out.print(i + " ");
        }

        System.out.println();
        int size = 1000000;
        int max = 1000000;
        int[] test3 = generateRandomArray(size, max);
        long t1 = System.currentTimeMillis();
        int[] arr = countingSort(test3, max);
        long t2 = System.currentTimeMillis();
        System.out.println("Distribution sort: " + (t2 - t1) + " milliseconds");

        long t3 = System.currentTimeMillis();
        java.util.Arrays.sort(test3);
        long t4 = System.currentTimeMillis();
        System.out.println("Java sort: " + (t4 - t3) + " milliseconds");
    }
}
