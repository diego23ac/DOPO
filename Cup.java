import java.util.*;

public class Cup{
    private int height;
    private String color;
    private Rectangle[] cupRectangle;
    private Lid lid;
    private boolean isVisible;
    
    public Cup(int value,int tHeight){
        this.height = 2*value -1;
        this.assignColor();
        this.createCup(tHeight);
        
    }
    
    private void createCup(int tHeight){
        Rectangle base = new Rectangle(5,this.height*15,"blue",30,300-this.height-tHeight*15);
        Rectangle ladoA;
        Rectangle ladoB;
    }
    private void assignColor(){
        
    }
}