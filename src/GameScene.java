import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class GameScene extends Scene {
    private ArrayList<StaticThing> lifePoints;
    private Camera camera;
    private StaticThing right;
    private StaticThing left;
    private StaticThing lifePoint;
    private Hero hero;
    int numberOfLives;
    boolean isRunning=false;
    private double backgroundSpeed=3;
    private int currentFrame = 0;
    private AnimationTimer timer;
    private ArrayList<Foe> listOfFoes;

    public GameScene(Pane root, double width, double height) {
        super(root, width, height);
        this.numberOfLives=3;
        this.left = new StaticThing(width, height, "file:C:/Users/mbeng/Documents/ENSEA_Mantou/2A/projetTDJavaMantou/files/desert.png");
        this.right = new StaticThing(width, height, "file:C:/Users/mbeng/Documents/ENSEA_Mantou/2A/projetTDJavaMantou/files/desert.png");
        int frameWidth = 75; // Width of each frame in the sprite sheet
        int frameHeight = 100; // Height of each frame in the sprite sheet


        // Calculate the x-coordinate of the i-th frame (assuming 0-based indexing)
        //int frameIndexToShow = 5;
        this.hero = new Hero(400, 250, 3, 6, frameHeight, frameWidth, "file:C:/Users/mbeng/Documents/ENSEA_Mantou/2A/projetTDJavaMantou/files/heros.png");
        // Set the desired frame index to display (ex: 3 for the 4th frame)
        //hero.setFrameIndex(frameIndexToShow);
        //création coeurs
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
        hero.getImageView().setLayoutX(400);
        hero.getImageView().setLayoutY(250);
        hero.getImageView().setOpacity(1);
        // Create a camera slightly off from the hero
        camera = new Camera(hero.getImageView().getLayoutX() - 50, 250);
        camera.bindToHero(hero);
        if (left != null && right != null && hero.getImageView() != null) {
            root.getChildren().add(left.getImageView());
            root.getChildren().add(right.getImageView());
            root.getChildren().add(hero.getImageView());

        } else {
            System.out.println("Un des nœuds est null. Vérifiez le chargement des images.");
        }
       /* if (camera != null && camera.cameraView != null) {
            root.getChildren().add(camera.cameraView);
        } else {
            System.out.println("Camera or cameraView is null.");
        }*/
        // Ajout des coeurs à la scène
        for (StaticThing heart : lifePoints) {
            root.getChildren().add(heart.getImageView());
        }
        //hero.setFrameIndex(1); //to display the different starting positions
        startBackgroundScrolling();
        //render(width);
        // Initialize the ArrayList of Foes
        listOfFoes = new ArrayList<>();
        // Create a single Foe and add it to the ArrayList
        Foe foe = new Foe(500, 250, 1, 6, frameHeight, frameWidth, "file:C:/path/to/foe_sprite_sheet.png");
        listOfFoes.add(foe);
        // Add the Foe's ImageView to the scene
        root.getChildren().add(foe.getImageView());
        //event to press/release key
        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                hero.jump();
            } else if (event.getCode() == KeyCode.LEFT) {
                // Set the left movement flag when the left arrow key is pressed
                hero.setMovingLeft(true);
            } else if (event.getCode() == KeyCode.RIGHT) {
                // Set the right movement flag when the right arrow key is pressed
                hero.setMovingRight(true);
            }
        });
        this.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                // Unset the left movement flag when the left arrow key is released
                hero.setMovingLeft(false);
            } else if (event.getCode() == KeyCode.RIGHT) {
                // Unset the right movement flag when the right arrow key is released
                hero.setMovingRight(false);
            }
        });

    }
    private void startBackgroundScrolling() {
        final long[] startNanoTime = {System.nanoTime()};
        final double frameDuration =0.12; // Temps en secondes entre chaque changement de frame
        timer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                updateBackgroundPosition();
                double elapsedTime = (currentNanoTime - startNanoTime[0]) / 1_000_000_000.0; // Temps écoulé en secondes
                if (elapsedTime > frameDuration) {
                    update((long) elapsedTime);
                    startNanoTime[0] = currentNanoTime;
                }
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
        camera.update(time);
        hero.update(time);


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
