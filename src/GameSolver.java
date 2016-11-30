import java.util.ArrayList;

/**
 * Created by angelica on 11/20/16.
 */
public class GameSolver {

    int [][] cell_values;
    Tree solver_tree;
    ArrayList<Integer> currentNodeValues = new ArrayList<Integer>();
    int currentLevel = 0;


    public GameSolver(int[][] cell_values){

        this.cell_values = cell_values;
        this.solver_tree = new Tree();

    }

    public void solveGame(){

        for (int i = 0; i <14; i +=2){
            for (int j = 0; j<7; j++) {
                if (cell_values[i][j] == 0){
                    ArrayList<Integer> possibleValues = findPossibleValues(i,j);
                    addValuestoTree(possibleValues,currentLevel);
            //        System.out.println("row" +i+"c"+j);
                    currentLevel++;
                    }
            }
        }
        solver_tree.traverseTree();
    }

    private ArrayList<Integer> findPossibleValues(int rowIndex, int columnIndex){
        ArrayList <Integer> possibleRowValues = findPossibleValueFromRowSum(rowIndex,columnIndex);
        ArrayList <Integer> possibleColumnValues = findPossibleValueFromColumnSum(rowIndex,columnIndex);
        ArrayList<Integer> possibleValues = new ArrayList<>();
        for (int i = 0; i<possibleColumnValues.size();i++){
            for (int j=0;j<possibleRowValues.size();j++){
                if (possibleColumnValues.get(i)==possibleRowValues.get(j)){
                    possibleValues.add(possibleColumnValues.get(i));
                }
            }
        }
        return possibleValues;
        }

    private ArrayList<Integer> findPossibleValueFromColumnSum(int rowIndex, int columnIndex) {
        Integer numberToAdd = 0;

        while(cell_values[rowIndex][columnIndex] == 0){
            numberToAdd ++;
            rowIndex--;
        }
        Integer sum = cell_values[rowIndex][columnIndex];
        return findComposition(sum,numberToAdd);

    }


    private ArrayList<Integer> findPossibleValueFromRowSum(int rowIndex, int columnIndex) {
        Integer numberToAdd = 1;


        while(cell_values[rowIndex][columnIndex] == 0){
            numberToAdd ++;
            columnIndex--;
        }
        Integer sum = cell_values[rowIndex][columnIndex];


        return findComposition(sum,numberToAdd);

        }


    private ArrayList<Integer> findComposition(Integer sum, Integer num){
        ArrayList<Integer> result = new ArrayList<>(num);
            for (int i =0; (i<sum&i<10);i++){
                result.add(i);
            }


        return result;
    }

    private void addValuestoTree(ArrayList<Integer> possibleValues, int level){
        TreeNode parentsToAdd = solver_tree.getNodeAt(level).get(0);
   //     System.out.println(possibleValues.size());

            for (int i = 0; i < possibleValues.size(); i ++){
 //               System.out.println("-_-");
                TreeNode childToAdd= new TreeNode(parentsToAdd,possibleValues.get(i));
                solver_tree.addChild(parentsToAdd,childToAdd,(level+1));
            }
    }

}





