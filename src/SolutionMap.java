import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by angelica on 11/26/16.
 */

public class SolutionMap {
    private CellContainer root;
    /*
    This hashmap tracks the location of each treenode.
     */
    private HashMap<int[], CellContainer> nodesHolder;

    public SolutionMap(){

        nodesHolder = new HashMap<>(49);

    }


    public void addNodes(ArrayList<Integer> integerArrayList, int xLoc, int yLoc){

        int[] loc =  new int []{xLoc,yLoc};


        CellContainer newCell = new CellContainer(integerArrayList,xLoc,yLoc);
        nodesHolder.put(loc, newCell);
            //System.out.println("oh");
    }


    public CellContainer getNodeAt (int xLoc, int yLoc){
        return nodesHolder.get(new int[]{xLoc,yLoc});
    }

    public void traverseTree(){
        for (Map.Entry<int[], CellContainer> entry: nodesHolder.entrySet()){
            String toPrint = "Level" + entry.getKey()+" {"+entry.getValue().getValue()+"}";
            System.out.println(toPrint);
        }
    }

}
