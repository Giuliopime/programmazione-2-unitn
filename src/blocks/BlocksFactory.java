package blocks;

import blocks.impl.*;
import ui.Furnace;

public class BlocksFactory {
    public AirBlock getAirBlock() {
        return new AirBlock();
    }

    public Furnace getFurnace() {
        return new Furnace();
    }

    public NullBlock getNullBlock() {
        return new NullBlock();
    }

    public RawIronBlock getRawIronBlock() {
        return new RawIronBlock();
    }

    public SandBlock getSandBlock() {
        return new SandBlock();
    }

    public WaterBlock getWaterBlock() {
        return new WaterBlock();
    }
}
