package W2.p2;


// Describe an algorithm that prints out all the unique elements in an array.
// What is the complexity of your algorithm? (Consider two different approaches: without sorting and with sorting)

// Example:
// Array = [6, 8, 10, 11, 6, 10] => print out [6, 8, 10, 11] (you can print the values in any order)

class problem2 {
    public static void main(String[] args) {
        //THE EXPLAINATION IN THE OBSIDIAN

        int[] A1= {6, 8, 10, 11, 6, 10};
        
        int[] A2 = new int[12];

        for(int i = 0; i < A1.length; i++){
            A2[A1[i]] ++;
        }

        for(int i = 0; i < A2.length; i++){
            if(A2[i] >= 1){
                System.out.println(i);
            }
        }
    }
}