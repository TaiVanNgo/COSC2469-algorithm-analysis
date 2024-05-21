package W7.p2;

import W3.LinkedListQueue;

class AdjacencyMatrix {
    int[][] matrix;
    String[] label;
    int size;

    AdjacencyMatrix(int size) {
        this.size = size;
        this.matrix = new int[this.size][this.size];

        this.label = new String[this.size];
    }

    void setLabel(String[] lable) {
        for (int i = 0; i < this.size; i++) {
            this.label[i] = lable[i];
        }
    }

    void connect(int index1, int index2) {
        // connect the indexes
        this.matrix[index1][index2] = 1;
        this.matrix[index2][index1] = 1;
    }

    public void DFS() {
        boolean[] visited = new boolean[this.size];

        DFS(0, visited);
    }

    private void DFS(int index, boolean[] visited) {
        if (visited[index]) {
            return;
        }
        System.out.println("Visited: " + this.label[index]);
        // set the visit to true
        visited[index] = true;

        for (int i = 0; i < this.size; i++) {
            if (this.matrix[index][i] == 1 && !visited[i]) {
                // if there is connection between index and i, and the node i havne't visited
                // yet, we process it
                DFS(i, visited);
            }
        }
    }

    public void BFS() {
        // set up
        boolean[] visited = new boolean[this.size];
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        // push the first node to the queue
        queue.enQueue(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.peekFront();
            queue.deQqueue();

            System.out.println("Visited: " + this.label[currentNode]);
            for (int i = 0; i < this.size; i++) {
                // go from 0 up to the size
                if (this.matrix[currentNode][i] == 1 && !visited[i]) {
                    // if there is a connection between teh current node to the i, and the i havne't
                    // visited yet => we process it
                    queue.enQueue(i);
                    visited[i] = true;
                }
            }
        }

    }

    public static void main(String[] args) {
        AdjacencyMatrix matrix = new AdjacencyMatrix(6);
        matrix.connect(0, 1);
        matrix.connect(0, 2);
        matrix.connect(0, 3);

        matrix.connect(1, 2);
        matrix.connect(1, 4);

        matrix.connect(2, 4);
        matrix.connect(2, 5);
        matrix.connect(2, 3);

        matrix.connect(3, 5);

        matrix.setLabel(new String[] { "A", "B", "C", "D", "E", "F", "G" });
        System.out.println("Depth First Search: ");
        matrix.DFS();

        AdjacencyMatrix matrix2 = new AdjacencyMatrix(6);
        matrix2.connect(0, 1);
        matrix2.connect(0, 2);
        matrix2.connect(0, 3);

        matrix2.connect(1, 2);
        matrix2.connect(1, 4);

        matrix2.connect(2, 4);
        matrix2.connect(2, 5);
        matrix2.connect(2, 3);

        matrix2.connect(3, 5);

        matrix2.setLabel(new String[] { "A", "B", "C", "D", "E", "F", "G" });
        System.out.println("Breadth First Search: ");
        matrix2.BFS();

    }
}