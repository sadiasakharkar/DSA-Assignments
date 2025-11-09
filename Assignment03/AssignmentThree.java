package Assignment03;
/*
Name: Sadia Sakharkar
Roll No: UCE2025002
Batch: A4
Assignment 03

DCLL

PROBLEM STATEMENT: 
Simulate the working of a simple music player playlist using Linked List 
demonstrating operations like adding songs, deleting songs, and moving 
forward & backward through the playlist.
*/

import java.util.*;

// import Node;

// Node class for each song
class Node {
    String song_name;
    String singers;
    Node prev;
    Node next;

    public Node(String song_name, String singers) {
        this.song_name = song_name;
        this.singers = singers;
        this.next = null;
        this.prev = null;
    }
}

// Playlist class
class Playlist {
    Node head;
    Node tail;
    static int size;
    Scanner sc = new Scanner(System.in);

    public Playlist() {
        head = null;
        tail = null;
        size = 0;
    }

    public Playlist createPlaylist() {
        return new Playlist();
    }

    // Add a song at beginning / end / middle
    public Node insertSong(Node head) {
        System.out.print("Enter the name of the song: ");
        String song = sc.nextLine();

        System.out.print("Enter the name of the singers: ");
        String singers = sc.nextLine();

        Node newSong = new Node(song, singers);

        System.out.println("Where do you want to insert?");
        System.out.println("1) Beginning \n2) End \n3) Middle");
        int choice = sc.nextInt();
        sc.nextLine();

        // if playlist empty
        if (head == null && tail == null) {
            head = tail = newSong;
            head.prev = tail;
            tail.next = head;
            size++;
            System.out.println("Song added to empty playlist!");
            return head;
        }

        switch (choice) {
            case 1: // Beginning
                newSong.next = head;
                head.prev = newSong;
                head = newSong;
                head.prev = tail;
                tail.next = head;
                size++;
                System.out.println("Song added at the beginning!");
                break;

            case 2: // End
                tail.next = newSong;
                newSong.prev = tail;
                tail = newSong;
                tail.next = head;
                head.prev = tail;
                size++;
                System.out.println("Song added at the end!");
                break;

            case 3: // Middle
                System.out.print("Enter index (1-based) to insert at: ");
                int idx = sc.nextInt();
                sc.nextLine();

                if (idx > size) {
                    System.out.println("⚠️ Invalid index!");
                    break;
                }
                Node curr = head;
                for (int i = 1; i < idx - 1; i++) {
                    curr = curr.next;
                }
                newSong.next = curr.next;
                curr.next.prev = newSong;
                curr.next = newSong;
                newSong.prev = curr;
                size++;
                System.out.println("Song added at index " + idx + "!");
                break;

            default:
                System.out.println("Invalid choice!");
        }
        return head;
    }

    // Show songs forward
    public void displaySongs(Node head) {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }
        Node curr = head;
        System.out.println("\n--- Playlist ---");
        do {
            System.out.println("Song: " + curr.song_name + " | Singer: " + curr.singers);
            curr = curr.next;
        } while (curr != head);
    }

    // Show songs reverse
    public void displaySongsReverse(Node head) {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }
        Node curr = tail;
        System.out.println("\n--- Playlist (Reverse) ---");
        do {
            System.out.println("Song: " + curr.song_name + " | Singer: " + curr.singers);
            curr = curr.prev;
        } while (curr != tail);
    }

    // Delete song
    public Node removeSong(Node head) {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return null;
        }
        System.out.print("Enter song name to delete: ");
        String song = sc.nextLine();
        System.out.print("Enter singer name: ");
        String singer = sc.nextLine();

        Node curr = head;
        do {
            if (curr.song_name.equalsIgnoreCase(song) && curr.singers.equalsIgnoreCase(singer)) {
                if (curr == head && curr == tail) {
                    head = tail = null;
                } else if (curr == head) {
                    head = head.next;
                    head.prev = tail;
                    tail.next = head;
                } else if (curr == tail) {
                    tail = tail.prev;
                    tail.next = head;
                    head.prev = tail;
                } else {
                    curr.prev.next = curr.next;
                    curr.next.prev = curr.prev;
                }
                size--;
                System.out.println("Song removed successfully!");
                return head;
            }
            curr = curr.next;
        } while (curr != head);

        System.out.println("Song not found!");
        return head;
    }

    // Search for a song
    public void searchASong(Node head) {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }
        System.out.print("Enter song name to search: ");
        String song = sc.nextLine();
        System.out.print("Enter singer name: ");
        String singer = sc.nextLine();

        Node curr = head;
        do {
            if (curr.song_name.equalsIgnoreCase(song) && curr.singers.equalsIgnoreCase(singer)) {
                System.out.println("Song found in playlist!");
                return;
            }
            curr = curr.next;
        } while (curr != head);

        System.out.println("Song not found!");
    }

    // Switch (go back or forward to a specific song)
    public Node shuffleSongs(Node head, Node currSong) {
        System.out.println("Currently listening: " + currSong.song_name);
        System.out.println("1) Go back \n2) Skip ahead");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.print("Enter song name to re-listen: ");
            String song = sc.nextLine();
            Node temp = currSong.prev;
            while (temp != currSong && !temp.song_name.equalsIgnoreCase(song)) {
                temp = temp.prev;
            }
            return (temp.song_name.equalsIgnoreCase(song)) ? temp : null;

        } else if (choice == 2) {
            System.out.print("Enter song name to skip to: ");
            String song = sc.nextLine();
            Node temp = currSong.next;
            while (temp != currSong && !temp.song_name.equalsIgnoreCase(song)) {
                temp = temp.next;
            }
            return (temp.song_name.equalsIgnoreCase(song)) ? temp : null;
        }
        System.out.println("Invalid choice!");
        return null;
    }
}

// Main class
public class AssignmentThree {
    public static void main(String[] args) {
        Playlist obj = new Playlist();
        Scanner sc = new Scanner(System.in);
        Playlist list = null;
        Node head = null;
        int ch;

        System.out.println("🎶 Welcome to Spotify Simulation 🎶");

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1) Create Playlist");
            System.out.println("2) Add Song");
            System.out.println("3) Show Playlist");
            System.out.println("4) Show Playlist (Reverse)");
            System.out.println("5) Remove Song");
            System.out.println("6) Search Song");
            System.out.println("7) Switch Song");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    list = obj.createPlaylist();
                    System.out.println("New playlist created!");
                    break;
                case 2:
                    head = list.insertSong(head);
                    break;
                case 3:
                    list.displaySongs(head);
                    break;
                case 4:
                    list.displaySongsReverse(head);
                    break;
                case 5:
                    head = list.removeSong(head);
                    break;
                case 6:
                    list.searchASong(head);
                    break;
                case 7:
                    System.out.print("Enter current song name: ");
                    String song = sc.nextLine();
                    Node curr = head;
                    boolean found = false;
                    if (curr != null) {
                        do {
                            if (curr.song_name.equalsIgnoreCase(song)) {
                                Node newCurr = list.shuffleSongs(head, curr);
                                if (newCurr != null)
                                    System.out.println("Now Playing: " + newCurr.song_name);
                                found = true;
                                break;
                            }
                            curr = curr.next;
                        } while (curr != head);
                    }
                    if (!found)
                        System.out.println("Current song not found!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

            System.out.print("Do you want to continue? (1 = yes, 0 = no): ");
            ch = sc.nextInt();
            sc.nextLine();

        } while (ch == 1);

        System.out.println("Thanks for using Spotify Simulation!");

        sc.close();
    }
}

// The above code is simulating a basic music playlist management system. It
// presents a menu with
// options to create a playlist, add songs, show the playlist in normal and
// reverse order, remove
// songs, search for songs, and switch between songs.

// OUTPUT:
// --- Menu ---
// 1) Create Playlist
// 2) Add Song
// 3) Show Playlist
// 4) Show Playlist (Reverse)
// 5) Remove Song
// 6) Search Song
// 7) Switch Song
// Enter your choice: 2
// Enter the name of the song: Shape of You
// Enter the name of the singers: Ed Sheeran
// Where do you want to insert?
// 1) Beginning
// 2) End
// 3) Middle
// 2
// Song added to empty playlist!
// Do you want to continue? (1 = yes, 0 = no): 1

// --- Menu ---
// 1) Create Playlist
// 2) Add Song
// 3) Show Playlist
// 4) Show Playlist (Reverse)
// 5) Remove Song
// 6) Search Song
// 7) Switch Song
// Enter your choice: 2
// Enter the name of the song: Shape of You
// Enter the name of the singers: Ed Sheeran
// Where do you want to insert?
// 1) Beginning
// 2) End
// 3) Middle
// 2
// Song added to empty playlist!
// Do you want to continue? (1 = yes, 0 = no): 1

// Enter your choice: 2
// Enter the name of the song: Believer
// Enter the name of the singers: Imagine Dragons
// Where do you want to insert?
// 1) Beginning
// 2) End
// 3) Middle
// 1
// Song added at the beginning!
// Do you want to continue? (1 = yes, 0 = no): 1

// Enter your choice: 2
// Enter the name of the song: Perfect
// Enter the name of the singers: Ed Sheeran
// Where do you want to insert?
// 1) Beginning
// 2) End
// 3) Middle
// 2
// Song added at the end!
// Do you want to continue? (1 = yes, 0 = no): 1

// Enter your choice: 2
// Enter the name of the song: Attention
// Enter the name of the singers: Charlie Puth
// Where do you want to insert?
// 1) Beginning
// 2) End
// 3) Middle
// 3
// Enter index (1-based) to insert at: 2
// Song added at index 2!
// Do you want to continue? (1 = yes, 0 = no): 1

// Enter your choice: 2
// Enter the name of the song: Attention
// Enter the name of the singers: Charlie Puth
// Where do you want to insert?
// 1) Beginning
// 2) End
// 3) Middle
// 3
// Enter index (1-based) to insert at: 2
// Song added at index 2!
// Do you want to continue? (1 = yes, 0 = no): 1

// Enter your choice: 4

// --- Playlist (Reverse) ---
// Song: Perfect | Singer: Ed Sheeran
// Song: Shape of You | Singer: Ed Sheeran
// Song: Attention | Singer: Charlie Puth
// Song: Believer | Singer: Imagine Dragons
// Do you want to continue? (1 = yes, 0 = no): 1

// Enter your choice: 6
// Enter song name to search: Perfect
// Enter singer name: Ed Sheeran
// Song found in playlist!
// Do you want to continue? (1 = yes, 0 = no): 1

// Enter your choice: 6
// Enter song name to search: Thunder
// Enter singer name: Imagine Dragons
// Song not found!
// Do you want to continue? (1 = yes, 0 = no): 1

// Enter your choice: 7
// Enter current song name: Attention
// Currently listening: Attention
// 1) Go back
// 2) Skip ahead
// 2
// Enter song name to skip to: Perfect
// Now Playing: Perfect
// Do you want to continue? (1 = yes, 0 = no): 1

// Enter your choice: 7
// Enter current song name: Perfect
// Currently listening: Perfect
// 1) Go back
// 2) Skip ahead
// 1
// Enter song name to re-listen: Believer
// Now Playing: Believer
// Do you want to continue? (1 = yes, 0 = no): 1

// Enter your choice: 5
// Enter song name to delete: Attention
// Enter singer name: Charlie Puth
// Song removed successfully!
// Do you want to continue? (1 = yes, 0 = no): 1

// Enter your choice: 5
// Enter song name to delete: Believer
// Enter singer name: Imagine Dragons
// Song removed successfully!
// Do you want to continue? (1 = yes, 0 = no): 1

// Enter your choice: 5
// Enter song name to delete: Perfect
// Enter singer name: Ed Sheeran
// Song removed successfully!
// Do you want to continue? (1 = yes, 0 = no): 1

// Enter your choice: 5
// Enter song name to delete: Shape of You
// Enter singer name: Ed Sheeran
// Song removed successfully!
// Do you want to continue? (1 = yes, 0 = no): 1

// Enter your choice: 3
// Playlist is empty!
// Do you want to continue? (1 = yes, 0 = no): 0

// Thanks for using Spotify Simulation!
