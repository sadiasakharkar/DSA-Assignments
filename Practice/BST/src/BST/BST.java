package BST;

import java.util.*;

class Node {
    String word, meaning;
    Node left, right;

    Node(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
        this.left = this.right = null;
    }
}

class Binary {

    Node root;
    Scanner sc = new Scanner(System.in);

    Binary() {
        root = null;
    }

    // INSERT
    Node insert(Node root, String word, String meaning) {
        if (root == null) {
            return new Node(word, meaning);
        }

        if (word.compareToIgnoreCase(root.word) < 0)
            root.left = insert(root.left, word, meaning);
        else if (word.compareToIgnoreCase(root.word) > 0)
            root.right = insert(root.right, word, meaning);
        else
            System.out.println("Keyword already exists!");

        return root;
    }

    // CREATE
    void create() {
        sc.nextLine();
        String choice;

        do {
            System.out.println("Enter keyword:");
            String word = sc.nextLine();

            System.out.println("Enter meaning:");
            String meaning = sc.nextLine();

            root = insert(root, word, meaning);

            System.out.println("Do you want to add more? (yes/no)");
            choice = sc.nextLine();

        } while (choice.equalsIgnoreCase("yes"));
    }

    // INORDER TRAVERSAL
    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root.word + " : " + root.meaning);
            inorder(root.right);
        }
    }

    void display() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        System.out.println("Displaying tree:");
        inorder(root);
    }

    // SEARCH NODE
    Node searchNode(Node root, String key) {
        if (root == null)
            return null;

        if (key.compareToIgnoreCase(root.word) == 0)
            return root;

        if (key.compareToIgnoreCase(root.word) < 0)
            return searchNode(root.left, key);
        else
            return searchNode(root.right, key);
    }

    // SEARCH
    void search() {
        sc.nextLine();
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        System.out.println("Enter keyword to search:");
        String key = sc.nextLine();

        Node res = searchNode(root, key);
        if (res == null)
            System.out.println("Keyword not found!");
        else
            System.out.println("Meaning: " + res.meaning);
    }

    // UPDATE
    void update() {
        sc.nextLine();
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }

        System.out.println("Enter keyword to update: ");
        String key = sc.nextLine();

        Node res = searchNode(root, key);
        if (res == null) {
            System.out.println("Keyword not found!");
        } else {
            System.out.println("Enter new meaning:");
            String newMeaning = sc.nextLine();
            res.meaning = newMeaning;
            System.out.println("Meaning updated!");
        }
    }

    // DELETE
    void delete(String key) {
        root = deleteNode(root, key);
    }

    Node deleteNode(Node root, String key) {
        if (root == null) {
            System.out.println("Keyword not found!");
            return null;
        }

        if (key.compareToIgnoreCase(root.word) < 0) {
            root.left = deleteNode(root.left, key);
        }
        else if (key.compareToIgnoreCase(root.word) > 0) {
            root.right = deleteNode(root.right, key);
        }
        else {
            // CASE 1: LEAF
            if (root.left == null && root.right == null) {
                return null;
            }
            // CASE 2: ONLY RIGHT
            else if (root.left == null) {
                return root.right;
            }
            // CASE 3: ONLY LEFT
            else if (root.right == null) {
                return root.left;
            }
            // CASE 4: TWO CHILDREN
            else {
                Node pred = findmax(root.left);
                root.word = pred.word;
                root.meaning = pred.meaning;
                root.left = deleteNode(root.left, pred.word);
            }
        }
        return root;
    }

    Node findmax(Node root) {
        while (root.right != null)
            root = root.right;
        return root;
    }
}

public class BST {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Binary b = new Binary();
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
                    b.create();
                    break;
                case 2:
                    b.display();
                    break;
                case 3:
                    b.search();
                    break;
                case 4:
                    b.update();
                    break;
                case 5:
                    System.out.print("Enter keyword to delete: ");
                    String key = sc.next();
                    b.delete(key);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
    }
}
