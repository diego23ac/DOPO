import java.util.*;

public class TowerContest {
    
    /**
     * Resuelve el problema de la maratón.
     * 
     * @param int n Numero de copas
     * @param int h Altura de la torre
     * @return String result Si es posible construir una torre de altura h
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
     * @param int n Numero de copas
     * @param int h Altura de la torre
     */
    public void simulate(int n, int h) {
        Tower tower = new Tower(h, 2*n);
        tower.makeVisible();
        String result = solve(n, h);
        if(!result.equals("impossible")) {
            String[] parts = result.split(" ");
            int[] cups = new int[parts.length];
            for(int i = 0; i < parts.length; i++) {
                cups[i] = Integer.parseInt(parts[i])/2 + 1;
            }
            
            for (Integer cup : cups) {
                tower.pushCup(cup);
            }
        }
    }
    
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
    
    private ArrayList<Integer> solve(ArrayList<Integer> cups,int h){
        int height=h;
        int newHeight=0;
        int index=cups.size()-1;
        int counter=0;
        ArrayList<Integer> solution=new ArrayList<Integer>();
        while(height>0){
            newHeight=height-(2*cups.get(index)-1);
            if(newHeight>=0){
                height=newHeight;
                solution.add(0,cups.get(index));
                if(solution.size()!=cups.size()&&newHeight==0){
                    ArrayList<Integer> newCups=new ArrayList<Integer>();
                    for(int i=0;i<cups.size();i++){
                        Integer value=cups.get(i);
                        if(!solution.contains(value)){newCups.add(value);}
                    }
                    ArrayList<Integer> rest=this.solve(newCups,h-solution.size());
                    if(rest!=null){
                        solution.addAll(rest);
                        return solution;
                    }else{
                        solution.remove(0);
                        height=h;
                    }
                }
            }else{newHeight=height;}
            index--;
            if(index<0&&height>0){
                counter++;
                newHeight=0;
                height=h-counter;
                index=cups.size()-1;
                solution.clear();
            }
            if(height<=0){break;}
        }
        if(solution.size()==cups.size()){return solution;}
        return null;
    }
}