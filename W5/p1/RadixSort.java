package W5.p1;

public class RadixSort {
    private int[] value;
    static final int BASE = 10;

    RadixSort(int[] value) {
        this.value = value;
    }

    private void countingSort(int baseTen) {
        // base ten to use to decide what is the digit that we want to get
        // ex: 1245, baseTen = 1 -> we get 5, baseTen = 10 we get 4...

        int[] temp = this.value;
        int[] frequencyArr = new int[BASE];
        for (int i = 0; i < temp.length; i++) {
            // find the frequency
            int digit = (temp[i] / baseTen) % 10;
            frequencyArr[digit]++;
        }

        // find accumulative frequencyArray
        for (int i = 1; i < frequencyArr.length; i++) {
            frequencyArr[i] += frequencyArr[i - 1];
        }

        // update the position for value(go from right to left)
        for (int i = this.value.length - 1; i >= 0; i--) {
            int digit = (this.value[i] / baseTen) % BASE;
            temp[frequencyArr[digit] - 1] = value[i];
            frequencyArr[digit]--;
        }

        // copy the temp to the value
        this.value = temp;
    }

    public void sort() {
        // find the max
        int max = this.value[0];
        for (int i = 1; i < this.value.length; i++) {
            if (this.value[i] > max) {
                max = this.value[i];
            }
        }

        // get the length of the max? ex; 10000 --> length = 5
        int lengthDigit = (max + "").length();
        int tenPower = 1;
        for (int i = 0; i < lengthDigit; i++) {
            countingSort(tenPower);
            tenPower *= 10;
        }
    }

    public int[] get() {
        return this.value;
    }

    public static void main(String[] args) {
        RadixSort array = new RadixSort(new int[] {
                1, 22, 333, 4444, 55555, 666666,
                7777777, 88888888,
                19, 21, 383, 12345, 1000000000,
                999999999, 1234567, 3456789, 900, 1001
        });

        System.out.println("Array before sorting: ");
        int[] testArray = array.get();

        for (int i : testArray) {
            System.out.print(i + " ");
        }

        System.out.println("\nArray after sorting: ");
        array.sort();
        testArray = array.get();

        for (int i : testArray) {
            System.out.print(i + " ");
        }
    }
}
