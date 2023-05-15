package blocks;

public abstract class AbstractSolidBlock extends AbstractBlock {
    public AbstractSolidBlock(String name, char content) {
        super(name, content, false, false);
    }
}
