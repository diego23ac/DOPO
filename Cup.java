import java.util.*;

public class Cup {
    private int height;
    private String color;
    private Rectangle cupRectangle[];
    private Lid lid;
    private boolean isVisible;

    public Cup(int value,int tMaxHeight,int tWidth, int tHeight) {
        this.height = 2*value -1;
        this.assignColor();
        this.cupRectangle = new Rectangle[3];
        this.createCup(tMaxHeight,tWidth, tHeight);
    }

    private void createCup(int tMaxHeight,int tWidth, int tHeight) {
        int mid = (tWidth*20/2) - this.height*20/2;
        if (this.height > 1) {
            Rectangle base = new Rectangle(20,this.height*20,"blue",30+mid,280-tHeight*20);
            base.makeVisible();
            cupRectangle[0] = base;
            Rectangle ladoA = new Rectangle(this.height*20,20,"blue",30+mid,300-this.height*20-tHeight*20);
            ladoA.makeVisible();
            cupRectangle[1] = ladoA;
            Rectangle ladoB = new Rectangle(this.height*20,20,"blue",mid+10+this.height*20,300-this.height*20-tHeight*20);
            ladoB.makeVisible();
            cupRectangle[2] = ladoB;
        } else {
            Rectangle base = new Rectangle(20,this.height*20,"blue",30+mid,280-tHeight*20);
            base.makeVisible();
            cupRectangle[0] = base;
        }
    }

    public void makeInvisible() {
        for(int i = 0; i<3;i++){
            cupRectangle[i].makeInvisible();
        }
    }

    private void assignColor() {

    }
    
    public int getHeight() {
        return height;
    }
}