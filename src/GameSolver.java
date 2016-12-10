import javafx.scene.control.Cell;

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
    final int ROW_NUM = 8;
    final int COLUMN_NUM = 5;
//    int[]  currentLocation;
    CheckboardGraphics checkboardGraphics;


    public GameSolver(int[][] cell_values, CheckboardGraphics checkboardGraphics){

        this.cell_values = cell_values;
        this.solutionMap = new SolutionMap();
        currentSolution = new int [cell_values.length][cell_values[0].length];
        rowSumsMap  = new HashMap<>();
        columnSumsMap =  new HashMap <>();
     //   currentLocation = new int[]{2,1};
        this.checkboardGraphics = checkboardGraphics;
    }

    /**
     * This method runs the solution to the game.
     */

    public void analyizeGameInit(){

        for (int i = 0; i <ROW_NUM; i +=2){
            for (int j = 0; j<COLUMN_NUM; j++) {
                if (cell_values[i][j] == 0){
                    findSums(i,j);
                }
            }
        }
        loopOverEmptyCells();
        solutionMap.traverseTree();
        for (Map.Entry<Integer,CellContainer>entry: solutionMap.getNodesHolder().entrySet()){
            System.out.println(entry.getKey());
            System.out.println("PossibleValues"+entry.getValue().getPossibleValue().toString());
        }
        solveEmptyCells();
    }

    private void loopOverEmptyCells() {
        for (Map.Entry<Integer, SolutionCell> entry: rowSumsMap.entrySet()){
            int numToAdd = entry.getValue().getNumToAdd();
            int sum = entry.getValue().getValue();
            int[] loc = entry.getValue().getLoc();
            int x = loc[0];
            int y = loc[1];
            for (int j = 1; j < numToAdd; j ++){
                ArrayList<Integer> possibleValues = fixedLengthPartition(sum,numToAdd);
                System.out.println("Sum is " + sum + "numToAdd is" + numToAdd + "Location at" + x +"," + y );
        //        System.out.println(possibleValues);

                solutionMap.addNodes(possibleValues,x, y+j);
            }
        }
        for (Map.Entry<Integer,SolutionCell> entry:columnSumsMap.entrySet()){
            int numToAdd = entry.getValue().getNumToAdd();
            int sum = entry.getValue().getValue();
            int[] loc = entry.getValue().getLoc();
            int x = loc[0];
            int y = loc[1];
            for (int j = 0; j < numToAdd; j ++){
                ArrayList<Integer> possibleValues = fixedLengthPartition(sum,numToAdd);
                solutionMap.addNodes(possibleValues,x+2*j+1, y);
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
        //System.out.println(columnSumsMap.size());
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
        Integer location = (rowIndex)*100+columnIndex;
        if (!columnSumsMap.containsKey(location)){
            SolutionCell solutionCell = new SolutionCell(rowIndex, columnIndex, sum,false);
            columnSumsMap.put(location, solutionCell);
        }
        else {
          //  System.out.println("ha");
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
        int[] currentLocation = new int[]{2,1};
        //System.out.print("Start"+currentLocation[0]+currentLocation[1]);

        while(currentLocation[0]<ROW_NUM) {
            if (solutionMap.contain(currentLocation[0]*100 + currentLocation[1]) ) {
                SolutionCell currentRowSumCell = findCorrespondingRowSum(currentLocation);
                SolutionCell currentColSumCell = findCorrespondingColSum(currentLocation);

                int currentRowIndex = currentLocation[0];
               //System.out.println("Row Sum Index" + currentRowSumCell.getRowIndex() + "Col" + currentRowSumCell.getColumnIndex());
                int currentColumnIndex = currentLocation[1];
               //System.out.println("Col Sum Index" + currentColSumCell.getRowIndex() + "Col" + currentColSumCell.getColumnIndex());


                CellContainer currentCell = solutionMap.getNodeAt(currentRowIndex, currentColumnIndex);
                Integer val = currentCell.accessAPossibleValue();
                currentSolution[currentRowIndex][currentColumnIndex] = val;
          //       System.out.println("Row"+currentLocation[0]+"Column"+currentLocation[1]);
                checkboardGraphics.changeDisplayNum(currentRowIndex*100+currentColumnIndex,val);
                // check if it's the rightmost cell corresponding to a row sum
                boolean rowSolved = true;
                boolean columnSolved = true;

                if ((currentColumnIndex - currentRowSumCell.getColumnIndex()) == currentRowSumCell.getNumToAdd()) {
                    while (!checkRowSum(currentRowSumCell)) {
                        if (currentCell.loopedOverAlready()){
                            rowSolved = false;
                            break;
                        }
                        val = currentCell.accessAPossibleValue();
                        checkboardGraphics.changeDisplayNum(currentRowIndex*100+currentColumnIndex,val);
                        currentSolution[currentRowIndex][currentColumnIndex] = val;
                    }
                }
                // check if it's the bottom cell corresponding to a column sum

               if ((((currentRowIndex+1) - currentColSumCell.getRowIndex())/2) == currentColSumCell.getNumToAdd()) {
                   if (rowSolved) {
                       if (!checkColSum(currentColSumCell) ) {
                           columnSolved = false;
                       }
                   }
               }

                if(!columnSolved){
             //       System.out.println("Calling goBackBeginningOfColSum");
                    currentLocation = goBackBeginningOfColSum(currentColSumCell.getRowIndex(),currentLocation);
                }
                else if (!rowSolved) {
                    //       System.out.println("Calling goBackBeginningOfRowSum");
                    currentLocation = goBackBeginningOfRowSum(currentRowSumCell.getColumnIndex(),currentLocation);
                }
                else{
               //     System.out.println("Calling updateCurrentLocationHorizontally");
                    currentLocation = updateCurrentLocationHorizontally(currentLocation);
                }
            }
            else{
                currentLocation = updateCurrentLocationHorizontally(currentLocation);
                System.out.println("Out Loop"+currentLocation[0]*100+currentLocation[1]);
            }
         //   System.out.println(currentLocation[0]*100 + currentLocation[1]);
        }
        //System.out.println(currentLocation[0]*100 + currentLocation[1]);
        System.out.println("Solving Complete");
    }



    private int[] updateCurrentLocationHorizontally(int[] currentLocation){
        currentLocation[1]++;
        if (!(currentLocation[1]<COLUMN_NUM)){
            currentLocation[0]+=2;
            currentLocation[1]=1;
        }
        return currentLocation;
    }

    private int[] goBackBeginningOfRowSum(int rowSumColIndex, int[]currentLocation){
        currentLocation[1] = rowSumColIndex+1;
        return currentLocation;
    }
    private int[] goBackBeginningOfColSum(int colSumRowIndex, int[]currentLocation){
        currentLocation[0] = colSumRowIndex+1;
        return currentLocation;
    }


    private SolutionCell findCorrespondingRowSum(int[] loc) {
        int copyrow = loc[0];
        int copycolumn = loc[1];
        while (solutionMap.contain(copyrow*100+copycolumn)) {
            copycolumn --;
        }
        return rowSumsMap.get(copyrow*100+copycolumn);
    }

    private SolutionCell findCorrespondingColSum(int[] loc) {
        int copyrow = loc[0]-1;
        int copycolumn = loc[1];
        while(!columnSumsMap.containsKey(copyrow*100+copycolumn)){
            copyrow -= 2;
        }
        return columnSumsMap.get(copyrow*100+copycolumn);
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
        int sum = currentRowSumCell.getValue();
        ArrayList<Integer> addedValue = new ArrayList<>();

        for (int i = 0; i < currentRowSumCell.getNumToAdd(); i ++){
            colIndex ++;
            cellValueRowSum = cellValueRowSum + currentSolution[rowIndex][colIndex];

            if (addedValue.contains(currentSolution[rowIndex][colIndex])){
                return false;
            }
            else if(cellValueRowSum>sum){
                return false;
            }
            addedValue.add(currentSolution[rowIndex][colIndex]);
        }
        return (cellValueRowSum == currentRowSumCell.getValue());

    }

    /**
     * check if the cells add up to their column sum
     * @param currentColSumCell
     * @return
     */

    private boolean checkColSum(SolutionCell currentColSumCell) {
         int cellValueColSum = 0;
        int rowIndex = currentColSumCell.getRowIndex()-1;
        int colIndex = currentColSumCell.getColumnIndex();
        Integer sum = currentColSumCell.getValue();
        ArrayList<Integer> addedValue = new ArrayList<>();
        for (int i = 0; i < currentColSumCell.getNumToAdd(); i ++){
            rowIndex = rowIndex + 2;
            int addvalue = cellValueColSum+currentSolution[rowIndex][colIndex];
            cellValueColSum = cellValueColSum + addvalue;
            if (addedValue.contains(addvalue)){
                return false;
            }
            else if(cellValueColSum>sum){
                return false;
            }
            addedValue.add(addvalue);
        }
        return (cellValueColSum == sum);
    }


}