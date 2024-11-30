import java.util.Arrays;

import leetcode.RemoveElement;

public class Playground {
    public static void main(String[] args) {

        int[] nums = { 3, 2, 2, 3 };
        System.out.println("before "+Arrays.toString(nums));
        int val = 3;
        RemoveElement removeElement = new RemoveElement();
        int[] result = removeElement.removeElement(nums, val);
        System.err.println("after "+Arrays.toString(result));

    }
}