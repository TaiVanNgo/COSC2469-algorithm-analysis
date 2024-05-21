package W11.p1;

public class problem1 {
    // [0, 3, 2, 0]
    // [3, 0, 0, 5],
    // [2, 0, 0, 9]
    // [0, 5, 9, 0]

    public static void main(String[] args) {
        int[][] map = new int[][] {
                { 0, 3, 2, 0 }, { 3, 0, 0, 5 }, { 2, 0, 0, 9 }, { 0, 5, 9, 0 }
        };

        findShortestPath(map, 0, 3);
    }

    public static void findShortestPath(int[][] map, int source, int destination) {
        // Dijkstra's Algorithm
        // first, decalre the distance from all the current place
        int size = map.length;

        int[] distance = new int[size];
        // Initally, make it infiny
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        // set the distance to the source is 0
        distance[source] = 0;

        boolean[] visited = new boolean[map.length];

        int[] parent = new int[map.length];// keep track the parent
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

            // after determine the shortest node we use
            // we go through the map to calculate the distance to each neighbor through the
            // current shortest node
            for (int i = 0; i < size; i++) {
                if (map[currentNode][i] > 0) {
                    // if the distacne from the currentNode to the i is > 0
                    // --> there is the connection between them
                    if (map[currentNode][i] + distance[currentNode] < distance[i]) {
                        // if we can find the distance to the i smaller than the current distance saved
                        // to the table
                        // update the distance table
                        distance[i] = map[currentNode][i] + distance[currentNode];
                        // update the parent of that node
                        parent[i] = currentNode;
                    }
                }
            }

            if (currentNode == destination) {// we use stack to keep track these thing
                // if our current node is the destination. We print it
                // first define the parent of the current node (current node in this if
                // statement is the desitnation already)
                String path = currentNode + "";
                while(parent[currentNode] != -1){
                    currentNode = parent[currentNode];
                    
                    //update 
                    path = currentNode + " -> " + path;
                }

                System.out.println(path);
                System.out.println(distance[destination]);
                return;
            }

            if (shortest == Integer.MAX_VALUE) {
                // we can not find the connection to go
                return;
            }
            // update hte visited
            visited[currentNode] = true;
        }

    }
}