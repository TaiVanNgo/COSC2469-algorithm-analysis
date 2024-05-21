package W11.p3;

public class LongestSequence {
    public static void main(String[] args) {
        System.out.println(

                longestSequence(new int[] { 5, 2, 3, 9, 6, 7, 8 }));
    }

    static int longestSequence(int[] arr) {
        int size = arr.length;
        int[] countTable = new int[size];
        for (int i = 0; i < size; i++) {
            countTable[i] = 1;
        }

        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    if (countTable[i] < (countTable[j] + 1)) {
                        countTable[i] = countTable[j] + 1;
                    }
                }
            }
        }

        int maxSequence = 0;
        for (int i = 0; i < size; i++) {
            if (countTable[i] > maxSequence) {
                maxSequence = countTable[i];
            }
        }

        return maxSequence;
    }
}
