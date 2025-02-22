package yeyAI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private YeyAI yeyAI;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image aiImage = new Image(this.getClass().getResourceAsStream("/images/yeyAI.jpg"));

    @FXML
    public void initialize() { scrollPane.vvalueProperty().bind(dialogContainer.heightProperty()); }

    /** Injects the Duke instance */
    public void setyeyAI(YeyAI y) {
        assert y != null : "YeyAI instance cannot be null!";
        yeyAI = y;

        String response = yeyAI.getResponse("hello");
        dialogContainer.getChildren().add(DialogBox.getAIDialog(response, aiImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userInput.getText() != null : "User input cannot be null!";

        String input = userInput.getText();
        assert !input.isEmpty() : "User input should not be empty!";

        String response = yeyAI.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAIDialog(response, aiImage)
        );
        userInput.clear();

        // If the user types "bye", delay for 2 seconds, then exit
        if (input.trim().equalsIgnoreCase("bye")) {
            new Thread(() -> {
                try {
                    yeyAI.saveTasks();
                    Thread.sleep(2000); // 2-second delay
                    Platform.runLater(() -> {
                        Stage stage = (Stage) dialogContainer.getScene().getWindow();
                        stage.close(); // Close the window
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}