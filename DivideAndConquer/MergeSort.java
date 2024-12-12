package DivideAndConquer;

public class MergeSort {

    public static void mergesort(int arr[], int low, int high) {

        if (low < high) {
            int mid = (low + high) / 2;
            mergesort(arr, low, mid);
            mergesort(arr, mid + 1, high);
            merge(arr, low, mid, high);


        }
    }

    private static void merge(int arr[], int low, int mid, int high) {

        int leftArr[] = new int[mid - low + 1];
        int rightArr[] = new int[high - mid];

        // * copying from arr to leftside
        for (int i = 0; i < leftArr.length; i++) {
            leftArr[i] = arr[low + i];
        }

        // * copying from arr to rightside
        for (int i = 0; i < rightArr.length; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }

        int i = 0;
        int j = 0;
        int k = low;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] < rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
                k++;
            } else {
                arr[k] = rightArr[j];
                j++;
                k++;
            }
        }

        while (i < leftArr.length && j >= rightArr.length) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < rightArr.length && i >= leftArr.length) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }

    };

}
