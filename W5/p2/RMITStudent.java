package W5.p2;

class RMITStudent {
  public String studentId;
  public String fullName;
  public String major;
  public double gpa;

  // public static final RMITStudent DELETED = new RMITStudent("DEL", "");


  RMITStudent(String studentId, String fullName, String major, double gpa) {
    this.studentId = studentId;
    this.fullName = fullName;
    this.major = major;
    this.gpa = gpa;
  }

  @Override
  public String toString() {
    return "ID: " + this.studentId + " Full Name: " + this.fullName + " Major: " + this.major + " GPA: " + this.gpa;
  }
}
