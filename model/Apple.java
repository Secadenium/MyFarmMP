import java.util.*;

public class Apple extends Crops{
    public Apple(){
        super("Apple",2,7,7,7,5,4,3,7,55,3.5,3);
        Random rand = new Random();
        int n = rand.nextInt((10-7)+1)+7;
        super.setProductsProd(n);
        //super.adjustBonus(10,55,7,5,7);
    }
}
