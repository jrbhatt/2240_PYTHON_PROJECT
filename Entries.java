// Class: Entries
// Description: it showes window to the user that ask for their initials
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

public class Entries {
    // Variable Description:
    // username:  it stores a String containing username
    // data: stores a Pair of a String array of the top players and their scores
    private String username = "ABC";
    private Pair<String[], int[]> data;

    // Method: display
    // Description: it shows a window that ask user to enter their initials updates and displays the high top score using the DBManager class. It also displays score on the exit.
    // An instance of Notifier is used to validate user's input.
    public String display(String title, String message, int score) {
        Stage display = new Stage();
        display.initModality(Modality.APPLICATION_MODAL);
        display.setTitle(title);
        display.setMinWidth(250);

        Label Message = new Label();
        Message.setText(message);

        TextField Initials = new TextField("ABC");

        // Limit input to maximum three characters
        Initials.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (Initials.getText().length() > 3) {
                    Initials.setText(Initials.getText().substring(0, 3));
                }
            }
        });

        // Limit input to uppercase letters
        Initials.setTextFormatter(new TextFormatter<Object>(e -> {
            e.setText(e.getText().toUpperCase());
            return e;
        }));

        // Exit and show high scores
        Button OK = new Button("Ok");
        OK.setOnAction(e -> {
            if (proofEntry(Initials, Initials.getText())) {
                username = Initials.getText();
                display.close();
                DatabaseControl.insert(Initials.getText(), score);
                data = DatabaseControl.getPoints();
                new TopScores().display(data.getKey(), data.getValue());
            }
        });

        // Layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(Message, Initials, OK);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20, 20, 20, 20));

        Scene scene = new Scene(layout);
        display.setScene(scene);
        display.show();
        return username;
    }

    // Method: proofEntry
    // This method returns true if the TextField contains only letters and exactly three initials
    // If the above condition is not met it resets the TextField while displaying the error message
    private boolean proofEntry(TextField initials, String text) {
        String regex="[a-zA-Z]+";
        if (text.matches(regex) && text.length() == 3) return true;
        else {
            new Notifier().display("Error", "Please Enter Three Letters Only");
            initials.setText("ABC");
            initials.requestFocus();
            return false;
        }
    }
}

