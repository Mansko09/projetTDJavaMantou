import javafx.animation.AnimationTimer;
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
    private double backgroundSpeed=2.0;

    private AnimationTimer timer;

    public GameScene(Pane root, double width, double height, Camera camera, int numberOfLives) {
        super(root, width, height);
        this.camera = camera;
        this.left = new StaticThing(width, height, "file:C:/Users/mbeng/Documents/ENSEA_Mantou/2A/projetTDJavaMantou/files/desert.png");
        this.right = new StaticThing(width, height, "file:C:/Users/mbeng/Documents/ENSEA_Mantou/2A/projetTDJavaMantou/files/desert.png");

        //ImageView heroImageView = new ImageView(new Image("file:C:/Users/mbeng/Documents/ENSEA_Mantou/2A/projetTDJavaMantou/files/hero.png"));
        this.hero = new Hero(600, 300, 3, 6, 100, 90, "file:C:/Users/mbeng/Documents/ENSEA_Mantou/2A/projetTDJavaMantou/files/heros.png");

        //ajout des coeurs
        double heartSize = 20;
        this.lifePoints=new ArrayList<StaticThing>();
        for (int i=0; i<numberOfLives;i++){
            this.lifePoint=new StaticThing(20,20,"file:C:/Users/mbeng/Documents/ENSEA_Mantou/2A/projetTDJavaMantou/files/heart_image.png");
            lifePoint.getImageView().setLayoutX(20 + i * (heartSize + 5));
            lifePoint.getImageView().setLayoutY(20);
            this.lifePoints.add(lifePoint);
        }

        left.getImageView().setLayoutX(0);
        right.getImageView().setLayoutX(width);
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
        // Ajout des coeurs à la scène
        for (StaticThing heart : lifePoints) {
            root.getChildren().add(heart.getImageView());
        }
        startBackgroundScrolling();
        //render(width);

    }

    private void startBackgroundScrolling() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                updateBackgroundPosition();
                camera.update(time);
                hero.update(time);
            }
        };
        timer.start();
    }


    private void updateBackgroundPosition() {
        double newLeftBackgroundX = left.getImageView().getLayoutX() - backgroundSpeed;
        double newRightBackgroundX = right.getImageView().getLayoutX() - backgroundSpeed;

        // Check if backgrounds moved completely out of the scene
        if (newLeftBackgroundX + left.getImageView().getFitWidth() <= 0) {
            newLeftBackgroundX = newRightBackgroundX + right.getImageView().getFitWidth();
        }
        if (newRightBackgroundX + right.getImageView().getFitWidth() <= 0) {
            newRightBackgroundX = newLeftBackgroundX + left.getImageView().getFitWidth();
        }
        left.getImageView().setLayoutX(newLeftBackgroundX);
        right.getImageView().setLayoutX(newRightBackgroundX);
    }
    private void update(long time) {
        // Call the hero's update method
        hero.update(time);
        // Call the camera's update method
        camera.update(time);
    }

    

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
