package blocks;

import blocks.interfaces.Block;

public abstract class AbstractBlock implements Block {
    protected final String blockName;

    protected final char content;

    protected final boolean falls_with_gravity;
    protected final boolean fall_through;

    protected AbstractBlock(String blockName, char content, boolean fallsWithGravity, boolean fallThrough) {
        this.blockName = blockName;
        this.content = content;
        falls_with_gravity = fallsWithGravity;
        fall_through = fallThrough;
    }

    /**
     * @return the content of the bloc
     */
    public char display() {
        return content;
    }

    public boolean isFalls_with_gravity() {
        return falls_with_gravity;
    }

    public boolean isFall_through() {
        return fall_through;
    }

    @Override
    public String toString() {
        return "AbstractBlock{" +
                "blockName='" + blockName + '\'' +
                ", content=" + content +
                ", falls_with_gravity=" + falls_with_gravity +
                ", fall_through=" + fall_through +
                '}';
    }
}
