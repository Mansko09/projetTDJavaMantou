import javafx.scene.Node;
import javafx.scene.Parent;

public class Camera extends Parent {
    int x;
    int y;

    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return (getX() + "," + getY());

    }
}

    /*@Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }*/

