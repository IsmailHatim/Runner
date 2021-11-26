import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;


public class GameScene extends Scene {
    private final String BACKGROUND_FILENAME = "desert.png";
    private final double BACKGROUND_WIDTH = (new Image("img\\" + BACKGROUND_FILENAME)).getWidth();
    private final double BACKGROUND_HEIGHT = (new Image("img\\" + BACKGROUND_FILENAME)).getHeight();
    private final String HERO_SPRITESHEET_FILENAME = "heros.png";
    private final String FOE_SPRITESHEET_FILENAME = "alien.PNG";
    private final int BACKGROUND_Y_OFFSET = 25;
    private final int LEVEL = 1;
    private final int INIT_NUMBER_FOES = 9;
    protected int length;
    protected int width;
    protected Camera camera = new Camera(0, 0, 0, 0, 0, 0);
    protected StaticThing background_left;
    protected StaticThing background_right;
    protected ArrayList<Foe> Foes =  new ArrayList<>();
    protected Pane pane = new Pane();


    protected Hero hero = new Hero(200, 220, 0, 0, 0, 0, HERO_SPRITESHEET_FILENAME);

    protected int numberOfLives;

    public GameScene(Pane pane, int length, int width) {

        super(pane, length, width, true);
        this.pane = pane;
        this.length = length;
        this.width = width;
        this.numberOfLives = 3;
        background_left = new StaticThing(0, 0, BACKGROUND_FILENAME, camera.getX(), camera.getY() + BACKGROUND_Y_OFFSET, length, width);
        background_right = new StaticThing(0, 0, BACKGROUND_FILENAME, camera.getX() - BACKGROUND_WIDTH, camera.getY() + BACKGROUND_Y_OFFSET, length, width);
        AnimationTimer timer = new AnimationTimer() {
            public void handle(long time) {
                hero.update(time);
                camera.update(time, hero.getxHero());
                update(time);
                System.out.println(hero.getHitBox().getMaxY());
                for (int k = 0; k < Foes.size(); k++){
                    if (Foes.get(k).getX() <= -50 ){
                        Foes.remove(k);
                    }
                    Foes.get(k).update(time);
                    if (hero.getHitBox().getMinX() > Foes.get(k).getHitBox().getMaxX()){
                        Foes.get(k).setDodged(true);
                    }
                    if ( (hero.getHitBox().getMaxX() > Foes.get(k).getHitBox().getMinX()) && (hero.getHitBox().getMaxY() > Foes.get(k).getHitBox().getMinY()) && (hero.getInvincibility()==false) &&(Foes.get(k).getDodged() == false) ){
                        Foes.get(k).die();
                        Foes.remove(k);
                        hero.setHit(true);
                    }
                }
            }
        };


        pane.getChildren().add(background_left.getImageView());
        pane.getChildren().add(background_right.getImageView());
        pane.getChildren().add(hero.getSprite());
        for (int i = 0; i < INIT_NUMBER_FOES + LEVEL; i++){
            Foe foe = new Foe(BACKGROUND_WIDTH + i*500 + (Math.random()*50),220, FOE_SPRITESHEET_FILENAME);
            Foes.add(foe);
            pane.getChildren().add(foe.getSprite());
        }


        this.addEventHandler(KeyEvent.KEY_PRESSED, pressedhandler);
        this.addEventHandler(KeyEvent.KEY_RELEASED, releasedhandler);

        timer.start();
    }

    public void update(long time) {
        background_left.getImageView().setViewport(new Rectangle2D(camera.getX() % BACKGROUND_WIDTH, camera.getY() + BACKGROUND_Y_OFFSET, length, width));
        background_right.getImageView().setViewport(new Rectangle2D(camera.getX() % BACKGROUND_WIDTH - BACKGROUND_WIDTH, camera.getY() + BACKGROUND_Y_OFFSET, length, width));
        if (Foes.size() == 0){
            for (int i = 0; i < INIT_NUMBER_FOES + LEVEL; i++){
                Foe foe = new Foe(BACKGROUND_WIDTH + i*500 + (Math.random()*50),220, FOE_SPRITESHEET_FILENAME);
                Foes.add(foe);
                pane.getChildren().add(foe.getSprite());
            }
        }
    }


    EventHandler<KeyEvent> pressedhandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            if (e.getCode() == KeyCode.RIGHT | e.getCode() == KeyCode.LEFT) {
                hero.move(e.getCode());
            }
            if (e.getCode() == KeyCode.UP) {
                if (hero.canJump) {
                    hero.jump();
                }
            }
            if (e.getCode() == KeyCode.SPACE){
                hero.shoot();
            }
        }
    };

    EventHandler<KeyEvent> releasedhandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            if (e.getEventType() == KeyEvent.KEY_RELEASED) {
                if (e.getCode() == KeyCode.RIGHT | e.getCode() == KeyCode.LEFT) {
                    hero.stopMoving();
                }
                if (e.getCode() == KeyCode.UP) {
                    hero.stopJumping();
                }
                if (e.getCode() == KeyCode.SPACE){
                    hero.stopShooting();
                }
            }
        }
    };
}