package W7.p2;

import W3.ArrayQueue;

public class problem2 {
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.setLabel(0, "A");
        graph.setLabel(1, "B");
        graph.setLabel(2, "C");
        graph.setLabel(3, "D");
        graph.setLabel(4, "E");
        graph.setLabel(5, "F");

        // A -> B C D
        graph.connect(0, 1);
        graph.connect(0, 2);
        graph.connect(0, 3);

        // B --> C E
        graph.connect(1, 2);
        graph.connect(1, 4);

        // C --> E
        graph.connect(2, 3);
        graph.connect(2, 4);
        graph.connect(2, 5);

        // D -->F
        graph.connect(3, 5);

        graph.DFS();
        graph.BFS();
    }
}

class Graph {
    int[][] graph;
    String[] label;
    int size;

    public Graph(int size) {
        this.size = size;
        this.graph = new int[size][size];
        label = new String[size];

        // create the inital values for the graph
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.graph[i][j] = 0;
            }
        }
    }

    public void connect(int node1, int node2) {
        // connect two nodes

        // since this is the undirected graph, so that we set the node in both sides.
        // A connect to B means B connect to A
        this.graph[node1][node2] = 1;
        this.graph[node2][node1] = 1;

    }

    public void setLabel(int index, String label) {
        this.label[index] = label;
    }

    public void DFS() {
        // this is depth-first search
        System.out.println("Depth-First Serach/Traversal!");
        // create the boolean array to check the visited nodes
        Boolean[] visited = new Boolean[this.size];
        for (int i = 0; i < this.size; i++) {
            visited[i] = false;
        }

        // start from A(or 0)
        DFSRecursive(0, visited);
    }

    private void DFSRecursive(int index, Boolean[] visited) {
        // base case. If the node at "index" is visited -> return
        if (visited[index]) {
            return;
        }

        // if the node is not visited, set the node is visted
        System.out.println("Visited: " + this.label[index]);
        visited[index] = true;
        // we perform the visited
        for (int i = 0; i < this.size; i++) {
            if (this.graph[index][i] == 1 && !visited[i]) {
                // from the current node, we go through the size to find the UNVISITED NODE
                // for ex: start from A.
                // the graph will be
                // --A B C D E F
                // A 0 1 1 1 0 0
                // B 1 0 1 0 1 0
                // C
                // ...
                // if we at A, we will go to B (if B is not visited)
                DFSRecursive(i, visited);
            }
        }
    }

    public void BFS() {
        System.out.println("Breadth-First Search/Traversal!");
        Boolean[] visited = new Boolean[this.size];
        for (int i = 0; i < this.size; i++) {
            visited[i] = false;
        }

        ArrayQueue<Integer> queue = new ArrayQueue<>();
        // create the queue

        // import the first node. (assume the first node is 0(or "A"))
        visited[0] = true;
        queue.enQueue(0);

        while (!queue.isEmpty()) {
            // run until the queue is empty

            // currentNOde is the node we are currently visiitng.
            // pick from the queue, then dequeue it from the queue
            int currentNode = queue.peekFront();
            queue.deQueue();
            System.out.println("Visited: " + this.label[currentNode]);

            for (int i = 0; i < this.size; i++) {
                if (this.graph[currentNode][i] == 1 && !visited[i]) {
                    // if the connection from the currentNode to the index i(go from 0 to size) is 1
                    // it is connected
                    // AND the node i havne't visited yet.
                    // We put this unvisited node into the queue and continue the whileloop
                    queue.enQueue(i);
                    visited[i] = true;
                }
            }
        }
    }

}
