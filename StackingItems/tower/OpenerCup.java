package tower;

import java.util.*;

/**
 * OpenerCup es un tipo de copa que elimina todas las tapas que le 
 * impiden el paso.
 */
public class OpenerCup extends Cup {
    /**
     * Constructor de la copa de tipo opener
     * 
     * @param value Valor asociado a la copa
     * @param towerMaxHeight Altura máxima de la torre
     * @param towerWidth Ancho de la torre
     * @param towerHeight Altura de la torre
     * @param isVisible Visibilidad de la torre
     * @param tower Torre a la cual pertenece
     */
    public OpenerCup(int value,int towerMaxHeight,int towerWidth,int towerHeight,boolean isVisible,Tower tower) {
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
