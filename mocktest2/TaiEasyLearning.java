package mocktest2;

public class TaiEasyLearning {
    int[][] learningMap;

    TaiEasyLearning(int[][] learningMap) {
        this.learningMap = learningMap;
    }

    // time complex N1 + N2 (N1 is length of seq1, N2 is length of seq2), ==> TIME
    // COMPLEX: O(N)
    int compare(int[] seq1, int[] seq2) {
        // 0 (0 1 5)
        // 1 (4 0 3)
        // 2 (2 1 0)

        int cost1 = 0, cost2 = 0;// this store the cost of two seq
        // seq1 = [0, 2];
        // seq2 = [0, 1, 2];
        for (int i = 0; i < seq1.length; i++) {
            int nextI = i + 1;
            if (nextI < seq1.length) {
                cost1 += learningMap[i][seq1[nextI]];
            }
        }

        for (int i = 0; i < seq2.length; i++) {
            int nextI = i + 1;
            if (nextI < seq2.length) {
                cost2 += learningMap[i][seq2[nextI]];
            }
        }

        if (cost1 > cost2) {
            return 1;
        }
        if (cost1 < cost2) {
            return -1;
        }
        return 0;
    }

    int bestSequence() {
        // 0 1 5
        // 4 0 3
        // 2 1 0
        // ************* SET UP PHASE *************
        int size = this.learningMap.length;
        // define the cost table that store all the costof the courses
        int[] costTable = new int[size];

        for (int i = 0; i < size; i++) {
            costTable[i] = Integer.MAX_VALUE;
        }
        costTable[0] = 0;// we pick the first course as the first course we take

        boolean[] processed = new boolean[size];// keep track the processed courses

        int count = 0;// use to keep track the processed course
        int totalCost = 0;// the result
        // ************* MAIN PROGRAM *************

        while (count < size) {
            // first find the course that has the minimum cost
            int smallestCost = Integer.MAX_VALUE;
            int currentCourse = -1;

            for (int i = 0; i < size; i++) {
                if (processed[i]) {
                    continue;
                }
                if (costTable[i] < smallestCost) {
                    // if we can find the course that have cost < than the current smallets cost
                    // update
                    smallestCost = costTable[i];
                    currentCourse = i;
                }
            }

            // after the for loop, we find the current course that we will process
            if (count == size - 1) {
                System.out.println(currentCourse);
            } else {
                System.out.print(currentCourse + "->");
            }

            processed[currentCourse] = true;
            totalCost += costTable[currentCourse];// update the total cost
            count++;

            // find all the neighbors of the current course
            for (int i = 0; i < size; i++) {
                if (processed[i]) {
                    continue;
                }
                if (this.learningMap[currentCourse][i] > 0) {// check if has the connection
                    if (this.learningMap[currentCourse][i] < costTable[i]) {
                        // update the costTable when find the better way to connect the course
                        costTable[i] = this.learningMap[currentCourse][i];
                    }
                }
            }
            // after this loop, we updated all the neighbors of the current node
        }

        // after the big while loop, we got the most optimized cost
        return totalCost;
    }

    public static void main(String[] args) {
        TaiEasyLearning easyLearning = new TaiEasyLearning(
                new int[][] { { 0, 1, 5 }, { 4, 0, 3 }, { 2, 1, 0 } });

        System.out.println("compare: " + easyLearning.compare(new int[] { 0, 2 }, new int[] { 0, 1, 2 }));
        //CÁI NÀY SAI RỒI, COI PHẦN SECOND_ATTEMPT
        int res = easyLearning.bestSequence();
        System.out.println("The total switching cost of the best learning sequence is: " + res);
    }
}
