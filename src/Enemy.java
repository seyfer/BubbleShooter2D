import java.awt.*;

/**
 * Created by seyfer on 05.09.15.
 */
public class Enemy
{
    private double x;
    private double y;
    private int r;

    private double dx;
    private double dy;
    private double rad;
    private double speed;

    private int health;
    private int type;
    private int rank;

    private Color color1;

    private boolean ready;
    private boolean dead;

    public Enemy(int type, int rank) {
        this.type = type;
        this.rank = rank;

        if (type == 1) {
            color1 = Color.BLUE;
            if (rank == 1) {
                speed = 2;
                r = 5;
                health = 1;
            }
        }

        x = Math.random() * GamePanel.WIDTH / 2 + GamePanel.WIDTH / 4;
        y = -r;

        double angle = Math.random() * 140 + 20;
        rad = Math.toRadians(angle);

        dx = Math.cos(rad) * speed;
        dy = Math.sin(rad) * speed;

        ready = false;
        dead = false;
    }

    public void hit() {
        health--;
        if (health <= 0) {
            dead = true;
        }
    }

    public void update() {
        x += dx;
        y += dy;

        //on board
        if (!ready) {
            if (x > r && x < GamePanel.WIDTH - r &&
                    y > r && y < GamePanel.HEIGHT - r) {
                ready = true;
            }
        }

        //borders
        if (x < r && dx < 0) dx = -dx;
        if (y < r && dy < 0) dy = -dy;
        if (x > GamePanel.WIDTH - r && dx > 0) dx = -dx;
        if (y > GamePanel.HEIGHT - r && dy > 0) dy = -dy;

    }

    public void draw(Graphics2D g) {
        g.setColor(color1);
        g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);

        g.setStroke(new BasicStroke(3));
        g.setColor(color1.darker());
        g.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));
    }

    public boolean isDead() {
        return dead;
    }

    public Enemy setDead(boolean dead) {
        this.dead = dead;
        return this;
    }

    public double getX() {
        return x;
    }

    public Enemy setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public Enemy setY(double y) {
        this.y = y;
        return this;
    }

    public int getR() {
        return r;
    }

    public Enemy setR(int r) {
        this.r = r;
        return this;
    }
}
