package plugins;

public class Factorial {
    private int n;

    public Factorial(int n) {
        this.n = n;
    }

    public Integer call() {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }
}
