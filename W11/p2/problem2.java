package W11.p2;

public class problem2 {
    public static void main(String[] args) {
        // castles = [
        // [0, 1, 2, 8]
        // [1, 0, 3, 5],
        // [2, 3, 0, 4]
        // [8, 5, 4, 0]
        // ]

        System.out.println("The short path to build the road to all castles is: ");
        int res = buildRoadSystem(new int[][] { { 0, 1, 2, 8 }, { 1, 0, 3, 5 }, { 2, 3, 0, 4 }, { 8, 5, 4, 0 } });
        System.out.println(res);
    }

    // PRIM'S Algorithm
    static int buildRoadSystem(int[][] map) {
        int size = map.length;
        int length = 0;// the length that we will return

        // define the distance of all nodes
        int[] distanceArr = new int[size];
        // intially, all the distance of all nodes are infinity
        for (int i = 0; i < size; i++) {
            distanceArr[i] = Integer.MAX_VALUE;
        }

        // we pick the first node is the start point
        distanceArr[0] = 0;

        // we use the spinning tree to perform this search
        // we need to keep track the node is added to the tree or not?
        boolean[] added = new boolean[size];

        int nodeCount = 0;// we use this to know that, if we proces all the node-> we stop the loop

        int[] parent = new int[size];
        for(int i = 0; i  < size; i++){
            parent[i] = -1;
        }
        
        while (nodeCount < size) {
            // first, we go through the distance table to choose the node that have the
            // shortest path in the table
            int shortestDistance = Integer.MAX_VALUE;
            int currentNode = -1;// the current node that we are processing
            for (int i = 0; i < size; i++) {
                if (added[i]) {
                    continue;// if the node is already added to the tree -> we skip it
                }
                if (distanceArr[i] < shortestDistance) {
                    // if we find the distance to the array that smaller than the the current
                    // shortestDistance, we update the shortestDistance and keep track the current
                    // processing node
                    shortestDistance = distanceArr[i];
                    currentNode = i;
                }
            }

            // after this loop, we must find the currentnode
            // if the shortesDistance is still infinity (MAX_VALUE), that mean we cannot
            // find the connection between these nodes,we end the while loop
            if (shortestDistance == Integer.MAX_VALUE) {
                return shortestDistance;
            }

            // if not, we add the processing node to the tree and continuing process
            added[currentNode] = true;
            // update the length and counting
            length += distanceArr[currentNode];
            nodeCount++;

            for (int i = 0; i < size; i++) {
                // We only work with the node that haven't added to the tree, if we find the
                // node is already added to the tree, we simply skip it
                if (added[i]) {
                    continue;
                }

                // if we find the path to the another castle
                if (map[currentNode][i] > 0) {
                    // compare that, if the distance to that node is smaller than the current
                    // distance to that node, if yes, we updated the table, if not we skip
                    if (map[currentNode][i] < distanceArr[i]) {
                        distanceArr[i] = map[currentNode][i];
                        parent[i] = currentNode;
                    }
                }
            }
        }
    
        // we return the result after the big while loop
        return length;
    }
}
