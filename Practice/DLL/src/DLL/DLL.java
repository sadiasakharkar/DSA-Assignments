package DLL;

import java.util.*;

class Node{
	int id ;
	String song ;
	String artist;
	
	Node prev , next;
	
	Node(int id , String song , String artist){
		this.id = id ;
		this.song = song;
		this.artist = artist;
		this.prev = null;
		this.next = null;
	}
}

class Playlist{
	Node head , tail , current;
	
	Playlist(){
		head = tail = current =  null;
	}
	
	
	public void insertfirst(int id , String song , String artist) {
		Node newnode = new Node(id , song , artist);
		
		if(head == null) {
			head = tail = current = newnode;
			return;
		}
		
		else {
			newnode.next = head;
			head.prev = newnode ;
			head = newnode;
		}
		System.out.println("New song added at the beginning!");
	}

	
	public void insertlast(int id , String song , String artist) {
		Node newsong = new Node(id , song , artist);
		
		if (tail == null) {
			head = tail = current = newsong;
		}
		else {
			tail.next = newsong ;
			newsong.prev = tail;
			tail = newsong;
		}
		
		System.out.println("Song inserted at last successfulluy");
	}
	
	public void insertat(int id , String name , String artist, int pos) {
		Node newSong = new Node(id , name , artist);
		
		if(pos < 0 ) {
			System.out.println("Invalid position!!(NEGATIVE????)");
			return;
		}
		
		if(head == null) {
			if(pos > 0 ) {
				System.out.println("invalid position man");
				return;
			}
			else {
				head = tail = current = newSong;
				newSong.prev = null;
				newSong.next = null;
				 System.out.println("New song inserted at postion specified");
				 return;
			}
		}
			
		if(pos == 0) {
			insertfirst(id , name , artist);
			return;
		}
			
			
		Node ptr = head;
		for(int i = 0 ; i < pos -1; i++) {
			ptr = ptr.next;
			if(ptr == null) {
				System.out.println("Invalid");
			return;
			}
		}
				
			if(ptr.next == null) {
				insertlast(id , name , artist);
				return;
			}
				
			newSong.next = ptr.next;
			newSong.prev = ptr;
			ptr.next.prev = newSong;
			ptr.next = newSong;
				
			System.out.println("New song added successfully at the position : " + pos);
	}
	
	public void displayforward() {
		if(head == null) {
			System.out.println("Playlist is emptyyyyy");
			return;
		}
		
		System.out.println("======Playlist(forward)");
		Node temp = head;
		while(temp !=null) {
			System.out.println("Song ID: " +temp.id+  "| Song name: "+ temp.song+ " | Song artist: "+ temp.artist );
			temp = temp.next;
		}
	}
	
	public void displayreverse() {
		if(tail == null) {
			System.out.println("Playlist is emptyyyyy");
			return;
		}
		
		System.out.println("======Playlist(reverse)");
		Node temp = tail;
		while(temp !=null) {
			System.out.println("Song ID: " +temp.id+  "| Song name: "+ temp.song+ " | Song artist: "+ temp.artist );
			temp = temp.prev;
		}
	}
	
	
	public void playnext() {
		if(current == null) {
			System.out.println("Nothing to play next");
		} else if(current.next!= null) {
			current = current.next;
			showcurrent();
		}
		else {
			System.out.println("You are at the last song!!");
		}
	}
	
	public void playprevious() {
		if(current == null) {
			System.out.println("Nothing to play next");
		} else if(current.prev!= null) {
			current = current.prev;
			showcurrent();
		}
		else {
			System.out.println("You are at the first song!!");
		}
	}
	
	public void showcurrent() {
		if(current == null) {
			System.out.println("No song in playlist");
			return;
		}
		System.out.println(" Now Playing -> ID:" + current.id+  " Song: "+ current.song +" Artist: " +current.artist );
	}
	
	public void deletesong(int id) {
		if(head == null) {
			System.out.println("No song in playlist");
			return;
		}
		
		Node temp = head;
		while(temp!= null && temp.id != id) {
			temp = temp.next;
		}
		
		if(temp == null) {
			System.out.println("Id not found!");
			return;
		}
		
		if(temp == head ) {
			head = head.next;
			if(head != null) {
				head.prev = null;
			}
		} else if(temp == tail) {
				tail = tail.prev;
				if(tail != null) {
					tail.next= null;
				}
			}
		else {
			temp.next.prev = temp.prev;
			temp.prev.next = temp.next;
		}
		
		if(current == temp) {
			if(temp.next!= null) {
				current = temp.next;
			}
			else
				current = temp.prev;
			}
			System.out.println("Song deleted successfully!!");
	}
}

public class DLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Playlist playlist = new Playlist();
        int choice;

        System.out.println("🎶 Welcome to Spotify Simulation 🎶");

        do {
            System.out.println("\n1. Insert First");
            System.out.println("2. Insert at Position");
            System.out.println("3. Insert Last");
            System.out.println("4. Play Next");
            System.out.println("5. Play Previous");
            System.out.println("6. Show Current");
            System.out.println("7. Delete Song");
            System.out.println("8. Display Forward");
            System.out.println("9. Display Reverse");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Song: ");
                    String s = sc.nextLine();
                    System.out.print("Artist: ");
                    String ar = sc.nextLine();
                    playlist.insertfirst(id, s, ar);
                }
                case 2 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Song: ");
                    String s = sc.nextLine();
                    System.out.print("Artist: ");
                    String ar = sc.nextLine();
                    System.out.print("Position: ");
                    int pos = sc.nextInt();
                    playlist.insertat(id, s, ar, pos);
                }
                case 3 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Song: ");
                    String s = sc.nextLine();
                    System.out.print("Artist: ");
                    String ar = sc.nextLine();
                    playlist.insertlast(id, s, ar);
                }
                case 4 -> playlist.playnext();
                case 5 -> playlist.playprevious();
                case 6 -> playlist.showcurrent();
                case 7 -> {
                    System.out.print("Enter ID to delete: ");
                    playlist.deletesong(sc.nextInt());
                }
                case 8 -> playlist.displayforward();
                case 9 -> playlist.displayreverse();
                case 10 -> System.out.println("Exiting...");
            }
        } while (choice != 10);

        sc.close();
    }
}
