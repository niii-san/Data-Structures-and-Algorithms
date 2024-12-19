package arrays;

import java.util.Arrays;

public class Arrs {

    static int[] insertion(int[] arr, int position, int data) {
        int[] newArr = new int[arr.length + 1];

        // first copying the elements of old to new
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }

        // moving elements onestep back until reaches position and puting the data in
        // position and exiting the loop
        for (int i = newArr.length - 1; i >= position; i--) {

            if (i == position) {

                newArr[i] = data;
                break;

            } else {

                newArr[i] = newArr[i - 1];

            }

        }

        return newArr;

    }

    static int[] deletion(int[] arr, int position) {
        int newNewArr[] = new int[arr.length - 1];

        for (int i = 0; i < position; i++) {
            newNewArr[i] = arr[i];
        }

        for (int i = position; i < newNewArr.length; i++) {
            newNewArr[i] = arr[i + 1];
        }

        return newNewArr;
    }

    public static void main(String[] args) {

        int[] myArr = { 1, 2, 3, 4, 5, 6, 7, 8 };

        int[] after = insertion(myArr, 0, 100);

        System.out.println("Before: " + Arrays.toString(myArr) + " After: " + Arrays.toString(after));

        int[] anotherArr = { 1, 2, 3, 4, 5, 6, 7, 8 };

        int[] afterDeletion = deletion(anotherArr, 4);

        System.out.println(
                "Before deletion: " + Arrays.toString(anotherArr) + " After deletion: "
                        + Arrays.toString(afterDeletion));

    }
}
