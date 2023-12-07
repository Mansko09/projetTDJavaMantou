import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

enum Behavior{
    RUNNING, JUMPING, SHOOTING, SHOOTING_JUMPING;
}
public abstract class AnimatedThing {
    //class as a mold for the heros and its oponents
    double x;
    double y;
    ImageView imageView;
    Behavior behavior;
    int numberOfLives;
    int windowWidth;
    int windowHeight;
    int offset;
    public AnimatedThing(double x, double y, int numberOfLives,int maxIndex, int windowHeight, int windowWidth, String fileName) {
        this.x = x;
        this.y = y;
        this.numberOfLives=numberOfLives;
        this.windowWidth = windowWidth;
        this.windowHeight=windowHeight;
        Image spriteSheet = new Image(fileName);
        this.imageView= new ImageView(spriteSheet);
        //set viewport for the first frame
        this.imageView.setViewport(new Rectangle2D(0,0,windowWidth,windowHeight));

    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getNumberOfLives() {
        return numberOfLives;
    }

}



