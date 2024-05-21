package W8.p1;

import java.util.Random;

class Merge_Quick_Sort {

    public static int[] generate() {
        int size = (int) 1e6;
        Random rnd = new Random();
        int[] res = new int[size];
        for (int i = 0; i < res.length; i++) {
            res[i] = rnd.nextInt((int) 1e4);
        }
        return res;
    }

    static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }

    public static void main(String[] args) {
        // int[] mergeArr = new int[] { 3, 7, 6, 4, 9, 8 };
        // new MergeSort().mergeSort(mergeArr);
        // printArr(mergeArr);
        // Effeciency testing
        // long mergeStartTime = System.currentTimeMillis();
        // int[] mergeArr2 = generate();
        // new MergeSort().mergeSort(mergeArr2);
        // // printArr(mergeArr2);
        // System.out.println("\nTime: " + (System.currentTimeMillis() - mergeStartTime)
        // + "ms");

        // int[] lomutoArr = new int[] { 3, 7, 6, 4, 9, 8 };
        // (new QuickSort()).quickSortLomuto(lomutoArr, 0, lomutoArr.length - 1);
        // printArr(lomutoArr);

        int[] hoareArr = new int[] { 3, 7, 6, 4, 9, 8 };
        (new QuickSort()).quickSortHoare(hoareArr, 0, hoareArr.length - 1);
        printArr(hoareArr);
    }
}

class QuickSort {
    void quickSortLomuto(int[] arr, int left, int right) {
        if (left < right) {
            // only process the array if left don't exceed right
            int p = paritionLomuto(arr, left, right);

            quickSortLomuto(arr, left, p - 1);
            quickSortLomuto(arr, p + 1, right);
        }
    }

    int paritionLomuto(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;

        for (int j = left; j < right; j++) {
            // if find the value that smaller than the pivot
            if (arr[j] < pivot) {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                i++;
            }
        }

        // after the loop, we swap
        int temp = arr[i];
        arr[i] = pivot;
        arr[right] = temp;

        return i;
    }

    void quickSortHoare(int[] arr, int left, int right) {
        if (left < right) {
            int p = partitionHoare(arr, left, right);

            quickSortHoare(arr, left, p);
            quickSortHoare(arr, p + 1, right);
        }
    }

    int partitionHoare(int[] arr, int left, int right) {
        int pivot = arr[left];
        int front = left;
        int back = right;
        while (true) {
            while (arr[front] < pivot) {
                front++;
            }
            while(arr[back] > pivot) {
                back--;
            }

            if (back <= front) {
                return back;
            }

            int temp = arr[front];
            arr[front] = arr[back];
            arr[back] = temp;
            front++;
            back--;
        }
    }

}

class MergeSort {
    void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int size = arr.length;
            // divide the arr into 2 half
            int mid = size / 2;

            // create the sub left array
            int[] leftArr = new int[mid];
            for (int i = 0; i < mid; i++) {
                leftArr[i] = arr[i];
            }

            int[] rightArr = new int[size - mid];
            for (int i = mid; i < size; i++) {
                rightArr[i - mid] = arr[i];
            }

            // after determine the 2 sub arrays, we continue call the funciton recursively
            mergeSort(leftArr);
            mergeSort(rightArr);

            // after that, merge it together
            merge(leftArr, rightArr, arr);
        }
    }

    void merge(int[] leftArr, int[] rightArr, int[] arr) {
        int left = 0, right = 0, pointer = 0;

        while (left < leftArr.length && right < rightArr.length) {
            // avoid going out of the array
            if (leftArr[left] < rightArr[right]) {
                arr[pointer] = leftArr[left];
                left++;// increase the left pointer
            } else {
                // if right is bigger
                arr[pointer] = rightArr[right];
                right++;
            }

            pointer++;// increase the pointer
        }

        while (left < leftArr.length) {
            arr[pointer] = leftArr[left];
            pointer++;
            left++;
        }

        while (right < rightArr.length) {
            arr[pointer] = rightArr[right];
            pointer++;
            right++;
        }
    }
}