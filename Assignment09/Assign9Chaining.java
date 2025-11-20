package Assignment09;

import java.util.*;

class Contact {
    long mobile_no;
    String name;
    String email;
    Contact next;

    public Contact(long number, String name, String email) {
        this.mobile_no = number;
        this.name = name;
        this.email = email;
        this.next = null;
    }
}

class ContactListChaining {
    int size;
    Contact[] table;

    ContactListChaining(int n) {
        size = n;
        table = new Contact[size];
    }

    int hash(long key) {
        return (int) (Math.abs(key % size));
    }

    boolean validName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    boolean validMobile(long num) {
        return String.valueOf(num).matches("\\d{10}");
    }

    boolean validEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    boolean exists(long number) {
        int index = hash(number);
        Contact temp = table[index];

        while (temp != null) {
            if (temp.mobile_no == number)
                return true;
            temp = temp.next;
        }
        return false;
    }

    public void insert() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Mobile: ");
        long number = sc.nextLong();
        sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        if (!validName(name) || !validMobile(number) || !validEmail(email)) {
            System.out.println("Invalid input");
            return;
        }
        if (exists(number)) {
            System.out.println("Contact already exists");
            return;
        }

        Contact newCon = new Contact(number, name, email);
        int index = hash(number);

        newCon.next = table[index];
        table[index] = newCon;

        System.out.println("Inserted at bucket " + index);
    }

    public void searchNumber() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        long number = sc.nextLong();

        int index = hash(number);
        Contact temp = table[index];

        while (temp != null) {
            if (temp.mobile_no == number) {
                System.out.println("Found: " + temp.name + " | " + temp.mobile_no + " | " + temp.email);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Not found");
    }

    public void searchName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        for (int i = 0; i < size; i++) {
            Contact temp = table[i];
            while (temp != null) {
                if (temp.name.equalsIgnoreCase(name)) {
                    System.out.println("Found: " + temp.name + " | " + temp.mobile_no + " | " + temp.email);
                    return;
                }
                temp = temp.next;
            }
        }
        System.out.println("Not found");
    }

    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number to delete: ");
        long number = sc.nextLong();

        int index = hash(number);
        Contact temp = table[index];
        Contact prev = null;

        while (temp != null) {
            if (temp.mobile_no == number) {
                if (prev == null)
                    table[index] = temp.next;
                else
                    prev.next = temp.next;

                System.out.println("Deleted");
                return;
            }
            prev = temp;
            temp = temp.next;
        }
        System.out.println("Not found");
    }

    public void display() {
        System.out.println("\n== Hash Table (Chaining) ==");
        for (int i = 0; i < size; i++) {
            System.out.print(i + " -> ");
            Contact temp = table[i];
            if (temp == null) {
                System.out.println("NULL");
                continue;
            }
            while (temp != null) {
                System.out.print("[" + temp.name + " | " + temp.mobile_no + " | " + temp.email + "] -> ");
                temp = temp.next;
            }
            System.out.println("NULL");
        }
    }
}

public class Assign9Chaining {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Hash size: ");
        int size = sc.nextInt();

        ContactListChaining ch = new ContactListChaining(size);
        int c;

        do {
            System.out.println("\n1.Insert 2.Display 3.Search Name 4.Search Number 5.Delete 0.Exit");
            System.out.print("Choice: ");
            c = sc.nextInt();
            sc.nextLine();

            switch (c) {
                case 1 -> ch.insert();
                case 2 -> ch.display();
                case 3 -> ch.searchName();
                case 4 -> ch.searchNumber();
                case 5 -> ch.delete();
                case 0 -> System.out.println("done");
                default -> System.out.println("wrong choice");
            }
        } while (c != 0);
    }
}
