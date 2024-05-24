public class TaiStreetNetwork {
    int[][] map;

    TaiStreetNetwork(int[][] map) {
        this.map = map;
    }

    // TIME COMPLEXITY: O(N)
    public int nearestNeighbour() {
        // for the nearest Neighbour of my home, just simply loop through the map and
        // return the smallest distance,

        int shortestDistance = Integer.MAX_VALUE;
        int nearestNeighbour = -1;

        for (int i = 1; i < this.map.length; i++) {
            if (this.map[0][i] > 0 && this.map[0][i] < shortestDistance) {
                // two conditions: (1) -> the distance must > 0,
                // -> -1 is cannot go, 0 is current position
                // (2), the distance to that neightbouar must be smaller than the current
                // shortest distance

                // update
                shortestDistance = this.map[0][i];
                nearestNeighbour = i;
            }
        }

        return nearestNeighbour;
    }

    // TIME COMPLEXITY: O(N^2)
    public int shortestToSchool() {
        // home is 0, campus is N - 1
        return shortestPath(0, this.map.length - 1);
    }

    private int shortestPath(int src, int desitnation) {
        int size = this.map.length;

        // declare some necessary tables to keeptrack
        int[] distanceTable = new int[size]; // keep track distance
        boolean[] visited = new boolean[size]; // keep track the visited place
        int[] previous = new int[size]; // keep track the parent

        // initialization step
        for (int i = 0; i < size; i++) {
            distanceTable[i] = Integer.MAX_VALUE;
            previous[i] = -1;
        }
        distanceTable[src] = 0; // zero distance from the src to itself

        while (true) {
            int shortestDistance = Integer.MAX_VALUE;
            int currentPlace = -1;// the currentPlace must be the place that currently has the shortest distance
                                  // to it, in the table
            for (int i = 0; i < size; i++) {
                if (visited[i]) {
                    continue;
                }
                if (shortestDistance > distanceTable[i]) {
                    shortestDistance = distanceTable[i];
                    currentPlace = i;
                }
            }

            // loop through the map to find the way to go
            for (int i = 0; i < size; i++) {
                if (visited[i]) {
                    continue;
                }
                // shortestNode and i are connected?
                if (this.map[currentPlace][i] > 0) {
                    // current distance to i > distance reached through shortestNode
                    if (distanceTable[i] > distanceTable[currentPlace] + this.map[currentPlace][i]) {
                        distanceTable[i] = distanceTable[currentPlace] + this.map[currentPlace][i];
                        previous[i] = currentPlace;
                    }
                }
            }

            if (currentPlace == desitnation) {
                // if we reach the RMIT
                String path = currentPlace + "";
                while (previous[currentPlace] != -1) {
                    currentPlace = previous[currentPlace];
                    path = currentPlace + " -> " + path;
                }

                System.out.println("Shortest path: " + path);
                return distanceTable[desitnation];
            }

            // cannot find the connection between places
            if (shortestDistance == Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            // continue the next round
            visited[currentPlace] = true;
        }
    }

    public static void main(String[] args) {
        TaiStreetNetwork street = new TaiStreetNetwork(
                new int[][] { { 0, -1, 5, 10 }, { -1, 0, 4, 2 }, { -1, 1, 0, 4 }, { 3, -1, 7, 0 } });

        System.out.println("Function 1:");
        System.out.println(street.nearestNeighbour());
        System.out.println("Function 2:");
        int shotestDistance = street.shortestToSchool();
        System.out.println("Shortest Distance: " + shotestDistance);

    }
}
