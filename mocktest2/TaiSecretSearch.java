package mocktest2;

public class TaiSecretSearch {

    public static void main(String[] args) {
        TaiSecretSearch problem = new TaiSecretSearch(-1, 1, 1, 1, -1, 0.5);

        System.out.printf("Time it take for argent A go to point Z(0, 0) is: %.6f\n", problem.timeFromA(0));

        System.out.printf("Appropriate position of C(x, 0) where the 2 agents arrive in the same time is: %.6f\n",
                problem.pointC());

    }

    // Person A
    double XA;
    double YA;
    double VA;

    // Person B
    double XB;
    double YB;
    double VB;

    public TaiSecretSearch(double xa, double ya, double va, double xb, double yb, double vb) {
        this.XA = xa;
        this.YA = ya;
        this.VA = va;

        this.XB = xb;
        this.YB = yb;
        this.VB = vb;
    }

    double timeFromA(double XZ) {
        // find the distance from A to the Xz (or to the line L) (use the absolute)
        double x = Math.abs(XZ - this.XA);// find the difference between A and Z (int x coordinate)
        double y = this.YA;

        // apply pytago
        double distance = Math.sqrt((x * x) + (y * y));

        // We have distance, have velocity => find time
        return (double) distance / (this.VA);
    }

    private double timeFromB(double XZ) {
        // find the distance from A to the Xz (or to the line L) (use the absolute)
        double x = Math.abs(XZ - this.XB);// find the difference between A and Z (int x coordinate)
        double y = this.YB;

        // apply pytago
        double distance = Math.sqrt((x * x) + (y * y));

        // We have distance, have velocity => find time
        return (double) distance / (this.VB);
    }

    double pointC() {
        // Find the point C that the agents A and B meet at the same time

        // time it take from A to C
        double XC = this.XA;

        double max = this.XB;
        if (this.XB < 1) {
            max = 1;
        }
        double min = this.XA;
        double eps = 0.0000001;

        while (max - min > eps) {
            double mid = (max + min) / 2.0;
            // compare time go from A to C and B to C
            if (this.timeFromA(XC) > this.timeFromB(XC)) {
                // it take long time for A --> C must be on left hand side
                // XC is the new mid
                max = mid;

                XC = (max + min) / 2.0;

            } else {
                // on the right side
                min = mid;

                XC = (max + min) / 2.0;
            }
        }

        return XC;
    }

}
