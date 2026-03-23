import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class TowerCC2Test.
 */
public class TowerCC2Test {
    /**
     * Default constructor for test class TowerCC2Test
     */
    public TowerCC2Test()
    {
    }

    /**
     * Una torre con 2 tazas debe tener altura 4 (1+3).
     */
    @Test
    public void accordingMVShouldHaveHeightFourWithTwoCups() {
        Tower t = new Tower(2);
        assertEquals(4, t.height());
        assertTrue(t.ok());
    }
 
    /**
     * swapToReduce debe retornar exactamente 2 objetos (los dos a intercambiar).
     */
    /*
    @Test
    public void accordingMVShouldReturnTwoObjectsInSwapToReduce() {
        Tower t = new Tower(5, 30);
        t.pushCup(1);
        t.pushCup(3);
        t.pushCup(2);
 
        String[][] swap = t.swapToReduce();
        assertTrue(t.ok());
 
        if (swap.length > 0) {
            assertEquals(2, swap.length);
        }
    }*/
    
    
}