package _coursework.Question2;

import java.util.Arrays;

/*
 * Question 2 (B)
You have two points in a 2D plane, represented by the arrays x_coords and y_coords. The goal is to find
the lexicographically pair i.e. (i, j) of points (one from each array) that are closest to each other.

Goal:
Determine the lexicographically pair of points with the smallest distance and smallest distance calculated
using
| x_coords [i] - x_coords [j]| + | y_coords [i] - y_coords [j]|

Note that
|x| denotes the absolute value of x.
A pair of indices (i1, j1) is lexicographically smaller than (i2, j2) if i1 < i2 or i1 == i2 and j1 < j2.

Input:
x_coords: The array of x-coordinates of the points.
y_coords: The array of y-coordinates of the points.
Output:
The indices of the closest pair of points.

Input: x_coords = [1, 2, 3, 2, 4], y_coords = [2, 3, 1, 2, 3]
Output: [0, 3]

Explanation: Consider index 0 and index 3. The value of | x_coords [i]- x_coords [j]| + | y_coords [i]-
y_coords [j]| is 1, which is the smallest value we can achieve.

 */

public class B_ClosestPair {
    // Class to represent a point with its x, y coordinates and its original index.
    static class Point {
        int x, y, index;

        Point(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    // Class to represent a pair of points.
    // We ensure that p.index is always less than q.index so that the pair is in
    // lexicographic order.
    static class Pair {
        Point p, q;

        Pair(Point p, Point q) {
            if (p.index <= q.index) {
                this.p = p;
                this.q = q;
            } else {
                this.p = q;
                this.q = p;
            }
        }

        // Compute the Manhattan distance between the two points.
        int distance() {
            return Math.abs(p.x - q.x) + Math.abs(p.y - q.y);
        }
    }

    // Main function to find the closest pair among points defined by x_coords and
    // y_coords.
    public static Pair findClosestPair(int[] x_coords, int[] y_coords) {
        int n = x_coords.length;
        Point[] points = new Point[n];
        // Create Point objects with original indices.
        for (int i = 0; i < n; i++) {
            points[i] = new Point(x_coords[i], y_coords[i], i);
        }
        // Sort points based on x coordinate.
        Arrays.sort(points, (a, b) -> Integer.compare(a.x, b.x));

        // Use divide and conquer to find the closest pair.
        return closestPair(points, 0, n - 1);
    }

    // Recursive divide and conquer function to find the closest pair in
    // points[left...right].
    private static Pair closestPair(Point[] points, int left, int right) {
        // Base case: when only two points exist, return that pair.
        if (right - left == 1) {
            return new Pair(points[left], points[right]);
        }
        // If there is only one point, no valid pair exists.
        if (left == right) {
            return null;
        }

        // Divide: Find the mid index.
        int mid = (left + right) / 2;

        // Recursively find the closest pair in the left half and right half.
        Pair leftPair = closestPair(points, left, mid);
        Pair rightPair = closestPair(points, mid + 1, right);

        // Choose the best (closest) pair among the two halves.
        Pair bestPair = null;
        if (leftPair == null) {
            bestPair = rightPair;
        } else if (rightPair == null) {
            bestPair = leftPair;
        } else {
            if (leftPair.distance() < rightPair.distance()) {
                bestPair = leftPair;
            } else if (leftPair.distance() > rightPair.distance()) {
                bestPair = rightPair;
            } else {
                // When distances are equal, choose the lexicographically smaller pair.
                bestPair = lexicographicallySmaller(leftPair, rightPair) ? leftPair : rightPair;
            }
        }

        int bestDist = (bestPair == null ? Integer.MAX_VALUE : bestPair.distance());

        // Build a "strip" of points that are close to the vertical line (x =
        // points[mid].x)
        int midX = points[mid].x;
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midX) < bestDist) {
                count++;
            }
        }
        Point[] strip = new Point[count];
        int j = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midX) < bestDist) {
                strip[j++] = points[i];
            }
        }
        // Sort the strip by y coordinate.
        Arrays.sort(strip, (a, b) -> Integer.compare(a.y, b.y));

        // Check pairs in the strip to see if there is a closer pair crossing the mid
        // line.
        for (int i = 0; i < strip.length; i++) {
            // Only need to check points whose y difference is less than bestDist.
            for (int k = i + 1; k < strip.length && (strip[k].y - strip[i].y) < bestDist; k++) {
                Pair currentPair = new Pair(strip[i], strip[k]);
                int d = currentPair.distance();
                if (d < bestDist) {
                    bestDist = d;
                    bestPair = currentPair;
                } else if (d == bestDist) {
                    if (lexicographicallySmaller(currentPair, bestPair)) {
                        bestPair = currentPair;
                    }
                }
            }
        }
        return bestPair;
    }

    // Compare two pairs lexicographically based on their original indices.
    // A pair is considered smaller if its first index is smaller,
    // or if the first indices are equal and its second index is smaller.
    private static boolean lexicographicallySmaller(Pair a, Pair b) {
        if (a == null)
            return false;
        if (b == null)
            return true;
        if (a.p.index < b.p.index)
            return true;
        if (a.p.index == b.p.index && a.q.index < b.q.index)
            return true;
        return false;
    }

    // Main function to test the algorithm.
    public static void main(String[] args) {
        // test 1
        int[] x_coords1 = { 1, 2, 3, 2, 4 };
        int[] y_coords1 = { 2, 3, 1, 2, 3 };
        Pair result1 = findClosestPair(x_coords1, y_coords1);

        // TESTING
        if (result1 != null) {
            System.out.println("[" + result1.p.index + ", " + result1.q.index + "]");
            // Output: [0, 3]
        } else {
            System.out.println("No valid pair found for test 1.");
        }

        // test 2
        int[] x_coords2 = { 5, 4, 3, 8, 6 };
        int[] y_coords2 = { 6, 4, 2, 7, 5 };
        Pair result2 = findClosestPair(x_coords2, y_coords2);

        // TESTING
        if (result2 != null) {
            System.out.println("[" + result2.p.index + ", " + result2.q.index + "]");
            // Output: [0, 4]
        } else {
            System.out.println("No valid pair found for test 2.");
        }

        // test 3
        int[] x_coords3 = { 8, 3, 5, 7, 1 };
        int[] y_coords3 = { 4, 8, 1, 3, 2 };
        Pair result3 = findClosestPair(x_coords3, y_coords3);

        // TESTING
        if (result3 != null) {
            System.out.println("[" + result3.p.index + ", " + result3.q.index + "]");
            // Output: [0, 3]
        } else {
            System.out.println("No valid pair found for test 3.");
        }
    }
}
