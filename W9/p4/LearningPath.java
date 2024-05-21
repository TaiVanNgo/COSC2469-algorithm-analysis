package W9.p4;

import W3.LinkedListQueue;

public class LearningPath {
    // courses = [
    // [0, 0, 0, 0],
    // [1, 0, 1, 0],
    // [0, 0, 0, 1],
    // [1, 0, 0, 0]
    // ]

    int[][] learningMap;
    String[] courseNames;
    int size;

    LearningPath(int[][] learningMap, String[] courseNames) {
        this.size = courseNames.length;
        this.learningMap = learningMap;
        this.courseNames = courseNames;
    }

    void bestPath() {
        // calculate the incoming courses for each course
        int[] prerequisteTable = new int[this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.learningMap[i][j] > 0) {
                    prerequisteTable[i]++;
                }
            }
        }

        boolean[] visited = new boolean[this.size];

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < this.size; i++) {
            if (prerequisteTable[i] == 0) {
                queue.enQueue(i);
                visited[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            int currentCourse = queue.peekFront();
            queue.deQqueue();

            System.out.print(courseNames[currentCourse] + " -> ");

            for (int i = 0; i < this.size; i++) {
                if (this.learningMap[i][currentCourse] > 0 && !visited[i]) {
                    // if find the course that has the presequise of the currentCourse
                    prerequisteTable[i]--;

                    if (prerequisteTable[i] == 0) {
                        queue.enQueue(i);
                        visited[i] = true;
                    }
                }
            }
        }

        // after while loop, we got the best learning path
    }

    public static void main(String[] args) {
        LearningPath learningPath = new LearningPath(
                new int[][] { { 0, 0, 0, 0 }, { 1, 0, 1, 0, }, { 0, 0, 0, 1 }, { 1, 0, 0, 0 } }, new String[] {
                        "Introduce to programming", "Fullstack Developmetn", "OOP", "Practical Database Concept" });

        learningPath.bestPath();
    }

}

class Course {
    String name;
    String previous;

    Course(String name, String previous) {
        this.name = name;
        this.previous = previous;
    }
}