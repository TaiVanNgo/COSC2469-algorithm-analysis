package test1_sample.attmp_2nd;

public class DiamondBox {
    String diamondBox;

    DiamondBox(String diamondBox) {
        this.diamondBox = diamondBox;
    }

    // We use stack to determine the [] is true or not
    // [[*] --> if we are currently at']' and we peek from the stack is not the [
    // --> wrong

    // []* --> we use the int openBox, if whenever we see '[', we ++ the openbox, --
    // when see ']', if
    // we see the * and the openBox is 0 --> this mean the * is lay outside the box
    // --> return false

    // [**][*] The different between if there is only one outside box is that ecept
    // the first index = 0, it always from 0 to N.
    // in this case, we see '[' and ']' which mean the open box is, 1 0 1 0, --> 2
    // outside box
    // in the correct case [[**]] --> the open box 1 2 1 0 --> correct
    // time complexity => O(N)
    boolean isValid() {
        VanTaiArrayStack stack = new VanTaiArrayStack();
        int openBox = 0;// this determin the open sign of box

        for (int i = 0; i < diamondBox.length(); i++) {
            if (diamondBox.charAt(i) == '[') {
                // if the diamond box is not empty
                if (i != 0 && openBox == 0) {
                    // if the openBox = 0 --> it has outer box
                    return false;
                }
                openBox++;
                stack.push(diamondBox.charAt(0));

            } else if (diamondBox.charAt(i) == ']') {
                if (stack.isEmpty()) {
                    // if the current char is ']', and the stack is empty --> return false
                    return false;
                }
                if (stack.peek() != '[') {
                    return false;
                }
                stack.pop();
                openBox--;
            } else {
                // this is diamond situation
                if (openBox <= 0) {
                    return false;
                }

            }
        }
        if (!stack.isEmpty()) {
            return false;// if after the loop, the stack still has value

        }
        return true;

    }

    // time complexity: O(Log(N)) //because we go to until we see the close bracket
    int depestLevel() {
        if (!this.isValid()) {
            return 0;// WRONG
        }
        int level = 0;

        for (int i = 0; i < diamondBox.length(); i++) {
            if (diamondBox.charAt(i) == '[') {
                level++;
            } else if (diamondBox.charAt(i) == ']') {
                return level;// if we see the ']', immediatelyt return the level
            }
        }

        return level;
    }

    // complexity is O(N)
    int maxDiamons() {
        if (!this.isValid()) {
            return 0;
        }

        int[] diamondAtLevel = new int[this.depestLevel() + 1];// create the array that has the size of the depest level
        // we use base 1 not base-0
        int currentLevel = 0;

        for (int i = 0; i < diamondBox.length(); i++) {
            if (diamondBox.charAt(i) == '[') {
                currentLevel++;
            } else if (diamondBox.charAt(i) == ']') {
                currentLevel--;// decrease the level when see the closed bracket
            } else if (diamondBox.charAt(i) == '*') {
                diamondAtLevel[currentLevel]++;
            }
        }

        // find the max value in the array
        int max = 0;
        for (int i = 0; i < diamondAtLevel.length; i++) {
            max = diamondAtLevel[i] > max ? diamondAtLevel[i] : max;
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println("-----METHOD 1----");
        DiamondBox box1 = new DiamondBox("[*]");
        System.out.println("Test: " + box1.diamondBox);
        if (box1.isValid()) {
            System.out.println("This is valid!");
        } else {
            System.out.println("This is not valid");
        }

        DiamondBox box2 = new DiamondBox("[[*]]");
        System.out.println("Test: " + box2.diamondBox);
        if (box2.isValid()) {
            System.out.println("This is valid!");
        } else {
            System.out.println("This is not valid");
        }

        DiamondBox box3 = new DiamondBox("[[[*]**]*]");
        System.out.println("Test: " + box3.diamondBox);
        if (box3.isValid()) {
            System.out.println("This is valid!");
        } else {
            System.out.println("This is not valid");
        }

        DiamondBox box4 = new DiamondBox("[]*");
        System.out.println("Test: " + box4.diamondBox);
        if (box4.isValid()) {
            System.out.println("This is valid!");
        } else {
            System.out.println("This is not valid");
        }

        DiamondBox box5 = new DiamondBox("[**][*]");
        System.out.println("Test: " + box5.diamondBox);
        if (box5.isValid()) {
            System.out.println("This is valid!");
        } else {
            System.out.println("This is not valid");
        }
        /*-------------------------------------------------------------------------- */

        System.out.println("-----METHOD 2----");

        System.out.println("The level of " + box1.diamondBox + " is: " + box1.depestLevel());
        System.out.println("The level of " + box2.diamondBox + " is: " + box2.depestLevel());
        System.out.println("The level of " + box3.diamondBox + " is: " + box3.depestLevel());

        /*-------------------------------------------------------------------------- */
        System.out.println("-----METHOD 3----");
        DiamondBox box6 = new DiamondBox("[**[**]*]");
        System.out.println("The max diamond of " + box6.diamondBox + " is " + box6.maxDiamons());
        System.out.println("The max diamond of " + box3.diamondBox + " is " + box3.maxDiamons());

    }

}

class VanTaiArrayStack {
    char[] items;
    private int size;
    private static int CAPACITY = 100;

    VanTaiArrayStack() {
        size = 0;
        items = new char[CAPACITY];
    }

    boolean push(char item) {
        // make sure the stack still have empty slot
        if (size < CAPACITY) {
            items[size] = item;
            size++;
            return true;
        }
        return false;
    }

    boolean pop() {
        // make sure the stack is not empty
        if (isEmpty()) {
            return false;
        }
        size--;
        return true;
    }

    Character peek() {
        // make sure the stack is not empty
        if (isEmpty()) {
            return null;
        }
        return items[size - 1];
    }

    boolean isEmpty() {
        return size == 0;
    }
}