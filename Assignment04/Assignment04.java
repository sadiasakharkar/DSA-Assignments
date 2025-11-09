package Assignment04;

import java.util.*;

public class Assignment04 {

    public static boolean subsetSum(int[] arr, int index, int target, List<Integer> current, List<Integer> result) {
        // Base case: if target becomes 0 → we found a valid subset
        if (target == 0) {
            result.addAll(current); // copy current subset into result
            return true; // return success
        }

        // If we run out of elements OR target goes negative → not possible
        if (index == arr.length || target < 0) {
            return false; // dead end
        }

        // Choice 1: Include current element in the subset
        current.add(arr[index]); // pick element
        boolean include = subsetSum(arr, index + 1, target - arr[index], current, result);
        current.remove(current.size() - 1); // undo choice (backtracking)

        // If including worked, we don’t need to check further
        if (include)
            return true;

        // Choice 2: Exclude current element and move forward
        boolean exclude = subsetSum(arr, index + 1, target, current, result);

        // Return true if either choice led to success
        return exclude;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 34, 4, 12, 5, 2 };
        int target = 9;

        List<Integer> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>(); // stores one valid subset if found

        // Call recursive function starting from index 0
        boolean exists = subsetSum(arr, 0, target, current, result);

        System.out.println("Subset exists? " + exists);
        if (exists) {
            System.out.println("One subset that sums to " + target + ": " + result);
        }
    }
}

// output:
// Enter size of array: 6
// Enter elements of array:
// 3 34 4 12 5 2
// Enter target sum: 9
// Subset exists? true
// One subset that sums to 9: [3, 4, 2]
