package W9.p2;

public class problem2 {
    public static void main(String[] args) {
        System.out.println("LOMUTO: ");
        int[] test = new int[] { 3, 5, 2, 1, 8, 9, 6, 7, 4 };

        int find1 = quickSelectLomuto(test, 1, 0, test.length - 1);
        System.out.println("The smallest elemnt in the array is: " + find1);

        test = new int[] { 3, 5, 2, 1, 8, 9, 6, 7, 4 };
        int find2 = quickSelectLomuto(test, 2, 0, test.length - 1);
        System.out.println("The second smallest elemnt in the array is: " + find2);

        test = new int[] { 3, 5, 2, 1, 8, 9, 6, 7, 4 };
        int find3 = quickSelectLomuto(test, 5, 0, test.length - 1);
        System.out.println("The Fifth smallest elemnt in the array is: " + find3);

        System.out.println("******************************* ");
        System.out.println("HOARE: ");

        find1 = quickSelectHoare(test, 1, 0, test.length - 1);
        System.out.println("The smallest elemnt in the array is: " + find1);

        test = new int[] { 3, 5, 2, 1, 8, 9, 6, 7, 4 };
        find2 = quickSelectHoare(test, 2, 0, test.length - 1);
        System.out.println("The second smallest elemnt in the array is: " + find2);

        test = new int[] { 3, 5, 2, 1, 8, 9, 6, 7, 4 };
        find3 = quickSelectHoare(test, 5, 0, test.length - 1);
        System.out.println("The Fifth smallest elemnt in the array is: " + find3);

        System.out.println("******************************* ");
        System.out.println("TEST EFFECTIONCY: ");
        // the array of 10.000.000 elements, the max element value is 10.000.000
        System.out.println("LOMUTO");
        test = generateRandomArray(10000000, 10000000);
        int[] testHoare = test;// copy the same array for hoare testing
        long lomutotime = System.currentTimeMillis();
        find1 = quickSelectLomuto(test, 1, 0, test.length - 1);
        System.out.println(
                "The smallest number is: " + find1 + "\nTime: " + (System.currentTimeMillis() - lomutotime) + "ms");

        System.out.println("HOARE");
        long hoaretime = System.currentTimeMillis();
        find1 = quickSelectHoare(testHoare, 1, 0, testHoare.length - 1);
        System.out.println(
                "The smallest number is: " + find1 + "\nTime: " + (System.currentTimeMillis() - hoaretime) + "ms");

    }

    static int quickSelectLomuto(int[] arr, int key, int left, int right) {
        int p = partitionLomuto(arr, left, right);

        if (p + 1 == key) {
            return arr[p];
        }
        if (key - 1 > p) {// if the key is greater than the key, we find the right range
            return quickSelectLomuto(arr, key, p + 1, right);
        }
        // if the key is smaller than the partition, we find in the left range
        return quickSelectLomuto(arr, key, left, p - 1);

    }

    static int partitionLomuto(int[] arr, int left, int right) {// Lomuto scheme
        int pivot = arr[right];

        int i = left;

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                // if we find the elemetn at the j is smaller than the pivot, we swap j and i
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;

                // increase i after swap
                i++;
            }
        }

        // after the for loop, we swap the i and the pivot
        // the position of pivot is right
        int temp = arr[i];
        arr[i] = pivot;
        arr[right] = temp;

        // return the position of the pivot
        return i;
    }

    static int quickSelectHoare(int[] arr, int key, int left, int right) {
        if (left == right) {
            return arr[left];
        }
        int p = partitionHoare(arr, left, right);

        if (p + 1 == key) {
            return arr[p];
        }
        if (p + 1 >= key) {// if the key is smaller than the p, we find the left range
            return quickSelectHoare(arr, key, left, p);
        }
        // if the key is greater than the partition, we find in the right range
        return quickSelectHoare(arr, key, p + 1, right);

    }

    static int partitionHoare(int[] arr, int left, int right) {
        int pivot = arr[left];// choose the pivot is the left array
        int front = left;
        int back = right;

        while (true) {
            while (arr[front] < pivot) {
                front++;
            }

            while (arr[back] > pivot) {
                back--;
            }

            if (back <= front) {
                return back;
            }

            // swap front and back
            int temp = arr[front];
            arr[front] = arr[back];
            arr[back] = temp;
            front++;
            back--;
        }

    }

    static int[] generateRandomArray(int size, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max + 1);
        }
        return arr;
    }
}