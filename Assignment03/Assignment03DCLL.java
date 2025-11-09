package Assignment03;
/*
Name: Sadia Sakharkar
Roll No: UCE2025002
Batch: A4
Assignment 03

DCLL (Circular Doubly Linked List)

PROBLEM STATEMENT: 
Simulate the working of a simple music player playlist using DCLL 
demonstrating operations like adding songs, deleting songs, and moving 
forward & backward through the playlist.
*/

import java.util.*;

// Node class
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

// Playlist class
class Playlist {
    Node head;
    Node tail;
    Node current;

    public Playlist() {
        head = tail = current = null;
    }

    // Insert at first
    public void insertFirst(int id, String name, String artist) {
        Node newSong = new Node(id, name, artist);
        if (head == null) {
            head = tail = current = newSong;
            head.next = head.prev = head;
        } else {
            newSong.next = head;
            newSong.prev = tail;
            head.prev = newSong;
            tail.next = newSong;
            head = newSong;
        }
        System.out.println("Song added at the beginning!");
    }

    // Insert at last
    public void insertLast(int id, String name, String artist) {
        Node newSong = new Node(id, name, artist);
        if (head == null) {
            head = tail = current = newSong;
            head.next = head.prev = head;
        } else {
            newSong.prev = tail;
            newSong.next = head;
            tail.next = newSong;
            head.prev = newSong;
            tail = newSong;
        }
        System.out.println("Song added at the end!");
    }

    // Insert at specific position (0-based index)
    public void insertAtPosition(int id, String name, String artist, int index) {
        if (index < 0) {
            System.out.println("Invalid position!");
            return;
        }

        Node newSong = new Node(id, name, artist);

        // Insert at beginning
        if (index == 0) {
            insertFirst(id, name, artist);
            return;
        }

        // Empty list and index > 0 is invalid
        if (head == null) {
            System.out.println("Invalid position, list is empty!");
            return;
        }

        Node ptr = head;
        for (int i = 0; i < index - 1; i++) {
            ptr = ptr.next;
            if (ptr == head)
                break; // reached end, stop
        }

        // If we reached back to head, insert at end
        if (ptr.next == head) {
            insertLast(id, name, artist);
            return;
        }

        // Normal insertion in middle
        newSong.prev = ptr;
        newSong.next = ptr.next;
        ptr.next.prev = newSong;
        ptr.next = newSong;

        // Update head or tail if needed
        if (ptr == tail)
            tail = newSong;

        head.prev = tail;
        tail.next = head;

        System.out.println("Song added at position " + index + "!");
    }

    // Display forward
    public void displayForward() {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }
        System.out.println("\n--- Playlist (Forward) ---");
        Node temp = head;
        do {
            System.out.println("ID: " + temp.id + " | Song: " + temp.songName + " | Artist: " + temp.artist);
            temp = temp.next;
        } while (temp != head);
    }

    // Display reverse
    public void displayReverse() {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }
        System.out.println("\n--- Playlist (Reverse) ---");
        Node temp = tail;
        do {
            System.out.println("ID: " + temp.id + " | Song: " + temp.songName + " | Artist: " + temp.artist);
            temp = temp.prev;
        } while (temp != tail);
    }

    // Play next song
    public void playNext() {
        if (current == null) {
            System.out.println("No songs in the playlist!");
        } else {
            current = current.next;
            showCurrent();
        }
    }

    // Play previous song
    public void playPrevious() {
        if (current == null) {
            System.out.println("No songs in the playlist!");
        } else {
            current = current.prev;
            showCurrent();
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
        boolean found = false;
        do {
            if (temp.id == id) {
                found = true;
                break;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("Song with ID " + id + " not found!");
            return;
        }

        if (temp == head && temp == tail) { // only one node
            head = tail = current = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            if (temp == head)
                head = temp.next;
            if (temp == tail)
                tail = temp.prev;
            if (current == temp)
                current = temp.next;
        }

        System.out.println("Song deleted successfully!");
    }
}

// Main class
public class Assignment03DCLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Playlist playlist = new Playlist();
        int choice;

        System.out.println("🎶 Welcome to Spotify Simulation (DCLL) 🎶");

        do {
            System.out.println("\n--- Music Player Menu ---");
            System.out.println("1. Insert Song at Beginning");
            System.out.println("2. Insert Song at End");
            System.out.println("3. Insert Song at Position");
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
                    playlist.insertLast(id, name, artist);
                }
                case 3 -> {
                    System.out.print("Enter Song ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Song Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Artist Name: ");
                    String artist = sc.nextLine();
                    System.out.print("Enter Position (0-based): ");
                    int pos = sc.nextInt();
                    sc.nextLine();
                    playlist.insertAtPosition(id, name, artist, pos);
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
