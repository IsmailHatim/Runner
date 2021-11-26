import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;


public class Hero extends AnimatedThing{
    private final static int HERO_LENGTH = 100;
    private final static int HERO_WIDTH = 75;
    private final int[] HERO_MAX_INDEX_LIST = {6, 2, 6, 2};
    private final int[] HERO_X_VIEW_LIST0 = {20, 95, 175, 272, 348, 426};
    private final int[] HERO_X_VIEW_LIST1 = {20, 95};
    private final int[] HERO_X_VIEW_LIST2 = {10, 85, 160, 256, 340, 424};
    private final int[] HERO_X_VIEW_LIST3 = {20, 95};
    private final int multFactor = 5;
    private final int GRAVITY = 20000;
    protected int heroState;
    protected double ax;
    protected double ay;
    protected double vx;
    protected double vy;
    protected double vxHero;
    protected double xHero;
    protected double yHero;
    protected int updateN = 0;
    protected int updateM = 0;
    protected boolean isJumping = false;
    protected boolean canJump = true;
    protected boolean isMoving = false;
    protected boolean hit = false;
    protected boolean invincibility = false;




    public Hero(double x, double y,double vx, double vy, double ax, double ay, String fileName){
        super(x, y, fileName,20,0, HERO_LENGTH, HERO_WIDTH,16000000);
        heroState = 0;
        xHero = 0;
        this.ax = ax;
        this.ay = ay;
        this.vx = vx;
        this.vy = vy;
        max_index = HERO_MAX_INDEX_LIST[heroState];

    }

    public void jump(){
        isJumping = true;
        heroState = 1;
        vy -= 900;
        canJump = false;
    }

    public void move(KeyCode e){
        isMoving = true;
            switch (e) {
                case RIGHT:
                    System.out.println(x<525);
                    if (x<525) {
                        ax = 100;
                        vxHero = 5;
                    }
                    else{
                        vxHero = 0;
                        ax = 0;
                        isMoving = false;
                    }
                    break;
                case LEFT:
                    if (x>0) {
                        ax = -100;
                        vxHero = -5;
                    }
                    else{
                        vxHero = 0;
                        ax = 0;
                        isMoving = false;
                    }
                    break;
        }
    }

    public void shoot(){
        heroState = 2;
    }


    public void stopMoving(){
        isMoving = false;
        vxHero = 0;
        ax = 0;
    }

    public void stopJumping(){
        isJumping = false;
    }

    public void stopShooting(){
        heroState = 0;
    }



    public void update(long time){
        updateN++;
        max_index = HERO_MAX_INDEX_LIST[heroState];


        if (hit){
            invincibility = true;
            updateM++;
            sprite.setOpacity(0.5);
            if (updateM % 10 == 0){
                invincibility = false;
                sprite.setOpacity(1);
                updateM = 0 ;
                hit = false;
            }
        }

        if (y > 220) {
            heroState = 0;
            vy = 0;
            y = 220;
            canJump = true;
        }
        if (y < 10){
            vy += GRAVITY/10;
            isJumping = false;
        }
        if (isMoving == false){
            vx = 500;
        }
        else{
            vx += ax*0.016;
        }

        vy += ay*0.016;


        y += vy*0.016;
        x += vxHero;

        xHero += vx*0.016;
        yHero += vy*0.016;


        sprite.setX(x);
        sprite.setY(y);
        if (heroState == 0) {
            sprite.setViewport(new Rectangle2D(HERO_X_VIEW_LIST0[index], 0, HERO_WIDTH, HERO_LENGTH));
        }
        else if (heroState == 1){
            if (isJumping) {
                sprite.setViewport(new Rectangle2D(HERO_X_VIEW_LIST1[0], 160, HERO_WIDTH, HERO_LENGTH));
            }
            else{
                sprite.setViewport(new Rectangle2D(HERO_X_VIEW_LIST1[1], 160, HERO_WIDTH, HERO_LENGTH));
            }
        }
        else if (heroState == 2){
            sprite.setViewport(new Rectangle2D(HERO_X_VIEW_LIST2[index], 326, HERO_WIDTH, HERO_LENGTH));
        }
        else if (heroState == 3){
            sprite.setViewport(new Rectangle2D(HERO_X_VIEW_LIST3[index], 490, HERO_WIDTH, HERO_LENGTH));
        }


        if (updateN % multFactor == 0){
        index++;
        updateN = 0;
        }
        if (index >= max_index) {
            index = 0;
        }

    }



    public int getHeroState(){return heroState;}
    public double getAx(){return ax;}
    public double getAy(){return ay;}
    public double getVx(){return vx;}
    public double getVy(){return vy;}
    public double getxHero(){return xHero;}
    public double getyHero(){return yHero;}
    public boolean getMoving(){return isMoving;}
    public boolean getJumping(){return isJumping;}
    public boolean getHit(){return hit;}
    public boolean getInvincibility(){return invincibility;}
    public Rectangle2D getHitBox(){
        return new Rectangle2D(x,y,65,75);
    }

    public void setHeroState(int heroState){this.heroState = heroState;}
    public void setAx(double ax){this.ax = ax;}
    public void setAy(double ay){this.ay = ay;}
    public void setVx(double vx){this.vx = vx;}
    public void setVy(double vy){this.vy = vy;}
    public void setMoving(boolean isMoving){this.isMoving = isMoving;}
    public void setJumping(boolean isJumping){this.isJumping = isJumping;}
    public void setHit(boolean hit){this.hit = hit;}
    public void setInvincibility(boolean invincibility){this.invincibility = invincibility;}
}
