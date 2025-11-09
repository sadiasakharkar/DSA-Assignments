package Assignment04;

import java.util.*;

class SubsetSum {
    public boolean isSubsetSum(int arr[], int idx, int sum) {
        if (idx == -1 && sum != 0) {
            return false;
        }
        if (sum == 0) {
            return true;
        }
        // include current array element
        boolean included = isSubsetSum(arr, idx - 1, sum - arr[idx]);
        // exclude current array element
        boolean notIncluded = isSubsetSum(arr, idx - 1, sum);
        return included || notIncluded;
    }

    public void printAllSubsets(ArrayList<Integer> list, int arr[], int idx, int sum) {
        if (idx == -1 && sum != 0) {
            return;
        }
        if (sum == 0) {
            System.out.println(list);
            return;
        }
        // include current element
        list.add(arr[idx]);
        printAllSubsets(list, arr, idx - 1, sum - arr[idx]);
        // backtrack
        list.remove(list.size() - 1);
        // exclude current element
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
        int idx = arr.length - 1;

        boolean exists = obj.isSubsetSum(arr, idx, sum);
        System.out.println("Subset exists? " + exists);

        if (exists) {
            System.out.println("One or more subsets that sum to " + sum + ":");
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