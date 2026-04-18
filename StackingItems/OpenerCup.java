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
        if (items.size() >= 2) {
            int index = items.indexOf(this) - 1;
            while (index >= 0 && items.get(index) instanceof Lid) {
                StackingItem item = items.get(index);
                tower.removeLid(item.getValue());
                index = items.indexOf(this) - 1;
            }
        }
    }
    
    /**
     * @return "openerCup" Retorna el tipo del objeto como String, en este caso una copa opener.
     */
    @Override
    public String getType() { return "opener"; }
}
