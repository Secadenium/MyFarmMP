package model;

public class Land {
    private Tiles[][] tile;

    public Land(){

        tile = new Tiles[5][10];

        for(int i=0;i<5;i++)
            for(int j=0;j<10;j++) {
                addTile(new Tiles(), i, j);
            }
    }

    public void addTile(Tiles tiles,int x, int y){
        tile[x][y]=tiles;
    }

    public void display(){
        for(int i=0;i<5;i++)
            for(int j=0;j<10;j++)
                System.out.println(tile[i][j].getHasRock());
    }
}
