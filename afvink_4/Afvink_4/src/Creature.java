import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public  class Creature {

    protected String name;
    protected Coordinate pos = new Coordinate();  // X AND Y POSITION values (For a coordinate system)
    protected double weight;      // weight in kilograms
    protected double size;        // placeholder for size in centimeters?


    /* todo
        - ask about constructor overloading... Should/can I put default 'sets' at the instantiation
        of an object in a different method and call it in all constructors or can I have super constructors that
        always run on top of the custom constructor
     */

    // Creature default constructor
    Creature(){
        // create random Creature

    // todo: ask if I have a superclass with parameterized constructors, the sublcass inherits those, too?

    }
    // Creature parameterized constuctor
    Creature(String name){
        this.name = name;
        // create random Creature
    }

    Creature(int x, int y){
        this.pos.setX(x);
        this.pos.setY(y);
    }

    public void move(){
        //Only moves around the X-axis for now
        int maxVelocity = (int) getMaxSpeed();
        int movedDistance =  ThreadLocalRandom.current().nextInt(-maxVelocity-1, maxVelocity+1);
        this.pos.setX(this.pos.getX() + movedDistance);


    }
    public Coordinate getPos(){
        return pos;
    }

    public void setPos(int x){
        pos.setX(x);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    public int getX(){
        return this.getPos().getX();
    }

    public int getY(){
        return this.getPos().getY();
    }



    public void setPos(int x, int y){
        pos.setX(x);
        pos.setY(y);
    }

    double getMaxSpeed(){
        final double v = 0.0;
        return v;
    }
}
