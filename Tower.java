import java.util.*;
import javax.swing.JOptionPane;

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
    
    /**
     * Constructor de la clase Tower
     * @param maxHeight La altura maxima de la torre
     * @param width El ancho de la torre
     */
    
    public Tower(int maxHeight,int width){
        //Se inicia con valores predeterminados
        this.height = 0;
        this.maxHeight = maxHeight;
        this.width = width;
        this.cups = new HashMap<Integer,Cup>();
        this.lids = new HashMap<Integer,Lid>();
        this.cupsValues = new ArrayList<Integer>();
        this.lidsValues = new ArrayList<Integer>();
        this.isVisible = false;
        this.isOk = false;
        this.createTower();
    }
    
    /**
     * Inserta la copa identificada con el número i en la torre.
     * 
     * Este método valida si la copa ya está en la torre (para evitar duplicados) y si la operación 
     * de inserción supera la altura máxima permitida. Si se puede insertar, crea una copa, la ingresa 
     * en el HashMap de copas y aumenta la altura de la torre 2*i - 1.
     * 
     * @param i El número de la taza
     */
    
    public void pushCup(int i){
        this.isOk = false;
        if (!cupsValues.contains(i) && this.height + 2*i - 1 <= this.maxHeight) {
            cupsValues.add(i);
            Cup cup = new Cup(i,this.maxHeight,this.width,this.height);
            cups.put(cups.size(), cup);   
            height += (cup.getHeight());
            this.isOk = true;
        } else if (cupsValues.contains(i)){
            JOptionPane.showMessageDialog(null, 
            "La copa ya está en la torre",
            "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Quita de la torre la última copa insertada.
     * 
     */
    
    public void popCup() { //Falta validar en caso de que no hayan copas
        Cup rCup = cups.remove(cups.size() - 1);
        int cupValue = (rCup.getHeight() + 1)/2;
        cupsValues.remove(Integer.valueOf(cupValue));
        rCup.makeInvisible();
        height -= (rCup.getHeight());
    }

    public void removeCup(int i){
        this.isOk = false;
        if (cupsValues.contains(i)) {
            cupsValues.remove(Integer.valueOf(i));
            int counter = 0;
            for (Cup cup : cups.values()) {
                if (i*2 - 1 == cup.getHeight()) {
                    Cup rCup = cups.remove(counter);
                    rCup.makeInvisible();
                    break;
                }
                counter += 1;
            }
            this.isOk = true;
        } else {
            JOptionPane.showMessageDialog(null, 
            "La copa no existe en la torre",
            "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    /**
     * Inserta la tapa identificada con el número i en la torre.
     * 
     * Este método valida si la tapa ya está en la torre (para evitar duplicados) y si la operación 
     * de inserción supera la altura máxima permitida. Si se puede insertar, crea una tapa, la ingresa 
     * en el HashMap de tapas y aumenta la altura de la torre en 1.
     * 
     * @param i El número de la tapa
     */
    
    public void pushLid(int i){
        this.isOk = false;
        if (!lidsValues.contains(i) && this.height + 1 <= this.maxHeight) {
            lidsValues.add(i);
            Lid lid = new Lid(i,this.height,this.width);
            lids.put(lids.size(), lid);
            height++;
            this.isOk = true;
        } else if (lidsValues.contains(i)){
            JOptionPane.showMessageDialog(null, 
            "La tapa ya está en la torre",
            "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void popLid() {
        Lid rLid = lids.remove(lids.size() - 1);
        int lidValue = (rLid.getWidth() + 1)/2;
        lidsValues.remove(Integer.valueOf(lidValue));
        rLid.makeInvisible();
        height--;
    }

    public void removeLid(int i){
        if (cupsValues.contains(i)) {
            cupsValues.remove(Integer.valueOf(i));
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
    }
    
    public void orderTower() {
        ArrayList<Integer> orderedValues = new ArrayList<Integer>();
        for (Cup cup : cups.values()) {
            int cupValue = (cup.getHeight() + 1)/2;
            orderedValues.add(cupValue);
        }
        Collections.sort(orderedValues, Collections.reverseOrder());
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
        Collections.sort(reverseOrderedValues);
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
    
    public void exit(){
        for(Cup cup : this.cups.values()){
            cup.makeInvisible();
        }

        for(Lid lid : this.lids.values()){
            lid.makeInvisible();
        }
        
        for(Rectangle rectangle : this.frame){
            rectangle.makeInvisible();
        }
    }
    
    public boolean ok(){
        return this.isOk;
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