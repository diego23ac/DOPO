import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TowerContestCTest {
    
    /**
     * Valida que para n = 4 y h = 11 el resultado debe ser 1 3 7 5
     */
    @Test
    public void accordingCOShouldContestSolveReturn1375(){
        TowerContest towerContest = new TowerContest();
        String result = towerContest.solve(4,11);
        assertEquals("1 3 7 5", result);
    }
    
    /**
     * Valida que para n = 5 y h = 11 el resultado debe ser 9 3 7 5 1
     */
    @Test
    public void accordingCOShouldTrivialSolutionReturn93751(){
        TowerContest towerContest = new TowerContest();
        String result = towerContest.solve(5, 11);
        assertEquals("9 3 7 5 1", result);
    }
}