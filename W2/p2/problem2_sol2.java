package W2.p2;

public class problem2_sol2 {
    public static void main(String[] args) {
        int[] A = { 6, 8, 10, 11, 6, 10 };

        boolean isUnique;// this is the boolean to determine the element is uniqe or not

        for (int i = 0; i < A.length; i++) {
            isUnique = true;
            for(int j = i + 1; j < A.length; j ++){
                if(A[i] == A[j]){
                    isUnique = false;//if there is an identical element with the current element
                    //it is not uniqe anymore
                }
            }

            if(isUnique){
                //After the "j" loop, print the elemtn if it is unique
                System.out.println(A[i]);
            }
        }

    }
}
