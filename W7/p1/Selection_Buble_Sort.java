package W7.p1;

class Selection_Bubble_sort {

    public static void main(String[] args) {
        System.out.println("Selection sort");
        int[] arr = new int[] { 5, 1, 9, 6, 2 };
        selectionSort(arr);

        System.out.println("\nBubble sort");
        int[] arr2 = new int[] { 5, 1, 6, 9, 2 };
        bubbleSort(arr2);
    }

    static void selectionSort(int[] arr) {
        // {6, 5, 2, 8, 9, 4};
        printArr(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            int minimumValue = arr[i];
            int minimumIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                // for each i, we go through the loop from i + 1 to the last, we find the
                // minimum element
                if (arr[j] < minimumValue) {
                    minimumValue = arr[j];
                    minimumIndex = j;
                }
            }

            // after each sub loop of j. We swap the minimum value with the current i
            int temp = minimumValue;
            arr[minimumIndex] = arr[i];
            arr[i] = temp;
            // print arr of each stage
            printArr(arr, i);

        }
    }

    static void bubbleSort(int[] arr) {
        printArr(arr);

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // if we find previous element is larger than the next element, we swap
                    // swap
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }

            printArr(arr, i);
        }
    }

    static void printArr(int arr[]) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + ", ");
            }
        }
        System.out.println("] (original state)");

    }

    static void printArr(int arr[], int iteration) {
        iteration++;
        System.out.print("[");

        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + ", ");
            }
        }

        String order = "";
        if (iteration == 1) {
            order = iteration + "st";
        } else if (iteration == 2) {
            order = iteration + "nd";
        } else if (iteration == 3) {
            order = iteration + "rd";
        } else {
            order = iteration + "th";
        }

        System.out.println("] (after the " + order + " iteration)");

    }

}