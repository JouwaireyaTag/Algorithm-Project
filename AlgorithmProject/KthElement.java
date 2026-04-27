
package AlgorithmProject;

import java.util.Scanner;

public class KthElement {
    
    // Non-recursive (merge)
    public static int kthMerge(int[] A, int[] B, int k) {
        int i = 0, j = 0, count = 0;

        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                count++;
                if (count == k) return A[i];
                i++;
            } else {
                count++;
                if (count == k) return B[j];
                j++;
            }
        }
         while (i < A.length) {
            count++;
            if (count == k) return A[i];
            i++;
        }

        while (j < B.length) {
            count++;
            if (count == k) return B[j];
            j++;
        }

        return -1;
    }
    

    // Recursive
     public static int kthRecursive(int[] A, int[] B, int k) {
        return helper(A, 0, B, 0, k);
    }
      private static int helper(int[] A, int startA, int[] B, int startB, int k) {

        if (startA >= A.length)
            return B[startB + k - 1];

        if (startB >= B.length)
            return A[startA + k - 1];

        if (k == 1)
            return Math.min(A[startA], B[startB]);

        int midA = Integer.MAX_VALUE;
        int midB = Integer.MAX_VALUE;

        if (startA + k / 2 - 1 < A.length)
            midA = A[startA + k / 2 - 1];

        if (startB + k / 2 - 1 < B.length)
            midB = B[startB + k / 2 - 1];
               if (midA < midB) {
            return helper(A, startA + k / 2, B, startB, k - k / 2);
        } else {
            return helper(A, startA, B, startB + k / 2, k - k / 2);
        }
    }
      
      
       // Function to check sorted array
       public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1])
                return false;
        }
        return true;
    }
       
      // MAIN
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Input first array
        System.out.print("Enter size of Array 1: ");
        int n = input.nextInt();
        int[] A = new int[n];

        System.out.println("Enter elements of Array 1 (sorted):");
        for (int i = 0; i < n; i++) {
            A[i] = input.nextInt();
        }  
         if (!isSorted(A)) {
            System.out.println("Error: Array 1 must be sorted!");
            return;
        }

        //  Input second array
        System.out.print("Enter size of Array 2: ");
        int m = input.nextInt();
        int[] B = new int[m];

        System.out.println("Enter elements of Array 2 (sorted):");
        for (int i = 0; i < m; i++) {
            B[i] = input.nextInt();
        }
        if (!isSorted(B)) {
            System.out.println("Error: Array 2 must be sorted!");
            return;
        }

        // Input k
        System.out.print("Enter k: ");
        int k = input.nextInt();

        if (k <= 0 || k > n + m) {
            System.out.println("Invalid k!");
            return;
        }
         // Output results
        System.out.println("Merge Result: " + kthMerge(A, B, k));
        System.out.println("Recursive Result: " + kthRecursive(A, B, k));
    }
}
