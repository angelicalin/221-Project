import java.util.ArrayList;

/**
 * Created by angelica on 11/25/16.
 * This treenode constitue the Tree class.
 */
public class CellContainer {
    ArrayList<Integer> possibleValues;
    int xLoc;
    int yLoc;
    Boolean visited;



    public CellContainer(ArrayList<Integer> possibleValues, int xLoc, int yLoc){

        this.possibleValues = possibleValues;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.visited = false;
    }



    public void setPossibleValues(ArrayList<Integer> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public ArrayList<Integer> getValue() {
        return possibleValues;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public void addAValue(Integer singleValue){
        this.possibleValues.add(singleValue);
    }

    public void addAValue(ArrayList<Integer> values){
        this.possibleValues.addAll(values);
    }


}
