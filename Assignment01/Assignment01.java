package Assignment01;

import java.util.*;

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

class Library {
    static Scanner sc = new Scanner(System.in);
    Book theBooks[] = new Book[50];
    int count = 0;

    void Accept_Book_Details() {
        System.out.print("Enter Book Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();
        System.out.print("Enter ISBN No: ");
        int isbn = sc.nextInt();
        sc.nextLine(); // consume newline

        theBooks[count++] = new Book(name, author, isbn);
        System.out.println("Book Added Successfully!\n");
    }

    void Display_Book_Details() {
        if (count == 0) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\n--- Book List ---");
        for (int i = 0; i < count; i++) {
            System.out.println(theBooks[i]);
        }
    }

    // Binary Search by ISBN (requires sorted array)
    void search_ISBN() {
        if (count == 0) {
            System.out.println("No books available.");
            return;
        }

        sort_books_auto(); // Bubble sort by default for safety

        System.out.print("Enter ISBN to search: ");
        int key = sc.nextInt();
        sc.nextLine();

        int low = 0, high = count - 1;
        boolean found = false;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (theBooks[mid].ISBNNo == key) {
                System.out.println("Book Found: " + theBooks[mid]);
                found = true;
                break;
            } else if (theBooks[mid].ISBNNo < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (!found)
            System.out.println("Book not found.");
    }

    // Linear Search by Author Name
    void search_Author() {
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

    void sort_books_auto() { // auto bubble sort
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

    void sort_books() {
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
        System.out.println("Books sorted by ISBN successfully.\n");
    }

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

    void insertionSort() {
        for (int i = 1; i < count; i++) {
            Book key = theBooks[i];
            int j = i - 1;
            while (j >= 0 && theBooks[j].ISBNNo > key.ISBNNo) {
                theBooks[j + 1] = theBooks[j];
                j--;
            }
            theBooks[j + 1] = key;
        }
    }

    void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    int partition(int low, int high) {
        int pivot = theBooks[high].ISBNNo;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (theBooks[j].ISBNNo <= pivot) {
                i++;
                Book temp = theBooks[i];
                theBooks[i] = theBooks[j];
                theBooks[j] = temp;
            }
        }
        Book temp = theBooks[i + 1];
        theBooks[i + 1] = theBooks[high];
        theBooks[high] = temp;
        return i + 1;
    }
}

public class Assignment01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library ob = new Library();
        int choice;

        do {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search by ISBN");
            System.out.println("4. Search by Author");
            System.out.println("5. Sort Books by ISBN");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> ob.Accept_Book_Details();
                case 2 -> ob.Display_Book_Details();
                case 3 -> ob.search_ISBN();
                case 4 -> ob.search_Author();
                case 5 -> ob.sort_books();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
        sc.close();
    }
}
