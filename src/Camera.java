import javafx.scene.Node;
import javafx.scene.Parent;
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
    Rectangle cameraView; //rectangle to represent the camera view

    public Camera(double x, double y) {
        this.x = x;
        this.y = y;
        //creation of the camera view rectangle
        cameraView=new Rectangle(300,100);
        cameraView.setStroke(Color.RED);
        cameraView.setFill(Color.TRANSPARENT);

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
        if (hero!=null){
            //spring force calculus
            double springForceX=stiffness*(hero.getX()-x);
            double dampingForceX=damping*velocityX;
            accelerationX=(springForceX-dampingForceX)/1.0; //assuming mass=1.0;

            //update position using Euler integration
            double deltaTime=time/1_000_000_000.0; //convert time to seconds
            x+=velocityX*deltaTime;
        }
        if (cameraView!=null){
            cameraView.setLayoutX(x);
            cameraView.setLayoutY(y);
        }

    }




}

    /*@Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }*/

