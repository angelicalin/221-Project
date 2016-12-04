import java.util.*;

/**
 * Created by angelica on 11/20/16.
 */
public class GameSolver {

    int [][] cell_values;
    SolutionMap solutionMap;
    //ArrayList<SolutionCell> rowSums = new ArrayList<>();
    HashMap <Integer, SolutionCell> rowSumsMap;
    HashMap<Integer,SolutionCell> columnSumsMap;
   // ArrayList<SolutionCell> columnSums = new ArrayList<>();
    int [][] currentSolution;


    public GameSolver(int[][] cell_values){

        this.cell_values = cell_values;
        this.solutionMap = new SolutionMap();
        currentSolution = new int [cell_values.length][cell_values[0].length];
        rowSumsMap  = new HashMap<>();
        columnSumsMap =  new HashMap <>();
    }

    /**
     * This method runs the solution to the game.
     */

    public void analyizeGameInit(){

        for (int i = 0; i <14; i +=2){
            for (int j = 0; j<7; j++) {
                if (cell_values[i][j] == 0){
                    findSums(i,j);
                }
            }
        }

        loopOverEmptyCells();
        solutionMap.traverseTree();
    }

    private void loopOverEmptyCells() {
        for (Map.Entry<Integer, SolutionCell> entry: rowSumsMap.entrySet()){
            int numToAdd = entry.getValue().getNumToAdd();
            int sum = entry.getValue().getValue();
            int[] loc = entry.getValue().getLoc();
            int x = loc[0];
            int y = loc[1];
            for (int j = 1; j < numToAdd+1; j ++){
                ArrayList<Integer> possibleValues = fixedLengthPartition(sum,numToAdd);
                System.out.println("Sum is " + sum + "numToAdd is" + numToAdd + "Location at" + x +"," + y );
                System.out.println(possibleValues);

                solutionMap.addNodes(possibleValues,x, y+j);
            }
        }
        for (Map.Entry<Integer,SolutionCell> entry:columnSumsMap.entrySet()){
            int numToAdd = entry.getValue().getNumToAdd();
            int sum = entry.getValue().getValue();
            int[] loc = entry.getValue().getLoc();
            int x = loc[0];
            int y = loc[1];
            for (int j = 1; j < numToAdd+1; j ++){
                ArrayList<Integer> possibleValues = fixedLengthPartition(sum,numToAdd);
                solutionMap.addNodes(possibleValues,x+2*j, y);
            }
        }
    }

    /**
     * Find all possible values for each cell in the game.
     * @param rowIndex: the row index of a cell.
     * @param columnIndex: the column index of a cell.
     * @return An array list of integers that contain all possible values for a cell
     */

    private void findSums(int rowIndex, int columnIndex){
        findRowSum(rowIndex,columnIndex);
        findColumnSum(rowIndex,columnIndex);
        System.out.println(columnSumsMap.size());
        }

    /**
     * Find possible values, according the the column sum the single cell corresponds to.
     * @param rowIndex the row index of a cell.
     * @param columnIndex the column index of a cell.
     * @return An array list of integers that contain possible values for a cell from column sum
     */
    private void findColumnSum(int rowIndex, int columnIndex) {
        Integer numberToAdd = 0;
        while(cell_values[rowIndex][columnIndex] == 0){
            numberToAdd ++;
            rowIndex--;
        }
        Integer sum = cell_values[rowIndex][columnIndex];
        Integer location = (rowIndex-1)*100+columnIndex;
        if (!columnSumsMap.containsKey(location)){
            SolutionCell solutionCell = new SolutionCell(rowIndex-1, columnIndex, sum,false);
            columnSumsMap.put(location, solutionCell);
        }
        else {
            System.out.println("ha");
            SolutionCell solutionCell = columnSumsMap.get(location);
            solutionCell.incrementNumToAdd();
            columnSumsMap.put(location,solutionCell);
        }
    }

    /**
     * Find possible values, according the the row sum the single cell corresponds to.
     * @param rowIndex the row index of a cell.
     * @param columnIndex the column index of a cell.
     * @return An array list of integers that contain possible values for a cell from row sum
     */
    private void findRowSum(int rowIndex, int columnIndex) {
        Integer numberToAdd = 0;

        while(cell_values[rowIndex][columnIndex] == 0){
            numberToAdd ++;
            columnIndex--;
        }
        Integer sum = cell_values[rowIndex][columnIndex];
        Integer location = 100*rowIndex+columnIndex;
        if (!rowSumsMap.containsKey(location)){
            SolutionCell solutionCell = new SolutionCell(rowIndex, columnIndex, sum,true);
            rowSumsMap.put(location,solutionCell);
        }
        else {
            SolutionCell solutionCell = rowSumsMap.get(location);
            solutionCell.incrementNumToAdd();
            rowSumsMap.put(location,solutionCell);
        }
    }

    /**
     * This method will take in two integers, a sum and a num
     * @param sum number to partition
     * @param num fixed partition parts
     * @return a list of all the possible number that can be used, and under 9
     */
    private ArrayList<Integer> fixedLengthPartition(int sum, int num){
        ArrayList <Integer> result = new ArrayList<>();
        ArrayList<Integer> partition = new ArrayList<>();
        partition.add(sum-num+1);
        for (int t = 1; t < num;t++) {
            partition.add(1);
        }
        if (checkUniqueArray(partition)) {
            result = union(partition,result);
        }
        boolean doable = true;
        while (doable){
            int a_1 = partition.get(0);
            int i = 1;
            while ((!(partition.get(i)<a_1-1))){
                i++;
                if (i>partition.size()-1){
                    doable = false;
                    break;
                }
            }
            if (!doable){
                break;
            }
            int a_i = partition.get(i);
            for (int j = 1; j<=i ;j ++){
                partition.set(j,a_i+1);
            }
            int current_sum = 0;
            for (int y = 1;y < partition.size();y++){
                current_sum += partition.get(y);
            }
            partition.set(0,sum-current_sum);
            if (checkUniqueArray(partition)) {
                result = union(partition,result);
            }
        }
        return result;
    }
    private ArrayList<Integer> union (ArrayList<Integer> list1, ArrayList<Integer> list2){
        Set<Integer> set = new HashSet<>();
        set.addAll(list1);
        set.addAll(list2);
        return new ArrayList<>(set);
    }

    private boolean checkUniqueArray(ArrayList<Integer> partition){
        boolean unique = true;
        for (int x = 0;x<partition.size()-1&unique;x++){
            if (partition.get(x)==partition.get(x+1)|partition.get(x)>9){
                unique = false;
            }
        }
        return unique;
    }


    private void solveEmptyCells() {
        int[] currentLocation = new int[]{2,0};
        while(currentLocation[0]<14) {

            SolutionCell currentRowSumCell = findCorrespondingRowSum(currentLocation);
            SolutionCell currentColSumCell = findCorrespondingColSum(currentLocation);

            int currentRowIndex = currentLocation[0];
            int currentColumnIndex = currentLocation[1];

            CellContainer currentCell = solutionMap.getNodeAt(currentRowIndex, currentColumnIndex);
            if (!currentCell.getVisited()){
                int currentValue = currentCell.getPossibleValue().get(0);
                currentSolution[currentRowIndex][currentColumnIndex] = currentValue;
                ArrayList<Integer> newPossibleValues = currentCell.getPossibleValue();
                newPossibleValues.remove(0);
                newPossibleValues.add(currentValue);
                currentCell.setPossibleValues(newPossibleValues);
                currentCell.setVisited(true);
            }


            // check if it's the rightmost cell corresponding to a row sum

            if ((currentRowIndex - currentRowSumCell.getRowIndex()) == currentColSumCell.getNumToAdd()){
                while (!checkRowSum(currentRowSumCell)){
                    //TODO: update current location and change values for those cells
                }
            }

            // check if it's the bottom cell corresponding to a row sum

            if ((currentRowIndex - currentRowSumCell.getRowIndex()) == currentColSumCell.getNumToAdd()){
                while (!checkColSum(currentColSumCell)){
                    //TODO: update current location and change values for those cells
                }
            }


            // Update current location after we make sure the previous cell adds up to the correct number
            if (currentLocation[1] == 7){
                currentLocation[0]+=2;
                currentLocation[1] = 0;
            } else{
                currentLocation[1]++;
            }

        }

    }




    private SolutionCell findCorrespondingRowSum(int[] loc) {
        while (solutionMap.contain(loc)) {
            loc[1] -= 1;
        }
        loc[1]-=1;

        return rowSumsMap.get(loc[0]*100+loc[1]);
    }

    private SolutionCell findCorrespondingColSum(int[] loc) {
        while(solutionMap.contain(loc)){
            loc[0] -= 2;
        }
        loc[0]-=2;

        return columnSumsMap.get(loc[0]*100+loc[1]);
    }

    /**
     * check if the cells add up to their row sum
     * @param currentRowSumCell
     * @return
     */
    private Boolean checkRowSum(SolutionCell currentRowSumCell) {
        int cellValueRowSum = 0;
        int rowIndex = currentRowSumCell.getRowIndex();
        int colIndex = currentRowSumCell.getColumnIndex();
        for (int i = 1; i <= currentRowSumCell.getNumToAdd(); i ++){
            colIndex ++;
            cellValueRowSum += currentSolution[rowIndex][colIndex];
        }

        if (cellValueRowSum == currentRowSumCell.getValue()){
            return true;
        } else{
            return false;
        }

    }

    /**
     * check if the cells add up to their column sum
     * @param currentColSumCell
     * @return
     */

    private boolean checkColSum(SolutionCell currentColSumCell) {
        int cellValueColSum = 0;
        int rowIndex = currentColSumCell.getRowIndex();
        int colIndex = currentColSumCell.getColumnIndex();
        for (int i = 1; i <= currentColSumCell.getNumToAdd(); i ++){
            rowIndex +=2;
            cellValueColSum += currentSolution[rowIndex][colIndex];
        }
        if (cellValueColSum == currentColSumCell.getValue()){
            return true;
        } else{
            return false;
        }
    }


}





