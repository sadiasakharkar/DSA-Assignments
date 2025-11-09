package Assignment05;

import java.util.*;

class Stack {
    private int maxSize;
    private int[] stk;
    private int top;

    // Constructor
    public Stack(int s) {
        maxSize = s;
        stk = new int[maxSize];
        top = -1;
    }

    // IsFull
    public boolean isFull() {
        return (top == maxSize - 1);
    }

    // IsEmpty
    public boolean isEmpty() {
        return (top == -1);
    }

    // Push
    public void push(int element) {
        if (isFull()) {
            System.out.println("Stack Overflow");
        } else {
            stk[++top] = element;
        }
    }

    // Pop
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return -1;
        } else {
            return stk[top--];
        }
    }

    // Evaluate Postfix Expression
    public void evaluate(String expr) {
        Stack s = new Stack(expr.length());

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (Character.isDigit(ch)) {
                if (s.isFull()) {
                    System.out.println("Stack Overflow");
                    System.out.println("Invalid postfix expression");
                    return;
                }
                s.push(ch - '0'); // convert char digit to int
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                int val2 = s.pop();
                if (val2 == -1 && s.isEmpty()) {
                    System.out.println("Invalid postfix expression");
                    return;
                }
                int val1 = s.pop();
                if (val1 == -1 && s.isEmpty()) {
                    System.out.println("Invalid postfix expression");
                    return;
                }

                switch (ch) {
                    case '+':
                        s.push(val1 + val2);
                        break;
                    case '-':
                        s.push(val1 - val2);
                        break;
                    case '*':
                        s.push(val1 * val2);
                        break;
                    case '/':
                        if (val2 == 0) {
                            System.out.println("Division by zero error");
                            return;
                        }
                        s.push(val1 / val2);
                        break;
                }
            } else {
                System.out.println("Invalid operator: " + ch);
                return;
            }
        }

        if (s.top != 0) {
            System.out.println("Invalid postfix expression");
        } else {
            System.out.println("Postfix Evaluation Result: " + s.pop());
        }
    }

    // Reverse a String
    public void reverse(String str) {
        Stack s = new Stack(str.length());

        for (int i = 0; i < str.length(); i++) {
            if (s.isFull()) {
                System.out.println("Stack Overflow while pushing characters");
                return;
            }
            s.push(str.charAt(i));
        }

        System.out.print("Reversed String: ");
        while (!s.isEmpty()) {
            System.out.print((char) s.pop());
        }
        System.out.println();
    }
}

public class Assignment_05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char ans;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Evaluate Postfix Expression");
            System.out.println("2. Reverse a String");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Postfix Expression: ");
                    String expr = sc.nextLine();
                    Stack evalStack = new Stack(expr.length());
                    evalStack.evaluate(expr);
                    break;

                case 2:
                    System.out.print("Enter a String: ");
                    String str = sc.nextLine();
                    Stack revStack = new Stack(str.length());
                    revStack.reverse(str);
                    break;

                default:
                    System.out.println("Enter Valid Option!!");
            }

            System.out.print("Would you like to continue? (Y/N): ");
            ans = sc.next().charAt(0);

        } while (ans == 'Y' || ans == 'y');

        sc.close();
    }
}

// OUTPUT:

// --- MENU ---
// 1. Evaluate Postfix Expression
// 2. Reverse a String
// Enter your choice: 1
// Enter Postfix Expression: 23+
// Postfix Evaluation Result: 5
// Would you like to continue? (Y/N): y

// --- MENU ---
// 1. Evaluate Postfix Expression
// 2. Reverse a String
// Enter your choice: 1
// Enter Postfix Expression: 2+
// Stack Underflow
// Invalid postfix expression
// Would you like to continue? (Y/N): y

// --- MENU ---
// 1. Evaluate Postfix Expression
// 2. Reverse a String
// Enter your choice: 1
// Enter Postfix Expression: 234+
// Invalid postfix expression
// Would you like to continue? (Y/N): y

// --- MENU ---
// 1. Evaluate Postfix Expression
// 2. Reverse a String
// Enter your choice: 1
// Enter Postfix Expression: 20/
// Division by zero error
// Would you like to continue? (Y/N): y

// --- MENU ---
// 1. Evaluate Postfix Expression
// 2. Reverse a String
// Enter your choice: 1
// Enter Postfix Expression: 23$
// Invalid operator: $
// Would you like to continue? (Y/N): y

// --- MENU ---
// 1. Evaluate Postfix Expression
// 2. Reverse a String
// Enter your choice: 2
// Enter a String: SadiaNouf
// Reversed String: fouNaidaS
// Would you like to continue? (Y/N): y
