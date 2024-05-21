package W9.p3;

public class BinarySearchSqrt {
    public static void main(String[] args) {
        // compare the results of our Binary search square root
        // with the built-in square root function
        System.out.printf("\nBuilt-in sqrt(5): %.6f", Math.sqrt(5));
        System.out.printf("\nBinary search sqrt(5): %.6f", sqrt(5));

        System.out.printf("\nBuilt-in sqrt(101): %.6f", Math.sqrt(101));
        System.out.printf("\nBinary search sqrt(101): %.6f", sqrt(101));

        System.out.printf("\nBuilt-in sqrt(0.5): %.6f", Math.sqrt(0.5));
        System.out.printf("\nBinary search sqrt(0.5): %.6f", sqrt(0.5));
    }

    static double sqrt(double number) {
        double exp = 0.0000001;

        double min = 0;
        double max = number;

        if (number < 1) {
            max = 1;
        }

        while ((max - min) > exp) {
            double mid = (max + min) / 2.0;

            if (mid * mid < number) {// if mid^2 < number --> we need the bigger mid
                min = mid;
            } else {
                max = mid;
            }
        }
        // after while loop, we got the max and min very close together, we return one
        // of them
        return max;
    }
}
