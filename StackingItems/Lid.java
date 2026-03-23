import java.util.*;

public class Lid {
    private static final int HEIGHT = 1;
    private int value;
    private int width;
    private String color;
    private int yBasePosition;
    private Rectangle rectangle;
    private Cup cup;
    private boolean isVisible;
    
    public Lid(int value, int towerMaxHeight, int towerHeight, int towerWidth, boolean isVisible) {
        this.value = value;
        this.width = 2*value - 1;
        this.assignColor(value);
        int middle = (20*towerWidth - 20*width)/2 + 22;
        this.rectangle = new Rectangle(20 * HEIGHT, width * 20, color,middle, towerMaxHeight*20-20 - towerHeight*20);
        this.yBasePosition = 280 - towerHeight*20;
        if(isVisible) { makeVisible(); }
    }

    public void moveDown(int value){
        rectangle.moveDown(value);
        yBasePosition += value * 20;
    }
    
    public void makeVisible() { rectangle.makeVisible(); }
    
    public void makeInvisible() { rectangle.makeInvisible(); }
    
    public int getWidth(){ return width; }
    
    public int getBasePosition() {return yBasePosition; }
    
    public int getValue() { return value; }
    
    private void assignColor(int value) {
        String[] colors = {"blue","green","red","yellow","magenta","black"};
        String color = colors[value - 1 % 5];
        this.color = color;
    }
}