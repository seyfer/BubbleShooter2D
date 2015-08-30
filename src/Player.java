import java.awt.*;

/**
 * Created by seyfer on 26.08.15.
 */
public class Player
{
    private int x;
    private int y;
    private int r;

    private int dx;
    private int dy;
    private int speed;

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    private int lives;
    private Color color1;
    private Color color2;

    public Player() {
        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT / 2;

        r = 5;
        dx = 0;
        dy = 0;
        speed = 5;
        lives = 3;

        color1 = Color.WHITE;
        color2 = Color.RED;
    }

    public void update() {

        if (left) {
            dx = -speed;
        }
        if (right) {
            dx = speed;
        }
        if (up) {
            dy = -speed;
        }
        if (down) {
            dy = speed;
        }

        x += dx;
        y += dy;

        //for borders
        if (x < r) x = r;
        if (y < r) y = r;
        if (x > GamePanel.WIDTH - r) x = GamePanel.WIDTH - r;
        if (y > GamePanel.HEIGHT - r) y = GamePanel.HEIGHT - r;

        dx = 0;
        dy = 0;
    }

    public void draw(Graphics2D g) {

        g.setColor(color1);
        g.fillOval(x - r, y - r, r * 2, r * 2);

        g.setStroke(new BasicStroke(3));
        g.setColor(color1.darker());
        g.drawOval(x - r, y - r, r * 2, r * 2);
        g.setStroke(new BasicStroke(1));

    }

    public Player setLeft(boolean left) {
        this.left = left;
        return this;
    }

    public Player setRight(boolean right) {
        this.right = right;
        return this;
    }

    public Player setUp(boolean up) {
        this.up = up;
        return this;
    }

    public Player setDown(boolean down) {
        this.down = down;
        return this;
    }
}
