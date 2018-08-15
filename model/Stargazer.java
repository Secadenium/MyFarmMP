package model;
/**
 *This is the stargazer crop class which extends the crop superclass.
 * This provides the specific data of the apple crop.
 */
public class Stargazer extends Crops {
    public Stargazer(){
        super("Stargazer",1,2.5,2,3,0, 1,2,1,10,9, 13);
        //super.adjustBonus(10,10,3,1,2.5);
    }
}
