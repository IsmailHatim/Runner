import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    private final int WINDOW_LENGTH = 600;
    private final int WINDOW_WIDTH = 350;



    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Runner");
        Group root = new Group();


        Pane pane = new Pane(root);
        GameScene theScene = new GameScene(pane, WINDOW_LENGTH, WINDOW_WIDTH);



        primaryStage.setScene(theScene);
        primaryStage.show();
        pane.requestFocus();
    }

    public static void main(String[] args){
        launch(args);
    }
}
