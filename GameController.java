package controller;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;

import application.Main;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.Crops;
import model.Tiles;
import model.Inventory;
import model.Farmer;
import javafx.scene.layout.StackPane;
import model.Turnip;

import java.util.ArrayList;

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
    @FXML private Label inventoryLabel;
    @FXML private Label inventoryDesc;
    @FXML private ImageView inventoryPreview;
    @FXML private Button inventoryButton;
    @FXML private AnchorPane inventoryPane;
    @FXML private GridPane inventoryGrid;
    @FXML private Label level;
    @FXML private Label gold;
    @FXML private Label time;
    @FXML private Button buy;
    @FXML private Slider amount;
    @FXML private Label seedAmount;
    @FXML private Label displayCost;
    @FXML private Button inventoryBack;

    private Main main;
    private Farmer farmer;
    private Node[][] grid;
    private boolean hoeEquipped;
    private boolean pickaxeEquipped;
    private boolean wateringCanEquipped;
    private boolean scytheEquipped;
    private boolean fertilizerEquipped;
    private boolean cursorEquipped;
    private int plantIndex;
    private int buyIndex;
    private boolean isTree;

    private ArrayList<Tiles> tile;

    public void initialize(){
        main = new application.Main();
        farmer = new Farmer();

        farmer.setName(main.getNameName());

        name.setText(main.getNameName());
        level.setText(farmer.getLevel()+"");
        gold.setText(farmer.getMoney()+"");

        AnimationTimer animationTimer = new AnimationTimer() {
            long time_now = System.nanoTime();

            @Override
            public void handle(long now) {
                long elapse = (now - time_now)/1000000000;
                long minute=elapse/60;
                elapse%=60;
                if(minute<10){
                    if(elapse<10)
                        time.setText("0"+minute+":0"+elapse);
                    else
                        time.setText("0"+minute+":"+elapse);
                }
                else
                    time.setText(minute+":"+elapse);
            }
        };
        animationTimer.start();

        plantIndex=100;

        int x=10;
        int y=5;

        grid = new Node[x][y];

        tile = new ArrayList<Tiles>(x*y);

        for(int a=0;a<x;a++){
            for (int b=0;b<y;b++){

                tile.add(new Tiles());

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

                if(tile.get(b+(a*y)).getHasRock()) {
                    rock.setImage(new Image(getClass().getResourceAsStream("/pictures/Rock.png")));
                }

                gridTile.add(view,a,b);
                grid[a][b]=view;

                final int i=a;
                final int j=b;

                ((Button)grid[i][j]).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                        public void handle(ActionEvent event){
                        System.out.println(tile.get(j+(i*y)).isPlowed() + " 2 TEST");
                        if(plantIndex==0&&tile.get(j+(i*y)).isPlowed()&&!tile.get(j+(i*y)).isHasPlant()){
                            tile.get(j+(i*y)).setHasPlant(true);
                            tile.get(j+(i*y)).setOccupied(true);
                            seedAnimation(plant,"Beet_Stage",i,j,y,0);
                        }
                        else if(plantIndex==1&&tile.get(j+(i*y)).isPlowed()&&!tile.get(j+(i*y)).isHasPlant()) {
                            tile.get(j+(i*y)).setHasPlant(true);
                            tile.get(j+(i*y)).setOccupied(true);
                            seedAnimation(plant,"Parsnip_Stage",i,j,y,1);
                        }
                        else if(plantIndex==2&&tile.get(j+(i*y)).isPlowed()&&!tile.get(j+(i*y)).isHasPlant()) {
                            tile.get(j+(i*y)).setHasPlant(true);
                            tile.get(j+(i*y)).setOccupied(true);
                            seedAnimation(plant,"Tomato_Stage",i,j,y,2);
                        }
                        else if(plantIndex==3&&tile.get(j+(i*y)).isPlowed()&&!tile.get(j+(i*y)).isHasPlant()) {
                            tile.get(j+(i*y)).setHasPlant(true);
                            tile.get(j+(i*y)).setOccupied(true);
                            seedAnimation(plant,"Potato_Stage",i,j,y,3);
                        }
                        else if(plantIndex==4&&tile.get(j+(i*y)).isPlowed()&&!tile.get(j+(i*y)).isHasPlant()) {
                            tile.get(j+(i*y)).setHasPlant(true);
                            tile.get(j+(i*y)).setOccupied(true);
                            seedAnimation(plant,"Fairy_Rose_Stage",i,j,y,4);
                        }
                        else if(plantIndex==5&&tile.get(j+(i*y)).isPlowed()&&!tile.get(j+(i*y)).isHasPlant()) {
                            tile.get(j+(i*y)).setHasPlant(true);
                            tile.get(j+(i*y)).setOccupied(true);
                            seedAnimation(plant,"Tulip_Stage",i,j,y,5);
                        }
                        else if(plantIndex==6&&tile.get(j+(i*y)).isPlowed()&&!tile.get(j+(i*y)).isHasPlant()) {
                            tile.get(j+(i*y)).setHasPlant(true);
                            tile.get(j+(i*y)).setOccupied(true);
                            seedAnimation(plant,"Summer_Spangle_Stage",i,j,y,6);
                        }
                        else if(plantIndex==7&&tile.get(j+(i*y)).isPlowed()&&!tile.get(j+(i*y)).isHasPlant()) {
                            tile.get(j+(i*y)).setHasPlant(true);
                            tile.get(j+(i*y)).setOccupied(true);
                            seedAnimation(plant,"Sunflower_Stage",i,j,y,7);
                        }
                        else if(plantIndex==8&&tile.get(j+(i*y)).isPlowed()&&!tile.get(j+(i*y)).isHasPlant()) {
                            setOccupyAll(plant, j, i, y);
                        }
                        else if(plantIndex==9&&tile.get(j+(i*y)).isPlowed()&&!tile.get(j+(i*y)).isHasPlant()) {
                            setOccupyAll(plant, j, i, y);
                        }
                        else if(plantIndex==10&&tile.get(j+(i*y)).isPlowed()&&!tile.get(j+(i*y)).isHasPlant()) {
                            setOccupyAll(plant, j, i, y);
                        }
                        else if(plantIndex==11&&tile.get(j+(i*y)).isPlowed()&&!tile.get(j+(i*y)).isHasPlant()) {
                            setOccupyAll(plant, j, i, y);
                        }

                        if (hoeEquipped&&!tile.get(j+(i*y)).getHasRock()&&!tile.get(j+(i*y)).isPlowed()) {
                            tile.get(j+(i*y)).setPlowed(true);
                            image.setImage(new Image(getClass().getResourceAsStream("/pictures/Plowed.png")));
                        }
                        if (pickaxeEquipped) {
                            rock.setDisable(true);
                            rock.setOpacity(0.0);
                            tile.get(j+(i*y)).setHasRock(false);
                            tile.get(j+(i*y)).setOccupied(false);
                        }
                        if (wateringCanEquipped) {
                            if (!tile.get(j+(i*y)).isWatered() && tile.get(j+(i*y)).isPlowed()) {
                                image.setImage(new Image(getClass().getResourceAsStream("/pictures/SoilPlowedWatered.png")));

                            }
                        }
                        if (scytheEquipped) {
                            if(tile.get(j+(i*y)).isHasPlant()){
                                image.setImage(new Image(getClass().getResourceAsStream("/pictures/Soil.png")));
                            }

                        }
                        if (fertilizerEquipped) {

                        }
                        if (cursorEquipped) {
                            if (fruitMenu.isDisabled() && tile.get(j+(i*y)).isPlowed()) {
                                showFruits(view.getLayoutX(), view.getLayoutY(), false, 1.0);
                            } else {
                                showFruits(view.getLayoutX(), view.getLayoutY(), true, 0.0);
                            }
                        }
                    }
                });
            }
        }
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

                amount.setOnMouseClicked(event -> {
                    switch (buyIndex) {
                        case 0:
                            displayCost.setText(Integer.toString(farmer.getTur().getSeedCost() * (int)amount.getValue()));
                        break;
                        case 1:
                            displayCost.setText(Integer.toString(farmer.getC().getSeedCost() * (int)amount.getValue()));
                            break;
                        case 2:
                            displayCost.setText(Integer.toString(farmer.getTo().getSeedCost() * (int)amount.getValue()));
                            break;
                        case 3:
                            displayCost.setText(Integer.toString(farmer.getP().getSeedCost() * (int)amount.getValue()));
                            break;
                        case 4:
                            displayCost.setText(Integer.toString(farmer.getR().getSeedCost() * (int)amount.getValue()));
                            break;
                        case 5:
                            displayCost.setText(Integer.toString(farmer.getTul().getSeedCost() * (int)amount.getValue()));
                            break;
                        case 6:
                            displayCost.setText(Integer.toString(farmer.getSt().getSeedCost() * (int)amount.getValue()));
                            break;
                        case 7:
                            displayCost.setText(Integer.toString(farmer.getSu().getSeedCost() * (int)amount.getValue()));
                            break;
                        case 8:
                            displayCost.setText(Integer.toString(farmer.getM().getSeedCost() * (int)amount.getValue()));
                            break;
                        case 9:
                            displayCost.setText(Integer.toString(farmer.getA().getSeedCost() * (int)amount.getValue()));
                            break;
                        case 10:
                            displayCost.setText(Integer.toString(farmer.getB().getSeedCost() * (int)amount.getValue()));
                            break;
                        case 11:
                            displayCost.setText(Integer.toString(farmer.getO().getSeedCost() * (int)amount.getValue()));
                            break;
                        case 12:
                            displayCost.setText(Integer.toString(farmer.getTool().getFertilizerPrice()*(int)amount.getValue()));
                            break;
                        case 13:
                            displayCost.setText(Integer.toString(farmer.getTur().getSeedCost() * (int)amount.getValue()));
                            break;
                        case 14:
                            displayCost.setText(Integer.toString(farmer.getTur().getSeedCost() * (int)amount.getValue()));
                            break;
                        case 15:
                            displayCost.setText(Integer.toString(farmer.getTur().getSeedCost() * (int)amount.getValue()));
                            break;
                    }
                });

                buy.setOnAction(event -> {
                    if(farmer.getMoney()>=(int)amount.getValue()) {
                        int amt = (int) amount.getValue();
                        switch (buyIndex) {
                            case 0:
                                farmer.getInventory().setTurnipCount(farmer.getInventory().getTurnipCount() + amt);
                                farmer.setMoney(farmer.getMoney() - farmer.getTur().getSeedCost() * (int) amount.getValue());
                                gold.setText(farmer.getMoney() + "");
                                break;
                            case 1:
                                farmer.getInventory().setCarrotCount(farmer.getInventory().getCarrotCount() + amt);
                                break;
                            case 2:
                                farmer.getInventory().setTomatoCount(farmer.getInventory().getTomatoCount() + amt);
                                break;
                            case 3:
                                farmer.getInventory().setPotatoCount(farmer.getInventory().getPotatoCount() + amt);
                                break;
                            case 4:
                                farmer.getInventory().setRoseCount(farmer.getInventory().getRoseCount() + amt);
                                break;
                            case 5:
                                farmer.getInventory().setTulipCount(farmer.getInventory().getTulipCount() + amt);
                                break;
                            case 6:
                                farmer.getInventory().setTurnipCount(farmer.getInventory().getStargazerCount() + amt);
                                break;
                            case 7:
                                farmer.getInventory().setSunflowerCount(farmer.getInventory().getSunflowerCount() + amt);
                                break;
                            case 8:
                                farmer.getInventory().setMangoCount(farmer.getInventory().getMangoCount() + amt);
                                break;
                            case 9:
                                farmer.getInventory().setAppleCount(farmer.getInventory().getAppleCount() + amt);
                                break;
                            case 10:
                                farmer.getInventory().setBananaCount(farmer.getInventory().getBananaCount() + amt);
                                break;
                            case 11:
                                farmer.getInventory().setOrangeCount(farmer.getInventory().getOrangeCount() + amt);
                                break;
                            case 12:
                                farmer.getInventory().setFertilizerCount(farmer.getInventory().getFertilizerCount() + amt);
                                break;
                            case 13:
                                farmer.getInventory().setTurnipCount(farmer.getInventory().getTurnipCount() + amt);
                                break;
                            case 14:
                                farmer.getInventory().setTurnipCount(farmer.getInventory().getTurnipCount() + amt);
                                break;
                            case 15:
                                farmer.getInventory().setTurnipCount(farmer.getInventory().getTurnipCount() + amt);
                                break;
                        }
                    }
                });

                buyIndex=100;

                switch (a+(b*4)) {
                    case 0:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Beet_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Beet_Seeds.png")));
                            previewLabel.setText("Turnip");
                            buyIndex=0;
                            previewDesc.setText(farmer.getTur().display());
                        });
                        break;
                    case 1:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Parsnip_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Parsnip_Seeds.png")));
                            previewLabel.setText("Carrot");
                            buyIndex=1;
                            previewDesc.setText(farmer.getC().display());
                        });
                        break;
                    case 2:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Tomato_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Tomato_Seeds.png")));
                            previewLabel.setText("Tomato");
                            buyIndex=2;
                            previewDesc.setText(farmer.getTo().display());

                        });
                        break;
                    case 3:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Potato_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Potato_Seeds.png")));
                            previewLabel.setText("Potato");
                            buyIndex=3;
                            previewDesc.setText(farmer.getP().display());

                        });
                        break;
                    case 4:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Fairy_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Fairy_Seeds.png")));
                            previewLabel.setText("Rose");
                            buyIndex=4;
                            previewDesc.setText(farmer.getR().display());

                        });
                        break;
                    case 5:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Tulip_Bulb.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Tulip_Bulb.png")));
                            previewLabel.setText("Tulip");
                            buyIndex=5;
                            previewDesc.setText(farmer.getTul().display());

                        });
                        break;
                    case 6:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Spangle_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Spangle_Seeds.png")));
                            previewLabel.setText("Stargazer");
                            buyIndex=6;
                            previewDesc.setText(farmer.getSt().display());

                        });
                        break;
                    case 7:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Sunflower_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Sunflower_Seeds.png")));
                            previewLabel.setText("Sunflower");
                            buyIndex=7;
                            previewDesc.setText(farmer.getSu().display());

                        });
                        break;
                    case 8:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Apricot_Sapling.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Apricot_Sapling.png")));
                            previewLabel.setText("Mango");
                            buyIndex=8;
                            previewDesc.setText(farmer.getM().display());

                        });
                        break;
                    case 9:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Apple_Sapling.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Apple_Sapling.png")));
                            previewLabel.setText("Apple");
                            buyIndex=9;
                            previewDesc.setText(farmer.getA().display());

                        });
                        break;
                    case 10:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/100px-Palm_Stage_1.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/100px-Palm_Stage_1.png")));
                            previewLabel.setText("Banana");
                            buyIndex=10;
                            previewDesc.setText(farmer.getB().display());

                        });
                        break;
                    case 11:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Orange_Sapling.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Orange_Sapling.png")));
                            previewLabel.setText("Orange");
                            buyIndex=11;
                            previewDesc.setText(farmer.getO().display());

                        });
                        break;
                    case 12:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Basic_Fertilizer.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Basic_Fertilizer.png")));
                            previewLabel.setText("Fertilizer");
                            buyIndex=12;
                            previewDesc.setText("Helps Grow Your Plants!");
                        });
                        break;
                    case 13:

                        break;
                    case 14:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Arrowhead.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Arrowhead.png")));
                            previewLabel.setText("Upgrade \nFarmer");
                            buyIndex=14;
                            previewDesc.setText("");
                        });
                        break;
                    case 15:

                        break;

                }

                fruits.setGraphic(fruitImage);
            }
        }



        for(int a=0;a<4;a++){
            for (int b=0;b<4;b++) {

                Button invFruit = new Button();
                invFruit.setMaxHeight(50);
                invFruit.setMaxWidth(50);
                invFruit.setMinHeight(50);
                invFruit.setMinWidth(50);

                ImageView invFruitImage = new ImageView();
                invFruitImage.setFitWidth(50);
                invFruitImage.setFitHeight(50);

                inventoryGrid.add(invFruit,a,b);

                switch (a+(b*4)) {
                    case 0:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Beet_Seeds.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Beet_Seeds.png")));
                            inventoryLabel.setText("Turnip");
                            seedAmount.setText("x"+farmer.getInventory().getTurnipCount()+"");
                            inventoryDesc.setText(farmer.getTur().display());
                        });
                        break;
                    case 1:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Parsnip_Seeds.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Parsnip_Seeds.png")));
                            inventoryLabel.setText("Carrot");
                            seedAmount.setText("x"+farmer.getInventory().getCarrotCount()+"");
                            inventoryDesc.setText(farmer.getC().display());
                        });
                        break;
                    case 2:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Tomato_Seeds.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Tomato_Seeds.png")));
                              inventoryLabel.setText("Tomato");
                            seedAmount.setText(farmer.getInventory().getTomatoCount()+"");
                            inventoryDesc.setText(farmer.getTo().display());
                        });
                        break;
                    case 3:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Potato_Seeds.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Potato_Seeds.png")));
                              inventoryLabel.setText("Potato");
                            seedAmount.setText("x"+farmer.getInventory().getPotatoCount()+"");
                            inventoryDesc.setText(farmer.getP().display());
                        });
                        break;
                    case 4:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Fairy_Seeds.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Fairy_Seeds.png")));
                              inventoryLabel.setText("Rose");
                            seedAmount.setText("x"+farmer.getInventory().getRoseCount()+"");
                            inventoryDesc.setText(farmer.getR().display());
                        });
                        break;
                    case 5:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Tulip_Bulb.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Tulip_Bulb.png")));
                              inventoryLabel.setText("Tulip");
                            seedAmount.setText("x"+farmer.getInventory().getTulipCount()+"");
                            inventoryDesc.setText(farmer.getTul().display());
                        });
                        break;
                    case 6:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Spangle_Seeds.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Spangle_Seeds.png")));
                              inventoryLabel.setText("Stargazer");
                            seedAmount.setText("x"+farmer.getInventory().getStargazerCount()+"");
                            inventoryDesc.setText(farmer.getSt().display());
                        });
                        break;
                    case 7:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Sunflower_Seeds.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Sunflower_Seeds.png")));
                              inventoryLabel.setText("Sunflower");
                            seedAmount.setText("x"+farmer.getInventory().getSunflowerCount()+"");
                            inventoryDesc.setText(farmer.getSu().display());
                        });
                        break;
                    case 8:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Apricot_Sapling.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Apricot_Sapling.png")));
                              inventoryLabel.setText("Mango");
                            seedAmount.setText("x"+farmer.getInventory().getMangoCount()+"");
                            inventoryDesc.setText(farmer.getM().display());
                        });
                        break;
                    case 9:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Apple_Sapling.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Apple_Sapling.png")));
                              inventoryLabel.setText("Apple");
                            seedAmount.setText("x"+farmer.getInventory().getAppleCount()+"");
                            inventoryDesc.setText(farmer.getA().display());
                        });
                        break;
                    case 10:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/100px-Palm_Stage_1.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/100px-Palm_Stage_1.png")));
                              inventoryLabel.setText("Banana");
                            seedAmount.setText("x"+farmer.getInventory().getBananaCount()+"");
                            inventoryDesc.setText(farmer.getB().display());
                        });
                        break;
                    case 11:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Orange_Sapling.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Orange_Sapling.png")));
                              inventoryLabel.setText("Orange");
                            seedAmount.setText("x"+farmer.getInventory().getOrangeCount()+"");
                            inventoryDesc.setText(farmer.getO().display());
                        });
                        break;
                    case 12:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Basic_Fertilizer.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Basic_Fertilizer.png")));
                              inventoryLabel.setText("Fertilizer");
                            seedAmount.setText("x"+farmer.getInventory().getFertilizerCount()+"");
                            inventoryDesc.setText("Helps Grow Your Plants!");
                        });
                        break;
                    case 13:

                        break;
                    case 14:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Arrowhead.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Arrowhead.png")));
                            inventoryLabel.setText("Upgrade \nFarmer");
                            seedAmount.setText("");
                            inventoryDesc.setText("");
                        });
                        break;
                    case 15:

                        break;

                }

                invFruit.setGraphic(invFruitImage);
            }
        }



        storeButton.setOnAction(event -> {
            if(storePane.isDisabled()){
                storePane.setDisable(false);
                storePane.setOpacity(1.0);
                inventoryPane.setDisable(true);
                inventoryPane.setOpacity(0.0);
            }
            else {
                storePane.setDisable(true);
                storePane.setOpacity(0.0);
                inventoryPane.setDisable(true);
                inventoryPane.setOpacity(0.0);
            }
        });
        inventoryButton.setOnAction(event -> {
            if(inventoryPane.isDisabled()){
                inventoryPane.setDisable(false);
                inventoryPane.setOpacity(1.0);
                storePane.setDisable(true);
                storePane.setOpacity(0.0);
            }
            else {
                inventoryPane.setDisable(true);
                inventoryPane.setOpacity(0.0);
                storePane.setDisable(true);
                storePane.setOpacity(0.0);
            }
        });
        storeBack.setOnAction(event -> {
            storePane.setDisable(true);
            storePane.setOpacity(0.0);
        });
        inventoryBack.setOnAction(event -> {
            inventoryPane.setDisable(true);
            inventoryPane.setOpacity(0.0);
        });

        for(int a=0;a<2;a++) { //this is what I need to modify for tree planting
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

                switch (b + (a * 6)) {
                    case 0:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Beet_Seeds.png")));

                        cropsB.setOnAction(event -> {
                            if (farmer.getInventory().getTurnipCount() > 0) {
                                plantIndex = 0;
                                farmer.getInventory().setTurnipCount(farmer.getInventory().getTurnipCount()-1);
                            }
                        });
                        break;
                    case 1:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Parsnip_Seeds.png")));
                        cropsB.setOnAction(event -> {
                            if (farmer.getInventory().getCarrotCount() > 0) {
                                plantIndex = 1;
                                farmer.getInventory().setCarrotCount(farmer.getInventory().getCarrotCount()-1);

                            }
                        });
                        break;
                    case 2:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Tomato_Seeds.png")));
                        cropsB.setOnAction(event -> {
                            if (farmer.getInventory().getTomatoCount() > 0) {
                                plantIndex = 2;
                                farmer.getInventory().setTomatoCount(farmer.getInventory().getTomatoCount()-1);

                            }
                        });
                        break;
                    case 3:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Potato_Seeds.png")));
                        cropsB.setOnAction(event -> {
                            if (farmer.getInventory().getPotatoCount() > 0) {
                                plantIndex = 3;
                                farmer.getInventory().setPotatoCount(farmer.getInventory().getPotatoCount()-1);

                            }
                        });
                        break;
                    case 4:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Fairy_Seeds.png")));
                        cropsB.setOnAction(event -> {
                            if (farmer.getInventory().getRoseCount() > 0) {
                                plantIndex = 4;
                                farmer.getInventory().setRoseCount(farmer.getInventory().getRoseCount()-1);

                            }
                        });
                        break;
                    case 5:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Tulip_Bulb.png")));
                        cropsB.setOnAction(event -> {
                            if (farmer.getInventory().getTulipCount() > 0) {
                                plantIndex = 5;
                                farmer.getInventory().setTulipCount(farmer.getInventory().getTulipCount()-1);

                            }
                        });
                        break;
                    case 6:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Spangle_Seeds.png")));
                        cropsB.setOnAction(event -> {
                            if (farmer.getInventory().getStargazerCount() > 0) {
                                plantIndex = 6;
                                farmer.getInventory().setStargazerCount(farmer.getInventory().getStargazerCount()-1);

                            }
                        });
                        break;
                    case 7:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Sunflower_Seeds.png")));
                        cropsB.setOnAction(event -> {
                            if (farmer.getInventory().getSunflowerCount() > 0) {
                                plantIndex = 7;
                                farmer.getInventory().setSunflowerCount(farmer.getInventory().getSunflowerCount()-1);

                            }
                        });
                        break;
                    case 8:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Apricot_Sapling.png")));
                        cropsB.setOnAction(event -> {
                            if (farmer.getInventory().getMangoCount() > 0) {

                                    plantIndex = 8;
                                    farmer.getInventory().setMangoCount(farmer.getInventory().getMangoCount() - 1);

                            }
                        });
                        break;
                    case 9:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Apple_Sapling.png")));
                        cropsB.setOnAction(event -> {
                            if (farmer.getInventory().getAppleCount() > 0) {
                                plantIndex = 9;
                                farmer.getInventory().setAppleCount(farmer.getInventory().getAppleCount()-1);

                            }
                        });
                        break;
                    case 10:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/100px-Palm_Stage_1.png")));
                        cropsB.setOnAction(event -> {
                            if (farmer.getInventory().getBananaCount() > 0) {
                                plantIndex = 10;
                                farmer.getInventory().setBananaCount(farmer.getInventory().getBananaCount()-1);

                            }
                        });
                        break;
                    case 11:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Orange_Sapling.png")));
                        cropsB.setOnAction(event -> {
                            if (farmer.getInventory().getOrangeCount() > 0) {
                                plantIndex = 11;
                                farmer.getInventory().setOrangeCount(farmer.getInventory().getOrangeCount()-1);

                            }
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

    public void seedAnimation(ImageView plant,String seed,int i,int j, int y,int type){

        tile.get(j+(i*y)).setHasPlant(true);
        tile.get(j+(i*y)).setPlantType(type);
        AnimationTimer animationTimer = new AnimationTimer() {
            long time_now = System.nanoTime();

            @Override
            public void handle(long now) {
                long elapse = (now - time_now)/1000000000;
                if (elapse == 0) {
                    plant.setImage(new Image(getClass().getResourceAsStream("/pictures/"+seed+"_1.png")));
                } else if (elapse == 5) {
                    plant.setImage(new Image(getClass().getResourceAsStream("/pictures/"+seed+"_2.png")));
                } else if (elapse == 10) {
                    plant.setImage(new Image(getClass().getResourceAsStream("/pictures/"+seed+"_3.png")));
                } else if (elapse == 15) {
                    plant.setImage(new Image(getClass().getResourceAsStream("/pictures/"+seed+"_4.png")));
                } else if (elapse == 20){
                    plant.setImage(new Image(getClass().getResourceAsStream("/pictures/"+seed+"_5.png")));
                    stop();
                }
            }
        };
        animationTimer.start();
        plantIndex=100;
    }

    public void setOccupyAll(ImageView plant, int j, int i, int y){
        try {
            if (!tile.get(j + (i - 1) * y).getIsOccupied() && !tile.get(j + (i + 1) * y).getIsOccupied())
                if ((!tile.get((j - 1) + (i - 1) * y).getIsOccupied() && !tile.get((j + 1) + (i - 1) * y).getIsOccupied())
                        && (!tile.get((j - 1) + (i + 1) * y).getIsOccupied() && !tile.get((j + 1) + (i + 1) * y).getIsOccupied())
                        && (!tile.get((j-1)+i*y).getIsOccupied() && !tile.get((j+1)+i*y).getIsOccupied())) {
                    tile.get(j+(i*y)).setHasPlant(true);
                    tile.get(j+(i-1)*y).setHasPlant(true);
                    tile.get((j-1)+(i-1)*y).setHasPlant(true);
                    tile.get((j+1)+(i-1)*y).setHasPlant(true);
                    tile.get(j+(i+1)*y).setHasPlant(true);
                    tile.get((j-1)+(i+1)*y).setHasPlant(true);
                    tile.get((j+1)+(i+1)*y).setHasPlant(true);
                    tile.get((j-1)+i*y).setHasPlant(true);
                    tile.get((j+1)+i*y).setHasPlant(true);
                    seedAnimation(plant, "Apricot_Stage", i, j, y, 8);
                }
                else
                    System.out.println("BAWAL");
        }
        catch (IndexOutOfBoundsException e) {
            seedAnimation(plant, "Apricot_Stage", i, j, y, 8);
        }
    }

}