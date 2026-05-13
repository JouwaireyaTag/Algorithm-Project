
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
        return helper(A, A.length, B, B.length, k);
    }
      private static int helper(int[] A, int m, int[] B, int n, int k) {
          
        // If first array is empty
        if (m == 0)
            return B[k - 1];

        // If second array is empty
        if (n == 0)
            return A[k - 1];

        // Base case
        if (k == 1)
            return Math.min(A[0], B[0]);

        // Take k/2 elements
        int i = Math.min(m, k / 2);
        int j = Math.min(n, k / 2);

        // Compare elements
        if (A[i - 1] < B[j - 1]) {

            // Create new array after removing first i elements
            int[] newA = new int[m - i];

            for (int x = 0; x < m - i; x++) {
                newA[x] = A[x + i];
            }

            return helper(newA, m - i, B, n, k - i);

        } else {

            // Create new array after removing first j elements
            int[] newB = new int[n - j];

            for (int x = 0; x < n - j; x++) {
                newB[x] = B[x + j];
            }

            return helper(A, m, newB, n - j, k - j);
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
