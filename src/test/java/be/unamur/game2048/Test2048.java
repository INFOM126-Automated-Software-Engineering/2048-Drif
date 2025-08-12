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

@Test
public void testStartGame_firstTileIs4_then2() {
    GameController gc = new GameController();
    gc.startGame(); // adds 2 tiles using "first empty from top-left"

    Grid grid = gc.getGrid();
    assertNotNull(grid);

    // By design: first added when tileAdded==0 is 4, then a 2 just after.
    assertEquals(4, grid.getTile(0).getValue());
    assertEquals(2, grid.getTile(1).getValue());
}

@Test
public void testMoveRight_merges1024_into2048_andWins() {
    // Arrange: two adjacent 1024 on the top row, moving right should merge to 2048
    Tile[][] tiles = new Tile[GameParams.sideLength][GameParams.sideLength];
    tiles[0][2] = new Tile(1024);
    tiles[0][3] = new Tile(1024);

    GameController gc = new GameController();
    gc.startGame(tiles);
    assertEquals(GameState.running, gc.getGamestate());

    // Act
    boolean moved = gc.moveRight(false);

    // Assert
    assertTrue(moved);
    assertEquals(GameState.won, gc.getGamestate());
    assertEquals(2048, gc.getHighestScore());
    assertNotNull(gc.getGrid().getTile(0, 3));
    assertEquals(2048, gc.getGrid().getTile(0, 3).getValue());
    assertNull(gc.getGrid().getTile(0, 2));
}

@Test
public void testGameOver_afterOneValidMove_thenNoMovesLeft() {
    // Arrange: checkerboard (no merges anywhere) with a single empty at (0,0)
    // After a LEFT move, something slides, then a new tile is added; no moves remain => over.
    Tile[][] tiles = new Tile[GameParams.sideLength][GameParams.sideLength];
    Integer[][] vals = {
        {null, 2, 4, 2},
        {4, 2, 4, 2},
        {2, 4, 2, 4},
        {4, 2, 4, 2}
    };
    for (int r = 0; r < GameParams.sideLength; r++) {
        for (int c = 0; c < GameParams.sideLength; c++) {
            if (vals[r][c] != null) {
                tiles[r][c] = new Tile(vals[r][c]);
            }
        }
    }

    GameController gc = new GameController();
    gc.startGame(tiles);
    assertEquals(GameState.running, gc.getGamestate());

    // Act
    boolean moved = gc.moveLeft(false);

    // Assert: a move happened, and since there are no merges possible afterwards, the game should be over
    assertTrue(moved);
    assertEquals(GameState.over, gc.getGamestate());
}
