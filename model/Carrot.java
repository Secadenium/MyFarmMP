package model;

import java.util.*;
/**
 *This is the carrot crop class which extends the crop superclass.
 * This provides the specific data of the apple crop.
 */
public class Carrot extends Crops {
    public Carrot (){
        super("Carrot",0,1.5,1,2,0,1,1,1,10,9,8);
        Random rand = new Random();
        int n = rand.nextInt((2-1)+1)+1;
        super.setProductsProd(n);
        //super.adjustBonus(10,10,2,1,1.5);
    }
}
