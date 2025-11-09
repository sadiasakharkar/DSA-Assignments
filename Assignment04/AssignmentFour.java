package Assignment04;

import java.util.*;

class SubsetSum {
    public boolean isSubsetSum(int arr[], int idx, int sum) {
        if (idx == -1 && (sum > 0 || sum < 0)) {
            return false;
        }
        if (sum == 0) {
            return true;
        }
        // include current array element in the sum
        boolean included = isSubsetSum(arr, idx - 1, sum - arr[idx]);
        // don't include current array element in the sum
        boolean notIncluded = isSubsetSum(arr, idx - 1, sum);
        return included || notIncluded;
    }

    public void printAllSubsets(ArrayList<Integer> list, int arr[], int idx, int sum) {
        if (idx == -1 && (sum > 0 || sum < 0)) {
            return;
        }
        if (sum == 0) {
            System.out.println(list);
            return;
        }
        // include current array element in the sum
        list.add(arr[idx]);
        printAllSubsets(list, arr, idx - 1, sum - arr[idx]);
        // backtracking
        list.remove(list.size() - 1);
        // don't include current array element in the sum
        printAllSubsets(list, arr, idx - 1, sum);
    }
}

public class AssignmentFour {

    public static void main(String[] args) {
        SubsetSum obj = new SubsetSum();
        int arr[] = { 3, 34, 4 };
        int idx = arr.length - 1;
        int sum = 12;
        System.out.println(obj.isSubsetSum(arr, idx, sum));
        obj.printAllSubsets(new ArrayList<>(), arr, idx, sum);

    }

}

// output:

// Enter size of array: 6
// Enter elements of array:
// 3 34 4 12 5 2
// Enter target sum: 9
// Subset exists? true
// One subset that sums to 9: [3, 4, 2]
