package Assignment06;
// Name: Sadia Sakharkar

// Roll No: UCE2025002
// Batch: A4
// Assignment 6
// PROBLEM STATEMENT:
// Given an array of jobs where every job has a deadline and associated profit if the
// job is finished before the deadline. It is also given that every job takes a single unit
// of time, so the minimum possible deadline for any job is 1. Maximize the total
// profit if only one job can be scheduled at a time.

import java.util.*;

class GreedyJob {
    char id;
    int deadline;
    int profit;

    public GreedyJob(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class Assignment06_PriorityQueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Take number of jobs
        System.out.print("Enter number of jobs: ");
        int n = sc.nextInt();

        ArrayList<GreedyJob> jobs = new ArrayList<>();

        // 2. Take job details from user
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Job ID (char): ");
            char id = sc.next().charAt(0);

            System.out.print("Enter Deadline (int): ");
            int deadline = sc.nextInt();

            System.out.print("Enter Profit (int): ");
            int profit = sc.nextInt();

            jobs.add(new GreedyJob(id, deadline, profit));
        }

        // 3. Sort by increasing deadline
        jobs.sort((j1, j2) -> j1.deadline - j2.deadline);

        // 4. Find maximum deadline
        int maxDeadline = 0;
        for (GreedyJob j : jobs) {
            maxDeadline = Math.max(maxDeadline, j.deadline);
        }

        // 5. Max-Heap based on profit
        PriorityQueue<GreedyJob> maxHeap = new PriorityQueue<>(
                (j1, j2) -> j2.profit - j1.profit);

        // 6. Iterate from last slot backward
        int jobIndex = n - 1;
        ArrayList<Character> result = new ArrayList<>();

        for (int t = maxDeadline; t > 0; t--) {
            // Add all jobs whose deadlines >= t
            while (jobIndex >= 0 && jobs.get(jobIndex).deadline >= t) {
                maxHeap.add(jobs.get(jobIndex));
                jobIndex--;
            }

            // Pick best profit job
            if (!maxHeap.isEmpty()) {
                GreedyJob best = maxHeap.poll();
                result.add(best.id);
            }
        }

        // 7. Print result (reverse order because we filled from back)
        Collections.reverse(result);

        System.out.print("Maximum profit job sequence: ");
        for (char c : result) {
            System.out.print(c + " ");

            sc.close();
        }
    }
}

// OUTPUT:
// Enter number of jobs: 5
// Enter Job ID (char): a
// Enter Deadline (int): 2
// Enter Profit (int): 100
// Enter Job ID (char): b
// Enter Deadline (int): 1
// Enter Profit (int): 19
// Enter Job ID (char): c
// Enter Deadline (int): 2
// Enter Profit (int): 27
// Enter Job ID (char): d
// Enter Deadline (int): 1
// Enter Profit (int): 25
// Enter Job ID (char): e
// Enter Deadline (int): 3
// Enter Profit (int): 15
// Maximum profit job sequence: c a e %