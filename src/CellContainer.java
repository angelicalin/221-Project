import java.util.*;

/**
 * Created by angelica on 11/25/16.
 * This treenode constitue the Tree class.
 */
public class CellContainer {
    ArrayList<Integer> possibleValues;
    int xLoc;
    int yLoc;
    Boolean visited;
    int visitNum = 0;
   // ArrayList<Integer> visitedValues;
    Integer firstValue;



    public CellContainer(ArrayList<Integer> possibleValues, int xLoc, int yLoc){

        this.possibleValues = possibleValues;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.visited = false;
        Collections.shuffle(possibleValues);
        firstValue = possibleValues.get(0);
     //   this.visitedValues = new ArrayList<Integer>();
    }



    public void setPossibleValues(ArrayList<Integer> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public ArrayList<Integer> getPossibleValue() {
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

    public void addPossibleValues(ArrayList<Integer> values){
        this.possibleValues = intersect(values,this.possibleValues);
        Collections.shuffle(possibleValues);
        this.firstValue = possibleValues.get(0);
    }

    public int accessAPossibleValue(){
        Integer result = possibleValues.remove(0);
        possibleValues.add(result);
        visitNum++;
        if (visitNum>20){
            Collections.shuffle(possibleValues);
            this.firstValue = possibleValues.get(0);
        }
        return result;
    }

    public boolean loopedOverAlready(){
        if (possibleValues.size()==1){
            return true;
        }
        return firstValue.equals(possibleValues.get(0));
    }

//    public ArrayList<Integer> getVisitedValues() {
//        return visitedValues;
//    }
//
//    public void setVisitedValues(ArrayList<Integer> visitedValues) {
//        this.visitedValues = visitedValues;
//    }
//
//    public void addVisitedValues(ArrayList<Integer> values){
//        this.visitedValues = intersect(values,this.visitedValues);
//    }

    private ArrayList<Integer> intersect (ArrayList<Integer> list1, ArrayList<Integer> list2){
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer t : list1){
            if (list2.contains(t)){
                list.add(t);
            }
        }
        return list;
    }




}