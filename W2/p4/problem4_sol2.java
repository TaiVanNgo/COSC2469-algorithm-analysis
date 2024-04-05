package W2.p4;

public class problem4_sol2 {

    public static void main(String[] args) {
        int[] A = { -40, -10, 30, 50, 99 };
        int currentSum;
        int bestSum = A[0] + A[A.length - 1];
        int bestI = 0 , bestJ = 0;

        int i = 0, j = A.length - 1;
        while(i < j){
            currentSum = A[i] + A[j];
            if(Math.abs(currentSum) < Math.abs(bestSum)){
                bestSum = currentSum;
                bestI = i;
                bestJ= j;
            }

            if(currentSum < 0){
                i ++;
            }else if(currentSum > 0){
                j --;
            }
        }

        System.out.println(A[bestI] + " and " + A[bestJ]);
    }

}
