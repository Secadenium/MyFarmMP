package model;
public class Tools {
    private int fertilizer;
    private Tiles tile;

    public Tools(){

    }

    public void waterCan(){

    }

    public void plowTile(){
        tile.setPlowed(true);
    }

    public void plowWither(){

    }

    public void pickAxe(){

    }

    public void fertilizer(){

    }

    public void addFertilizer(int fertilizer) {
        this.fertilizer += fertilizer;
    }

    public int getFertilizer() {
        return fertilizer;
    }
}
