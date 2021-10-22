package ninekothecat.ironhoppersmod.registries;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.util.registry.Registry;
import ninekothecat.ironhoppersmod.IronHoppersMod;
import ninekothecat.ironhoppersmod.blocks.ironhopper.IronHopper;

public class ItemRegistry {
    public static void register() {
        Registry.register(Registry.ITEM, IronHopper.identifier, new BlockItem(IronHopper.ironhopper, new FabricItemSettings().group(IronHoppersMod.ITEM_GROUP)));

    }
}
