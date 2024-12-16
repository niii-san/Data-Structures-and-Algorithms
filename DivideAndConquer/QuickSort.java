package DivideAndConquer;

public class QuickSort {

    public static void quicksort(int arr[], int start, int end) {

        if (start < end) {
            int p = partition(arr, start, end);
            quicksort(arr, start, p - 1);
            quicksort(arr, p + 1, end);
        }

    };

    public static int partition(int arr[], int start, int end) {
        int leftPointer = start;
        int rightPointer = end;
        int pivotPointer =end;

        while (leftPointer < rightPointer) {

            while (arr[leftPointer] <= arr[pivotPointer] && leftPointer<=end-1) {
                leftPointer++;
            }

            while (arr[rightPointer] >= arr[pivotPointer] && rightPointer>=start+1) {
                rightPointer--;
            }

            if (leftPointer < rightPointer) {
                swap(arr, leftPointer, rightPointer);
            }

        }
        swap(arr, rightPointer, pivotPointer);

        return rightPointer;
    }

    public static void swap(int arr[], int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    };

}
