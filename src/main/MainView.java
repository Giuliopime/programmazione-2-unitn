package main;

import blocks.impl.Furnace;
import blocks.interfaces.SmeltableBlock;
import data.Map;

public class MainView {
    private Map map;
    private Furnace furnace;

    public MainView() {
        map = new Map();
        furnace = new Furnace();
    }

    public void moveIntoFurnace(int x, int y) {
        try {
            SmeltableBlock block = map.setupBlockOfCoordinatesForSmelting(x, y);
            furnace.setInput(block);
            try {
                furnace.smelt();
            } catch (UnsupportedOperationException e) {
                System.out.println("The furnace output is not empty");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Coordinates not valid");
        } catch (UnsupportedOperationException e) {
            System.out.println("The block selected to smelt is not smeltable");
        }
    }
}
