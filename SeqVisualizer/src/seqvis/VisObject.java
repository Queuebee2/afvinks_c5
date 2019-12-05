package seqvis;

import java.awt.*;

public class VisObject {

    private int x;
    private Color color;


    VisObject(int x, Color color) {
        this.x = x;
        this.color = color;
    }
    VisObject() {
        this(0, new Color(0,0,0));
    }


}
