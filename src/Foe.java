import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.List;

public class Foe extends AnimatedThing{
    int Index=0;
    private int currentFrameIndex=0;
    int frameWidth = 80; // Width of each frame in the sprite sheet
    int frameHeight = 100; // Height of each frame in the sprite sheet
    int totalFrames = 8; // Total number of frames in the sprite sheet
    //private List<Integer> listOfFoeFrames;
    private List<Integer> listOfHeroFrames;
    private double speed;//constant speed for the foe

    public Foe(double x, double y, int numberOfLives, int maxIndex, int windowHeight, int windowWidth, String fileName) {
        super(x, y, numberOfLives, maxIndex, windowHeight, windowWidth, fileName);
        /*listOfFoeFrames = new ArrayList<>();
        int frame0=0;
        int frame1=frameWidth + 15;
        int frame2=2*frameWidth + 20;
        int frame3= 3 * frameWidth + 25;
        listOfFoeFrames.add(frame0);
        listOfFoeFrames.add(frame1);
        listOfFoeFrames.add(frame2);
        listOfFoeFrames.add(frame3);*/
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
        this.speed=3;
    }
    //code with the different starting positions of the hero
    public void setFrameIndex(int frameIndexToShow) {
        int frameWidth = 70; // Width of each frame in the sprite sheet
        int frameHeight = 100; // Height of each frame in the sprite sheet
        int frameX=0;
        //int frameY=110;
        int frameY=0;
        if (frameIndexToShow==0){
            //frameX=listOfFoeFrames.get(0);
            frameX=listOfHeroFrames.get(0);
        }
        else {
            if (frameIndexToShow > 0 && frameIndexToShow <= getTotalFrames()) {
                if (frameIndexToShow < 6) {
                    //frameX = listOfFoeFrames.get(frameIndexToShow);
                    frameX = listOfHeroFrames.get(frameIndexToShow);
                }
            }
        }
        // Set the viewport to display the specified frame
        getImageView().setViewport(new Rectangle2D(frameX, frameY, frameWidth, frameHeight));
    }
    @Override
    public void update(long time) {
        setFrameIndex(currentFrameIndex);
        currentFrameIndex = (currentFrameIndex + 1) % 6; // Loop through frames
    }


    public int getTotalFrames() {
        return totalFrames;
    }

    public double getSpeed() {
        return speed;
    }
}

