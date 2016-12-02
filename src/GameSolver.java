import java.util.*;

/**
 * Created by angelica on 11/20/16.
 */
public class GameSolver {

    int [][] cell_values;
    SolutionMap solutionMap;
 //   TreeNode currentNode;
  //  int currentLevel = 0;
    ArrayList<SolutionCell> rowSums = new ArrayList<>();
    TreeMap<Integer,SolutionCell> columnSums = new TreeMap <>();


    public GameSolver(int[][] cell_values){

        this.cell_values = cell_values;
        this.solutionMap = new SolutionMap();
   //     this.currentNode = solutionMap.getRoot();
    }

    /**
     * This method runs the solution to the game.
     */

    public void analyizeGameInit(){

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
        solutionMap.traverseTree();
        pruneTreeOnRow();
        pruneTreeOnColumn();
        solutionMap.traverseTree();
//        for (int x = 0; x<rowSums.size();x++){
//            String rowSumString = "row " +rowSums.get(x).getX() + " column " + rowSums.get(x).getY() + " is value " +rowSums.get(x).getValue()+ " to Add " + rowSums.get(x).getNumToAdd() + "numbers";
//            System.out.println(rowSumString);
//            System.out.println("yeah~");
//        }
//        for (Map.Entry<Integer, SolutionCell> entry: columnSums.entrySet()){
//            String columnSum = "row " +entry.getValue().getX() +" column " + entry.getValue().getY()+ " is value " +entry.getValue().getValue()+ " to Add " + entry.getValue().getNumToAdd() + "numbers";
//            System.out.println(columnSum);
//        }
    }

    /**
     * Find all possible values for each cell in the game.
     * @param rowIndex: the row index of a cell.
     * @param columnIndex: the column index of a cell.
     * @return An array list of integers that contain all possible values for a cell
     */

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

    /**
     * Find possible values, according the the column sum the single cell corresponds to.
     * @param rowIndex the row index of a cell.
     * @param columnIndex the column index of a cell.
     * @return An array list of integers that contain possible values for a cell from column sum
     */


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

    /**
     * Find possible values, according the the row sum the single cell corresponds to.
     * @param rowIndex the row index of a cell.
     * @param columnIndex the column index of a cell.
     * @return An array list of integers that contain possible values for a cell from row sum
     */

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


    /**
     *
     * @param sum
     * @return
     */

    private ArrayList<Integer> findComposition(Integer sum){
        ArrayList<Integer> result = new ArrayList<>();
            for (int i =1; (i<sum&i<10);i++){
                result.add(i);
            }


        return result;
    }

    private void addValuestoTree(ArrayList<Integer> possibleValues, int xLoc, int yLoc){
     //   TreeNode parentsToAdd = solverMap.getNodeAt(level).get(0);
   //     TreeNode parentsToAdd = currentNode;
   //     System.out.println(possibleValues.size());

            for (int i = 0; i < possibleValues.size(); i ++){
 //               System.out.println("-_-");
                TreeNode childToAdd= new TreeNode(possibleValues.get(i));
                solutionMap.addNodes(childToAdd,xLoc,yLoc);
            }
       // currentNode = parentsToAdd.getChildofIndex(0);
    }

    private void pruneTreeOnRow(){
        for (int i = 0; i<rowSums.size();i++){
            SolutionCell singleRowSum = rowSums.get(i);
            int xLocofRowSum = singleRowSum.getX();
            int yLocofRowSum = singleRowSum.getY();
            int numToAdd = singleRowSum.getNumToAdd();
            if (singleRowSum.getValue()%numToAdd == 0) {

                int numberToRemove = singleRowSum.getValue() / numToAdd;
                System.out.println(numberToRemove);
                for(int j =1; j <= numToAdd; j++){
                    solutionMap.removeChildofValue(numberToRemove,xLocofRowSum,yLocofRowSum+j);
                }
            }

        }
    }
    private void pruneTreeOnColumn(){
        for (Map.Entry<Integer, SolutionCell> entry: columnSums.entrySet()){
//            String columnSum = "row " +entry.getValue().getX() +" column " + entry.getValue().getY()+ " is value " +entry.getValue().getValue()+ " to Add " + entry.getValue().getNumToAdd() + "numbers";
//            System.out.println(columnSum);
            SolutionCell singleColumnSum = entry.getValue();
            int xLocofRowSum = singleColumnSum.getX();
            int yLocofRowSum = singleColumnSum.getY();
            int numToAdd = singleColumnSum.getNumToAdd();
            if (singleColumnSum.getValue()%numToAdd == 0) {

                int numberToRemove = singleColumnSum.getValue() / numToAdd;
  //              System.out.println(numberToRemove);
                for(int j =1; j <= numToAdd; j +=2){
                    solutionMap.removeChildofValue(numberToRemove,xLocofRowSum+j,yLocofRowSum);
                }
            }

        }
    }

}





