package W7;

public class Permutation {

    static void permuation(int arr[], int fi) {// fi is fixed index
        // basecase, if the fixed index go to the length . we print it
        if (fi == arr.length) {
            displayArr(arr);
            return;
        }

        // we go from the fixid to the next element
        for (int i = fi; i < arr.length; i++) {
            // [1,2,3]
            // from the fixed index, we swap the 2 left elements
            // if the fi = 0 --> value = 1;
            // we keep 1, we swap 0 with 0, and call the permutation with the fi ++
            swap(arr, fi, i);
            permuation(arr, fi + 1);
            swap(arr, fi, i);
        }
    }

    static void swap(int[] arr, int fi, int i) {
        int temp = arr[fi];
        arr[fi] = arr[i];
        arr[i] = temp;
    }

    static void displayArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        permuation(new int[] { 1, 2, 3}, 0);
    }

}
