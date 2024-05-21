package mocktest2.forth_attempt;

public class TaiAlgorithmTraveler {
    int[][] cost;
    int M;

    TaiAlgorithmTraveler(int[][] cost, int M) {
        this.cost = cost;
        this.M = M;
    }

    public int cheapestCity() {
        int cheapestCost = Integer.MAX_VALUE;
        int cheapestCity = -1;

        for (int i = 1; i < this.cost.length; i++) {
            int currentCost = shortestPath(0, i);
            if (currentCost < cheapestCost) {
                cheapestCost = currentCost;
                cheapestCity = i;
            }
        }

        return cheapestCity;
    }

    public int[] costQueries(int[] targetCities) {
        int[] res = new int[targetCities.length];

        for (int i = 0; i < targetCities.length; i++) {
            res[i] = shortestPath(0, targetCities[i]);
        }

        return res;
    }

    public int maxVisited() {
        int maxCities = 0;
        int totalCost = 0;
        for (int i = 1; i < this.cost.length; i++) {
            int currentVisitCost = shortestPath(0, i);

            if (totalCost < this.M && currentVisitCost <= this.M) {
                maxCities++;
                totalCost += currentVisitCost;
            }
        }
        return maxCities;
    }

    private int shortestPath(int src, int dest) {
        int size = this.cost.length;

        int[] distanceTable = new int[size]; // distance[i] stores the minimum distance from src to i
        boolean[] visited = new boolean[size]; // visited state
        int[] previous = new int[size]; // used to construct the shortest path; previous[i] stores the node that is
                                        // visited before i

        // initialization
        for (int i = 0; i < size; i++) {
            distanceTable[i] = Integer.MAX_VALUE;
            previous[i] = -1;
        }
        distanceTable[src] = 0; // zero distance from the src to itself

        while (true) {
            // Greedy choice: retrieve the shortest-distance node from
            // unvisited nodes
            int shortest = Integer.MAX_VALUE;
            int currentNode = -1;
            for (int i = 0; i < size; i++) {
                if (visited[i]) {
                    continue;
                }
                if (shortest > distanceTable[i]) {
                    shortest = distanceTable[i];
                    currentNode = i;
                }
            }

            // update the shortest distance through shortest node
            // to all unvisited nodes
            for (int i = 0; i < size; i++) {
                if (visited[i]) {
                    continue;
                }
                // shortestNode and i are connected?
                if (this.cost[currentNode][i] > 0) {
                    // current distance to i > distance reached through shortestNode
                    if (distanceTable[i] > distanceTable[currentNode] + this.cost[currentNode][i]) {
                        distanceTable[i] = distanceTable[currentNode] + this.cost[currentNode][i];
                        previous[i] = currentNode;
                    }
                }
            }

            if (currentNode == dest) {
                // we reach the destination
                // display the shortest path
                // String path = currentNode + "";
                // while (previous[currentNode] != -1) {
                // currentNode = previous[currentNode];
                // path = currentNode + " -> " + path;
                // }

                // System.out.println("Shortest path: " + path);
                return distanceTable[dest];
            }

            // even shortest is INFINITY => stop
            if (shortest == Integer.MAX_VALUE) {
                // we cannot go further
                return Integer.MAX_VALUE;
            }
            // continue the next round
            visited[currentNode] = true;
        }
    }

    public static void main(String[] args) {
        TaiAlgorithmTraveler traveler = new TaiAlgorithmTraveler(
                new int[][] { { -1, 3, 10, 15 }, { 5, -1, 8, 5 }, { 6, 4, -1, 2 }, { 7, 3, 1, -1 } }, 22);

        System.out.println(traveler.cheapestCity());
        int[] res = traveler.costQueries(new int[] { 1, 2 });
        for (int i : res) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(traveler.maxVisited());
    }
}
