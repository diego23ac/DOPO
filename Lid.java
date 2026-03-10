import java.util.*;

public class Lid {
    private static final int HEIGHT = 1;
    private int width;
    private String color;
    private Rectangle rectangle;
    private Cup cup;
    private boolean isVisible;
    
    public Lid(int value, int tHeight) {
        this.width = 2*value - 1;
        //this.assignColor();
        this.cupRectangle = new Rectangle();
        rectangle = new Rectangle(5 * HEIGHT, this.height * 15, "red", 30, 295);
        rectangle.makeVisible();
    }

    public int getWidth(){
        return width;
    }

    public void makeInvisible() {
        this.rectangle.makeInvisible();
    }
}