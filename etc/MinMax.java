package etcs;

public class MinMax {

    public static class Pair {
        int min;
        int max;

        public Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    public Pair findMinMax(int arr[], int s, int e) {

        if (s == e) {
            // * Single element
            return new Pair(arr[s], arr[e]);
        }
        if (s + 1 == e) {
            // * Two elements
            if (arr[s] > arr[e]) {
                return new Pair(arr[e], arr[s]);
            } else {
                return new Pair(arr[s], arr[e]);
            }
        }

        int mid = (s + e) / 2;
        Pair leftPair = findMinMax(arr, s, mid);
        Pair rightPair = findMinMax(arr, s, mid + 1);

        int overallMin = Math.min(leftPair.min, rightPair.min);
        int overallMax = Math.max(leftPair.max, rightPair.max);

        return new Pair(overallMin, overallMax);
    }

}
