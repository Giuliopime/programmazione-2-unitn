package data;

public class Map {
    private final Block[][] map;
    private final int mapXSize;
    private final int mapYSize;


    /**
     * Initializes the map to a 10x10 block map
     */
    public Map() {
        mapXSize = 10;
        mapYSize = 10;
        map = new Block[mapXSize][mapYSize];
    }

    /**
     * Asserts that two coordinates are within the map bounds and the specified xLowerBound and yLowerBound params
     * @param x the x coordinate to check
     * @param y the y coordinate to check
     * @param xLowerBound lowest value the x coordinate can have
     * @param yLowerBound lowest value the y coordinate can have
     */
    private void check_coordinates_bounds(int x, int y, int xLowerBound, int yLowerBound) {
        if (x >= mapXSize || y >= mapXSize || x < xLowerBound || y < yLowerBound) {
            throw new IllegalArgumentException(
                    "The X and Y coordinates are not within the bounds\n" +
                            "X bounds are " + xLowerBound + " - " + mapXSize + "\n" +
                            "Y bounds are " + yLowerBound + " - " + mapYSize
            );
        }
    }

    /**
     * Asserts that two coordinates are within the map bounds and 0 as the lower bound for both coordinates
     * @param x the x coordinate to check
     * @param y the y coordinate to check
     */
    private void check_coordinates_bounds(int x, int y) {
        check_coordinates_bounds(x, y, 0, 0);
    }

    /**
     * Changes the block in the map via its coordinates
     * @param x x coordinate
     * @param y y coordinate
     */
    public void change_cell(int x, int y) {
        check_coordinates_bounds(x, y);

        map[x][y] = new Block('A');
    }

    /**
     * Swaps a block with the block below it
     * This will swap the blocks <b>only</b> if the block below is {@link Block#isFall_through()}
     * @param x the x coordinate of the block to swap
     * @param y the y coordinate of the block to swap
     */
    private void swap(int x, int y) {
        check_coordinates_bounds(x, y, 0 , 1);

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
     * Note that if the block {@link Block#isFalls_with_gravity()} then it will fall down when possible
     * @param x insert x coordinate
     * @param y insert y coordinate
     * @param block block to insert
     */
    public void insert_at_cords(int x, int y, Block block) {
        check_coordinates_bounds(x, y);

        if (block.isFalls_with_gravity() && y != 0) {
            swap(x, y);
        }
    }

    /**
     * @return the total number of blocks in the map
     */
    public int getBlocksCount() {
        return mapXSize * mapYSize;
    }

    /**
     * Shows all the blocks of the map
     */
    public void display_on_out() {
        for (int x=0; x<mapXSize; x++) {
            for (int y=0; y<mapYSize; y++) {
                System.out.print("\t" + map[x][y].display());
            }
            System.out.println("\n");
        }
    }
}
