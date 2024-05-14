package W10.p2;

public class problem2 {
    // Sort an array of integers using Heap sort.
    public static void main(String[] args) {
        int[] arr = new int[] { 0, 7, 1, 3, 6, 9, 10, 12 };
        System.out.println("The array before Heap Sort: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        // build the tree from an array
        BinaryHeaps tree = new BinaryHeaps();
        tree.buildTree(arr);

        for (int i = arr.length - 1; i >= 0; i--) {
            // we will go from the right to the left, since the tree will extract the
            // biggest array to the smallest array.
            arr[i] = tree.extractmax();
        }

        System.out.println("The array after Heap Sort: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }

        // test insert the value to the tree
        BinaryHeaps tree2 = new BinaryHeaps();
        int[] arrInsertTest = new int[] { 0, 7, 1, 3, 6, 9, 10, 12 };
        for (int i : arrInsertTest) {
            tree2.insert(i);
        }

        for (int i = arrInsertTest.length - 1; i >= 0; i--) {
            // we will go from the right to the left, since the tree will extract the
            // biggest array to the smallest array.
            arrInsertTest[i] = tree2.extractmax();
        }
        System.out.println();
        for (int i : arr) {
            System.out.print(i + " ");
        }

    }

}

class BinaryHeaps {
    int MAX_SIZE = 1000;// the max size of the heaps tree is 1000
    int[] heaps;
    int size;// this is the actually size of the heaps tree if it is created

    public BinaryHeaps() {
        this.heaps = new int[this.MAX_SIZE];
        this.size = 0;
    }

    public void buildTree(int[] arr) {
        this.size = arr.length; // copy the size of the arary to the tree

        // copy all the element of the array to the tree
        for (int i = 0; i < this.size; i++) {
            this.heaps[i] = arr[i];
        }

        // heapify the tree
        // go from the middle of the tree to the first one. Since we don't need to check
        // the leaf nodes.
        for (int i = this.size / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    private void heapify(int nodeIdx) {
        int biggestNodeIdx = nodeIdx;// this variable store the index of the biggest node.(at moment, I set it to the
                                     // nodeIdx)

        // the posiiton of the left node of node is nodeIdx * 2 + 1. For the right is
        // nodeIdx * 2 + 2;
        int leftIdx = nodeIdx * 2 + 1;
        int rightIdx = nodeIdx * 2 + 2;

        // we compare the node with its left and right children
        // with the left first
        if (leftIdx < this.size && heaps[leftIdx] > heaps[biggestNodeIdx]) {
            // if the left is biger than the current biggest
            biggestNodeIdx = leftIdx;
        }

        if (rightIdx < this.size && heaps[rightIdx] > heaps[biggestNodeIdx]) {
            biggestNodeIdx = rightIdx;
        }

        // if after the two comparisons above, the current NodeIdx still the biggest, we
        // will return without swapping value
        if (biggestNodeIdx == nodeIdx) {
            return;
        }

        // if it can reach this case, the biggest index is updated to the left or right
        // of the current case.
        // we will swap it with the nodeIdx

        int temp = heaps[nodeIdx];
        heaps[nodeIdx] = heaps[biggestNodeIdx];
        heaps[biggestNodeIdx] = temp;

        // heapify it again to check that is there any children below the index of
        // biggest node?.
        // AT this time, the biggestNodeIdx will have the value of the nodeIdx (which is
        // the node that we are processing)
        heapify(biggestNodeIdx);
    }

    public int extractmax() {
        // the esxtract max will remove the root of the tree.
        // which is the first position

        // first we keep the value of the tree's root
        int temp = heaps[0];

        // then, we take the last node of the tree, and store it to the root, to don't
        // make the tree lost its shape
        heaps[0] = heaps[size - 1];
        size--;// decrease the size of the tree

        // then we heapify the tree again with the new root, to make sure that the tree
        // is not violate its properties
        heapify(0);

        // return the root that we want to extract
        return temp;
    }

    public void insert(int node) {
        // we increase the size of the tree first
        this.size++;
        heaps[this.size - 1] = node;// we put the new node to the last node of the tree

        // we determine the indexes current node and the parent node
        int currentIdx = this.size - 1;
        int parentIdx = (currentIdx - 1) / 2;

        while (heaps[currentIdx] > heaps[parentIdx] && currentIdx > 0) {
            // we will run this while loop whnever the current index value is greater than
            // its parent value

            // SWAP
            int temp = heaps[currentIdx];
            heaps[currentIdx] = heaps[parentIdx];
            heaps[parentIdx] = temp;

            // update the position
            currentIdx = parentIdx;
            parentIdx = (currentIdx - 1) / 2;
        }
    }
}