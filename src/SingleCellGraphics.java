import acm.graphics.GCompound;
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
    public SingleCellGraphics(int valueUp, int valueDown, boolean fillable){
        GRect squareCell = new GRect(50,50);
        GLine seperationLine = new GLine(0,0,50,50);
        this.add(seperationLine);
        this.add(squareCell);
        setCellColor(fillable);
    }

    private void setCellColor(boolean fillable){
        if (!fillable){
            this.setColor(Color.BLACK);

        }
    }
}
