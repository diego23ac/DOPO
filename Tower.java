import java.util.*;

public class Tower{
    private int height; //altura
    private int width; //ancho
    private HashMap<Integer,Cup> cups;
    private HashMap<Integer,Lid> lids;
    private boolean isVisible;
    private boolean isOk;
    private Rectangle frame;
    
    public Tower(int maxHeight,int width){
        this.height = maxHeight;
        this.width = width;
        this.cups = new HashMap<Integer,Cup>();
        this.lids = new HashMap<Integer,Lid>();
        this.isVisible = false;
        this.isOk = true;
        this.createTower();
        
    }
    
    private void createTower(){
        ArrayList<Rectangle> frames;
        for(int i = 0; i < this.height; i++){
            Rectangle rectangle = new Rectangle(5,20,"black",0,300-this.height-i*15);
            rectangle.makeVisible();
        }
    }
    
    public void makeVisible(){
        isVisible = true;
        frame.makeVisible();
    }
    
    public void pushCup(int i){ //validar
       Cup cup = new Cup(i, this.height,this.width);
       
       cups.put(cups.size(), cup);
    }
    
    public void popCup() {
        Cup rCup = cups.remove(cups.size() - 1);
        rCup.makeInvisible();
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
    }

    public void popLid() {
        Lid rLid = lids.remove(lids.size() - 1);
        rLid.makeInvisible();
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