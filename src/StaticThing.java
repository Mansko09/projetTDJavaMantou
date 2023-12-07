import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StaticThing {
    double sizeX;
    double sizeY;
    ImageView imageView;

    public StaticThing(double sizeX,double sizeY, String fileName){
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        try {
            Image image = new Image(fileName);
            this.imageView = new ImageView(image);
            this.imageView.setFitHeight(sizeY);
            this.imageView.setFitWidth(sizeX);
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement de l'image : " + e.getMessage());
            e.printStackTrace();
            // Gérer l'erreur : peut-être utiliser une image de secours ou afficher un message à l'utilisateur
        }
    }

    public ImageView getImageView() {
        return imageView;
    }
}
