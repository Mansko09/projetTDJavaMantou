import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.List;

public class Hero extends AnimatedThing {
    private long lastUpdateTime;
    int maxIndex;
    private int currentFrameIndex=0;
    int frameWidth = 80; // Width of each frame in the sprite sheet
    int frameHeight = 100; // Height of each frame in the sprite sheet
    int totalFrames = 8; // Total number of frames in the sprite sheet
    private List<Integer> listOfHeroFrames;
    private double speed;//constant speed for the hero
    private double gravity = 0.5;
    private double jumpForce = -12; //aka the height of the jump
    private double velocityY=0;
    private boolean isJumping = false;

    public Hero(double x, double y, int numberOfLives, int maxIndex,  int windowHeight, int windowWidth, String fileName, double gravity, double jumpForce) {
        super(x, y, numberOfLives,maxIndex, windowHeight, windowWidth, fileName);
        listOfHeroFrames = new ArrayList<>();
        int frame0=0;
        int frame1=frameWidth + 15;
        int frame2=2*frameWidth + 20;
        int frame3= 3 * frameWidth + 25;
        int frame4=4 * frameWidth +30 ;
        int frame5= 5 * frameWidth + 25;
        listOfHeroFrames.add(frame0);
        listOfHeroFrames.add(frame1);
        listOfHeroFrames.add(frame2);
        listOfHeroFrames.add(frame3);
        listOfHeroFrames.add(frame4);
        listOfHeroFrames.add(frame5);
        int jumping0=0;
        int running1=frameWidth+15;
        listOfHeroFrames.add(jumping0);
        listOfHeroFrames.add(running1);

        this.speed=speed;
        this.gravity=gravity;
        this.jumpForce=jumpForce;
    }
    //code with the different starting positions of the hero
    public void setFrameIndex(int frameIndexToShow) {
        int frameWidth = 75; // Width of each frame in the sprite sheet
        int frameHeight = 100; // Height of each frame in the sprite sheet
        int frameX=0;
        int frameY=0;
        if (frameIndexToShow==0){
            frameX=listOfHeroFrames.get(0);
        }
        else {
            if (frameIndexToShow >0 && frameIndexToShow < getTotalFrames()) {
                frameX = listOfHeroFrames.get(frameIndexToShow);
                if (frameIndexToShow >= 6) {
                    frameY = 160;
                }
            }
        }
        // Set the viewport to display the specified frame
        getImageView().setViewport(new Rectangle2D(frameX, frameY, frameWidth, frameHeight));
    }
    @Override
    public void update(long time) {
        // Update the frame index for the running animation if not jumping
        if (!isJumping) {
            setFrameIndex(currentFrameIndex);
            currentFrameIndex = (currentFrameIndex + 1) % 6; // because there are only 6 frames for running
        }

        // Apply gravity to the Y velocity to simulate falling
        velocityY += gravity ;

        // Update hero's position in the Y direction based on the velocity
        double newY = getImageView().getLayoutY() + velocityY;

        // Ensure the Hero cannot go below pixel 250
        if (newY > 250) {
            newY = 250;
            velocityY = 0; // Stop falling when reaching the ground
            isJumping = false; // Set jumping state to false
            setFrameIndex(0); // Display default frame when landing (adjust as needed)
        }

        // Check if the Hero is jumping or falling
        if (velocityY < 0) { // If the Hero is moving upwards (jumping)
            setFrameIndex(6); // Display jumping0 frame
            isJumping = true; // Set jumping state to true
        } else if (velocityY >= 0 && isJumping) { // If the Hero is falling
            setFrameIndex(7); // Display jumping falling frame
        } else if (!isJumping) { // If not jumping or falling, switch back to running animation
            setFrameIndex(currentFrameIndex);
            currentFrameIndex = (currentFrameIndex + 1) % 6; // because there are only 6 frames for running
        }

        // Set the new Y position for the Hero
        getImageView().setLayoutY(newY);
    }
    private boolean isOnGround() {
        // Check if the Hero is on the ground
        return getImageView().getLayoutY() == 250;
    }
    public int getTotalFrames() {
        return totalFrames;
    }
    //method to update the Hero's position based on constant speed
    public void move(double time){
        double newX=getImageView().getLayoutX() +speed*time;
        getImageView().setLayoutX(newX);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void jump(){
        if(isOnGround()){
            velocityY=jumpForce;
            isJumping = true; // Set jumping state to true when jumping
            setFrameIndex(6); // Display jumping0 frame when jumping starts (adjust as needed)
        }
    }


}
