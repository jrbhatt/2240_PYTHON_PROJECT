import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

// Class: Home
// Description: It is  associated with the Home screen of the game
public class Home {
    //Variable Description:
    // root:  stores the Pane start screen
    private Pane root;

    public Home(){
        this.root = createLayout();
    }

    // Getter method for root
    public Pane getRoot(){
        return root;
    }


    //Method: createLayout
    // It builds game and returns the first screen of game
    private Pane createLayout() {
        root = new Pane();
        root.setPrefSize(Main.width, Main.height);

        // Logo of snake
        ImageView Logo = new ImageView(new Image("resources/snake.png"));
        Logo.setFitHeight(450);
        Logo.setPreserveRatio(true);
        Logo.setLayoutX(30);
        Logo.setLayoutY(130);

        // Controls diagram
        ImageView controls = new ImageView(new Image("resources/controls.png"));
        controls.setFitHeight(100);
        controls.setPreserveRatio(true);
        controls.setLayoutX(150);
        controls.setLayoutY(40);

        // Control instructions of the game
        Text keys = new Text("  Use arrow keys to direct the python \n Spacebar to pause / resume the game");
        keys.setFont(Font.font("Bahnschrift", FontWeight.BOLD, 20));
        keys.setFill(Color.BLACK);
        keys.setLayoutX(260);
        keys.setLayoutY(90);

        // Play button image
        ImageView play = new ImageView(new Image("resources/Playt.png"));
        play.setFitHeight(150);
        play.setPreserveRatio(true);
        play.setLayoutX(320);
        play.setLayoutY(170);

        // Click here instructions
        // Control instructions
        Text Here = new Text("Click to play");
        Here.setFont(Font.font("Bahnschrift", FontWeight.BOLD, 20));
        Here.setFill(Color.BLACK);
        Here.setLayoutX(343);
        Here.setLayoutY(348);

        // Background
        ImageView bcgImage = new ImageView(Main.bcgImage);

        // Add elements to the root
        root.getChildren().setAll(bcgImage, Logo, controls, keys, play, Here);

        return root;
    }
}

