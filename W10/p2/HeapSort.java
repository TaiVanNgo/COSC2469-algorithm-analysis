package W10.p2;

public class HeapSort {
    static int SIZE = 1000;
    int[] heaps;
    int size;

    HeapSort() {
        this.heaps = new int[SIZE];
        this.size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    void buildTree(int[] arr) {
        this.size = arr.length;
        for (int i = 0; i < this.size; i++) {
            heaps[i] = arr[i];
        }

        // go from mid to the top
        int mid = this.size / 2;
        for (int i = mid; i >= 0; i--) {
            heapify(i);
        }
    }

    void heapify(int nodeIdx) {
        // check with its children, the parent must larger than its children
        int leftIdx = (nodeIdx * 2) + 1;
        int rightIdx = (nodeIdx * 2) + 2;
        int biggestValueIdx = nodeIdx;

        // now we compare with its children
        if (leftIdx < this.size && heaps[leftIdx] > heaps[biggestValueIdx]) {
            // swap this left child with its parent
            biggestValueIdx = leftIdx;
        }

        if (rightIdx < this.size && heaps[rightIdx] > heaps[biggestValueIdx]) {
            biggestValueIdx = rightIdx;
        }

        if (biggestValueIdx == nodeIdx) {
            return;
        }

        // swap the current node with the biggest value index
        int temp = heaps[biggestValueIdx];
        heaps[biggestValueIdx] = heaps[nodeIdx];
        heaps[nodeIdx] = temp;

        heapify(biggestValueIdx);
    }

    int extractMax() {
        int max = heaps[0];

        heaps[0] = heaps[this.size - 1];
        this.size--;

        heapify(0);

        return max;
    }

    void insert(int node) {
        // insert to the end of the tree
        this.size++;

        this.heaps[size - 1] = node;// assign the new node to the last element of the heap
        int currentNodeIdx = size - 1;
        // find the new node parent
        int parentIdx = (currentNodeIdx - 1) / 2;

        while (heaps[currentNodeIdx] > heaps[parentIdx] && currentNodeIdx > 0) {
            // run this while loop when the currentNode value is > its parent value
            // swap time
            int temp = heaps[currentNodeIdx];
            heaps[currentNodeIdx] = heaps[parentIdx];
            heaps[parentIdx] = temp;

            currentNodeIdx = parentIdx;
            parentIdx = (currentNodeIdx - 1) / 2;
        }
    }

    public static void main(String[] args) {
        // HeapSort heapSort = new HeapSort();
        // int[] arr = new int[] { 3, 7, 6, 4, 9, 8 };

        // heapSort.buildTree(arr);
        // while (!heapSort.isEmpty()) {
        // System.out.println(heapSort.extractMax());
        // }

        HeapSort heapSort2 = new HeapSort();
        heapSort2.insert(3);
        heapSort2.insert(7);
        heapSort2.insert(6);
        heapSort2.insert(4);
        heapSort2.insert(9);
        heapSort2.insert(8);

        while (!heapSort2.isEmpty()) {
            System.out.println(heapSort2.extractMax());
        }

    }
}
