import java.util.ArrayList;

/**
 * Created by angelica on 11/20/16.
 */
public class GameSolver {

    int [][] cell_values;
    Tree solver_tree;
    ArrayList<Integer> currentNodeValues = new ArrayList<Integer>();

    public GameSolver(int[][] cell_values){
        this.cell_values = cell_values;
        solver_tree = new Tree();
    }

    public void gameSolve(){
        for (int i = 0; i <14; i ++){
            for (int j = 0; j<7; j++) {
                if (cell_values[i][j] == 0){
                    if(i%2 == 1){
                        findPossibleValuesforRowSum(i,j);
                    } else if (i % 2 == 0) {
                        findPossibleValuesforColumnSum(i,j);
                    }
                }
            }
        }
    }

    private ArrayList<Integer> findPossibleValuesforRowSum(int i, int j) {
        int numberToAdd = 0;
        while(cell_values[i][j] == 0){
            numberToAdd ++;
            j--;
        }
        ArrayList<Integer> possibleValues=findComposition(cell_values[i][j], numberToAdd);
        return possibleValues;
    }



    private ArrayList<Integer> findPossibleValuesforColumnSum(int i, int j){
        int numberToAdd = 0;
        while(cell_values[i][j] == 0){
            numberToAdd ++;
            i--;
        }
        ArrayList<Integer> possibleValues=findComposition(cell_values[i][j], numberToAdd);
        return possibleValues;
    }

    private ArrayList<Integer> findComposition(int sum, int num){
        ArrayList<Integer> result = new ArrayList<>(num);
        Integer i = sum/num+1;
        while (i<9 & (sum-i)>0 & i<sum){
            result.add(i);
            i++;
        }
        return result;
    }
}
