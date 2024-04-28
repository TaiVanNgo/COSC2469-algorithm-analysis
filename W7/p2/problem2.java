package W7.p2;

public class problem2 {
    
}

class Graph{
    int[][] matrix;
    String[] label;
    int size;

    Graph(int size){
        this.size = size;
        this.matrix = new int[this.size][this.size];
        for(int i = 0; i < size; i ++){
            for(int j = 0; j < size; j++){
                matrix[i][j] = 0;
            }
        }

        this.label = new String[this.size];
    }

    public void setLabel(int index, String label){
        this.label[index] = label;
    }

    public void addEdge(int node1, int node2){
        matrix[node1][node2] = 1;
        matrix[node2][node1] = 1;
    }


}
