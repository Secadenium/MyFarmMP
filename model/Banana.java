package model;

import java.util.*;
/**
 *This is the banana crop class which extends the crop superclass.
 * This provides the specific data of the apple crop.
 */
public class Banana extends Crops{
    public Banana(){
        super("Banana",2,8,8,8,5,5,3,10,60,3.5,3);
        Random rand = new Random();
        int n = rand.nextInt((15-10)+1)+10;
        super.setProductsProd(n);
        //super.adjustBonus(10,60,8,5,8);
    }
}
