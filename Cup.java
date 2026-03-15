import java.util.*;

public class Cup {
    private int height;
    private String color;
    private Rectangle cupRectangle[];
    private Lid lid;
    private boolean isVisible;

    public Cup(int value,int towerMaxHeight,int towerWidth, int towerHeight) {
        this.height = 2*value -1;
        this.assignColor(value);
        this.cupRectangle = new Rectangle[3];
        this.createCup(towerMaxHeight,towerWidth, towerHeight);
    }

    private void createCup(int towerMaxHeight,int towerWidth, int towerHeight) {
        int middle = (towerWidth*20 - this.height*20)/2;
        if (this.height > 1) {
            Rectangle base = new Rectangle(20,this.height*20,color,30 + middle,280 - towerHeight*20);
            cupRectangle[0] = base;
            Rectangle ladoA = new Rectangle(this.height*20,20,color,30+middle,300 - this.height*20-towerHeight*20);
            cupRectangle[1] = ladoA;
            Rectangle ladoB = new Rectangle(this.height*20,20,color,middle+10 + this.height*20,300-this.height*20 - towerHeight*20);
            cupRectangle[2] = ladoB;
        } else {
            Rectangle base = new Rectangle(20,this.height*20,color,30+middle,280-towerHeight*20);
            cupRectangle[0] = base;
        }
        this.makeVisible();
    }

    public void makeVisible(){
        isVisible = true;
        for(int i = 0; i < 3; i++){
            if (cupRectangle[i] != null) {
            cupRectangle[i].makeVisible();
            }
        }
    }
    
    public void makeInvisible() {
        for(int i = 0; i < 3; i++) {
            if (cupRectangle[i] != null) {
            cupRectangle[i].makeInvisible();
            }
        }
    }

    private void assignColor(int value) {
        String[] colors = {"blue","green","red","yellow","magenta","black"};
        String color = colors[(value - 1) % 5];
        this.color = color;
    }
    
    public int getHeight() {
        return height;
    }
}