import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AnalogClockApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        ClockPane clockPane = new ClockPane(300);
        clockPane.setClockFaceColor(Color.LIGHTBLUE);
        clockPane.setHourHandColor(Color.BLACK);
        clockPane.setMinuteHandColor(Color.BLACK);
        clockPane.setSecondHandColor(Color.RED);

        // Create a VBox to hold clock customization controls
        VBox controlBox = createControlBox(clockPane);

        // Create a BorderPane to organize clock and controls
        BorderPane root = new BorderPane();
        root.setCenter(clockPane);
        root.setRight(controlBox);

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Analog Clock App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createControlBox(ClockPane clockPane) {
        VBox controlBox = new VBox(10);
        controlBox.setPadding(new Insets(10));

        // Clock size ComboBox
        Label sizeLabel = new Label("Clock Size:");
        ObservableList<Integer> sizes = FXCollections.observableArrayList(200, 300, 400);
        ComboBox<Integer> sizeComboBox = new ComboBox<>(sizes);
        sizeComboBox.setValue(300);
        sizeComboBox.setOnAction(event -> {
            int selectedSize = sizeComboBox.getValue();
            clockPane.setSize(selectedSize);
        });

        // Clock face color ComboBox
        Label colorLabel = new Label("Clock Face Color:");
        ObservableList<String> colors = FXCollections.observableArrayList("LIGHTBLUE", "LIGHTGREEN", "LIGHTPINK");
        ComboBox<String> colorComboBox = new ComboBox<>(colors);
        colorComboBox.setValue("LIGHTBLUE");
        colorComboBox.setOnAction(event -> {
            Color selectedColor = Color.valueOf(colorComboBox.getValue());
            clockPane.setClockFaceColor(selectedColor);
        });

        // Hour hand color ComboBox
        Label hourHandColorLabel = new Label("Hour Hand Color:");
        ComboBox<String> hourHandColorComboBox = new ComboBox<>(colors);
        hourHandColorComboBox.setValue("BLACK");
        hourHandColorComboBox.setOnAction(event -> {
            Color selectedColor = Color.valueOf(hourHandColorComboBox.getValue());
            clockPane.setHourHandColor(selectedColor);
        });

        // Minute hand color ComboBox
        Label minuteHandColorLabel = new Label("Minute Hand Color:");
        ComboBox<String> minuteHandColorComboBox = new ComboBox<>(colors);
        minuteHandColorComboBox.setValue("BLACK");
        minuteHandColorComboBox.setOnAction(event -> {
            Color selectedColor = Color.valueOf(minuteHandColorComboBox.getValue());
            clockPane.setMinuteHandColor(selectedColor);
        });

        // Second hand color ComboBox
        Label secondHandColorLabel = new Label("Second Hand Color:");
        ComboBox<String> secondHandColorComboBox = new ComboBox<>(colors);
        secondHandColorComboBox.setValue("RED");
        secondHandColorComboBox.setOnAction(event -> {
            Color selectedColor = Color.valueOf(secondHandColorComboBox.getValue());
            clockPane.setSecondHandColor(selectedColor);
        });

        controlBox.getChildren().addAll(
                sizeLabel, sizeComboBox,
                colorLabel, colorComboBox,
                hourHandColorLabel, hourHandColorComboBox,
                minuteHandColorLabel, minuteHandColorComboBox,
                secondHandColorLabel, secondHandColorComboBox
        );

        return controlBox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class ClockPane extends Pane {
    private int size;
    private Color clockFaceColor;
    private Color hourHandColor;
    private Color minuteHandColor;
    private Color secondHandColor;

    private Circle clockFace;
    private Line hourHand;
    private Line minuteHand;
    private Line secondHand;
    private Text digitalTime;

    public ClockPane() {
        this(200);
    }

    public ClockPane(int size) {
        this.size = size;
        this.clockFaceColor = Color.WHITE;
        this.hourHandColor = Color.BLACK;
        this.minuteHandColor = Color.BLACK;
        this.secondHandColor = Color.RED;

        initializeClock();
    }

    private void initializeClock() {
        createClockFace();
        createHourHand();
        createMinuteHand();
        createSecondHand();
        createDigitalTime();

        // Update clock hands and digital time every second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> update()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void createClockFace() {
        clockFace = new Circle(size / 2, size / 2, size / 2);
        clockFace.setFill(clockFaceColor);
        getChildren().add(clockFace);
    }

    private void createHourHand() {
        hourHand = new Line();
        hourHand.setStrokeWidth(5);
        hourHand.setStroke(hourHandColor);
        getChildren().add(hourHand);
    }

    private void createMinuteHand() {
        minuteHand = new Line();
        minuteHand.setStrokeWidth(3);
        minuteHand.setStroke(minuteHandColor);
       
