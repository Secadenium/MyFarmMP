package controller;
import application.Main;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.*;

import java.util.ArrayList;

public class GameController {
    @FXML
    private Label name;
    @FXML
    private GridPane fruitMenu;
    @FXML
    private Label previewLabel;
    @FXML
    private ImageView fruitMenuBack;
    @FXML
    private Button storeButton;
    @FXML
    private AnchorPane storePane;
    @FXML
    private Button storeBack;
    @FXML
    private GridPane gridTile;
    @FXML
    private GridPane toolGrid;
    @FXML
    private GridPane storeGrid;
    @FXML
    private ImageView storePreview;
    @FXML
    private Label previewDesc;
    @FXML
    private Label inventoryLabel;
    @FXML
    private Label inventoryDesc;
    @FXML
    private ImageView inventoryPreview;
    @FXML
    private Button inventoryButton;
    @FXML
    private AnchorPane inventoryPane;
    @FXML
    private GridPane inventoryGrid;
    @FXML
    private Label level;
    @FXML
    private Label gold;
    @FXML
    private Label time;
    @FXML
    private Button buy;
    @FXML
    private Slider amount;
    @FXML
    private Label seedAmount;
    @FXML
    private Label displayCost;
    @FXML
    private Button inventoryBack;
    @FXML
    private Label xpStat;
    @FXML
    private Label itemSelection;
    @FXML
    private Button upgradeButton;
    @FXML
    private AnchorPane upgradePane;
    @FXML
    private Button farmerPic1;
    @FXML
    private Button farmerPic2;
    @FXML
    private Button farmerPic3;
    @FXML
    private Label upgradeText;
    @FXML
    private Button upgradeBuy;
    @FXML private Button tutorialB;
    @FXML private AnchorPane tutorialPane;
    @FXML private Button learn;
    @FXML private Button learnCF;
    @FXML private Button learnFT;
    @FXML private Button learnT;
    @FXML private Button tutorialBack;
    @FXML private Label info;
    @FXML private AnchorPane tutorialCF;
    @FXML private AnchorPane tutorialFT;
    @FXML private AnchorPane tutorialT;
    @FXML private Button tutorialCFBack;
    @FXML private Button tutorialFTBack;
    @FXML private Button tutorialTBack;
    @FXML private Button tutorialFTNext;
    @FXML private Button tutorialFTPrev;
    @FXML private Button tutorialCFNext;
    @FXML private Button tutorialTPrev;
    @FXML private Label infoCF;
    @FXML private Label infoFT;
    @FXML private Label infoT;

    private Main main;
    private Farmer farmer;
    private Node[][] grid, seedGrid;
    private boolean hoeEquipped;
    private boolean pickaxeEquipped;
    private boolean wateringCanEquipped;
    private boolean scytheEquipped;
    private boolean fertilizerEquipped;
    private boolean cursorEquipped;
    private int plantIndex;
    private int buyIndex;
    private int upgradeIndex;

    private ArrayList<Tiles> tile;

    public void initialize() {
        main = new Main();
        farmer = new Farmer();

        farmer.setName(Main.getNameName());

        name.setText(Main.getNameName());
        level.setText(farmer.getLevel() + "");
        gold.setText(farmer.getMoney() + "");

        AnimationTimer animationTimer = new AnimationTimer() {
            long time_now = System.nanoTime();

            @Override
            public void handle(long now) {
                long elapse = (now - time_now) / 1000000000;
                long minute = elapse / 60;
                elapse %= 60;
                if (minute < 10) {
                    if (elapse < 10)
                        time.setText("0" + minute + ":0" + elapse);
                    else
                        time.setText("0" + minute + ":" + elapse);
                }
                else if(elapse<10) {
                        time.setText("" + minute + ":0" + elapse);
                }
                else
                    time.setText(minute + ":" + elapse);

                gold.setText("" + farmer.getMoney());
                xpStat.setText(farmer.getExp() + " / " + (farmer.getLevel() * 100));
                level.setText("" + farmer.getLevel());
                if (farmer.getExp() >= farmer.getLevel() * 100) {
                    farmer.setExp(farmer.getExp() - (farmer.getLevel() * 100));
                    farmer.setLevel(farmer.getLevel() + 1);
                    if(farmer.getLevel()%10==0){
                        levelUp();
                    }
                }

            }
        };

        animationTimer.start();

        plantIndex = 100;
        upgradeIndex=100;

        int x = 10;
        int y = 5;

        grid = new Node[x][y];

        tile = new ArrayList<Tiles>(x * y);

        for (int a = 0; a < x; a++) {
            for (int b = 0; b < y; b++) {

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

                if (tile.get(b + (a * y)).getHasRock()) {
                    rock.setImage(new Image(getClass().getResourceAsStream("/pictures/Rock.png")));
                }

                gridTile.add(view, a, b);
                grid[a][b] = view;

                final int i = a;
                final int j = b;

                ((Button) grid[i][j]).setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ((Button) grid[i][j]).setTooltip(new Tooltip("Ready for Plowing!"));
                        if (tile.get(j + (i * y)).isHasPlant()) {
                            ((Button) grid[i][j]).setTooltip(new Tooltip(tile.get(j + (i * y)).getCrop().displayInfoCrop() +
                                    "\nWater Used: " + tile.get(j + (i * y)).getWaterUsed() + "\nFertilizer Used: " + tile.get(j + (i * y)).getFertilizerUsed()));
                        } else if (tile.get(j + (i * y)).isPlowed()) {
                            ((Button) grid[i][j]).setTooltip(new Tooltip("Ready for Planting!"));
                        } else if (tile.get(j + (i * y)).isHasRock()) {
                            ((Button) grid[i][j]).setTooltip(new Tooltip("Destroy Me!"));
                        } else if (tile.get(j + (i * y)).isHasWither()) {
                            ((Button) grid[i][j]).setTooltip(new Tooltip("Plow Me Out!"));
                        }
                    }
                });

                ((Button) grid[i][j]).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println(tile.get(j + (i * y)).isPlowed() + " 2 TEST");
                        if (!tile.get(j + (i * y)).isHasPlant()&&!tile.get(j+(i*y)).getIsOccupied()) {
                            if (plantIndex == 0 && tile.get(j + (i * y)).isPlowed()&&!tile.get(j+(i*y)).getIsOccupied()) {
                                seedAnimation(plant, "Beet_Stage", i, j, y, 0);
                                farmer.getInventory().setTurnipCount(farmer.getInventory().getTurnipCount() - 1);
                            } else if (plantIndex == 1 && tile.get(j + (i * y)).isPlowed() && !tile.get(j + (i * y)).getIsOccupied()) {
                                seedAnimation(plant, "Parsnip_Stage", i, j, y, 1);
                                farmer.getInventory().setCarrotCount(farmer.getInventory().getCarrotCount() - 1);
                            } else if (plantIndex == 2 && tile.get(j + (i * y)).isPlowed() && !tile.get(j + (i * y)).getIsOccupied()) {
                                seedAnimation(plant, "Tomato_Stage", i, j, y, 2);
                                farmer.getInventory().setTomatoCount(farmer.getInventory().getTomatoCount() - 1);
                            } else if (plantIndex == 3 && tile.get(j + (i * y)).isPlowed() && !tile.get(j + (i * y)).getIsOccupied()) {
                                seedAnimation(plant, "Potato_Stage", i, j, y, 3);
                                farmer.getInventory().setPotatoCount(farmer.getInventory().getPotatoCount() - 1);
                            } else if (plantIndex == 4 && tile.get(j + (i * y)).isPlowed() && !tile.get(j + (i * y)).getIsOccupied()) {
                                seedAnimation(plant, "Fairy_Rose_Stage", i, j, y, 4);
                                farmer.getInventory().setRoseCount(farmer.getInventory().getRoseCount() - 1);
                            } else if (plantIndex == 5 && tile.get(j + (i * y)).isPlowed() && !tile.get(j + (i * y)).getIsOccupied()) {
                                seedAnimation(plant, "Tulip_Stage", i, j, y, 5);
                                farmer.getInventory().setTulipCount(farmer.getInventory().getTulipCount() - 1);
                            } else if (plantIndex == 6 && tile.get(j + (i * y)).isPlowed() && !tile.get(j + (i * y)).getIsOccupied()) {
                                seedAnimation(plant, "Summer_Spangle_Stage", i, j, y, 6);
                                farmer.getInventory().setStargazerCount(farmer.getInventory().getStargazerCount() - 1);
                            } else if (plantIndex == 7 && tile.get(j + (i * y)).isPlowed() && !tile.get(j + (i * y)).getIsOccupied()) {
                                seedAnimation(plant, "Sunflower_Stage", i, j, y, 7);
                                farmer.getInventory().setSunflowerCount(farmer.getInventory().getSunflowerCount() - 1);
                            } else if (plantIndex == 8 && tile.get(j + (i * y)).isPlowed() && !tile.get(j + (i * y)).getIsOccupied()) {
                                setOccupyAll(plant, j, i, y);
                            } else if (plantIndex == 9 && tile.get(j + (i * y)).isPlowed() && !tile.get(j + (i * y)).getIsOccupied()) {
                                setOccupyAll(plant, j, i, y);
                            } else if (plantIndex == 10 && tile.get(j + (i * y)).isPlowed() && !tile.get(j + (i * y)).getIsOccupied()) {
                                setOccupyAll(plant, j, i, y);
                            } else if (plantIndex == 11 && tile.get(j + (i * y)).isPlowed() && !tile.get(j + (i * y)).getIsOccupied()) {
                                setOccupyAll(plant, j, i, y);
                            }
                        }

                        if (hoeEquipped) {
                            if (!tile.get(j + (i * y)).getHasRock() && !tile.get(j + (i * y)).isPlowed()) {
                                tile.get(j + (i * y)).setPlowed(true);
                                image.setImage(new Image(getClass().getResourceAsStream("/pictures/Plowed.png")));
                            } else if (tile.get(j + (i * y)).getCrop().isWithered() && farmer.getMoney() > 1 && tile.get(j + (i * y)).isHasPlant()) {
                                    image.setImage(new Image(getClass().getResourceAsStream("/pictures/Soil.png")));
                                    plant.imageProperty().set(null);
                                    farmer.setMoney(farmer.getMoney() - 2);

                                    tile.get(j + (i * y)).getCrop().setIsWithered(false);
                                    tile.get(j + (i * y)).setHasPlant(false);
                                    tile.get(j + (i * y)).setOccupied(false);
                                    tile.get(j + (i * y)).setPlowed(false);
                                    tile.get(j + (i * y)).setHasRock(false);
                                    farmer.setExp(farmer.getExp()+5);

                                    ((Button) grid[i][j]).setTooltip(new Tooltip("Ready for Plowing!"));
                                    tile.get(j + (i * y)).setWaterUsed(0);
                                    tile.get(j + (i * y)).setFertilizerUsed(0);

                            }
                        }
                        if (pickaxeEquipped) {
                            rock.setDisable(true);
                            rock.setOpacity(0.0);
                            tile.get(j + (i * y)).setHasRock(false);
                            tile.get(j + (i * y)).setOccupied(false);
                            farmer.setExp(farmer.getExp()+10);
                        }
                        if (wateringCanEquipped) {
                            if (tile.get(j + (i * y)).isPlowed() && tile.get(j + (i * y)).isHasPlant() && (tile.get(j + (i * y)).getCrop().getWaterBonusLimit() > tile.get(j + (i * y)).getWaterUsed())) {
                                image.setImage(new Image(getClass().getResourceAsStream("/pictures/SoilPlowedWatered.png")));
                                tile.get(j + (i * y)).setWaterUsed(tile.get(j + (i * y)).getWaterUsed() + 1);
                                if(tile.get(j+(i*y)).getFertilizerUsed()>0){
                                    image.setImage(new Image(getClass().getResourceAsStream("/pictures/Fertilized_Soil_Water.png")));
                                }
                            }
                        }
                        if (scytheEquipped) {
                            if (tile.get(j + (i * y)).isHasPlant() && tile.get(j + (i * y)).getCrop().isHarvestable()) {
                                image.setImage(new Image(getClass().getResourceAsStream("/pictures/Soil.png")));
                                plant.imageProperty().set(null);
                                if (tile.get(j + (i * y)).getFertilizerUsed() >= tile.get(j + (i * y)).getCrop().getFertilizerNeeded() && tile.get(j + (i * y)).getFertilizerUsed() <= tile.get(j + (i * y)).getCrop().getFertilizerBonusLimit()) {
                                    tile.get(j + (i * y)).setFertilizerUsed(tile.get(j + (i * y)).getFertilizerUsed());
                                } else if (tile.get(j + (i * y)).getFertilizerUsed() > tile.get(j + (i * y)).getCrop().getFertilizerBonusLimit()) {
                                    tile.get(j + (i * y)).setFertilizerUsed(tile.get(j + (i * y)).getCrop().getFertilizerBonusLimit());
                                }
                                farmer.setMoney(((farmer.getMoney() + tile.get(j + (i * y)).getCrop().computeSellPrice() + ((((tile.get(j + (i * y)).getWaterUsed()
                                        * (tile.get(j + (i * y)).getCrop().getBaseSell() * 0.25)) + (tile.get(j + (i * y)).getFertilizerUsed() * (tile.get(j + (i * y)).getCrop().getBaseSell() * 0.5)))
                                        * tile.get(j + (i * y)).getCrop().getProductsProd()) - tile.get(j + (i * y)).getCrop().getHarvestCost()))));
                                if (tile.get(j + (i * y)).getCrop().getCropType() == 1) {
                                    farmer.setMoney(farmer.getMoney() + ((tile.get(j + (i * y)).getCrop().getBaseSell() + ((tile.get(j + (i * y)).getWaterUsed()
                                            * (tile.get(j + (i * y)).getCrop().getBaseSell() * 0.25)) + (tile.get(j + (i * y)).getFertilizerUsed() * (tile.get(j + (i * y)).getCrop().getBaseSell() * 0.5)))) * 0.05));
                                }
                                farmer.setExp(farmer.getExp() + tile.get(j + (i * y)).getCrop().getExpUp());
                                tile.get(j + (i * y)).getCrop().setIsHarvestable(false);
                                tile.get(j + (i * y)).getCrop().setIsWithered(false);
                                tile.get(j + (i * y)).setHasPlant(false);
                                tile.get(j + (i * y)).setOccupied(false);
                                tile.get(j + (i * y)).setPlowed(false);
                                tile.get(j + (i * y)).setHasRock(false);

                                ((Button) grid[i][j]).setTooltip(new Tooltip("Ready for Plowing!"));
                                tile.get(j + (i * y)).setWaterUsed(0);
                                tile.get(j + (i * y)).setFertilizerUsed(0);
                            }
                        }
                        if (fertilizerEquipped) {
                            if (farmer.getInventory().getFertilizerCount() > 0 && !tile.get(j + (i * y)).isHasPlant() && tile.get(j + (i * y)).isPlowed()) {
                                image.setImage(new Image(getClass().getResourceAsStream("/pictures/Fertilized_Soil.png")));
                                tile.get(j + (i * y)).setFertilizerUsed(tile.get(j + (i * y)).getFertilizerUsed() + 1);
                                farmer.getInventory().setFertilizerCount(farmer.getInventory().getFertilizerCount() - 1);
                            }
                        }
                        if (cursorEquipped) {
                            if (fruitMenu.isDisabled() && tile.get(j + (i * y)).isPlowed()) {
                                showFruits(view.getLayoutX(), view.getLayoutY(), false, 1.0);
                            } else {
                                showFruits(view.getLayoutX(), view.getLayoutY(), true, 0.0);
                            }
                        }
                    }
                });
            }
        }
        for (int a = 0; a < 6; a++) {
            for (int b = 0; b < 1; b++) {
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

                toolGrid.add(tools, a, b);


                switch (a) {
                    case 0:
                        tools.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                tools.setTooltip(new Tooltip("The hoe will allow you to plow land when the soil is unplowed" +
                                        "\nand will remove withered crops"));
                            }
                        });
                        tools.setOnAction(event -> {
                            if (hoeEquipped == false) {
                                itemSelection.setText("Tool Equipped: Hoe");
                                hoeEquipped = true;
                                pickaxeEquipped = false;
                                wateringCanEquipped = false;
                                scytheEquipped = false;
                                fertilizerEquipped = false;
                                cursorEquipped = false;
                            } else {
                                itemSelection.setText("Tool Equipped: None");
                                hoeEquipped = false;
                            }
                        });
                        break;
                    case 1:
                        tools.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                tools.setTooltip(new Tooltip("The pickaxe will allow you to destroy existing rocks"));
                            }
                        });
                        tools.setOnAction(event -> {
                            if (pickaxeEquipped == false) {
                                itemSelection.setText("Tool Equipped: Pickaxe");
                                pickaxeEquipped = true;
                                hoeEquipped = false;
                                wateringCanEquipped = false;
                                scytheEquipped = false;
                                fertilizerEquipped = false;
                                cursorEquipped = false;
                            } else {
                                itemSelection.setText("Tool Equipped: None");
                                pickaxeEquipped = false;
                            }
                        });
                        break;
                    case 2:
                        tools.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                tools.setTooltip(new Tooltip("The watering can will allow you to water plants to keep them alive" +
                                        "\nand gives additional bonuses."));
                            }
                        });
                        tools.setOnAction(event -> {
                            if (wateringCanEquipped == false) {
                                itemSelection.setText("Tool Equipped: Watering Can");
                                wateringCanEquipped = true;
                                hoeEquipped = false;
                                pickaxeEquipped = false;
                                scytheEquipped = false;
                                fertilizerEquipped = false;
                                cursorEquipped = false;
                            } else {
                                itemSelection.setText("Tool Equipped: None");
                                wateringCanEquipped = false;
                            }
                        });
                        break;
                    case 3:
                        tools.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                tools.setTooltip(new Tooltip("The scythe allows you to harvest grown plants"));
                            }
                        });
                        tools.setOnAction(event -> {
                            if (scytheEquipped == false) {
                                itemSelection.setText("Tool Equipped: Scythe");
                                scytheEquipped = true;
                                hoeEquipped = false;
                                pickaxeEquipped = false;
                                wateringCanEquipped = false;
                                fertilizerEquipped = false;
                                cursorEquipped = false;
                            } else {
                                itemSelection.setText("Tool Equipped: None");
                                scytheEquipped = false;
                            }
                        });
                        break;
                    case 4:
                        tools.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                tools.setTooltip(new Tooltip("The fertilizer helps grow the plants but becareful, " +
                                        "\nyou can only use fertilizer when no plant is present."));
                            }
                        });
                        tools.setOnAction(event -> {
                            if (fertilizerEquipped == false) {
                                itemSelection.setText("Tool Equipped: Fertilizer");
                                fertilizerEquipped = true;
                                hoeEquipped = false;
                                pickaxeEquipped = false;
                                wateringCanEquipped = false;
                                scytheEquipped = false;
                                cursorEquipped = false;
                            } else {
                                itemSelection.setText("Tool Equipped: None");
                                fertilizerEquipped = false;
                            }
                        });
                        break;
                    case 5:
                        tools.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                tools.setTooltip(new Tooltip("The cursor tool allows you to click on plowed tiles to be able" +
                                        "\nto select seeds for planting."));
                            }
                        });
                        tools.setOnAction(event -> {
                            if (cursorEquipped == false) {
                                itemSelection.setText("Tool Equipped: Cursor");
                                hoeEquipped = false;
                                pickaxeEquipped = false;
                                wateringCanEquipped = false;
                                scytheEquipped = false;
                                fertilizerEquipped = false;
                                cursorEquipped = true;
                            } else {
                                itemSelection.setText("Tool Equipped: None");
                                cursorEquipped = false;
                            }

                        });
                        break;
                }
            }
        }

        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {

                Button fruits = new Button();
                fruits.setMaxHeight(50);
                fruits.setMaxWidth(50);
                fruits.setMinHeight(50);
                fruits.setMinWidth(50);

                ImageView fruitImage = new ImageView();
                fruitImage.setFitWidth(50);
                fruitImage.setFitHeight(50);

                storeGrid.add(fruits, a, b);

                amount.setOnMouseClicked(event -> {
                    switch (buyIndex) {
                        case 0:
                            displayCost.setText(Integer.toString(farmer.getTur().getSeedCost() * (int) amount.getValue()));
                            break;
                        case 1:
                            displayCost.setText(Integer.toString(farmer.getC().getSeedCost() * (int) amount.getValue()));
                            break;
                        case 2:
                            displayCost.setText(Integer.toString(farmer.getTo().getSeedCost() * (int) amount.getValue()));
                            break;
                        case 3:
                            displayCost.setText(Integer.toString(farmer.getP().getSeedCost() * (int) amount.getValue()));
                            break;
                        case 4:
                            displayCost.setText(Integer.toString(farmer.getR().getSeedCost() * (int) amount.getValue()));
                            break;
                        case 5:
                            displayCost.setText(Integer.toString(farmer.getTul().getSeedCost() * (int) amount.getValue()));
                            break;
                        case 6:
                            displayCost.setText(Integer.toString(farmer.getSt().getSeedCost() * (int) amount.getValue()));
                            break;
                        case 7:
                            displayCost.setText(Integer.toString(farmer.getSu().getSeedCost() * (int) amount.getValue()));
                            break;
                        case 8:
                            displayCost.setText(Integer.toString(farmer.getM().getSeedCost() * (int) amount.getValue()));
                            break;
                        case 9:
                            displayCost.setText(Integer.toString(farmer.getA().getSeedCost() * (int) amount.getValue()));
                            break;
                        case 10:
                            displayCost.setText(Integer.toString(farmer.getB().getSeedCost() * (int) amount.getValue()));
                            break;
                        case 11:
                            displayCost.setText(Integer.toString(farmer.getO().getSeedCost() * (int) amount.getValue()));
                            break;
                        case 12:
                            displayCost.setText(Integer.toString(farmer.getTool().getFertilizerPrice() * (int) amount.getValue()));
                            break;
                    }
                });

                buy.setOnAction(event -> {
                    int amt = (int) amount.getValue();
                    switch (buyIndex) {
                        case 0:
                            if (farmer.getMoney() >= farmer.getTur().getSeedCost() * (int) amount.getValue()) {
                                farmer.getInventory().setTurnipCount(farmer.getInventory().getTurnipCount() + amt);
                                farmer.setMoney(farmer.getMoney() - farmer.getTur().getSeedCost() * (int) amount.getValue());
                            }
                            break;
                        case 1:
                            if (farmer.getMoney() >= farmer.getC().getSeedCost() * (int) amount.getValue()) {
                                farmer.getInventory().setCarrotCount(farmer.getInventory().getCarrotCount() + amt);
                                farmer.setMoney(farmer.getMoney() - farmer.getC().getSeedCost() * (int) amount.getValue());
                            }
                            break;
                        case 2:
                            if (farmer.getMoney() >= farmer.getTo().getSeedCost() * (int) amount.getValue()) {
                                farmer.getInventory().setTomatoCount(farmer.getInventory().getTomatoCount() + amt);
                                farmer.setMoney(farmer.getMoney() - farmer.getTo().getSeedCost() * (int) amount.getValue());
                            }
                            break;
                        case 3:
                            if (farmer.getMoney() >= farmer.getP().getSeedCost() * (int) amount.getValue()) {
                                farmer.getInventory().setPotatoCount(farmer.getInventory().getPotatoCount() + amt);
                                farmer.setMoney(farmer.getMoney() - farmer.getP().getSeedCost() * (int) amount.getValue());
                            }
                            break;
                        case 4:
                            if (farmer.getMoney() >= farmer.getR().getSeedCost() * (int) amount.getValue()) {
                                farmer.getInventory().setRoseCount(farmer.getInventory().getRoseCount() + amt);
                                farmer.setMoney(farmer.getMoney() - farmer.getR().getSeedCost() * (int) amount.getValue());
                            }
                            break;
                        case 5:
                            if (farmer.getMoney() >= farmer.getTul().getSeedCost() * (int) amount.getValue()) {
                                farmer.getInventory().setTulipCount(farmer.getInventory().getTulipCount() + amt);
                                farmer.setMoney(farmer.getMoney() - farmer.getTul().getSeedCost() * (int) amount.getValue());
                            }
                            break;
                        case 6:
                            if (farmer.getMoney() >= farmer.getSt().getSeedCost() * (int) amount.getValue()) {
                                farmer.getInventory().setStargazerCount(farmer.getInventory().getStargazerCount() + amt);
                                farmer.setMoney(farmer.getMoney() - farmer.getSt().getSeedCost() * (int) amount.getValue());
                            }
                            break;
                        case 7:
                            if (farmer.getMoney() >= farmer.getSu().getSeedCost() * (int) amount.getValue()) {
                                farmer.getInventory().setSunflowerCount(farmer.getInventory().getSunflowerCount() + amt);
                                farmer.setMoney(farmer.getMoney() - farmer.getSu().getSeedCost() * (int) amount.getValue());
                            }
                            break;
                        case 8:
                            if (farmer.getMoney() >= farmer.getM().getSeedCost() * (int) amount.getValue()) {
                                farmer.getInventory().setMangoCount(farmer.getInventory().getMangoCount() + amt);
                                farmer.setMoney(farmer.getMoney() - farmer.getM().getSeedCost() * (int) amount.getValue());
                            }
                            break;
                        case 9:
                            if (farmer.getMoney() >= farmer.getA().getSeedCost() * (int) amount.getValue()) {
                                farmer.getInventory().setAppleCount(farmer.getInventory().getAppleCount() + amt);
                                farmer.setMoney(farmer.getMoney() - farmer.getA().getSeedCost() * (int) amount.getValue());
                            }
                            break;
                        case 10:
                            if (farmer.getMoney() >= farmer.getB().getSeedCost() * (int) amount.getValue()) {
                                farmer.getInventory().setBananaCount(farmer.getInventory().getBananaCount() + amt);
                                farmer.setMoney(farmer.getMoney() - farmer.getB().getSeedCost() * (int) amount.getValue());
                            }
                            break;
                        case 11:
                            if (farmer.getMoney() >= farmer.getO().getSeedCost() * (int) amount.getValue()) {
                                farmer.getInventory().setOrangeCount(farmer.getInventory().getOrangeCount() + amt);
                                farmer.setMoney(farmer.getMoney() - farmer.getO().getSeedCost() * (int) amount.getValue());
                            }
                            break;
                        case 12:
                            if (farmer.getMoney() >= farmer.getTool().getFertilizerPrice() * (int) amount.getValue()) {
                                farmer.getInventory().setFertilizerCount(farmer.getInventory().getFertilizerCount() + amt);
                                farmer.setMoney(farmer.getMoney() - farmer.getTool().getFertilizerPrice() * (int) amount.getValue());
                            }
                            break;
                    }
                    farmer.setExp(farmer.getExp()+10);
                });

                buyIndex = 100;

                switch (a + (b * 4)) {
                    case 0:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Beet_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Beet_Seeds.png")));
                            previewLabel.setText("Turnip");
                            buyIndex = 0;
                            previewDesc.setText(farmer.getTur().display());
                        });
                        break;
                    case 1:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Parsnip_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Parsnip_Seeds.png")));
                            previewLabel.setText("Carrot");
                            buyIndex = 1;
                            previewDesc.setText(farmer.getC().display());
                        });
                        break;
                    case 2:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Tomato_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Tomato_Seeds.png")));
                            previewLabel.setText("Tomato");
                            buyIndex = 2;
                            previewDesc.setText(farmer.getTo().display());
                        });
                        break;
                    case 3:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Potato_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Potato_Seeds.png")));
                            previewLabel.setText("Potato");
                            buyIndex = 3;
                            previewDesc.setText(farmer.getP().display());
                        });
                        break;
                    case 4:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Fairy_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Fairy_Seeds.png")));
                            previewLabel.setText("Rose");
                            buyIndex = 4;
                            previewDesc.setText(farmer.getR().display());
                        });
                        break;
                    case 5:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Tulip_Bulb.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Tulip_Bulb.png")));
                            previewLabel.setText("Tulip");
                            buyIndex = 5;
                            previewDesc.setText(farmer.getTul().display());
                        });
                        break;
                    case 6:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Spangle_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Spangle_Seeds.png")));
                            previewLabel.setText("Stargazer");
                            buyIndex = 6;
                            previewDesc.setText(farmer.getSt().display());
                        });
                        break;
                    case 7:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Sunflower_Seeds.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Sunflower_Seeds.png")));
                            previewLabel.setText("Sunflower");
                            buyIndex = 7;
                            previewDesc.setText(farmer.getSu().display());
                        });
                        break;
                    case 8:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Apricot_Sapling.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Apricot_Sapling.png")));
                            previewLabel.setText("Mango");
                            buyIndex = 8;
                            previewDesc.setText(farmer.getM().display());
                        });
                        break;
                    case 9:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Apple_Sapling.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Apple_Sapling.png")));
                            previewLabel.setText("Apple");
                            buyIndex = 9;
                            previewDesc.setText(farmer.getA().display());
                        });
                        break;
                    case 10:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/100px-Palm_Stage_1.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/100px-Palm_Stage_1.png")));
                            previewLabel.setText("Banana");
                            buyIndex = 10;
                            previewDesc.setText(farmer.getB().display());
                        });
                        break;
                    case 11:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Orange_Sapling.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Orange_Sapling.png")));
                            previewLabel.setText("Orange");
                            buyIndex = 11;
                            previewDesc.setText(farmer.getO().display());
                        });
                        break;
                    case 12:
                        fruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Basic_Fertilizer.png")));
                        fruits.setOnAction(event -> {
                            System.out.println("Image Test");
                            storePreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Basic_Fertilizer.png")));
                            previewLabel.setText("Fertilizer");
                            buyIndex = 12;
                            previewDesc.setText("Helps Grow Your Plants!");
                        });
                        break;
                }
                fruits.setGraphic(fruitImage);
            }
        }

        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {

                Button invFruit = new Button();
                invFruit.setMaxHeight(50);
                invFruit.setMaxWidth(50);
                invFruit.setMinHeight(50);
                invFruit.setMinWidth(50);

                ImageView invFruitImage = new ImageView();
                invFruitImage.setFitWidth(50);
                invFruitImage.setFitHeight(50);

                inventoryGrid.add(invFruit, a, b);

                switch (a + (b * 4)) {
                    case 0:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Beet_Seeds.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Beet_Seeds.png")));
                            inventoryLabel.setText("Turnip");
                            seedAmount.setText("x" + farmer.getInventory().getTurnipCount() + "");
                            inventoryDesc.setText(farmer.getTur().display());
                        });
                        break;
                    case 1:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Parsnip_Seeds.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Parsnip_Seeds.png")));
                            inventoryLabel.setText("Carrot");
                            seedAmount.setText("x" + farmer.getInventory().getCarrotCount() + "");
                            inventoryDesc.setText(farmer.getC().display());
                        });
                        break;
                    case 2:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Tomato_Seeds.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Tomato_Seeds.png")));
                            inventoryLabel.setText("Tomato");
                            seedAmount.setText("x" + farmer.getInventory().getTomatoCount() + "");
                            inventoryDesc.setText(farmer.getTo().display());
                        });
                        break;
                    case 3:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Potato_Seeds.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Potato_Seeds.png")));
                            inventoryLabel.setText("Potato");
                            seedAmount.setText("x" + farmer.getInventory().getPotatoCount() + "");
                            inventoryDesc.setText(farmer.getP().display());
                        });
                        break;
                    case 4:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Fairy_Seeds.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Fairy_Seeds.png")));
                            inventoryLabel.setText("Rose");
                            seedAmount.setText("x" + farmer.getInventory().getRoseCount() + "");
                            inventoryDesc.setText(farmer.getR().display());
                        });
                        break;
                    case 5:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Tulip_Bulb.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Tulip_Bulb.png")));
                            inventoryLabel.setText("Tulip");
                            seedAmount.setText("x" + farmer.getInventory().getTulipCount() + "");
                            inventoryDesc.setText(farmer.getTul().display());
                        });
                        break;
                    case 6:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Spangle_Seeds.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Spangle_Seeds.png")));
                            inventoryLabel.setText("Stargazer");
                            seedAmount.setText("x" + farmer.getInventory().getStargazerCount() + "");
                            inventoryDesc.setText(farmer.getSt().display());
                        });
                        break;
                    case 7:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Sunflower_Seeds.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Sunflower_Seeds.png")));
                            inventoryLabel.setText("Sunflower");
                            seedAmount.setText("x" + farmer.getInventory().getSunflowerCount() + "");
                            inventoryDesc.setText(farmer.getSu().display());
                        });
                        break;
                    case 8:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Apricot_Sapling.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Apricot_Sapling.png")));
                            inventoryLabel.setText("Mango");
                            seedAmount.setText("x" + farmer.getInventory().getMangoCount() + "");
                            inventoryDesc.setText(farmer.getM().display());
                        });
                        break;
                    case 9:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Apple_Sapling.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Apple_Sapling.png")));
                            inventoryLabel.setText("Apple");
                            seedAmount.setText("x" + farmer.getInventory().getAppleCount() + "");
                            inventoryDesc.setText(farmer.getA().display());
                        });
                        break;
                    case 10:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/100px-Palm_Stage_1.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/100px-Palm_Stage_1.png")));
                            inventoryLabel.setText("Banana");
                            seedAmount.setText("x" + farmer.getInventory().getBananaCount() + "");
                            inventoryDesc.setText(farmer.getB().display());
                        });
                        break;
                    case 11:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Orange_Sapling.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Orange_Sapling.png")));
                            inventoryLabel.setText("Orange");
                            seedAmount.setText("x" + farmer.getInventory().getOrangeCount() + "");
                            inventoryDesc.setText(farmer.getO().display());
                        });
                        break;
                    case 12:
                        invFruitImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Basic_Fertilizer.png")));
                        invFruit.setOnAction(event -> {
                            System.out.println("Image Test");
                            inventoryPreview.setImage(new Image(getClass().getResourceAsStream("/pictures/Basic_Fertilizer.png")));
                            inventoryLabel.setText("Fertilizer");
                            seedAmount.setText("x" + farmer.getInventory().getFertilizerCount() + "");
                            inventoryDesc.setText("Helps Grow Your Plants!");
                        });
                        break;
                }
                invFruit.setGraphic(invFruitImage);
            }
        }

        storeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                storeButton.setTooltip(new Tooltip("This is where you can buy seeds, fertilizers and upgrade you farmer!"));
            }
        });
        storeButton.setOnAction(event -> {
            if (storePane.isDisabled()) {
                storePane.setDisable(false);
                storePane.setOpacity(1.0);
                inventoryPane.setDisable(true);
                inventoryPane.setOpacity(0.0);
                upgradePane.setDisable(true);
                upgradePane.setOpacity(0.0);
                tutorialPane.setDisable(true);
                tutorialPane.setOpacity(0.0);
                tutorialCF.setDisable(true);
                tutorialCF.setOpacity(0.0);
                tutorialFT.setDisable(true);
                tutorialFT.setOpacity(0.0);
                tutorialT.setDisable(true);
                tutorialT.setOpacity(0.0);
            } else {
                closePanels();
            }
        });
        inventoryButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                inventoryButton.setTooltip(new Tooltip("This is where you can check how many seeds and fertilizers you have" +
                        "\nand their information."));
            }
        });
        inventoryButton.setOnAction(event -> {
            if (inventoryPane.isDisabled()) {
                inventoryPane.setDisable(false);
                inventoryPane.setOpacity(1.0);
                storePane.setDisable(true);
                storePane.setOpacity(0.0);
                upgradePane.setDisable(true);
                upgradePane.setOpacity(0.0);
                tutorialPane.setDisable(true);
                tutorialPane.setOpacity(0.0);
                tutorialCF.setDisable(true);
                tutorialCF.setOpacity(0.0);
                tutorialFT.setDisable(true);
                tutorialFT.setOpacity(0.0);
                tutorialT.setDisable(true);
                tutorialT.setOpacity(0.0);
            } else {
                closePanels();
            }
        });
        upgradeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                upgradeButton.setTooltip(new Tooltip("This is where you can upgrade your farmer for more benefits!"));
            }
        });

        upgradeButton.setOnAction(event -> {
            if (upgradePane.isDisabled()) {
                upgradePane.setDisable(false);
                upgradePane.setOpacity(1.0);
                inventoryPane.setDisable(true);
                inventoryPane.setOpacity(0.0);
                storePane.setDisable(true);
                storePane.setOpacity(0.0);
                tutorialPane.setDisable(true);
                tutorialPane.setOpacity(0.0);
                tutorialCF.setDisable(true);
                tutorialCF.setOpacity(0.0);
                tutorialFT.setDisable(true);
                tutorialFT.setOpacity(0.0);
                tutorialT.setDisable(true);
                tutorialT.setOpacity(0.0);
            } else {
                closePanels();
            }
        });

        upgradeButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/pictures/Arrowhead.png"))));
        upgradeBuy.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/pictures/Arrowhead.png"))));

        farmerPic1.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/pictures/upgrade1.png"))));
        farmerPic1.setOnAction(event -> {
            upgradeText.setText("Registered Farmer (Lvl 10 & 200 OC): \nEarning/Buying: +/- 2 \nWater/Fertilizer Bonus Limit: +0 \nHarvest Time: -5%");
            upgradeIndex = 1;
        });
        farmerPic2.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/pictures/upgrade2.png"))));
        farmerPic2.setOnAction(event -> {
            upgradeText.setText("Distinguished Farmer (Lvl 15 & 250 OC): \nEarning/Buying: +/- 3 \nWater/Fertilizer Bonus Limit: +1 \nHarvest Time: -10%");
            upgradeIndex = 2;
        });
        farmerPic3.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/pictures/upgrade3.png"))));
        farmerPic3.setOnAction(event -> {
            upgradeText.setText("Honorable Farmer (Lvl 20 & 350 OC): \nEarning/Buying: +/- 5 \nWater/Fertilizer Bonus Limit: +2 \nHarvest Time: -15%");
            upgradeIndex = 3;
        });

        upgradeBuy.setOnAction(event -> {
            farmer.upgradeFarmer(upgradeIndex);

            if(farmer.getFarmerType()!=0)
                reverseStats();
            adjustStats(upgradeIndex);

            upgradeIndex=100;
        });


        storeBack.setOnAction(event -> {
            storePane.setDisable(true);
            storePane.setOpacity(0.0);
        });
        inventoryBack.setOnAction(event -> {
            inventoryPane.setDisable(true);
            inventoryPane.setOpacity(0.0);
        });

        tutorialB.setOnAction(event -> {
            if(tutorialPane.isDisabled()){
                tutorialPane.setDisable(false);
                tutorialPane.setOpacity(1.0);
                upgradePane.setDisable(true);
                upgradePane.setOpacity(0.0);
                inventoryPane.setDisable(true);
                inventoryPane.setOpacity(0.0);
                storePane.setDisable(true);
                storePane.setOpacity(0.0);

            }
            else{
                closePanels();
            }
        });

        tutorialBack.setOnAction(event -> {
            tutorialPane.setDisable(true);
            tutorialPane.setOpacity(0.0);
        });
        // steps on planting
        learn.setOnAction(event -> {
            if(info.isDisabled()){
                info.setDisable(false);
                info.setOpacity(1.0);
                info.setText("As a farmer your goal is to \nplant crops and harvest them \nto make money." +
                        "\n\nYour task in this game is to \nraise money and level up/\nregister higher as a farmer");
            }
            else{
                info.setDisable(true);
                info.setOpacity(0.0);
            }
        });
        learnCF.setOnAction(event -> {
            if(tutorialCF.isDisabled()){
                tutorialCF.setDisable(false);
                tutorialCF.setOpacity(1.0);
                infoCF.setText("You can view the information on crops and flowers in the \ninventory\n\n"+ "Steps on planting and harvesting crops:\n"+
                        "1. You have to make sure there is no obstructions \non the tile\n" +
                        "2. Next you have to plow the tile before planting\n" +
                        "3. Next you add the fertilizer before planting any seed\n" +
                        "4. Next you plant the crop/flower\n"+
                        "5. Next you can now water the plant when a seed \nhas been planted\n" +
                        "6. Once the plant has grown, you can harvest it using the \nscythe tool.\n" +
                        "7. After harvesting, the tile will go back to being unplowed.\n");
            }
            else{
                tutorialCF.setDisable(true);
                tutorialCF.setOpacity(0.0);
            }
        });
        tutorialCFBack.setOnAction(event -> {
            tutorialCF.setDisable(true);
            tutorialCF.setOpacity(0.0);
        });
        tutorialCFNext.setOnAction(event -> {
            tutorialCF.setDisable(true);
            tutorialCF.setOpacity(0.0);
            tutorialFT.setDisable(false);
            tutorialFT.setOpacity(1.0);
            infoFT.setText("NOTE: \n" +
                    "-Make sure that the crop has enough water and \nfertilizer or else it will wither when it reaches the timer\n" +
                    "-If the plant successfully grown, it will still wither\n after twice the time it took to grow\n" +
                    "-You can't plant a tree when there are obstructions \naround it (all 8 directions)\n" +
                    "-You can't plant crops around a planted tree \n(all 8 directions)\n" +
                    "-Hover over tiles or tools to check their \nspecific actions/details");
        });
        learnFT.setOnAction(event -> {
            if(tutorialFT.isDisabled()){
                tutorialFT.setDisable(false);
                tutorialFT.setOpacity(1.0);
                infoFT.setText("NOTE: \n" +
                        "-Make sure that the crop has enough water and \nfertilizer or else it will wither when it reaches the timer\n" +
                        "-If the plant successfully grown, it will still wither\n after twice the time it took to grow\n" +
                        "-You can't plant a tree when there are obstructions \naround it (all 8 directions)\n" +
                        "-You can't plant crops around a planted tree \n(all 8 directions)\n" +
                        "-Hover over tiles or tools to check their \nspecific actions/details");
            }
            else{
                tutorialFT.setDisable(true);
                tutorialFT.setOpacity(0.0);
            }
        });
        tutorialFTBack.setOnAction(event -> {
            tutorialFT.setDisable(true);
            tutorialFT.setOpacity(0.0);
        });
        tutorialFTNext.setOnAction(event -> {
            tutorialT.setDisable(false);
            tutorialT.setOpacity(1.0);
            tutorialFT.setDisable(true);
            tutorialFT.setOpacity(0.0);
            infoT.setText("These are the different tools that you can use in farming: \n"+
                    "1. Hoe : A tool that you use to ready the land tile for \nfarming. This tool is also used in taking out withered \nplants.\n"+
                    "2. Pickaxe : A tool that is used in removing rocks that \noccupy the land tile.\n"+
                    "3. Watering Can : A tool that is used in watering the \nplants.\n"+
                    "4. Scythe : A tool that is used in harvesting plants when \nthey are done growing.\n"+
                    "5. Fertilizer : A tool that is used to aid the plant in \ngrowing.\n"+
                    "6. Cursor tool : The default tool to select tiles for \nplanting.");
        });
        tutorialFTPrev.setOnAction(event -> {
            tutorialFT.setDisable(true);
            tutorialFT.setOpacity(0.0);
            tutorialCF.setDisable(false);
            tutorialCF.setOpacity(1.0);
            infoCF.setText("You can view the information on crops and flowers in the \ninventory\n\n"+ "Steps on planting and harvesting crops:\n"+
                    "1. You have to make sure there is no obstructions \non the tile\n" +
                    "2. Next you have to plow the tile before planting\n" +
                    "3. Next you add the fertilizer before planting any seed\n" +
                    "4. Next you plant the crop/flower\n"+
                    "5. Next you can now water the plant when a seed \nhas been planted\n" +
                    "6. Once the plant has grown, you can harvest it using the \nscythe tool.\n" +
                    "7. After harvesting, the tile will go back to being unplowed.\n");
        });
        learnT.setOnAction(event -> {
            if(tutorialT.isDisabled()){
                tutorialT.setDisable(false);
                tutorialT.setOpacity(1.0);
                infoT.setText("These are the different tools that you can use in farming: \n"+
                        "1. Hoe : A tool that you use to ready the land tile for \nfarming. This tool is also used in taking out withered \nplants.\n"+
                        "2. Pickaxe : A tool that is used in removing rocks that \noccupy the land tile.\n"+
                        "3. Watering Can : A tool that is used in watering the \nplants.\n"+
                        "4. Scythe : A tool that is used in harvesting plants when \nthey are done growing.\n"+
                        "5. Fertilizer : A tool that is used to aid the plant in \ngrowing.\n"+
                        "6. Cursor tool : The default tool to select tiles for \nplanting.");
            }
            else{
                tutorialT.setDisable(true);
                tutorialT.setOpacity(0.0);
            }
        });
        tutorialTBack.setOnAction(event -> {
            tutorialT.setDisable(true);
            tutorialT.setOpacity(0.0);
        });
        tutorialTBack.setOnAction(event -> {
            tutorialT.setDisable(true);
            tutorialT.setOpacity(0.0);
            tutorialFT.setDisable(false);
            tutorialFT.setOpacity(1.0);
            infoFT.setText("NOTE: \n" +
                    "-Make sure that the crop has enough water and \nfertilizer or else it will wither when it reaches the timer\n" +
                    "-If the plant successfully grown, it will still wither\n after twice the time it took to grow\n" +
                    "-You can't plant a tree when there are obstructions \naround it (all 8 directions)\n" +
                    "-You can't plant crops around a planted tree \n(all 8 directions)\n" +
                    "-Hover over tiles or tools to check their \nspecific actions/details");
        });
        tutorialTPrev.setOnAction(event -> {
            tutorialT.setDisable(true);
            tutorialT.setOpacity(0.0);
            tutorialFT.setDisable(false);
            tutorialFT.setOpacity(1.0);
            infoFT.setText("NOTE: \n" +
                    "-Make sure that the crop has enough water and \nfertilizer or else it will wither when it reaches the timer\n" +
                    "-If the plant successfully grown, it will still wither\n after twice the time it took to grow\n" +
                    "-You can't plant a tree when there are obstructions \naround it (all 8 directions)\n" +
                    "-You can't plant crops around a planted tree \n(all 8 directions)\n" +
                    "-Hover over tiles or tools to check their \nspecific actions/details");
        });

        seedGrid = new Node[2][6];


        for (int a = 0; a < 2; a++) {
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
                seedGrid[a][b] = cropsB;

                final int g = a;
                final int h = b;

                switch (b + (a * 6)) {
                    case 0:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Beet_Seeds.png")));
                        cropsB.setOnMouseEntered(event -> cropsB.setTooltip(new Tooltip("x" + farmer.getInventory().getTurnipCount())));
                        cropsB.setOnAction(event -> {
                            itemSelection.setText("Seed Equipped: Turnip");
                            if (farmer.getInventory().getTurnipCount() > 0) {
                                plantIndex = 0;
                            }
                        });
                        break;
                    case 1:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Parsnip_Seeds.png")));
                        cropsB.setOnMouseEntered(event -> cropsB.setTooltip(new Tooltip("x" + farmer.getInventory().getCarrotCount())));
                        cropsB.setOnAction(event -> {
                            itemSelection.setText("Seed Equipped: Carrot");
                            if (farmer.getInventory().getCarrotCount() > 0) {
                                plantIndex = 1;
                            }
                        });
                        break;
                    case 2:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Tomato_Seeds.png")));
                        cropsB.setOnMouseEntered(event -> cropsB.setTooltip(new Tooltip("x" + farmer.getInventory().getTomatoCount())));
                        cropsB.setOnAction(event -> {
                            itemSelection.setText("Seed Equipped: Tomato");
                            if (farmer.getInventory().getTomatoCount() > 0) {
                                plantIndex = 2;
                            }
                        });
                        break;
                    case 3:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Potato_Seeds.png")));
                        cropsB.setOnMouseEntered(event -> cropsB.setTooltip(new Tooltip("x" + farmer.getInventory().getPotatoCount())));
                        cropsB.setOnAction(event -> {
                            itemSelection.setText("Seed Equipped: Potato");
                            if (farmer.getInventory().getPotatoCount() > 0) {
                                plantIndex = 3;
                            }
                        });
                        break;
                    case 4:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Fairy_Seeds.png")));
                        cropsB.setOnMouseEntered(event -> cropsB.setTooltip(new Tooltip("x" + farmer.getInventory().getRoseCount())));
                        cropsB.setOnAction(event -> {
                            itemSelection.setText("Seed Equipped: Rose");
                            if (farmer.getInventory().getRoseCount() > 0) {
                                plantIndex = 4;
                            }
                        });
                        break;
                    case 5:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Tulip_Bulb.png")));
                        cropsB.setOnMouseEntered(event -> cropsB.setTooltip(new Tooltip("x" + farmer.getInventory().getTulipCount())));
                        cropsB.setOnAction(event -> {
                            itemSelection.setText("Seed Equipped: Tulip");
                            if (farmer.getInventory().getTulipCount() > 0) {
                                plantIndex = 5;
                            }
                        });
                        break;
                    case 6:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Spangle_Seeds.png")));
                        cropsB.setOnMouseEntered(event -> cropsB.setTooltip(new Tooltip("x" + farmer.getInventory().getStargazerCount())));
                        cropsB.setOnAction(event -> {
                            itemSelection.setText("Seed Equipped: Stargazer");
                            if (farmer.getInventory().getStargazerCount() > 0) {
                                plantIndex = 6;
                            }
                        });
                        break;
                    case 7:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Sunflower_Seeds.png")));
                        cropsB.setOnMouseEntered(event -> cropsB.setTooltip(new Tooltip("x" + farmer.getInventory().getSunflowerCount())));
                        cropsB.setOnAction(event -> {
                            itemSelection.setText("Seed Equipped: Sunflower");
                            if (farmer.getInventory().getSunflowerCount() > 0) {
                                plantIndex = 7;
                            }
                        });
                        break;
                    case 8:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Apricot_Sapling.png")));
                        cropsB.setOnMouseEntered(event -> cropsB.setTooltip(new Tooltip("x" + farmer.getInventory().getMangoCount())));
                        cropsB.setOnAction(event -> {
                            itemSelection.setText("Seed Equipped: Mango");
                            if (farmer.getInventory().getMangoCount() > 0) {
                                plantIndex = 8;
                            }
                        });
                        break;
                    case 9:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Apple_Sapling.png")));
                        cropsB.setOnMouseEntered(event -> cropsB.setTooltip(new Tooltip("x" + farmer.getInventory().getAppleCount())));
                        cropsB.setOnAction(event -> {
                            itemSelection.setText("Seed Equipped: Apple");
                            if (farmer.getInventory().getAppleCount() > 0) {
                                plantIndex = 9;
                            }
                        });
                        break;
                    case 10:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/100px-Palm_Stage_1.png")));
                        cropsB.setOnMouseEntered(event -> cropsB.setTooltip(new Tooltip("x" + farmer.getInventory().getBananaCount())));
                        cropsB.setOnAction(event -> {
                            itemSelection.setText("Seed Equipped: Banana");
                            if (farmer.getInventory().getBananaCount() > 0) {
                                plantIndex = 10;
                            }
                        });
                        break;
                    case 11:
                        cropsBImage.setImage(new Image(getClass().getResourceAsStream("/pictures/Orange_Sapling.png")));
                        cropsB.setOnMouseEntered(event -> cropsB.setTooltip(new Tooltip("x" + farmer.getInventory().getOrangeCount())));
                        cropsB.setOnAction(event -> {
                            itemSelection.setText("Seed Equipped: Orange");
                            if (farmer.getInventory().getOrangeCount() > 0) {
                                plantIndex = 11;
                            }
                        });
                        break;
                }
                cropsB.setGraphic(cropsBImage);
            }
        }
    }

    public void showFruits(double x, double y, boolean visible, double opac) {
        fruitMenu.setDisable(visible);
        fruitMenu.setOpacity(opac);
        fruitMenuBack.setDisable(visible);
        fruitMenuBack.setOpacity(opac);

        fruitMenu.setLayoutX(x + 8);
        fruitMenu.setLayoutY(y + 8);
        fruitMenuBack.setLayoutX(x);
        fruitMenuBack.setLayoutY(y);
    }

    public void seedAnimation(ImageView plant, String seed, int i, int j, int y, int type) {
        tile.get(j + (i * y)).setHasPlant(true);
        tile.get(j + (i * y)).setOccupied(true);
        tile.get(j + (i * y)).setPlantType(type);

        switch (type) {
            case 0:
                tile.get(j + (i * y)).setCrop(farmer.getTur());
                break;
            case 1:
                tile.get(j + (i * y)).setCrop(farmer.getC());
                break;
            case 2:
                tile.get(j + (i * y)).setCrop(farmer.getTo());
                break;
            case 3:
                tile.get(j + (i * y)).setCrop(farmer.getP());
                break;
            case 4:
                tile.get(j + (i * y)).setCrop(farmer.getR());
                break;
            case 5:
                tile.get(j + (i * y)).setCrop(farmer.getTul());
                break;
            case 6:
                tile.get(j + (i * y)).setCrop(farmer.getSt());
                break;
            case 7:
                tile.get(j + (i * y)).setCrop(farmer.getSu());
                break;
            case 8:
                tile.get(j + (i * y)).setCrop(farmer.getM());
                break;
            case 9:
                tile.get(j + (i * y)).setCrop(farmer.getA());
                break;
            case 10:
                tile.get(j + (i * y)).setCrop(farmer.getB());
                break;
            case 11:
                tile.get(j + (i * y)).setCrop(farmer.getO());
                break;
        }

        AnimationTimer animationTimer = new AnimationTimer() {
            long time_now = System.nanoTime();

            @Override
            public void handle(long now) {
                long elapse = (now - time_now) / 1000000000;
                if (elapse == 0) {
                    plant.setImage(new Image(getClass().getResourceAsStream("/pictures/" + seed + "_1.png")));
                } else if (elapse == (tile.get(j+(i*y)).getCrop().getHarvestTime()*60)*0.25) {
                    plant.setImage(new Image(getClass().getResourceAsStream("/pictures/" + seed + "_2.png")));
                } else if (elapse == (tile.get(j+(i*y)).getCrop().getHarvestTime()*60)*0.50) {
                    plant.setImage(new Image(getClass().getResourceAsStream("/pictures/" + seed + "_3.png")));
                } else if (elapse == (tile.get(j+(i*y)).getCrop().getHarvestTime()*60)*0.75) {
                    plant.setImage(new Image(getClass().getResourceAsStream("/pictures/" + seed + "_4.png")));
                } else if (elapse == (tile.get(j+(i*y)).getCrop().getHarvestTime()*60)) {
                    if ((tile.get(j + (i * y)).getWaterUsed() <= tile.get(j + (i * y)).getCrop().getWaterBonusLimit() && tile.get(j + (i * y)).getWaterUsed() >= tile.get(j + (i * y)).getCrop().getWaterNeeded())
                            && tile.get(j + (i * y)).getFertilizerUsed() >= tile.get(j + (i * y)).getCrop().getFertilizerNeeded()) {
                        plant.setImage(new Image(getClass().getResourceAsStream("/pictures/" + seed + "_5.png")));
                        tile.get(j + (i * y)).getCrop().setIsHarvestable(true);
                        stop();
                    } else {
                        plant.setImage(new Image(getClass().getResourceAsStream("/pictures/Wither.png")));
                        tile.get(j + (i * y)).getCrop().setIsWithered(true);
                        if (tile.get(j + (i * y)).getPlantType() == 2) {
                            tile.get(j + (i * y)).setOccupied(false);
                            tile.get(j + (i - 1) * y).setOccupied(false);
                            tile.get((j - 1) + (i - 1) * y).setOccupied(false);
                            tile.get((j + 1) + (i - 1) * y).setOccupied(false);
                            tile.get(j + (i + 1) * y).setOccupied(false);
                            tile.get((j - 1) + (i + 1) * y).setOccupied(false);
                            tile.get((j + 1) + (i + 1) * y).setOccupied(false);
                            tile.get((j - 1) + i * y).setOccupied(false);
                            tile.get((j + 1) + i * y).setOccupied(false);
                        }
                        stop();
                    }
                } else if (elapse == (tile.get(j+(i*y)).getCrop().getHarvestTime()*60)*2) {
                    plant.setImage(new Image(getClass().getResourceAsStream("/pictures/Wither.png")));
                    tile.get(j + (i * y)).getCrop().setIsWithered(true);
                    if (tile.get(j + (i * y)).getPlantType() == 2) {
                        tile.get(j + (i * y)).setOccupied(false);
                        tile.get(j + (i - 1) * y).setOccupied(false);
                        tile.get((j - 1) + (i - 1) * y).setOccupied(false);
                        tile.get((j + 1) + (i - 1) * y).setOccupied(false);
                        tile.get(j + (i + 1) * y).setOccupied(false);
                        tile.get((j - 1) + (i + 1) * y).setOccupied(false);
                        tile.get((j + 1) + (i + 1) * y).setOccupied(false);
                        tile.get((j - 1) + i * y).setOccupied(false);
                        tile.get((j + 1) + i * y).setOccupied(false);
                    }
                    stop();
                }
            }
        };

        animationTimer.start();

        plantIndex = 100;
        itemSelection.setText("Seed Equipped: None");
    }

    public void setOccupyAll(ImageView plant, int j, int i, int y) {
        try {
            if ((!tile.get(j + (i - 1) * y).getIsOccupied() && !tile.get(j + (i + 1) * y).getIsOccupied())
                    && (!tile.get((j - 1) + (i - 1) * y).getIsOccupied() && !tile.get((j + 1) + (i - 1) * y).getIsOccupied())
                    && (!tile.get((j - 1) + (i + 1) * y).getIsOccupied() && !tile.get((j + 1) + (i + 1) * y).getIsOccupied())
                    && (!tile.get((j - 1) + i * y).getIsOccupied() && !tile.get((j + 1) + i * y).getIsOccupied())) {
                tile.get(j + (i * y)).setOccupied(true);
                tile.get(j + (i - 1) * y).setOccupied(true);
                tile.get((j - 1) + (i - 1) * y).setOccupied(true);
                tile.get((j + 1) + (i - 1) * y).setOccupied(true);
                tile.get(j + (i + 1) * y).setOccupied(true);
                tile.get((j - 1) + (i + 1) * y).setOccupied(true);
                tile.get((j + 1) + (i + 1) * y).setOccupied(true);
                tile.get((j - 1) + i * y).setOccupied(true);
                tile.get((j + 1) + i * y).setOccupied(true);
                if (plantIndex == 8) {
                    seedAnimation(plant, "Apricot_Stage", i, j, y, 8);
                    farmer.getInventory().setMangoCount(farmer.getInventory().getMangoCount() - 1);
                } else if (plantIndex == 9) {
                    seedAnimation(plant, "Apple_Stage", i, j, y, 9);
                    farmer.getInventory().setAppleCount(farmer.getInventory().getAppleCount() - 1);
                } else if (plantIndex == 10) {
                    seedAnimation(plant, "100px-Palm_Stage", i, j, y, 10);
                    farmer.getInventory().setBananaCount(farmer.getInventory().getBananaCount() - 1);
                } else if (plantIndex == 11) {
                    seedAnimation(plant, "Orange_Stage", i, j, y, 11);
                    farmer.getInventory().setOrangeCount(farmer.getInventory().getOrangeCount() - 1);
                }
            } else
                itemSelection.setText("You can't place a tree there!");
        } catch (IndexOutOfBoundsException e) {
                if (plantIndex == 8) {
                    seedAnimation(plant, "Apricot_Stage", i, j, y, 8);
                    farmer.getInventory().setMangoCount(farmer.getInventory().getMangoCount() - 1);
                } else if (plantIndex == 9) {
                    seedAnimation(plant, "Apple_Stage", i, j, y, 9);
                    farmer.getInventory().setAppleCount(farmer.getInventory().getAppleCount() - 1);
                } else if (plantIndex == 10) {
                    seedAnimation(plant, "100px-Palm_Stage", i, j, y, 10);
                    farmer.getInventory().setBananaCount(farmer.getInventory().getBananaCount() - 1);
                } else if (plantIndex == 11) {
                    seedAnimation(plant, "Orange_Stage", i, j, y, 11);
                    farmer.getInventory().setOrangeCount(farmer.getInventory().getOrangeCount() - 1);
                }
        }
    }

    public void adjustStats(int upgradeIndex){
        farmer.getTur().setFarmerType(upgradeIndex);
        farmer.getTur().adjustBonus();
        farmer.getC().setFarmerType(upgradeIndex);
        farmer.getC().adjustBonus();
        farmer.getTo().setFarmerType(upgradeIndex);
        farmer.getTo().adjustBonus();
        farmer.getP().setFarmerType(upgradeIndex);
        farmer.getP().adjustBonus();
        farmer.getR().setFarmerType(upgradeIndex);
        farmer.getR().adjustBonus();
        farmer.getTul().setFarmerType(upgradeIndex);
        farmer.getTul().adjustBonus();
        farmer.getSt().setFarmerType(upgradeIndex);
        farmer.getSt().adjustBonus();
        farmer.getSu().setFarmerType(upgradeIndex);
        farmer.getSu().adjustBonus();
        farmer.getM().setFarmerType(upgradeIndex);
        farmer.getM().adjustBonus();
        farmer.getA().setFarmerType(upgradeIndex);
        farmer.getA().adjustBonus();
        farmer.getB().setFarmerType(upgradeIndex);
        farmer.getB().adjustBonus();
        farmer.getO().setFarmerType(upgradeIndex);
        farmer.getO().adjustBonus();
    }

    public void reverseStats(){
        farmer.getTur().reverseBonus();
        farmer.getC().reverseBonus();
        farmer.getTo().reverseBonus();
        farmer.getP().reverseBonus();
        farmer.getR().reverseBonus();
        farmer.getTul().reverseBonus();
        farmer.getSt().reverseBonus();
        farmer.getSu().reverseBonus();
        farmer.getM().reverseBonus();
        farmer.getA().reverseBonus();
        farmer.getB().reverseBonus();
        farmer.getO().reverseBonus();
    }

    public void levelUp(){
        farmer.getTur().levelUpStats();
        farmer.getC().levelUpStats();
        farmer.getTo().levelUpStats();
                farmer.getP().levelUpStats();
        farmer.getR().levelUpStats();
        farmer.getTul().levelUpStats();
        farmer.getSt().levelUpStats();
                farmer.getSu().levelUpStats();
                farmer.getM().levelUpStats();
        farmer.getA().levelUpStats();
        farmer.getB().levelUpStats();
        farmer.getO().levelUpStats();
    }

    public void closePanels(){
        tutorialPane.setDisable(true);
        tutorialPane.setOpacity(0.0);
        tutorialCF.setDisable(true);
        tutorialCF.setOpacity(0.0);
        tutorialFT.setDisable(true);
        tutorialFT.setOpacity(0.0);
        tutorialT.setDisable(true);
        tutorialT.setOpacity(0.0);
        upgradePane.setDisable(true);
        upgradePane.setOpacity(0.0);
        inventoryPane.setDisable(true);
        inventoryPane.setOpacity(0.0);
        storePane.setDisable(true);
        storePane.setOpacity(0.0);
    }
}