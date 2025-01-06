package leetcode;

import java.util.Arrays;

public class PlusOne {

    public static int[] plusOne(int[] digits) {

        int digitsLength = digits.length;

        for (int i = digitsLength - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] result = new int[digitsLength + 1];
        result[0] = 1;
        return result;
    }

    public static void main(String[] args) {

        int[] digits = { 8, 9, 9, 9 };

        System.out.println("before: " + Arrays.toString(digits));

        System.out.println("result: " + Arrays.toString(plusOne(digits)));

    }

}