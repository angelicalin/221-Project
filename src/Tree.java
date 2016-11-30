import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by angelica on 11/26/16.
 */

public class Tree {
    private TreeNode root;
    /*
    This hashmap tracks the location of each treenode.
     */
    private HashMap<Integer, ArrayList<TreeNode>> nodesHolder;

    public Tree(){
        root = new TreeNode(null, -1);
        nodesHolder = new HashMap<>(49);
        init();
    }

    private void init(){
        ArrayList<TreeNode> rootArray = new ArrayList<>();
        rootArray.add(root);
        nodesHolder.put(0,rootArray);
    }

    public TreeNode getRoot (){
        return root;
    }

//    public void addChild(TreeNode parentNode, TreeNode childNode, int xLocOfChild, int yLocOfChild){
    public void addChild(TreeNode parentNode, TreeNode childNode, int levelInTree){
        parentNode.addChild(childNode);
//        if (nodesHolder.containsKey(xLocOfChild*100+yLocOfChild)){
//            ArrayList<TreeNode> arrayToChange = nodesHolder.get(xLocOfChild*100+yLocOfChild);
//            arrayToChange.add(childNode);
//            nodesHolder.put(xLocOfChild*100+yLocOfChild, arrayToChange);
//        }
//        else{
//            ArrayList<TreeNode> newArray = new ArrayList<> ();
//            newArray.add(childNode);
//            nodesHolder.put(xLocOfChild*100+yLocOfChild, newArray);
//        }
        if(nodesHolder.containsKey(levelInTree)){
            ArrayList<TreeNode> arrayToChange = nodesHolder.get(levelInTree);
            arrayToChange.add(childNode);
            nodesHolder.put(levelInTree, arrayToChange);
            //System.out.println("hi");
        }
        else{
            ArrayList<TreeNode> newArray = new ArrayList<>();
            newArray.add(childNode);
            nodesHolder.put(levelInTree,newArray);
            //System.out.println("oh");
        }
    }

//    public ArrayList<TreeNode> getNodeAt (int xLocofNode, int yLocofNode){
//        return nodesHolder.get(xLocofNode*100+yLocofNode);
//    }
    public ArrayList<TreeNode> getNodeAt (int level){
        return nodesHolder.get(level);
    }

    public String toString(){
        return "has" + nodesHolder.get(0).size();
    }

//    public void removeChildFrom(TreeNode parentNode, TreeNode childNodeToRemove, int xLocofNodetoRemove, int yLocofNodetoRemove){
//        parentNode.removeChild(childNodeToRemove);
//        ArrayList<TreeNode> arrayToChange = nodesHolder.get(xLocofNodetoRemove*100+yLocofNodetoRemove);
//        arrayToChange.remove(childNodeToRemove);
//        nodesHolder.put(xLocofNodetoRemove*100+yLocofNodetoRemove,arrayToChange);
//
//    }

}
