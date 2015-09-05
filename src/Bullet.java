import java.awt.*;

/**
 * Created by seyfer on 04.09.15.
 */
public class Bullet
{
    private double x;
    private double y;
    private int r;

    private double dx;
    private double dy;
    private double rad;
    private double speed;

    private Color color1;

    public Bullet(double angle, int x, int y) {
        this.x = x;
        this.y = y;

        r = 2;
        rad = Math.toRadians(angle);
        speed = 15;
        dx = Math.cos(rad) * speed;
        dy = Math.sin(rad) * speed;

        color1 = Color.YELLOW;
    }

    public boolean update() {

        x += dx;
        y += dy;

        if (x < -r || x > GamePanel.WIDTH + r || y < -r || y > GamePanel.HEIGHT + r) {
            return true;
        }

        return false;
    }

    public void draw(Graphics2D g) {
        g.setColor(color1);
        g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
    }

    public double getX() {
        return x;
    }

    public Bullet setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public Bullet setY(double y) {
        this.y = y;
        return this;
    }

    public int getR() {
        return r;
    }

    public Bullet setR(int r) {
        this.r = r;
        return this;
    }
}
