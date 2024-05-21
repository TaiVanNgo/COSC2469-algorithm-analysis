package mocktest2.second_attempt;

public class TaiSecretSearch {
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

    public double timeFromA(double XZ) {
        double length = Math.sqrt((this.XA - XZ) * (this.XA - XZ) + this.YA * this.YA);
        return length / this.VA;
    }

    private double timeFromB(double XZ) {
        double length = Math.sqrt((this.XB - XZ) * (this.XB - XZ) + this.YB * this.YB);
        return length / this.VB;
    }

    public double pointC() {
        double esp = 0.0000001;
        double min = this.XA;
        double max = this.XB;

        while (max - min > esp) {
            double mid = (max + min) / 2;   
            double TA = timeFromA(mid);
            double TB = timeFromB(mid);

            if(TA < TB){
                min = mid;
            }else {
                max = mid;
            }
        }

        return max;//after the loop, max and min is really close, we can return either
    }

    public static void main(String[] args) {
        TaiSecretSearch secretSearch = new TaiSecretSearch(-1, 1, 1, 1, -1, 0.5);
        System.out.println(secretSearch.timeFromA(0));
        System.out.println(secretSearch.pointC());

    }
}
