import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class constructs a map. The keys are the location of cells, and the values are the cell containers.
 */

public class SolutionMap {

    /*
    This hashmap tracks the location of each Empty Cell.
     */
    private HashMap<Integer, CellContainer> nodesHolder;

    public SolutionMap(){

        nodesHolder = new HashMap<>(49);

    }

    public HashMap<Integer, CellContainer> getNodesHolder() {
        return nodesHolder;
    }

    public void addNodes(ArrayList<Integer> integerArrayList, int rowIndex, int columnIndex){

        Integer loc = rowIndex*100+columnIndex;

        if (nodesHolder.containsKey(loc)){
            CellContainer cellToChange = nodesHolder.get(loc);
            cellToChange.addPossibleValues(integerArrayList);
            nodesHolder.put(loc,cellToChange);
        }
        else {
            CellContainer newCell = new CellContainer(integerArrayList, rowIndex, columnIndex);
            nodesHolder.put(loc, newCell);
    //        System.out.println("oh");
        }

    }
    public boolean contain(Integer loc){
        return nodesHolder.containsKey(loc);
    }


    public CellContainer getNodeAt (int xLoc, int yLoc){

        return nodesHolder.get(xLoc*100+yLoc);
    }

    public void traverseTree(){
        for (Map.Entry<Integer, CellContainer> entry: nodesHolder.entrySet()){
           // String toPrint = "Level" + entry.getKey()[0]+","+entry.getKey()[1]+" {"+entry.getValue().getValue()+"}";
           // System.out.println(toPrint);
        }
    }

}
