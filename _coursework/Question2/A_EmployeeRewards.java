package _coursework.Question2;
import java.util.Arrays;

/*
 * Question 2 (A)
a)
You have a team of n employees, and each employee is assigned a performance rating given in the
integer array ratings. You want to assign rewards to these employees based on the following rules:

Every employee must receive at least one reward.
Employees with a higher rating must receive more rewards than their adjacent colleagues.

Goal:
Determine the minimum number of rewards you need to distribute to the employees.

Input:
ratings: The array of employee performance ratings.
Output:
The minimum number of rewards needed to distribute.

Example 1:
Input: ratings = [1, 0, 2]
Output: 5
Explanation: You can allocate to the first, second and third employee with 2, 1, 2 rewards respectively.

Example 2:
Input: ratings = [1, 2, 2]
Output: 4

Explanation: You can allocate to the first, second and third employee with 1, 2, 1 rewards respectively.
The third employee gets 1 rewards because it satisfies the above two conditions.
 */


public class A_EmployeeRewards {
    public static int minRewards(int[] ratings) {
        int n = ratings.length; //number of employees

        // creating an array to store the rewards for each employee
        int[] rewards = new int[n];

        // initialize each employees reward to at least 1, since every employee must receive at least one reward
        Arrays.fill(rewards, 1);

        // left-to-right pass to handle increasing sequences
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                rewards[i] = rewards[i - 1] + 1; // ensuring increasing order reward allocation
            }
        }

        // right-to-left pass for ensuring decreasing sequences are handled
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                rewards[i] = Math.max(rewards[i], rewards[i + 1] + 1);
            }
        }

        // calculating the total rewards required
        int totalRewards = 0;
        for (int reward : rewards) {
            totalRewards += reward;
        }

        return totalRewards; // reeturning the minimum rewards needed
    }

    public static void main(String[] args) {

        // TESTING
        // test 1
        int[] ratings1 = {1, 0, 2};
        System.out.println(minRewards(ratings1)); // Output: 5

        // test 2
        int[] ratings2 = {1, 2, 2};
        System.out.println(minRewards(ratings2)); // Output: 4

        // test 3
        int[] ratings3 = {1, 2, 3, 4, 5};
        System.out.println(minRewards(ratings3)); // Output: 15

        // test 4 
        int[] ratings4 = {5, 4, 3, 2, 1};
        System.out.println(minRewards(ratings4)); // Output: 15

        // test 5
        int[] ratings5 = {1, 3, 2, 4, 5};
        System.out.println(minRewards(ratings5)); // Output: 11
    }
}
