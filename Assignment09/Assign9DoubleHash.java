package Assignment09;

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

class ContactListDoubleHash {
    private static final Contact DELETED = new Contact(-1, "DELETED", "");
    int size;
    Contact[] table;

    ContactListDoubleHash(int n) {
        size = n;
        table = new Contact[size];
    }

    int hash1(long key) {
        return (int) (Math.abs(key % size));
    }

    int hash2(long key) {
        return 7 - (int) (Math.abs(key % 7));
    }

    private boolean validName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private boolean validMobile(long num) {
        return String.valueOf(num).matches("\\d{10}");
    }

    private boolean validEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    private boolean exists(long number) {
        int h1 = hash1(number);
        int h2 = hash2(number);
        int i = 0;

        while (table[(h1 + i * h2) % size] != null) {
            int pos = (h1 + i * h2) % size;

            if (table[pos] != DELETED && table[pos].mobile_no == number)
                return true;

            i++;
            if (i == size)
                break;
        }
        return false;
    }

    public void insert() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Mobile: ");
        long num = sc.nextLong();
        sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        if (!validName(name) || !validMobile(num) || !validEmail(email)) {
            System.out.println("Invalid input");
            return;
        }
        if (exists(num)) {
            System.out.println("Contact already exists");
            return;
        }

        Contact c = new Contact(num, name, email);

        int h1 = hash1(num);
        int h2 = hash2(num);
        int i = 0;

        while (i < size) {
            int pos = (h1 + i * h2) % size;
            if (table[pos] == null || table[pos] == DELETED) {
                table[pos] = c;
                System.out.println("Inserted at " + pos);
                return;
            }
            System.out.println("Collision at " + pos + " step: " + i);
            i++;
        }
        System.out.println("Table full");
    }

    public void searchNumber() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        long number = sc.nextLong();

        int h1 = hash1(number);
        int h2 = hash2(number);
        int i = 0;

        while (i < size) {
            int pos = (h1 + i * h2) % size;
            if (table[pos] == null)
                break;
            if (table[pos] != DELETED && table[pos].mobile_no == number) {
                Contact c = table[pos];
                System.out.println("Found: " + c.name + " | " + c.mobile_no + " | " + c.email);
                return;
            }
            i++;
        }
        System.out.println("Not found");
    }

    public void searchName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        for (Contact c : table) {
            if (c != null && c != DELETED && c.name.equalsIgnoreCase(name)) {
                System.out.println("Found: " + c.name + " | " + c.mobile_no + " | " + c.email);
                return;
            }
        }
        System.out.println("Not found");
    }

    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number to delete: ");
        long number = sc.nextLong();

        int h1 = hash1(number);
        int h2 = hash2(number);
        int i = 0;

        while (i < size) {
            int pos = (h1 + i * h2) % size;
            if (table[pos] == null)
                break;

            if (table[pos] != DELETED && table[pos].mobile_no == number) {
                table[pos] = DELETED;
                System.out.println("Deleted");
                return;
            }
            i++;
        }
        System.out.println("Not found");
    }

    public void display() {
        System.out.println("\nIndex | Name | Number | Email");
        System.out.println("-----------------------------------");
        for (int i = 0; i < size; i++) {
            if (table[i] == null)
                System.out.println(i + " | NULL");
            else if (table[i] == DELETED)
                System.out.println(i + " | <DELETED>");
            else
                System.out.println(i + " | " + table[i].name + " | " + table[i].mobile_no + " | " + table[i].email);
        }
    }
}

public class Assign9DoubleHash {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size: ");
        int size = sc.nextInt();

        ContactListDoubleHash d = new ContactListDoubleHash(size);

        int ch;
        do {
            System.out.println("\n1.Insert 2.Display 3.Search Name 4.Search Number 5.Delete 0.Exit");
            System.out.print("Choice: ");
            ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> d.insert();
                case 2 -> d.display();
                case 3 -> d.searchName();
                case 4 -> d.searchNumber();
                case 5 -> d.delete();
                case 0 -> System.out.println("Done");
                default -> System.out.println("Invalid");
            }
        } while (ch != 0);
    }
}
