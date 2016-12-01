import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by angelica on 11/26/16.
 */

public class SolutionMap {
    private TreeNode root;
    /*
    This hashmap tracks the location of each treenode.
     */
    private HashMap<Integer, ArrayList<TreeNode>> nodesHolder;

    public SolutionMap(){
        root = new TreeNode(null, -1);
        nodesHolder = new HashMap<>(49);
        init();
    }

    private void init(){
        ArrayList<TreeNode> rootArray = new ArrayList<>();
        rootArray.add(root);
        nodesHolder.put(-1,rootArray);
    }

    public TreeNode getRoot (){
        return root;
    }

    public void addChild(TreeNode parentNode, TreeNode childNode, int xLocOfChild, int yLocOfChild){
//    public void addChild(TreeNode parentNode, TreeNode childNode, int levelInTree){
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
        Integer levelInTree = xLocOfChild * 100 + yLocOfChild;
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
    public ArrayList<TreeNode> getNodeAt (int xLoc, int yLoc){
        return nodesHolder.get(xLoc * 100 + yLoc);
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
    public void traverseTree(){
        for (Map.Entry<Integer, ArrayList<TreeNode>> entry: nodesHolder.entrySet()){
            String toPrint = "Level" + entry.getKey()+" {";
            for(int i =0;i<entry.getValue().size();i++){
                toPrint = toPrint + entry.getValue().get(i).getValue() + ", ";
            }
            toPrint+="}";
            System.out.println(toPrint);
        }
    }

    public void removeChildofValue (int childValue, int xLocofChild, int yLocofChild){
        ArrayList<TreeNode> childrenList = nodesHolder.get(xLocofChild*100+yLocofChild);
        ArrayList<TreeNode> copyOfChildrenList = childrenList;
        System.out.println(childrenList.size());
        for (int i=0; i<childrenList.size();i++){
            TreeNode child = childrenList.get(i);
            if (child.getValue() == childValue){
                try {
                    copyOfChildrenList.remove(child);
                }
                catch (NullPointerException e){}
            }
        }
        nodesHolder.put(xLocofChild*100+yLocofChild, copyOfChildrenList);
    }
}
