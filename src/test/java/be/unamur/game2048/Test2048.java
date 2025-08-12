package be.unamur.game2048;

import static org.junit.Assert.*;

import org.junit.Test;
import be.unamur.game2048.controllers.*;
import be.unamur.game2048.helpers.*;
import be.unamur.game2048.models.*;

public class Test2048 {

    @Test
    public void testTileEqualsOk() {
        // Arrange
        Tile tile1 = new Tile(2);
        Tile tile2 = new Tile(2);

        // Assert
        assertTrue(tile1.equals(tile2));
    }

    @Test
    public void testTileEqualsDifferent() {
        // Arrange
        Tile tile1 = new Tile(2);
        Tile tileDiff = new Tile(4);

        // Assert
        assertFalse(tile1.equals(tileDiff));
    }

    @Test
    public void testTileEqualsWithSelf() {
        // Arrange
        Tile tile1 = new Tile(2);

        // Assert
        assertTrue(tile1.equals(tile1));
    }

    @Test
    public void testTileEqualsOtherType() {
        // Arrange
        Tile tile1 = new Tile(2);
        Integer other = 5;
        // Assert
        assertFalse(tile1.equals(other));
    }

    @Test
    public void testTileEqualsNull() {
        // Arrange
        Tile tile1 = new Tile(2);
        // Assert
        assertFalse(tile1.equals(null));
    }


    @Test
    public void testTileSetMergedFalse(){
        // Arrange
        Tile tile1 = new Tile(2);

        // Assert
        assertFalse(tile1.isMerged());
    }

    @Test
    public void testTileSetMergedTrue(){
        // Arrange
        Tile tile1 = new Tile(2);

        // Act
        tile1.setMerged(true);

        // Assert
        assertTrue(tile1.isMerged());
    }

    @Test
    public void testTileTostringWithGetValue(){
        // Arrange
        Integer val = 2;
        Tile tile1 = new Tile(val);

        // Assert
        assertTrue(new Integer(tile1.getValue()).toString().equals(val.toString()));
    }

    @Test
    public void testTileTostringNormal(){
        // Arrange
        Integer val = 2;
        Tile tile1 = new Tile(val);

        // Assert
        assertTrue(tile1.toString().equals(val.toString()));
    }

    @Test
    public void testTileCanMergeWithSame(){
        // Arrange
        Tile tile1 = new Tile(2);
        Tile tile1_dub = new Tile(2);

        // Assert
        // assertFalse(tile1.canMergeWith(tile1));
        assertTrue(tile1.canMergeWith(tile1_dub));
    }

    @Test
    public void testTileCanMergeWithNull(){
        // Arrange
        Tile tile1 = new Tile(2);

        // Assert
        assertFalse(tile1.canMergeWith(null));
    }

    @Test
    public void testTileCanMergeWithAlredyMerged(){
        // Arrange
        Tile tile1 = new Tile(2);
        Tile tile2 = new Tile(2);

        // Act
        tile2.setMerged(true);


        // Assert
        assertFalse(tile1.canMergeWith(tile2));
    }

    @Test
    public void testTileCanMergeWithDifferent(){
        // Arrange
        Tile tile1 = new Tile(2);
        Tile tile3 = new Tile(4);

        // Assert
        assertFalse(tile1.canMergeWith(tile3));
    }

    @Test
    public void testTileMergeWithSame(){
        // Arrange
        Tile tile1 = new Tile(2);
        Tile tile2 = new Tile(2);

        // Assert
        assertTrue(tile1.mergeWith(tile2) == 4);
        assertTrue(tile1.isMerged());
    }

    @Test
    public void testTileMergeWithDifferent(){
        // Arrange
        Tile tile1 = new Tile(2);
        Tile tile2 = new Tile(4);

        // Assert
        assertTrue(tile1.mergeWith(tile2) == -1);
    }

    @Test
    public void testGridGetLength(){
        // Arrange
        Grid grid = new Grid();
        // Assert
        assertTrue(grid.getLength() == GameParams.sideLength);
    }

    @Test
    public void testGridDefault(){
        // Arrange
        Grid grid = new Grid();
        // Assert
        for(int i = 0; i < grid.getLength(); i++){
            for(int j = 0; j < grid.getLength(); j++){
                assertNull(grid.getTile(i, j));
            }
        }
    }

    @Test
    public void testGridSetTile(){
        // Arrange
        Grid grid = new Grid();
        Tile tile1 = new Tile(4);
        Tile tile2 = new Tile(4);
        //Act
        grid.setTile(2, tile1);
        // Assert
        assertTrue(grid.getTile(2).equals(tile2));

    }

    @Test
    public void testGridWithTiles(){
        // Arrange
        Tile[][] tiles = new Tile[GameParams.sideLength][GameParams.sideLength];
        Integer[][] values = new Integer[GameParams.sideLength][GameParams.sideLength];
        for(int i = 0; i < GameParams.sideLength; i++){
            for(int j = 0; j < GameParams.sideLength; j++){
                tiles[i][j] = new Tile((i*j)+2);
                values[i][j] = new Integer((i*j)+2);
            }
        }
        Grid grid = new Grid(tiles);

        // Assert
        for(int i = 0; i < grid.getLength(); i++){
            assertTrue(GridHelper.rowEqual(grid, i, values[i]));
        }

    }

    @Test
    public void testGridGetCol(){
        // Arrange
        Grid grid = new Grid( );
        Tile[] tiles = new Tile[GameParams.sideLength];
        Integer[] values = new Integer[GameParams.sideLength];
        // act
        for(int j = 0; j < GameParams.sideLength; j++){
            grid.setTile(4*j,new Tile((j)+2));
            values[j] = new Integer((j)+2);
        }


        // Assert
        assertTrue(GridHelper.colEqual(grid, 0, values));

    }

    @Test
    public void testGridGetRow(){
        // Arrange
        Grid grid = new Grid( );
        Tile[] tiles = new Tile[GameParams.sideLength];
        Integer[] values = new Integer[GameParams.sideLength];
        // act
        for(int j = 0; j < GameParams.sideLength; j++){
            grid.setTile(j,new Tile((j)+2));
            values[j] = new Integer((j)+2);
        }


        // Assert
        assertTrue(GridHelper.rowEqual(grid, 0, values));

    }


    @Test
    public void testGridClearMerged(){
        // Arrange
        Tile[][] tiles = new Tile[GameParams.sideLength][GameParams.sideLength];
        Integer[][] values = new Integer[GameParams.sideLength][GameParams.sideLength];
        for(int i = 0; i < GameParams.sideLength; i++){
            for(int j = 0; j < GameParams.sideLength; j++){
                tiles[i][j] = new Tile((i*j)+2);
                values[i][j] = new Integer((i*j)+2);
            }
        }
        Grid grid = new Grid(tiles);
        grid.getTile(2,2).setMerged(true);
        // Assert
        assertTrue(grid.getTile(2,2).isMerged());
        grid.clearMerged();

        for(int i = 0; i < grid.getLength(); i++){
            for(Tile tile : grid.getRow(i)){
                assertFalse(tile.isMerged());
            }
        }

    }

    @Test
    public void testGameControllerGetGameStateDefault(){
        // Arrange
        GameController gc = new GameController();

        // Assert
        assertTrue(gc.getGamestate() == GameState.start);

    }



    @Test
    public void testGameControllerStartGameDefault(){
        // Arrange
        GameController gc = new GameController();
        // Act
        gc.startGame();
        Grid grid = gc.getGrid();

        // Assert
        assertFalse(grid == null);
        assertTrue(gc.getGamestate() == GameState.running);
        assertTrue(gc.getScore() == 0);
        assertTrue(gc.getHighestScore() == 0);
    }

}
