import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StartScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Load the background image
        Image backgroundImage = new Image("file:C:/Users/mbeng/Documents/ENSEA_Mantou/2A/projetTDJavaMantou/files/desert.png");

        // Create an ImageView to display the background image
        ImageView backgroundImageView = new ImageView(backgroundImage);

        Button startButton = new Button("Start Game");
        startButton.setOnAction(event -> {
            // When the start button is clicked, launch the game
            GameScene gameScene = new GameScene(new Pane(), 1400, 400);
            Stage gameStage = new Stage();
            gameStage.setScene(gameScene);
            gameStage.show();

            // Close the start screen
            primaryStage.close();
        });

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(event -> {
            // When the quit button is clicked, exit the application
            System.exit(0);
        });

        HBox buttonBox = new HBox(10); // 10 is the spacing between nodes
        buttonBox.getChildren().addAll(startButton, quitButton);
        buttonBox.setAlignment(Pos.CENTER); // Center the HBox

        // StackPane to hold the background image and the button HBox
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, buttonBox);

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Start Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
