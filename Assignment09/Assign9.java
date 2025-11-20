package Assignment09;

// Name: Sadia Sakharkar
// Batch: A4
// Assignment 09

// Problem Statement: Design mobile phone contact list which stores name and contact number in ascending order.
// Develop a code to search a particular contact details of specified name, insert new contact and
// delete particular contact from list

// linear probing (open addressing technique) for collision handling in hash table without replacement.

import java.util.*;

class Contact {
    long mobile_no;
    String name;
    String email;

    public Contact(long number, String name, String email) {
        this.mobile_no = number;
        this.name = name;
        this.email = email;
    }
}

class ContactList {
    Scanner sc = new Scanner(System.in);

    private static final Contact DELETED = new Contact(-1, "DELETED", "");

    int size;
    Contact[] hashTable;

    ContactList(int s) {
        this.size = s;
        hashTable = new Contact[size];
    }

    int hash(long key) {
        return (int) (Math.abs(key % size));
    }

    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private boolean isValidMobile(long num) {
        return String.valueOf(num).matches("\\d{10}");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    private boolean exists(long number) {
        int index = hash(number);
        int start = index;
        while (hashTable[index] != null) {
            if (hashTable[index] != DELETED && hashTable[index].mobile_no == number)
                return true;
            index = (index + 1) % size;
            if (index == start)
                break;
        }
        return false;
    }

    public void create() {

        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Mobile Number: ");
        long num = sc.nextLong();
        sc.nextLine();

        System.out.print("Enter Email ID: ");
        String email = sc.nextLine().trim();

        if (!isValidName(name)) {
            System.out.println("Invalid name.");
            return;
        }
        if (!isValidMobile(num)) {
            System.out.println("Invalid mobile number (must be 10 digits).");
            return;
        }
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return;
        }
        if (exists(num)) {
            System.out.println("Contact with this number already exists.");
            return;
        }

        Contact newCon = new Contact(num, name, email);
        int index = hash(num);
        int startIndex = index;

        boolean collision = false;
        while (hashTable[index] != null && hashTable[index] != DELETED) {
            collision = true;
            int oldIndex = index;
            index = (index + 1) % size;

            System.out.println("Collision at index " + oldIndex + " → trying next slot...");
            if (index == startIndex) {
                System.out.println("Hash table full. Cannot insert contact.");
                return;
            }
        }

        hashTable[index] = newCon;

        if (collision) {
            System.out.println("Collision resolved. Contact stored at index " + index);
        } else {
            System.out.println("Contact inserted at index " + index);
        }
    }

    public void search_by_name() {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine();

        boolean found = false;
        for (Contact c : hashTable) {
            if (c != null && c != DELETED && c.name.equalsIgnoreCase(name)) {
                System.out.printf("Found: %-10s | %-15d | %-20s%n",
                        c.name, c.mobile_no, c.email);
                found = true;
            }
        }
        if (!found)
            System.out.println("Contact not found.");
    }

    public void search() {
        System.out.print("Enter number to search: ");
        long number = sc.nextLong();

        int index = hash(number);
        int startIndex = index;

        while (hashTable[index] != null) {
            if (hashTable[index] != DELETED && hashTable[index].mobile_no == number) {
                Contact c = hashTable[index];
                System.out.printf("Found: %-10s | %-15d | %-20s%n",
                        c.name, c.mobile_no, c.email);
                return;
            }
            index = (index + 1) % size;
            if (index == startIndex)
                break;
        }
        System.out.println("Contact not found.");
    }

    void delete() {
        System.out.print("Enter number to delete: ");
        long number = sc.nextLong();

        int index = hash(number);
        int startIndex = index;

        while (hashTable[index] != null) {
            if (hashTable[index] != DELETED && hashTable[index].mobile_no == number) {
                hashTable[index] = DELETED;
                System.out.println("Contact deleted.");
                return;
            }
            index = (index + 1) % size;
            if (index == startIndex)
                break;
        }
        System.out.println("Contact not found.");
    }

    void display() {
        System.out.println("\n======= HASH TABLE CONTENTS =======");
        System.out.printf("%-8s | %-15s | %-12s | %-20s%n", "Index", "Name", "Mobile No", "Email");
        System.out.println("-----------------------------------------------------------------------");

        for (int i = 0; i < size; i++) {
            Contact c = hashTable[i];
            if (c == null)
                System.out.printf("%-8d | %-15s | %-12s | %-20s%n", i, "NULL", "-", "-");
            else if (c == DELETED)
                System.out.printf("%-8d | %-15s | %-12s | %-20s%n", i, "DELETED", "-", "-");
            else
                System.out.printf("%-8d | %-15s | %-12d | %-20s%n", i, c.name, c.mobile_no, c.email);
        }
    }

}

public class Assign9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of hash table: ");
        int size = sc.nextInt();

        ContactList t = new ContactList(size);
        int choice;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. INSERT");
            System.out.println("2. DISPLAY");
            System.out.println("3. SEARCH BY NAME");
            System.out.println("4. SEARCH BY NUMBER");
            System.out.println("5. DELETE BY NUMBER");
            System.out.println("0. EXIT");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> t.create();
                case 2 -> t.display();
                case 3 -> t.search_by_name();
                case 4 -> t.search();
                case 5 -> t.delete();
                case 0 -> System.out.println("Goodbye.");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 0);
        sc.close();
    }
}
