package model;

import java.util.Random;

public class Tiles {
    private boolean isPlowed;
    private boolean isOccupied;
    private boolean hasRock;
    private boolean isWatered;
    private boolean hasWither;
    private Tools tool;

    public boolean isWatered() {
        return isWatered;
    }

    public void setWatered(boolean watered) {
        isWatered = watered;
    }

    public boolean isHasWither() {
        return hasWither;
    }

    public void setHasWither(boolean hasWither) {
        this.hasWither = hasWither;
    }

    public Tiles(){
        Random rand = new Random();
        int n = rand.nextInt(100)+1;

        if(n>=75&&n<=100){
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

    public Tools getTool() {
        return tool;
    }

    public void setTool(Tools tool) {
        this.tool = tool;
    }

    public void setPlowed(boolean plowed) {
        isPlowed = plowed;
    }
}
