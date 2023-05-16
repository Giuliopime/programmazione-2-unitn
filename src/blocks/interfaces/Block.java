package blocks.interfaces;

public interface Block extends InventoryBlock {
    char display();

    boolean isFallsWithGravity();

    boolean isFallThrough();

    boolean isPickable();

    boolean isDestroiesFallingBlocks();

    boolean isDestroiableOnFall();
}
