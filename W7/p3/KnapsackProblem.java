package W7.p3;

class KnapsackProblem {

    public static void main(String[] args) {
        Item[] items = new Item[] {
                new Item(7, 42),
                new Item(3, 12),
                new Item(4, 40),
                new Item(5, 25),
        };

        KnapsackProblem problem = new KnapsackProblem(items, 7);
        problem.findBestSubset();
        problem.displayResult();
    }

    Item[] items;
    int maxWeight;
    int size;
    int bestValue;
    boolean[] bestSubset;

    KnapsackProblem(Item[] items, int maxWeight) {
        this.items = items;
        this.maxWeight = maxWeight;
        this.size = items.length;
        this.bestValue = 0;
        this.bestSubset = new boolean[this.size];
    }

    void findBestSubset() {
        // first if the index i
        subset(0, new boolean[this.size]);
    }

    private void subset(int index, boolean[] take) {
        if (index == this.size) {
            // process it
            process(index, take);
            return;
        }

        // if we take it?
        take[index] = true;
        subset(index + 1, take);

        // if we don't take it?
        take[index] = false;
        subset(index + 1, take);
    }

    private void process(int index, boolean[] take) {
        int weight = 0, value = 0;// this variables keep track the weight and value of current sbuset

        for (int i = 0; i < this.size; i++) {
            if (take[i]) {
                weight += this.items[i].weight;
                value += this.items[i].value;
            }
            // check if the weight exceeds the knap sack capacity -> return
            if (weight > this.maxWeight) {
                return;
            }
        }

        // after this process we compare with the current best value
        if (value >= this.bestValue) {
            // we updated the best subset and the best value
            this.bestValue = value;
            this.bestSubset = take.clone();
        }
    }

    void displayResult() {
        for (int i = 0; i < this.bestSubset.length; i++) {
            if (bestSubset[i]) {
                System.out.println("Weight: " + this.items[i].weight + " Value:" + this.items[i].value);
            }
        }
        System.out.println("Best value: " + this.bestValue);
    }
}

class Item {
    int weight;
    int value;

    Item(int weight, int value) {
        this.value = value;
        this.weight = weight;
    }

}