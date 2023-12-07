import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// ... (import statements)

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TD PROJECT : RUNNER");
        primaryStage.setResizable(false);
        Camera camera = new Camera(0,0);
        Group root= new Group();
        Pane pane = new Pane(root);
        GameScene gameScene=new GameScene(pane,1400,400,camera, 3);
        primaryStage.setScene(gameScene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }



}


