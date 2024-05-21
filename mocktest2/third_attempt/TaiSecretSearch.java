package mocktest2.third_attempt;

public class TaiSecretSearch {
    // agent A
    double XA;
    double YA;
    double VA;

    // agent B
    double XB;
    double YB;
    double VB;

    TaiSecretSearch(double XA, double YA, double VA, double XB, double YB, double VB) {
        this.XA = XA;
        this.YA = YA;
        this.VA = VA;
        this.XB = XB;
        this.YB = YB;
        this.VB = VB;
    }

    // time complexity: O(1)
    double timeFromA(double XZ) {
        // find difference of XZ and XA
        double x = Math.abs(XZ - this.XA);

        double distance = Math.sqrt((x * x) + (this.YA * this.YA));

        return (double) distance / this.VA;
    }

    double timeFromB(double XZ) {
        // find difference of XZ and XA
        double x = Math.abs(XZ - this.XB);

        double distance = Math.sqrt((x * x) + (this.YB * this.YB));

        return (double) distance / this.VB;
    }

    double pointC() {
        // find XC
        double esp = 0.0000001;
        double min = this.XA;
        double max = this.XB;

        while (max - min > esp) {
            double XC = (max + min) / 2;
            if (timeFromA(XC) > timeFromB(XC)) {
                // if time from A > time from B to XC -> the C is too close to B, we move it to
                // the side of point A (left hand side)
                max = XC;
            } else {
                min = XC;
            }
        }

        return max;// return max or min are both ok
    }

    public static void main(String[] args) {
        TaiSecretSearch search = new TaiSecretSearch(-1, 1, 1, 1, -1, 0.5);
        System.out.printf("%.6f\n", search.timeFromA(0));
        System.out.printf("%.6f\n", search.pointC());

    }
}
