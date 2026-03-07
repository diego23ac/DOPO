import java.util.*;

public class Cup{
    private int height;
    private String color;
    private Rectangle cupRectangle[];
    private Lid lid;
    private boolean isVisible;

    public Cup(int value,int tHeight){
        this.height = 2*value -1;
        this.assignColor();
        this.cupRectangle = new Rectangle[3];
        this.createCup(tHeight);
    }

    private void createCup(int tHeight){
        Rectangle base = new Rectangle(5,this.height*15,"blue",30,295);
        base.makeVisible();
        cupRectangle[0] = base;
        Rectangle ladoA = new Rectangle(this.height*15,5,"blue",30,300-this.height*15);
        ladoA.makeVisible();
        cupRectangle[1] = ladoA;
        Rectangle ladoB = new Rectangle(this.height*15,5,"blue",30+this.height*15,300-this.height*15);
        ladoB.makeVisible();
        cupRectangle[2] = ladoB;
    }

    public void makeInvisible(){
        for(int i = 0;i<3;i++){
            cupRectangle[i].makeInvisible();
        }
    }

    private void assignColor(){

    }
}