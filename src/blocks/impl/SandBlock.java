package blocks.impl;

import blocks.AbstractBlock;
import blocks.interfaces.Block;
import blocks.interfaces.SmeltableBlock;

public class SandBlock extends AbstractBlock implements SmeltableBlock {

    public SandBlock() {
        super("sand", '_', true, false, true, false, true);
    }

    @Override
    public Block smelt() {
        return new GlassBlock();
    }
}
