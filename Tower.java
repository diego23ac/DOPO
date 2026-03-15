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
        if (!cupsValues.contains(i) && this.height + 2*i - 1 <= this.maxHeight) {
            cupsValues.add(i);
            Cup cup = new Cup(i,this.maxHeight,this.width,this.height);
            cups.put(cups.size(), cup);   
            height += (cup.getHeight());
            this.isOk = true;
        } else if (cupsValues.contains(i)){
            JOptionPane.showMessageDialog(null, 
            "La copa ya está en la torre.",
            "Error", JOptionPane.ERROR_MESSAGE);
            this.isOk = false;
        } else {
            JOptionPane.showMessageDialog(null, 
            "Límite de altura máximo de la torre superado.",
            "Error", JOptionPane.ERROR_MESSAGE);
            this.isOk = false;
        }
    }
    
    /**
     * Quita de la torre la última copa insertada.
     * 
     * Este método valida si hay copas en la torre, remueve del HashMap y del ArrayList y disminuye la
     * altura de la torre según la altura de la copa removida (2*i - 1)
     */
    
    public void popCup() {
        if (cups.size() > 0) {
            Cup rCup = cups.remove(cups.size() - 1);
            int cupValue = (rCup.getHeight() + 1)/2;
            cupsValues.remove(Integer.valueOf(cupValue));
            rCup.makeInvisible();
            height -= (rCup.getHeight());
            this.isOk = true;
        } else {
            JOptionPane.showMessageDialog(null, 
            "No es posible hacer popCup porque no hay copas en la torre.",
            "Error", JOptionPane.ERROR_MESSAGE);
            this.isOk = false;
        }
    }

    //Por documentar
    public void removeCup(int i){
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
            this.isOk = false;
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
            this.isOk = false;
        }
    }
    
    
    /**
     * Quita de la torre la última copa insertada.
     * 
     * Este método valida si hay tapas en la torre, remueve del HashMap y del ArrayList y disminuye en 1 la
     * altura de la torre.
     */
    
    public void popLid() {
        if (lids.size() > 0) {
            Lid rLid = lids.remove(lids.size() - 1);
            int lidValue = (rLid.getWidth() + 1)/2;
            lidsValues.remove(Integer.valueOf(lidValue));
            rLid.makeInvisible();
            height--;
        } else {
            JOptionPane.showMessageDialog(null, 
            "No es posible hacer popLid porque no hay tapas en la torre.",
            "Error", JOptionPane.ERROR_MESSAGE);
            this.isOk = false;
        }
    }

    //Por documentar
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
            this.isOk = true;
        } else {
            JOptionPane.showMessageDialog(null, 
            "La tapa no existe en la torre",
            "Error", JOptionPane.ERROR_MESSAGE);
            this.isOk = false;
        }
    }
    
    //Por documentar
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
    
    //Por documentar
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

    //Por documentar
    public int height(){
        return height;
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
    
    public void makeVisible(){
        isVisible = true;
        for(Rectangle rectangle: frame){
            rectangle.makeVisible();
        }
    }
    
    public void makeInvisible(){
        isVisible = false;
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
    
    public void exit(){
        this.makeInvisible();
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
    
}