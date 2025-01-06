package leetcode;

import java.util.Arrays;

public class RemoveElement {
    public static int removeElement(int[] nums, int val) {

        int back = nums.length - 1;

        for (int i = 0; i <= back;) {
            if (nums[i] == val) {
                if (nums[back] == val) {
                    back--;

                } else {
                    System.out.println(back);
                    int temp = nums[i];
                    nums[i] = nums[back];
                    nums[back] = temp;
                    back--;
                    i++;
                }
            } else {
                i++;
            }
        }

        System.out.println(Arrays.toString(nums));
        return back + 1;

    }

    public static void main(String[] args) {
        int[] nums = { 3, 2, 2, 3 };
        System.out.println("result= " + removeElement(nums, 3));

    }
}
