import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by angelica on 11/26/16.
 */
public class Tree {
    private TreeNode root;
    private HashMap nodesHolder;

    public Tree(){
        root = new TreeNode(null, -1);
        nodesHolder = new HashMap(49);
    }

    public TreeNode getRoot (){
        return root;
    }

    public void addChild(TreeNode parentNode, TreeNode childNode, int xLocOfChild, int yLocOfChild){
        parentNode.addChild(childNode);

        nodesHolder.put()
    }

    public void removeChildFrom(TreeNode parentNode, TreeNode childNodeToRemove){
        parentNode.removeChild(childNodeToRemove);
    }

}
