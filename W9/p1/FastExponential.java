package W9.p1;

public class FastExponential {
    // Implement an algorithm to calculate (X^N) % 1,000,000,007. N is an integer
    // and can be as big as 10^9.
    static int MOD = 1000000007;

    public static void main(String[] args) {
        System.out.println("Power brute force: " + powerBruteForce(2, 10));
        System.out.println("Power decrease conquer: " + fastExponential(2, 10));

        System.out.println("Power brute force: " + powerBruteForce(7, 10001));
        System.out.println("Power decrease conquer: " + fastExponential(7, 10001));

        // performance test
        long t1 = System.currentTimeMillis();
        System.out.println("Power brute force: " + powerBruteForce(201, 500000000));
        System.out.println("Time: " + (System.currentTimeMillis() - t1) + "ms");
        t1 = System.currentTimeMillis();
        System.out.println("Power decrease conquer: " + fastExponential(201, 500000000));
        System.out.println("Time: " + (System.currentTimeMillis() - t1) + "ms");

    }

    static int powerBruteForce(int X, int N) {
        long res = 1;
        for (int i = 0; i < N; i++) {
            res = (res * X) % MOD;
        }
        return (int) res;
    }

    static int fastExponential(int X, int N) {
        // for ex: 2^ 10 -> X = 2, N = 10
        if (N == 0) {
            return 1;
        }
        if (N == 1) {
            return X;
        }

        long result = fastExponential(X, N / 2);

        result = (result * result) % MOD;

        if (N % 2 == 0) {
            return (int) result;
        }

        return (int) ((result * X) % MOD);
    }

}
