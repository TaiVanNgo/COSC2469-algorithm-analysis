package mocktest2.third_attempt;

public class TaiEasyLearning {
    int[][] learningMap;

    TaiEasyLearning(int[][] learningMap) {
        this.learningMap = learningMap;
    }

    // time complexity: O(N)
    public int compare(int[] seq1, int[] seq2) {
        int cost1 = 0;
        for (int i = 0; i < seq1.length; i++) {
            if (i + 1 < seq1.length) {
                cost1 += this.learningMap[seq1[i]][seq1[i + 1]];
            }
        }

        int cost2 = 0;
        for (int i = 0; i < seq2.length; i++) {
            if (i + 1 < seq2.length) {
                cost2 += this.learningMap[seq2[i]][seq2[i + 1]];
            }
        }

        if (cost1 > cost2) {
            return 1;
        } else if (cost1 < cost2) {
            return -1;
        }

        return 0;
    }

    public int bestSequence() {
        int source = 0;
        int destination = this.learningMap.length - 1;

        int size = this.learningMap.length;
        int length = 0;

        boolean[] visited = new boolean[size]; // visited state
        // distance table
        int[] distanceTable = new int[size];
        int[] previous = new int[size];

        for (int i = 0; i < size; i++) {
            distanceTable[i] = Integer.MAX_VALUE;
            previous[i] = -1;
        }
        distanceTable[source] = 0;// start from course 0

        while (true) {
            int shortestLength = Integer.MAX_VALUE;
            int currentNode = -1; // index of the node having the shortest distance to the tree

            // determine the shortest-distance node to the tree
            for (int i = 0; i < size; i++) {
                if (visited[i]) {
                    continue;
                }
                if (shortestLength > distanceTable[i]) {
                    shortestLength = distanceTable[i];
                    currentNode = i;
                }
            }
            for (int i = 0; i < size; i++) {
                if (visited[i]) {
                    continue;
                }
                // shortestNode and i are connected?
                if (this.learningMap[currentNode][i] > 0) {
                    if (distanceTable[i] > distanceTable[currentNode] + this.learningMap[currentNode][i]) {
                        distanceTable[i] = distanceTable[currentNode] + this.learningMap[currentNode][i];
                        previous[i] = currentNode;
                    }
                }
            }

            if (currentNode == destination) {
                // we reach the destination
                // display the shortest path
                String path = currentNode + "";
                while (previous[currentNode] != -1) {
                    currentNode = previous[currentNode];
                    path = currentNode + " " + path;
                }

                System.out.println(path);
                return distanceTable[destination];
            }

            if (shortestLength == Integer.MAX_VALUE) {
                // we cannot go further
                return Integer.MAX_VALUE;
            }
            // continue the next round
            visited[currentNode] = true;
        }
    }

    public static void main(String[] args) {
        TaiEasyLearning learning = new TaiEasyLearning(new int[][] { { 0, 1, 5 }, { 4, 0, 3 }, { 2, 1, 0 } });

        System.out.println(learning.compare(new int[] { 0, 2 }, new int[] { 0, 1, 2 }));

        System.out.println(learning.bestSequence());

    }
}
