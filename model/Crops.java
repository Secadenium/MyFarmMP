package model;

public class Crops {
    private String seedName;
    private int cropType;
    private double harvestTime;
    private double harvestTimeBonus;
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
        isWithered=false;
        isHarvestable=false;
        this.waterBonusLimit=waterBonusLimit;
        this.fertilizerBonusLimit=fertilizerBonusLimit;
    }

    public void setHarvestTimeBonus(double harvestTimeBonus) {
        this.harvestTimeBonus = harvestTimeBonus;
    }

    public void setProductsProd(int prod){
        productsProd=prod;
    }

    public void display(){
        System.out.println("\nSeed Name: "+seedName);
        System.out.println("Crop Type: "+cropType);
        System.out.println("Harvest Time: "+harvestTime);
        System.out.println("Water Needed: "+waterNeeded);
        System.out.println("Water Bonus Limit: "+waterBonusLimit);
        System.out.println("Fertilizer Needed: "+fertilizerNeeded);
        System.out.println("Fertilizer Bonus Limit: "+fertilizerBonusLimit);
        System.out.println("Products Produced: "+productsProd);
        System.out.println("Seed Cost: "+seedCost);
        System.out.println("Base Sell: "+baseSell);
        System.out.println("Experience: "+expUp);
        System.out.println("Wither Time: "+computeWither());
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
}
