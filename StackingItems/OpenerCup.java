import java.util.*;

/**
 * OpenerCup es un tipo de copa que elimina todas las tapas que le 
 * impiden el paso.
 */
public class OpenerCup extends Cup {
    
    /**
     * Constructor de la copa de tipo opener
     */
    public OpenerCup(int value,int towerMaxHeight,int towerWidth,int towerHeight,boolean isVisible,Tower tower, ArrayList<StackingItem> items) {
        super(value, towerMaxHeight, towerWidth, towerHeight, isVisible, tower);
    }
    
    public void deleteLidsInterrupting(ArrayList<StackingItem> items) {
        int index = items.size() - 2;
        StackingItem item = items.get(index);
        while (index >= 0 && item instanceof Lid) {
            tower.removeLid(item.getValue());
            index--;
            if (index != -1) {
                item = items.get(index);
            }
        }
    }
}
