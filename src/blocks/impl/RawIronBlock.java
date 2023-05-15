package blocks.impl;

import blocks.AbstractSolidBlock;
import blocks.interfaces.Block;
import blocks.interfaces.SmeltableBlock;

public class RawIronBlock extends AbstractSolidBlock implements SmeltableBlock {
    public RawIronBlock() {
        super("raw-iron", 'i');
    }

    @Override
    public Block smelt() {
        return new IronSwordBlock();
    }
}
