import java.util.*;

/**
 * HierarchicalCup es un tipo de copa que desplaza los elementos de menor tamaño
 * y que si logra llegar al fondo no se deja quitar.
 */
public class HierarchicalCup extends Cup {
    /**
     * Constructor de la copa de tipo hierarchical
     */
    public HierarchicalCup(int value,int towerMaxHeight,int towerWidth,int towerHeight,boolean isVisible,Tower tower,ArrayList<StackingItem> items) {
        super(value, towerMaxHeight, towerWidth, towerHeight, isVisible, tower);
    }
    
    public void switchSmallerItems(ArrayList<StackingItem> items) {
        if (items.size() >= 2) {
            String[] o1 = new String[]{"hierarchical", super.value + ""};
            int index = items.indexOf(this) - 1;
            while (index >= 0 && super.value > items.get(index).getValue()) {
                StackingItem item = items.get(index);
                String[] o2 = new String[]{item.getType(), item.getValue() + ""};
                tower.swap(o1, o2);
                index = items.indexOf(this) - 1;
            }
            
            if (yBasePosition == 0) {
                super.removable = false;
                
            }
        }
    }
    
    /**
     * @return "hierarchical" Retorna el tipo del objeto como String, en este caso una copa jerárquica.
     */
    @Override
    public String getType() { return "hierarchical"; }
}
