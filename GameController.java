package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameController {

    @FXML
    private Button tool1;
    @FXML
    private Button button00;
    @FXML
    private Button button01;
    @FXML
    private Button button02;
    @FXML
    private Button button03;
    @FXML
    private Button button04;
    @FXML
    private Button button05;
    @FXML
    private Button button06;
    @FXML
    private Button button07;
    @FXML
    private Button button08;
    @FXML
    private Button button09;

    @FXML
    private Button button10;
    @FXML
    private Button button11;
    @FXML
    private Button button12;
    @FXML
    private Button button13;
    @FXML
    private Button button14;
    @FXML
    private Button button15;
    @FXML
    private Button button16;
    @FXML
    private Button button17;
    @FXML
    private Button button18;
    @FXML
    private Button button19;

    @FXML
    private Button button20;
    @FXML
    private Button button21;
    @FXML
    private Button button22;
    @FXML
    private Button button23;
    @FXML
    private Button button24;
    @FXML
    private Button button25;
    @FXML
    private Button button26;
    @FXML
    private Button button27;
    @FXML
    private Button button28;
    @FXML
    private Button button29;

    @FXML
    private Button button30;
    @FXML
    private Button button31;
    @FXML
    private Button button32;
    @FXML
    private Button button33;
    @FXML
    private Button button34;
    @FXML
    private Button button35;
    @FXML
    private Button button36;
    @FXML
    private Button button37;
    @FXML
    private Button button38;
    @FXML
    private Button button39;

    @FXML
    private Button button40;
    @FXML
    private Button button41;
    @FXML
    private Button button42;
    @FXML
    private Button button43;
    @FXML
    private Button button44;
    @FXML
    private Button button45;
    @FXML
    private Button button46;
    @FXML
    private Button button47;
    @FXML
    private Button button48;
    @FXML
    private Button button49;

    @FXML
    private Label name;

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void initialize(){

        name.setText(username);
        tool1.setOnAction(event -> {
            System.out.println("TOOL1 TEST");
        });

        button00.setOnAction(event -> {

        });
        button01.setOnAction(event -> {

        });
        button02.setOnAction(event -> {

        });
        button03.setOnAction(event -> {

        });
        button04.setOnAction(event -> {

        });
        button05.setOnAction(event -> {

        });
        button06.setOnAction(event -> {

        });
        button07.setOnAction(event -> {

        });
        button08.setOnAction(event -> {

        });
        button09.setOnAction(event -> {

        });
    }
}
