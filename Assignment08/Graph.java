package Assignment08;

import java.util.*;

class Node {
    int data;
    int weight;
    Node next;

    Node(int data, int weight) {
        this.data = data;
        this.weight = weight;
        this.next = null;
    }
}

class CustomLinkedList {
    Node head;

    public void addlast(int data, int weight) {
        Node newnode = new Node(data, weight);

        if (head == null) {
            head = newnode;
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newnode;
    }
}

class Edge {
    int src, dest, weight;

    Edge(int s, int d, int w) {
        this.src = s;
        this.dest = d;
        this.weight = w;
    }
}

class GraphList {
    int v;
    CustomLinkedList adjList[];

    GraphList(int v) {
        this.v = v;
        adjList = new CustomLinkedList[v + 1];

        for (int i = 1; i <= v; i++) {
            adjList[i] = new CustomLinkedList();
        }
    }

    public void CreateGraph() {
        Scanner sc = new Scanner(System.in);

        System.out.println("enter the number od edges:");
        int e = sc.nextInt();

        if (e <= 0) {
            System.out.println("Invalid number there should be at least one edge");
            return;
        }

        for (int i = 0; i < e; i++) {
            System.out.println("Enter for an edge " + (i + 1) + " in format (src dest weight):");
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int w = sc.nextInt();

            if (v1 < 1 || v2 < 1 || v1 > v || v2 > v) {
                System.out.println("Invalid!!1 skipping ... try again");
                i--;
                continue;
            }

            if (v1 == v2) {
                System.out.println("self loop is nto allowed!!");
                i--;
                continue;
            }

            adjList[v1].addlast(v2, w);
            adjList[v2].addlast(v1, w);

        }

        System.out.println("Graph created successfully");

    }

    public void DisplayGraph() {
        System.out.println("Graph is displayed using adjacency list:");
        for (int i = 1; i <= v; i++) {
            System.out.println(i + " -> ");
            Node temp = adjList[i].head;

            while (temp != null) {
                System.out.println("(" + temp.data + "w = " + temp.weight + ")");
                temp = temp.next;
            }
            System.out.println("null");

        }
    }

    void dfs(int start, int end, boolean[] vis, ArrayList<Integer> path) {
        path.add(start);
        if (start == end) {
            return;
        }
        vis[start] = true;

        Node temp = adjList[start].head;

        while (temp != null) {
            if (!vis[temp.data]) {
                dfs(temp.data, end, vis, path);
                if (path.get(path.size() - 1) == end)
                    return;
            }
            temp = temp.next;
        }
        path.remove(path.size() - 1);
    }

    void finddfs(int start, int end) {
        boolean vis[] = new boolean[v + 1];
        ArrayList<Integer> path = new ArrayList<>();

        dfs(start, end, vis, path);

        if (!path.isEmpty() && path.get(path.size() - 1) == end) {
            System.out.println("path found" + path);
        } else {
            System.out.println("path not found");
        }
    }
}

public class Graph {

}
