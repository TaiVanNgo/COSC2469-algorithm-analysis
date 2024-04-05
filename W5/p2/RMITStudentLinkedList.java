package W5.p2;

public class RMITStudentLinkedList {
    public int size;
    public RMITStudentNode head;

    RMITStudentLinkedList(){
        this.head = null;
        this.size = 0;
    }

    public boolean insert(RMITStudent student){
        if(this.head == null){
            this.head = new RMITStudentNode(student);
            size++;
            return true;
        }

        //normal case
        RMITStudentNode tempNode = this.head;

        while(tempNode != null){
            if(tempNode.data.studentId == student.studentId){
                return false;//return if the student id is aldready exists
            }
            if(tempNode.next == null){
                if(tempNode.data.studentId == student.studentId){
                    return false;
                }
                RMITStudentNode newNode = new RMITStudentNode(student);
                tempNode.next = newNode;
                size++;
                return true;
            }
            tempNode = tempNode.next;
        }

        return false;
    }

    public RMITStudent get(String id){
        RMITStudentNode tempNode = this.head;

        while(tempNode != null){
            if(tempNode.data.studentId == id){
                return tempNode.data;
            }
            tempNode = tempNode.next;
        }

        return null;
    }

    public boolean remove(String id){
        if(this.head == null){
            return false;
        }

        //if head is the first node --> delete head
        if(head.data.studentId == id){
            return removeAtHead();
        }

        RMITStudentNode current = this.head.next;
        RMITStudentNode prev = this.head;

        while(current != null){
            if(current.data.studentId == id){
                prev.next = current.next;
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }

        return false;
    }

    private boolean removeAtHead() {
        if (head == null) {
          return false;
        }

        head = head.next;
        size--;
        return true;
      }



    public void displayList(){
        RMITStudentNode tempNode = this.head;
        while(tempNode != null){
            System.out.print(tempNode.data.gpa + " -> ");
            tempNode = tempNode.next;
        }
        System.out.println();
    }
}
