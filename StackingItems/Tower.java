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
     * Constructor 1 de la clase Tower
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
     * Constructor 2 de la clase Tower
     * Crea una torre con el número de copas deseadas desde 1 a cups.
     * 
     * @param cups Cantidad de copas
     */
    public Tower(int cups) {
        this(calculateMaxHeight(cups), calculateMaxHeight(cups));
        for (int i = 1; i <= cups; i++) { pushCup(i);}
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
            int cupValue = rCup.getValue();
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
            Lid lid = new Lid(i, maxHeight, height, width, isVisible);
            lids.add(lid);
            height++;
            isOk = true;
        } else if (lidsValues.contains(i)){
            showJOptionPane("La tapa ya está en la torre");
            isOk = false;
        } else {
            showJOptionPane("Límite de altura máximo de la torre superado.");
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
            int lidValue =  rLid.getValue();
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
            int cupValue = cup.getValue();
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
            int cupValue = cup.getValue();
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
    
    /**
     * Intercambia la posición de dos objetos de la torre.
     *
     * Obtiene los elementos actuales de la torre usando stackingItems, 
     * identifica las posiciones de los elementos a intercambiar y los intercambia 
     * en el arreglo. Luego elimina todas las copas y tapas de la torre y vuelve a hacer
     * push con el nuevo orden del arreglo.
     *
     * @param o1 Objeto 1 identificado por su tipo y numero {{tipo:String},{valor:String}}.
     * @param o2 Objeto 1 identificado por su tipo y numero {{tipo:String},{valor:String}}.
     */
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

    /**
     * @return height Altura de la torre
     */
    public int height() { return height; }
    
    /**
     * Retorna los numeros de las copas que tienen una tapa del mismo numero en la torre.
     *
     * Recorre las copas de la torre, obtiene su valor y verifica si existe una tapa con el mismo 
     * número en la torre. Si existe, agrega el número de la copa a una lista temporal.
     * Ordena los valores de menor a mayor y convierte la lista a un arreglo de enteros y lo retorna.
     *
     * @return items Arreglo con los números de las copas que tienen tapa.
     */
    public int[] lidedCups() {
        ArrayList<Integer> temporalItems = new ArrayList<Integer>();
        for (Cup cup : cups) {
            int value = cup.getValue();
            if (isLidInTower(value)) {
                if (!temporalItems.contains(value)) { temporalItems.add(value); }
            }
        }
        Collections.sort(temporalItems);
        int[] items = new int[temporalItems.size()];
        for (int i = 0; i < temporalItems.size(); i++) { items[i] = temporalItems.get(i); }
        return items;
    }
    
    /**
     * Retorna una matriz con los elementos de la torre desde la base hasta la cima.
     *
     * Recorre las copas de la torre y las añade en una lista temporal con lo siguiente 
     * {"cup", valor:String, posición de la base}. Luego recorre las tapas y hace el mismo 
     * procedimiento, cambiando "cup" por "lid". Se ordena la lista según la posición de la 
     * base, de modo que queden ordenados desde la base hasta la cima. Y por último se 
     * construye una matriz de String con el tipo y el valor de cada elemento y se retorna.
     *
     * @return items Matriz donde cada fila contiene:
     * - El tipo de elemento ("cup" o "lid")
     * - El valor del elemento
     */
    public String[][] stackingItems() {
        ArrayList<Object[]> temporalItems = new ArrayList<>();
        for (Cup cup : cups) {
            int value = cup.getValue();
            Object[] item = new Object[]{"cup", "" + value, cup.getBasePosition()};
            temporalItems.add(item);
        }
    
        for (Lid lid : lids) {
            int value = lid.getValue();
            Object[] item = new Object[]{"lid", "" + value, lid.getBasePosition()};
            temporalItems.add(item);
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
        for(Cup cup : cups){ cup.makeVisible(); }

        for(Lid lid : lids){ lid.makeVisible(); }
        
        for(Rectangle rectangle: frame){ rectangle.makeVisible(); }
    }
    
    /**
     * Hace invisible la torre (incluyendo las copas y las tapas que estén en ella)
     * recorriendo los ArrayList (cups, lids, frame) y llamando a makeInvisible().
     */
    public void makeInvisible() {
        isVisible = false;
        for(Cup cup : cups){ cup.makeInvisible(); }

        for(Lid lid : lids){ lid.makeInvisible(); }
        
        for(Rectangle rectangle : frame){ rectangle.makeInvisible(); }
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
    public boolean ok() { return isOk; }
    
    /**
     * Método para crear la interfaz de la torre
     * 
     * Crea y guarda en frame:
     * - las marcas que representan los centímetros de altura
     * - la base de la torre
     * - la linea vertical que separa las marcas de las copas y tapas
     */
    private void createTower() {
        Canvas canvas = Canvas.getCanvas(maxHeight, width);
        frame = new ArrayList<Rectangle>();
        for(int i = 0; i < maxHeight; i++){
            Rectangle rectangle = new Rectangle(3,20,"black",0,maxHeight*20 - i*20-20);
            frame.add(rectangle);
        }
        frame.add(new Rectangle(maxHeight*20,2,"black",20,0));
        frame.add(new Rectangle(2,width*20 + 22,"black",0,maxHeight*20+1));
    }
    
    private boolean isLidInTower(int value) {
        for (Lid lid : lids) {
            if (lid.getValue() == value) { return true; }
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
    
    private static int calculateMaxHeight(int cups) {
        int maxHeight = 0;
        for (int i = 1; i <= cups; i++) { maxHeight += 2*i - 1; }
        return maxHeight;
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

    public String[][] swapToReduce() {
        String[][] original = stackingItems();
    
        String[][] nuevo = new String[original.length][2];
        for (int i = 0; i < original.length; i++) {
            nuevo[i][0] = original[i][0];
            nuevo[i][1] = original[i][1];
        }
    
        int baseHeight = height();
    
        for (int i = 0; i < nuevo.length; i++) {
            for (int j = i + 1; j < nuevo.length; j++) {
    
                String[] o1 = new String[] { nuevo[i][0], nuevo[i][1] };
                String[] o2 = new String[] { nuevo[j][0], nuevo[j][1] };
    
                swap(o1, o2);
                int newHeight = height();
    
                while (cups.size() > 0) { popCup(); }
                while (lids.size() > 0) { popLid(); }
    
                for (int k = 0; k < nuevo.length; k++) {
                    int v = Integer.parseInt(nuevo[k][1]);
                    if ("cup".equals(nuevo[k][0])) {
                        pushCup(v);
                    } else if ("lid".equals(nuevo[k][0])) {
                        pushLid(v);
                    }
                }
    
                if (newHeight < baseHeight) {
                    isOk = true;
                    return new String[][] { o1, o2 };
                }
            }
        }
    
        isOk = false;
        return null;
    }
}