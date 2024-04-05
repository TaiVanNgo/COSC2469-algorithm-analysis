package test1_sample.attmp_1st;

public class problem1 {
    public static void main(String[] args) {
        StackSimulation stack = new StackSimulation();

        System.out.println("----FIRST METHOD------");
        System.out.println("First Case:");
        String[] result = stack.popAll(new String[] { "A" });
        for (String i : result) {
            System.out.println(i);
        }

        System.out.println("Second Case:");
        String[] result2 = stack.popAll(new String[] { "A", "B", "C" });
        for (String i : result2) {
            System.out.println(i);
        }

        System.out.println("Third Case:");
        String[] result3 = stack.popAll(new String[] {});
        for (String i : result3) {
            System.out.println(i);
        }

        System.out.println("----SECOND METHOD------");
        System.out.println(stack.minOperation(new String[] { "A", "B", "C" }, new String[] { "A", "B", "C" }));
        System.out.println(stack.minOperation(new String[] { "A", "B", "C" }, new String[] { "A", "B" }));
        System.out.println(stack.minOperation(new String[] { "A", "B", "C" }, new String[] { "A", "B", "C", "D" }));
        System.out.println(stack.minOperation(new String[] { "A", "B", "C" }, new String[] { "A", "C", "B" }));

    }

    static class StackSimulation {

        // complexity = O(N) --> N is the size of the stack (parameter)
        public String[] popAll(String[] stack) {
            // popAll(["A"]) => return ["A"].
            // popAll(["A", "B", "C"]) => return ["C", "B", "A"].
            // popAll([]) => return [] (an empty String array).

            // String[] array = new String[stack.length];
            if (stack.length == 0)
                return stack;// if the array is empty

            String[] result = new String[stack.length];

            for (int i = 0; i < stack.length; i++) {
                result[i] = stack[stack.length - 1 - i];
            }
            return result;
        }

        // Complexity = O(N)
        public int minOperation(String[] targetStack, String[] currentStack) {
            // we need to find the lowest index that the target[i] != current stack
            // after find the index that Target and Current are different. --> pop and push
            // to the current

            int differentIndex = 0;
            while (differentIndex < targetStack.length && differentIndex < currentStack.length) {
                // run until the different index exceed the length of T or C length
                if (targetStack[differentIndex] != currentStack[differentIndex]) {
                    break;
                }
                differentIndex++;
            }

            int popStep = currentStack.length - differentIndex;// number of step we need to pop
            int pushStep = targetStack.length - differentIndex; // number of step we need to push

            return popStep + pushStep;
        }
    }

}
