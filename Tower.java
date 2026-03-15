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
    
    public int[] lidedCups() {
        ArrayList<Integer> result = new ArrayList<Integer>();
    
        for (Cup cup : cups.values()) {
            int cupH = cup.getHeight();
            int value = (cupH + 1) / 2;
    
            if (existsLidWithWidth(cupH)) {
                if (!result.contains(value)) {
                    result.add(value);
                }
            }
        }
    
        Collections.sort(result);
    
        int[] out = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            out[i] = result.get(i);
        }
        return out;
    }
    
    public String[][] stackingItems() {
        ArrayList<String[]> items = new ArrayList<String[]>();
    
        for (Cup cup : cups.values()) {
            int value = (cup.getHeight() + 1) / 2;
            items.add(new String[] {"cup", "" + value});
        }
    
        for (Lid lid : lids.values()) {
            int value = (lid.getWidth() + 1) / 2;
            items.add(new String[] {"lid", "" + value});
        }
    
        sortItemsByNumber(items);
    
        String[][] out = new String[items.size()][2];
        for (int i = 0; i < items.size(); i++) {
            out[i][0] = items.get(i)[0];
            out[i][1] = items.get(i)[1];
        }
        return out;
    }
    
    private void sortItemsByNumber(ArrayList<String[]> items) {
        for (int i = 0; i < items.size(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < items.size(); j++) {
                int numJ = Integer.parseInt(items.get(j)[1]);
                int numMin = Integer.parseInt(items.get(minIndex)[1]);
                if (numJ < numMin) {
                    minIndex = j;
                }
            }
            String[] temp = items.get(i);
            items.set(i, items.get(minIndex));
            items.set(minIndex, temp);
        }
    }
    
    private boolean existsLidWithWidth(int width) {
        for (Lid lid : lids.values()) {
            if (lid.getWidth() == width) {
                return true;
            }
        }
        return false;
    }
    public int height(){
        return height;
    }
}