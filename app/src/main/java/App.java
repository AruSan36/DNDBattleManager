
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.Screen;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Screen screen = new Screen();
        Scene scene = new Scene(screen.getRoot(), 800, 600);

        stage.setTitle("DND Battle Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
