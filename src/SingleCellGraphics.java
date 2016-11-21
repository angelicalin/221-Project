import acm.graphics.GRect;
import com.sun.deploy.util.BlackList;

import java.awt.*;

/**
 * Created by Leqi on 11/16/16.
 */
public class SingleCellGraphics extends GRect{

    public SingleCellGraphics(double xLoc, double yLoc, int value, boolean fillable){
        super(xLoc,yLoc,50,50);
        setFilled(true);
        setCellColor(fillable);
    }

    private void setCellColor(boolean fillable){
        if (!fillable){
            this.setColor(Color.BLACK);

        }
    }
}
