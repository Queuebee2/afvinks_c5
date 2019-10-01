public class RaceManager {

    private Handler handler;
    private HUD hud;

    public STATE raceState = STATE.Creating;
    public int tickTimer = 100;
    protected Horse winningHorse;

    public enum STATE {
        RaceInProgress,
        Finished,
        Creating,
        Waiting;
    }

    public RaceManager(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }


    public void tick() {

        //todo make farthestHorse global
        // send  vars to HUD
        // remove HEALTHBAR from hud
        tickTimer--;

        switch (raceState) {
            case Creating:
                //  todo create in a loop and make loopsize global
                handler.addObject(new Horse(handler));
                handler.addObject(new Horse(handler));
                handler.addObject(new Horse(handler));
                handler.addObject(new Horse(handler));
                handler.addObject(new Horse(handler));
                raceState = STATE.Waiting;
                break;
            case Waiting:
                if (tickTimer <= 0) {
                    raceState = STATE.RaceInProgress;
                }
                break;
            case RaceInProgress:
                for (int i = 0; i < handler.object.size(); i++) {
                    Creature contestant = handler.object.get(i);

                    if (contestant.getX() > 300) { //  todo make global
                        raceState = STATE.Finished;
                        tickTimer = 1000;
                    }
                    contestant.move();
                }
                break;
            case Finished:
                if (tickTimer <= 0) {
                    for (int i = handler.object.size() - 1; i >=0; i--) {
                        Creature contestant = handler.object.get(i);
                        handler.removeObject(contestant);
                    }
                    tickTimer = 100;
                    Horse.resetHorses(); // static method to set horseCount to 0 so the y*horseCount doesnt incr
                    raceState = STATE.Creating;

                }
                break;
        }
    }

}
