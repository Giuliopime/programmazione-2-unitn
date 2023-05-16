package blocks;

public abstract class AbstractSolidBlock extends AbstractBlock {
    public AbstractSolidBlock(String name, char content, boolean destroiesFallingBlocks, boolean destroiableOnFall) {
        super(name, content, false, false, true, destroiesFallingBlocks, destroiableOnFall);
    }
}
