import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.List;

public class Hero extends AnimatedThing {
    private long lastUpdateTime;
    int maxIndex;
    private int currentFrameIndex=0;
    int frameWidth = 80; // Width of each frame in the sprite sheet
    int frameHeight = 100; // Height of each frame in the sprite sheet
    int totalFrames = 6; // Total number of frames in the sprite sheet
    private List<Integer> listOfHeroFrames;
    private double speed;//constant speed for the hero
    private double gravity = 0.5;
    private double jumpForce = -12;
    private double velocityY=0;

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
        this.speed=speed;
        this.gravity=gravity;
        this.jumpForce=jumpForce;
    }
    //code to display the different starting positions of the hero
    public void setFrameIndex(int frameIndexToShow) {
        int frameWidth = 75; // Width of each frame in the sprite sheet
        int frameHeight = 100; // Height of each frame in the sprite sheet
        int frameX;
        int distanceBtwFrames;
        if (frameIndexToShow==0){
            frameX=listOfHeroFrames.get(0);
        }
        else {
            switch (frameIndexToShow) {
                case 1:
                    // Calculate the x-coordinate of the frame to display (based on frameIndexToShow)
                    frameX= listOfHeroFrames.get(1);
                    break;
                case 2:
                    // Calculate the x-coordinate of the frame to display (based on frameIndexToShow)
                    frameX = listOfHeroFrames.get(2);
                    break;
                case 3:
                    // Calculate the x-coordinate of the frame to display (based on frameIndexToShow)
                    frameX = listOfHeroFrames.get(3);
                    break;
                case 4:
                    // Calculate the x-coordinate of the frame to display (based on frameIndexToShow)
                    frameX =listOfHeroFrames.get(4);
                    break;
                case 5:
                    // Calculate the x-coordinate of the frame to display (based on frameIndexToShow)
                    frameX = listOfHeroFrames.get(5);
                    break;
                default:
                    frameX = 0;
                    System.out.println("Problem with the frame index0");
            }
        }
        // Set the viewport to display the specified frame
        getImageView().setViewport(new Rectangle2D(frameX, 0, frameWidth, frameHeight));
    }
    @Override
    public void update(long time) {
        // Update the frame index for the running animation
        setFrameIndex(currentFrameIndex);
        currentFrameIndex = (currentFrameIndex + 1) % getTotalFrames();
        //apply gravity to the Y velocity to simulate falling
        velocityY+=gravity;
        //update hero's position in the Y direction based on the velocity
        double newY=getImageView().getLayoutY()+velocityY;
        // Ensure the Hero cannot go below pixel 250
        if (newY > 250) {
            newY = 250;
            velocityY = 0; // Stop falling when reaching the ground
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
        }
    }


}
