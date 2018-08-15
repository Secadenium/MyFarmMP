package model;

public class Crops {
    private String seedName;
    private int cropType;
    private double harvestTime;
    private double harvestTimeTemp;
    private int waterNeeded;
    private int waterBonusLimit;
    private int fertilizerNeeded;
    private int fertilizerBonusLimit;
    private double harvestCost;
    private int productsProd;
    private int seedCost;
    private double baseSell;
    private double actualSell;
    private int expUp;
    private boolean isWithered;
    private boolean isHarvestable;
    private int farmerType;
    private double cropBonus;

    /**
     * This crop class holds all the values and variables for the seeds.
     * It acts as a super class for all the subclasses of seeds.
     * @param seedName
     * @param cropType
     * @param harvestTime
     * @param waterNeeded
     * @param waterBonusLimit
     * @param fertilizerNeeded
     * @param fertilizerBonusLimit
     * @param harvestCost
     * @param productsProd
     * @param seedCost
     * @param baseSell
     * @param expUp
     */
    public Crops (String seedName, int cropType, double harvestTime, int waterNeeded, int waterBonusLimit, int fertilizerNeeded,
                  int fertilizerBonusLimit, double harvestCost, int productsProd, int seedCost, double baseSell, int expUp){
        this.seedName=seedName;
        this.cropType=cropType;
        this.harvestTime=harvestTime;
        this.waterNeeded=waterNeeded;
        this.fertilizerNeeded=fertilizerNeeded;
        this.harvestCost=harvestCost;
        this.productsProd=productsProd;
        this.seedCost=seedCost;
        this.baseSell=baseSell;
        this.expUp=expUp;
        this.cropBonus=0;
        isWithered=false;
        isHarvestable=false;
        this.waterBonusLimit=waterBonusLimit;
        this.fertilizerBonusLimit=fertilizerBonusLimit;
    }


    public void setProductsProd(int prod){
        productsProd=prod;
    }

    /**
     * This method adjusts the special bonuses that is given when the farmer registers up
     */
    public void adjustBonus() {
        switch (farmerType) {
            case 0:
                seedCost -= 0;
                baseSell += 0;
                waterBonusLimit += 0;
                fertilizerBonusLimit += 0;
                harvestTime = harvestTime - (harvestTime * 0.00);
                break;
            case 1:
                seedCost -= 2;
                baseSell += 2;
                waterBonusLimit += 0;
                fertilizerBonusLimit += 0;
                harvestTimeTemp=harvestTime;
                harvestTime = harvestTime - (harvestTime * 0.05);
                break;
            case 2:
                seedCost -= 3;
                baseSell += 3;
                waterBonusLimit += 1;
                fertilizerBonusLimit += 1;
                harvestTimeTemp=harvestTime;
                harvestTime = harvestTime - (harvestTime * 0.10);
                break;
            case 3:
                seedCost -= 5;
                baseSell += 5;
                waterBonusLimit += 2;
                fertilizerBonusLimit += 2;
                harvestTimeTemp=harvestTime;
                harvestTime = harvestTime - (harvestTime * 0.15);
                break;
        }
    }

    /**
     * This method resets the bonuses of the crops
     */
        public void reverseBonus(){
            switch(farmerType){
                case 1:
                    seedCost+=2;
                    baseSell-=2;
                    waterBonusLimit-=0;
                    fertilizerBonusLimit-=0;
                    harvestTime=harvestTimeTemp;
                    break;
                case 2:
                    seedCost+=3;
                    baseSell-=3;
                    waterBonusLimit-=1;
                    fertilizerBonusLimit-=1;
                    harvestTime=harvestTimeTemp;
                    System.out.println("farmer adjusted lvl 2");
                    break;
                case 3:
                    seedCost+=5;
                    baseSell-=5;
                    waterBonusLimit-=2;
                    fertilizerBonusLimit-=2;
                    harvestTime=harvestTimeTemp;
                    break;
            }

    }

    /**
     * This provides the additional bonuses depending on the level of the user
     */
    public void levelUpStats(){
        baseSell+=1;
        seedCost-=1;
    }

    /**
     * Displays all the information about the seed
     * @return
     */
    public String display(){
        return "Seed Name: "+seedName+"\nCrop Type: "+cropType+"\nHarvest Time: "+harvestTime+"\nWater Needed: "+waterNeeded+
                "\nFertilizer Needed: "+fertilizerNeeded+"\nProducts Produced: "+productsProd+"\nSeed Cost: "+seedCost+
                "\nBase Sell: "+baseSell+"\nExperience: "+expUp+"\nWither Time: "+computeWither();
    }

    /**
     * Displays the crop info when it's planted
     * @return
     */
    public String displayInfoCrop(){
        return "Seed Name: "+seedName+"\nHarvest Time: "+harvestTime+"\nWater Needed: "+waterNeeded+
                "\nFertilizer Needed: "+fertilizerNeeded+"\nProducts Produced: "+productsProd+
                "\nExperience: "+expUp+"\nWither Time: "+computeWither()+"\nHarvestable: "+isHarvestable;
    }

    public double computeSellPrice() {
        return baseSell + cropBonus/*farmerBonus*/;
    }

    public double computeWither(){
        return harvestTime*2;
    }

    public void setIsWithered(boolean wither){
        isWithered=wither;
    }

    public void setIsHarvestable(boolean harvest){
        isHarvestable=harvest;
    }

    public void setFarmerType(int farmerType) {
        this.farmerType = farmerType;
    }

    public boolean isHarvestable() {
        return isHarvestable;
    }

    public int getSeedCost() {
        return seedCost;
    }

    public boolean isWithered() {
        return isWithered;
    }

    public int getWaterBonusLimit() {
        return waterBonusLimit;
    }

    public int getFertilizerBonusLimit() {
        return fertilizerBonusLimit;
    }

    public int getWaterNeeded() {
        return waterNeeded;
    }

    public int getFertilizerNeeded() {
        return fertilizerNeeded;
    }

    public int getExpUp() {
        return expUp;
    }

    public int getProductsProd() {
        return productsProd;
    }

    public double getHarvestCost() {
        return harvestCost;
    }

    public double getBaseSell() {
        return baseSell;
    }

    public int getCropType() {
        return cropType;
    }

    public double getHarvestTime() {
        return harvestTime;
    }

}
