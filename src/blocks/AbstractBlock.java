package blocks;

import blocks.interfaces.Block;

public abstract class AbstractBlock implements Block {
    protected final String blockName;

    protected final char content;

    protected final boolean fallsWithGravity;

    protected final boolean fallThrough;

    protected final boolean pickable;

    protected final boolean destroiesFallingBlocks;

    protected final boolean destroiableOnFall;

    protected AbstractBlock(String blockName, char content, boolean fallsWithGravity, boolean fallThrough, boolean pickable, boolean destroiesFallingBlocks, boolean destroiableOnFall) {
        this.blockName = blockName;
        this.content = content;
        this.fallsWithGravity = fallsWithGravity;
        this.fallThrough = fallThrough;
        this.pickable = pickable;
        this.destroiesFallingBlocks = destroiesFallingBlocks;
        this.destroiableOnFall = destroiableOnFall;
    }

    /**
     * @return the content of the bloc
     */
    public char display() {
        return content;
    }

    public boolean isFallsWithGravity() {
        return fallsWithGravity;
    }

    public boolean isFallThrough() {
        return fallThrough;
    }

    public boolean isPickable() {
        return pickable;
    }

    public boolean isDestroiableOnFall() {
        return destroiableOnFall;
    }

    public boolean isDestroiesFallingBlocks() {
        return destroiesFallingBlocks;
    }

    @Override
    public void displayInInventory() {
        System.out.println(content);
    }

    @Override
    public String toString() {
        return "AbstractBlock{" +
                "blockName='" + blockName + '\'' +
                ", content=" + content +
                ", falls_with_gravity=" + fallsWithGravity +
                ", fall_through=" + fallThrough +
                '}';
    }
}
