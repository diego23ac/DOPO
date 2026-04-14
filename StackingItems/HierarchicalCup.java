import java.util.*;

/**
 * HierarchicalCup es un tipo de copa que desplaza los elementos de menor tamaño
 * y que si logra llegar al fondo no se deja quitar.
 */
public class HierarchicalCup extends Cup {
    
    /**
     * Constructor de la copa de tipo opener
     */
    public HierarchicalCup(int value,int towerMaxHeight,int towerWidth,int towerHeight,boolean isVisible,Tower tower, ArrayList<StackingItem> items) {
        super(value, towerMaxHeight, towerWidth, towerHeight, isVisible, tower);
    }
    
    public void switchSmallerItems(ArrayList<StackingItem> items) {
        int index = items.size() - 2;
        StackingItem item = items.get(index);
        int value = item.getValue();
        while (index >= 0 && super.value > value) {
            if (item instanceof Cup) {
                tower.removeCup(value);
                tower.pushCup(value);
            } else {
                tower.removeLid(value);
                tower.pushLid(value);
            }
            index--;
            item = items.get(index);
            value = item.getValue();
        }
    }
}
