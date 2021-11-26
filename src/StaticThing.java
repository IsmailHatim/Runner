import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StaticThing {
    protected double x;
    protected double y;
    protected double x_view;
    protected double y_view;
    protected ImageView imageView = new ImageView();

    public StaticThing(double x, double y, String fileName, double x_view, double y_view, int length, int width){
        this.x = x;
        this.y = y;
        imageView.setImage(new Image("img\\"+fileName));
        imageView.setX(x);
        imageView.setY(y);
        imageView.setViewport(new Rectangle2D(x_view,y_view,length,width));
    }


    //Getter et Setter -----
    public double getX(){return x;}
    public double getY(){return y;}
    public double getX_view(){return x_view;}
    public double getY_view(){return y_view;}
    public ImageView getImageView(){return imageView;}

    public void setX(double x){this.x = x;}
    public void setY(double y){this.y = y;}
    public void setX_view(double x_view){this.x_view = x_view;}
    public void setY_view(double y_view){this.y_view = y_view;}
    public void setImageView(ImageView imageView){this.imageView = imageView;}

}
