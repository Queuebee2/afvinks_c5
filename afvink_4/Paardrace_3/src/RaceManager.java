import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.awt.*;
import java.sql.Statement;
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

        this.g = g;
    }

    public void start() { // de hele race
        this.RaceState = STATE.RaceInProgress;
        resetHorses();
        System.out.println("Race Started");

        while (RaceState == STATE.RaceInProgress) {
            move(this.g);
            checkWinners();      // switches state when there are winners.
        }

        System.out.println("Race Finished");
        for (Paard p : winners) {
            System.out.println();
        }


    }

    public void move(Graphics g) {
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
