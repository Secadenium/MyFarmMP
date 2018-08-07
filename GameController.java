package controller;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;

import application.Main;
import model.Tiles;
import javafx.scene.layout.StackPane;

public class GameController {
    @FXML private Label name;
    @FXML private GridPane fruitMenu;
    @FXML private Label previewLabel;
    @FXML private ImageView fruitMenuBack;
    @FXML private Button storeButton;
    @FXML private AnchorPane storePane;
    @FXML private Button storeBack;
    @FXML private GridPane gridTile;
    @FXML private GridPane toolGrid;
    @FXML private GridPane storeGrid;
    @FXML private ImageView storePreview;
    @FXML private Label previewDesc;

    private Main main;
    private Node[][] grid,gridTool,gridStoreNode;
    private boolean hoeEquipped;
    private boolean pickaxeEquipped;
    private boolean wateringCanEquipped;
    private boolean scytheEquipped;
    private boolean fertilizerEquipped;
    private boolean cursorEquipped;

    private Tiles tile;

    public void initialize(){

        int x=10;
        int y=5;

        grid = new Node[x][y];

        for(int a=0;a<x;a++){
            for (int b=0;b<y;b++){

                tile = new Tiles();

                Button view = new Button();
                view.setMaxHeight(80);
                view.setMaxWidth(80);
                view.setMinHeight(80);
                view.setMinWidth(80);

                ImageView image = new ImageView();
                image.setFitWidth(80);
                image.setFitHeight(80);
                image.setImage(new Image(getClass().getResourceAsStream("/pictures/Soil.png")));

                StackPane stack = new StackPane();
                stack.getChildren().add(image);

                ImageView plant = new ImageView();
                plant.setFitWidth(80);
                plant.setFitHeight(80);

                stack.getChildren().add(plant);

                ImageView rock = new ImageView();
                rock.setFitWidth(80);
                rock.setFitHeight(80);

                stack.getChildren().add(rock);

                view.setGraphic(stack);

                if(tile.getHasRock()) {
                    rock.setImage(new Image(getClass().getResourceAsStream("/pictures/Rock.png")));
                }

                gridTile.add(view,a,b);
                grid[a][b]=view;

                final int i=a;
                final int j=b;

                ((Button)grid[i][j]).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                            public void handle(ActionEvent event){
                        if (hoeEquipped) {
                            tile.setPlowed(true);
                            image.setImage(new Image(getClass().getResourceAsStream("/pictures/Plowed.png")));
                        }
                        if (pickaxeEquipped) {
                            rock.setDisable(true);
                            rock.setOpacity(0.0);
                            tile.setHasRock(false);
                            tile.setOccupied(false);
                        }
                        if (wateringCanEquipped) {
                            if (!tile.isWatered() && !tile.isPlowed()) {
                                image.setImage(new Image(getClass().getResourceAsStream("/pictures/SoilWatered.png")));

                            } else if (tile.isPlowed()) {
                                image.setImage(new Image(getClass().getResourceAsStream("/pictures/SoilPlowedWatered.png")));

                            }
                        }
                        if (scytheEquipped) {

                        }
                        if (fertilizerEquipped) {

                        }
                        if (cursorEquipped) {
                            if (fruitMenu.isDisabled() && tile.isPlowed()) {
                                showFruits(view.getLayoutX(), view.getLayoutY(), false, 1.0);
                            } else {
                                showFruits(view.getLayoutX(), view.getLayoutY(), true, 0.0);
                            }
                        }
                    }
                });
            }
        }

        gridTool = new Node[6][1];

        for(int a=0;a<6;a++){
            for (int b=0;b<1;b++){
                Button tools = new Button();
                tools.setMaxHeight(48);
                tools.setMaxWidth(48);
                tools.setMinHeight(48);
                tools.setMinWidth(48);

                ImageView imageTool = new ImageView();
                imageTool.setFitWidth(48);
                imageTool.setFitHeight(48);
                switch (a) {
                    case 0:
                        imageTool.setImage(new Image(getClass().getResourceAsStream("/pictures/Hoe.png")));
                        break;
                    case 1:
                        imageTool.setImage(new Image(getClass().getResourceAsStream("/pictures/Pickaxe.png")));
                        break;
                    case 2:
                        imageTool.setImage(new Image(getClass().getResourceAsStream("/pictures/WateringCan.png")));
                        break;
                    case 3:
                        imageTool.setImage(new Image(getClass().getResourceAsStream("/pictures/Scythe.png")));
                        break;
                    case 4:
                        imageTool.setImage(new Image(getClass().getResourceAsStream("/pictures/Basic_Fertilizer.png")));
                        break;
                    case 5:
                        imageTool.setImage(new Image(getClass().getResourceAsStream("/pictures/Cursor.png")));
                        break;
                }

                tools.setGraphic(imageTool);

                toolGrid.add(tools,a,b);
                gridTool[a][b]=tools;

                switch (a) {
                    case 0:
                        tools.setOnAction(event -> {
                            if(hoeEquipped==false) {
                                hoeEquipped = true;
                                pickaxeEquipped = false;
                                wateringCanEquipped = false;
                                scytheEquipped = false;
                                fertilizerEquipped = false;
                                cursorEquipped = false;
                            }
                            else
                                hoeEquipped=false;
                        });
                        break;
                    case 1:
                        tools.setOnAction(event -> {
                            if(pickaxeEquipped==false) {
                                pickaxeEquipped = true;
                                hoeEquipped=false;
                                wateringCanEquipped=false;
                                scytheEquipped=false;
                                fertilizerEquipped=false;
                                cursorEquipped=false;
                            }
                            else
                                pickaxeEquipped=false;
                        });
                        break;
                    case 2:
                        tools.setOnAction(event -> {
                            if(wateringCanEquipped==false) {
                                wateringCanEquipped = true;
                                hoeEquipped = false;
                                pickaxeEquipped = false;
                                scytheEquipped = false;
                                fertilizerEquipped = false;
                                cursorEquipped = false;
                            }
                            else
                                wateringCanEquipped=false;
                        });
                        break;
                    case 3:
                        tools.setOnAction(event -> {
                            if(scytheEquipped==false) {
                                scytheEquipped = true;
                                hoeEquipped = false;
                                pickaxeEquipped = false;
                                wateringCanEquipped = false;
                                fertilizerEquipped = false;
                                cursorEquipped = false;
                            }
                            else
                                scytheEquipped=false;
                        });
                        break;
                    case 4:
                        tools.setOnAction(event -> {
                            if(fertilizerEquipped==false) {
                                fertilizerEquipped = true;
                                hoeEquipped = false;
                                pickaxeEquipped = false;
                                wateringCanEquipped = false;
                                scytheEquipped = false;
                                cursorEquipped = false;
                            }
                            else
                                fertilizerEquipped=false;
                        });
                        break;
                    case 5:
                        tools.setOnAction(event -> {
                            if(cursorEquipped==false){
                                hoeEquipped=false;
                                pickaxeEquipped=false;
                                wateringCanEquipped=false;
                                scytheEquipped=false;
                                fertilizerEquipped=false;
                                cursorEquipped=true;
                            }
                            else
                                cursorEquipped=false;

                        });
                        break;
                }
            }
        }

        for(int a=0;a<4;a++){
            for (int b=0;b<4;b++) {

                Button fruits = new Button();
                fruits.setMaxHeight(50);
                fruits.setMaxWidth(50);
                fruits.setMinHeight(50);
                fruits.setMinWidth(50);

                ImageView fruitImage = new ImageView();
                fruitImage.setFitWidth(50);
                fruitImage.setFitHeight(50);

                storeGrid.add(fruits,a,b);

                switch (a+(b*4)) {
                    case 0:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Beet_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Beet_Seeds.png")));
                            previewLabel.setText("Turnip");
                            previewDesc.setText("Harvest Time: \nWater Needed: \nFertilizer Needed: \nHarvest Cost: \nProducts Produced: \nSeed Cost: \nBase Sell: ");
                        });
                        break;
                    case 1:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Parsnip_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Parsnip_Seeds.png")));
                            previewLabel.setText("Carrot");
                        });
                        break;
                    case 2:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Tomato_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Tomato_Seeds.png")));
                            previewLabel.setText("Tomato");
                        });
                        break;
                    case 3:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Potato_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Potato_Seeds.png")));
                            previewLabel.setText("Potato");
                        });
                        break;
                    case 4:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Fairy_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Fairy_Seeds.png")));
                            previewLabel.setText("Rose");
                        });
                        break;
                    case 5:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Tulip_Bulb.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Tulip_Bulb.png")));
                            previewLabel.setText("Tulip");
                        });
                        break;
                    case 6:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Spangle_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Spangle_Seeds.png")));
                            previewLabel.setText("Stargazer");
                        });
                        break;
                    case 7:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Sunflower_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Sunflower_Seeds.png")));
                            previewLabel.setText("Sunflower");
                        });
                        break;
                    case 8:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Apricot_Sapling.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Apricot_Sapling.png")));
                            previewLabel.setText("Mango");
                        });
                        break;
                    case 9:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Apple_Sapling.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Apple_Sapling.png")));
                            previewLabel.setText("Apple");
                        });
                        break;
                    case 10:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/100px-Palm_Stage_1.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/100px-Palm_Stage_1.png")));
                            previewLabel.setText("Banana");
                        });
                        break;
                    case 11:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Orange_Sapling.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Orange_Sapling.png")));
                            previewLabel.setText("Orange");
                        });
                        break;
                    case 12:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Basic_Fertilizer.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Basic_Fertilizer.png")));
                            previewLabel.setText("Fertilizer");
                        });
                        break;
                    case 13:
                        //fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Cursor.png")));
                        break;
                    case 14:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Arrowhead.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Arrowhead.png")));
                            previewLabel.setText("Upgrade Farmer");
                        });
                        break;
                    case 15:
                        //fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Cursor.png")));
                        break;

                }

                fruits.setGraphic(fruitImage);
            }
        }
        main = new application.Main();

        name.setText(main.getNameName());

        storeButton.setOnAction(event -> {
            if(storePane.isDisabled()){
                storePane.setDisable(false);
                storePane.setOpacity(1.0);
            }
            else {
                storePane.setDisable(true);
                storePane.setOpacity(0.0);
            }
        });
        storeBack.setOnAction(event -> {
            storePane.setDisable(true);
            storePane.setOpacity(0.0);
        });

        for(int a=0;a<2;a++) {
            for (int b = 0; b < 6; b++) {

                Button cropsB = new Button();
                cropsB.setMaxHeight(40);
                cropsB.setMaxWidth(40);
                cropsB.setMinHeight(40);
                cropsB.setMinWidth(40);

                ImageView cropsBImage = new ImageView();
                cropsBImage.setFitWidth(40);
                cropsBImage.setFitHeight(40);

                fruitMenu.add(cropsB, a, b);

                switch (b + (a * 2)) {
                    case 0:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Beet_Seeds.png")));
                        cropsB.setOnAction(event -> {

                        });
                        break;
                    case 1:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Parsnip_Seeds.png")));
                        cropsB.setOnAction(event -> {

                        });
                        break;
                    case 2:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Tomato_Seeds.png")));
                        cropsB.setOnAction(event -> {

                        });
                        break;
                    case 3:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Potato_Seeds.png")));
                        cropsB.setOnAction(event -> {

                        });
                        break;
                    case 4:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Fairy_Seeds.png")));
                        cropsB.setOnAction(event -> {

                        });
                        break;
                    case 5:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Tulip_Bulb.png")));
                        cropsB.setOnAction(event -> {

                        });
                        break;
                    case 6:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Spangle_Seeds.png")));
                        cropsB.setOnAction(event -> {

                        });
                        break;
                    case 7:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Sunflower_Seeds.png")));
                        cropsB.setOnAction(event -> {

                        });
                        break;
                    case 8:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Apricot_Sapling.png")));
                        cropsB.setOnAction(event -> {

                        });
                        break;
                    case 9:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Apple_Sapling.png")));
                        cropsB.setOnAction(event -> {

                        });
                        break;
                    case 10:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/100px-Palm_Stage_1.png")));
                        cropsB.setOnAction(event -> {

                        });
                        break;
                    case 11:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Orange_Sapling.png")));
                        cropsB.setOnAction(event -> {

                        });
                        break;
                }
                cropsB.setGraphic(cropsBImage);
            }
        }
    }

    public void showFruits(double x, double y,boolean visible, double opac){
            fruitMenu.setDisable(visible);
            fruitMenu.setOpacity(opac);
            fruitMenuBack.setDisable(visible);
            fruitMenuBack.setOpacity(opac);

            fruitMenu.setLayoutX(x+8);
            fruitMenu.setLayoutY(y+8);
            fruitMenuBack.setLayoutX(x);
            fruitMenuBack.setLayoutY(y);
    }
}
