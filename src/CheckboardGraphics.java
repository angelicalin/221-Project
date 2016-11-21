/**
 * Created by Leqi on 11/16/16.
 */

import acm.graphics.GCompound;
import java.awt.*;



public class CheckboardGraphics extends GCompound{
    public static final double BOARD_WIDTH = 350.0;
    public static final double BOARD_HEIGHT = 350.0;
    public static final double CELL_LENGTH = 50.0;
    private SingleCellGraphics singleCell;
    private double xPos = CELL_LENGTH;
    private double yPos = CELL_LENGTH;

    public CheckboardGraphics(double width, double height)  {

        //TODO:pass single cell's integer value


        /*
        singleCell = new SingleCellGraphics(0,0,0,  )
         */

    }

    public CheckboardGraphics(Color color) {
        this(BOARD_WIDTH, BOARD_HEIGHT);
    }


    public static double getBoardWidth() {
        return BOARD_WIDTH;
    }

    public static double getBoardHeight() {
        return BOARD_HEIGHT;
    }
}
