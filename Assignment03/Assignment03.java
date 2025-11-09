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
        head = null;
        tail = null;
        current = null;
    }

    // Insert song at end
    public void addSong(int id, String name, String artist) {
        Node newSong = new Node(id, name, artist);

        if (head == null) {
            head = tail = current = newSong;
        } else {
            tail.next = newSong;
            newSong.prev = tail;
            tail = newSong;
        }
        System.out.println("Song added successfully!");
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
            return;
        }
        if (current.next != null) {
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
            return;
        }
        if (current.prev != null) {
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
            current = (temp.next != null) ? temp.next : temp.prev;
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
            System.out.println("1. Add Song");
            System.out.println("2. Play Next Song");
            System.out.println("3. Play Previous Song");
            System.out.println("4. Show Current Song");
            System.out.println("5. Delete Song by ID");
            System.out.println("6. Show Playlist (Forward)");
            System.out.println("7. Show Playlist (Reverse)");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Song ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Song Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Artist Name: ");
                    String artist = sc.nextLine();
                    playlist.addSong(id, name, artist);
                    break;

                case 2:
                    playlist.playNext();
                    break;

                case 3:
                    playlist.playPrevious();
                    break;

                case 4:
                    playlist.showCurrent();
                    break;

                case 5:
                    System.out.print("Enter Song ID to delete: ");
                    int delId = sc.nextInt();
                    sc.nextLine();
                    playlist.deleteSong(delId);
                    break;

                case 6:
                    playlist.displayForward();
                    break;

                case 7:
                    playlist.displayReverse();
                    break;

                case 8:
                    System.out.println("Exiting... Thanks for using Spotify Simulation!");
                    break;

                default:
                    System.out.println("Invalid choice, try again!");
            }
        } while (choice != 8);

        sc.close();
    }
}

// OUTPUT:
// 🎶 Welcome to Spotify Simulation 🎶

// --- Music Player Menu ---
// 1. Add Song
// 2. Play Next Song
// 3. Play Previous Song
// 4. Show Current Song
// 5. Delete Song by ID
// 6. Show Playlist (Forward)
// 7. Show Playlist (Reverse)
// 8. Exit
// Enter your choice: 1
// Enter Song ID: 101
// Enter Song Name: Shape of You
// Enter Artist Name: Ed Sheeran
// Song added successfully!

// --- Music Player Menu ---
// Enter your choice: 1
// Enter Song ID: 102
// Enter Song Name: Blinding Lights
// Enter Artist Name: The Weeknd
// Song added successfully!

// --- Music Player Menu ---
// Enter your choice: 1
// Enter Song ID: 103
// Enter Song Name: Perfect
// Enter Artist Name: Ed Sheeran
// Song added successfully!

// --- Music Player Menu ---
// Enter your choice: 6
// --- Playlist (Forward) ---
// ID: 101 | Song: Shape of You | Artist: Ed Sheeran
// ID: 102 | Song: Blinding Lights | Artist: The Weeknd
// ID: 103 | Song: Perfect | Artist: Ed Sheeran

// --- Music Player Menu ---
// Enter your choice: 7
// --- Playlist (Reverse) ---
// ID: 103 | Song: Perfect | Artist: Ed Sheeran
// ID: 102 | Song: Blinding Lights | Artist: The Weeknd
// ID: 101 | Song: Shape of You | Artist: Ed Sheeran

// --- Music Player Menu ---
// Enter your choice: 4
// 🎵 Now Playing → ID: 101, Song: Shape of You, Artist: Ed Sheeran

// --- Music Player Menu ---
// Enter your choice: 2
// 🎵 Now Playing → ID: 102, Song: Blinding Lights, Artist: The Weeknd

// --- Music Player Menu ---
// Enter your choice: 2
// 🎵 Now Playing → ID: 103, Song: Perfect, Artist: Ed Sheeran

// --- Music Player Menu ---
// Enter your choice: 2
// You are at the last song!

// --- Music Player Menu ---
// Enter your choice: 3
// 🎵 Now Playing → ID: 102, Song: Blinding Lights, Artist: The Weeknd

// --- Music Player Menu ---
// Enter your choice: 3
// 🎵 Now Playing → ID: 101, Song: Shape of You, Artist: Ed Sheeran

// --- Music Player Menu ---
// Enter your choice: 3
// You are at the first song!

// --- Music Player Menu ---
// Enter your choice: 5
// Enter Song ID to delete: 102
// Song deleted successfully!

// --- Music Player Menu ---
// Enter your choice: 6
// --- Playlist (Forward) ---
// ID: 101 | Song: Shape of You | Artist: Ed Sheeran
// ID: 103 | Song: Perfect | Artist: Ed Sheeran

// --- Music Player Menu ---
// Enter your choice: 7
// --- Playlist (Reverse) ---
// ID: 103 | Song: Perfect | Artist: Ed Sheeran
// ID: 101 | Song: Shape of You | Artist: Ed Sheeran

// --- Music Player Menu ---
// Enter your choice: 4
// 🎵 Now Playing → ID: 101, Song: Shape of You, Artist: Ed Sheeran

// --- Music Player Menu ---
// Enter your choice: 5
// Enter Song ID to delete: 101
// Song deleted successfully!

// --- Music Player Menu ---
// Enter your choice: 5
// Enter Song ID to delete: 103
// Song deleted successfully!

// --- Music Player Menu ---
// Enter your choice: 6
// Playlist is empty!

// --- Music Player Menu ---
// Enter your choice: 4
// No song is playing currently!

// --- Music Player Menu ---
// Enter your choice: 8
// Exiting... Thanks for using Spotify Simulation!
