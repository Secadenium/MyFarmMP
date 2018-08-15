package model;

import java.util.Random;

public class Tiles {
    private boolean isPlowed;
    private boolean isOccupied;
    private boolean hasRock;
    private boolean isWatered;
    private boolean hasWither;
    private boolean hasPlant;
    private int waterUsed;
    private int fertilizerUsed;
    private int plantType;
    private Crops crop;
    private boolean sideOrCorner;

    public boolean isHasWither() {
        return hasWither;
    }

    /**
     * This class is the individual grid of the grid pane present in the game.
     * This holds all the boolean values to change the stats of the specific tile.
     */
    public Tiles(){
        Random rand = new Random();
        int n = rand.nextInt(100)+1;

        if(n>80&&n<=100){
            hasRock=true;
            isOccupied=true;
        }
        else {
            hasRock = false;
            isOccupied = false;
        }
        isPlowed=false;
        isWatered=false;
        hasWither=false;
        fertilizerUsed=0;
        waterUsed=0;
        crop=null;
    }

    public boolean getHasRock(){
        return hasRock;
    }

    public void setHasRock(boolean hasRock) {
        this.hasRock = hasRock;
    }

    public boolean isPlowed() {
        return isPlowed;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public boolean isHasRock() {
        return hasRock;
    }

    public void setPlowed(boolean plowed) {
        isPlowed = plowed;
    }

    public boolean isHasPlant() {
        return hasPlant;
    }

    public void setHasPlant(boolean hasPlant) {
        this.hasPlant = hasPlant;
    }

    public int getPlantType() {
        return plantType;
    }

    public void setPlantType(int plantType) {
        this.plantType = plantType;
    }

    public int getFertilizerUsed() {
        return fertilizerUsed;
    }

    public void setFertilizerUsed(int fertilizerUsed) {
        this.fertilizerUsed = fertilizerUsed;
    }

    public Crops getCrop() {
        return crop;
    }

    public void setCrop(Crops crop) {
        this.crop = crop;
    }

    public int getWaterUsed() {
        return waterUsed;
    }

    public void setWaterUsed(int waterUsed) {
        this.waterUsed = waterUsed;
    }

    public boolean isSideOrCorner() {
        return sideOrCorner;
    }

    public void setSideOrCorner(boolean sideOrCorner) {
        this.sideOrCorner = sideOrCorner;
    }
}
