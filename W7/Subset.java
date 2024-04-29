package W7;

class Subset {

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };

        subset(arr, new Boolean[] { false, false, false, false, false, false, false }, 0);
    }

    public static void subset(int[] arr, Boolean[] selected, int index) {
        // base case
        // if index is == the length. we return
        if (index == arr.length) {
            displayArr(arr, selected);
            return;
        }

        // normal case, we divided it to 2 cases, select or not select.
        // For exapmle, with the array [1,2,3]. Index 0 is 1,
        // for the first index, we can divide it into 2 cases, which is take 0 or not
        // take 1.
        // if we take 1, we have the array of [1], and in the other side, we have the
        // array of []
        // keep increment the index, to select or not select the next index. if we have
        // [1], we select 2. ==> we have [1,2]

        // we select,
        selected[index] = true;
        subset(arr, selected, index + 1);

        // we not select
        selected[index] = false;
        subset(arr, selected, index + 1);
    }

    private static void displayArr(int[] arr, Boolean[] selected) {
        System.out.print("Subset: [");
        for (int i = 0; i < arr.length; i++) {
            if (selected[i] == true) {
                if (i == arr.length - 1) {
                    System.out.print(arr[i]);
                } else {
                    System.out.print(arr[i] + ", ");
                }
            }
        }
        System.out.print("]\n");
    }

}