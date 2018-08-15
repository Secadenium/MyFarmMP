package model;
/**
 *This is the rose crop class which extends the crop superclass.
 * This provides the specific data of the apple crop.
 */
public class Rose extends Crops{
    public Rose(){
        super("Rose",1,1,1,2,0,1,2,1,5,5, 5);
        //super.adjustBonus(10,5,2,1,1);
    }
}
