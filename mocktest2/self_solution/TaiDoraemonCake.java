package mocktest2.self_solution;

public class TaiDoraemonCake {

    Topic[] topic;
    double A;// surface area of the cake

    TaiDoraemonCake(Topic[] topic, double A) {
        this.topic = topic;
        this.A = A;
    }

    // MAIN FUNCTION

    // time complexity: sort the array (worse case): O(N^2) + the while loop go
    // through the X
    // ==> Time complexity: O(N^2) + O(X) ==> O(N^2)
    public double weightByNumber(int X) {
        // find the X best largest weights
        // sort the array and then find it

        // copy the topic first
        Topic[] topicCopy = this.topic.clone();

        // quick sort
        quickSort(topicCopy, 0, this.topic.length - 1);
        // sort the array first
        // after sort, pick the last elements of the arrya

        double res = 0;
        int pointer = topicCopy.length - 1;
        while (X > 0) {
            res += topicCopy[pointer].W;
            pointer--;
            X--;
        }

        return res;
    }
    //time complexity O(2^N)
    double largestWeight() {
        boolean[] bestChoice = new boolean[this.topic.length];
        // subset approach
        bestChoice = this.subset(new boolean[this.topic.length], 0, bestChoice);

        System.out.print("Topic positon that we should choose: " );
        for (int i = 0; i < bestChoice.length; i++) {
            if (bestChoice[i]) {
                System.out.print(i + " ");
            }
        }

        return this.CalculateWeight(bestChoice);
    }

    public static void main(String[] args) {
        TaiDoraemonCake doraemonCake = new TaiDoraemonCake(
                new Topic[] { new Topic(8, 7), new Topic(10, 8), new Topic(5, 3) }, 10);

        System.out.print("The largest weight that we can achieve when the nubmer of topic is 2 is: ");
        System.out.println(doraemonCake.weightByNumber(2));


        double res = doraemonCake.largestWeight();
        System.out.println("\nLargest Weight: " + res);
    }

    // ADDITION FUCNTION
    private void quickSort(Topic[] topic, int left, int right) {
        if (left < right) {
            int p = partition(topic, left, right);
            quickSort(topic, left, p - 1);
            quickSort(topic, p + 1, right);

        }
    }

    private int partition(Topic[] topic, int left, int right) {
        Topic pivot = topic[right];
        int i = left;

        for (int j = left; j < right; j++) {
            // if the j is smaller than the pivot
            if (topic[j].W < pivot.W) {
                // SWAP
                Topic temp = topic[j];
                topic[j] = topic[i];
                topic[i] = temp;

                i++;
            }
        }

        // SWAP (with the pivot) pivot position is right
        Topic temp = topic[i];
        topic[i] = topic[right];
        topic[right] = temp;

        return i;
    }

    private double CalculateWeight(boolean[] selected) {
        double res = 0;
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                res += this.topic[i].W;
            }
        }
        return res;
    }

    private boolean[] subset(boolean[] select, int index, boolean[] bestCase) {
        if (index == this.topic.length) {
            // process
            bestCase = process(select, bestCase);

            return bestCase;
        }

        // choose index
        select[index] = false;
        bestCase = subset(select, index + 1, bestCase);

        // not choose index
        select[index] = true;
        bestCase = subset(select, index + 1, bestCase);

        return bestCase;
    }

    private boolean[] process(boolean[] select, boolean[] bestCase) {
        double w = 0, s = 0;

        // go through the current selected list.
        for (int i = 0; i < select.length; i++) {
            if (select[i]) {
                w += this.topic[i].W;
                s += this.topic[i].S;

                if (s > this.A) {
                    // if the s of current selected list exceed the surface of cake ==> return
                    return bestCase;
                }
            }
        }

        // find the weight of best case
        double bestWeight = this.CalculateWeight(bestCase);

        if (w > bestWeight) {
            // update the best case
            bestCase = select.clone();
        }

        return bestCase;
    }
}

class Topic {
    double W;// weight
    double S; // printed surface area

    Topic(double W, double S) {
        this.W = W;
        this.S = S;
    }
}
