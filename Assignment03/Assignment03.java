package Assignment03;
/*
Name: Sadia Sakharkar
Roll No: UCE2025002
Batch: A4
Assignment 03

DLL

PROBLEM STATEMENT: 
Simulate the working of a simple music player playlist using Linked List 
demonstrating operations like adding songs, deleting songs, and moving 
forward & backward through the playlist.
*/

import java.util.*;

// Node class to define song structure
class Node {
    int id;
    String songName;
    String artist;
    Node prev;
    Node next;

    public Node(int id, String songName, String artist) {
        this.id = id;
        this.songName = songName;
        this.artist = artist;
        this.prev = null;
        this.next = null;
    }
}

// Playlist class with operations
class Playlist {
    Node head;
    Node tail;
    Node current;

    public Playlist() {
        head = tail = current = null;
    }

    // Insert song at first position
    public void insertFirst(int id, String name, String artist) {
        Node newSong = new Node(id, name, artist);
        if (head == null) {
            head = tail = current = newSong;
        } else {
            newSong.next = head;
            head.prev = newSong;
            head = newSong;
        }
        System.out.println("Song added at the beginning!");
    }

    // Insert song at last position
    public void insertLast(int id, String name, String artist) {
        Node newSong = new Node(id, name, artist);
        if (tail == null) {
            head = tail = current = newSong;
        } else {
            tail.next = newSong;
            newSong.prev = tail;
            tail = newSong;
        }
        System.out.println("Song added at the end!");
    }

    // Insert song at middle position (after floor(count/2) nodes)
    public void insertAtPosition(int id, String name, String artist, int pos) {
        Node newSong = new Node(id, name, artist);

        // Check for invalid index
        if (pos < 0) {
            System.out.println("Invalid position!");
            return;
        }

        // Case 1: Empty list
        if (head == null) {
            if (pos > 0) {
                System.out.println("Invalid position, list is empty!");
                return;
            } else {
                head = tail = current = newSong;
                newSong.next = null;
                newSong.prev = null;
                System.out.println("Song added as the first song!");
                return;
            }
        }

        // Case 2: Insert at head
        // Insert at beginning
        if (pos == 0) {
            insertFirst(id, name, artist);
            return;
        }

        // Case 3: Insert at any middle position(at specific index)
        // Traverse to position - 1
        Node ptr = head;
        for (int i = 0; i < pos - 1; i++) {
            ptr = ptr.next;
            if (ptr == null) {
                System.out.println("Invalid position!");
                return;
            }
        }

        // Insert at end if ptr.next is null
        if (ptr.next == null) {
            insertLast(id, name, artist);
            return;
        }

        // Insert in middle
        newSong.next = ptr.next;
        newSong.prev = ptr;
        ptr.next.prev = newSong;
        ptr.next = newSong;

        System.out.println("Song added at position " + pos + "!");
    }

    // Show playlist forward
    public void displayForward() {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }
        System.out.println("\n--- Playlist (Forward) ---");
        Node temp = head;
        while (temp != null) {
            System.out.println("ID: " + temp.id + " | Song: " + temp.songName + " | Artist: " + temp.artist);
            temp = temp.next;
        }
    }

    // Show playlist reverse
    public void displayReverse() {
        if (tail == null) {
            System.out.println("Playlist is empty!");
            return;
        }
        System.out.println("\n--- Playlist (Reverse) ---");
        Node temp = tail;
        while (temp != null) {
            System.out.println("ID: " + temp.id + " | Song: " + temp.songName + " | Artist: " + temp.artist);
            temp = temp.prev;
        }
    }

    // Play next song
    public void playNext() {
        if (current == null) {
            System.out.println("No songs in the playlist!");
        } else if (current.next != null) {
            current = current.next;
            showCurrent();
        } else {
            System.out.println("You are at the last song!");
        }
    }

    // Play previous song
    public void playPrevious() {
        if (current == null) {
            System.out.println("No songs in the playlist!");
        } else if (current.prev != null) {
            current = current.prev;
            showCurrent();
        } else {
            System.out.println("You are at the first song!");
        }
    }

    // Show current song
    public void showCurrent() {
        if (current == null) {
            System.out.println("No song is playing currently!");
        } else {
            System.out.println("\n🎵 Now Playing → ID: " + current.id + ", Song: " + current.songName + ", Artist: "
                    + current.artist);
        }
    }

    // Delete song by ID
    public void deleteSong(int id) {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }

        Node temp = head;
        while (temp != null && temp.id != id) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Song with ID " + id + " not found!");
            return;
        }

        if (temp == head) {
            head = head.next;
            if (head != null)
                head.prev = null;
        } else if (temp == tail) {
            tail = tail.prev;
            if (tail != null)
                tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }

        if (current == temp) {
            if (temp.next != null) {
                current = temp.next; // move to the next song
            } else {
                current = temp.prev; // no next, so move to previous
            }
        }

        System.out.println("Song deleted successfully!");
    }
}

// Main class
public class Assignment03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Playlist playlist = new Playlist();
        int choice;

        System.out.println("🎶 Welcome to Spotify Simulation 🎶");

        do {
            System.out.println("\n--- Music Player Menu ---");
            System.out.println("1. Insert Song at Beginning");
            System.out.println("2. Insert Song at Position");
            System.out.println("3. Insert Song at End");
            System.out.println("4. Play Next Song");
            System.out.println("5. Play Previous Song");
            System.out.println("6. Show Current Song");
            System.out.println("7. Delete Song by ID");
            System.out.println("8. Show Playlist (Forward)");
            System.out.println("9. Show Playlist (Reverse)");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Song ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Song Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Artist Name: ");
                    String artist = sc.nextLine();
                    playlist.insertFirst(id, name, artist);
                }
                case 2 -> {
                    System.out.print("Enter Song ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Song Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Artist Name: ");
                    String artist = sc.nextLine();
                    System.out.print("Enter position to insert: ");
                    int pos = sc.nextInt();
                    sc.nextLine();
                    playlist.insertAtPosition(id, name, artist, pos);
                }
                case 3 -> {
                    System.out.print("Enter Song ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Song Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Artist Name: ");
                    String artist = sc.nextLine();
                    playlist.insertLast(id, name, artist);
                }
                case 4 -> playlist.playNext();
                case 5 -> playlist.playPrevious();
                case 6 -> playlist.showCurrent();
                case 7 -> {
                    System.out.print("Enter Song ID to delete: ");
                    int delId = sc.nextInt();
                    sc.nextLine();
                    playlist.deleteSong(delId);
                }
                case 8 -> playlist.displayForward();
                case 9 -> playlist.displayReverse();
                case 10 -> System.out.println("Exiting... Thanks for using Spotify Simulation!");
                default -> System.out.println("Invalid choice, try again!");
            }
        } while (choice != 10);

        sc.close();
    }
}
