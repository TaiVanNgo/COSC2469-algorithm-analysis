package test1_sample.attmp_3rd;

public class TaiStudentBST {
    StudentNode root;
    int size;

    TaiStudentBST() {
        this.root = null;
        this.size = 0;
    }

    // time complexity: O(Log(N)) because it doesn't need to go through the tree to
    // add value
    void addStudent(Student student) {
        // check teh tree has the root or not?
        if (this.root == null) {
            this.root = new StudentNode(null, student);// make the parameter become the root
            size++;
            return;
        }

        // in the normal case
        StudentNode travel_node = this.root;// use the travel node to go through the tree
        while (travel_node != null) {
            if (student.gpa < travel_node.data.gpa) {
                // go left
                if (travel_node.left == null) {
                    // appropriate position to add
                    StudentNode newNode = new StudentNode(travel_node, student);
                    travel_node.left = newNode;
                    size++;
                    return;
                }
                travel_node = travel_node.left;
            } else if (student.gpa > travel_node.data.gpa) {
                // go right
                if (travel_node.right == null) {
                    // appropriate position to add
                    StudentNode newNode = new StudentNode(travel_node, student);
                    travel_node.right = newNode;
                    size++;
                    return;
                }
                travel_node = travel_node.right;
            } else {
                // duplication
                return;
            }
        }
    }

    // time complexity: O(Log(N)) because it doesn't need to go through the tree
    Student nextStudent(Student student) {
        StudentNode node = this.root;
        Student next_student = null;

        while (node != null) {
            // go until the node is null
            if (student.gpa < node.data.gpa) {
                // go left
                // if (node.data.gpa > student.gpa) {
                    next_student = node.data;
                // }

                node = node.left;
            } else if (student.gpa >= node.data.gpa) {
                // go right
                if (node.data.gpa > student.gpa) {
                    next_student = node.data;
                }

                node = node.right;
            }
        }

        return next_student;
    }

    public static void main(String[] args) {
        Student s1 = new Student(1, "A", 70.0); // the first parameter is student id
        Student s2 = new Student(2, "B", 69.0);
        Student s3 = new Student(3, "C", 80.0);
        Student s4 = new Student(4, "D", 78.0);
        Student s5 = new Student(5, "E", 68.0);

        TaiStudentBST tree = new TaiStudentBST();

        tree.addStudent(s1);
        tree.addStudent(s2);
        tree.addStudent(s3);
        tree.addStudent(s4);
        tree.addStudent(s5);

        Student test1 = tree.nextStudent(s1);
        System.out.println(test1.to_string());

        Student test2 = tree.nextStudent(s4);
        System.out.println(test2.to_string());

        Student test3 = tree.nextStudent(s5);
        if (test3 != null)
            System.out.println(test3.to_string());
        else {
            System.out.println("NULL");
        }
    }
}

class Student {
    int id;
    String name;
    double gpa;

    Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    String to_string() {
        return "ID: " + this.id + " Name: " + this.name + " GPA: " + this.gpa;
    }
}

class StudentNode {
    Student data;

    StudentNode parent;
    StudentNode left;
    StudentNode right;

    StudentNode(StudentNode parent, Student data) {
        this.parent = parent;
        this.data = data;

        this.left = null;
        this.right = null;
    }
}