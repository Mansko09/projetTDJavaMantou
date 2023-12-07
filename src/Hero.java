import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Hero extends AnimatedThing {
    private long lastUpdateTime;
    int maxIndex;
    private final int frameWidth = 75; // Width of each frame in the sprite sheet
    private final int frameHeight = 100; // Height of each frame in the sprite sheet
    private int currentFrame = 0; // Current frame index
    private final int totalFrames = 8; // Total number of frames in the sprite sheet

    public Hero(double x, double y, int numberOfLives, int maxIndex,  int windowHeight, int windowWidth, String fileName) {
        super(x, y, numberOfLives,maxIndex, windowHeight, windowWidth, fileName);
        getImageView().setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
    }
    @Override
    public void update(long time) {
        if ((time - lastUpdateTime)>=100_000_000L){
            changeFrameIndex();//Change sprite index logic
            lastUpdateTime=time;
        }

    }
    private void changeFrameIndex() {
        // Implement logic to change the sprite index according to your sprite sheet

        // Example: Increment the frame index by 1 (assuming it loops back to 0)
        currentFrame++;
        if (currentFrame > maxIndex) {
            currentFrame = 0;
        }
        int nextFrameX=currentFrame*frameWidth;
        getImageView().setViewport(new Rectangle2D(nextFrameX,0,frameWidth,frameHeight));
    }

}
