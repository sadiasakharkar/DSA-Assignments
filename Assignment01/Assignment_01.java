package Assignment01;
/* 
Name: Sadia Sakharkar
Roll No: UCE2025002
Batch: A4
Assignment 01

Problem Statement:
College Library maintains records of books. Write a program using JAVA for the following operations:
1. Add a new book in a library having existing books.
2. Search a book in library based on ISBN number (Binary search).
3. Sort books based on ISBN numbers (Bubble or Insertion sort, Quick sort).
4. List all books of specific author (Linear search).
Use appropriate data structure.
*/

import java.util.*;

// Book class
class Book {
    String BookName;
    String AuthorName;
    int ISBNNo;

    Book(String bookName, String authorName, int isbnNo) {
        this.BookName = bookName;
        this.AuthorName = authorName;
        this.ISBNNo = isbnNo;
    }

    @Override
    public String toString() {
        return "Book Name: " + BookName + ", Author: " + AuthorName + ", ISBN: " + ISBNNo;
    }
}

// Library class
class Library {
    static Scanner sc = new Scanner(System.in);
    Book[] theBooks = new Book[50];
    int count = 0;

    // Add book
    void addBook() {
        if (count >= theBooks.length) {
            System.out.println("Library is full.");
            return;
        }

        System.out.print("Enter Book Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();
        System.out.print("Enter ISBN No: ");
        int isbn = sc.nextInt();
        sc.nextLine();
        theBooks[count++] = new Book(name, author, isbn);
        System.out.println("Book Added Successfully!");
    }

    // Display
    void displayBooks() {
        if (count == 0) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\n--- Book List ---");
        for (int i = 0; i < count; i++) {
            System.out.println(theBooks[i]);
        }
    }

    // Binary Search
    void searchISBN_Binary() {
        if (count == 0) {
            System.out.println("No books available.");
            return;
        }

        // Sort first
        bubbleSort();

        System.out.print("Enter ISBN to search: ");
        int key = sc.nextInt();
        sc.nextLine();

        int low = 0, high = count - 1, foundIndex = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (theBooks[mid].ISBNNo == key) {
                foundIndex = mid;
                break;
            } else if (theBooks[mid].ISBNNo < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (foundIndex != -1)
            System.out.println("Book Found: " + theBooks[foundIndex]);
        else
            System.out.println("Book not found.");
    }

    // Linear Search by Author
    void searchAuthor_Linear() {
        if (count == 0) {
            System.out.println("No books available.");
            return;
        }
        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (theBooks[i].AuthorName.equalsIgnoreCase(author)) {
                System.out.println(theBooks[i]);
                found = true;
            }
        }
        if (!found)
            System.out.println("No books found by " + author);
    }

    // Sorting Options
    void sortBooksByISBN() {
        if (count == 0) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\nChoose Sorting Algorithm:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Insertion Sort");
        System.out.println("3. Quick Sort");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> bubbleSort();
            case 2 -> insertionSort();
            case 3 -> quickSort(0, count - 1);
            default -> System.out.println("Invalid choice.");
        }

        System.out.println("Books sorted by ISBN successfully.");
        displayBooks();
    }

    // Bubble Sort
    void bubbleSort() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (theBooks[j].ISBNNo > theBooks[j + 1].ISBNNo) {
                    Book temp = theBooks[j];
                    theBooks[j] = theBooks[j + 1];
                    theBooks[j + 1] = temp;
                }
            }
        }
    }

    // Insertion Sort

    void insertionSort() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (theBooks[j].ISBNNo < theBooks[j - 1].ISBNNo) {
                    Book temp = theBooks[j];
                    theBooks[j] = theBooks[j - 1];
                    theBooks[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
    }

    // void insertionSort() {
    // for (int i = 1; i < count; i++) {
    // Book key = theBooks[i];
    // int j = i - 1;
    // while (j >= 0 && theBooks[j].ISBNNo > key.ISBNNo) {
    // theBooks[j + 1] = theBooks[j];
    // j--;
    // }
    // theBooks[j + 1] = key;
    // }
    // }

    // Quick Sort
    void quickSort(int low, int high) {
        if (low < high) {
            int pivot = partition(low, high);
            quickSort(low, pivot - 1);
            quickSort(pivot + 1, high);
        }
    }

    int partition(int low, int high) {
        int pivot = theBooks[high].ISBNNo;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (theBooks[j].ISBNNo < pivot) {
                i++;
                Book temp = theBooks[i];
                theBooks[i] = theBooks[j];
                theBooks[j] = temp;
            }
        }
        i++;
        Book temp = theBooks[i];
        theBooks[i] = theBooks[high];
        theBooks[high] = temp;
        return i;
    }
}

// Main
public class Assignment_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        int choice;
        do {
            System.out.println("\n- Library Menu -");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search by ISBN (Binary Search)");
            System.out.println("4. List all books by Author");
            System.out.println("5. Sort Books by ISBN");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> lib.addBook();
                case 2 -> lib.displayBooks();
                case 3 -> lib.searchISBN_Binary();
                case 4 -> lib.searchAuthor_Linear();
                case 5 -> lib.sortBooksByISBN();
                case 0 -> System.out.println("Exiting... Thank you!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
        sc.close();
    }
}
