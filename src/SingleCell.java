/**
 * Created by angelica on 11/16/16.
 */
public class SingleCell {

    int x;
    int y;
    int value;
    boolean fillable = false;

    public SingleCell(int x, int y, int value, boolean fillable){
        this.value = value;
        this.x=x;
        this.y=y;
        this.fillable = fillable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleCell that = (SingleCell) o;

        if (x != that.x) return false;
        if (y != that.y) return false;
        return value == that.value;

    }

    public boolean isUnfillable(){
        return fillable;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
