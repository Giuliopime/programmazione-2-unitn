package ui;

import blocks.impl.AirBlock;
import blocks.impl.WaterBlock;
import blocks.interfaces.Block;
import core.MapCoordinates;

public class Map {
    private final Block[][] map;

    /**
     * Initializes the map to a 10x10 block map
     */
    public Map() {
        map = new Block[MapCoordinates.MAP_X_SIZE][MapCoordinates.MAP_X_SIZE];

        for (int i=0; i<MapCoordinates.MAP_X_SIZE; i++) {
            for (int y=0; y<MapCoordinates.MAP_X_SIZE; y++) {
                map[i][y] = new AirBlock();
            }
        }
    }

    /**
     * Changes the block in the map via its coordinates
     * @param x x coordinate
     * @param y y coordinate
     * @throws IllegalArgumentException if the coordinates are not within bounds
     */
    public void removeBlock(int x, int y) throws IllegalArgumentException {
        MapCoordinates.checkCoordinatesBounds(x, y);

        map[x][y] = new AirBlock();
    }

    /**
     * Swaps a block with the block below it
     * This will swap the blocks <b>only</b> if the block below is {@link AirBlock#isFallThrough()}
     * @param x the x coordinate of the block to swap
     * @param y the y coordinate of the block to swap
     * @throws IllegalArgumentException if the coordinates are not within bounds
     */
    private void swap(int x, int y) throws IllegalArgumentException {
        MapCoordinates.checkCoordinatesBounds(x, y, 0 , 1);

        Block block = map[x][y];
        Block blockBelow = map[x][y-1];

        if (blockBelow.isFallThrough()) {
            map[x][y-1] = block;
            map[x][y] = blockBelow;
            if (y-1 > 0) {
                swap(x, y - 1);
            }
        } else if (blockBelow.isDestroiesFallingBlocks() && block.isDestroiableOnFall()) {
            map[x][y] = new AirBlock();
            if (y + 1 < MapCoordinates.MAP_Y_SIZE) {
                makeFall(x, y+1);
            }
        }
    }

    private void makeFall(int x, int y) {
        MapCoordinates.checkCoordinatesBounds(x, y, 0 , 0);

        // Can't fall in the void
        if (y == 0) {
            return;
        }

        Block block = map[x][y];
        Block blockBelow = map[x][y-1];

        if (block.isFallsWithGravity() && blockBelow.isFallThrough()) {
            map[x][y-1] = map[x][y];
            map[x][y] = blockBelow;
            if (y-1 > 0) {
                swap(x, y - 1);
            }
        }
    }

    /**
     * Insert a block in the map via the coordinates
     * Note that if the block {@link AirBlock#isFallsWithGravity()} then it will fall down when possible
     * @param x insert x coordinate
     * @param y insert y coordinate
     * @param block block to insert
     * @throws IllegalArgumentException if the coordinates are not within bounds
     */
    public void insertAtCords(int x, int y, Block block) throws IllegalArgumentException {
        MapCoordinates.checkCoordinatesBounds(x, y);

        map[x][y] = block;

        if (block.isFallsWithGravity() && y != 0) {
            swap(x, y);
        }
    }

    /**
     * Shows all the blocks of the map
     */
    public void displayOnOut() {
        // This is reversed because we print to the console starting from the top instead of the bottom
        for (int x=MapCoordinates.MAP_X_SIZE - 1; x>=0; x--) {
            for (int y=0; y<MapCoordinates.MAP_Y_SIZE; y++) {
                System.out.print("\t" + map[x][y].display());
            }
            System.out.println("\n");
        }
    }

    public void addSea() {
        addColumnsOfWater(3);
    }

    public void addRiver() {
        addColumnsOfWater(1);
    }

    /**
     * Adds columns of water to the map, starts from the top row
     * @param amount amount of columns of water to add
     */

    private void addColumnsOfWater(int amount) {
        try {
            for (int i=0; i<amount; i++) {
                insertAtCords(i, MapCoordinates.MAP_Y_SIZE - 1, new WaterBlock());
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPickable(int x, int y) throws IllegalArgumentException {
        MapCoordinates.checkCoordinatesBounds(x, y);

        return map[x][y].isPickable();
    }

    private void checkForGravity(int x, int y) {
        // TODO: Create coordinate class and move check on its constructor
        MapCoordinates.checkCoordinatesBounds(x, y);

        for (int i=y; i<MapCoordinates.MAP_Y_SIZE; i++) {
            makeFall(x, i);
        }
    }

    /**
     * Used to pick up a block and replace it with an AirBlock.
     * Use {@link #isPickable(int, int)} before calling this method
     * @param x
     * @param y
     * @return the picked up block
     * @throws IllegalArgumentException if the coordinates are invalid
     * @throws UnsupportedOperationException if the block is not pickable
     */
    public Block pickUpBlock(int x, int y) throws IllegalArgumentException, UnsupportedOperationException {
        if (isPickable(x, y)) {
            Block block = map[x][y];
            map[x][y] = new AirBlock();

            checkForGravity(x, y + 1);

            return block;
        } else {
            throw new UnsupportedOperationException("The block is not pickable");
        }
    }

    /**
     * Retrieves the block at certain cords if it's a smeltable block
     * @param x x coords
     * @param y y coords
     * @return the smeltable block
     * @throws UnsupportedOperationException if the block at coords is not smeltable
     * @throws IllegalArgumentException If the coordinates are not within bounds
     */
    /*public SmeltableBlock setupBlockOfCoordinatesForSmelting(int x, int y) throws UnsupportedOperationException, IllegalArgumentException {
        MapCoordinates.checkCoordinatesBounds(x, y);

        Block blockToSmelt = map[x][y];
        if (!(blockToSmelt instanceof SmeltableBlock)) {
            throw new UnsupportedOperationException("The block at coordinates x=" + x + " y=" + y + " is not smeltable");
        }

        return (SmeltableBlock) blockToSmelt;
    }*/
}
