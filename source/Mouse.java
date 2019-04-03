import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//Class Name: Food
// Description: This class creates a food sprite and gives it a position in the game


public class Mouse{
    // Varible Description:
    // mouseImage: stores an image of the snake food which is mouse
    // mouseI: stores an ImageView of mouseImage which is Mouse
    // psition3: stores an int xPos for position
    // postition_3: stores an int yPos for position
    private final Image mouseImage =  new Image("resources/Mouse.png");
    private final ImageView mouseI;
    private int position3, position_3;

    //Constructor that initialize position
    public Mouse(Python python) {
        this.mouseI = new ImageView(mouseImage);
        move(python);
    }

    // Method : move
    // This method finds a new position for the food and calls againstPython to checck its position against the python
    public void move(Python python) {
        this.position3 = ((int)(Math.random()*(Main.width-Main.square))/Main.square * Main.square);
        this.position_3 = ((int)(Math.random()*(Main.height-Main.square))/Main.square * Main.square);
        if (this.position_3 == 0) this.position_3 = position_3 +40;
        getFImage().setFitHeight(40);
        getFImage().setPreserveRatio(true);
        getFImage().setTranslateX(position3);
        getFImage().setTranslateY(position_3);
        if (this.position_3 == 0) this.position_3 = position_3 +40;
        getFImage().setFitHeight(40);
        getFImage().setPreserveRatio(true);
        getFImage().setTranslateX(position3);
        getFImage().setTranslateY(position_3);
        if (againstPython(python)) {
            move(python);
        }
    }

    //Method: againstPython
    // This method checks the food's position in the game against the snake
    public boolean againstPython(Python python) {
        for (Node part : python.getPython()) {
            if (mouseI.getTranslateX() == part.getTranslateX()
                    && mouseI.getTranslateY() == part.getTranslateY()) {
                return true;
            }
        }
        return false;
    }

    // Getter for the mouseI
    public ImageView getFImage() {
        return mouseI;
    }
}