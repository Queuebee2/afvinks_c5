import java.awt.*;

public class HUD {

    public static int HEALTH = 1000;
    public static int maxHEALTH = 1000;

    private int greenValue, redValue = 255;

    private int score = 0;
    private int level = 0;
    private int fps = 0;


    public void tick() {

        HEALTH = Game.clamp(HEALTH,0, maxHEALTH);
        greenValue = Game.clamp(greenValue, 0, 255);
        greenValue = (int) (((double) HEALTH / (double) maxHEALTH) * 255);
        redValue =   255-greenValue;

        score++;
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15,15,209,32);
        g.setColor(new Color(redValue, greenValue, 0));
        g.fillRect(20,20,(int)(((double) HEALTH / (double) maxHEALTH) * 100d)*2,24);
        g.setColor(Color.WHITE);
        g.drawRect(15,15,209,32);
        g.setColor(Color.blue);
        // g.drawString( String.format("%.2f",(((double) HEALTH / (double) maxHEALTH) * 100d))+ "%", 24, 32);
        g.drawString( "HORSE RACE", 24, 32);

        g.setColor(Color.gray);
        g.fillRect(15,15+32,100,48+16);
        g.setColor(Color.blue);
        g.drawString("Time : " +  score, 24,  48+16);
        //g.drawString(": "+ level, 24, 64+16);
        g.drawString("FPS: " + fps, 24, 64+16+16);
    }

    public void score(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void incLevel() {
        this.level++;
    }

    public void setFPS(int fps) {
        this.fps = fps;
    }
}
