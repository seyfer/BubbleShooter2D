import java.awt.*;

/**
 * Created by seyfer on 10/24/15.
 */
public class Text
{
    private double x;
    private double y;
    private long time;
    private String s;

    private long start;

    public Text(double x, double y, long time, String s) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.s = s;
        start = System.nanoTime();
    }

    public boolean update() {
        long elapsed = (System.nanoTime() - start) / 1000000;
        return elapsed > time;
    }

    public void draw(Graphics2D g) {
        g.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        g.setColor(Color.WHITE);
        g.drawString(s, (int) x, (int) y);
    }
}
