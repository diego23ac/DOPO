package tower;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase de pruebas del Ciclo 2
 */
public class TowerCC2Test {
    
    /**
     * Verifica que el método swap() reordene de manera correcta las
     * tapas y las copas de la torre al hacer varios intercambios (tres).
     */
    @Test
    public void accordingCOShouldReorderItemsUsingThreeSwaps(){
        Tower tower = new Tower(25, 15);
        int[] cups = {1, 2, 3, 4};
        int[] lids = {4, 1, 3, 2};
        for (int cup : cups) { tower.pushCup(cup); }
        
        for (int lid : lids) { tower.pushLid(lid); }
        
        tower.swap(new String[]{"cup","4"}, new String[]{"lid","1"});
        tower.swap(new String[]{"cup","2"}, new String[]{"lid","3"});
        tower.swap(new String[]{"lid","4"}, new String[]{"cup","1"});
        String[][] items = tower.stackingItems();
        
        assertTrue(sameItem(items[0], "lid", "4"));
        assertTrue(sameItem(items[1], "lid", "3"));
        assertTrue(sameItem(items[2], "cup", "3"));
        assertTrue(sameItem(items[3], "lid", "1"));
        assertTrue(sameItem(items[4], "cup", "1"));
        assertTrue(sameItem(items[5], "cup", "4"));
        assertTrue(sameItem(items[6], "cup", "2"));
    }
    
    /**
     * Verifica que el método cover() coloque las tapas encima de las copas
     * que corresponden a su mismo número cuando los dos objetos existen 
     * en la torre.
     */
    @Test
    public void accordingCOShouldCoverCupsWhenMatchingLidsExist(){
        Tower tower = new Tower(31, 20);
        int[] cups = {5, 2, 4, 1, 3};
        int[] lids = {5, 3, 4};
        for (int cup : cups) { tower.pushCup(cup); }
        
        for (int lid : lids) { tower.pushLid(lid); }
        tower.cover();
        int[] liddedCups = tower.liddedCups();
        assertEquals(3, liddedCups.length);
        assertEquals(3, liddedCups[0]);
        assertEquals(4, liddedCups[1]);
        assertEquals(5, liddedCups[2]);
    }
    
    /**
     * Verifica que una torre creada con 2 copas tenga la altura correcta.
     *
     * Utiliza el constructor Tower(int cups) para crear las copas desde 1
     * hasta el número indicado. Para 2 copas la altura total debe ser 4.
     * 1 + 3 = 4
     */
    @Test
    public void accordingMVShouldHaveHeightFourWithTwoCups() {
        Tower tower = new Tower(2);
        assertEquals(4, tower.height());
        assertTrue(tower.ok());
    }
    
    /**
     * Verifica que el método cover() no modifique la torre cuando
     * no existen pares copa-tapa con el mismo número.
     */
    @Test
    public void accordingORShouldCoverNotChangeTowerWithoutPairs() {
        Tower tower = new Tower(10, 10);
        tower.pushCup(4);
        tower.pushLid(2);
        int height = tower.height();
 
        tower.cover();
 
        assertTrue(tower.ok());
        assertEquals(height, tower.height());
        assertEquals(0, tower.liddedCups().length);
    }
    
    /**
     * Verifica que una torre creada con el constructor Tower(int cups)
     * no tenga tapas inicialmente.
     */
    @Test
    public void accordingSShouldStartWithNoLids() {
        Tower tower = new Tower(5);
        int[] liddedCups = tower.liddedCups();
        assertNotNull(liddedCups);
        assertEquals(0, liddedCups.length, "No debería haber tapas al crear la torre");
    }
    
    /**
     * Verifica que el método swap() intercambie correctamente la posición de 
     * una copa y una tapa dentro de la torre.
     */
    @Test
    public void accordingSShouldSwapCupAndLid() {
        Tower tower = new Tower(2);
        tower.pushLid(1);
        tower.swap(new String[]{"cup", "2"}, new String[]{"lid", "1"});
        
        String[][] items = tower.stackingItems();
        assertTrue(sameItem(items[1], "lid", "1"), 
                    "Después del swap, lid 1 debería estar primero");
    }
    
    /** 
     * Verifica que swap falla cuando una de las tazas no existe. 
    */
    @Test
    public void accordingBGShouldNotSwapNonExistentCup() {
        Tower tower = new Tower(2);
        tower.swap(new String[]{"cup","9"}, new String[]{"cup","1"});
        assertFalse(tower.ok());
    }
    
    private boolean sameItem(String[] item, String type, String value) {
        return item[0].equals(type) && item[1].equals(value);
    }
}