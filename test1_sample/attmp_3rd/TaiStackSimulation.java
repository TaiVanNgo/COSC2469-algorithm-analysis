package test1_sample.attmp_3rd;

public class TaiStackSimulation {

    // complexity: O(N) --> Since it go through the length of the stack (the
    // parameter)
    String[] popAll(String[] stack) {
        // popAll(["A"]) => return ["A"].
        // popAll(["A", "B", "C"]) => return ["C", "B", "A"].
        // popAll([]) => return [] (an empty String array).
        String[] returnStack = new String[stack.length];

        int index = 0;
        for (int i = stack.length - 1; i >= 0; i--) {
            returnStack[index] = stack[i];
            index++;
        }

        return returnStack;
    }

    // The complexity: O(M + N) = O(N)
    int minOperation(String[] targetStack, String[] currentStack) {
        // (["A", "B", "C"], ["A", "B", "C", "D"]) => return one
        // (["A", "B", "C"], ["A", "C", "B"]) => return four

        int incorrectPoint = 0;

        while (incorrectPoint < targetStack.length && incorrectPoint < currentStack.length) {
            if (targetStack[incorrectPoint] == currentStack[incorrectPoint]) {
                incorrectPoint++;
            } else {
                break;
            }
        }

        int pop = currentStack.length - incorrectPoint;
        int push = targetStack.length - incorrectPoint;

        return pop + push;
    }

    public static void main(String[] args) {
        TaiStackSimulation stack = new TaiStackSimulation();

        System.out.println("------MEHTHOD1--------");
        String[] str1 = stack.popAll(new String[] { "A" });
        String[] str2 = stack.popAll(new String[] { "A", "B", "C" });
        String[] str3 = stack.popAll(new String[] {});

        System.out.println("TEST: A");
        for (int i = 0; i < str1.length; i++) {
            System.out.print(str1[i] + " \n");
        }

        System.out.println("TEST: A, B, C");
        for (int i = 0; i < str2.length; i++) {
            System.out.print(str2[i] + " ");
        }

        System.out.println("\nTEST: EMPTY\n");
        for (int i = 0; i < str3.length; i++) {
            System.out.print(str3[i]);
        }

        System.out.println("------MEHTHOD2--------");
        System.out.println(stack.minOperation(new String[] { "A", "B", "C" }, new String[] { "A", "B", "C" }));
        System.out.println(stack.minOperation(new String[] { "A", "B", "C" }, new String[] { "A", "B" }));
        System.out.println(stack.minOperation(new String[] { "A", "B", "C" }, new String[] { "A", "B", "C", "D" }));
        System.out.println(stack.minOperation(new String[] { "A", "B", "C" }, new String[] { "A", "C", "B" }));

    }
}
