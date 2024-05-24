public class TaiStudentSet {
    Student[] students;

    TaiStudentSet(Student[] students) {
        this.students = students;
    }

    // TIME COMPLEXITY: O(N)
    public int countGrade(String grade) {
        int count = 0;
        for (int i = 0; i < this.students.length; i++) {
            // go through the array to find the grade that we want
            if (this.students[i].grade == grade) {
                // if we can find, increase the count
                count++;
            }
        }

        return count;
    }

    // TIME COMPLEXITY: O(N * logN)
    public Student[] sortByGrade() {
        Student[] sortedStudent = this.students.clone();

        new MergeSort().mergeSort(sortedStudent);

        return sortedStudent;
    }

    public static void main(String[] args) {
        Student[] students = new Student[] {
                new Student(1, "Student A", 3.5, "HD"),
                new Student(2, "Student B", 3.2, "DI"),
                new Student(3, "Student C", 3.0, "DI"),
                new Student(4, "Student D", 3.0, "NN"),
                new Student(5, "Student E", 3.0, "PA"),
                new Student(6, "Student F", 4.0, "HD") };

        TaiStudentSet studentSet = new TaiStudentSet(students);

        System.out.println("Function1: ");
        System.out.println(studentSet.countGrade("DI"));

        System.out.println("Function2: ");
        Student[] sortedStudents = studentSet.sortByGrade();
        for (Student stu : sortedStudents) {
            System.out.println("Id: " + stu.id + " Grade: " + stu.grade);
        }
    }
}

class Student {
    int id;
    String name;
    double GPA;
    String grade;
    int gradeNum;

    Student(int id, String name, double GPA, String grade) {
        this.id = id;
        this.name = name;
        this.GPA = GPA;
        this.grade = grade;

        if (grade == "HD") {
            gradeNum = 1;
        } else if (grade == "DI") {
            gradeNum = 2;
        } else if (grade == "CR") {
            gradeNum = 3;
        } else if (grade == "PA") {
            gradeNum = 4;
        } else {
            gradeNum = 5;
        }
    }
}

class MergeSort {
    public void mergeSort(Student arr[]) {
        if (arr.length > 1) {
            int n = arr.length;
            int middle = n / 2;

            // create 2 sub-arrays from arr
            Student[] sub1 = new Student[middle];
            for (int i = 0; i < middle; i++) {
                sub1[i] = arr[i];
            }
            Student[] sub2 = new Student[n - middle];
            for (int i = middle; i < n; i++) {
                sub2[i - middle] = arr[i];
            }

            // sort first and second halves
            mergeSort(sub1);
            mergeSort(sub2);

            // merge sub1 and sub2 into the original array
            merge(sub1, sub2, arr);
        }
    }

    // merge two sub-arrays sub1 and sub2 into the array dest
    public void merge(Student[] sub1, Student[] sub2, Student[] dest) {
        int p1 = 0, p2 = 0, pDest = 0; // pointers to 3 arrays
        while (p1 < sub1.length && p2 < sub2.length) {
            if (sub1[p1].gradeNum <= sub2[p2].gradeNum) {
                dest[pDest] = sub1[p1];
                p1++;
            } else {
                dest[pDest] = sub2[p2];
                p2++;
            }
            pDest++;
        }

        // copy remaining elements, if any
        while (p1 < sub1.length) {
            dest[pDest++] = sub1[p1++];
        }
        while (p2 < sub2.length) {
            dest[pDest++] = sub2[p2++];
        }
    }
}