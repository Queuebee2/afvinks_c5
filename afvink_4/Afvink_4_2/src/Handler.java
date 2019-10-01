import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<Creature> object = new LinkedList<Creature>();


    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            Creature tempObject = object.get(i);
            tempObject.tick();
        }

    }

    public void render(Graphics g) {

        for (int i = 0; i < object.size(); i++) {
            Creature tempObject = object.get(i);
            tempObject.render(g);
        }

    }

    public void addObject(Creature object) {
        this.object.add(object);
    }

    public void removeObject(Creature object) {
        this.object.remove(object);
    }

}