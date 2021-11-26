import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public abstract class AnimatedThing {
    protected double x;
    protected double y;
    protected ImageView sprite = new ImageView();
    protected double x_view;
    protected double y_view;
    protected int index;
    protected int framePeriod;
    protected int max_index;

    public AnimatedThing(double x,double y, String fileName, double x_view, double y_view, int length, int width, int framePeriod){
        this.x = x;
        this.y = y;
        this.framePeriod = framePeriod;
        sprite.setImage(new Image("img\\" + fileName));
        sprite.setViewport(new Rectangle2D(x_view,y_view,width,length));
    }

    public abstract void update(long time);

    //Getter et Setter -----
    public double getX(){return x;}
    public double getY(){return y;}
    public ImageView getSprite(){return sprite;}
    public double getX_view(){return x_view;}
    public double getY_view(){return y_view;}
    public int getIndex(){return index;}
    public int getFramePeriod(){return framePeriod;}
    public int getMax_index(){return max_index;}

    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public void setSprite(ImageView sprite){this.sprite = sprite;}
    public void setX_view(double x_view){this.x_view = x_view;}
    public void setY_view(double y_view){this.y_view = y_view;}
    public void setIndex(int index){this.index = index;}
    public void setFramePeriod(int framePeriod){this.framePeriod = framePeriod;}
    public void setMax_index(int max_index){this.max_index = max_index;}

}
