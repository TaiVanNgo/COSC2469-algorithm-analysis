package mocktest2;

import W3.LinkedListQueue;

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
        int size = this.learningMap.length;
        boolean[] visited = new boolean[size];
        int[] distance = new int[size];

        for (int i = 0; i < size; i++) {
            distance[i] = Integer.MAX_VALUE;// initially, the distance to all nodes are infinity
        }
        distance[0] = 0;// fisrt the first node is 0

        // 0 1 5
        // 4 0 3
        // 2 1 0

        int[] parent = new int[size];// keep track the parent
        for (int i = 0; i < size; i++) {
            parent[i] = -1;// -1 means that they don't have parent
        }

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        int shortest = Integer.MAX_VALUE;
        int currentNode = -1;

        // we will determine the node that we start to process (the processed node willl
        // be the node that have the smallest distance in the table distacne)
        for (int i = 0; i < size; i++) {
            if (visited[i]) {
                continue;
            }

            if (distance[i] < shortest) {
                shortest = distance[i];
                currentNode = i;
            }
        }

        if (currentNode != -1) {
            visited[currentNode] = true;
            queue.enQueue(currentNode);
        }

        while (!queue.isEmpty()) {
            // find the shortest neighbour
            for (int i = 0; i < size; i++) {
                if (this.learningMap[currentNode][i] > 0) {

                }
            }

        }

    }

    public static void main(String[] args) {
        TaiEasyLearning easyLearning = new TaiEasyLearning(
                new int[][] { { 0, 1, 5 }, { 4, 0, 3 }, { 2, 1, 0 } });

        System.out.println("compare: " + easyLearning.compare(new int[] { 0, 2 }, new int[] { 0, 1, 2 }));
    }
}
