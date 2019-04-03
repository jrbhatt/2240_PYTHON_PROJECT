import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
//CClass: Python
// Description: Represents the game's python logically as a list of Nodes
public class Python {
    // Variables
    // python and ObservableList of nodes to store ImageViews of the snake and their positions
    private ObservableList<Node> python;

    // Constructuor to initialize list of nodes
    public Python(Group Python) {
        this.python = Python.getChildren(); // Initialize nodes
    }

    //Getter method for python
    public ObservableList<Node> getPython() {
        return python;
    }
}