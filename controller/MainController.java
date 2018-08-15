/**
 * This Main Controller controls the GUI of the Starting menu
 */

package controller;

import application.Main;
import model.Farmer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * This holds the buttons and codes for the starting menu
 */
public class MainController {
    @FXML
    private Button startButton;
    @FXML
    private MediaView menuMedia;
    @FXML
    private MediaView menuMusic;
    @FXML
    private Label start;
    @FXML
    private TextField userName;

    private Main mainM;

    /**
     * initializes the video and song
     * changes scene towards the game scene
     */
    public void initialize(){

        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/pictures/Scene.mp4").toExternalForm()));
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        menuMedia.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnReady(() -> {

        });

        MediaPlayer mediaPlayer1 = new MediaPlayer(new Media(getClass().getResource("/pictures/Song.mp3").toExternalForm()));
        mediaPlayer1.setAutoPlay(true);
        mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
        menuMusic.setMediaPlayer(mediaPlayer1);
        mediaPlayer1.setOnReady(() -> {

        });

        userName.setOnAction(event -> {
            start.setOpacity(1.0);
            startButton.setDisable(false);
        });

        startButton.setOnAction(event -> {
            mainM = new application.Main();
            System.out.println("CHECK START BUTTON");
            mainM.setNameName(userName.getText());
            try {
                mainM.changeScene("/view/Game.fxml");

            } catch (Exception e) {
                e.printStackTrace();
            }
            mediaPlayer.stop();
        });
    }
}