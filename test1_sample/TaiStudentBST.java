package test1_sample;

public class TaiStudentBST {

    StudentNode root;

    TaiStudentBST() {
        root = null;
    }

    // complexity O(log(N)) since the tree is balanced
    public void addStudent(Student student) {
        // if the root of the tree is curently null
        if (this.root == null) {
            // set the student (input) become the root
            this.root = new StudentNode(student);
            return;
        }

        StudentNode travel_node = this.root; // use this node to travel through the tree
        // in the normal case
        // if student's gpa < travel_node gpa, go left
        // if student's gpa > travel_node gpa, go right
        while (travel_node != null) {
            if (student.gpa < travel_node.data.gpa) {
                // if the left of the travel_node is null, this is the appropirate to add
                // student to it
                if (travel_node.left == null) {
                    StudentNode newNode = new StudentNode(student);
                    travel_node.left = newNode;
                    newNode.parent = travel_node;
                    return;
                }

                travel_node = travel_node.left;// if not we go to left until see the null
            } else if (student.gpa > travel_node.data.gpa) {
                if (travel_node.right == null) {
                    StudentNode newNode = new StudentNode(student);
                    travel_node.right = newNode;
                    newNode.parent = travel_node;
                    return;
                }

                travel_node = travel_node.right;

            }
        }

        return;
    }

    // Time complexity of this funciton is O(log(N)) since the tree is balanced
    public Student nextStudent(Student student) {
        StudentNode currentStudent = this.root;
        StudentNode inputStudent = new StudentNode(student);

        Student next_student = null;// this student is the student that we want to find

        while (currentStudent != null) {
            if (inputStudent.data.gpa < currentStudent.data.gpa) {
                // go left if the input student has gpa < current student's gpa
                if (currentStudent.data.gpa > inputStudent.data.gpa) {
                    // if the current student that have the gpa > tha nthe input student
                    // we store this data to the next_student
                    next_student = currentStudent.data;
                }
                // if not, go to the left
                currentStudent = currentStudent.left;
            } else if (inputStudent.data.gpa >= currentStudent.data.gpa) {
                if (currentStudent.data.gpa > inputStudent.data.gpa) {
                    next_student = currentStudent.data;
                }

                currentStudent = currentStudent.right;
            }
        }

        return next_student;

    }

    public static void main(String[] args) {

        Student s1 = new Student(1, "A", 70.0); // the first parameter is student id
        Student s2 = new Student(2, "B", 65.0);
        Student s3 = new Student(3, "C", 80.0);
        Student s4 = new Student(4, "D", 78.0);
        Student s5 = new Student(5, "E", 68.0);

        TaiStudentBST tree = new TaiStudentBST();

        tree.addStudent(s1);
        tree.addStudent(s2);
        tree.addStudent(s3);
        tree.addStudent(s4);
        tree.addStudent(s5);

        Student stu1 = tree.nextStudent(s1);
        Student stu2 = tree.nextStudent(s5);
        Student stu3 = tree.nextStudent(s3);

        if (stu1 != null)
            System.out.println(stu1.to_string());
        else
            System.out.println("NULL");

        if (stu2 != null)
            System.out.println(stu2.to_string());
        else
            System.out.println("NULL");

        if (stu3 != null)
            System.out.println(stu3.to_string());
        else
            System.out.println("NULL");

    }

}

class Student {
    int id;
    String name;
    double gpa; // this is unique

    Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    Student() {

    }

    public String to_string() {
        return "ID: " + this.id + " Name: " + this.name + " GPA: " + this.gpa + "\n";
    }
}

class StudentNode {
    Student data;

    StudentNode parent;
    StudentNode left;
    StudentNode right;

    StudentNode(Student student) {
        this.data = student;
        parent = left = right = null;
    }
}
