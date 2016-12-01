import java.util.*;

/**
 * Created by angelica on 11/20/16.
 */
public class GameSolver {

    int [][] cell_values;
    Tree solver_tree;
    TreeNode currentNode;
  //  int currentLevel = 0;
    ArrayList<SolutionCell> rowSums = new ArrayList<>();
    TreeMap<Integer,SolutionCell> columnSums = new TreeMap <>();


    public GameSolver(int[][] cell_values){

        this.cell_values = cell_values;
        this.solver_tree = new Tree();
        this.currentNode = solver_tree.getRoot();
    }

    public void solveGame(){

        for (int i = 0; i <14; i +=2){
            for (int j = 0; j<7; j++) {
                if (cell_values[i][j] == 0){
                    ArrayList<Integer> possibleValues = findPossibleValues(i,j);
                    addValuestoTree(possibleValues,i, j);
            //        System.out.println("row" +i+"c"+j);
            //        currentLevel++;
                    }
            }
        }
        solver_tree.traverseTree();
        for (int x = 0; x<rowSums.size();x++){
            String rowSumString = "row " +rowSums.get(x).getX() + " column " + rowSums.get(x).getY() + " is value " +rowSums.get(x).getValue()+ " to Add " + rowSums.get(x).getNumToAdd() + "numbers";
            System.out.println(rowSumString);
            System.out.println("yeah~");
        }
        for (Map.Entry<Integer, SolutionCell> entry: columnSums.entrySet()){
            String columnSum = "row " +entry.getValue().getX() +" column " + entry.getValue().getY()+ " is value " +entry.getValue().getValue()+ " to Add " + entry.getValue().getNumToAdd() + "numbers";
            System.out.println(columnSum);
        }
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
        if (numberToAdd==1){
            SolutionCell solutionCell = new SolutionCell(rowIndex, columnIndex, sum,false);
            columnSums.put(rowIndex * 100+columnIndex, solutionCell);
        }
        else {
            SolutionCell solutionCell = columnSums.get(rowIndex*100+columnIndex);
            solutionCell.incrementNumToAdd();
            columnSums.put(rowIndex * 100+columnIndex,solutionCell);
        }
        return findComposition(sum);

    }


    private ArrayList<Integer> findPossibleValueFromRowSum(int rowIndex, int columnIndex) {
        Integer numberToAdd = 0;

        while(cell_values[rowIndex][columnIndex] == 0){
            numberToAdd ++;
            columnIndex--;
        }
        Integer sum = cell_values[rowIndex][columnIndex];

        if (numberToAdd==1){
            SolutionCell solutionCell = new SolutionCell(rowIndex, columnIndex, sum,true);
            rowSums.add(solutionCell);
        }
        else {
            rowSums.get(rowSums.size()-1).incrementNumToAdd();
        }

        return findComposition(sum);

        }


    private ArrayList<Integer> findComposition(Integer sum){
        ArrayList<Integer> result = new ArrayList<>();
            for (int i =1; (i<sum&i<10);i++){
                result.add(i);
            }


        return result;
    }

    private void addValuestoTree(ArrayList<Integer> possibleValues, int xLoc, int yLoc){
     //   TreeNode parentsToAdd = solver_tree.getNodeAt(level).get(0);
        TreeNode parentsToAdd = currentNode;
   //     System.out.println(possibleValues.size());

            for (int i = 0; i < possibleValues.size(); i ++){
 //               System.out.println("-_-");
                TreeNode childToAdd= new TreeNode(parentsToAdd,possibleValues.get(i));
                solver_tree.addChild(parentsToAdd,childToAdd,xLoc,yLoc);
            }
        currentNode = parentsToAdd.getChildofIndex(0);
    }

    private void fillInSolution (){

    }

}





