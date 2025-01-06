package leetcode;

public class TwoSum {

    public static int[] ts(int nums[], int target) {

        int[] result = { -1, -1 };

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {

                if (i != j) {
                    if (nums[i] + nums[j] == target) {
                        result[0] = i;
                        result[1] = j;
                        return result;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String args[]) {

        int[] nums = { 9, 20, 15, 1, 9, 15, 2, 5 };
        int target = 21;

        int[] result = ts(nums, target);
        System.err.println("i= " + result[0] + " j= " + result[1]);

    }

}

// Input: nums = [2,7,11,15], target = 9
// Output: [0,1]
// Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
