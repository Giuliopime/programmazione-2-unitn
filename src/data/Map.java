package data;

import blocks.impl.AirBlock;
import blocks.impl.WaterBlock;
import blocks.interfaces.Block;
import blocks.interfaces.SmeltableBlock;

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

    public void test() {
        addRiver();
    }

    /**
     * Changes the block in the map via its coordinates
     * @param x x coordinate
     * @param y y coordinate
     * @throws IllegalArgumentException if the coordinates are not within bounds
     */
    public void change_cell(int x, int y) throws IllegalArgumentException {
        MapCoordinates.checkCoordinatesBounds(x, y);

        map[x][y] = new AirBlock();
    }

    /**
     * Swaps a block with the block below it
     * This will swap the blocks <b>only</b> if the block below is {@link AirBlock#isFall_through()}
     * @param x the x coordinate of the block to swap
     * @param y the y coordinate of the block to swap
     * @throws IllegalArgumentException if the coordinates are not within bounds
     */
    private void swap(int x, int y) throws IllegalArgumentException {
        MapCoordinates.checkCoordinatesBounds(x, y, 0 , 1);

        Block blockBelow = map[x][y-1];
        if (blockBelow.isFall_through()) {
            map[x][y-1] = map[x][y];
            map[x][y] = blockBelow;
            if (y-1 > 0) {
                swap(x, y - 1);
            }
        }
    }

    /**
     * Insert a block in the map via the coordinates
     * Note that if the block {@link AirBlock#isFalls_with_gravity()} then it will fall down when possible
     * @param x insert x coordinate
     * @param y insert y coordinate
     * @param block block to insert
     * @throws IllegalArgumentException if the coordinates are not within bounds
     */
    public void insertAtCords(int x, int y, Block block) throws IllegalArgumentException {
        MapCoordinates.checkCoordinatesBounds(x, y);

        map[x][y] = block;

        if (block.isFalls_with_gravity() && y != 0) {
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

    /**
     * Retrieves the block at certain cords if it's a smeltable block
     * @param x x coords
     * @param y y coords
     * @return the smeltable block
     * @throws UnsupportedOperationException if the block at coords is not smeltable
     * @throws IllegalArgumentException If the coordinates are not within bounds
     */
    public SmeltableBlock setupBlockOfCoordinatesForSmelting(int x, int y) throws UnsupportedOperationException, IllegalArgumentException {
        MapCoordinates.checkCoordinatesBounds(x, y);

        Block blockToSmelt = map[x][y];
        if (!(blockToSmelt instanceof SmeltableBlock)) {
            throw new UnsupportedOperationException("The block at coordinates x=" + x + " y=" + y + " is not smeltable");
        }

        return (SmeltableBlock) blockToSmelt;
    }
}
