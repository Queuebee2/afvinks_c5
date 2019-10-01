import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


class Horse extends Creature{

        private int horsePower = 1;
        private static int maxHorsePower = 3;
        public static int horseNumber = 0;
        double maxSpeed;


    Horse() {
        this(0, 0);
    }

    Horse(int x, int y) {
        System.out.println("Horse constructor at work");
        horseNumber = ++horseNumber;
        setName("Horse_" + Integer.toString(horseNumber));
        horsePower = ThreadLocalRandom.current().nextInt(1, maxHorsePower);
        this.setPos(x, y);
        talk(); // moet dit this.talk zijn? Moet die andere gewoon setPos zijn? HhhhhHH?
    }

    public String toString(){
        return "my name is " + this.getName() + ", my horsenumber is " + Integer.toString(this.horseNumber) + " and my horsepower is " + Integer.toString(this.horsePower) +
                " I'm on X:" + Integer.toString(this.getX()) + ", Y:" + Integer.toString(this.getY());
    }

    public void talk(){
        System.out.println(this.toString());
    }

    @Override
    double getMaxSpeed (){
        final double maxSpeed = horsePower*2.31;
        return maxSpeed;
    }

    public void printCurrentHorseNumber(){System.out.println(horseNumber);}
}

