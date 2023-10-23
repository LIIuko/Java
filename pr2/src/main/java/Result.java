import java.io.Serializable;

public class Result implements Serializable {
    private double x1;
    private double x2;

    public Result(double x1, double x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }
}
