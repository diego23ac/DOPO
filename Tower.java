import java.util.*;

public class Tower{
    private int height; //altura
    private int maxHeight;
    private int width; //ancho
    private HashMap<Integer,Cup> cups;
    private HashMap<Integer,Lid> lids;
    private ArrayList<Integer> cupsValues;
    private ArrayList<Integer> lidsValues;
    private boolean isVisible;
    private boolean isOk;
    private ArrayList<Rectangle> frame;
    
    public Tower(int maxHeight,int width){
        this.height = 0;
        this.maxHeight = maxHeight;
        this.width = width;
        this.cups = new HashMap<Integer,Cup>();
        this.lids = new HashMap<Integer,Lid>();
        this.cupsValues = new ArrayList<Integer>();
        this.lidsValues = new ArrayList<Integer>();
        this.isVisible = false;
        this.isOk = true;
        this.createTower();
    }
    
    public void pushCup(int i){
        if (!cupsValues.contains(i)) {
            cupsValues.add(i);
            Cup cup = new Cup(i, this.maxHeight,this.width, this.height);
            cups.put(cups.size(), cup);   
            height += (cup.getHeight());
        }
    }
    
    public void popCup() {
        Cup rCup = cups.remove(cups.size() - 1);
        int cupValue = (rCup.getHeight() + 1)/2;
        cupsValues.remove(Integer.valueOf(cupValue));
        rCup.makeInvisible();
        height -= (rCup.getHeight());
    }

    public void removeCup(int i){
        int counter = 0;
        for (Cup cup : cups.values()) {
            if (i*2 - 1 == cup.getHeight()) {
                Cup rCup = cups.remove(counter);
                rCup.makeInvisible();
                break;
            }
            counter += 1;
        }
    }

    public void pushLid(int i){
        if (!lids.containsKey(i)) {
           Lid lid = new Lid(i, this.height,this.width);
           lids.put(lids.size(), lid);
           height++;
        }
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
    
    public void orderTower() {
        ArrayList<Integer> orderedValues = new ArrayList<Integer>();
        for (Cup cup : cups.values()) {
            int cupValue = (cup.getHeight() + 1)/2;
            orderedValues.add(cupValue);
        }
        Collections.sort(orderedValues);
        int cupsNumber = this.cups.size();
        for(int i = 0; i < cupsNumber; i++) {
            this.popCup();
        }

        for(Integer value: orderedValues){
            this.pushCup(value);
        }
    }
    
    public void reverseTower() {
        ArrayList<Integer> reverseOrderedValues = new ArrayList<Integer>();
        for (Cup cup : cups.values()) {
            int cupValue = (cup.getHeight() + 1)/2;
            reverseOrderedValues.add(cupValue);
        }
        Collections.sort(reverseOrderedValues, Collections.reverseOrder());
        int cupsNumber = this.cups.size();
        for(int i = 0; i < cupsNumber; i++) {
            this.popCup();
        }

        for(Integer value: reverseOrderedValues){
            this.pushCup(value);
        }
    }

    public int height(){
        return height;
    }
    
    public void makeVisible(){
        isVisible = true;
        for(Rectangle rectangle: frame){
            rectangle.makeVisible();
        }
    }
    
    private void createTower(){
        frame = new ArrayList<Rectangle>();
        for(int i = 0; i < this.maxHeight; i++){
            Rectangle rectangle = new Rectangle(4,20,"black",0,280 - i*20);
            this.frame.add(rectangle);
        }
        
        Rectangle yAxis = new Rectangle(this.maxHeight*20,2,"black",20,302 - this.maxHeight*20);
        this.frame.add(yAxis);
        
        Rectangle xAxis = new Rectangle(2,this.width*20 + 25,"black",0,300);
        this.frame.add(xAxis);
    }
}