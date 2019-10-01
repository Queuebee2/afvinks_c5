import javax.swing.*;
import javax.xml.stream.events.StartDocument;


/* todo
    - RaceTrackLength
    - int Paard.horsepower = 1;
    - double Paard.weight = ;
    - paard needs to know its raceTime/lifetime? Does that work? NO. because if we start horse 1 and
    then start 10 other horses, horse 1 would have the advantage of having to race against 1 horse, 2
    horse, 3 horse, wheras horse 10 will always be competing with all other horses.

    to make the race game fair, the race isn't time-bound. Rather, it's bound to which horse reaches
    the finish in the fewest steps. This prevents horse 1 from exectuing 9 more steps than horse 10.
    todo
    - Paard eigen run method die zijn persoonlijke x en y verandert? Of x en y in een soort
    raceveld bewaren
 */

import static javax.swing.UIManager.getSystemLookAndFeelClassName;


public class HorseRace {

    // todo: contestantamount = trackamount  < horseamount
    //       red tracks for unused tracks

    private static int CONTESTANTAMOUNT = 5;
    private static int START_X = 0;
    private static int TRACKLENGTH = 100;
    private static Creature[] contestants;
    private static boolean raceInProgress = false;


    private static void runRace(RaceGUI gui){
        int posX;

        //so this is like the gameloop.

        for (Creature horse:contestants){
            /*
                todo:
                   horses can have like a 'pathToWalk'
                   Each gametick the pathToWalk can only decrease by maxHorseSpeed
             */
            posX = horse.getX();
            horse.move();
            int distanceTraveled = horse.getX() - posX;
                    // how does the image get re-rendered?
                    // do we need to add to the line that already exists
                    // or is it no different than just redrawing the rect
                    // or are we overwriting a rect for no reason, should we do
                    // new rect - existing rect
            gui.log("moved horse");
        }

    }

    private static void createRace(RaceGUI gui){
        gui.log("creating race");
        contestants = new Creature[CONTESTANTAMOUNT];
        for (int i = 0; i < CONTESTANTAMOUNT; i++) {
            gui.log("creating horse " + i);

            Creature contestingHorse = new Horse(START_X, i*10+10);

            gui.log(contestingHorse.getPos().toString());
            contestants[i] = contestingHorse;
            gui.log("added horse "+i);
            gui.log("creating track");

            gui.createTrack(START_X, i*10+10, TRACKLENGTH);

            gui.log("created track " + i);
        }}

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, IllegalAccessException, InstantiationException
        {
            System.out.println("Public Main Started - HorseRace");

            UIManager.setLookAndFeel(getSystemLookAndFeelClassName());

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {

                    RaceGUI raceGUI = new RaceGUI();
                    raceGUI.setVisible(true);

                    // todo : extract gamestate and update it to the GUI every loop, somehow
                    //  - so right now I think the 'gamestate' is represented in the Horse objects, in
                    //  combination with the START_X value

                    createRace(raceGUI);
                    runRace(raceGUI);


                }});
        }
    }
