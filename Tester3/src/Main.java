import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage) {
        Enter enter = new Enter();
        enter.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}