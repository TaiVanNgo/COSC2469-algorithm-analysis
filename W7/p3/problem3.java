package W7.p3;

public class problem3 {

    // Given an array V storing the values of N items, an array W storing the
    // weights of N items, and a knapsack capacity C. Calculate and return the
    // subset of N items that has the highest sum value, and its total weight does
    // not exceed C.
    Item[] items;
    Boolean[] bestCase;
    int maxWeight;
    int bestValue = 0;

    public problem3(Item[] items, int maxWeight) {
            this.items = items;
            this.bestCase = new Boolean[items.length];
            this.maxWeight = maxWeight;
        }

    public void start() {
        this.subset(items, new Boolean[bestCase.length], 0);
    }

    private void subset(Item[] items, Boolean[] selected, int index) {
        // base case if the index is equal the length of items. we do some process and
        // return
        if (index == items.length) {
            process(items, selected);
            return;
        }

        // if we select
        selected[index] = true;
        subset(items, selected, index + 1);

        // if we not select
        selected[index] = false;
        subset(items, selected, index + 1);
    }

    private void process(Item[] items, Boolean[] selected) {
        int weight = 0, value = 0;
        for (int i = 0; i < items.length; i++) {
            if (selected[i]) {
                weight += items[i].weight;
                value += items[i].value;
            }

            if (weight > this.maxWeight) {
                return;// if the weihgt is currently exceed the max weight, we return the function
            }
        }
        // after the loop, we check the best value
        if (value >= this.bestValue) {

            bestValue = value;
            bestCase = selected.clone();
        }
    }

    public void displayResult() {
        System.out.println("The combination of items that have the best value and its weight not exceed "
                + this.maxWeight + " are: ");
        int bestWeight = 0;
        for (int i = 0; i < items.length; i++) {
            if (bestCase[i]) {
                bestWeight += items[i].weight;
                System.out.println("Item: Weight: " + items[i].weight + " Value: " + items[i].value);
            }
        }
        System.out.println("Total weight: " + bestWeight);
        System.out.println("Total value: " + this.bestValue);
    }

    public static void main(String[] args) {
        Item[] items = new Item[] {
                new Item(7, 42),
                new Item(3, 12),
                new Item(4, 40),
                new Item(5, 25),
        };

        problem3 knapsackProblem = new problem3(items, 7);

        knapsackProblem.start();
        knapsackProblem.displayResult();
    }
}

class Item {
    int weight;
    int value;

    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}