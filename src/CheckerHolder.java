import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.HashMap;

/**
 * Created by angelica on 11/16/16.
 */
public class CheckerHolder {
    private HashMap checkHolderMap;

    public CheckerHolder(){
        checkHolderMap = new HashMap();
    }

    public SingleCell addSingleCell(SingleCell c){
        checkHolderMap.put(c.getX()*10000+c.getY(),c);
        return c;
    }

    public SingleCell readSingleCell(int xLoc, int yLoc){
        SingleCell result = (SingleCell) checkHolderMap.get(xLoc*1000+yLoc);
        return result;
    }

    public void removeSingleCell(int xLoc, int yLoc){
        checkHolderMap.remove(xLoc*1000+yLoc);
    }


}
