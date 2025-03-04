package _coursework.Question1;
import java.util.PriorityQueue;

/*
 * Qustion 1 (B)
You have two sorted arrays of investment returns, returns1 and returns2, and a target number k. You
want to find the kth lowest combined return that can be achieved by selecting one investment from each
array.

Rules:
 The arrays are sorted in ascending order.
 You can access any element in the arrays.

Goal:
Determine the kth lowest combined return that can be achieved.

Input:
 returns1: The first sorted array of investment returns.
 returns2: The second sorted array of investment returns.
 k: The target index of the lowest combined return.
Output:
 The kth lowest combined return that can be achieved.

Example 1:
Input: returns1= [2,5], returns2= [3,4], k = 2
Output: 8
Explanation: The 2 smallest investments are are:
- returns1 [0] * returns2 [0] = 2 * 3 = 6
- returns1 [0] * returns2 [1] = 2 * 4 = 8
The 2nd smallest investment is 8.

Example 2:
Input: returns1= [-4,-2,0,3], returns2= [2,4], k = 6
Output: 0

Explanation: The 6 smallest products are:
- returns1 [0] * returns2 [1] = (-4) * 4 = -16
- returns1 [0] * returns2 [0] = (-4) * 2 = -8
- returns1 [1] * returns2 [1] = (-2) * 4 = -8
- returns1 [1] * returns2 [0] = (-2) * 2 = -4
- returns1 [2] * returns2 [0] = 0 * 2 = 0
- returns1 [2] * returns2 [1] = 0 * 4 = 0
The 6th smallest investment is 0.
 */

public class B_KthLowestInvestmentReturn {
    public static int findKthLowestReturn(int[] returns1, int[] returns2, int k) {
        // priority queue to store the products in ascending order
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0]) // min heap based on the product value
        );

        // initializing the heap with pairs (product, index1, index2)
        // only push the first element from `returns1` multiplied by all elements from `returns2`
        for (int j = 0; j < returns2.length; j++) {
            minHeap.offer(new int[]{returns1[0] * returns2[j], 0, j});
        }

        // extracting elements from the heap `k` times to get the kth smallest product
        int result = 0;
        while (k > 0) {
            int[] current = minHeap.poll(); // extracting the smallest element from the heap
            result = current[0]; // storing the extracted product value
            int i = current[1]; // index in `returns1`
            int j = current[2]; // index in `returns2`

            // pushing the next possible element from `returns1` while keeping `returns2` fixed
            if (i + 1 < returns1.length) {
                minHeap.offer(new int[]{returns1[i + 1] * returns2[j], i + 1, j});
            }
            k--; // reducing the count of elements to extract
        }

        return result; // returning the k-th smallest product
    }
    public static void main(String[] args) {
        int[] testOneReturns1 = {-4, -2, 0, 3};
        int[] testOneReturns2 = {2, 4};
        int k = 6;

        int[] testTwoReturns1 = {2, 5};
        int[] testTwoReturns2 = {3, 4};
        int k2 = 2;

        int[] testThreeReturns1 = {1, 2, 3, 4, 5};
        int[] testThreeReturns2 = {6, 7, 8, 9, 10};
        int k3 = 5;

        // TESTING
        // test 1
        System.out.println(findKthLowestReturn(testOneReturns1,testOneReturns2, k)); // Output: 0
        // test 2
        System.out.println(findKthLowestReturn(testTwoReturns1,testTwoReturns2, k2)); // Output: 8
        // test 3 
        System.out.println(findKthLowestReturn(testThreeReturns1,testThreeReturns2, k3)); // Output: 10
    }
}
