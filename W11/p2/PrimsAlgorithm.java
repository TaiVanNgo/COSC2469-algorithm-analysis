package W11.p2;

public class PrimsAlgorithm {
    // castles = [
    // [0, 1, 2, 8]
    // [1, 0, 3, 5],
    // [2, 3, 0, 4]
    // [8, 5, 4, 0]
    // ]

    public static void main(String[] args) {
        System.out.println(buildRoad(new int[][] { { 0, 1, 2, 8 }, { 1, 0, 3, 5 }, { 2, 3, 0, 4 }, { 8, 5, 4, 0 } }));

    }

    static int buildRoad(int[][] arr) {
        int size = arr.length;

        // define a distance table
        int[] distanceTable = new int[size];
        for (int i = 0; i < size; i++) {
            distanceTable[i] = Integer.MAX_VALUE;
        }
        distanceTable[0] = 0;// we pick the start point is index 0

        boolean[] added = new boolean[size];// add to the spining tree

        int count = 0;// we detect when added count to the size, we stop
        int length = 0;// the length of the road
        while (count < size) {
            // first we find the smallest distance
            int shortestPath = Integer.MAX_VALUE;
            int currentNode = -1;// current node is the shortest node.

            for (int i = 0; i < size; i++) {
                if (distanceTable[i] < shortestPath && !added[i]) {
                    shortestPath = distanceTable[i];
                    currentNode = i;
                }
            }

            // after the for loop, we will get the shortest path and the current node
            // we mark it as visited

            added[currentNode] = true;
            length += distanceTable[currentNode];
            count++;

            for (int i = 0; i < size; i++) {
                if (arr[currentNode][i] < distanceTable[i] && !added[i]) {
                    // if we find the path from currentndode to the i and the i hasn't added to the
                    // tree, we updated it and put it to the tree
                    distanceTable[i] = arr[currentNode][i];
                }
            }

        }
        return length;
    }

}