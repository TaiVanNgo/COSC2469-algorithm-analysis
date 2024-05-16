package W10.p3;

import W3.ArrayStack;
import W3.LinkedList;
import W3.LinkedListQueue;

//Implement a program to solve the two water jugs problem.
public class problem3 {
    public static void main(String[] args) {
        ProcessStage stage = new ProcessStage(0, 0, "", null);
        (new WaterJugs()).findResult(stage, 5);
    }
}

class WaterJugs {
    public void findResult(ProcessStage initialStage, int target) {
        // first we create the boolean to keep track all the stages that we already
        // visit
        boolean[][] visited = new boolean[initialStage.MAX_FIRST + 1][initialStage.MAX_SECOND + 1];
        // why it + 1 ? the reason is we use base 0 for the array
        // for ex: if we have 2 jugs of 3,4. The array will have the size of
        // Boolean.length = 4, Boolean[0].length = 5.
        // we can not access the 4 and 5. it can only go up to visited [3][4]

        LinkedListQueue<ProcessStage> queue = new LinkedListQueue<>();
        queue.enQueue(initialStage);// enqueue the first stage.
        visited[initialStage.first][initialStage.second] = true;

        // run until the queue is empty
        while (!queue.isEmpty()) {
            // first we dequeue and process
            ProcessStage currentStage = queue.peekFront();
            queue.deQqueue();

            // is it our target?
            if (currentStage.first == target || currentStage.second == target) {
                showSolution(currentStage, initialStage);
                return;
            }

            LinkedList<ProcessStage> newStagesList = currentStage.generateStage();// from the current stage, we generate
                                                                                  // the list of all cases of its
                                                                                  // children
            newStagesList.reset();
            while (newStagesList.hasNext()) {
                // from this we go through the list, until we reach the end of the list
                // first check whether this stage we process or not
                ProcessStage current = newStagesList.next();
                if (visited[current.first][current.second]) {
                    continue;// skip
                }

                // if we haven't visited this stage yet, we push it in the queue, waiting for
                // processing
                queue.enQueue(current);
                visited[current.first][current.second] = true;
            }
        }

        // if we can access this code (the queue is empty and we still cannot find the
        // solution)
        System.out.println("There is no solution found!");
    }

    private void showSolution(ProcessStage currentStage, ProcessStage initalStage) {
        // we will go from the currentStage go up to the initialstage
        ProcessStage current = currentStage;

        // I use stack to show the result
        ArrayStack<ProcessStage> stack = new ArrayStack<>();
        while (!current.equals(initalStage)) {
            stack.push(current);
            current = current.parent;
        }

        // define the first stage
        System.out.println("Initial stage: " + initalStage.first + ", " + initalStage.second);
        // after push all the cases, take it out and print
        while (!stack.isEmpty()) {
            System.out.println(stack.peek().toString());
            stack.pop();
        }
    }
}

class ProcessStage {
    // the capacity of 2 jugs
    final int MAX_FIRST = 99;
    final int MAX_SECOND = 100;
    // the actual water that the two jugs store
    int first;
    int second;
    String how;// this string show how the first and second is reached
    ProcessStage parent;

    public ProcessStage(int first, int second, String how, ProcessStage parent) {
        this.first = first;
        this.second = second;
        this.how = how;
        this.parent = parent;
    }

    public LinkedList<ProcessStage> generateStage() {
        LinkedList<ProcessStage> result = new LinkedList<>();

        // *********** EMPTY ***********
        // empty first;
        if (this.first > 0) {
            // if the first jug store something (> 0), we empty it, create the new child for
            // it
            result.insertAt(result.size, new ProcessStage(0, this.second, "Empty first jugs", this));// add at the index
            // (result.size) means
            // that add to the end
            // of the list
            // the value of new child inserted to the list is first: 0, second: curernt
            // second value. The way to reach this is empty the first jugs, the child parent
            // is "this" the instance that call this function
        }

        // empty second
        if (this.second > 0) {
            result.insertAt(result.size, new ProcessStage(this.first, 0, "Empty second jugs", this));
        }

        // *********** FILL IN ***********

        // if first or second is smaller than the maximum capacity (still have space to
        // store water)->we fill to it
        if (this.first < this.MAX_FIRST) {
            result.insertAt(result.size,
                    new ProcessStage(this.MAX_FIRST, this.second, "Fill water to first jug", this));
        }

        // fill to the second jugs
        if (this.second < this.MAX_SECOND) {
            result.insertAt(result.size,
                    new ProcessStage(this.first, this.MAX_SECOND, "Fill water to second jug", this));
        }

        // *********** POUR WATER FROM FIRST TO SECOND AND VICE VERSA ***********
        // second jug pour to first jug (only do that if first still has enough space)
        if (this.first < MAX_FIRST && this.second > 0) {
            // after go to this code, we still need to consider whether if we pour the
            // second to first, will it exceed the maximum capacity of the first jug
            if (this.first + this.second <= this.MAX_FIRST) {
                // if it not exceed (the second will pour all to the first)
                result.insertAt(result.size, new ProcessStage(this.first + this.second,
                        0, "Pour water from 2nd jug to 1st jug", this));
            } else {
                // this case is when first + second exeed the max_first, the first jug wil be
                // come max
                result.insertAt(result.size, new ProcessStage(this.MAX_FIRST,
                        (this.first + this.second) - this.MAX_FIRST, "Pour water from 2nd jug to 1st jug", this));
            }
        }

        // Pour the firstjug to second jug
        if (this.second < MAX_SECOND && this.first > 0) {
            if (this.first + this.second <= this.MAX_SECOND) {
                result.insertAt(result.size,
                        new ProcessStage(0, this.first + this.second, "Pour water from 1st jug to 2nd jug", this));
            } else {
                result.insertAt(result.size, new ProcessStage((this.first + this.second) - this.MAX_SECOND,
                        this.MAX_SECOND, "Pour water from 1st jug to 2nd jug", this));
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return this.how + " to reach: " + this.first + ", " + this.second;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ProcessStage)) {
            return false;
        }
        ProcessStage otherState = (ProcessStage) other;
        return (first == otherState.first && second == otherState.second);
    }
}
