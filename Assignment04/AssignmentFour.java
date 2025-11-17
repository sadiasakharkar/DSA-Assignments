package Assignment04;

import java.util.*;

class SubsetSum {

    public boolean isSubsetSum(int arr[], int idx, int sum) {

        // If we checked everything and sum is still not 0 → not possible
        if (idx == -1 && sum != 0) {
            return false;
        }

        // If sum becomes 0 anytime → we found a valid subset
        if (sum == 0) {
            return true;
        }

        // Try taking the current element
        boolean included = isSubsetSum(arr, idx - 1, sum - arr[idx]);

        // Try NOT taking the current element
        boolean notIncluded = isSubsetSum(arr, idx - 1, sum);

        // If any path is true → subset exists
        return included || notIncluded;
    }

    public void printAllSubsets(ArrayList<Integer> list, int arr[], int idx, int sum) {

        // If nothing left to check and sum not completed → stop
        if (idx == -1 && sum != 0) {
            return;
        }

        // If sum hits 0 → whatever is in list is one valid subset → print it
        if (sum == 0) {
            System.out.println(list);
            return;
        }

        // Include current element in the list
        list.add(arr[idx]);

        // Move to next element with reduced sum
        printAllSubsets(list, arr, idx - 1, sum - arr[idx]);

        // Remove the last element (backtracking)
        list.remove(list.size() - 1);

        // Try excluding the current element
        printAllSubsets(list, arr, idx - 1, sum);
    }
}

public class AssignmentFour {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter elements of array:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter target sum: ");
        int sum = sc.nextInt();

        SubsetSum obj = new SubsetSum();

        // Start checking from last index of array
        int idx = arr.length - 1;

        // Check if ANY subset exists
        boolean exists = obj.isSubsetSum(arr, idx, sum);
        System.out.println("Subset exists? " + exists);

        // If yes → print all the subsets that make the sum
        if (exists) {
            System.out.println("Subsets that sum to " + sum + ":");
            obj.printAllSubsets(new ArrayList<>(), arr, idx, sum);
        }

        sc.close();
    }
}

// OUTPUT:

// Enter size of array: 6
// Enter elements of array:
// 3 34 4 12 5 2
// Enter target sum: 9
// Subset exists? true
// One or more subsets that sum to 9:
// [2, 4, 3]
// [5, 4]