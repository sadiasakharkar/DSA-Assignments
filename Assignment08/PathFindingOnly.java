package Assignment08;

import java.util.*;

// Node for adjacency list
class Node {
    int data;
    int weight;
    Node next;

    Node(int data, int weight) {
        this.data = data;
        this.weight = weight;
    }
}

class CustomLinkedList {
    Node head;

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

class GraphList {
    int v;
    CustomLinkedList adjList[];

    GraphList(int v) {
        this.v = v;
        adjList = new CustomLinkedList[v + 1];
        for (int i = 1; i <= v; i++)
            adjList[i] = new CustomLinkedList();
    }

    public void createGraph() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        for (int i = 0; i < e; i++) {
            System.out.print("\nEnter edge " + (i + 1) + " (src dest weight): ");
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int w = sc.nextInt();

            adjList[v1].addLast(v2, w);
            adjList[v2].addLast(v1, w);
        }

        System.out.println("\nGraph created");
    }

    // dfs path
    void dfs(int s, int e, boolean[] vis, ArrayList<Integer> path) {
        path.add(s);
        if (s == e)
            return;
        vis[s] = true;

        Node temp = adjList[s].head;
        while (temp != null) {
            if (!vis[temp.data]) {
                dfs(temp.data, e, vis, path);
                if (!path.isEmpty() && path.get(path.size() - 1) == e)
                    return;
            }
            temp = temp.next;
        }
        path.remove(path.size() - 1);
    }

    void findPathDFS(int start, int end) {
        boolean vis[] = new boolean[v + 1];
        ArrayList<Integer> path = new ArrayList<>();
        dfs(start, end, vis, path);

        if (!path.isEmpty() && path.get(path.size() - 1) == end)
            System.out.println("Path: " + path);
        else
            System.out.println("No path found");
    }

    // bfs is here but fully commented
    /*
     * public void BFS(int start) {
     * boolean visited[] = new boolean[v + 1];
     * Queue<Integer> q = new LinkedList<>();
     * 
     * q.add(start);
     * visited[start] = true;
     * 
     * System.out.print("BFS: ");
     * while (!q.isEmpty()) {
     * int cur = q.poll();
     * System.out.print(cur + " ");
     * 
     * Node temp = adjList[cur].head;
     * while (temp != null) {
     * if (!visited[temp.data]) {
     * visited[temp.data] = true;
     * q.add(temp.data);
     * }
     * temp = temp.next;
     * }
     * }
     * System.out.println();
     * }
     */
}

public class PathFindingOnly {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();

        GraphList g = new GraphList(v);
        g.createGraph();

        System.out.print("Enter start: ");
        int start = sc.nextInt();
        System.out.print("Enter end: ");
        int end = sc.nextInt();

        g.findPathDFS(start, end);

        // bfs call commented as requested
        // g.BFS(start);
    }
}

// OUTPUT:
// Enter number of vertices: 4
// Enter number of edges: 5

// Enter edge 1 (src dest weight): 1 2 10
// Enter edge 2 (src dest weight): 1 3 6
// Enter edge 3 (src dest weight): 2 3 5
// Enter edge 4 (src dest weight): 2 4 15
// Enter edge 5 (src dest weight): 3 4 4

// Enter start: 1
// Enter end: 4

// Graph created
// Path: [1, 2, 4]