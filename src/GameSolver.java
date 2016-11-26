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
        for (int i = 0; i <14; i ++){
            for (int j = 0; j<7; j++) {
                if (cell_values[i][j] == 0){
                    if(i%2 == 1){
                        currentNodeValues = findAdjancentRowSum(i,j);

                    } else if (i%2==0) {

                    }
                }
            }
        }
    }

    public ArrayList<Integer> findAdjancentRowSum(int i, int j) {
        int numberToAdd = 0;
        int forbiddenNumber = 0;
        ArrayList<Integer> possibleValues = new ArrayList<Integer>();

        while(cell_values[i][j] == 0){
            numberToAdd ++;
            i--;
        }

        int rowSum = cell_values[i][j];
        if(rowSum % numberToAdd == 0){
            forbiddenNumber = rowSum/numberToAdd;
        }


        for (int k = 1; k< rowSum; k++){
            possibleValues.add(k);
            }

        possibleValues.remove(forbiddenNumber);

        return possibleValues;

        }


    }








}
