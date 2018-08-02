package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stageM;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stageM=primaryStage;
        Stage window=primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Main.fxml"));

        Scene start = new Scene(loader.load(),1000,600);
        window.setScene(start);
        window.setResizable(false);
        window.setTitle("Fire Watch Farm");
        window.show();
    }

    public void changeScene(String fxml) throws  Exception{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));

        Scene scene = new Scene(pane);
        stageM.setScene(scene);
        stageM.show();
    }
}