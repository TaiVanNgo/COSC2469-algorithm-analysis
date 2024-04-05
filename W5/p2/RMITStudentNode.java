package W5.p2;

class RMITStudentNode {
    public RMITStudent data;
    public RMITStudentNode next;

    RMITStudentNode(RMITStudent data) {
        this.data = data;
        this.next = null;
    }
}