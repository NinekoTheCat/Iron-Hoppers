package ninekothecat.ironhoppersmod.registries;

import net.minecraft.util.registry.Registry;
import ninekothecat.ironhoppersmod.blocks.ironhopper.IronHopper;

public class BlockRegistry {

    public static void register() {
        Registry.register(Registry.BLOCK, IronHopper.identifier, IronHopper.ironhopper);
    }
}
