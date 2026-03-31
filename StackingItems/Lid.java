import java.util.*;

public class Lid extends StackingItem {
    private int width;
    private Rectangle lidRectangle[];
    private Cup cup;
    
    public Lid(int value, int towerMaxHeight, int towerHeight, int towerWidth, boolean isVisible) {
        super(value, isVisible);
        this.height = 1;
        this.width = 2*value - 1;
        this.assignColor(value);
        this.lidRectangle = new Rectangle[2];
        this.create(towerMaxHeight,towerWidth, towerHeight);
        if(isVisible) { makeVisible(); }
    }

    public void moveDown(int value){
        for(int i = 0; i < 2; i++){
            lidRectangle[i].moveDown(value);
        }
        yBasePosition += value * 20;
    }
    
    public void makeVisible() { 
        isVisible = true;
        for(int i = 0; i < 2; i++){
            lidRectangle[i].makeVisible();
        }
    }
    
    public void makeInvisible() { 
        isVisible = false;
        for(int i = 0; i < 2; i++){
            lidRectangle[i].makeInvisible();
        } 
    }
    
    public int getWidth(){ return width; }
    
    public String getType(){ return "lid"; }
    
    private void assignColor(int value) {
        String[] colors = {"LPink","LGreen","LRed","LBlue","LYellow","black"};
        String color = colors[(value - 1) % 5];
        this.color = color;
    }
    
    private void create(int towerMaxHeight, int towerWidth, int towerHeight) {
        int middle = 10*(towerWidth - width) + 22;
        lidRectangle[0] = new Rectangle(20, width * 20, color,middle, 20*(towerMaxHeight - towerHeight - 1));
        lidRectangle[1] = new Rectangle(12, 12, "LYellowDiamond",(10*towerWidth) + 16, 20*(towerMaxHeight - towerHeight) - 16);
        yBasePosition = towerHeight;
        System.out.println("Tapa: " + value + ", PosicionBase: " + yBasePosition + ", PosicionTope: " + this.getTopPosition());
    }
}