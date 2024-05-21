package mocktest2.third_attempt;

public class TaiDoraemonCake {
    Topic[] topics;
    double A;

    TaiDoraemonCake(Topic[] topics, double A) {
        this.topics = topics;
        this.A = A;
    }

    // time complexity O(N*logN)
    double weightByNumber(int X) {
        double[] weightsTable = new double[this.topics.length];

        for (int i = 0; i < this.topics.length; i++) {
            weightsTable[i] = this.topics[i].W;
        }

        MergeSort.mergeSort(weightsTable);

        double res = 0;
        for (int i = weightsTable.length - 1; i >= weightsTable.length - X; i--) {
            res += weightsTable[i];

        }
        return res;
    }

    // time complexity O(2^N)
    double largestWeigth() {
        Subset.subset(topics.clone(), new boolean[this.topics.length], 0, this.A);

        for (int i = 0; i < Subset.bestSubset.length; i++) {
            if (Subset.bestSubset[i]) {
                System.out.print(i + " ");

            }

        }
        System.out.println();
        return Subset.bestWeight;
    }

    public static void main(String[] args) {
        TaiDoraemonCake cake = new TaiDoraemonCake(new Topic[] { new Topic(8, 7), new Topic(10, 8), new Topic(5, 3) },
                10);

        System.out.println(cake.weightByNumber(2));
        double res = cake.largestWeigth();
        System.out.println(res);

    }
}

class Topic {
    double W;
    double S;

    Topic(double W, double S) {
        this.W = W;
        this.S = S;
    }
}

class Subset {
    public static boolean[] bestSubset;
    public static double bestWeight;

    static void subset(Topic[] input, boolean[] selected, int idx, double A) {
        if (idx == input.length) {
            process(input, selected, A);
            return;
        }

        // Not selected
        selected[idx] = false;
        subset(input, selected, idx + 1, A);

        // Selected
        selected[idx] = true;
        subset(input, selected, idx + 1, A);
    }

    static void process(Topic[] set, boolean[] selected, double A) {
        double weight = 0, surface = 0;
        for (int i = 0; i < set.length; i++) {
            if (selected[i]) {
                weight += set[i].W;
                surface += set[i].S;

                if (surface > A) {
                    return;// exceed the cake's area
                }
            }
        }
        // if not, we compare with the best weight
        if (weight > bestWeight) {
            bestWeight = weight;
            bestSubset = selected.clone();
        }
    }
}

class MergeSort {
    public static void mergeSort(double arr[]) {
        if (arr.length > 1) {
            int n = arr.length;
            int middle = n / 2;

            // create 2 sub-arrays from arr
            double[] sub1 = new double[middle];
            for (int i = 0; i < middle; i++) {
                sub1[i] = arr[i];
            }
            double[] sub2 = new double[n - middle];
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
    public static void merge(double[] sub1, double[] sub2, double[] dest) {
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