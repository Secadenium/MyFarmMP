package model;

import java.util.*;
/**
 *This is the mango crop class which extends the crop superclass.
 * This provides the specific data of the apple crop.
 */
public class Mango extends Crops{
    public Mango(){
        super("Mango",2,7,7,7,4,4,3,5,50,4,3);
        Random rand = new Random();
        int n = rand.nextInt((10-5)+1)+5;
        super.setProductsProd(n);
    }
}
