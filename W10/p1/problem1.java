package W10.p1;

public class problem1 {
    // There are N tasks. Each task has a start time and end time (both are
    // integers). What is the maximum number of tasks a person can finish if that
    // person cannot do two or more tasks at the same time?

    public static void main(String[] args) {
        Task[] tasks = new Task[] { new Task("task1", 4, 5),
                new Task("task2", 2, 6), new Task("task3", 1, 3),
                new Task("task4", 6, 7) };

        String res = countTasks(tasks);
        System.out.println(res);

    }

    public static String countTasks(Task[] tasks) {
        String res = "";
        // from the array of task, we find the number of tasks that the people can do in
        // a day

        // first, we need to sort a tasks
        sortTasks(tasks, 0, tasks.length - 1);

        Task current = tasks[0];// first we pick the first tasks
        res += current.name + " -> ";
        for (int i = 1; i < tasks.length; i++) {
            // go each tasks to check
            // first we must sort the tasks based on it finish time
            // if the next
            if (tasks[i].start_time >= current.end_time) {
                // we pick a new task if its start time is larger than the current endtime
                current = tasks[i];
                res += current.name + " -> ";
            }
        }

        return res;
    }

    private static void sortTasks(Task[] tasks, int left, int right) {
        // use quick sort
        if (left < right) {
            int partition = partition(tasks, left, right);
            sortTasks(tasks, left, partition);
            sortTasks(tasks, partition + 1, right);
        }
    }

    private static int partition(Task[] tasks, int left, int right) {
        // define front and back
        int front = left;
        int back = right;

        Task median = tasks[front];// assume that median is the tasks of positiont 'left'

        while (true) {
            // we sort based on it finish time.
            while (tasks[front].end_time < median.end_time) {
                front++;
            }
            while (tasks[back].end_time > median.end_time) {
                back--;
            }

            if (back <= front) {
                return back;
            }

            // swap
            Task temp = tasks[front];
            tasks[front] = tasks[back];
            tasks[back] = temp;

            front++;
            back--;
        }
    }

    static class Task {
        String name;
        int start_time;
        int end_time;

        public Task(String name, int start_time, int end_time) {
            this.name = name;
            this.start_time = start_time;
            this.end_time = end_time;
        }

    }

}
