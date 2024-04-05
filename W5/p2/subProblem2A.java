package W5.p2;

public class subProblem2A {

    public static void main(String[] args) {
        RMITStudent[] students = new RMITStudent[] {
                new RMITStudent("S1001", "Alice Johnson", "Computer Science", 3.5),
                new RMITStudent("S1002", "Bob Smith", "Mathematics", 3.7),
                new RMITStudent("S1003", "Catherine Lee", "Biology", 3.8),
                new RMITStudent("S1004", "David Kim", "Physics", 3.6),
                new RMITStudent("S1005", "Ella Martinez", "Chemistry", 3.9),
                new RMITStudent("S1006", "Frank Owen", "English Literature", 3.4),
                new RMITStudent("S1007", "Grace Park", "Political Science", 3.5),
                new RMITStudent("S1008", "Henry Nguyen", "Psychology", 3.2),
                new RMITStudent("S1009", "Isabella Rodriguez", "History", 3.3),
                new RMITStudent("S1010", "Jack Wilson", "Economics", 3.7)
        };

        System.out.println("============TEST SEPARATE CHAINING============");
        RMITStudentCollectionSeperateChaining collectionChaining = new RMITStudentCollectionSeperateChaining(5);

        for (RMITStudent student : students) {
            collectionChaining.put(student);
        }

        RMITStudent test1 = collectionChaining.get(students[5].studentId);
        RMITStudent test2 = collectionChaining.get(students[2].studentId);
        RMITStudent test3 = collectionChaining.get(students[9].studentId);

        System.out.println(test1.toString());
        System.out.println(test2.toString());
        System.out.println(test3.toString());

        // collectionChaining.remove(students[0].studentId);
        collectionChaining.remove(students[5].studentId);

        if (collectionChaining.get(students[5].studentId) == null) {
            System.out.println("Can not find this student");
        }

        System.out.println("============TEST LINEAR PROBING============");
        RMITStudentCollectionLinearProbing collectionLinearFullSpace = new RMITStudentCollectionLinearProbing(8);
        System.out.println("TEST ADDING WHEN FULL SPACE: ");
        for (RMITStudent student : students) {
            collectionLinearFullSpace.put(student);
        }

        System.out.println("NORMAL CASE: ");
        RMITStudentCollectionLinearProbing collectionLinear = new RMITStudentCollectionLinearProbing(10);
        System.out.println("TEST ADDING WHEN FULL SPACE");
        for (RMITStudent student : students) {
            collectionLinear.put(student);
        }

        System.out.println("TEST GET STUDENT");
        RMITStudent test4 = collectionLinear.get(students[5].studentId);
        RMITStudent test5 = collectionLinear.get(students[2].studentId);
        RMITStudent test6 = collectionLinear.get(students[9].studentId);

        System.out.println(test4.toString());
        System.out.println(test5.toString());
        System.out.println(test6.toString());

        System.out.println("TEST REMOVE STUDENT!");
        collectionLinear.remove(students[5].studentId);
        collectionLinear.put(test6);


    }
}

class RMITStudentCollectionSeperateChaining {
    public int tableSize;
    public RMITStudentLinkedList[] hashTable;

    public RMITStudentCollectionSeperateChaining(int tableSize) {
        this.tableSize = tableSize;
        hashTable = new RMITStudentLinkedList[tableSize];
    }

    int hashCharacter(char c) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        return str.indexOf(c);
    }

    int hashString(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash += hashCharacter(str.charAt(i));
        }

        return hash % tableSize;
    }

    boolean put(RMITStudent s) {
        int hashId = hashString(s.studentId);
        boolean result = false;

        if (hashTable[hashId] == null) {
            // if the hash table at this hash id is null, we add student to this table
            hashTable[hashId] = new RMITStudentLinkedList();

            result = hashTable[hashId].insert(s);
        } else {
            // if the hash table not null, we simply insert student into it
            result = hashTable[hashId].insert(s);
        }

        return result;
    }

    RMITStudent get(String studentId) {
        int hashId = hashString(studentId);

        if (hashTable[hashId] == null) {
            return null;// if the table at hashId is null --> there no value for us to find
        }

        // normal case
        RMITStudent student = hashTable[hashId].get(studentId);

        return student;
    }

    boolean remove(String id) {
        int hashId = hashString(id);

        return hashTable[hashId].remove(id);
    }
}

class RMITStudentCollectionLinearProbing {
    public int tableSize;
    public RMITStudent[] hashTable;

    RMITStudentCollectionLinearProbing(int tableSize) {
        this.tableSize = tableSize;
        hashTable = new RMITStudent[this.tableSize];
    }

    int hashCharacter(char c) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        return str.indexOf(c);
    }

    int hashString(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash += hashCharacter(str.charAt(i));
        }

        return hash % tableSize;
    }

    boolean put(RMITStudent s) {
        int hashId = hashString(s.studentId);

        if (hashTable[hashId] == null || hashTable[hashId].studentId == "DELETED") {
            // if the location of this student is null, we assign student to it
            hashTable[hashId] = s;
            return true;
        }

        // if it not null
        int firstHashId = hashId;
        hashId++;
        // we move until we see the appropriate location
        while (hashId != firstHashId) {
            // run until the first hash id == the hash id --> this mean the hash go 1 loop
            // if we can't find the appropriate location for student after this loop -->
            // full space

            if (hashTable[hashId] == null || hashTable[hashId].studentId == "DELETED") {
                hashTable[hashId] = s;
                return true;
            }

            hashId = ((hashId + 1) % this.tableSize);
        }

        System.out.println("Full Space!");
        return false;
    }

    RMITStudent get(String studentId) {
        int hashId = hashString(studentId);

        if (hashTable[hashId].studentId == studentId) {
            // if we find it in precise place, return it
            return hashTable[hashId];
        }

        if (hashTable[hashId] == null) {// return null if that location is null
            return null;
        }

        int firstHashId = hashId;
        hashId++;
        while (hashId != firstHashId && hashTable[hashId] != null) {
            if (hashTable[hashId].studentId == studentId) {
                return hashTable[hashId];
            }

            hashId = (hashId + 1) % this.tableSize;
        }

        return null;
    }

    boolean remove(String studentId) {
        int hashId = hashString(studentId);

        if (hashTable[hashId].studentId == studentId) {
            // hashTable[hashId] = DEL;
            hashTable[hashId].studentId = "DELETED";
            return true;
        }

        // if can not find go aroudn to find it
        int firstHashId = hashId;
        hashId++;
        while (hashId != firstHashId && hashTable[hashId] != null) {
            if (hashTable[hashId].studentId == studentId) {
                hashTable[hashId].studentId = "DELETED";
            }

            hashId = (hashId + 1) % this.tableSize;
        }

        return false;
    }
}