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
        int index = items.size() - 2;
        StackingItem item = items.get(index);
        int value = item.getValue();
        String[] o1 = new String[] {"cup",super.value + ""};
        while (index >= 0 && super.value > value) {
            String[] o2 = new String[] {item.getType(), item.getValue() + ""};
            tower.swap(o1,o2);
            index--;
            if (index != -1) {
                item = items.get(index);
                value = item.getValue();
            }
        }
    }
    
    /**
     * @return "hierarchicalCup" Retorna el tipo del objeto como String, en este caso una copa jerárquica.
     */
    public String getType() { return "hierarchicalCup"; }
}
