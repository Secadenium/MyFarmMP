/**
 * S18A
 * @author Jeff Capistrano
 * @author Sean Yuhico
 * This MyFarm project was done as a requirement for OBJECTP as a final project
 * MyFarm is a farming simulation where the player can plant, harvest and earn money to expand their farm
 *
 *
 * All sprites used came from the farming game called "Stardew Valley". We do not own any of these sprites
 * and merely used them as compliance of the specifications and because of its accessibility and realibility.
 *
 * The video and song used in the Start Menu belongs to their respective owners who took inspiration from
 * the game "Fire Watch".
 */

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This class is the main GUI model of the project where all the GUI related methods are initialized
 * This is also how the application launches.
 *
 */
public class Main extends Application {

    public static Stage stageM;
    public static String nameName;
    private static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Sets the initial stage and scene when the code is ran
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stageM=primaryStage;
        Stage window=primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Main.fxml"));

        Scene start = new Scene(loader.load(),1000,600);
            //start.setCursor(new ImageCursor(new Image(getClass().getResourceAsStream("/pictures/Cursor.png"))));
        window.setScene(start);
        window.setResizable(false);
        window.setTitle("Fire Watch Farm");
        window.show();
    }

    /**
     * When called, changes the scene to the specified fxml link.
     * @param fxml
     * @throws Exception
     */
    public void changeScene(String fxml) throws  Exception{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));

        Scene scene = new Scene(pane);
        stageM.setScene(scene);
        stageM.show();
        Main.scene =scene;
    }

    /**
     * Sets the name which is inputted by the user
     * @param nameName
     */
    public static void setNameName(String nameName) {
        Main.nameName = nameName;
    }

    /**
     * Gets the String nameName
     * @return nameName
     */
    public static String getNameName() {
        return nameName;
    }

}