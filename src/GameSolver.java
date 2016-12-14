import javafx.scene.control.Cell;

import java.util.*;

/**
 * Created by angelica on 11/20/16.
 */
public class GameSolver {

    int [][] cell_values;
    SolutionMap solutionMap;
    //ArrayList<SolutionCell> rowSums = new ArrayList<>();
    HashMap<Integer, SolutionCell> rowSumsMap;
    HashMap<Integer, SolutionCell> columnSumsMap;
    //ArrayList<SolutionCell> columnSums = new ArrayList<>();
    int[][] currentSolution;
    final int ROW_NUM = 14;
    final int COLUMN_NUM = 7;
    //    int[]  currentLocation;
    CheckboardGraphics checkboardGraphics;


    public GameSolver(int[][] cell_values, CheckboardGraphics checkboardGraphics){

        this.cell_values = cell_values;
        this.solutionMap = new SolutionMap();
        currentSolution = new int[cell_values.length][cell_values[0].length];
        rowSumsMap = new HashMap<>();
        columnSumsMap = new HashMap<>();
        //   currentLocation = new int[]{2,1};
        this.checkboardGraphics = checkboardGraphics;
    }

    /**
     * This method runs the solution to the game.
     */

    public void analyizeGameInit() {

        for (int i = 0; i < ROW_NUM; i += 2) {
            for (int j = 0; j < COLUMN_NUM; j++) {
                if (cell_values[i][j] == 0) {
                    findSums(i, j);
                }
            }
        }
        loopOverEmptyCells();
    //    solutionMap.traverseTree();
        for (Map.Entry<Integer,CellContainer>entry: solutionMap.getNodesHolder().entrySet()){
            System.out.println(entry.getKey());
            System.out.println("PossibleValues"+entry.getValue().getPossibleValue().toString());
        }
        solveEmptyCells();
    }

    /**
     * This method loops over only the empty cells and add the possible values of each empty cells to the solution map
     */

    private void loopOverEmptyCells() {
        for (Map.Entry<Integer, SolutionCell> entry : rowSumsMap.entrySet()) {
            int numToAdd = entry.getValue().getNumToAdd();
            int sum = entry.getValue().getValue();
            int[] loc = entry.getValue().getLoc();
            int x = loc[0];
            int y = loc[1];
            for (int j = 1; j < numToAdd + 1; j++) {
                ArrayList<Integer> possibleValues = fixedLengthPartition(sum, numToAdd);
                System.out.println("Sum is " + sum + "numToAdd is" + numToAdd + "Location at" + x + "," + y);
                //        System.out.println(possibleValues);

                solutionMap.addNodes(possibleValues, x, y + j);
            }
        }
        for (Map.Entry<Integer, SolutionCell> entry : columnSumsMap.entrySet()) {
            int numToAdd = entry.getValue().getNumToAdd();
            int sum = entry.getValue().getValue();
            int[] loc = entry.getValue().getLoc();
            int x = loc[0]+1;
            int y = loc[1];
            for (int j = 0; j < 2*numToAdd; j+=2) {
                ArrayList<Integer> possibleValues = fixedLengthPartition(sum, numToAdd);
                System.out.println("Sum is " + sum + "numToAdd is" + numToAdd + "Location at" + x + "," + y);
                //        System.out.println(possibleValues);

                solutionMap.addNodes(possibleValues, x+j, y);
            }
        }
    }

    /**
     * Find all possible values for each cell in the game.
     * @param rowIndex:    the row index of a cell.
     * @param columnIndex: the column index of a cell.
     * @return An array list of integers that contain all possible values for a cell
     */

    private void findSums(int rowIndex, int columnIndex) {
        findRowSum(rowIndex, columnIndex);
        findColSum(rowIndex, columnIndex);
    }


    /**
     * Find possible values, according the the row sum the single cell corresponds to.
     * @param rowIndex the row index of a cell.
     * @param columnIndex the column index of a cell.
     * @return An array list of integers that contain possible values for a cell from row sum
     */
    private void findRowSum(int rowIndex, int columnIndex) {
        while (cell_values[rowIndex][columnIndex] == 0) {
            columnIndex--;
        }
        Integer sum = cell_values[rowIndex][columnIndex];
        Integer location = 100 * rowIndex + columnIndex;
        if (!rowSumsMap.containsKey(location)) {
            SolutionCell solutionCell = new SolutionCell(rowIndex, columnIndex, sum, true);
            rowSumsMap.put(location, solutionCell);
        } else {
            SolutionCell solutionCell = rowSumsMap.get(location);
            solutionCell.incrementNumToAdd();
            rowSumsMap.put(location, solutionCell);
        }
    }

    private void findColSum(int rowIndex, int columnIndex){
        rowIndex--;
        while(cell_values[rowIndex][columnIndex]==0){
            rowIndex-=2;
        }
        Integer sum = cell_values[rowIndex][columnIndex];
        Integer location = 100 * rowIndex + columnIndex;
        if (!columnSumsMap.containsKey(location)) {
            SolutionCell solutionCell = new SolutionCell(rowIndex, columnIndex, sum, false);
            columnSumsMap.put(location, solutionCell);
        } else {
            SolutionCell solutionCell = columnSumsMap.get(location);
            solutionCell.incrementNumToAdd();
            columnSumsMap.put(location, solutionCell);
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

    /**
     * combine two lists and return their union
     * @param list1
     * @param list2
     * @return
     */

    private ArrayList<Integer> union (ArrayList<Integer> list1, ArrayList<Integer> list2){
        Set<Integer> set = new HashSet<>();
        set.addAll(list1);
        set.addAll(list2);
        return new ArrayList<>(set);
    }


    /**
     * check if the array has unique values
     * @param partition
     * @return
     */

    private boolean checkUniqueArray(ArrayList<Integer> partition){
        boolean unique = true;
        for (int x = 0;x<partition.size()-1&unique;x++){
            if (partition.get(x)==partition.get(x+1)|partition.get(x)>9){
                unique = false;
            }
        }
        return unique;
    }


    /**
     * solve the empty cells by filling in their possible values and checking if they add up to their row sums and column sums
     */


    private void solveEmptyCells() {
        int[] currentLocation = new int[]{2,1};
        //System.out.print("Start"+currentLocation[0]+currentLocation[1]);

        while(currentLocation[0]<ROW_NUM) {
            if (solutionMap.contain(currentLocation[0]*100 + currentLocation[1]) ) {
                SolutionCell currentRowSumCell = findCorrespondingRowSum(currentLocation);
                SolutionCell currentColSumCell = findCorrespondingColSum(currentLocation);
                CellContainer currentCell = solutionMap.getNodeAt(currentLocation[0], currentLocation[1]);
                Integer val = currentCell.accessAPossibleValue();
                currentSolution[currentLocation[0]][currentLocation[1]] = val;
                checkboardGraphics.changeDisplayNum(currentLocation[0]*100+currentLocation[1],val);
                boolean rowSolved = true;
                boolean colSolved = true;
                boolean isLastRow = false;

                if ((currentLocation[1] - currentRowSumCell.getColumnIndex()) == currentRowSumCell.getNumToAdd()) {
                    isLastRow=true;
                    while (!checkRowSum(currentRowSumCell)) {
                        if (currentCell.loopedOverAlready()) {
                            rowSolved = false;
                            break;
                        }
                        val = currentCell.accessAPossibleValue();
                        checkboardGraphics.changeDisplayNum(currentLocation[0]*100+currentLocation[1],val);
                        currentSolution[currentLocation[0]][currentLocation[1]] = val;
                    }
                }
                if ((currentLocation[0] - (currentColSumCell.getRowIndex()-1)) == 2*currentColSumCell.getNumToAdd()) {
                    if(!isLastRow) {
                        while(!checkColSum(currentColSumCell)){
                            if(currentCell.loopedOverAlready()){
                                colSolved=false;
                                break;
                            }
                            val = currentCell.accessAPossibleValue();
                            checkboardGraphics.changeDisplayNum(currentLocation[0] * 100 + currentLocation[1], val);
                            currentSolution[currentLocation[0]][currentLocation[1]] = val;
                    }
                    }
                    else {
                        colSolved = checkColSum(currentColSumCell);
                    }
                }

                if (!colSolved) {
                    currentLocation = goBackBeginningOfColSum(currentColSumCell.getRowIndex(), currentLocation);
                } else if (!rowSolved) {
                    currentLocation = goBackBeginningOfRowSum(currentRowSumCell.getColumnIndex(), currentLocation);
                } else {
                    currentLocation = updateCurrentLocationHorizontally(currentLocation);
                }
            }
            else{
                currentLocation = updateCurrentLocationHorizontally(currentLocation);
                System.out.println("Out Loop"+currentLocation[0]*100+"g"+currentLocation[1]);
            }
         //   System.out.println(currentLocation[0]*100 + currentLocation[1]);
        }
        //System.out.println(currentLocation[0]*100 + currentLocation[1]);
        System.out.println("Solving Complete");
    }


    /**
     * update the current location horizontally
     * @param currentLocation
     * @return
     */


    private int[] updateCurrentLocationHorizontally(int[] currentLocation){
        currentLocation[1] = currentLocation[1]+1;
        if (!(currentLocation[1]<COLUMN_NUM)){
            currentLocation[0]+=2;
            currentLocation[1]=1;
    private int[] updateCurrentLocationHorizontally(int[] currentLocation) {
        currentLocation[1] = currentLocation[1] + 1;
        if (!(currentLocation[1] < COLUMN_NUM)) {
            currentLocation[0] += 2;
            currentLocation[1] = 1;
        }
        return currentLocation;
    }

    /**
     * make current location jump back to the beginning of the row sum
     * @param rowSumColIndex
     * @param currentLocation
     * @return
     */

    private int[] goBackBeginningOfRowSum(int rowSumColIndex, int[]currentLocation){
        currentLocation[1] = rowSumColIndex+1;
        return currentLocation;
    }

    private int[] goBackBeginningOfColSum(int colSumRowIndex, int[] currentLocation) {
        currentLocation[0] = colSumRowIndex + 1;
        return currentLocation;
    }

    /**
     * find the row sum that corresponds to the current empty cell
     * @param loc
     * @return
     */

    private SolutionCell findCorrespondingRowSum(int[] loc) {
        int copyrow = loc[0];
        int copycolumn = loc[1];
        while (solutionMap.contain(copyrow*100+copycolumn)) {
            copycolumn --;
        }
        return rowSumsMap.get(copyrow*100+copycolumn);
    }

    private SolutionCell findCorrespondingColSum(int[] loc) {
        int copyrow = loc[0];
        int copycolumn = loc[1];
        while (solutionMap.contain(copyrow * 100 + copycolumn)) {
            copyrow -= 2;
        }
        return columnSumsMap.get((copyrow+1) * 100 + copycolumn);
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
        ArrayList<Integer> addedValue = new ArrayList<>();
        for (int i = 0; i < currentRowSumCell.getNumToAdd(); i ++){
            colIndex ++;
            cellValueRowSum = cellValueRowSum + currentSolution[rowIndex][colIndex];
            if (addedValue.contains(currentSolution[rowIndex][colIndex])){
                return false;
            }
            addedValue.add(currentSolution[rowIndex][colIndex]);

        }
        System.out.println(cellValueRowSum);
        return (cellValueRowSum == currentRowSumCell.getValue());

    }

    private boolean checkColSum(SolutionCell currentColSumCell) {
        int cellValueColSum = 0;
        int rowIndex = currentColSumCell.getRowIndex()-1;
        int colIndex = currentColSumCell.getColumnIndex();
        ArrayList<Integer> addedValue = new ArrayList<>();
        for (int i = 0; i < currentColSumCell.getNumToAdd(); i++) {
            rowIndex += 2;
            cellValueColSum += currentSolution[rowIndex][colIndex];
            if (addedValue.contains(currentSolution[rowIndex][colIndex])) {
                return false;
            }
            addedValue.add(currentSolution[rowIndex][colIndex]);
        }
        return (cellValueColSum == currentColSumCell.getValue());

    }
}