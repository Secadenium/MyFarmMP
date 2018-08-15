package model;

public class Farmer {
    private String name;
    private int exp;
    private int level;
    private double money;
    private int farmerType;
    private Crops crop;
    private Tools tool;
    private Inventory inventory;
    private Crops tur,c,to,p,r,tul,st,su,m,a,b,o;

    /**
     * This is the Farmer class which acts as the main class wherein all the other classes are connected to.
     * everything passes through this class.
     * Everything is also initialized here (crops, tools, inventory)
     */
    public Farmer(){
        crop = new Crops("",0,0,0,0,0,0,0,0,0,0,0);
        tool = new Tools();
        inventory = new Inventory();
        exp=0;
        level=1;
        money=200;
        farmerType=0;
        inventory.setFertilizerCount(inventory.getFertilizerCount()+5);

        tur = new Turnip();
        c = new Carrot();
        to = new Tomato();
        p = new Potato();

        r = new Rose();
        tul = new Tulip();
        st = new Stargazer();
        su = new Sunflower();

        m = new Mango();
        a = new Apple();
        b = new Banana();
        o = new Orange();
    }

    /**
     * This method is how the farmer can upgrade their registry level.
     * It checks if the farmer's level, money and exp is applicable to rank up
     * @param upgrade
     */
    public void upgradeFarmer(int upgrade){
        switch (upgrade){
            case 1:
                if(level>=10&&money>=200&&!((farmerType==1)||(farmerType==2)||(farmerType==3))) {
                    money-=200;
                    farmerType = 1;
                    crop.setFarmerType(1);
                }
                break;
            case 2:
                if(level>=15&&money>=250&&!((farmerType==2)||(farmerType==3))) {
                    money-=250;
                    farmerType = 2;
                    crop.setFarmerType(2);
                }
                break;
            case 3:
                if(level>=20&&money>=350&&!((farmerType==3))) {
                    money-=350;
                    farmerType = 3;
                    crop.setFarmerType(3);
                }
                break;
                default:
                    crop.setFarmerType(0);
                    break;
        }
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Tools getTool() {
        return tool;
    }

    public Crops getTur() {
        return tur;
    }

    public Crops getC() {
        return c;
    }

    public Crops getTo() {
        return to;
    }

    public Crops getP() {
        return p;
    }

    public Crops getR() {
        return r;
    }

    public Crops getTul() {
        return tul;
    }

    public Crops getSt() {
        return st;
    }

    public Crops getSu() {
        return su;
    }

    public Crops getM() {
        return m;
    }

    public Crops getA() {
        return a;
    }

    public Crops getB() {
        return b;
    }

    public Crops getO() {
        return o;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
