import java.util.Arrays;

import DivideAndConquer.QuickSort;

public class Playground {
    public static void main(String[] args) {

        int arr[] = { 5, 1, 3, 9, 2,2, 4, 8, 7, 6 };

        System.out.println("Before QS: " + Arrays.toString(arr));

        QuickSort.quicksort(arr, 0, 9);

        System.out.println("After QS: " + Arrays.toString(arr));

    }
}