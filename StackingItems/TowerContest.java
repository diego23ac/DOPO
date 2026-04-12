import java.util.*;
import javax.swing.JOptionPane;

/**
 * Clase que desarrolla el problema de la maratón.
 */
public class TowerContest {
    /**
     * Resuelve el problema de la maratón.
     * 
     * @param n Numero de copas
     * @param h Altura de la torre
     * @return result Si es posible construir una torre de altura h
     * con un número n de copas, retorna las alturas de las copas en el orden
     * en que deberían ser insertadas. De lo contrario, retorna 'impossible'
     */
    public String solve(int n, int h){
        StringBuilder result = new StringBuilder();
        if( h == n*n - 2  || h > n*n || h < 2*n - 1) { 
            result.append("impossible"); 
        } else if (h == 2*n + 1) {
            ArrayList<Integer> solution = this.trivialSolution(n, h);
            for (int i = 0; i < solution.size(); i++) {
                result.append(2*solution.get(i) - 1);
                if (i < solution.size() - 1) {
                    result.append(" ");
                }
            }
        } else {
            ArrayList<Integer> cups = new ArrayList<Integer>();
            for (int i = 1; i <= n; i++) {
                cups.add(i);
            }
            ArrayList<Integer> solution = this.solve(cups,h);
            if(solution == null) { return"impossible"; }
            
            for (int i = 0; i < solution.size(); i++) {
                result.append(2*solution.get(i) - 1);
                if (i < solution.size() - 1) {
                    result.append(" ");
                }
            }
        }
        return result.toString();
    }
    
    /**
     * Dibuja la imagen de la solución, si existe y es posible graficarla.
     * 
     * @param n Numero de copas
     * @param h Altura de la torre
     */
    public void simulate(int n, int h) {
        String result = solve(n, h);
        if(!result.equals("impossible")) {
            Tower tower = new Tower(h, 2*n);
            tower.makeVisible();
            String[] parts = result.split(" ");
            int[] cups = new int[parts.length];
            for(int i = 0; i < parts.length; i++) {
                cups[i] = Integer.parseInt(parts[i])/2 + 1;
            }
            
            for (Integer cup : cups) {
                tower.pushCup(cup);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No es posible simular la solución :(",
            "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Resuelve los casos triviales mostrados en el video del ProblemJ, que es cuando
     * la altura h es 2*n + 1. Para ello la secuencia es [n, 2, n - 1, n - 2, ..., 1],
     * teniendo en cuenta que el dos no se repite y que esto solo funciona para n > 3
     * 
     * @param n Numero de copas
     * @param h Altura de la torre
     */
    private ArrayList<Integer> trivialSolution(int n, int h){
        ArrayList<Integer> solution = new ArrayList<Integer>();
        solution.add(n);
        solution.add(2);
        for(int i = n - 1; i >= 3; i--){
            solution.add(i);
        }
        solution.add(1);
        return solution;
    }
    
    /**
     * Resuelve los casos no triviales que es cuando la altura h no es 2*n + 1.
     * 
     * @param int n Numero de copas
     * @param int h Altura de la torre
     */
    private ArrayList<Integer> solve(ArrayList<Integer> cups, int h){
        int height = h;
        int index = cups.size() - 1;
        ArrayList<Integer> solution = new ArrayList<Integer>();
        while (height > 0 && index >= 0) {
            int cup = cups.get(index);
            int newHeight = height - (2*cup - 1);
            if (newHeight >= 0) {
                height = newHeight;
                solution.add(0, cup);
            }
            index--;
        }
        ArrayList<Integer> remainingCups = new ArrayList<Integer>();
        for(int i = 0; i < cups.size(); i++){
            Integer value = cups.get(i);
            if(!solution.contains(value)){ remainingCups.add(value); }
        }
        Collections.sort(remainingCups, Collections.reverseOrder());
        solution.addAll(remainingCups);
        return solution;
    }
}