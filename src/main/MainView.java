package main;

import blocks.impl.AirBlock;
import blocks.impl.NullBlock;
import blocks.interfaces.Block;
import ui.Furnace;
import blocks.interfaces.SmeltableBlock;
import ui.Inventory;
import ui.Map;

public class MainView {
    private Map map;
    private Inventory inventory;
    private Furnace furnace;

    public MainView() {
        map = new Map();
        furnace = new Furnace();
    }

    /**
     * Picks up a block from the map and puts it into the inventory
     */
    public void pickUpBlock(int x, int y) {
        try {
            if (map.isPickable(x, y)) {
                inventory.addBlock(map.pickUpBlock(x, y));
            } else {
                System.out.println("That block is not pickable!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Moves a block from the inventory into the furnace if it's smeltable
     * @param index inventory block index
     */
    public void moveIntoFurnaceFromInventory(int index) {
        try {
            SmeltableBlock smeltableBlock = inventory.getSmeltable(index);
            furnace.setInput(smeltableBlock);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The inventory index is invalid");
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Moves the smelted block from the furnace into the inventory
     */
    public void moveSmeltedIntoInventoryFromFurnace() {
        try {
            inventory.addBlock(furnace.getSmeltedBlock());
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void smelt() {
        try {
            furnace.smelt();
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void toggleInventoryComparator() {
        inventory.toggleComparator();
    }

/*
    public void moveIntoFurnace(int x, int y) {
        try {
            SmeltableBlock block = map.setupBlockOfCoordinatesForSmelting(x, y);
            furnace.setInput(block);
            try {
                furnace.smelt();
                Block smeltedBlock = furnace.getSmeltedBlock();
                inventory.addBlock(smeltedBlock);
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Coordinates not valid");
        } catch (UnsupportedOperationException e) {
            System.out.println("The block selected to smelt is not smeltable");
        }
    }*/
}
