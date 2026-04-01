import java.util.*;
import javax.swing.JOptionPane;

public class Tower{
    private int height;
    private int maxHeight;
    private int width;
    private ArrayList<Cup> cups;
    private ArrayList<Lid> lids;
    private ArrayList<StackingItem> items;
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
        this.items = new ArrayList<StackingItem>();
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
     * de inserción supera la altura máxima permitida. Si se puede insertar, crea una copa y la apila,
     * por último aumenta la altura de la torre.
     * 
     * @param i El número de la copa
     */
    public void pushCup(int i) {
        if (!cupAlreadyExists(i)) {
            int basePosition = calculateBasePosition(i);
            System.out.println("maxHeight: " + maxHeight + " height: " + height + " alturaEstimada: " + (basePosition + 2*i - 1));
            if (!(maxHeight < basePosition + 2*i - 1)) {
                if (height < basePosition + 2*i - 1) { height = basePosition + 2*i - 1; }
                cupsValues.add(i);
                Cup cup = new Cup(i, maxHeight, width, basePosition, isVisible);
                cups.add(cup);
                items.add(cup);
                isOk = true;
            } else {
                showJOptionPane("Límite de altura máximo de la torre superado.");
                isOk = false;
            }
        }
    }
    
    /**
     * Quita de la torre la última copa insertada.
     * 
     * Este método valida si hay copas en la torre, si las hay entonces remueve la última del ArrayList, 
     * baja la posición de las tapas que estaban arriba y recalcula la altura de la torre. 
     */
    public void popCup() {
        int cupPosition = 0;
        if (cups.size() > 0) {
            Cup rCup = cups.remove(cups.size() - 1);
            rCup.makeInvisible();
            items.remove(rCup);
            reDraw();
            isOk = true;
        } else {
            showJOptionPane("No es posible hacer popCup porque no hay copas en la torre.");
            isOk = false;
        }
    }

    /**
     * Quita de la torre la copa con el número i.
     * 
     * Este método valida si la copa está en la torre, si está entonces reconstruye la torre usando StackingItems
     * sin la copa con el número i
     * 
     * @param i El número de la copa
     */
    public void removeCup(int i) {
        if (!cupsValues.contains(i)) {
            showJOptionPane("La copa no existe en la torre");
            isOk = false;
        } else {
            for (Cup cup : cups) {
                if (cup.getValue() == i) {
                    cup.makeInvisible();
                    items.remove(cup);
                    reDraw();
                    isOk = true;
                }
            }
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
        if (!lidAlreadyExists(i)) {
            int basePosition = calculateBasePosition(i);
            if (!(maxHeight < basePosition + 1)) {
                if (height < basePosition + 1) { height = basePosition + 1; }
                lidsValues.add(i);
                Lid lid = new Lid(i, maxHeight, basePosition, width, isVisible);
                lids.add(lid);
                items.add(lid);
                isOk = true;
            } else {
                showJOptionPane("Límite de altura máximo de la torre superado.");
                isOk = false;
            }
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
            rLid.makeInvisible();
            items.remove(rLid);
            reDraw();
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
        if (!lidsValues.contains(i)) {
            showJOptionPane("La tapa no existe en la torre");
            isOk = false;
        } else {
            for (Lid lid : lids) {
                if (lid.getValue() == i) {
                    lid.makeInvisible();
                    items.remove(lid);
                    reDraw();
                    isOk = true;
                }
            }
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
            
            while (cups.size() > 0) { popCup(); }
        
            while (lids.size() > 0) { popLid(); }
    
            for (int i = 0; i < items.length; i++) {
                if ("cup".equals(items[i][0])) { 
                    pushCup(Integer.parseInt(items[i][1]));
                } else if ("lid".equals(items[i][0])) {
                    pushLid(Integer.parseInt(items[i][1]));
                }
            }
            isOk = true;
        } else if (o1ItemsPosition == -1) {
            showJOptionPane("El objeto 1 no existe en la torre");
            isOk = false;
        } else if (o1ItemsPosition == -1) {
            showJOptionPane("El objeto 2 no existe en la torre");
            isOk = false;
        }
    }

    /**
     * @return height Altura de la torre
     */
    public int height() { return height; }
    
    /**
     * Retorna los números de las copas tapadas por sus tapas ordenados de menor a mayor.
     *
     * Obtiene los elementos de la torre usando stackingItems() y los recorre verificando si existe 
     * una copa seguida inmediatamente por una tapa con el mismo número. Si existe, agrega el número 
     * de la copa a una lista temporal. Ordena los valores de menor a mayor y convierte la lista 
     * a un arreglo de enteros y lo retorna.
     *
     * @return items Arreglo con los números de las copas tapadas.
     */
    public int[] liddedCups() {
        ArrayList<Integer> temporalItems = new ArrayList<Integer>();
        String[][] items = stackingItems();
        for (int i = 0; i < items.length - 1; i++) {
            if (items[i][0].equals("cup")) {
                if (items[i+1][0].equals("lid") && items[i][1].equals(items[i+1][1])) {
                    int value = Integer.parseInt(items[i][1]);
                    if (!temporalItems.contains(value)) { temporalItems.add(value); }
                }
            }
        }
        Collections.sort(temporalItems);
        int[] liddedCups = new int[temporalItems.size()];
        for (int i = 0; i < temporalItems.size(); i++) { liddedCups[i] = temporalItems.get(i); }
        return liddedCups;
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
        for(StackingItem item : items){ item.makeVisible(); }
        
        for(Rectangle rectangle: frame){ rectangle.makeVisible(); }
    }
    
    /**
     * Hace invisible la torre (incluyendo las copas y las tapas que estén en ella)
     * recorriendo los ArrayList (cups, lids, frame) y llamando a makeInvisible().
     */
    public void makeInvisible() {
        isVisible = false;
        for(StackingItem item : items){ item.makeInvisible(); }
        
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
        items.clear();
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
    
    private void reDraw() {
        ArrayList<StackingItem> temporalItems = new ArrayList<StackingItem>(items);
        exit();
        for (StackingItem item : temporalItems) {
            if ("cup".equals(item.getType())) {
                pushCup(item.getValue());
            } else if ("lid".equals(item.getType())) {
                pushLid(item.getValue());
            }
        }
        if (isVisible) { makeVisible(); }
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
    /**
     * realiza posibles swaps para un arreglo y comprobar como es que se puede reducir la altura
     * retorna los dos objetos que pueden reducir la altura y su longitud
     * si no es posible reducirla retorna null
     * 
     * @return String[][] si hay objetos que la reduzcan o null si no los hay
     */
    public String[][] swapToReduce() {
        String[][] original = stackingItems();
        int originalHeight = effectiveHeight(original);
    
        for (int i = 0; i < original.length; i++) {
            for (int j = i + 1; j < original.length; j++) {
    
                String[] o1 = new String[]{original[i][0], original[i][1]};
                String[] o2 = new String[]{original[j][0], original[j][1]};
    
                swap(o1, o2);
    
                int newHeight = effectiveHeight(stackingItems());
    
                restoreTower(original);
    
                if (newHeight < originalHeight) {
                    isOk = true;
                    return new String[][]{o1, o2};
                }
            }
        }
    
        isOk = false;
        return null;
    }

    /**
    * Restaura la torre exactamente al estado representado en la matriz items.
    *
    * Este método elimina todas las copas y tapas actuales de la torre y luego
    * reconstruye la estructura en el mismo orden en que aparecen en items,
    * usando pushCup y pushLid.
    *
    * @param items matriz con el estado que se quiere reconstruir en la torre
    */
    private void restoreTower(String[][] items) {
        while (cups.size() > 0) { popCup(); }
        while (lids.size() > 0) { popLid(); }
        
        for (int i = 0; i < items.length; i++) {
            int value = Integer.parseInt(items[i][1]);
        
            if ("cup".equals(items[i][0])) {
                pushCup(value);
            } else if ("lid".equals(items[i][0])) {
                pushLid(value);
            }
        }
    }
    
    /**
     * Calcula la altura efectiva de una configuración de la torre.
     *
     * La altura efectiva no se calcula usando el atributo height de la clase,
     * sino interpretando la disposición de los elementos en items.
     *
     * @param items matriz con los elementos de la torre ordenados desde la base hasta la cima
     * @return la altura efectiva de la configuración dada
     */
    private int effectiveHeight(String[][] items) {
        int total = 0;
    
        for (int i = 0; i < items.length; i++) {
            String type = items[i][0];
            int value = Integer.parseInt(items[i][1]);
    
            if ("cup".equals(type)) {
                total += 2 * value - 1;
            } else if ("lid".equals(type)) {
                boolean coversCup =
                    i > 0 &&
                    "cup".equals(items[i - 1][0]) &&
                    items[i - 1][1].equals(items[i][1]);
    
                if (!coversCup) {
                    total += 1;
                }
            }
        }
    
        return total;
    }
    
    //Metodos para que el código no quede tan largo :D
    
    /**
     * Valida si la tapa está en la torre
     * 
     * @param int value Número de la tapa
     * @return true si está en la torre, false de lo contrario
     */
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
    
    /**
     * Calcula la altura máxima de una torre con determinado número de copas.
     * (Es para usarlo en el constructor 2)
     * 
     * @param int cups Número de copas
     * @return int maxHeight Altura máxima de la torre
     */
    private static int calculateMaxHeight(int cups) {
        int maxHeight = 0;
        for (int i = 1; i <= cups; i++) { maxHeight += 2*i - 1; }
        return maxHeight;
    }
    
    /**
     * Valida si la copa está en la torre
     * 
     * @param int i Número de la copa
     * @return true si está en la torre, false de lo contrario
     */
    private boolean cupAlreadyExists(int i) {
        if (cupsValues.contains(i)) {
            showJOptionPane("La copa ya está en la torre.");
            isOk = false;
            return true;
        }
        return false;
    }
    
    /**
     * Valida si la tapa está en la torre
     * 
     * @param int i Número de la tapa
     * @return true si está en la torre, false de lo contrario
     */
    private boolean lidAlreadyExists(int i) {
        if (lidsValues.contains(i)) {
            showJOptionPane("La tapa ya está en la torre.");
            isOk = false;
            return true;
        }
        return false;
    }
    
    /**
     * Calcula la posición de la base para ubicar un nuevo item
     * 
     * @param int i Número de la copa
     * @return true si está en la torre, false de lo contrario
     */
    private int calculateBasePosition(int i) {
        if (items.isEmpty()) { return height; }
        
        if (i < items.get(items.size() - 1).getValue()) {
            int lastBase = items.get(items.size() - 1).getBasePosition();
            return lastBase + 1;
        }
        return calculateMiddlePosition(i);
    }
    
    private int calculateMiddlePosition(int i) {
        int maxPosition = 0;
        for (int j = 0; j < items.size(); j++) {
            if (items.get(j).getValue() >= items.get(maxPosition).getValue()) { maxPosition = j; }
        }
        int lastValue = items.get(maxPosition).getValue();
        int lastTop = items.get(maxPosition).getTopPosition();
        int index = maxPosition + 1;
        while (index <= items.size() - 1 && ((lastValue >= items.get(index).getValue() && i < lastValue) || (lastValue < items.get(index).getValue()))) {
            lastValue = items.get(index).getValue();
            lastTop = items.get(index).getTopPosition();
            index++;
        }
        return lastTop;
    }
    
    private int recalculateHeight() {
        if (cupsValues.isEmpty()) { return 0; }
        int newHeight = 2*cupsValues.get(0) - 1;
        for (int i = 1; i < cupsValues.size(); i++) {
            int value = cupsValues.get(i);
            int previous = cupsValues.get(i - 1);
            if (value >= previous) {
                newHeight += 2*value - 1;
            }
        }
        return newHeight;
    }
}