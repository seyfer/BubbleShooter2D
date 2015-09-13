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

    private boolean firing;
    private long firingTimer;
    private long firingDelay;

    private boolean recovering;
    private long recoveryTimer;

    private int lives;
    private Color color1;
    private Color color2;

    private int score;

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

        firing = false;
        firingTimer = System.nanoTime();
        firingDelay = 200;

        recovering = false;
        recoveryTimer = 0;

        score = 0;
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

        if (firing) {
            long elapsed = (System.nanoTime() - firingTimer) / 1000000;
            if (elapsed > firingDelay) {
                GamePanel.bullets.add(new Bullet(270, x, y));
                firingTimer = System.nanoTime();
            }
        }

        long elapsed = (System.nanoTime() - recoveryTimer) / 1000000;
        if (elapsed > 2000) {
            recovering = false;
            recoveryTimer = 0;
        }
    }

    public void draw(Graphics2D g) {

        if (recovering) {
            g.setColor(color2);
            g.fillOval(x - r, y - r, r * 2, r * 2);

            g.setStroke(new BasicStroke(3));
            g.setColor(color2.darker());
            g.drawOval(x - r, y - r, r * 2, r * 2);
            g.setStroke(new BasicStroke(1));
        } else {
            g.setColor(color1);
            g.fillOval(x - r, y - r, r * 2, r * 2);

            g.setStroke(new BasicStroke(3));
            g.setColor(color1.darker());
            g.drawOval(x - r, y - r, r * 2, r * 2);
            g.setStroke(new BasicStroke(1));
        }
    }

    public void loseLife() {
        lives--;
        recovering = true;
        recoveryTimer = System.nanoTime();
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

    public boolean isFiring() {
        return firing;
    }

    public Player setFiring(boolean firing) {
        this.firing = firing;
        return this;
    }

    public int getLives() {
        return lives;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean isRecovering() {
        return recovering;
    }

    public int getScore() {
        return score;
    }

    public Player setScore(int score) {
        this.score = score;
        return this;
    }

    public void addScore(int i) {
        score += i;
    }
}
