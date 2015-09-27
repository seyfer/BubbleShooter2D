import java.awt.*;

/**
 * Created by seyfer on 14.09.15.
 */
public class PowerUp
{
    /**
     * 1 -- + 1 life
     * 2 -- + 1 power
     * 3 -- + 2 power
     */
    private int type;
    private Color color1;
    private double x;
    private double y;
    private int r;

    public PowerUp(int type, double x, double y) {
        this.type = type;

        this.x = x;
        this.y = y;

        if (type == 1) {
            color1 = Color.PINK;
            r = 3;
        }
        if (type == 2) {
            color1 = Color.YELLOW;
            r = 3;
        }
        if (type == 3) {
            color1 = Color.YELLOW;
            r = 5;
        }
    }

    public boolean update() {
        y += 2;

        if (y > GamePanel.HEIGHT + r) {
            return true;
        }

        return false;
    }

    public void draw(Graphics2D g) {
        g.setColor(color1);
        g.fillRect((int) (x - r), (int) (y - r), r * 2, r * 2);

        g.setStroke(new BasicStroke(3));
        g.setColor(color1.darker());
        g.drawRect((int) (x - r), (int) (y - r), r * 2, r * 2);
        g.setStroke(new BasicStroke(1));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public int getType() {
        return type;
    }
}
