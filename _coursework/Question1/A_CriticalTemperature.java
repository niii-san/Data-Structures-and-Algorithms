package _coursework.Question1;

/*
 * Question 1 (A)
a)
You have a material with n temperature levels. You know that there exists a critical temperature f where
0 <= f <= n such that the material will react or change its properties at temperatures higher than f but
remain unchanged at or below f.

Rules:
 You can measure the material's properties at any temperature level once.
 If the material reacts or changes its properties, you can no longer use it for further measurements.
 If the material remains unchanged, you can reuse it for further measurements.

Goal:
Determine the minimum number of measurements required to find the critical temperature.

Input:
 k: The number of identical samples of the material.
 n: The number of temperature levels.
Output:
 The minimum number of measurements required to find the critical temperature.
Example 1:
Input: k = 1, n = 2

Output: 2
Explanation:
Check the material at temperature 1. If its property changes, we know that f = 0.
Otherwise, raise temperature to 2 and check if property changes. If its property changes, we know that f =
1.If its property changes at temperature, then we know f = 2.
Hence, we need at minimum 2 moves to determine with certainty what the value of f is.

Example 2:
Input: k = 2, n = 6
Output: 3

Example 3:
Input: k = 3, n = 14
Output: 4
 */

public class A_CriticalTemperature {
    // Solution funciton
    public static int minMeasurements(int k, int n) {
        // dp[i][j] represents the maximum number of temperature levels we can check
        // using 'i' samples and 'j' measurements.
        int[][] dp = new int[k + 1][n + 1];

        int minTests = 0; // Keeps track of the minimum number of tests required.

        while (dp[k][minTests] < n) { // Keeps track of the minimum number of tests required
            minTests++; // Increase the number of tests allowed.

            for (int samples = 1; samples <= k; samples++) {
                /**
                 * Recurrence relation explanation:
                 * - If the material breaks at a given temperature, we can only test below it,
                 * i.e., dp[samples - 1][minTests - 1].
                 * - If the material does not break, we can continue testing above it, i.e.,
                 * dp[samples][minTests - 1].
                 * - We add 1 to account for the current test itself.
                 */
                dp[samples][minTests] = dp[samples - 1][minTests - 1] + dp[samples][minTests - 1] + 1;
            }
        }

        return minTests; // Return the minimum number of measurements required
    }

    public static void main(String[] args) {
        // Test cases and output
        System.out.println(minMeasurements(1, 2)); // Output: 2
        System.out.println(minMeasurements(2, 6)); // Output: 3
        System.out.println(minMeasurements(3, 14)); // Output: 4
        System.out.println(minMeasurements(5, 25)); // Output: 5
    }
    /**
     * Time Complexity Analysis:
     * The worst-case time complexity is O(k * minTests), where minTests is the
     * minimum number of tests needed.
     * Since minTests grows logarithmically with n, the overall complexity is
     * approximately O(k * log n).
     */
}