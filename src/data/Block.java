package data;

public class Block {
    private final char content;

    private final boolean falls_with_gravity;
    private final boolean fall_through;

    public Block() {
        content = '.';
        falls_with_gravity = false;
        fall_through = true;
    }

    public Block(char contenuto) {
        this.content = contenuto;
        falls_with_gravity = true;
        fall_through = false;
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
}
