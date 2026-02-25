import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Representa una torre apilable de tazas (cups) y tapas (lids). Permite operaciones de apilamiento,
 * reorganización, consulta y visualización con figuras del paquete shapes.
 */
public class Tower {
    private static final int CANVAS_W = 300;
    private static final int CANVAS_H = 300;
    private static final int MARGIN_TOP = 10;
    private static final int MARGIN_BOTTOM = 10;
    private static final int MARGIN_SIDE = 20;
    private int towerHeight;
    private int towerWidth;
    private ArrayList<Cup> towerCups;
    private ArrayList<Lid> towerLids;
    private ArrayList<Integer> stackNum;
    private ArrayList<Boolean> stackIsLid;
    private ArrayList<Rectangle> cmMarks;
    private boolean isVisible;
    private boolean itsOk;
    private static final String[] COLORS = {"red","blue","yellow","green","magenta","black"};
    /**
     * Crea una torre con ancho y altura máxima definidos.
     * @param width ancho lógico de la torre
     * @param maxHeight altura máxima lógica de la torre (cm)
     */
    public Tower(int width, int maxHeight) {
        this.towerWidth = width;
        this.towerHeight = maxHeight;
        this.towerCups = new ArrayList<Cup>();
        this.towerLids = new ArrayList<Lid>();

        this.stackNum = new ArrayList<Integer>();
        this.stackIsLid = new ArrayList<Boolean>();
        this.cmMarks = new ArrayList<Rectangle>();

        this.isVisible = false;
        this.itsOk = true;
    }

    /**
     * Apila una taza con número i en la cima de la torre (altura lógica = i).
     * @param i número de la taza
     */
    public void pushCup(int i) {
        if (findCup(i) != null) { fail("Ya existe la taza #" + i); return; }

        int cupHeight = i;
        if (currentHeightNoSideEffects() + cupHeight > towerHeight) {
            fail("No cabe la taza #" + i + " en la torre");
            return;
        }

        String color = pickCupColor();
        Cup c = new Cup(i, cupHeight, color);
        towerCups.add(c);

        stackNum.add(i);
        stackIsLid.add(false);

        relinkPairs();
        changed();
        success();
    }

    /**
     * Retira una taza desde la cima de la torre. Si la taza está tapada (lid i encima), se retiran ambas.
     */
    public void popCup() {
        int top = topIndex();
        if (top < 0) { fail("La torre está vacía"); return; }

        if (!stackIsLid.get(top).booleanValue()) {
            removeAt(top);
            relinkPairs();
            changed();
            success();
            return;
        }

        if (top - 1 >= 0
                && !stackIsLid.get(top - 1).booleanValue()
                && stackNum.get(top - 1).intValue() == stackNum.get(top).intValue()) {
            removeAt(top);
            removeAt(top - 1);
            relinkPairs();
            changed();
            success();
            return;
        }

        fail("No hay una taza en la cima para retirar");
    }

    /**
     * Elimina una taza con número i desde cualquier posición. Si está tapada, elimina también su tapa.
     * @param i número de la taza
     */
    public void removeCup(int i) {
        if (findCup(i) == null) { fail("No existe la taza #" + i); return; }

        int idx = indexOfCupInStack(i);
        if (idx < 0) { fail("La taza #" + i + " no está apilada"); return; }

        if (idx + 1 < stackNum.size()
                && stackIsLid.get(idx + 1).booleanValue()
                && stackNum.get(idx + 1).intValue() == i) {
            removeAt(idx + 1);
            removeAt(idx);
        } else {
            removeAt(idx);
        }

        relinkPairs();
        changed();
        success();
    }

    /**
     * Apila una tapa con número i en la cima. Su color coincide con la taza i si existe.
     * @param i número de la tapa
     */
    public void pushLid(int i) {
        if (findLid(i) != null) { fail("Ya existe la tapa #" + i); return; }

        if (currentHeightNoSideEffects() + Lid.HEIGHT > towerHeight) {
            fail("No cabe la tapa #" + i + " en la torre");
            return;
        }

        Cup c = findCup(i);
        String color = (c != null) ? c.getColor() : COLORS[Math.abs(i) % COLORS.length];

        Lid l = new Lid(i, towerWidth, color);
        towerLids.add(l);

        stackNum.add(i);
        stackIsLid.add(true);

        relinkPairs();
        changed();
        success();
    }

    /**
     * Retira una tapa desde la cima de la torre.
     */
    public void popLid() {
        int top = topIndex();
        if (top < 0) { fail("La torre está vacía"); return; }

        if (stackIsLid.get(top).booleanValue()) {
            removeAt(top);
            relinkPairs();
            changed();
            success();
            return;
        }

        fail("No hay una tapa en la cima para retirar");
    }

    /**
     * Elimina una tapa con número i desde cualquier posición.
     * @param i número de la tapa
     */
    public void removeLid(int i) {
        if (findLid(i) == null) { fail("No existe la tapa #" + i); return; }

        int idx = indexOfLidInStack(i);
        if (idx < 0) { fail("La tapa #" + i + " no está apilada"); return; }

        removeAt(idx);
        relinkPairs();
        changed();
        success();
    }

    /**
     * Reordena la torre de mayor a menor (por número). Si existen cup y lid del mismo número, la lid queda encima.
     */
    public void orderTower() {
        ArrayList<Integer> nums = allNumbersPresentSorted();
        ArrayList<Integer> newNum = new ArrayList<Integer>();
        ArrayList<Boolean> newIsLid = new ArrayList<Boolean>();

        int h = 0;

        for (int idx = nums.size() - 1; idx >= 0; idx--) {
            int n = nums.get(idx).intValue();
            Cup c = findCup(n);
            Lid l = findLid(n);

            if (c != null) {
                int ch = c.getHeight();

                if (h + ch <= towerHeight) {
                    newNum.add(n);
                    newIsLid.add(false);
                    h += ch;

                    if (l != null && h + Lid.HEIGHT <= towerHeight) {
                        newNum.add(n);
                        newIsLid.add(true);
                        h += Lid.HEIGHT;
                    }
                } else {
                    if (l != null && h + Lid.HEIGHT <= towerHeight) {
                        newNum.add(n);
                        newIsLid.add(true);
                        h += Lid.HEIGHT;
                    }
                }
            } else {
                if (l != null && h + Lid.HEIGHT <= towerHeight) {
                    newNum.add(n);
                    newIsLid.add(true);
                    h += Lid.HEIGHT;
                }
            }
        }

        stackNum = newNum;
        stackIsLid = newIsLid;

        syncCollectionsToStack();
        relinkPairs();
        changed();
        success();
    }

    /**
     * Invierte el orden de apilamiento. Si una tapa está inmediatamente sobre su taza, ambas se mueven juntas.
     */
    public void reverseTower() {
        ArrayList<Integer> newNum = new ArrayList<Integer>();
        ArrayList<Boolean> newIsLid = new ArrayList<Boolean>();

        int h = 0;

        for (int idx = stackNum.size() - 1; idx >= 0; ) {
            boolean topIsLid = stackIsLid.get(idx).booleanValue();
            int numTop = stackNum.get(idx).intValue();

            if (topIsLid
                    && idx - 1 >= 0
                    && !stackIsLid.get(idx - 1).booleanValue()
                    && stackNum.get(idx - 1).intValue() == numTop) {

                Cup c = findCup(numTop);
                int cupH = (c != null) ? c.getHeight() : 0;
                int blockH = cupH + Lid.HEIGHT;

                if (cupH > 0 && h + blockH <= towerHeight) {
                    newNum.add(numTop);
                    newIsLid.add(false);
                    newNum.add(numTop);
                    newIsLid.add(true);
                    h += blockH;
                }
                idx -= 2;
            } else {
                int itemH;

                if (topIsLid) {
                    itemH = Lid.HEIGHT;
                } else {
                    Cup c = findCup(numTop);
                    itemH = (c != null) ? c.getHeight() : 0;
                }

                if (itemH > 0 && h + itemH <= towerHeight) {
                    newNum.add(numTop);
                    newIsLid.add(topIsLid);
                    h += itemH;
                }

                idx -= 1;
            }
        }

        stackNum = newNum;
        stackIsLid = newIsLid;

        syncCollectionsToStack();
        relinkPairs();
        changed();
        success();
    }

    /**
     * Retorna la altura actual ocupada por los elementos apilados.
     * @return altura total actual (cm)
     */
    public int height() {
        itsOk = true;
        return currentHeightNoSideEffects();
    }

    /**
     * Retorna los números de las tazas que están tapadas, ordenados de menor a mayor.
     * @return arreglo con los números de tazas tapadas
     */
    public int[] lidedCups() {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (Cup c : towerCups) if (c.isLided()) nums.add(c.getNumber());
        Collections.sort(nums);

        int[] out = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) out[i] = nums.get(i).intValue();

        itsOk = true;
        return out;
    }

    /**
     * Retorna los elementos apilados desde la cima hacia la base (tipo y número).
     * @return matriz String[n][2] donde [i][0]=tipo ("cup"/"lid") y [i][1]=número
     */
    public String[][] stackingItems() {
        int n = stackNum.size();
        String[][] out = new String[n][2];

        int r = 0;
        for (int k = n - 1; k >= 0; k--) {
            out[r][0] = stackIsLid.get(k).booleanValue() ? "lid" : "cup";
            out[r][1] = "" + stackNum.get(k).intValue();
            r++;
        }

        itsOk = true;
        return out;
    }

    /**
     * Hace visible la torre en el canvas si cabe en la ventana.
     */
    public void makeVisible() {
        if (!canFitInCanvas()) {
            fail("La torre no cabe en la ventana");
            isVisible = false;
            return;
        }

        isVisible = true;
        render();
        itsOk = true;
    }

    /**
     * Oculta la torre, borrando las figuras que estén dibujadas.
     */
    public void makeInvisible() {
        if (isVisible) {
            clearGraphics();
            Canvas.getCanvas().setVisible(false);
        }
        isVisible = false;
        itsOk = true;
    }

    /**
     * Cierra la visualización de la torre (equivalente a hacerla invisible).
     */
    public void exit() {
        makeInvisible();
        itsOk = true;
    }

    /**
     * Indica si la última operación fue exitosa.
     * @return true si la última operación fue válida, false en caso de fallo
     */
    public boolean ok() {
        return itsOk;
    }

    /**
     * Redibuja la torre si actualmente está visible.
     */
    private void changed() {
        if (isVisible) render();
    }

    /**
     * Redibuja toda la torre en el canvas, incluyendo marcas de cm y elementos apilados.
     */
    private void render() {
        if (!isVisible) return;

        Canvas.getCanvas();

        clearGraphics();
        drawCmMarks();

        int p = pixelsPerCm();
        int widthPx = towerWidth * p;

        int xLeft = (CANVAS_W - widthPx) / 2;
        int yBottom = CANVAS_H - MARGIN_BOTTOM;

        int thickness = Math.max(2, p / 3);

        for (int k = 0; k < stackNum.size(); k++) {
            int num = stackNum.get(k).intValue();
            boolean isLid = stackIsLid.get(k).booleanValue();

            if (isLid) {
                Lid l = findLid(num);
                if (l != null) {
                    int lidHpx = Lid.HEIGHT * p;
                    int yTop = yBottom - lidHpx;
                    l.drawAt(xLeft, yTop, widthPx, lidHpx);
                    yBottom -= lidHpx;
                }
            } else {
                Cup c = findCup(num);
                if (c != null) {
                    int cupHpx = c.getHeight() * p;
                    c.drawAt(xLeft, yBottom, widthPx, cupHpx, thickness);
                    yBottom -= cupHpx;
                }
            }
        }
    }

    /**
     * Borra todas las figuras del canvas asociadas a cups, lids y marcas de cm.
     */
    private void clearGraphics() {
        for (Cup c : towerCups) c.eraseGraphics();
        for (Lid l : towerLids) l.eraseGraphics();

        for (Rectangle r : cmMarks) r.makeInvisible();
        cmMarks.clear();
    }

    /**
     * Dibuja marcas de cm como pequeñas líneas al lado izquierdo de la torre.
     */
    private void drawCmMarks() {
        int p = pixelsPerCm();
        int widthPx = towerWidth * p;

        int xLeft = (CANVAS_W - widthPx) / 2;
        int yBottom = CANVAS_H - MARGIN_BOTTOM;

        int markX = xLeft - 12;
        int markW = 10;
        int markH = 2;

        for (int cm = 1; cm <= towerHeight; cm++) {
            int y = yBottom - (cm * p);
            Rectangle m = new Rectangle();
            m.changeColor("black");
            m.changeSize(markH, markW);
            m.moveHorizontal(markX - 70);
            m.moveVertical(y - 15);
            m.makeVisible();
            cmMarks.add(m);
        }
    }

    /**
     * Verifica si la torre cabe en el canvas con la escala calculada.
     * @return true si la torre cabe, false en caso contrario
     */
    private boolean canFitInCanvas() {
        int p = pixelsPerCm();
        if (p < 1) return false;

        int widthPx = towerWidth * p;
        int heightPx = towerHeight * p;

        boolean okW = widthPx <= (CANVAS_W - 2 * MARGIN_SIDE);
        boolean okH = heightPx <= (CANVAS_H - MARGIN_TOP - MARGIN_BOTTOM);

        return okW && okH;
    }

    /**
     * Calcula cuántos pixeles representan 1 cm lógico, intentando maximizar el tamaño sin salirse del canvas.
     * @return pixeles por cm (si retorna 0, no cabe)
     */
    private int pixelsPerCm() {
        int maxP = 10;

        int pH = (CANVAS_H - MARGIN_TOP - MARGIN_BOTTOM) / Math.max(1, towerHeight);
        int pW = (CANVAS_W - 2 * MARGIN_SIDE) / Math.max(1, towerWidth);

        int p = Math.min(maxP, Math.min(pH, pW));
        return p;
    }

    /**
     * Retorna el índice del elemento superior de la torre en el stack.
     * @return índice del tope o -1 si está vacío
     */
    private int topIndex() {
        return stackNum.size() - 1;
    }

    /**
     * Busca una taza por número en la colección de tazas.
     * @param number número de la taza
     * @return objeto Cup si existe, o null si no existe
     */
    private Cup findCup(int number) {
        for (Cup c : towerCups) if (c.getNumber() == number) return c;
        return null;
    }

    /**
     * Busca una tapa por número en la colección de tapas.
     * @param number número de la tapa
     * @return objeto Lid si existe, o null si no existe
     */
    private Lid findLid(int number) {
        for (Lid l : towerLids) if (l.getNumber() == number) return l;
        return null;
    }

    /**
     * Retorna el índice de una taza dentro del orden apilado.
     * @param number número de la taza
     * @return índice dentro del stack o -1 si no está apilada
     */
    private int indexOfCupInStack(int number) {
        for (int k = 0; k < stackNum.size(); k++) {
            if (!stackIsLid.get(k).booleanValue() && stackNum.get(k).intValue() == number) return k;
        }
        return -1;
    }

    /**
     * Retorna el índice de una tapa dentro del orden apilado.
     * @param number número de la tapa
     * @return índice dentro del stack o -1 si no está apilada
     */
    private int indexOfLidInStack(int number) {
        for (int k = 0; k < stackNum.size(); k++) {
            if (stackIsLid.get(k).booleanValue() && stackNum.get(k).intValue() == number) return k;
        }
        return -1;
    }

    /**
     * Elimina un elemento del stack en una posición específica y lo remueve también de la colección correspondiente.
     * @param idx índice del elemento en el stack
     */
    private void removeAt(int idx) {
        int number = stackNum.remove(idx).intValue();
        boolean isLid = stackIsLid.remove(idx).booleanValue();

        if (isLid) {
            Lid l = findLid(number);
            if (l != null) towerLids.remove(l);
        } else {
            Cup c = findCup(number);
            if (c != null) towerCups.remove(c);
        }
    }

    /**
     * Reconstruye los enlaces cup<->lid, asociándolos solo si están adyacentes (cup abajo y lid arriba) con mismo número.
     */
    private void relinkPairs() {
        for (Cup c : towerCups) c.setLid(null);
        for (Lid l : towerLids) l.setCup(null);

        for (int k = 0; k < stackNum.size() - 1; k++) {
            if (!stackIsLid.get(k).booleanValue() && stackIsLid.get(k + 1).booleanValue()) {
                int cupNum = stackNum.get(k).intValue();
                int lidNum = stackNum.get(k + 1).intValue();
                if (cupNum == lidNum) {
                    Cup c = findCup(cupNum);
                    Lid l = findLid(lidNum);
                    if (c != null && l != null) {
                        c.setLid(l);
                        l.setCup(c);
                    }
                }
            }
        }
    }

    /**
     * Retorna los números que existen en la torre (cups y lids) sin repetir, ordenados ascendentemente.
     * @return lista ordenada de números presentes
     */
    private ArrayList<Integer> allNumbersPresentSorted() {
        HashSet<Integer> set = new HashSet<Integer>();
        for (Cup c : towerCups) set.add(c.getNumber());
        for (Lid l : towerLids) set.add(l.getNumber());

        ArrayList<Integer> nums = new ArrayList<Integer>(set);
        Collections.sort(nums);
        return nums;
    }

    /**
     * Sincroniza las colecciones de cups y lids para que solo queden los elementos presentes en el stack.
     */
    private void syncCollectionsToStack() {
        HashSet<Integer> keepCups = new HashSet<Integer>();
        HashSet<Integer> keepLids = new HashSet<Integer>();

        for (int k = 0; k < stackNum.size(); k++) {
            int n = stackNum.get(k).intValue();
            if (stackIsLid.get(k).booleanValue()) keepLids.add(n);
            else keepCups.add(n);
        }

        for (int i = towerCups.size() - 1; i >= 0; i--) {
            if (!keepCups.contains(towerCups.get(i).getNumber())) towerCups.remove(i);
        }
        for (int i = towerLids.size() - 1; i >= 0; i--) {
            if (!keepLids.contains(towerLids.get(i).getNumber())) towerLids.remove(i);
        }
    }

    /**
     * Calcula la altura total actual de la torre sin modificar banderas ni estado de validación.
     * @return altura total actual (cm)
     */
    private int currentHeightNoSideEffects() {
        int h = 0;
        for (int k = 0; k < stackNum.size(); k++) {
            int n = stackNum.get(k).intValue();
            if (stackIsLid.get(k).booleanValue()) h += Lid.HEIGHT;
            else {
                Cup c = findCup(n);
                if (c != null) h += c.getHeight();
            }
        }
        return h;
    }

    /**
     * Selecciona un color para una nueva taza, intentando no repetir colores ya usados.
     * @return color seleccionado
     */
    private String pickCupColor() {
        for (int i = 0; i < COLORS.length; i++) {
            String candidate = COLORS[i];
            boolean used = false;
            for (Cup c : towerCups) {
                if (candidate.equals(c.getColor())) { used = true; break; }
            }
            if (!used) return candidate;
        }
        return COLORS[0];
    }

    /**
     * Marca la última operación como exitosa.
     */
    private void success() {
        itsOk = true;
    }

    /**
     * Marca la última operación como fallida y, si la torre está visible, imprime un mensaje.
     * @param msg mensaje de error
     */
    private void fail(String msg) {
        itsOk = false;
        if (isVisible) System.out.println("[Tower] " + msg);
    }
}
