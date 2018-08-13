package controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

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
    private GameController game;



    public void initialize(){


        Media media = new Media("file:/Users/jeffcapistrano/Desktop/Scene.mp4");
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        menuMedia.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnReady(() -> {

        });

        Media media1 = new Media("file:/Users/jeffcapistrano/Desktop/Song.mp3");
        MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
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
            game = new GameController();
            System.out.println("CHECK START BUTTON");
            mainM.setNameName(userName.getText());
            try {
                mainM.changeScene("/view/Game.fxml");

            } catch (Exception e) {
                e.printStackTrace();
            }
            mediaPlayer.stop();
            //mediaPlayer1.stop();
        });
    }
}