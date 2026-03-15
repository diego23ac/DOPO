import java.util.*;

public class Lid {
    private static final int HEIGHT = 1;
    private int width;
    private String color;
    private Rectangle rectangle;
    private Cup cup;
    private boolean isVisible;
    
    public Lid(int value, int towerHeight, int towerWidth) {
        this.width = 2*value - 1;
        this.assignColor(value);
        int middle = (20*towerWidth - 20*this.width)/2;
        this.rectangle = new Rectangle(20 * HEIGHT, this.width * 20, color, 30 + middle, 280 - towerHeight*20);
        rectangle.makeVisible();
    }

    public int getWidth() {
        return width;
    }

    public void makeInvisible() {
        this.rectangle.makeInvisible();
    }
    
    private void assignColor(int value) {
        String[] colors = {"blue","green","red","yellow","magenta","black"};
        String color = colors[value - 1 % 5];
        this.color = color;
    }
}