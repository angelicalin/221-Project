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
    }

    public void GameSolver(){
        for (int i = 0; i <14; i +=2){
            for (int j = 0; j<7; j++) {
                if (cell_values[i][j] == 0){
                    ArrayList possibleRowSum = findPossibleValuesforRowSum(i,j);
                    }
            }
        }
    }

    public ArrayList<Integer> findPossibleValues(int i, int j){

    }

    public ArrayList<Integer> findPossibleValuesforRowSum(int i, int j) {
        int numberToAdd = 0;
        int forbiddenNumber = 0;
        ArrayList<Integer> possibleValues = new ArrayList<Integer>();

        while(cell_values[i][j] == 0){
            numberToAdd ++;
            j--;
        }

        int rowSum = cell_values[i][j];

        for (int k = 1; k< rowSum; k++){
            possibleValues.add(k);
        }

        possibleValues.remove(forbiddenNumber);


        }

    private ArrayList<Integer> findPossibleValuesforColumnSum(int i, int j){
        int numberToAdd = 0;
        int forbiddenNumber = 0;
        ArrayList<Integer> possibleValues = new ArrayList<Integer>();

        while(cell_values[i][j] == 0){
            numberToAdd ++;
            i--;
        }

        int columnSum = cell_values[i][j];


        for (int k = 1; k< columnSum; k++){
            possibleValues.add(k);
        }

        possibleValues.remove(forbiddenNumber);

        return possibleValues;

    }

    private ArrayList<Integer> findComposition(int sum, int num){
        ArrayList<Integer> result = new ArrayList<>(num);
        Integer i = sum/num+1;
        while (i+1<9 & (sum-i)>0){
            result.add(i);
        }
        return result;
    }

}





