import java.util.ArrayList;
import java.util.List;

/**
 * Created by angelica on 11/25/16.
 */
public class TreeNode {
    TreeNode parent;
    int value;
    List<TreeNode> children = new ArrayList();
    public TreeNode(TreeNode parent, int value){
        this.parent = parent;
        this.value = value;
    }

    public void addChild (TreeNode childNode){
        children.add(childNode);
    }

    public TreeNode getParent() {
        return parent;
    }

    public int getValue() {
        return value;
    }

    public List<TreeNode> getChildrenList() {
        return children;
    }

    public TreeNode getChildofIndex(int i){
        return children.get(i);
    }
}
