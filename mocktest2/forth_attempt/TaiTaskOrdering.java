package mocktest2.forth_attempt;

public class TaiTaskOrdering {
    TaiTask[] tasks;

    TaiTaskOrdering(TaiTask[] tasks) {
        this.tasks = tasks;
    }

    // time complexity: O(N)
    public double currentHappiness() {
        // (3, 9),
        // (2, 2),
        // (4, 10)
        int size = this.tasks.length;
        double fun = 0;
        for (int i = 1; i < size; i++) {
            if (this.tasks[i - 1].fun < this.tasks[i].fun) {
                fun += this.tasks[i].fun;
            }
        }

        double difficulty = 0;
        for (int i = 1; i < size; i++) {
            if (this.tasks[i - 1].difficulty > this.tasks[i].difficulty) {
                difficulty += this.tasks[i].difficulty;
            }
        }

        return fun + difficulty;
    }

    private double currentHappiness(TaiTask[] arr) {
        // (3, 9),
        // (2, 2),
        // (4, 10)
        int size = arr.length;
        double fun = 0;
        for (int i = 1; i < size; i++) {
            if (arr[i - 1].fun < arr[i].fun) {
                fun += arr[i].fun;
            }
        }

        double difficulty = 0;
        for (int i = 1; i < size; i++) {
            if (arr[i - 1].difficulty > arr[i].difficulty) {
                difficulty += arr[i].difficulty;
            }
        }

        return fun + difficulty;
    }

    // time complexity O(N * logN)
    public double funOnlyHappiness() {
        TaiTask[] funTasks = this.tasks.clone();
        new MergeSort().mergeSort(funTasks);

        return currentHappiness(funTasks);
    }

    // time complexity O(N!)
    public double maxHappiness() {

        Permutation.permute(this.tasks.clone(), new boolean[this.tasks.length], new TaiTask[this.tasks.length], 0);

        return Permutation.bestHappiness; 
    }

    public static void main(String[] args) {
        TaiTaskOrdering taskOrder = new TaiTaskOrdering(
                new TaiTask[] { new TaiTask(3, 9), new TaiTask(2, 2), new TaiTask(4, 10) });

        System.out.println(taskOrder.currentHappiness());

        System.out.println(taskOrder.funOnlyHappiness());

        taskOrder.maxHappiness();
    }
}

class TaiTask {
    double fun;
    double difficulty;

    TaiTask(double fun, double difficulty) {
        this.fun = fun;
        this.difficulty = difficulty;
    }
}

class Permutation {
    static TaiTask[] bestOrder;
    static double bestHappiness;

    static void permute(TaiTask[] input, boolean[] taken, TaiTask[] current, int idx) {
        if (idx == input.length) {
            process(current);
            return;
        }

        for (int i = 0; i < input.length; i++) {
            if (taken[i]) {
                continue;
            }
            current[idx] = input[i];
            taken[i] = true;
            permute(input, taken, current, idx + 1);
            taken[i] = false;
        }
    }

    static void process(TaiTask[] permutation) {
        double happiness = currentHappiness(permutation);

        if (happiness > bestHappiness) {
            bestHappiness = happiness;
            bestOrder = permutation.clone();
        }

    }

    private static double currentHappiness(TaiTask[] arr) {
        // (3, 9),
        // (2, 2),
        // (4, 10)
        int size = arr.length;
        double fun = 0;
        for (int i = 1; i < size; i++) {
            if (arr[i - 1].fun < arr[i].fun) {
                fun += arr[i].fun;
            }
        }

        double difficulty = 0;
        for (int i = 1; i < size; i++) {
            if (arr[i - 1].difficulty > arr[i].difficulty) {
                difficulty += arr[i].difficulty;
            }
        }

        return fun + difficulty;
    }
}

class MergeSort {
    public void mergeSort(TaiTask arr[]) {
        if (arr.length > 1) {
            int n = arr.length;
            int middle = n / 2;

            // create 2 sub-arrays from arr
            TaiTask[] sub1 = new TaiTask[middle];
            for (int i = 0; i < middle; i++) {
                sub1[i] = arr[i];
            }
            TaiTask[] sub2 = new TaiTask[n - middle];
            for (int i = middle; i < n; i++) {
                sub2[i - middle] = arr[i];
            }

            // sort first and second halves
            mergeSort(sub1);
            mergeSort(sub2);

            // merge sub1 and sub2 into the original array
            mergeFun(sub1, sub2, arr);
        }
    }

    // merge two sub-arrays sub1 and sub2 into the array dest
    public void mergeFun(TaiTask[] sub1, TaiTask[] sub2, TaiTask[] dest) {
        int p1 = 0, p2 = 0, pDest = 0; // pointers to 3 arrays
        while (p1 < sub1.length && p2 < sub2.length) {
            if (sub1[p1].fun <= sub2[p2].fun) {
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