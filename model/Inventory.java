/**
 * This class holds all the amounts/counts of each seed and fertilizer
 */

package model;

public class Inventory {
    private int turnipCount;
    private int carrotCount;
    private int tomatoCount;
    private int potatoCount;
    private int roseCount;
    private int tulipCount;
    private int stargazerCount;
    private int sunflowerCount;
    private int mangoCount;
    private int appleCount;
    private int bananaCount;
    private int orangeCount;
    private int fertilizerCount;

    /**
     * Initializes all the counts to 0
     */
    public Inventory() {
        this.turnipCount=0;
        this.carrotCount=0;
        this.tomatoCount=0;
        this.potatoCount=0;
        this.roseCount=0;
        this.tulipCount=0;
        this.stargazerCount=0;
        this.sunflowerCount=0;
        this.mangoCount=0;
        this.appleCount=0;
        this.bananaCount=0;
        this.orangeCount=0;
        this.fertilizerCount=0;
    }

    public int getTurnipCount() {
        return turnipCount;
    }

    public void setTurnipCount(int turnipCount) {
        this.turnipCount = turnipCount;
    }

    public int getCarrotCount() {
        return carrotCount;
    }

    public void setCarrotCount(int carrotCount) {
        this.carrotCount = carrotCount;
    }

    public int getTomatoCount() {
        return tomatoCount;
    }

    public void setTomatoCount(int tomatoCount) {
        this.tomatoCount = tomatoCount;
    }

    public int getPotatoCount() {
        return potatoCount;
    }

    public void setPotatoCount(int potatoCount) {
        this.potatoCount = potatoCount;
    }

    public int getRoseCount() {
        return roseCount;
    }

    public void setRoseCount(int roseCount) {
        this.roseCount = roseCount;
    }

    public int getTulipCount() {
        return tulipCount;
    }

    public void setTulipCount(int tulipCount) {
        this.tulipCount = tulipCount;
    }

    public int getStargazerCount() {
        return stargazerCount;
    }

    public void setStargazerCount(int stargazerCount) {
        this.stargazerCount = stargazerCount;
    }

    public int getSunflowerCount() {
        return sunflowerCount;
    }

    public void setSunflowerCount(int sunflowerCount) {
        this.sunflowerCount = sunflowerCount;
    }

    public int getMangoCount() {
        return mangoCount;
    }

    public void setMangoCount(int mangoCount) {
        this.mangoCount = mangoCount;
    }

    public int getAppleCount() {
        return appleCount;
    }

    public void setAppleCount(int appleCount) {
        this.appleCount = appleCount;
    }

    public int getBananaCount() {
        return bananaCount;
    }

    public void setBananaCount(int bananaCount) {
        this.bananaCount = bananaCount;
    }

    public int getOrangeCount() {
        return orangeCount;
    }

    public void setOrangeCount(int orangeCount) {
        this.orangeCount = orangeCount;
    }

    public int getFertilizerCount() {
        return fertilizerCount;
    }

    public void setFertilizerCount(int fertilizerCount) {
        this.fertilizerCount = fertilizerCount;
    }
}