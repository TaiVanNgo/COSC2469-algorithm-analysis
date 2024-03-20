package W3.p4;

import java.util.Scanner;

import W3.ArrayStack;

// Write a program that reads in a sequence of characters,
// and determines whether its parentheses, brackets and curly braces are balanced.
// Examples:

// []: balanced

// {}: balanced

// (): balanced

// (){{[]}}(): balanced

// {): not balanced

// {{[[}}]]: not balanced

public class problem4 {

    public static boolean isBalanced(String input) {
        ArrayStack<Character> stack = new ArrayStack<>();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(' || input.charAt(i) == '[' || input.charAt(i) == '{') {
                stack.push(input.charAt(i));
            } else {
                char openChar = stack.peek();
                if ((openChar == '(' && input.charAt(i) != ')') ||
                        (openChar == '{' && input.charAt(i) != '}') ||
                        (openChar == '[' && input.charAt(i) != ']')) {
                    return false;
                }

                stack.pop(); // remove the open racket
            }
        }

        // if we run through the loop and the Stack still doens't emtyp
        // there is missing the close racket

        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Create a Scanner object

        System.out.print("Enter the character: ");
        String input = sc.nextLine();
        isBalanced("()");
        if (isBalanced(input)) {
            System.out.println("Balanced");
        } else {
            System.out.println("Not balanced");
        }

    }
}
