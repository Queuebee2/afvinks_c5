import java.awt.*;
import java.util.Random;

public class Paard {

    public static int PAARDCOUNT = 1;

    private int x;

    public int getY() {
        return y;
    }

    private int y;
    private String name;
    private Random r;
    private Color kleur;

    public Paard(String name, int x) {
        this.name = name;
        this.x = x;
        this.y = (PAARDCOUNT*15) + (15*PAARDCOUNT);
        this.r = new Random();
        this.kleur = new Color(r.nextInt(200), r.nextInt(200), r.nextInt(200));
        PAARDCOUNT++;
    }

    public Paard() {
        this("Paard", 0);
    }

    public Paard(int nummer) {
        this("Paard_" + Integer.toString(nummer), 0);
    }

    public void move() {
        this.x += r.nextInt(5);
    }

    public void render(Graphics g) {
        g.setColor(this.kleur);
        g.fillOval(x, y,15 ,15);
    }

    public int getX() {
        return x;
}

    public void setX(int x) {
        this.x = x;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
