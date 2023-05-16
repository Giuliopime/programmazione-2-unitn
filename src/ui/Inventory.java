package ui;

import blocks.AbstractBlock;
import blocks.interfaces.Block;
import blocks.interfaces.InventoryBlock;
import blocks.interfaces.SmeltableBlock;
import core.AlphabeticalBlockComparator;
import core.BlockComparator;

import java.util.ArrayList;
import java.util.Comparator;

public class Inventory {
    private ArrayList<InventoryBlock> inventoryBlocks;
    private Comparator<Block> comparator;

    public Inventory() {
        this.inventoryBlocks = new ArrayList<>();
        this.comparator = new BlockComparator();
    }

    public void toggleComparator() {
        if (comparator instanceof BlockComparator) {
            comparator = new AlphabeticalBlockComparator();
        } else {
            comparator = new BlockComparator();
        }
    }

    public void sort() {
        inventoryBlocks.sort(comparator);
    }


    /**
     * Adds a block to the inventory
     * @param block
     */
    public void addBlock(InventoryBlock block) {
        inventoryBlocks.add(block);
        sort();
    }

    /**
     * Get a smeltable block from the inventory via its index and removes it from the inventory!
     * @param index
     * @return the smeltable block
     * @throws IndexOutOfBoundsException if the index is not valid
     * @throws UnsupportedOperationException if the block at that index is not smeltable
     */
    public SmeltableBlock getSmeltable(int index) throws IndexOutOfBoundsException, UnsupportedOperationException {
        InventoryBlock block = inventoryBlocks.remove(index);

        if (!(block instanceof SmeltableBlock)) {
            throw new UnsupportedOperationException("The block at index " + index + " is not smeltable");
        }

        return (SmeltableBlock) block;
    }

    /**
     * Displays the content of the inventory by calling {@link InventoryBlock#displayInInventory()} on each block
     */
    public void display() {
        for(InventoryBlock block: inventoryBlocks) {
            block.displayInInventory();
        }
    }
}
