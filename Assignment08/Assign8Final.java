package Assignment08;

// import Assignment07.Node;
// import Assignment03.Node;

// Name: Sadia Sakharkar

// Roll No: UCE2025002
// Batch: A4
// Assignment 08

// Problem Statement: You are given a weighted, undirected graph G with n stations (1 ≤ n ≤ 10^5)
// and m communication channels (n−1 ≤ m ≤ 2×10^5).
// Each channel has an integer bandwidth (1 ≤ bandwidth ≤ 10^9).
// Task 1: Find the path from one station to another station
// Task 2: Select n−1 channels so that all stations are connected and the
// sum of the bandwidths is maximized. Print this sum

import java.util.*;

// Node class represents one edge in adjacency list
class Node {
    int data; // connected vertex
    int weight; // edge weight
    Node next; // pointer to next edge

    Node(int data, int weight) {
        this.data = data;
        this.weight = weight;
        this.next = null;
    }
}

class CustomLinkedList {
    Node head;

    // Add new connection at end of list
    public void addLast(int data, int weight) {
        Node newNode = new Node(data, weight);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = newNode;
    }
}

// Class to store edges (used in Kruskal’s algorithm)
class Edge {
    int src, dest, weight;

    Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }
}

// Graph class handles all graph operations
class GraphList {
    int v; // number of vertices
    CustomLinkedList adjList[];

    GraphList(int v) {
        this.v = v;
        adjList = new CustomLinkedList[v + 1]; // 1-based indexing
        for (int i = 1; i <= v; i++) {
            adjList[i] = new CustomLinkedList();
        }
    }

    // Function to create graph safely with validation
    public void createGraph() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        if (e <= 0) {
            System.out.println("Graph must have at least one edge!");
            return;
        }

        for (int i = 0; i < e; i++) {
            System.out.print("\nEnter edge " + (i + 1) + " (src dest weight): ");
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int weight = sc.nextInt();

            // Validation for vertex range
            if (v1 < 1 || v1 > v || v2 < 1 || v2 > v) {
                System.out.println(" Invalid vertex! Skipping edge...");
                i--; // repeat this edge input
                continue;
            }

            // Avoid self-loops
            if (v1 == v2) {
                System.out.println(" Self loop not allowed! Skipping edge...");
                i--;
                continue;
            }

            // Add edge both ways (undirected graph)
            adjList[v1].addLast(v2, weight);
            adjList[v2].addLast(v1, weight);
        }

        System.out.println("\n✅ Graph created successfully!");
    }

    // Display adjacency list (for understanding)
    public void displayGraph() {
        System.out.println("\nAdjacency List:");
        for (int i = 1; i <= v; i++) {
            System.out.print(i + " → ");
            Node temp = adjList[i].head;
            while (temp != null) {
                System.out.print("(" + temp.data + ", w=" + temp.weight + ") → ");
                temp = temp.next;
            }
            System.out.println("null");
        }
    }

    // DFS traversal to find path from start to end
    public void findPathDFS(int start_v, int end_v) {
        boolean visited[] = new boolean[v + 1];
        ArrayList<Integer> result = new ArrayList<>();

        System.out.println("\nPerforming DFS...");
        dfs(start_v, end_v, visited, result);

        if (!result.isEmpty() && result.get(result.size() - 1) == end_v) {
            System.out.println("✅ Path found: " + result);
        } else {
            System.out.println(" No path found between " + start_v + " and " + end_v);
        }
    }

    // Recursive DFS function
    void dfs(int s, int e, boolean[] visited, ArrayList<Integer> res) {
        res.add(s);
        if (s == e)
            return; // path found

        visited[s] = true;

        Node temp = adjList[s].head;
        while (temp != null) {
            int neighbour = temp.data;
            if (!visited[neighbour]) {
                dfs(neighbour, e, visited, res);
                if (res.get(res.size() - 1) == e)
                    return; // stop recursion if path found
            }
            temp = temp.next;
        }
        res.remove(res.size() - 1); // backtrack
    }

    // Kruskal’s Algorithm to find Maximum Bandwidth (Max Spanning Tree)
    public void findMaxBandwidth() {
        ArrayList<Edge> edges = new ArrayList<>();

        // Collect all edges from adjacency list
        for (int i = 1; i <= v; i++) {
            Node temp = adjList[i].head;
            while (temp != null) {
                if (i < temp.data) // avoid duplicate edges
                    edges.add(new Edge(i, temp.data, temp.weight));
                temp = temp.next;
            }
        }

        if (edges.isEmpty()) {
            System.out.println("\nNo edges to calculate bandwidth!");
            return;
        }

        // Sort edges in descending order (for maximum spanning tree)
        edges.sort((a, b) -> b.weight - a.weight);

        int pred[] = new int[v + 1];
        for (int i = 1; i <= v; i++)
            pred[i] = i;

        int totalCost = 0, count = 0;

        for (Edge edge : edges) {
            int sRoot = find(pred, edge.src);
            int dRoot = find(pred, edge.dest);

            // If adding this edge doesn’t form a cycle
            if (sRoot != dRoot) {
                totalCost += edge.weight;
                pred[dRoot] = sRoot;
                count++;
            }
            if (count == v - 1)
                break; // MST complete
        }

        System.out.println("\n🌐 Maximum Bandwidth (Total Weight): " + totalCost);
    }

    // Union-Find function (find root of vertex)
    int find(int[] pred, int v) {
        if (pred[v] == v)
            return v;
        return pred[v] = find(pred, pred[v]);
    }
}

// Main class
public class Assign8Final {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();
        if (v <= 0) {
            System.out.println("Graph must have at least one vertex!");
            return;
        }

        GraphList g = new GraphList(v);
        g.createGraph();
        g.displayGraph();

        System.out.print("\nEnter start vertex: ");
        int start = sc.nextInt();
        System.out.print("Enter end vertex: ");
        int end = sc.nextInt();

        if (start < 1 || start > v || end < 1 || end > v) {
            System.out.println("Invalid vertices for DFS!");
            return;
        }

        g.findPathDFS(start, end);
        g.findMaxBandwidth();

        System.out.println("\nProgram executed successfully!");
    }
}

// Output:
// Enter no of vertices : 4
// Enter no of edges : 5
// Enter vertex pair for Edge 1: 1 2
// Enter weight : 10
// Enter vertex pair for Edge 2: 1 3
// Enter weight : 6
// Enter vertex pair for Edge 3: 2 3
// Enter weight : 5
// Enter vertex pair for Edge 4: 2 4
// Enter weight : 15
// Enter vertex pair for Edge 5: 3 4
// Enter weight : 4

// Enter start and end vertex (start, end) : 1 4

// Path : [1, 2, 4]
