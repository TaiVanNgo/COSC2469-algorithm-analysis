package W10.p1;

public class ActivitySelection {

    static int selectTasks(Task[] tasks) {
        // first we need to sort the tasks based on the end time
        MergeSort.mergeSort(tasks);
        // after sorting the tasks, we loop through the tasks and process

        Task currentTask = tasks[0];// pick the first task
        int count = 1;
        System.out.print(currentTask.name + " -> ");

        for (int i = 1; i < tasks.length; i++) {
            if (tasks[i].start > currentTask.end) {
                currentTask = tasks[i];// we pick this
                System.out.print(tasks[i].name + " -> ");
                count++;

            }
        }

        return count;
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[] { new Task("task1", 4, 5),
                new Task("task2", 2, 6), new Task("task3", 1, 3),
                new Task("task4", 6, 7) };

        System.out.println(
                selectTasks(tasks)

        );
    }
}

class Task {
    String name;
    int start;
    int end;

    Task(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
}

class MergeSort {
    static void mergeSort(Task[] tasks) {
        if (tasks.length > 1) {
            // find mid
            int mid = tasks.length / 2;

            // create the left sub array
            Task[] leftTasks = new Task[mid];
            for (int i = 0; i < mid; i++) {
                leftTasks[i] = tasks[i];
            }

            Task[] rightTasks = new Task[tasks.length - mid];

            for (int i = mid; i < tasks.length; i++) {
                rightTasks[i - mid] = tasks[i];
            }

            mergeSort(leftTasks);
            mergeSort(rightTasks);

            merge(leftTasks, rightTasks, tasks);
        }
    }

    static void merge(Task[] leftTasks, Task[] rightTasks, Task[] tasks) {
        int left = 0, right = 0, pointer = 0;

        while (left < leftTasks.length && right < rightTasks.length) {
            if (leftTasks[left].end < rightTasks[right].end) {
                tasks[pointer] = leftTasks[left];
                left++;
            } else {
                tasks[pointer] = rightTasks[right];
                right++;
            }
            pointer++;
        }

        while (left < leftTasks.length) {
            tasks[pointer] = leftTasks[left];
            left++;
            pointer++;
        }

        while (right < rightTasks.length) {
            tasks[pointer] = rightTasks[right];
            right++;
            pointer++;
        }
    }
}