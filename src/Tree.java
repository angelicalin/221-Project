import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by angelica on 11/26/16.
 */
public class Tree {
    private TreeNode root;
    private HashMap<Integer, ArrayList<TreeNode>> nodesHolder;

    public Tree(){
        root = new TreeNode(null, -1);
        nodesHolder = new HashMap(49);
    }

    public TreeNode getRoot (){
        return root;
    }

    public void addChild(TreeNode parentNode, TreeNode childNode, int xLocOfChild, int yLocOfChild){
        parentNode.addChild(childNode);
        if (nodesHolder.containsKey(xLocOfChild*100+yLocOfChild)){
            ArrayList<TreeNode> arrayToChange = nodesHolder.get(xLocOfChild*100+yLocOfChild);
            arrayToChange.add(childNode);
            nodesHolder.put(xLocOfChild*100+yLocOfChild, arrayToChange);
        }
        else{
            ArrayList<TreeNode> newArray = new ArrayList();
            newArray.add(childNode);
            nodesHolder.put(xLocOfChild*100+yLocOfChild, newArray);
        }
    }

    public ArrayList<TreeNode> getNodeAt (int xLocofNode, int yLocofNode){
        return nodesHolder.get(xLocofNode*100+yLocofNode);
    }

    public void removeChildFrom(TreeNode parentNode, TreeNode childNodeToRemove, int xLocofNodetoRemove, int yLocofNodetoRemove){
        parentNode.removeChild(childNodeToRemove);
        ArrayList<TreeNode> arrayToChange = nodesHolder.get(xLocofNodetoRemove*100+yLocofNodetoRemove);
        arrayToChange.remove(childNodeToRemove);
        nodesHolder.put(xLocofNodetoRemove*100+yLocofNodetoRemove,arrayToChange);

    }

}
