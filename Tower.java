import java.util.*;

public class Tower{
    private int height; //altura
    private int maxHeight;
    private int width; //ancho
    private HashMap<Integer,Cup> cups;
    private HashMap<Integer,Lid> lids;
    private boolean isVisible;
    private boolean isOk;
    private ArrayList<Rectangle> frame;
    
    public Tower(int maxHeight,int width){
        this.height = 0;
        this.maxHeight = maxHeight;
        this.width = width;
        this.cups = new HashMap<Integer,Cup>();
        this.lids = new HashMap<Integer,Lid>();
        this.isVisible = false;
        this.isOk = true;
        this.createTower();
        
    }
    
    private void createTower(){
        frame = new ArrayList<Rectangle>();
        for(int i = 0; i < this.maxHeight; i++){
            Rectangle rectangle = new Rectangle(4,20,"black",0,280-i*20);
            this.frame.add(rectangle);
        }
        Rectangle yAxis = new Rectangle(this.maxHeight*20,2,"black",25,302-this.maxHeight*20);
        this.frame.add(yAxis);
        Rectangle xAxis = new Rectangle(2,this.width*20,"black",25,300);
        this.frame.add(xAxis);
    }
    
    public void makeVisible(){
        isVisible = true;
        for(Rectangle rectangle: frame){
            rectangle.makeVisible();
        }
    }
    
    public void pushCup(int i){ //validar
        Cup cup = new Cup(i, this.maxHeight,this.width, this.height);
        cups.put(cups.size(), cup);   
        height += (cup.getHeight());
    }
    
    public void popCup() {
        Cup rCup = cups.remove(cups.size() - 1);
        rCup.makeInvisible();
        height -= (rCup.getHeight());
    }

    public void removeCup(int i){
        int counter = 0;
        for (Cup cup : cups.values()) {
            if (i *2-1== cup.getHeight()) {
                Cup rCup = cups.remove(counter);
                rCup.makeInvisible();
                break;
            }
            counter += 1;
        }
    }

    public void pushLid(int i){ //validar
       Lid lid = new Lid(i, this.height,this.width);
       lids.put(lids.size(), lid);
       height++;
    }

    public void popLid() {
        Lid rLid = lids.remove(lids.size() - 1);
        rLid.makeInvisible();
        height--;
    }

    public void removeLid(int i){
        int counter = 0;
        for (Lid lid : lids.values()) {
            if (i *2-1 == lid.getWidth()) {
                Lid rLid = lids.remove(counter);
                rLid.makeInvisible();
                break;
            }
            counter += 1;
        }
    }

    public int height(){
        return height;
    }
}