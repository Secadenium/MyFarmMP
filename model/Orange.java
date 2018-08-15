package model;
import java.util.*;
/**
 *This is the orange crop class which extends the crop superclass.
 * This provides the specific data of the apple crop.
 */
public class Orange extends Crops{
    public Orange(){
        super("Orange",2,8,8,8,6,6,3,13,65,4.5,3);
        Random rand = new Random();
        int n = rand.nextInt((15-13)+1)+13;
        super.setProductsProd(n);
        //super.adjustBonus(10,65,8,6,8);
    }
}
