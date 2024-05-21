package W9.p4;

import W3.LinkedListQueue;

public class problem4_2ndapproach {
    public static void main(String[] args) {
        int[][] map = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 0, 1, 0 },
                { 0, 0, 0, 1 },
                { 1, 0, 0, 0 }
        };

        String[] courseName = new String[] {
                "Introduction to Programming", "Fullstack Developent", "OOP", "Pracitcal Database Concept"
        };

        String learningPath = LearningPath(map, courseName);
        System.out.println(learningPath);
        
    }

    static String LearningPath(int[][] map, String[] courseName) {
        String roadMap = "";
        int count = 0;

        // create instance for courses
        Course[] courses = new Course[map.length];
        for (int i = 0; i < map.length; i++) {
            courses[i] = new Course(courseName[i], i);

            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 1) {
                    // increase the prerequiste if find the another course need the "i" course
                    courses[i].increasePrerequiste();
                }
            }
        }

        // Create the queue
        LinkedListQueue<Course> queue = new LinkedListQueue<>();
        // find the course that have no prerequiste
        for (int i = 0; i < courses.length; i++) {
            if (courses[i].isEmptyPrerequisite()) {
                queue.enQueue(courses[i]);
                courses[i].visited = true;
            }
        }

        // run this loop until the quque is empty
        while (!queue.isEmpty()) {
            // peek the first course out
            Course currentCourse = queue.peekFront();
            queue.deQqueue();
            // update the roadmap
            roadMap += (currentCourse.name + " -> ");
            count++;//update the count to check is asnwer this possible or not

            for (int i = 0; i < courses.length; i++) {
                if (map[i][currentCourse.index] == 1 && !courses[i].isVisited()) {
                    // go to each course to find is there any course have the prerequiste of
                    // currentcourse
                    courses[i].decreasePrerequiste();

                    if(courses[i].isEmptyPrerequisite()){
                        //if the courses have the prerequiste number is 0. we add it to the queue
                        queue.enQueue(courses[i]);
                        courses[i].visited = true;
                    }
                }
            }
        }

        if(count < map.length){
            return "IMPOSSIBLE!";
        }

        return roadMap;
    }

    static class Course {
        private String name;
        int index;
        private int prerequisite;
        private Boolean visited;

        Course(String name, int index) {
            this.name = name;
            this.index = index;
            this.prerequisite = 0;
            this.visited = false;
        }

        public void increasePrerequiste() {
            this.prerequisite++;
        }

        public void decreasePrerequiste() {
            this.prerequisite--;
        }

        public Boolean isVisited() {
            return visited;
        }

        public Boolean isEmptyPrerequisite() {
            return this.prerequisite == 0;
        }
    }

}
