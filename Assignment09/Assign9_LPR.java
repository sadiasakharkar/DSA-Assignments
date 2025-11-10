package Assignment09;

import java.util.*;
import java.util.regex.*;

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

class ContactList_LPR { // LPR = Linear Probing With Replacement
    private static final Contact DELETED = new Contact(-1, "DELETED", "");
    int size;
    Contact[] hashTable;

    ContactList_LPR(int n) {
        this.size = n;
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

    // 💥 Linear Probing WITH Replacement
    public void create() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Mobile Number: ");
        long num = sc.nextLong();
        sc.nextLine();

        System.out.print("Enter Email ID: ");
        String email = sc.nextLine().trim();

        if (!isValidName(name)) {
            System.out.println("❌ Invalid name!");
            return;
        }
        if (!isValidMobile(num)) {
            System.out.println("❌ Invalid mobile number (10 digits)");
            return;
        }
        if (!isValidEmail(email)) {
            System.out.println("❌ Invalid email format!");
            return;
        }
        if (exists(num)) {
            System.out.println("⚠️ Contact with this number already exists!");
            return;
        }

        Contact newCon = new Contact(num, name, email);
        int index = hash(num);
        int startIndex = index;

        while (hashTable[index] != null && hashTable[index] != DELETED) {
            long existingKey = hashTable[index].mobile_no;
            int existingHash = hash(existingKey);

            if (existingHash != index) {
                // Replace logic 💡
                System.out.println("🔄 Replacing contact at index " + index + " for better placement");

                Contact temp = hashTable[index];
                hashTable[index] = newCon;
                newCon = temp; // reinserting displaced one
                num = newCon.mobile_no;
                index = hash(num);
                startIndex = index;
            } else {
                index = (index + 1) % size;
                if (index == startIndex) {
                    System.out.println("⚠️ Hash table full! Cannot insert contact");
                    return;
                }
            }
        }

        hashTable[index] = newCon;
        System.out.println("✅ Contact stored at index " + index);
    }

    public void search_by_name() {
        Scanner sc = new Scanner(System.in);
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
            System.out.println("❌ Contact not found");
    }

    public void search() {
        Scanner sc = new Scanner(System.in);
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
        System.out.println("❌ Contact not found");
    }

    void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number to delete: ");
        long number = sc.nextLong();

        int index = hash(number);
        int startIndex = index;

        while (hashTable[index] != null) {
            if (hashTable[index] != DELETED && hashTable[index].mobile_no == number) {
                hashTable[index] = DELETED;
                System.out.println("✅ Contact deleted!");
                return;
            }
            index = (index + 1) % size;
            if (index == startIndex)
                break;
        }
        System.out.println("❌ Contact not found!");
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
                System.out.printf("%-8d | %-15s | %-12s | %-20s%n", i, "<DELETED>", "-", "-");
            else
                System.out.printf("%-8d | %-15s | %-12d | %-20s%n", i, c.name, c.mobile_no, c.email);
        }
    }

}

public class Assign9_LPR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of hash table: ");
        int size = sc.nextInt();

        ContactList_LPR t = new ContactList_LPR(size);
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
                case 0 -> System.out.println("👋 Bye bye!");
                default -> System.out.println("❌ Invalid choice!");
            }

        } while (choice != 0);
    }
}
