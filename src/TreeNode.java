import java.util.ArrayList;
import java.util.List;

/**
 * Created by angelica on 11/25/16.
 * This treenode constitue the Tree class.
 */
public class TreeNode {
    //TreeNode parent;
    int value;
    ArrayList<TreeNode> children = new ArrayList<>();
    public TreeNode(int value){
   //     this.parent = parent;
        this.value = value;
    }

    public void addChild (TreeNode childNode){
        children.add(childNode);
    }

  //  public TreeNode getParent() {
  //      return parent;
    //}

    public int getValue() {
        return value;
    }

    public ArrayList<TreeNode> getChildrenList() {
        return children;
    }

    public TreeNode getChildofIndex(int i){
        return children.get(i);
    }

    public void removeChild(TreeNode childNode){
        children.remove(childNode);
    }
}
