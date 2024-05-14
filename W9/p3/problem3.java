package W9.p3;

class problem3 {
    public static void main(String[] args) {
        System.out.printf("Built-in square root of 5: %.6f\n", Math.sqrt(5));
        System.out.printf("Binary search square root 5: %.6f\n", sqrt(5));

        System.out.println("********************************************");
        System.out.printf("Built-in square root of 101: %.6f\n", Math.sqrt(101));
        System.out.printf("Binary search square root 101: %.6f\n", sqrt(101));

        System.out.println("********************************************");
        System.out.printf("Built-in square root of 0.5: %.6f\n", Math.sqrt(0.5));
        System.out.printf("Binary search square root 0.5: %.6f\n", sqrt(0.5));
    }

    static double sqrt(double X) {
        double esp = 0.0000001; // set the esp to the very small number
        double max = X;
        double min = 0;
        // the min to max is the 0 to the number that we want to calculate

        if (X < 1) {
            // if X < 0, we need to set it become 1.
            max = 1;
        }

        while (max - min > esp) {
            // find the middle element from min to max
            double mid = (max + min) / 2.0;// use 2.0 to ensure that the mid is in the "double" type

            double res = mid * mid;
            if (res < X) {
                // if res < x, we need to find the number on the right handside
                min = mid;
            } else {
                // if not, the answer is in the left handside
                max = mid;
            }
        }

        return min;
    }
}