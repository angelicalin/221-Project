/**
 * Created by angelica on 11/16/16.
 */
public class SolutionCell {

    int x;
    int y;
    int value;
    boolean isRowSum = false;
    int numToAdd = 1;


    public SolutionCell(int x, int y, int value, boolean isRowSum){
        this.value = value;
        this.x=x;
        this.y=y;
        this.isRowSum = isRowSum;
    }

    public int[] getLoc(){
        return new int[]{x,y};
    }
    public void incrementNumToAdd(){
        numToAdd++;
    }
    public int getNumToAdd(){
        return numToAdd;
    }


    public int getRowIndex() {
        return x;
    }

    public void setRowIndex(int x) {
        this.x = x;
    }

    public int getColumnIndex() {
        return y;
    }

    public void setColumnIndex(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}