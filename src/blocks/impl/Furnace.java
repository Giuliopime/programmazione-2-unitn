package blocks.impl;

import blocks.interfaces.Block;
import blocks.interfaces.SmeltableBlock;

/**
 * Can be used to smelt smeltable blocks
 */
public class Furnace {
    private SmeltableBlock input;
    private Block output;

    public Furnace() {
        input = new NullBlock();
        output = new NullBlock();
    }

    /**
     * Sets the furnace input smeltable block
     * Call {@link #smelt()} to smelt the input block and get the smelted block as the output
     * @param input smeltable block
     */
    public void setInput(SmeltableBlock input) {
        this.input = input;
    }

    /**
     * Smelts the input block
     *
     * @throws UnsupportedOperationException if the furnace output is not empty
     */
    public void smelt() throws UnsupportedOperationException {
        if (!(output instanceof NullBlock)) {
            throw new UnsupportedOperationException("The furnace output is not empty");
        }

        output = input.smelt();
        input = new NullBlock();
    }

    /**
     * Gets the block smelted via {@link #smelt()}
     * @throws UnsupportedOperationException if nothing has been smelted yet
     * @return the smelted Block
     */
    public Block getSmeltedBlock() throws UnsupportedOperationException {
        if (output instanceof NullBlock) {
            throw new UnsupportedOperationException("Nothing has been smelted yet in the furnace");
        }

        return output;
    }

    /**
     * Displays the furnace input and output
     */
    public void displayOnOut() {
        System.out.println("|| " + input.display() + " ==> " + output.display() + " ||");
    }
}
