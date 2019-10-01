import java.awt.*;
import java.util.Random;

public class Horse extends Creature {

    private Handler handler;

    private static final int horsepower = 1;
    protected static int horseCount;
    private int horseNumber;
    private String name;
    private Random r = new Random();



    public Horse(String name, int horseNumber, int x, int y, ID id,  Handler handler) {
        super(name, x, y, id);
        this.horseNumber = horseNumber;
        this.handler = handler;
        this.velX = 1;

    }

    public Horse(Handler handler) {
        this("Horse", ++horseCount, 0, horseCount*30+100, ID.Horse, handler);
    }

    @Override
    public void tick() {
        x =  Game.clamp(x, 0, 400);

    }

    public void move() {
        x += velX;
        accelerate();

    }

    public void accelerate() {
        switch (r.nextInt(3)) {
            case 1:
                velX += 1;
                break;
            case 2:
                velX -= 1;
                break;
            case 3:
                break;

        }
        velX = Game.clamp(velX, -2, 3);



    }

    @Override
    public void render(Graphics g) {

        g.setColor(new Color(166, 92, 27));
        g.fillOval(x,y,26,26);

    }

    public static void resetHorses() {
        horseCount = 0;
    }
}
