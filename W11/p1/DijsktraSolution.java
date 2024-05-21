package W11.p1;

import javax.lang.model.type.IntersectionType;

public class DijsktraSolution {
    int[][] map;

    DijsktraSolution(int[][] map) {
        this.map = map;
    }

    void shortestPath(int source, int destination) {
        // [0, 3, 2, 0]
        // [3, 0, 0, 5],
        // [2, 0, 0, 9]
        // [0, 5, 9, 0]

        int size = this.map.length;
        // first we define the distance table where store the distances to all nodes
        int[] distanceTable = new int[size];
        for (int i = 0; i < size; i++) {
            distanceTable[i] = Integer.MAX_VALUE;
        }

        // we start from the source is 0, set the distance to source to 0
        distanceTable[source] = 0;

        boolean[] visited = new boolean[size];
        int[] previous = new int[size];
        for (int i = 0; i < size; i++) {
            previous[i] = -1;
        }

        while (true) {
            // first we find the node that has the shortest path in the map
            int shortTestDistance = Integer.MAX_VALUE;
            int currentNode = -1;// the current node must be the node that has the shortes distance
            for (int i = 0; i < size; i++) {
                if (distanceTable[i] < shortTestDistance && !visited[i]) {
                    shortTestDistance = distanceTable[i];
                    currentNode = i;
                }
            }

            // if after loop, the shortest distance is still the infinity, we return
            if (shortTestDistance == Integer.MAX_VALUE) {
                return;
            }

            // after the for loop we will find current node
            visited[currentNode] = true;

            // if we reach the destination
            for (int i = 0; i < size; i++) {
                if (this.map[currentNode][i] > 0 && !visited[i]) {
                    if (this.map[currentNode][i] + distanceTable[currentNode] < distanceTable[i]) {
                        // udpate
                        distanceTable[i] = this.map[currentNode][i] + distanceTable[currentNode];
                        previous[i] = currentNode;
                    }
                }
            }

            if (currentNode == destination) {
                int parent = previous[currentNode];
                String path = currentNode + "";
                do {
                    path = parent + " -> " + path;
                    parent = previous[parent];
                } while (parent != -1);
                System.out.println("Path: " + path);
                System.out.println("Shortest distance:");
                System.out.println(distanceTable[destination]);
            }
        }
    }

    public static void main(String[] args) {
        DijsktraSolution solution = new DijsktraSolution(
                new int[][] { { 0, 3, 2, 0 }, { 3, 0, 0, 5 }, { 2, 0, 0, 9 }, { 0, 5, 9, 0 } });

        solution.shortestPath(0, 3);
    }
}
