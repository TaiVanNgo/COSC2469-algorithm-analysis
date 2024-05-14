package W9.p4;

import W3.LinkedListQueue;

/*
courses = [
    [0, 0, 0, 0],
    [1, 0, 1, 0],
    [0, 0, 0, 1],
    [1, 0, 0, 0]
]


*/
public class problem4 {
    public static void main(String[] args) {
        Program program = new Program(4,
                new String[] { "Introduction to programming", "Fullstack", "OOP", "Database Concept" });

        program.addPrerequisite(1, 0);
        program.addPrerequisite(1, 2);
        program.addPrerequisite(2, 3);
        program.addPrerequisite(3, 0);

        System.out.println(program.learningPath());
    }
}

class Program {
    int[][] map;
    String[] courseName;
    int SIZE;

    public Program(int size, String[] courseName) {
        this.SIZE = size;
        map = new int[SIZE][SIZE];
        this.courseName = new String[SIZE];

        for (int i = 0; i < courseName.length; i++) {
            this.courseName[i] = courseName[i];
        }
    }

    public void addPrerequisite(int i, int j) {
        // the course at "i" need the course at "j"
        // OR:
        // we need to learn the code at j before the course at i
        map[i][j] = 1;
    }

    public String learningPath() {
        String learningPath = "";
        Boolean[] visited = new Boolean[this.SIZE];
        // this array of boolean is used to check the node is visted or not
        // mark all the visited is false
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        // create an empty queue
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        // create the indegree for all courses (incomming past)
        // or we can describe that calculate all the presiquite courses of each course
        int[] prerequisite = new int[this.SIZE];
        for (int i = 0; i < this.SIZE; i++) {
            for (int j = 0; j < this.SIZE; j++) {
                if (map[i][j] == 1) {
                    prerequisite[i]++;
                }
            }
        }

        for (int i = 0; i < this.SIZE; i++) {
            // we go through each course first.
            // we find the course that have no prerequesite
            if (prerequisite[i] == 0) {
                // if we find it, we push it in the queue
                queue.enQueue(i);
                visited[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.peekFront();// get the first elemetn out
            learningPath += (courseName[course] + " -> ");// push it to the road map
            queue.deQqueue();// remove the first elemetn of the queue

            for (int i = 0; i < this.SIZE; i++) {
                if (map[i][course] == 1 && !visited[i]) {
                    prerequisite[i]--;
                    if (prerequisite[i] == 0) {
                        queue.enQueue(i);
                        visited[i] = true;
                    }
                }
            }
        }

        return learningPath;

    }
}
