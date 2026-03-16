import java.util.*;
import javax.swing.JOptionPane;

public class Tower{
    private int height;
    private int maxHeight;
    private int width;
    private ArrayList<Cup> cups;
    private ArrayList<Lid> lids;
    private ArrayList<Integer> cupsValues;
    private ArrayList<Integer> lidsValues;
    private boolean isVisible;
    private boolean isOk;
    private ArrayList<Rectangle> frame;
    
    /**
     * Constructor de la clase Tower
     * 
     * @param maxHeight La altura maxima de la torre
     * @param width El ancho de la torre
     */
    
    public Tower(int maxHeight,int width){
        this.height = 0;
        this.maxHeight = maxHeight;
        this.width = width;
        this.cups = new ArrayList<Cup>();
        this.lids = new ArrayList<Lid>();
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
     * de inserción supera la altura máxima permitida. Si se puede insertar, crea una copa y la ingresa 
     * en el ArrayList de copas, valida si la tapa con el mismo númeo ya está en la torre, (para colocar
     * la tapa sobre la copa) y por último aumenta la altura de la torre 2*i - 1.
     * 
     * @param i El número de la copa
     */
    
    public void pushCup(int i){
        if (!cupsValues.contains(i) && height + 2*i - 1 <= maxHeight) {
            cupsValues.add(i);
            Cup cup = new Cup(i, maxHeight, width, height, isVisible);
            cups.add(cup);   
            height += (cup.getHeight());
            isOk = true;
        } else if (cupsValues.contains(i)){
            showJOptionPane("La copa ya está en la torre.");
            isOk = false;
        } else {
            showJOptionPane("Límite de altura máximo de la torre superado.");
            isOk = false;
        }
    }
    
    /**
     * Quita de la torre la última copa insertada.
     * 
     * Este método valida si hay copas en la torre, si las hay entonces remueve la última del ArrayList, 
     * baja la posición de las tapas que estaban arriba y disminuye la altura de la torre según la altura 
     * de la copa removida (2*i - 1)
     */
    
    public void popCup() {
        int cupPosition = 0;
        if (cups.size() > 0) {
            Cup rCup = cups.remove(cups.size() - 1);
            int cupValue = (rCup.getHeight() + 1)/2;
            cupsValues.remove(Integer.valueOf(cupValue));
            cupPosition = rCup.getBasePosition();
            for (Lid lid : lids) {
                if (lid.getBasePosition() < cupPosition) { lid.moveDown(rCup.getHeight()); }
            }
            rCup.makeInvisible();
            height -= rCup.getHeight();
            isOk = true;
        } else {
            showJOptionPane("No es posible hacer popCup porque no hay copas en la torre.");
            isOk = false;
        }
    }

    /**
     * Quita de la torre la copa con el número i.
     * 
     * Este método valida si la copa está en la torre, si está entonces remueve del ArrayList y baja 
     * la posición de las tapas y copas que estaban arriba. También disminuye la altura de la torre según la altura 
     * de la copa removida (2*i - 1)
     * 
     * @param i El número de la copa
     */
    
    public void removeCup(int i) {
        if (cupsValues.contains(i)) {
            cupsValues.remove(Integer.valueOf(i));
            int counter = 0;
            int cupPosition = 0;
            for (Cup cup : cups) {
                if (i*2 - 1 == cup.getHeight()) {
                    Cup rCup = cups.remove(counter);
                    rCup.makeInvisible();
                    cupPosition = rCup.getBasePosition();
                    break;
                }
                counter += 1;
            }

            for (Lid lid : lids) {
                if (lid.getBasePosition() < cupPosition) {
                    lid.moveDown(2*i - 1);
                }
            }
            
            for (Cup cup : cups) {
                if (cup.getBasePosition() < cupPosition) {
                    cup.moveDown(2*i - 1);
                }
            }
            height -= 2*i - 1;
            isOk = true;
        } else {
            showJOptionPane( "La copa no existe en la torre");
            isOk = false;
        }
    }
    
    /**
     * Inserta la tapa identificada con el número i en la torre.
     * 
     * Este método valida si la tapa ya está en la torre (para evitar duplicados) y si la operación 
     * de inserción supera la altura máxima permitida. Si se puede insertar, crea una tapa, la ingresa 
     * en el ArrayList de tapas y aumenta la altura de la torre en 1.
     * 
     * @param i El número de la tapa
     */
    
    public void pushLid(int i) {
        if (!lidsValues.contains(i) && height + 1 <= maxHeight) {
            lidsValues.add(i);
            Lid lid = new Lid(i, height, width, isVisible);
            lids.add(lid);
            height++;
            isOk = true;
        } else if (lidsValues.contains(i)){
            showJOptionPane("La tapa ya está en la torre");
            isOk = false;
        }
    }
    
    /**
     * Quita de la torre la última tapa insertada.
     * 
     * Este método valida si hay tapas en la torre, si las hay entonces remueve la última del ArrayList,
     * baja la posición de las copas que estaban arriba y disminuye la altura de la torre en 1.
     */
    
    public void popLid() {
        int lidPosition = 0;
        if (lids.size() > 0) {
            Lid rLid = lids.remove(lids.size() - 1);
            int lidValue = (rLid.getWidth() + 1)/2;
            lidsValues.remove(Integer.valueOf(lidValue));
            lidPosition = rLid.getBasePosition();
            for (Cup cup : cups) {
                if (cup.getBasePosition() < lidPosition) { cup.moveDown(1); }
            }
            rLid.makeInvisible();
            height--;
            isOk = true;
        } else {
            showJOptionPane("No es posible hacer popLid porque no hay tapas en la torre.");
            isOk = false;
        }
    }

    /**
     * Quita de la torre la tapa con el número i.
     * 
     * Este método valida si la tapa está en la torre, si está entonces remueve del ArrayList y baja 
     * la posición de las tapas y copas que estaban arriba. También disminuye 1 a la altura de la torre.
     * 
     * @param i El número de la copa
     */
    public void removeLid(int i) {
        if (lidsValues.contains(i)) {
            lidsValues.remove(Integer.valueOf(i));
            int counter = 0;
            int lidPosition = 0;
            for (Lid lid : lids) {
                if (i *2-1 == lid.getWidth()) {
                    Lid rLid = lids.remove(counter);
                    rLid.makeInvisible();
                    lidPosition = rLid.getBasePosition();
                    break;
                }
                counter += 1;
            }
            
            for (Lid lid : lids) {
                if (lid.getBasePosition() < lidPosition) {
                    lid.moveDown(1);
                }
            }
            
            for (Cup cup : cups) {
                if (cup.getBasePosition() < lidPosition) {
                    cup.moveDown(1);
                }
            }
            height--;
            isOk = true;
        } else {
            showJOptionPane("La tapa no existe en la torre");
            isOk = false;
        }
    }
    
    /**
     * Ordena la torre de modo que:
     * - El orden es de mayor a menor.
     * - El número menor siempre queda en la cima de la torre.
     * 
     * Crea un ArrayList de los números de las copas y los ordena de mayor a menor.
     * Hace popCup de todas las copas de la torre.
     * Hace pushCup en orden de las copas.
     * Si habia una tapa y una copa con el mismo número, ubica la tapa encima de la copa.
     */
    
    public void orderTower() {
        ArrayList<Integer> orderedValues = new ArrayList<Integer>();
        for (Cup cup : cups) {
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
            if (lidsValues.contains(value)){
                removeLid(value);
                pushLid(value);
            }
        }
    }
    
    /**
     * Ordena la torre de modo que:
     * - El orden es de menor a mayor.
     * - El número menor siempre queda en la base de la torre.
     * 
     * Crea un ArrayList de los números de las copas y los ordena de menor a mayor.
     * Hace popCup de todas las copas de la torre.
     * Hace pushCup en orden de las copas.
     * Si habia una tapa y una copa con el mismo número, ubica la tapa encima de la copa.
     */
    
    public void reverseTower() {
        ArrayList<Integer> reverseOrderedValues = new ArrayList<Integer>();
        for (Cup cup : cups) {
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
            if (lidsValues.contains(value)){
                removeLid(value);
                pushLid(value);
            }
        }
    }

    public int height() { return height; }
    
    public int[] lidedCups() {
        ArrayList<Integer> result = new ArrayList<Integer>();
    
        for (Cup cup : cups) {
            int cupH = cup.getHeight();
            int value = (cupH + 1) / 2;
    
            if (existsLidWithWidth(cupH)) {
                if (!result.contains(value)) {
                    result.add(value);
                }
            }
        }
    
    
        int[] out = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            out[i] = result.get(i);
        }
        return out;
    }
    
    public String[][] stackingItems() {
        ArrayList<Object[]> temporalItems = new ArrayList<>();
        for (Cup cup : cups) {
            int value = (cup.getHeight() + 1) / 2;
            temporalItems.add(new Object[]{"cup", "" + value, cup.getBasePosition()});
        }
    
        for (Lid lid : lids) {
            int value = (lid.getWidth() + 1) / 2;
            temporalItems.add(new Object[]{"lid", "" + value, lid.getBasePosition()});
        }
        
        temporalItems.sort((a,b) -> Integer.compare((int)b[2], (int)a[2]));
        String[][] items = new String[temporalItems.size()][2];
        
        for (int i = 0; i < temporalItems.size(); i++) {
            items[i][0] = (String)temporalItems.get(i)[0];
            items[i][1] = (String)temporalItems.get(i)[1];
        }
        return items;
    }
    
    /**
     * Hace visible la torre (incluyendo las copas y las tapas que estén en ella)
     * recorriendo los ArrayList (cups, lids, frame) y llamando a makeVisible().
     */
    
    public void makeVisible() {
        isVisible = true;
        for(Cup cup : cups){
            cup.makeVisible();
        }

        for(Lid lid : lids){
            lid.makeVisible();
        }
        
        for(Rectangle rectangle: frame){
            rectangle.makeVisible();
        }
    }
    
    /**
     * Hace invisible la torre (incluyendo las copas y las tapas que estén en ella)
     * recorriendo los ArrayList (cups, lids, frame) y llamando a makeInvisible().
     */
    
    public void makeInvisible() {
        isVisible = false;
        for(Cup cup : cups){
            cup.makeInvisible();
        }

        for(Lid lid : lids){
            lid.makeInvisible();
        }
        
        for(Rectangle rectangle : frame){
            rectangle.makeInvisible();
        }
    }
    
    /**
     * Hace invisible a la torre, vacía todos los ArrayList y reinicia la altura de la
     * torre a cero.
     */
    
    public void exit() {
        makeInvisible();
        cups.clear();
        lids.clear();
        cupsValues.clear();
        lidsValues.clear();
        height = 0;
        isOk = true;
    }
    
    /**
     * Indica si se logró realizar la ultima operación.
     * 
     * @return true si se logró realizar la ultima operación, false de lo contrario.
     */
    
    public boolean ok() {
        return isOk;
    }
    
    /**
     * Método para crear la interfaz de la torre
     * 
     * Crea y guarda en frame:
     * - las marcas que representan los centímetros de altura
     * - la base de la torre
     * - la linea vertical que separa las marcas de las copas y tapas
     */
    
    private void createTower() {
        frame = new ArrayList<Rectangle>();
        for(int i = 0; i < maxHeight; i++){
            Rectangle rectangle = new Rectangle(4,20,"black",0,280 - i*20);
            frame.add(rectangle);
        }
        frame.add(new Rectangle(maxHeight*20,2,"black",20,302 - maxHeight*20));
        frame.add(new Rectangle(2,width*20 + 25,"black",0,300));
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
        for (Lid lid : lids) {
            if (lid.getWidth() == width) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Si el simulador está visible, presenta un mensaje especial al usuario en caso de que 
     * no se pueda realizar alguna acción. Usa JOptionPane.
     * 
     * @param message Mensaje del error ocurrido
     */
    private void showJOptionPane(String message) {
        if (isVisible) {
            JOptionPane.showMessageDialog(null, message,
            "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Tower(int cups) {
        this(calculateMaxHeight(cups), calculateMaxHeight(cups));
        for (int i = 1; i <= cups; i++) { pushCup(i);}
    }
    
    private static int calculateMaxHeight(int cups) {
        int maxHeight = 0;
        for (int i = 1; i <= cups; i++) { maxHeight += 2*i - 1; }
        return maxHeight;
    }
    
    public void swap(String[] o1, String[] o2) {
        String[][] items = stackingItems();
        int o1ItemsPosition = -1;
        int o2ItemsPosition = -1;
        
        for (int i = 0; i < items.length; i++) {
            if (o1[0].equals(items[i][0]) && o1[1].equals(items[i][1])) { o1ItemsPosition = i; }
            
            if (o2[0].equals(items[i][0]) && o2[1].equals(items[i][1])) { o2ItemsPosition = i;}
        }
        
        if (o1ItemsPosition != -1 && o2ItemsPosition != -1) {
            String[] temporal = items[o1ItemsPosition];
            items[o1ItemsPosition] = items[o2ItemsPosition];
            items[o2ItemsPosition] = temporal;
        }
        
        while (cups.size() > 0) { popCup(); }
        
        while (lids.size() > 0) { popLid(); }

        for (int i = 0; i < items.length; i++) {
            if ("cup".equals(items[i][0])) { 
                pushCup(Integer.parseInt(items[i][1]));
            } else if ("lid".equals(items[i][0])) {
                pushLid(Integer.parseInt(items[i][1]));
            }
        }        
    }
    
    public void cover() {
        boolean moved = true;
    
        while (moved) {
            moved = false;
            String[][] items = stackingItems();
    
            for (int i = 0; i < items.length; i++) {
                if ("cup".equals(items[i][0])) {
                    String v = items[i][1];
    
                    int lidIndex = -1;
                    for (int j = 0; j < items.length; j++) {
                        if ("lid".equals(items[j][0]) && v.equals(items[j][1])) {
                            lidIndex = j;
                            break;
                        }
                    }
    
                    if (lidIndex != -1 && lidIndex != i + 1) {
                        if (lidIndex > i + 1) {
                            swap(new String[]{"lid", v}, items[lidIndex - 1]);
                        } else if (lidIndex + 1 < items.length) {
                            swap(new String[]{"lid", v}, items[lidIndex + 1]);
                        }
                        moved = true;
                        break;
                    }
                }
            }
        }
    
        isOk = true;
    }
}