import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.List;

public class Hero extends AnimatedThing {
    private long lastUpdateTime;
    int maxIndex;
    int frameWidth = 75; // Width of each frame in the sprite sheet
    int frameHeight = 100; // Height of each frame in the sprite sheet
    int totalFrames = 6; // Total number of frames in the sprite sheet
    private List<Integer> listOfHeroFrames;

    public Hero(double x, double y, int numberOfLives, int maxIndex,  int windowHeight, int windowWidth, String fileName) {
        super(x, y, numberOfLives,maxIndex, windowHeight, windowWidth, fileName);
        listOfHeroFrames = new ArrayList<>();
        int frame0=frameWidth;
        int frame1=frameWidth + 25;
        int frame2=2*frameWidth + 25;
        int frame3=3 * frameWidth + 30;
        int frame4=4 * frameWidth +50 ;
        int frame5=5 * frameWidth + 50;
        listOfHeroFrames.add(frame0);
        listOfHeroFrames.add(frame1);
        listOfHeroFrames.add(frame2);
        listOfHeroFrames.add(frame3);
        listOfHeroFrames.add(frame4);
        listOfHeroFrames.add(frame5);
    }

    public void setFrameIndex(int frameIndexToShow) {
        int frameWidth = 75; // Width of each frame in the sprite sheet
        int frameHeight = 100; // Height of each frame in the sprite sheet
        int frameX;
        int distanceBtwFrames;
        if (frameIndexToShow==0){
            frameX=0;
        }
        else {
            switch (frameIndexToShow) {
                case 1:
                    // Calculate the x-coordinate of the frame to display (based on frameIndexToShow)
                    distanceBtwFrames = frameWidth + 25;
                    break;
                case 2:
                    // Calculate the x-coordinate of the frame to display (based on frameIndexToShow)
                    distanceBtwFrames = 2 * frameWidth + 25;
                    break;
                case 3:
                    // Calculate the x-coordinate of the frame to display (based on frameIndexToShow)
                    distanceBtwFrames = 3 * frameWidth + 30;
                    break;
                case 4:
                    // Calculate the x-coordinate of the frame to display (based on frameIndexToShow)
                    distanceBtwFrames = 4 * frameWidth +35 ;
                    break;
                case 5:
                    // Calculate the x-coordinate of the frame to display (based on frameIndexToShow)
                    distanceBtwFrames = 5 * frameWidth + 40;
                    break;
                default:
                    distanceBtwFrames = 0;

            }
            frameX = distanceBtwFrames ;
        }
        // Set the viewport to display the specified frame
        getImageView().setViewport(new Rectangle2D(frameX, 0, frameWidth, frameHeight));
    }
    @Override
    public void update(long time) {
        if ((time - lastUpdateTime)>=100_000_000L){
            changeFrameIndex(0);//Change sprite index logic
            lastUpdateTime=time;
        }

    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public void changeFrameIndex(int currentFrame) {
        if (currentFrame < listOfHeroFrames.size()) {
            // Get the next frame position from the list and set the viewport
            int nextFrameX = listOfHeroFrames.get(currentFrame);
            getImageView().setViewport(new Rectangle2D(nextFrameX, 0, frameWidth, frameHeight));
        }
    }
    /*public void changeFrameIndex() {
        /*int currentFrame = 0; // Current frame index
        // Increment the frame index by 1 (assuming it loops back to 0)
        currentFrame++;
        if (currentFrame >= totalFrames ) {
            currentFrame = 0;
        }
        // Condition spécifique pour gérer le cas où currentFrame = 0
        if (currentFrame == 0) {
            // Garder la première frame affichée
            getImageView().setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        } else {
            // Calculer la coordonnée x de la prochaine frame dans la sprite sheet
            int nextFrameX = listOfHeroFrames.get(currentFrame);

            // Afficher la prochaine frame dans la sprite sheet
            getImageView().setViewport(new Rectangle2D(nextFrameX, 0, frameWidth, frameHeight));
        }
    }*/


}
