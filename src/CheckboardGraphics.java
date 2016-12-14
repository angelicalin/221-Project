/**
 * This game draws check board for the game and add in all single cells
 */

import acm.graphics.GCompound;

import java.util.HashMap;


public class CheckboardGraphics extends GCompound{
    private static final double BOARD_WIDTH = 350.0;
    private static final double BOARD_HEIGHT = 350.0;
    private static final double CELL_LENGTH = 50.0;
  //  private static final int row_num_cells = 7;
    int ROW_NUM;
    int COLUMN_NUM;
    private HashMap<Integer, SingleCellGraphics> cellGraphicsMap = new HashMap<>();



    public CheckboardGraphics(int [][] cell_values)  {
        this.ROW_NUM = cell_values.length/2;
        this.COLUMN_NUM = cell_values[0].length;
        for (int i = 0; i < ROW_NUM; i++) {
            for (int j = 0; j < COLUMN_NUM; j++){
                Integer VALUE_UP = cell_values[2*i][j];
                Integer VALUE_DOWN = cell_values[2*i+1][j];
                boolean fillable = true;

                if (VALUE_UP !=0 | (VALUE_DOWN != 0)) {
                    fillable = false;
                }
                SingleCellGraphics singleCell = new SingleCellGraphics(VALUE_UP, VALUE_DOWN, fillable);

                double xPos = j*CELL_LENGTH;
                double yPos = i*CELL_LENGTH;
                add(singleCell, xPos, yPos);
                cellGraphicsMap.put((2*i*100+j),singleCell);
            }

        }


    }

    public static double getBoardWidth() {
        return BOARD_WIDTH;
    }

    public static double getBoardHeight() {
        return BOARD_HEIGHT;
    }

    /**
     * change the number displayed in one cell
     * @param loc
     * @param newNum
     */


    public void changeDisplayNum(int loc, Integer newNum){
        SingleCellGraphics singleCellGraphics = cellGraphicsMap.get(loc);
        singleCellGraphics.setNewNum(newNum);
    }
}
