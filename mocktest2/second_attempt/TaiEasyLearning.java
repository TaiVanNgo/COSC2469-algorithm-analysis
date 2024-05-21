package mocktest2.second_attempt;

public class TaiEasyLearning {
    int[][] cost;

    public TaiEasyLearning(int[][] c) {
        this.cost = c;
    }

    // O(N)
    public int compare(int[] seq1, int[] seq2) {
        int c1 = 0;
        int c2 = 0;

        for (int i = 0; i < seq1.length - 1; i++) {
            c1 += cost[seq1[i]][seq1[i + 1]];
        }

        for (int i = 0; i < seq2.length - 1; i++) {
            c2 += cost[seq2[i]][seq2[i + 1]];
        }
        if (c1 > c2) {
            return 1;
        }

        if (c1 < c2) {
            return -1;
        }

        return 0;
    }

    // complexity O(N^2)
    public int bestSequence() {
        int destination = this.cost.length - 1;
        // Dijkstra's Algorithm
        int size = this.cost.length;

        int[] distance = new int[size];
        // Initally, make it infiny
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[0] = 0;

        boolean[] visited = new boolean[this.cost.length];

        int[] parent = new int[this.cost.length];// keep track the parent
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;// -1 means that they don't have parent
        }

        while (true) {
            // find the shortest distance in the distance table first
            int shortest = Integer.MAX_VALUE;
            int currentNode = -1;// the current node we process is the node that have the shortest path
            for (int i = 0; i < size; i++) {
                if (visited[i]) {
                    continue;
                }

                if (distance[i] < shortest) {
                    shortest = distance[i];
                    currentNode = i;
                }
            }

            for (int i = 0; i < size; i++) {
                if (this.cost[currentNode][i] > 0) {
                    if (this.cost[currentNode][i] + distance[currentNode] < distance[i]) {
                        distance[i] = this.cost[currentNode][i] + distance[currentNode];
                        // update the parent of that node
                        parent[i] = currentNode;
                    }
                }
            }

            if (currentNode == destination) {
                String path = currentNode + "";
                while (parent[currentNode] != -1) {
                    currentNode = parent[currentNode];

                    // update
                    path = currentNode + " " + path;
                }

                System.out.println(path);
                return distance[destination];
            }

            if (shortest == Integer.MAX_VALUE) {
                // we can not find the connection to go
                return Integer.MAX_VALUE;
            }
            // update hte visited
            visited[currentNode] = true;
        }
    }

    public static void main(String[] args) {
        TaiEasyLearning learningPath = new TaiEasyLearning(
                new int[][] { { 0, 1, 5 },
                        { 4, 0, 3 },
                        { 2, 1, 0 }
                });

        System.out.println(
                learningPath.compare(new int[] { 0, 2 }, new int[] { 0, 1, 2 }));

        System.out.println(learningPath.bestSequence());
    }
}
