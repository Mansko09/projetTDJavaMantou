import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.security.cert.PolicyNode;
import java.util.ArrayList;

public class GameScene extends Scene {
    private ArrayList<StaticThing> lifePoints;
    private Camera camera;
    private StaticThing right;
    private StaticThing left;
    private StaticThing lifePoint;
    private Hero hero;
    int numberOfLives;

    public GameScene(Pane root, double width, double height, Camera camera, int numberOfLives) {
        super(root, width, height);
        this.camera = camera;
        this.left = new StaticThing(width, height, "file:C:/Users/mbeng/Documents/ENSEA_Mantou/2A/projetTDJavaMantou/files/desert.png");
        this.right = new StaticThing(width, height, "file:C:/Users/mbeng/Documents/ENSEA_Mantou/2A/projetTDJavaMantou/files/desert.png");

        //ImageView heroImageView = new ImageView(new Image("file:C:/Users/mbeng/Documents/ENSEA_Mantou/2A/projetTDJavaMantou/files/hero.png"));
        this.hero = new Hero(600, 300, 3, 6, 100, 90, "file:C:/Users/mbeng/Documents/ENSEA_Mantou/2A/projetTDJavaMantou/files/heros.png");

        //ajout des coeurs
        this.lifePoints=new ArrayList<StaticThing>();
        for (int i=0; i<numberOfLives;i++){
            this.lifePoint=new StaticThing(20,20,"file:C:/Users/mbeng/Documents/ENSEA_Mantou/2A/projetTDJavaMantou/files/heart_image.jpg");
            this.lifePoints.add(lifePoint);
        }


        //Hero's starting position
        hero.getImageView().setLayoutX(600);
        hero.getImageView().setLayoutY(250);
        hero.getImageView().setOpacity(1);
        if (left != null && right != null && hero.getImageView() != null) {
            root.getChildren().add(left.getImageView());
            root.getChildren().add(right.getImageView());
            root.getChildren().add(hero.getImageView());

        } else {
            System.out.println("Un des nœuds est null. Vérifiez le chargement des images.");
        }


        //render(width);
    }

    /*private void createHearts(int numberOfLives){
        double heartSize = 20; // Set the size of the hearts
        // Create and position hearts based on numberOfLives
        for (int i = 0; i < numberOfLives; i++) {
            ImageView heart = new ImageView("files/heart_image.png");
            heart.setFitWidth(heartSize);
            heart.setFitHeight(heartSize);
            heart.setLayoutX(i * (heartSize + 5)); // Adjust the spacing between hearts
            heart.setLayoutY(10); // Adjust the Y position of hearts as needed
            heartPane.getChildren().add(heart);
        }
    }
    // Method to update the displayed hearts based on the number of lives
    public void updateHearts(int remainingLives) {
        //heartPane.getChildren().clear(); // Clear existing hearts

        // Create hearts based on the updated remaining lives
        for (int i = 0; i < remainingLives; i++) {
            ImageView heart = new ImageView("heart_image.png");
            // Set size and position similar to createHearts() method
            // Add the heart ImageView to heartPane.getChildren()
        }
    }*/

  /*  public void setViewPos(double posX, double posY){
        this.imageView.setX(posX);
        this.imageView.setY(posY);
    }

    public void render(double width){
        //modify positions based on camera coordinates
        double cameraX=camera.getX();
        double cameraY=camera.getY();

        // Update positions of the left and right StaticThings based on the camera
        //double leftX = -cameraX;
        double leftY = -cameraY;
        left.getImageView().setX(leftX);
        left.getImageView().setY(leftY);

        double rightX = width - cameraX;
        double rightY = -cameraY;
        right.getImageView().setX(rightX);
        right.getImageView().setY(rightY);//
        double leftSizeX=this.left.getSizeX();
        double rightSizeX=this.right.getSizeX();
        this.left.setViewPos(-cameraX+(leftSizeX+rightSizeX)*floor((cameraX+rightSizeX)/(leftSizeX+rightSizeX)),-cameraY);
        this.right.setViewPos(-cameraX+leftSizeX+(leftSizeX+rightSizeX)*floor(cameraX/(leftSizeX+rightSizeX)),-cameraY);



    }*/

}
