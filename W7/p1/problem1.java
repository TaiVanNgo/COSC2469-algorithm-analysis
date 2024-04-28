package W7.p1;

public class problem1 {
    // Implement Selection sort and Bubble sort and display the array being sorted
    // after each iteration of the outer loop.
    static void selectionSort(int[] arr) {
        int temp = 0;
        int minIndex = -1;
        displayArr(arr, 0);
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    // udpate the min
                    minIndex = j;
                }
            }

            // after each loop, we swap the value
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
            // print the each outer iteration
            displayArr(arr, i + 1);
        }
    }

    static void bubbleSort(int[] arr) {
        displayArr(arr, 0);
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j + 1] < arr[j]){
                    temp =  arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            displayArr(arr, i + 1);
        }
    }

    static void displayArr(int[] arr, int iter) {
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                System.out.print("[" + arr[i] + ", ");
            } else if (i == arr.length - 1) {
                System.out.print(arr[i] + "]");
            } else {
                System.out.print(arr[i] + ", ");
            }
        }
        if (iter == 0) {// original state
            System.out.print(" (original state)");
            System.out.println();
            return;
        } else {
            String str = iter == 1 ? "st" : iter == 2 ? "nd" : iter == 3 ? "rd" : "th";

            System.out.print(" (after the " + iter + str + " iteration)");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[] { 5, 1, 9, 6, 2 };
        System.out.println("SELECTION SORT TEST:");
        selectionSort(arr1);

        int[] arr2 = new int[] { 5, 1, 9, 6, 2 };
        System.out.println("BUBBLE SORT TEST:");
        bubbleSort(arr2);
    }
}
