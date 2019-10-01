import javax.sql.StatementEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable {


    public static final int WIDTH = 800, HEIGHT = 600;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private RaceManager raceManager;
    protected RaceGUI raceGUI;
    protected HUD hud;


    public Game() {

        setPreferredSize(new Dimension(390,420));
        setIgnoreRepaint(true);

        hud = new HUD();
        handler = new Handler();
        raceGUI = new RaceGUI(WIDTH, HEIGHT, this);


        raceManager = new RaceManager(handler, hud);






    }


    public synchronized void start() {

        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();      // stops/kill thread
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tick() {
        handler.tick();
        raceManager.tick();
        hud.tick();

    }


    private void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        // does this let g point to bs's grahpics ?
        Graphics g = bs.getDrawGraphics();

        //background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, Game.WIDTH - 17, Game.HEIGHT - 40);

        // render things that have to be rendered
        handler.render(g);
        hud.render(g);

        // render hud if game is in progress, otherwise render menu or print error
//        if (gameState == STATE.Game) {
//            hud.render(g);
//        } else if (gameState == STATE.Menu) {
//            menu.render(g);
//        } else {
//            System.out.println("GAME STATE INCORRECT!!");
//        }

        // dont understand this? dont we have to do something like
        // bs.setgraphics(g) before disposing?

        g.dispose();
        bs.show();

    }

    @Override
    public void run() {
        this.requestFocus(); // SO I HAD TO PUT IT HERE FFS!!!!!!
        long lastTime = System.nanoTime();
        double amountOfTicks = 30.0;        // ticks per second
        double ns = 1000000000 / amountOfTicks; // 9 0's
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        //gameloop
        while (running) {
            // check if we need to render a new frame or we can wait a bit
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
                frames++;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);

//                System.out.println("FPS: " + frames + "\nobj#: " + handler.object.size() + "\n of which enemies: " + handler.reportEnemies()); // highest fps achieved : 27023563
//                if (frames > 100) {
//                    System.out.println("adding more");
//                    spawnWaveTest(25, handler);
//
//                }
                hud.setFPS(frames);
                frames = 0;
            }
        }
        stop();
    }

    public static int clamp(int var, int min, int max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return min;
        } else {
            return var;
        }
    }
    public static float clamp(float var, float min, float max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return min;
        } else {
            return var;
        }
    }

    // creat and start a new game
    public static void main(String[] args) {
        new Game();
    }

}
