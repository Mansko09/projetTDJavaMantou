import javax.swing.text.html.ImageView;

public class StaticThing {
    double sizeX;
    double sizeY;
    ImageView imageView;

    public StaticThing(double sizeX,double sizeY, ImageView imageView, String fileName){
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        this.imageView=imageView;

    }

    public ImageView getImageView() {
        return imageView;
    }
}
