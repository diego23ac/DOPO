import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase de pruebas de unidad propias para el problema de la maratón
 */
public class TowerContestTest {
    /**
     * Valida que para h = n^2 - 2 es impossible
     */
    @Test
    public void ShouldBeImpossibleSolveForNSquaredMinusTwo(){
        TowerContest towerContest = new TowerContest();
        String resultA = towerContest.solve(5, 23);
        String resultB = towerContest.solve(4, 14);
        String resultC = towerContest.solve(6, 34);
        
        assertEquals("impossible", resultA);
        assertEquals("impossible", resultB);
        assertEquals("impossible", resultC);
    }
    
    /**
     * Valida que para h > n^2 es impossible
     */
    @Test
    public void ShouldBeImpossibleSolveForHigherThanNSquared(){
        TowerContest towerContest = new TowerContest();
        String resultA = towerContest.solve(5, 26);
        String resultB = towerContest.solve(5, 999);
        String resultC = towerContest.solve(6, 50);
        
        assertEquals("impossible", resultA);
        assertEquals("impossible", resultB);
        assertEquals("impossible", resultC);
    }
    
    /**
     * Valida que para h < 2*n - 1 es impossible
     */
    @Test
    public void ShouldBeImpossibleSolveForLowerThan2NMinusOne(){
        TowerContest towerContest = new TowerContest();
        String resultA = towerContest.solve(4, 6);
        String resultB = towerContest.solve(4, 3);
        String resultC = towerContest.solve(6, 9);
        
        assertEquals("impossible", resultA);
        assertEquals("impossible", resultB);
        assertEquals("impossible", resultC);
    }
    
    /**
     * Valida que para h = 2*n + 1 la solución retornada son las alturas
     * de la solución trivial ([n, 2, n - 1, n - 2, ..., 1] si n > 3)
     */
    @Test
    public void ShouldSolveReturnTheTrivialSolutionForNHigherThanThree(){
        TowerContest towerContest = new TowerContest();
        String resultA = towerContest.solve(4, 9);
        String resultB = towerContest.solve(5, 11);
        String resultC = towerContest.solve(6, 13);
        
        assertEquals("7 3 5 1", resultA);
        assertEquals("9 3 7 5 1", resultB);
        assertEquals("11 3 9 7 5 1", resultC);
    }
    
    /**
     * Valida que la solución trivial para n <= 3 no funciona
     */
    @Test
    public void ShouldSolveNotReturnTheTrivialSolutionIfNIsLowerThanOrEqualsThree(){
        TowerContest towerContest = new TowerContest();
        String result = towerContest.solve(3, 7);
        
        assertEquals("impossible", result);
    }
    
    /**
     * Valida casos que no son imposibles o que no son la solucion trivial
     */
    @Test
    public void ShouldSolveNotImpossibleCases(){
        TowerContest towerContest = new TowerContest();
        String resultA = towerContest.solve(5, 15);
        String resultB = towerContest.solve(5, 19);
        String resultC = towerContest.solve(4, 16);
        
        assertEquals("1 5 9 7 3", resultA);
        assertEquals("3 7 9 5 1", resultB);
        assertEquals("1 3 5 7", resultC);
    }
}
