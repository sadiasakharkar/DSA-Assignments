package Assignment02;

// SLL implementation of Code Club members

import java.util.*;

// Member class
class Member {
    int memberId;
    String name;
    String address;
    String position;
    Member link;

    Member(int memberId, String name, String address, String position) {
        this.memberId = memberId;
        this.name = name;
        this.address = address;
        this.position = position;
        this.link = null;
    }
}

// CodeClub class - linked list
class CodeClub {
    Member head;
    Scanner sc = new Scanner(System.in);

    CodeClub() {
        head = null;
    }

    void create() {
        System.out.print("How many members you wanna add? ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for member " + (i + 1));
            insert_node();
        }
    }

    void insert_node() {
        System.out.print("Enter member ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter address: ");
        String address = sc.nextLine();
        System.out.print("Enter position (member/secretary/president): ");
        String position = sc.nextLine();

        Member newMember = new Member(id, name, address, position);

        if (head == null) {
            head = newMember;
        } else {
            Member temp = head;
            while (temp.link != null) {
                temp = temp.link;
            }
            temp.link = newMember;
        }
        System.out.println("Member added!");
    }

    void display() {
        if (head == null) {
            System.out.println("No members right now.");
            return;
        }

        System.out.println("\n--- Code Club Members ---");
        Member temp = head;
        while (temp != null) {
            System.out.println("ID: " + temp.memberId);
            System.out.println("Name: " + temp.name);
            System.out.println("Address: " + temp.address);
            System.out.println("Position: " + temp.position);
            System.out.println("----------------------");
            temp = temp.link;
        }
    }

    void delet_node() {
        System.out.print("Enter member ID to remove: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (head == null) {
            System.out.println("No members to delete.");
            return;
        }

        if (head.memberId == id) {
            head = head.link;
            System.out.println("Member deleted!");
            return;
        }

        Member prev = head;
        Member curr = head.link;

        while (curr != null && curr.memberId != id) {
            prev = curr;
            curr = curr.link;
        }

        if (curr == null) {
            System.out.println("Member not found.");
        } else {
            prev.link = curr.link;
            System.out.println("Member deleted!");
        }
    }

    void update() {
        System.out.print("Enter member ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Member temp = head;
        while (temp != null && temp.memberId != id) {
            temp = temp.link;
        }

        if (temp == null) {
            System.out.println("Member not found.");
        } else {
            System.out.print("Enter new name: ");
            temp.name = sc.nextLine();
            System.out.print("Enter new address: ");
            temp.address = sc.nextLine();
            System.out.print("Enter new position: ");
            temp.position = sc.nextLine();
            System.out.println("Member updated!");
        }
    }
}

// Main class
public class Assignment_02 {
    public static void main(String[] args) {
        CodeClub club = new CodeClub();
        Scanner sc = new Scanner(System.in);
        int choice;
        char ans;

        System.out.println("Welcome to Code Club!");

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Create members");
            System.out.println("2. Insert member");
            System.out.println("3. Show members");
            System.out.println("4. Delete member");
            System.out.println("5. Update member");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> club.create();
                case 2 -> club.insert_node();
                case 3 -> club.display();
                case 4 -> club.delet_node();
                case 5 -> club.update();
                case 6 -> System.out.println("Bye!");
                default -> System.out.println("Invalid option!");
            }

            if (choice != 6) {
                System.out.print("Continue? (Y/N): ");
                ans = sc.next().charAt(0);
            } else {
                break;
            }
        } while (ans == 'Y' || ans == 'y');

        sc.close();
    }
}

// Output:

// Welcome to Code Club!

// --- Menu ---
// 1. Create members
// 2. Insert member
// 3. Show members
// 4. Delete member
// 5. Update member
// 6. Exit
// Choose option: 1

// Case 1: Create Members
// How many members you wanna add? 3

// Enter details for member 1
// Enter member ID: 101
// Enter name: Sadia
// Enter address: Pune
// Enter position (member/secretary/president): member
// Member added!

// Enter details for member 2
// Enter member ID: 102
// Enter name: Zikra
// Enter address: Mumbai
// Enter position (member/secretary/president): secretary
// Member added!

// Enter details for member 3
// Enter member ID: 103
// Enter name: Abd
// Enter address: Delhi
// Enter position (member/secretary/president): president
// Member added!

// Continue? (Y/N): Y

// Case 3: Display Members
// --- Menu ---
// Choose option: 3

// --- Code Club Members ---
// ID: 101
// Name: Sadia
// Address: Pune
// Position: member

// ID: 102
// Name: Zikra
// Address: Mumbai
// Position: secretary

// ID: 103
// Name: Abd
// Address: Delhi
// Position: president
// Continue? (Y/N): Y

// Case 4: Delete a Member
// --- Menu ---
// Choose option: 4
// Enter member ID to remove: 102
// Member deleted!
// Continue? (Y/N): Y

// Case 5: Update a Member
// --- Menu ---
// Choose option: 5
// Enter member ID to update: 101
// Enter new name: Sadia S.
// Enter new address: Pune West
// Enter new position: member
// Member updated!

// Continue? (Y/N): Y
// Case 3 Again: Display Members (After Delete & Update)
// --- Menu ---
// Choose option: 3

// --- Code Club Members ---
// ID: 101
// Name: Sadia S.
// Address: Pune West
// Position: member

// ID: 103
// Name: Abd
// Address: Delhi
// Position: president
// Continue? (Y/N): Y

// Case 2: Insert Another Member
// --- Menu ---
// Choose option: 2
// Enter member ID: 104
// Enter name: Zikra
// Enter address: Bangalore
// Enter position (member/secretary/president): secretary
// Member added!
// Continue? (Y/N): Y

// Case 3: Display Members (Final List)
// --- Menu ---
// Choose option: 3

// --- Code Club Members ---
// ID: 101
// Name: Sadia S.
// Address: Pune West
// Position: member

// ID: 103
// Name: Abd
// Address: Delhi
// Position: president

// ID: 104
// Name: Zikra
// Address: Bangalore
// Position: secretary
// Continue? (Y/N): Y

// Case 6: Exit
// --- Menu ---
// Choose option: 6
// Bye!
