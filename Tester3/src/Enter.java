import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.*;

public class Enter {

    public void show(Stage primaryStage) {
        Button button = new Button("模擬輸入完畢");
        button.setStyle("-fx-font-size: 16px;");
        button.setOnAction(e -> {
            List<WorkoutData> data = mockData();
            AnalysisWindow analysis = new AnalysisWindow();
            Stage resultStage = new Stage();
            analysis.showAnalysis(resultStage, data);
        });

        StackPane root = new StackPane(button);
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.setTitle("訓練資料輸入");
        primaryStage.show();
    }

    private List<WorkoutData> mockData() {
        List<WorkoutData> data = new ArrayList<>();
        data.add(new WorkoutData("17:00", "18:10", "胸,腿", 20));
        data.add(new WorkoutData("17:10", "18:00", "肩,背", 15));
        data.add(new WorkoutData("16:30", "17:20", "腿,腹", 25));
        data.add(new WorkoutData("18:00", "19:00", "胸", 10));
        data.add(new WorkoutData("17:00", "18:00", "腿", 20));
        data.add(new WorkoutData("18:30", "19:15", "背", 15));
        data.add(new WorkoutData("16:00", "17:00", "肩", 5));
        return data;
    }
}
