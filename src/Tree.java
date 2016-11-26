import java.util.ArrayList;
import java.util.List;

/**
 * Created by angelica on 11/26/16.
 */
public class Tree {
    private TreeNode root;

    public Tree(){
        root = new TreeNode(null, -1);
    }

    public TreeNode getRoot (){
        return root;
    }
}
