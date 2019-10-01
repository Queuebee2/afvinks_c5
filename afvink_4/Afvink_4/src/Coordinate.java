public class Coordinate {

    private int x;
    private int y;
    private int z;

    @Override
    public String toString() {
        return "C"+" X:"+x+" Y:"+y+" Z:"+z;
    }

    public Coordinate(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Coordinate(int x) {
        this.x = x;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;

    }
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}