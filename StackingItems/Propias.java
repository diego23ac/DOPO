

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class Propias.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Propias
{
    
    /**
     * Valida que para n = 4 y h = 11 el resultado debe ser 1 3 7 5
     */
    @Test
    public void accordingCOShouldContestSolveReturn135(){
        Tower tower = new Tower(21,12);
        tower.makeVisible();
        tower.pushCup(5);
        tower.pushCup(4);
        tower.pushCup(3);
        tower.pushLid(2);
        tower.pushLid(3);
        tower.pushLid(5);
    }
    
    @Test
    public void accordingCOShouldContestSolveReturn15(){
        Tower tower = new Tower(21,12);
        tower.makeVisible();
        tower.pushCup(5);
        tower.pushCup(4);
        
        tower.pushLid(2);
        tower.pushLid(3);
        tower.pushLid(5);
    }
    
    @Test
    public void accordingCOShouldContestSolveReturn1325(){
        Tower tower = new Tower(21,12);
        tower.makeVisible();
        tower.pushCup(2);
        tower.pushCup(4);
        tower.pushCup(3);
        
        tower.pushLid(2);
        tower.pushLid(3);
    }
    
    @Test
    public void accordingCOShouldContestSolveRetu32rn1325(){
        Tower tower = new Tower(21,12);
        tower.makeVisible();
        tower.pushCup(3);
        tower.pushCup(2);
        tower.pushLid(1);
        tower.pushCup(1);
        tower.pushCup(5);
        tower.pushLid(2);
        tower.pushLid(3);
        tower.pushCup(4);
        tower.pushLid(4);
    }
}