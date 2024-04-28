package W8.p3;

public class problem3 {

    static int numberOfInversion(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    res++;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 1, 5, 6 };

        System.out.println(numberOfInversion(arr));
    }
}
