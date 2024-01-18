import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class Camera extends Parent {
    double x;
    double y;
    double velocityX=0.0;
    double accelerationX=0.0;
    double stiffness=1.0;//stiffness constant (k/m)
    double damping=1.2; //damping constant (f/m)

    private Hero hero;
    ImageView cameraView; //red rectangle to display

    public Camera(double x, double y) {
        this.x = x;
        this.y = y;
        Image image = new Image("file:C:/Users/mbeng/Documents/ENSEA_Mantou/2A/projetTDJavaMantou/files/red_rectangle.jpg");
        // Create an ImageView with the loaded image
        cameraView = new ImageView(image);
        cameraView.setFitWidth(200);
        cameraView.setFitHeight(100);


        //position the camera view
        cameraView.setLayoutX(x);
        cameraView.setLayoutY(y);

    }
    public void bindToHero(Hero hero){
        this.hero=hero;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return (getX() + "," + getY());

    }
    public void update(long time){
        if (hero != null) {
            // Spring force calculation
            double springForceX = stiffness * (hero.getX() - x);
            double dampingForceX = damping * velocityX;
            accelerationX = (springForceX - dampingForceX) / 1.0; // Assuming mass = 1.0;

            // Update velocity using Euler integration
            double deltaTime = time / 1_000_000.0; // Convert time to seconds
            velocityX += accelerationX * deltaTime;

            // Update position using Euler integration
            x += velocityX * deltaTime;
        }

        if (cameraView != null) {
            // Adjust camera's position to follow the hero (centering the hero in the view)
            if (hero != null) {
                //double offsetX = 1400 / 2.0; //1400=screen width
                double heroX = hero.getX();
                // Center the camera on the hero (adjust the camera's X position)
                cameraView.setLayoutX(heroX);
            }
        }

    }
}

