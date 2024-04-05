class TaiStudentLinkedList {
    int size;
    StudentNode head;

    TaiStudentLinkedList() {
        this.size = 0;
        this.head = null;
    }

    // complexity in the worst case is O(N) since it must go through the nodes in
    // the list to compare
    int add(Student student) {
        // if the head is currently null
        if (this.head == null) {
            StudentNode newNode = new StudentNode(student, null);
            head = newNode;
            size++;
            return 0;
        }

        // if it smaller than the head
        if (student.gpa < head.data.gpa) {
            StudentNode newNode = new StudentNode(student, head);// create new node point to head
            head = newNode;// update new node
            size++;
            return 0;
        }

        // in the normal case
        StudentNode tempNode = this.head;// go through the list from head
        StudentNode prevNode = null;
        int position = 0;
        while (tempNode != null) {
            // go until see the appropriate position, find the node that larger thatn the
            // current node
            if (student.gpa < tempNode.data.gpa) {
                // if find the appropriate position
                // we use prev node to insert it
                StudentNode newNode = new StudentNode(student, tempNode);// create new node point to tempNode
                prevNode.next = newNode;
                size++;
                return position;
            }

            prevNode = tempNode;
            tempNode = tempNode.next;
            position++;// update position
        }
        // if we reach the the null --> add at the end
        StudentNode newNode = new StudentNode(student, null);// create new node point to tempNode
        prevNode.next = newNode;
        size++;
        return position;
    }

    // complexity O(N) since we have to go through the list to check
    Student nearest(double searchGPA) {
        if (size == 0) {
            // if the tree is empty
            return null;
        }

        // if it smaller than the head --> return head
        if (searchGPA < this.head.data.gpa) {
            return head.data;
        }

        // go through the list to check
        double minDistance = 4.1;// more than gpa
        Student nearestStudent = null;
        double currentDistance = 0;

        StudentNode tempNode = this.head;
        while (tempNode != null) {
            currentDistance = Math.abs(tempNode.data.gpa - searchGPA);

            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                // update the student\
                nearestStudent = tempNode.data;
            }

            tempNode = tempNode.next;
        }

        return nearestStudent;
    }

    void displayList() {
        StudentNode temp = this.head;
        while (temp != null) {
            System.out.print(temp.data.gpa + " -> ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        TaiStudentLinkedList list = new TaiStudentLinkedList();

        System.out.println("------------FIRST METHOD!------------------");
        System.out.println(list.add(new Student(1, "A", 3.3)));
        System.out.println(list.add(new Student(2, "B", 3.6)));
        System.out.println(list.add(new Student(3, "C", 3.1)));
        System.out.println(list.size);

        System.out.println("THE LIST AFTER ADDING");
        list.displayList();

        System.out.println("------------SECOND METHOD!------------------");
        Student nearest1 = list.nearest(0.0);
        Student nearest2 = list.nearest(3.5);
        Student nearest3 = list.nearest(3.2);

        System.out.println("The nearest 0.0");
        System.out.println(nearest1.to_string());

        System.out.println("The nearest 3.5");
        System.out.println(nearest2.to_string());

        System.out.println("The nearest 3.2");
        System.out.println(nearest3.to_string());
    }
}

class StudentNode {
    Student data;
    StudentNode next;

    StudentNode(Student data, StudentNode next) {
        this.data = data;
        this.next = next;

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
        return "ID: " + id + " Name: " + name + " GPA: " + gpa;
    }
}
