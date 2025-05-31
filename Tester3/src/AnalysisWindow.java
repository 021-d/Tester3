import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.util.*;

public class AnalysisWindow {

    public void showAnalysis(Stage stage, List<WorkoutData> data) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f0f8ff;");

        WorkoutData latest = data.get(data.size() - 1);
        int totalTime = latest.getDuration();
        int cardioTime = latest.getCardioTime();
        double cardioRatio = totalTime == 0 ? 0 : (double) cardioTime / totalTime;

        // 上區
        VBox top = new VBox(5);
        top.setAlignment(Pos.CENTER);

        Label durationLabel = new Label(totalTime + " 分鐘");
        durationLabel.setFont(Font.font("Microsoft JhengHei", FontWeight.BOLD, 32));
        durationLabel.setTextFill(Color.DARKBLUE);
        durationLabel.setStyle("-fx-border-color: #4682b4; -fx-border-width: 2px; -fx-padding: 20px; -fx-border-radius: 10px;");

        Label cardioLabel = new Label("有氧比例：" + String.format("%.1f", cardioRatio * 100) + " %");
        cardioLabel.setTextFill(Color.BLUE);
        cardioLabel.setFont(Font.font("Microsoft JhengHei", 16));

        top.getChildren().addAll(durationLabel, cardioLabel);

        // 中區
        VBox middle = new VBox(10);
        Label todayLabel = new Label("▶ 今日訓練菜單");
        todayLabel.setFont(Font.font("Microsoft JhengHei", FontWeight.BOLD, 18));
        String[] allMuscles = {"肩", "胸", "背", "腿", "腹"};
        Map<String, Integer> countMap = new LinkedHashMap<>();
        for (String m : allMuscles) countMap.put(m, 0);

        String todayMuscle = latest.getMuscleGroup();
        for (String muscle : todayMuscle.split(",")) {
            muscle = muscle.trim();
            countMap.put(muscle, countMap.getOrDefault(muscle, 0) + 1);
        }

        VBox todayMuscleBox = new VBox();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            todayMuscleBox.getChildren().add(new Label(entry.getKey() + "*" + entry.getValue()));
        }

        Label historyLabel = new Label("▶ 最近七次訓練統計");
        historyLabel.setFont(Font.font("Microsoft JhengHei", FontWeight.BOLD, 18));

        Map<String, Integer> historyMap = new LinkedHashMap<>();
        for (String m : allMuscles) historyMap.put(m, 0);
        for (WorkoutData wd : data) {
            for (String muscle : wd.getMuscleGroup().split(",")) {
                muscle = muscle.trim();
                historyMap.put(muscle, historyMap.getOrDefault(muscle, 0) + 1);
            }
        }

        VBox historyBox = new VBox();
        for (Map.Entry<String, Integer> entry : historyMap.entrySet()) {
            historyBox.getChildren().add(new Label(entry.getKey() + "*" + entry.getValue()));
        }

        middle.getChildren().addAll(todayLabel, todayMuscleBox, historyLabel, historyBox);

        // 下區
        VBox bottom = new VBox();
        bottom.setPadding(new Insets(10));
        bottom.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: gray;");
        Label suggestionTitle = new Label("系統建議");
        suggestionTitle.setFont(Font.font("Microsoft JhengHei", FontWeight.BOLD, 16));

        Label suggestion = new Label();
        StringBuilder sb = new StringBuilder();

        if (cardioRatio < 0.2) sb.append("建議增加有氧運動。\n");
        if (countMap.get("腿") == 0) sb.append("你今天沒練腿！可以補練下半身。\n");
        if (historyMap.get("肩") == 0) sb.append("最近都沒練肩，建議補足上半身。\n");
        if (sb.length() == 0) sb.append("訓練分配良好，請持續保持！");

        suggestion.setText(sb.toString());
        suggestion.setWrapText(true);

        bottom.getChildren().addAll(suggestionTitle, suggestion);

        // 合併三區
        root.getChildren().addAll(top, middle, bottom);

        stage.setScene(new Scene(root, 400, 600));
        stage.setTitle("運動分析結果");
        stage.show();
    }
}
