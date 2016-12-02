import java.util.*;

/**
 * Created by angelica on 11/20/16.
 */
public class GameSolver {

    int [][] cell_values;
    SolutionMap solutionMap;
    ArrayList<SolutionCell> rowSums = new ArrayList<>();
    TreeMap<Integer,SolutionCell> columnSums = new TreeMap <>();
    int [][] currentSolution;


    public GameSolver(int[][] cell_values){

        this.cell_values = cell_values;
        this.solutionMap = new SolutionMap();
        currentSolution = new int [cell_values.length/2][cell_values[0].length];
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

        System.out.println(fixedLengthPartition(15,2));

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
        if (numberToAdd==1){
            SolutionCell solutionCell = new SolutionCell(rowIndex, columnIndex, sum,false);
            columnSums.put(rowIndex * 100+columnIndex, solutionCell);
        }
        else {
            SolutionCell solutionCell = columnSums.get(rowIndex*100+columnIndex);
            solutionCell.incrementNumToAdd();
            columnSums.put(rowIndex * 100+columnIndex,solutionCell);
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

        if (numberToAdd==1){
            SolutionCell solutionCell = new SolutionCell(rowIndex, columnIndex, sum,true);
            rowSums.add(solutionCell);
        }
        else {
            rowSums.get(rowSums.size()-1).incrementNumToAdd();
        }
        }

    private void addValuestoTree(ArrayList<Integer> possibleValues, int xLoc, int yLoc){
        solutionMap.addNodes(possibleValues,xLoc,yLoc);
       // currentNode = parentsToAdd.getChildofIndex(0);
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

        boolean doable = true;
        while (doable){
            int a_1 = partition.get(0);
            int i = 1;
            while ((!(partition.get(i)<a_1-1))){
                i++;
                if (i>=partition.size()-1){
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
            boolean unique = true;
            for (int x = 0;x<partition.size()-1&unique;x++){
                if (partition.get(x)==partition.get(x+1)|partition.get(x)>9){
                    unique = false;
                }
            }
            if (unique) {
                result.addAll(partition);
            }
            Set<Integer> hs = new HashSet<>();
            hs.addAll(result);
            result.clear();
            result.addAll(hs);
        }
        return result;
    }


}





