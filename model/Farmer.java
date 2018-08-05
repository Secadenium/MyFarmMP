package model;

import model.Crops;

public class Farmer {
    private String name;
    private boolean isMale;
    private int exp;
    private int level;
    private double money;
    private int farmerType;
    private Crops crop;
    private Land land;
    private Tools tool;

    public Farmer(String name, boolean isMale){
        crop = new Crops("",0,0,0,0,0,0,0,0,0,0,0);
        land = new Land();
        tool = new Tools();
        this.name=name;
        this.isMale=isMale;
        exp=0;
        level=1;
        money=50;
        farmerType=0;
        tool.addFertilizer(5);
    }

    public void upgradeFarmer(int upgrade){
        switch (upgrade){
            case 1:
                if(level>=10&&money>=200&&!((farmerType==1)||(farmerType==2)||(farmerType==3))) {
                    money-=200;
                    farmerType = 1;
                    //crop Update Method Here
                }
                break;
            case 2:
                if(level>=15&&money>=250&&!((farmerType==2)||(farmerType==3))) {
                    money-=250;
                    farmerType = 2;
                    //crop Update Method Here
                }
                break;
            case 3:
                if(level>=20&&money>=350&&!((farmerType==3))) {
                    money-=350;
                    farmerType = 3;
                    crop.setFarmerType(3);
                    crop.setHarvestTimeBonus(6-(6*.15));
                    //crop Update Method Here
                }
                break;
                default:
                    System.out.println("Invalid Input");
        }
    }

    public void levelUp(){
        if(exp>=level*100){
            exp-=(level*100);
            level++;
        }
        else{
            System.out.println("Insufficient Exp");
        }
    }

    public void levelBonus(){
        if(level%5>0){
            //crop Update Method Here
        }
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addExp(int exp) {
        this.exp += exp;
    }

    public int getFarmerType() {
        return farmerType;
    }

    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }
}
