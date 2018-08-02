import java.util.*;

public class Potato extends Crops{
    public Potato(){
        super("Potato",0,5,4,5,2,3,1,1,25,13,25);
        Random rand = new Random();
        int n = rand.nextInt((6-1)+1)+1;
        super.setProductsProd(n);
        //super.adjustBonus(10,25,5,3,5);
    }
}
