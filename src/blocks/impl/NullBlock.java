package blocks.impl;

import blocks.AbstractSolidBlock;
import blocks.interfaces.Block;
import blocks.interfaces.SmeltableBlock;

public class NullBlock extends AbstractSolidBlock implements SmeltableBlock {
    public NullBlock() {
        super("null", '/', false, false);
    }

    @Override
    public Block smelt() {
        return new NullBlock();
    }
}
