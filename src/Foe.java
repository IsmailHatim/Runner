import javafx.geometry.Rectangle2D;

public class Foe extends AnimatedThing{
    private final static int FOE_LENGTH = 150;
    private final static int FOE_WIDTH = 110;
    private final int[] FOE_MAX_INDEX_LIST = {2, 3};
    private final int[] FOE_X_VIEW_LIST0 = {50, 258};
    private final int[] FOE_X_VIEW_LIST1 = {50, 258, 466};

    protected double vx = 5;
    protected int multFactor =5;
    protected int foeState;
    protected double updateN=0;
    protected boolean dodged = false;

    public Foe(double x, double y, String fileName){
        super(x,y,fileName,50,22,FOE_LENGTH,FOE_WIDTH,16000000);
        sprite.setFitHeight(75);
        sprite.setFitWidth(50);
        sprite.setY(y);
        foeState = 0;
        max_index = FOE_MAX_INDEX_LIST[foeState];
    }

    public void update(long time){
        updateN++;
        max_index = FOE_MAX_INDEX_LIST[foeState];

        sprite.setX(x);
        x -= vx;
        if (foeState == 0) {
            sprite.setViewport(new Rectangle2D(FOE_X_VIEW_LIST0[index], 22, FOE_WIDTH, FOE_LENGTH));
        }
        else if (foeState == 1){
            sprite.setViewport(new Rectangle2D(FOE_X_VIEW_LIST1[index], 22, FOE_WIDTH, FOE_LENGTH));
        }


        if (updateN % multFactor == 0){
            index++;
            updateN = 0;
        }
        if (index >= max_index) {
            index = 0;
        }
    }
    public void die(){
        sprite.setOpacity(0);
    }

    public boolean getDodged(){return dodged;}
    public Rectangle2D getHitBox(){
        return new Rectangle2D(x,y,50,75);
    }

    public void setDodged(boolean dodged){this.dodged = dodged;}
}
