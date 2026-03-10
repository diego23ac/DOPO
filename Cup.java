import java.util.*;

public class Cup {
    private int height;
    private String color;
    private Rectangle cupRectangle[];
    private Lid lid;
    private boolean isVisible;

    public Cup(int value,int tHeight,int tWidth) {
        this.height = 2*value -1;
        this.assignColor();
        this.cupRectangle = new Rectangle[3];
        this.createCup(tHeight,tWidth);
    }

    private void createCup(int tHeight,int tWidth) {
        int mid= (tWidth*15/2)-this.height*15/2;
        Rectangle base = new Rectangle(20,this.height*15,"blue",30+mid,280);
        base.makeVisible();
        cupRectangle[0] = base;
        Rectangle ladoA = new Rectangle(this.height*15,20,"blue",30+mid,300-this.height*15);
        ladoA.makeVisible();
        cupRectangle[1] = ladoA;
        Rectangle ladoB = new Rectangle(this.height*15,20,"blue",mid+30+this.height*15,300-this.height*15);
        ladoB.makeVisible();
        cupRectangle[2] = ladoB;
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