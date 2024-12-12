import java.util.Arrays;

import DivideAndConquer.MergeSort;

public class Playground {
    public static void main(String[] args) {

        int arr[]={5,1,3,9,2,4,8,7,6};

        MergeSort.mergesort(arr, 0, 8);

        System.out.println(Arrays.toString(arr));

        


    }
}