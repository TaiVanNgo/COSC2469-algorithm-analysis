package W8.p1;

public class problem1 {

    void mergeSort() {

    }

    void quickSort() {

    }
}

class MergeSort {
    public void mergeSort(int arr[]) {
        if (arr.length > 1) {
            int n = arr.length;
            int middle = n / 2;

            // create 2 sub-arrays from arr
            int[] sub1 = new int[middle];
            for (int i = 0; i < middle; i++) {
                sub1[i] = arr[i];
            }
            int[] sub2 = new int[n - middle];
            for (int i = middle; i < n; i++) {
                sub2[i - middle] = arr[i];
            }

            // sort first and second halves
            mergeSort(sub1);
            mergeSort(sub2);

            // merge sub1 and sub2 into the original array
            merge(sub1, sub2, arr);
        }
    }

    // merge two sub-arrays sub1 and sub2 into the array dest
    public void merge(int[] sub1, int[] sub2, int[] dest) {
        int p1 = 0, p2 = 0, pDest = 0; // pointers to 3 arrays
        while (p1 < sub1.length && p2 < sub2.length) {
            if (sub1[p1] <= sub2[p2]) {
                dest[pDest] = sub1[p1];
                p1++;
            } else {
                dest[pDest] = sub2[p2];
                p2++;
            }
            pDest++;
        }

        // copy remaining elements, if any
        while (p1 < sub1.length) {
            dest[pDest++] = sub1[p1++];
        }
        while (p2 < sub2.length) {
            dest[pDest++] = sub2[p2++];
        }
    }
}

class QuickSortLomuto {
    // sort with quick sort
    public void quickSort(int arr[], int left, int right) {
        if (left < right) {
            try {
                int p = partition(arr, left, right);
                quickSort(arr, left, p - 1);
                quickSort(arr, p + 1, right);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    // Lomuto partition
    // Return a partition point p
    // Where all elements arr[left, p - 1] <= arr[p] <= all elements arr[p + 1,
    // right]
    public int partition(int arr[], int left, int right) {
        int p = arr[right]; // select the right-most element as pivot
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] <= p) {
                // swap
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;

                // increase i
                i++;
            }
        }
        // swap
        int tmp = arr[i];
        arr[i] = arr[right];
        arr[right] = tmp;

        return i;

    }
}

class QuickSortHoare {
    // sort with quick sort
    public void quickSort(int arr[], int left, int right) {
        if (left < right) {
            int p = partition(arr, left, right);
            quickSort(arr, left, p);
            quickSort(arr, p + 1, right);
        }
    }
    // Hoare partition
    // Return a partition point p
    // Where all elements arr[left, p] <= all elements arr[p + 1, right]
    public int partition(int arr[], int left, int right) {
        int p = arr[left]; // select the left-most element as pivot
        int front = left;
        int back = right;
        while (true) {
            while (arr[front] < p) {
                front++;
            }
            while (arr[back] > p) {
                back--;
            }
            if (front >= back) {
                return back;
            }
            // swapping
            int t = arr[front];
            arr[front] = arr[back];
            arr[back] = t;
            front++;
            back--;
        }
    }
}
