package plugins;


public class Repeater {
    private int n;
    private String text;

    public Repeater(int n, String text) {
        this.n = n;
        this.text = text;
    }

    public String call() {
        String result = "";
        for (int i = 0; i < n; i++) {
            result += text + '\n';
        }
        return result;
    }
}
