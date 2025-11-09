package Assignment09;
// The provided code is a Java program that implements a mobile phone contact list. It allows users to

// perform the following operations:
// Name: Sadia Sakharkar
// Roll No: UCE2025002
// Batch: A4
// Assignment 09

// Problem Statement: Design mobile phone contact list which stores name and contact number in ascending order.
// Develop a code to search a particular contact details of specified name, insert new contact and
// delete particular contact from list

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
    int size = 10;
    Contact hashTable[] = new Contact[size];

    // Hash by phone number since it's unique
    int hash(long num) {
        return Math.abs(Long.valueOf(num).hashCode()) % size;
    }

    public void create() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Mobile Number: ");
        long num = sc.nextLong();
        sc.nextLine();

        System.out.print("Enter name : ");
        String name = sc.nextLine();

        System.out.print("Enter Email ID: ");
        String email = sc.nextLine();

        Contact newCon = new Contact(num, name, email);
        int index = hash(num);
        int startIndex = index;

        while (hashTable[index] != null) {
            index = (index + 1) % size;
            if (index == startIndex) {
                System.out.println("Hash table full. Cannot insert contact.");
                return;
            }
        }
        hashTable[index] = newCon;
        System.out.println("Contact inserted successfully.");
    }

    public void search() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Mobile Number to search: ");
        long num = sc.nextLong();

        int index = hash(num);
        int startIndex = index;

        while (hashTable[index] != null) {
            if (hashTable[index].mobile_no == num) {
                Contact c = hashTable[index];
                System.out.println("Contact Found: " + c.name + " | " + c.mobile_no + " | " + c.email);
                return;
            }
            index = (index + 1) % size;
            if (index == startIndex)
                break;
        }
        System.out.println("Contact not found.");
    }

    void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Mobile Number to delete: ");
        long num = sc.nextLong();

        int index = hash(num);
        int startIndex = index;

        while (hashTable[index] != null) {
            if (hashTable[index].mobile_no == num) {
                hashTable[index] = null;
                System.out.println("Contact deleted successfully.");
                return;
            }
            index = (index + 1) % size;
            if (index == startIndex)
                break;
        }
        System.out.println("Contact not found.");
    }

    void display() {
        ArrayList<Contact> list = new ArrayList<>();
        for (Contact c : hashTable)
            if (c != null)
                list.add(c);

        if (list.isEmpty()) {
            System.out.println("Contact list is empty.");
            return;
        }

        list.sort(Comparator.comparing(c -> c.name));
        System.out.println("Contacts (sorted by name):");
        for (Contact c : list)
            System.out.println(c.name + " | " + c.mobile_no + " | " + c.email);
    }
}

public class Assignment9 {
    public static void main(String[] args) {
        ContactList t = new ContactList();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. INSERT  2. DISPLAY  3. SEARCH  4. DELETE  0. EXIT");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    t.create();
                    break;
                case 2:
                    t.display();
                    break;
                case 3:
                    t.search();
                    break;
                case 4:
                    t.delete();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}

// Output:
// 1. INSERT 2. DISPLAY 3. SEARCH 4. DELETE 0. EXIT
// Enter your choice: 1
// Enter Mobile Number: 9876543210
// Enter name : Sadi
// Enter Email ID: sadi@gmail.com
// Contact inserted successfully.

// 1. INSERT 2. DISPLAY 3. SEARCH 4. DELETE 0. EXIT
// Enter your choice: 1
// Enter Mobile Number: 9123456789
// Enter name : Arya
// Enter Email ID: arya@gmail.com
// Contact inserted successfully.

// 1. INSERT 2. DISPLAY 3. SEARCH 4. DELETE 0. EXIT
// Enter your choice: 2
// Contacts (sorted by name):
// Arya | 9123456789 | arya@gmail.com
// Sadi | 9876543210 | sadi@gmail.com

// 1. INSERT 2. DISPLAY 3. SEARCH 4. DELETE 0. EXIT
// Enter your choice: 3
// Enter Mobile Number to search: 9876543210
// Contact Found: Sadi | 9876543210 | sadi@gmail.com

// 1. INSERT 2. DISPLAY 3. SEARCH 4. DELETE 0. EXIT
// Enter your choice: 4
// Enter Mobile Number to delete: 9123456789
// Contact deleted successfully.

// 1. INSERT 2. DISPLAY 3. SEARCH 4. DELETE 0. EXIT
// Enter your choice: 2
// Contacts (sorted by name):
// Sadi | 9876543210 | sadi@gmail.com

// 1. INSERT 2. DISPLAY 3. SEARCH 4. DELETE 0. EXIT
// Enter your choice: 0
// Exiting...
