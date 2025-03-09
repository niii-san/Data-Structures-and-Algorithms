package __practice;

public class Q9_PowerDnC {

    static int power(int x, int n) {
        if (n == 0) {
            return 1;
        }
        int halfpower = power(x, n / 2);

        if (n % 2 == 0) {
            return halfpower * halfpower;
        } else {
            return x * halfpower * halfpower;
        }
    }

    public static void main(String[] args) {

        System.out.println(power(5, 5));

    }
}
