
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.Screen;

public class App extends Application {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        
        Screen screen = new Screen(WIDTH, HEIGHT);
        Scene scene = new Scene(screen);
        AssetManager.loadAll();

        stage.getIcons().add(AssetManager.assets.get("DNDIcon"));
        stage.setTitle("DNDbattlemanager");
        stage.setScene(scene);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setResizable(false);
        //stage.setX(50);
        //stage.setY(50); 
        stage.show();
    }
    
}