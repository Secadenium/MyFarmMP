package model;

import java.util.*;
/**
 *This is the tomato crop class which extends the crop superclass.
 * This provides the specific data of the apple crop.
 */
public class Tomato extends Crops {
    public Tomato(){
        super("Tomato",0,2.5,3,4,1,2,1,1,20,15,13);
        Random rand = new Random();
        int n = rand.nextInt((3-1)+1)+1;
        super.setProductsProd(n);
        //super.adjustBonus(10,20,4,2,2.5);
    }
}
