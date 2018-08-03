package model;
import java.util.Random;

public class Tiles {
    private boolean isPlowed;
    private boolean isOccupied;
    private boolean hasRock;
    private Tools tool;

    public Tiles(){
        Random rand = new Random();
        int n = rand.nextInt(100)+1;

        if(n>=75&&n<=100){
            hasRock=true;
            isOccupied=true;
        }
        else {
            hasRock = false;
            isOccupied = false;
        }
        isPlowed=false;

    }

    public boolean getHasRock(){
        return hasRock;
    }

    public void setPlowed(boolean plowed) {
        isPlowed = plowed;
    }
}
