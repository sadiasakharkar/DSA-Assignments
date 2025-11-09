package Assignment02;

import java.util.*;

// Member class (Node of Linked List)
class Member {
    int memberId;
    String name;
    String address;
    String position; // member, secretary, president etc.
    Member link;

    Member(int id, String name, String addr, String pos) {
        this.memberId = id;
        this.name = name;
        this.address = addr;
        this.position = pos;
        this.link = null;
    }
}

// CodeClub class (Linked List implementation)
class CodeClub {
    Member head;
    Scanner sc = new Scanner(System.in);

    CodeClub() {
        head = null;
    }

    // Create initial list
    void create() {
        System.out.print("Enter number of members to add: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            insertNode();
        }
    }

    // Insert new member
    void insertNode() {
        System.out.print("Enter Member ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Address: ");
        String addr = sc.nextLine();
        System.out.print("Enter Position (member/secretary/president): ");
        String pos = sc.nextLine();

        Member newMember = new Member(id, name, addr, pos);

        if (head == null) {
            head = newMember;
        } else {
            Member temp = head;
            while (temp.link != null) {
                temp = temp.link;
            }
            temp.link = newMember;
        }
        System.out.println("Member added successfully!");
    }

    // Delete member by ID
    void deleteNode() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        System.out.print("Enter Member ID to delete: ");
        int id = sc.nextInt();

        if (head.memberId == id) {
            head = head.link;
            System.out.println("Member deleted successfully!");
            return;
        }

        Member temp = head;
        while (temp.link != null && temp.link.memberId != id) {
            temp = temp.link;
        }

        if (temp.link == null) {
            System.out.println("Member not found!");
        } else {
            temp.link = temp.link.link;
            System.out.println("Member deleted successfully!");
        }
    }

    // Update member details
    void update() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        System.out.print("Enter Member ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Member temp = head;
        while (temp != null && temp.memberId != id) {
            temp = temp.link;
        }

        if (temp == null) {
            System.out.println("Member not found!");
        } else {
            System.out.print("Enter new Name: ");
            temp.name = sc.nextLine();
            System.out.print("Enter new Address: ");
            temp.address = sc.nextLine();
            System.out.print("Enter new Position: ");
            temp.position = sc.nextLine();
            System.out.println("Member updated successfully!");
        }
    }

    // Display all members
    void display() {
        if (head == null) {
            System.out.println("No members to display!");
            return;
        }

        Member temp = head;
        System.out.println("\n--- Code Club Members ---");
        while (temp != null) {
            System.out.println("ID: " + temp.memberId + ", Name: " + temp.name +
                    ", Address: " + temp.address + ", Position: " + temp.position);
            temp = temp.link;
        }
        System.out.println(" ");
    }
}

// Main class
public class Assignment02 {
    public static void main(String[] args) {
        CodeClub club = new CodeClub();
        Scanner sc = new Scanner(System.in);
        char ans;

        do {
            System.out.println("\nCode Club Menu");
            System.out.println("1. Create Member List");
            System.out.println("2. Display Members");
            System.out.println("3. Add New Member");
            System.out.println("4. Delete Member");
            System.out.println("5. Update Member Details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    club.create();
                    break;
                case 2:
                    club.display();
                    break;
                case 3:
                    club.insertNode();
                    break;
                case 4:
                    club.deleteNode();
                    break;
                case 5:
                    club.update();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Enter Valid Option!!");
            }

            System.out.print("Would you like to continue? (Y/N): ");
            ans = sc.next().charAt(0);
        } while (ans == 'Y' || ans == 'y');
    }
}
