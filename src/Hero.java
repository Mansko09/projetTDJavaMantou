import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Hero extends AnimatedThing {
    int Index = 0;
    private int currentFrameIndex = 0;
    int frameWidth = 80; // Width of each frame in the sprite sheet for running
    int frameRSWidth = 75; // Width of each frame in the sprite sheet for shooting
    int frameHeight = 100; // Height of each frame in the sprite sheet
    int totalFrames = 13; // Total number of frames in the sprite sheet
    private List<Integer> listOfHeroFrames;
    private List<Integer> listOfHeroShooting;
    private double speed;//constant speed for the hero
    private double gravity = 0.8;
    private double jumpForce = -20; //aka the height of the jump
    private double velocityY = -15;
    private boolean isJumping = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;
    private boolean isShooting = false;
    private boolean isShootingAnimationInProgress=false;

    public Hero(double x, double y, int numberOfLives, int maxIndex, int windowHeight, int windowWidth, String fileName) {
        super(x, y, numberOfLives, maxIndex, windowHeight, windowWidth, fileName);
        //data for making the hero run
        listOfHeroFrames = new ArrayList<>();
        int frame0 = 0;
        int frame1 = frameWidth + 15;
        int frame2 = 2 * frameWidth + 20;
        int frame3 = 3 * frameWidth + 25;
        int frame4 = 4 * frameWidth + 30;
        int frame5 = 5 * frameWidth + 25;
        listOfHeroFrames.add(frame0);
        listOfHeroFrames.add(frame1);
        listOfHeroFrames.add(frame2);
        listOfHeroFrames.add(frame3);
        listOfHeroFrames.add(frame4);
        listOfHeroFrames.add(frame5);
        int jumping0 = 0;
        int jumping1 = frameWidth + 15;
        listOfHeroFrames.add(jumping0);
        listOfHeroFrames.add(jumping1);
        //data for making the hero shoot
        listOfHeroShooting = new ArrayList<>();
        int rands0 = 0; //run and shoot
        int rands1 = frameRSWidth;
        int rands2 = 2 * frameRSWidth + 15;
        int rands3 = 3 * frameRSWidth + 25;
        int rands4 = 4 * frameRSWidth + 40;
        int rands5 = 5 * frameRSWidth + 55;
        listOfHeroShooting.add(rands0);
        listOfHeroShooting.add(rands1);
        listOfHeroShooting.add(rands2);
        listOfHeroShooting.add(rands3);
        listOfHeroShooting.add(rands4);
        listOfHeroShooting.add(rands5);
        this.speed = speed;
    }

    //code with the different starting positions of the hero
    public void setFrameIndex(int frameIndexToShow) {
        int frameWidth = 75; // Width of each frame in the sprite sheet
        int frameHeight = 100; // Height of each frame in the sprite sheet
        int frameX = 0;
        int frameY = 0;
        if (frameIndexToShow == 0) {
            frameX = listOfHeroFrames.get(0);
        }
        else {
            if (frameIndexToShow > 0 && frameIndexToShow <= getTotalFrames()) {
                if (frameIndexToShow < 6) {
                    frameX = listOfHeroFrames.get(frameIndexToShow);
                } else if (frameIndexToShow < 8 && frameIndexToShow >= 6) {
                    frameY = 160;
                    frameX = listOfHeroFrames.get(frameIndexToShow);
                }
            }
        }
        // Set the viewport to display the specified frame
        getImageView().setViewport(new Rectangle2D(frameX, frameY, frameWidth, frameHeight));
    }
    public void setFrameIndexShooting(int frameIndexToShow) {
        int frameWidth = 75; // Width of each frame in the sprite sheet
        int frameHeight = 100; // Height of each frame in the sprite sheet
        int frameX = 0;
        int frameY = 330;
        if (frameIndexToShow == 0) {
            frameX = listOfHeroShooting.get(0);
        }
        else {
            if (frameIndexToShow > 0 && frameIndexToShow <= getTotalFrames()) {
                if (frameIndexToShow < 6) {
                    frameX = listOfHeroShooting.get(frameIndexToShow);
                }
            }
        }
        // Set the viewport to display the specified frame
        getImageView().setViewport(new Rectangle2D(frameX, frameY, frameWidth, frameHeight));
    }

    @Override
    public void update(long time) {
        if (isShooting) {
            shoot();
        }
        // Update the frame index for the running animation if not jumping
        if (!isJumping && !isShooting) {
            setFrameIndex(currentFrameIndex);
            currentFrameIndex = (currentFrameIndex + 1) % 6; // Loop through frames
        }
        // Check if moving left or right and adjust X position
        if (isMovingLeft){
            moveLeft();
        } else if (isMovingRight) {
            moveRight();
        }

        // Apply gravity to the Y velocity to simulate falling
        velocityY += gravity + 10;

        // Update hero's position in the Y direction based on the velocity
        double newY = getImageView().getLayoutY() + velocityY;

        // Ensure the Hero cannot go below pixel 250
        if (newY > 250) {
            newY = 250;
            velocityY = 0; // Stop falling when reaching the ground
            isJumping = false; // Set jumping state to false
            //setFrameIndex(0); // Display default frame when landing (adjust as needed)
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

    //methods to set movement flags
    public void setMovingLeft(boolean movingLeft) {
        isMovingLeft = movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        isMovingRight = movingRight;
    }

    private boolean isOnGround() {
        // Check if the Hero is on the ground
        return getImageView().getLayoutY() == 250;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void jump() {
        if (isOnGround()) {
            velocityY = jumpForce - 30;
            isJumping = true; // Set jumping state to true when jumping
            setFrameIndex(6); // Display jumping0 frame when jumping starts
        }
    }

    public void moveLeft() {
        // Adjust the hero's X position to move left
        double newX = getImageView().getLayoutX() - 20; // Adjust the movement speed as needed

        // Check if the new X position is within the left boundary
        if (newX >= 0) {
            getImageView().setLayoutX(newX);
            // Flip the hero's image horizontally
            getImageView().setScaleX(-1);
        }
    }

    public void moveRight() {
        double newX = getImageView().getLayoutX() + 20; // Adjust the movement speed as needed;
        // Check if the new X position is within the right boundary
        if (newX <= (1400 - 100)) {
            getImageView().setLayoutX(newX);
            // Set the hero's image scale back to normal
            getImageView().setScaleX(1);
        }

    }

    public void shoot() {
        // Check if shooting animation is already in progress
        if (!isShootingAnimationInProgress) {
            // Set the initial frame index for shooting animation
            currentFrameIndex = 0;

            // Set a flag to indicate that shooting animation is in progress
            isShootingAnimationInProgress = true;

            // Create a Timeline to handle the shooting animation
            Timeline shootingTimeline = new Timeline();

            // Set the duration of each frame in milliseconds
            int frameDuration = 5000;

            // Add a KeyFrame for each frame in the shooting animation
            for (int i = 0; i < 6; i++) {
                int frameIndex = i;
                shootingTimeline.getKeyFrames().add(
                        new KeyFrame(Duration.millis(frameDuration * i), event -> {
                            setFrameIndexShooting(frameIndex);
                        })
                );
            }

            // Set the cycle count to 1 to play the shooting animation once
            shootingTimeline.setCycleCount(1);

            // Set an event handler to reset the shooting animation flag when it completes
            shootingTimeline.setOnFinished(event -> isShootingAnimationInProgress = false);

            // Start the shooting Timeline
            shootingTimeline.play();
        }

    }
    public void setShooting ( boolean value) {
        isShooting = value;
    }

}
