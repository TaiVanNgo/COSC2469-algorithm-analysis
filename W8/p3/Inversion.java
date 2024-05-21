package W8.p3;

public class Inversion {
    static int inversionBruteForce(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    res++;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] test2 = new int[] { 1, 2, 3, 4, 5, 6, 8, 7 };

        System.out.println(
                inversionBruteForce(test2)
        );
    }
}
