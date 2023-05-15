package blocks.impl;

import blocks.AbstractBlock;

public class AirBlock extends AbstractBlock {
    public AirBlock() {
        super(
                "air",
                '.',
                false,
                true
        );
    }
}
