package mocktest2.second_attempt;

public class TaiDoraemonCake {
    Topic[] topics;
    double A;

    TaiDoraemonCake(Topic[] topics, double A) {
        this.topics = topics;
        this.A = A;
    }

    // Time complexity: O( Nlog(N) )
    public double weightByNumber(int X) {
        double[] weightArr = new double[this.topics.length];
        for (int i = 0; i < weightArr.length; i++) {
            weightArr[i] = this.topics[i].W;
        }

        new MergeSort().mergeSort(weightArr);

        double res = 0;
        for (int i = weightArr.length - 1; i > weightArr.length - 1 - X; i--) {
            res += weightArr[i];
        }

        return res;
    }

    // Time complexity is: O(2^N)
    public double largestWeight() {
        Subset.subset(topics.clone(), new boolean[this.topics.length], 0, this.A);

        for (int i = 0; i < Subset.bestSubset.length; i++) {
            if (Subset.bestSubset[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        return Subset.bestW;
    }

    public static void main(String[] args) {
        TaiDoraemonCake doraemonCake = new TaiDoraemonCake(
                new Topic[] { new Topic(8, 7), new Topic(10, 8), new Topic(5, 3) }, 10);

        System.out.println(doraemonCake.weightByNumber(2));

        System.out.println(doraemonCake.largestWeight());
    }

}

class Subset {
    static double bestW;
    static boolean[] bestSubset;

    static void subset(Topic[] arr, boolean[] selected, int index, double A) {
        // base case
        // if index is == the length. we return
        if (index == arr.length) {
            process(arr, selected, A);
            return;
        }
        // we select,
        selected[index] = true;
        subset(arr, selected, index + 1, A);

        // we not select
        selected[index] = false;
        subset(arr, selected, index + 1, A);
    }

    private static void process(Topic[] arr, boolean[] selected, double A) {
        double total_weight = 0, total_surface = 0;

        for (int i = 0; i < arr.length; i++) {
            if (selected[i]) {
                total_weight += arr[i].W;
                total_surface += arr[i].S;

                if (total_surface > A) {
                    return;
                }
            }
        }

        if (total_weight > bestW) {
            bestW = total_weight;
            bestSubset = selected.clone();
        }

    }
}

class Topic {
    public double W;
    public double S;

    public Topic(double w, double s) {
        this.W = w;
        this.S = s;
    }
}

class MergeSort {
    void mergeSort(double[] arr) {
        if (arr.length > 1) {
            int size = arr.length;
            // divide the arr into 2 half
            int mid = size / 2;

            // create the sub left array
            double[] leftArr = new double[mid];
            for (int i = 0; i < mid; i++) {
                leftArr[i] = arr[i];
            }

            double[] rightArr = new double[size - mid];
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

    void merge(double[] leftArr, double[] rightArr, double[] arr) {
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