package W9.p1;

class problem1 {
    static int MOD = 1000000007;

    public static void main(String[] args) {
        System.out.println("Result of 2^100:");
        System.out.println("Power fast: " + fastExp(2, 100));
        System.out.println("Power bbrute force: " + bruteForceExp(2, 100));

        System.out.println("--------------------------------------------");
        System.out.println("Result of 7^10001:");
        long time1 = System.currentTimeMillis();
        System.out.println("Power fast: " + fastExp(7, 10001));
        System.out.println("Take: " + (System.currentTimeMillis() - time1));

        long time2 = System.currentTimeMillis();
        System.out.println("Power bruteforce: " + bruteForceExp(7, 10001));
        System.out.println("Take: " + (System.currentTimeMillis() - time2));

        System.out.println("--------------------------------------------");
        System.out.println("Result of 201^500000000:");
        long time3 = System.currentTimeMillis();
        System.out.println("Power fast: " + fastExp(201, 500000000));
        System.out.println("Take: " + (System.currentTimeMillis() - time3));

        long time4 = System.currentTimeMillis();
        System.out.println("Power bruteforce: " + bruteForceExp(201, 500000000));
        System.out.println("Take: " + (System.currentTimeMillis() - time4));

    }

    // we have (X^N)^M = X^(N*M)
    // so to sipmlify the X^N we use (X^N/2)^N ==> X^(N/2*N)
    // static int powerFast(int X, int N) {
    // if (N == 0) {
    // return 1;
    // }
    // if (N == 1) {
    // return X;
    // }
    // long sub = powerFast(X, N / 2);
    // sub = (sub * sub) % MOD;
    // if (N % 2 == 1) {
    // return (int)((sub * X) % MOD);
    // }
    // return (int)sub;
    // }
    static int fastExp(int X, int N) {
        if (N == 0) {
            return 1;
        }

        if (N == 1) {
            return X;
        }

        long result = fastExp(X, N / 2);
        result = (result * result) % MOD;

        if (N % 2 == 1) {
            return (int) ((result * X) % MOD);
        }
        return (int)result;
    }


    static int bruteForceExp(int X, int N) {
        long res = 1;
        for (int i = 0; i < N; i++) {
            res = (res * X) % MOD;
        }

        return (int)res;
    }

}