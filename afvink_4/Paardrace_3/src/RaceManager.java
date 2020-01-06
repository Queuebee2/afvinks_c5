import java.awt.*;
import java.util.LinkedList;

public class RaceManager {


    private LinkedList<Paard> winners = new LinkedList<Paard>();
    public LinkedList<Paard> paarden = new LinkedList<Paard>();


    private Graphics g;

    public enum STATE {
        Waiting,
        RaceInProgress,
        RaceFinished;
    }

    public STATE RaceState = STATE.Waiting;

    public RaceManager(Graphics g) {

        for (int i = 0; i < PaardenRace.AANTALPAARDEN; i++) {
            addObject(new Paard());
        }
        // graphics komen van het raceveld af (een JPanel)
        this.g = g;
    }

    public void start() { // de hele race
        this.RaceState = STATE.RaceInProgress;
        resetHorses();
        System.out.println("Race Started");

        int UPDATES_PER_SECOND = 60;
        int UPDATE_DEVIDER = 1000000;
        long execStamp = System.nanoTime() / UPDATE_DEVIDER;

        while (RaceState == STATE.RaceInProgress) {

            long now = System.nanoTime();
            tick(this.g);
            checkWinners();      // switches state when there are winners.
        }

        System.out.println("Race Finished");
        for (Paard p : winners) {
            System.out.println();
        }


    }

    public void tick(Graphics g) {
        for (int i = 0; i < this.paarden.size(); i++) {
            Paard tempPaard =  this.paarden.get(i);
            tempPaard.move();
            tempPaard.render(g);

            if (winningHorse(tempPaard)) {
                this.winners.add(tempPaard);
            }
        }

    }

    private void checkWinners() {
        if (winners.size() >= 1) {
            this.RaceState = STATE.RaceFinished;
        }
    }

    private boolean winningHorse(Paard paard) {
        if (paard.getX() >= PaardenRace.FINISHLINE) {
            return true;
        }
        return false;
    }

    public void addObject(Paard paard) {
        this.paarden.add(paard);
    }

    public void resetHorses() {
        for (Paard p : paarden) {
            p.setX(0);
            g.setColor(Color.GREEN);
            g.fillRect(0,0,300,300);
        }
        clearWinners();

    }
    public void clearWinners() {
        winners.clear();
    }

}
