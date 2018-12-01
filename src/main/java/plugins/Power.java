package plugins;


public class Power {
    private double n, power;

    public Power(int n, int power) {
        this.n = n;
        this.power = power;
    }

    public Double call() {
        return Math.pow(n, power);
    }
}
