import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

    public void addValues(ArrayList<Integer> values){
        this.possibleValues = union(values,this.possibleValues);
    }

    private ArrayList<Integer> union (ArrayList<Integer> list1, ArrayList<Integer> list2){
        Set<Integer> set = new HashSet<>();
        set.addAll(list1);
        set.addAll(list2);
        return new ArrayList<>(set);
    }


}
