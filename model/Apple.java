package model;

import java.util.*;

/**
 *This is the apple crop class which extends the crop superclass.
 * This provides the specific data of the apple crop.
 */
public class Apple extends Crops{
    public Apple(){
        super("Apple",2,7,7,7,5,4,3,7,55,3.5,3);
        Random rand = new Random();
        int n = rand.nextInt((10-7)+1)+7;
        super.setProductsProd(n);
    }
}
