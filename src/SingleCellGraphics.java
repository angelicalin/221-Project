import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by Leqi on 11/16/16.
 */
public class SingleCellGraphics extends GCompound{
    int valueUp;
    int valueDown;
    boolean fillable;
    GRect singleCell;
    GLabel up;
    GLabel down;
    public SingleCellGraphics(Integer valueUp, Integer valueDown, boolean fillable){
        GRect squareCell = new GRect(50,50);
        this.singleCell = squareCell;
        GLabel labelUp = new GLabel(valueUp.toString());
        this.up = labelUp;
        GLabel labelDown = new GLabel(valueDown.toString());
        this.down = labelDown;
        this.add(squareCell);
        this.add(labelDown,5,45);
        this.add(labelUp,30,15);
        this.fillable = fillable;
        this.valueDown = valueDown;
        this.valueUp = valueUp;
        setUpCell();

    }
    public void setNewNum(Integer newNum){
        up.setVisible(true);
        up.setLabel(newNum.toString());
    }
    private void setUpCell(){
        if (!fillable&(valueUp==-1)&(valueDown==-1)){
            singleCell.setFilled(true);
            singleCell.setFillColor(Color.cyan);
        }
        else if (!fillable){
            GLine seperationLine = new GLine(0,0,50,50);
            this.add(seperationLine);
            singleCell.setFilled(true);
            singleCell.setFillColor(Color.PINK);
        }
        if ((valueDown==0|valueDown==-1)){
            down.setVisible(false);
        }
        if (valueUp==0|valueUp == -1){
            up.setVisible(false);
        }
    }
}
