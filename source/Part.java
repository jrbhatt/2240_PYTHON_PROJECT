import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
// Class: Part
//Description: Manages sprites

public class Part {

    private static Image Hu =  new Image("resources/Head_u.png");
    private static Image HD =  new Image("resources/Head_d.png");
    private static Image HL =  new Image("resources/Head_l.png");
    private static Image HR =  new Image("resources/Head_R.png");
    private static Image Part =  new Image("resources/cPart.png");
    private static ImageView PartI = new ImageView(HR);

    //Method: changeMovement
    //Descripton: Returns the ImageView of the Snake for the python direction given as a char
    public static ImageView changeMovement(char dir) {
        switch (dir) {
            case 'U':
                PartI = new ImageView(Hu);
                return PartI;
            case 'D':
                PartI = new ImageView(HD);
                return PartI;
            case 'L':
                PartI = new ImageView(HL);
                return PartI;
            case 'R':
                PartI = new ImageView(HR);
                return PartI;
            case 'S':
                PartI = new ImageView(Part);
                return PartI;
            default:
                return null;
        }
    }
}