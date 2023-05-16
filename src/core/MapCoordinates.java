package core;

/**
 * Utility class for defining map coordinates
 */
public class MapCoordinates {
    /**
     * Number of rows of the map
     */
    public static final int MAP_X_SIZE = 10;

    /**
     * Number of columns of the map
     */
    public static final int MAP_Y_SIZE = 10;

    /**
     * Asserts that two coordinates are within the map bounds and the specified xLowerBound and yLowerBound params
     * @param x the x coordinate to check
     * @param y the y coordinate to check
     * @param xLowerBound lowest value the x coordinate can have
     * @param yLowerBound lowest value the y coordinate can have
     * @throws IllegalArgumentException if the coordinates are not within bounds
     */
    public static void checkCoordinatesBounds(int x, int y, int xLowerBound, int yLowerBound) throws IllegalArgumentException {
        if (x >= MapCoordinates.MAP_Y_SIZE || y >= MapCoordinates.MAP_X_SIZE || x < xLowerBound || y < yLowerBound) {
            throw new IllegalArgumentException(
                    "The X and Y coordinates are not within the bounds\n" +
                            "X bounds are " + xLowerBound + " - " + MAP_X_SIZE + "\n" +
                            "Y bounds are " + yLowerBound + " - " + MAP_Y_SIZE
            );
        }
    }

    /**
     * Asserts that two coordinates are within the map bounds and 0 as the lower bound for both coordinates
     * @param x the x coordinate to check
     * @param y the y coordinate to check
     * @throws IllegalArgumentException if the coordinates are not within bounds
     */
    public static void checkCoordinatesBounds(int x, int y) throws IllegalArgumentException {
        checkCoordinatesBounds(x, y, 0, 0);
    }

    /**
     * @return the total number of blocks with the current coordinate bounds
     */
    public static int getMapArea() {
        return MAP_X_SIZE * MAP_Y_SIZE;
    }
}
