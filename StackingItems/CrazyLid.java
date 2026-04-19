import java.util.*;

/**
 * CrazyLid es un tipo de tapa que en lugar de tapar a su copa como
 * haría una tapa normal, se ubica en su base
 */
public class CrazyLid extends Lid {
    /**
     * Constructor de la tapa de tipo crazy
     */
    public CrazyLid(int value, int towerMaxHeight, int towerHeight, int towerWidth, boolean isVisible, Tower tower) {
        super(value, towerMaxHeight, towerHeight, towerWidth, isVisible, tower);
    }
    
    /**
     * Si tapa a la copa compañera, hace un swap entre esta tapa y 
     * la copa para que la tapa quede debajo.
     *
     * @param items Lista actual de items de la torre (de Tower.items).
     */
    public void crazy(ArrayList<StackingItem> items) {
        if (items.size() >= 2) {
            StackingItem item = items.get(items.size() - 2);
            if (item instanceof Cup && item.getValue() == super.value) {
                String[] lid = new String[]{"crazy", super.value + ""};
                String[] cup = new String[]{item.getType(), item.getValue() + ""};
                tower.swap(lid, cup);
            }
        }
    }
    
    /**
     * @return "crazy" Retorna el tipo del objeto como String, en este caso una tapa "loca".
     */
    @Override
    public String getType() {
        return "crazy";
    }
}
