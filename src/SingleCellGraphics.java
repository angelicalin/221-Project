import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GRect;
import com.sun.deploy.util.BlackList;

import java.awt.*;

/**
 * Created by Leqi on 11/16/16.
 */
public class SingleCellGraphics extends GCompound{
    int valueUp;
    int valueDown;
    boolean fillable;
    GRect singleCell;
    public SingleCellGraphics(Integer valueUp, Integer valueDown, boolean fillable){
        GRect squareCell = new GRect(50,50);
        this.singleCell = squareCell;
        GLabel labelUp = new GLabel(valueUp.toString());
        GLabel labelDown = new GLabel(valueDown.toString());
        this.add(squareCell);
        this.add(labelDown,5,30);
        this.add(labelUp,30,5);
        this.fillable = fillable;
        this.valueDown = valueDown;
        this.valueUp = valueUp;
        setUpCell();

    }

    private void setUpCell(){
        if (!fillable&(valueUp==-1)&(valueDown==-1)){
            singleCell.setFilled(true);
            singleCell.setFillColor(Color.BLACK);
        }
        else if (!fillable){
            GLine seperationLine = new GLine(0,0,50,50);
            this.add(seperationLine);
        }
    }
}
