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

    private boolean hit;
    private long hitTimer;

    public Enemy(int type, int rank) {
        this.type = type;
        this.rank = rank;

        if (type == 1) {
//            color1 = Color.BLUE;
            color1 = new Color(0, 0, 255, 128);
            if (rank == 1) {
                speed = 2;
                r = 5;
                health = 1;
            }
            if (rank == 2) {
                speed = 2;
                r = 10;
                health = 2;
            }
            if (rank == 3) {
                speed = 1.5;
                r = 20;
                health = 3;
            }
            if (rank == 4) {
                speed = 1.5;
                r = 30;
                health = 4;
            }
        }
        //stronger, faster default
        if (type == 2) {
//            color1 = Color.RED;
            color1 = new Color(255, 0, 0, 128);
            if (rank == 1) {
                speed = 3;
                r = 5;
                health = 2;
            }
            if (rank == 2) {
                speed = 3;
                r = 10;
                health = 3;
            }
            if (rank == 3) {
                speed = 2.5;
                r = 20;
                health = 3;
            }
            if (rank == 4) {
                speed = 2.5;
                r = 30;
                health = 4;
            }
        }
        //slow but hard too kill
        if (type == 3) {
//            color1 = Color.GREEN;
            color1 = new Color(0, 255, 0, 128);
            if (rank == 1) {
                speed = 1.5;
                r = 5;
                health = 3;
            }
            if (rank == 2) {
                speed = 1.5;
                r = 10;
                health = 4;
            }
            if (rank == 3) {
                speed = 1.5;
                r = 25;
                health = 5;
            }
            if (rank == 4) {
                speed = 1.5;
                r = 45;
                health = 5;
            }
        }

        x = Math.random() * GamePanel.WIDTH / 2 + GamePanel.WIDTH / 4;
        y = -r;

        double angle = Math.random() * 140 + 20;
        rad = Math.toRadians(angle);

        dx = Math.cos(rad) * speed;
        dy = Math.sin(rad) * speed;

        ready = false;
        //on board
        if (x > r && x < GamePanel.WIDTH - r &&
                y > r && y < GamePanel.HEIGHT - r) {
            ready = true;
        }

        dead = false;

        hit = false;
        hitTimer = 0;
    }

    public void hit() {
        health--;
        if (health <= 0) {
            dead = true;
        }

        hit = true;
        hitTimer = System.nanoTime();
    }

    public void explode() {

        if (rank > 1) {

            int amount = 0;
            if (type == 1) {
                amount = 3;
            }
            if (type == 2) {
                amount = 3;
            }
            if (type == 3) {
                amount = 4;
            }

            for (int i = 0; i < amount; i++) {

                Enemy e = new Enemy(getType(), getRank() - 1);

//                e.setSlow(slow);
                e.x = this.x;
                e.y = this.y;
                double angle = 0;
                if (!ready) {
                    angle = Math.random() * 140 + 20;
                } else {
                    angle = Math.random() * 360;
                }
                e.rad = Math.toRadians(angle);
                e.dx = Math.cos(e.rad) * speed;
                e.dy = Math.sin(e.rad) * speed;

                GamePanel.enemies.add(e);
            }
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

        if (hit) {
            long elapsed = (System.nanoTime() - hitTimer) / 1000000;
            if (elapsed > 50) {
                hit = false;
                hitTimer = 0;
            }
        }
    }

    public void draw(Graphics2D g) {
        Color colorToUse;
        if (hit) {
            colorToUse = Color.WHITE;
        } else {
            colorToUse = color1;
        }

        g.setColor(colorToUse);
        g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);

        g.setStroke(new BasicStroke(3));
        g.setColor(colorToUse.darker());
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

    public int getType() {
        return type;
    }

    public int getRank() {
        return rank;
    }
}
