import java.util.*;

public class Lid {
    private static final int HEIGHT = 1;
    private int width;
    private String color;
    private Rectangle rectangle;
    private Cup cup;
    private boolean isVisible;
    
    public Lid(int value, int tHeight, int tWidth) {
        this.width = 2*value - 1;
        int mid= (tWidth*15/2)-this.width*15/2;
        //this.assignColor();
        this.rectangle = new Rectangle(20 * HEIGHT, this.width * 15, "red", 30+mid, 280);
        rectangle.makeVisible();
    }

    public int getWidth(){
        return width;
    }

    public void makeInvisible() {
        this.rectangle.makeInvisible();
    }
}