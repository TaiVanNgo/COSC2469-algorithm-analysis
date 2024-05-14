package W8.p1;

import java.util.Random;

class problem1 {
    static final int SIZE = (int) 1e6;
    static final int SIZE_SMALL = 10;
    static final int RANGE = (int) 1e4;

    // use a small range (for example, 100) makes
    // QuickSort/Lomuto partition raise a StackOverflow exception
    // why?

    public static int[] generate(boolean small) {
        int size = SIZE;
        if (small) {
            size = SIZE_SMALL;
        }
        Random rnd = new Random();
        int[] res = new int[size];
        for (int i = 0; i < res.length; i++) {
            res[i] = rnd.nextInt(RANGE);
        }
        return res;
    }

    public static void print(int[] arr) {
        StringBuilder sb = new StringBuilder("Array: ");
        boolean first = true;
        for (int n : arr) {
            if (!first) {
                sb.append(", " + n);
            } else {
                sb.append(n);
                first = false;
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        // int[] arr = generate(true);
        // System.out.println("Before Merge Sort: ");
        // print(arr);
        // new MergeSort().mergeSort(arr);
        // System.out.println("After Merge Sort: ");
        // print(arr);

        int[] arr1 = new int[] { 3, 7, 8, 5, 4, 2, 6, 1 };
        System.out.println("Before Lomuto Quick Sort: ");
        print(arr1);
        new QuickSortLomuto().quickSort(arr1, 0, arr1.length - 1);
        System.out.println("After Lomuto Quick Sort: ");
        print(arr1);
    }
}

class MergeSort {
    void mergeSort(int[] arr) {
        if (arr.length > 1) {
            // first we divide the array into 2 sub arrays
            int length = arr.length;
            int middle = length / 2;

            // we create 2 sub arrs;
            int[] leftArr = new int[middle];
            for (int i = 0; i < middle; i++) {
                // copy value to the left arry
                leftArr[i] = arr[i];
            }

            int[] rightArr = new int[length - middle];
            for (int i = middle; i < length; i++) {
                // copy value to the right arr;
                rightArr[i - middle] = arr[i];
            }

            // then we call the merge sort to seperate it again and again
            mergeSort(leftArr);// we divide the left array first
            mergeSort(rightArr);// we divide the left array first

            // then we merge it together
            merge(leftArr, rightArr, arr);
        }
    }

    void merge(int[] leftArr, int[] rightArr, int[] arr) {
        int l = 0, r = 0, i = 0;
        while (l < leftArr.length && r < rightArr.length) {
            // prevent the left and right go out of the sub arrasy. "l" is the pointer for
            // left sub arr, "r" is for right sub arr
            // i for main arr
            if (leftArr[l] < rightArr[r]) {
                arr[i] = leftArr[l];
                l++;
            } else {
                arr[i] = rightArr[r];
                r++;
            }
            i++;
        }

        // at the end, it may be left or right still have number haven't copy yet, we
        // copy it into the array since the 2 sub arrs is sorted already
        while (l < leftArr.length) {
            arr[i] = leftArr[l];
            l++;
            i++;
        }

        while (r < rightArr.length) {
            arr[i] = rightArr[r];
            i++;
            r++;
        }
    }
}

class QuickSortLomuto {
    void quickSort(int[] arr, int left, int right) {
        // base case if the left exceed teh right
        if (left < right) {
            int p = partition(arr, left, right);

            quickSort(arr, left, p - 1);
            quickSort(arr, p + 1, right);
        }
        // find the partition

    }

    int partition(int arr[], int left, int right) {
        // we assume that the partion at the right
        int p = arr[right];
        int i = left; // i goes from left

        for (int j = left; j < right; j++) {
            if (arr[j] <= p) {
                // if we find the value that smaller than the partiion, we swap value of i and j
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }

        // after the loop, we swap the partition with the i
        int temp = arr[i];
        arr[i] = p;
        arr[right] = temp;

        return i;
    }
}