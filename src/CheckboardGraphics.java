/**
 * Created by Leqi on 11/16/16.
 */

import acm.graphics.GCompound;



public class CheckboardGraphics extends GCompound{
    private static final double BOARD_WIDTH = 350.0;
    private static final double BOARD_HEIGHT = 350.0;
    private static final double CELL_LENGTH = 50.0;
    private static final int row_num_cells = 7;
    private SingleCellGraphics singleCell;


    public CheckboardGraphics(int [][] cell_values)  {

        for (int i = 0; i < row_num_cells; i++) {
            for (int j = 0; j < row_num_cells; j++){
                Integer VALUE_UP = cell_values[2*i][j];
                Integer VALUE_DOWN = cell_values[2*i+1][j];
                boolean fillable = true;

                if (VALUE_UP !=0 | (VALUE_DOWN != 0)) {
                    fillable = false;
                }
                singleCell = new SingleCellGraphics(VALUE_UP, VALUE_DOWN, fillable);

                double xPos = j*CELL_LENGTH;
                double yPos = i*CELL_LENGTH;
                add(singleCell, xPos, yPos);
            }

        }


    }



    public static double getBoardWidth() {
        return BOARD_WIDTH;
    }

    public static double getBoardHeight() {
        return BOARD_HEIGHT;
    }
}
