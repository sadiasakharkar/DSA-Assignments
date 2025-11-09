package Assignment07;
// Name: Sadia Sakharkar

// Roll No: UCE2025002
// Batch: A4
// Assignment 07

// Problem Statement:
// Create a Dictionary that stores keywords and its meanings, using appropriate data structure.
// Implement its operations such as add, delete, display, search and update its values.

import java.util.Scanner;

// import Assignment03.Node;

// Node class for BST
class Node {
    String word, meaning;
    Node left, right;

    Node(String w, String m) {
        word = w;
        meaning = m;
        left = null;
        right = null;
    }
}

// Binary Search Tree class for dictionary operations
class Binary {
    Node root;
    Scanner sc = new Scanner(System.in);

    Binary() {
        root = null;
    }

    // Insert a new node into BST
    Node insert(Node root, String w, String m) {
        if (root == null) {
            root = new Node(w, m);
            System.out.println("Added: " + w);
            return root;
        }
        if (w.compareToIgnoreCase(root.word) < 0) {
            root.left = insert(root.left, w, m);
        } else if (w.compareToIgnoreCase(root.word) > 0) {
            root.right = insert(root.right, w, m);
        } else {
            System.out.println("Keyword already exists!");
        }
        return root;
    }

    // Create method - repeatedly add keywords
    void create() {
        String choice;
        do {
            System.out.print("Enter keyword: ");
            String w = sc.next();
            System.out.print("Enter meaning: ");
            sc.nextLine(); // consume leftover newline
            String m = sc.nextLine();

            root = insert(root, w, m);

            System.out.print("Add more? (yes/no): ");
            choice = sc.next();
        } while (choice.equalsIgnoreCase("yes"));
    }

    // Display the dictionary in sorted order (inorder traversal)
    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root.word + " : " + root.meaning);
            inorder(root.right);
        }
    }

    void display() {
        if (root == null) {
            System.out.println("Dictionary is empty!");
            return;
        }
        System.out.println("\nDictionary contents:");
        inorder(root);
    }

    // Search for a keyword
    void search() {
        if (root == null) {
            System.out.println("Dictionary is empty!");
            return;
        }
        System.out.print("Enter keyword to search: ");
        String key = sc.next();
        Node res = searchNode(root, key);
        if (res == null) {
            System.out.println("Keyword not found!");
        } else {
            System.out.println("Meaning: " + res.meaning);
        }
    }

    Node searchNode(Node root, String key) {
        if (root == null)
            return null;
        if (key.equalsIgnoreCase(root.word))
            return root;
        if (key.compareToIgnoreCase(root.word) < 0)
            return searchNode(root.left, key);
        else
            return searchNode(root.right, key);
    }

    // Update meaning of a keyword
    void update() {
        if (root == null) {
            System.out.println("Dictionary is empty!");
            return;
        }
        System.out.print("Enter keyword to update: ");
        String key = sc.next();
        Node res = searchNode(root, key);
        if (res == null) {
            System.out.println("Keyword not found!");
        } else {
            System.out.print("Enter new meaning: ");
            sc.nextLine();
            String newMeaning = sc.nextLine();
            res.meaning = newMeaning;
            System.out.println("Updated successfully.");
        }
    }

    // Delete a keyword from BST
    void delete1(String key) {
        root = deleteNode(root, key);
    }

    Node deleteNode(Node root, String key) {
        if (root == null) {
            System.out.println("Keyword not found!");
            return root;
        }
        if (key.compareToIgnoreCase(root.word) < 0) {
            root.left = deleteNode(root.left, key);
        } else if (key.compareToIgnoreCase(root.word) > 0) {
            root.right = deleteNode(root.right, key);
        } else {
            // Found node to delete
            if (root.left == null && root.right == null) {
                // Leaf node
                root = null;
                System.out.println("Deleted leaf node.");
            } else if (root.left == null) {
                // Only right child
                root = root.right;
                System.out.println("Deleted node with only right child.");
            } else if (root.right == null) {
                // Only left child
                root = root.left;
                System.out.println("Deleted node with only left child.");
            } else {
                // Two children: find inorder predecessor (max in left subtree)
                Node pred = findMax(root.left);
                root.word = pred.word;
                root.meaning = pred.meaning;
                root.left = deleteNode(root.left, pred.word);
                System.out.println("Deleted node with two children.");
            }
        }
        return root;
    }

    // Find maximum node in subtree (rightmost node)
    Node findMax(Node root) {
        while (root.right != null)
            root = root.right;
        return root;
    }
}

// Main class with menu
public class Assignment_07 {
    public static void main(String[] args) {
        Binary dictionary = new Binary();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n** MENU **");
            System.out.println("0) Quit");
            System.out.println("1) Create/Add keywords");
            System.out.println("2) Display dictionary");
            System.out.println("3) Search keyword");
            System.out.println("4) Update keyword");
            System.out.println("5) Delete keyword");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("Terminated.");
                    break;
                case 1:
                    dictionary.create();
                    break;
                case 2:
                    dictionary.display();
                    break;
                case 3:
                    dictionary.search();
                    break;
                case 4:
                    dictionary.update();
                    break;
                case 5:
                    System.out.print("Enter keyword to delete: ");
                    String key = sc.next();
                    dictionary.delete1(key);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
    }
}

// OUTPUT:

// ** MENU **
// 0) Quit
// 1) Create/Add keywords
// 2) Display dictionary
// 3) Search keyword
// 4) Update keyword
// 5) Delete keyword
// Enter choice: 1
// Enter keyword: cat
// Enter meaning: animal
// Added: cat
// Add more? (yes/no): yes
// Enter keyword: apple
// Enter meaning: fruit
// Added: apple
// Add more? (yes/no): yes
// Enter keyword: eel
// Enter meaning: fish
// Added: eel
// Add more? (yes/no): yes
// Enter keyword: banana
// Enter meaning: yellow fruit
// Added: banana
// Add more? (yes/no): no

// ** MENU **
// 0) Quit
// 1) Create/Add keywords
// 2) Display dictionary
// 3) Search keyword
// 4) Update keyword
// 5) Delete keyword
// Enter choice: 2

// Dictionary contents:
// apple : fruit
// banana : yellow fruit
// cat : animal
// eel : fish

// ** MENU **
// 0) Quit
// 1) Create/Add keywords
// 2) Display dictionary
// 3) Search keyword
// 4) Update keyword
// 5) Delete keyword
// Enter choice: 3
// Enter keyword to search: apple
// Meaning: fruit

// ** MENU **
// 0) Quit
// 1) Create/Add keywords
// 2) Display dictionary
// 3) Search keyword
// 4) Update keyword
// 5) Delete keyword
// Enter choice: 4
// Enter keyword to update: apple
// Enter new meaning: sweet fruit
// Updated successfully.

// ** MENU **
// 0) Quit
// 1) Create/Add keywords
// 2) Display dictionary
// 3) Search keyword
// 4) Update keyword
// 5) Delete keyword
// Enter choice: 5
// Enter keyword to delete: eel
// Deleted leaf node.

// ** MENU **
// 0) Quit
// 1) Create/Add keywords
// 2) Display dictionary
// 3) Search keyword
// 4) Update keyword
// 5) Delete keyword
// Enter choice: 2

// Dictionary contents:
// apple : sweet fruit
// banana : yellow fruit
// cat : animal

// ** MENU **
// 0) Quit
// 1) Create/Add keywords
// 2) Display dictionary
// 3) Search keyword
// 4) Update keyword
// 5) Delete keyword
// Enter choice: