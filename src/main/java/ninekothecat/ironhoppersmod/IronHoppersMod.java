package ninekothecat.ironhoppersmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import ninekothecat.ironhoppersmod.blocks.ironhopper.IronHopper;
import ninekothecat.ironhoppersmod.registries.BlockEntityRegistry;
import ninekothecat.ironhoppersmod.registries.BlockRegistry;
import ninekothecat.ironhoppersmod.registries.GeneralScreenHandlerRegistry;
import ninekothecat.ironhoppersmod.registries.ItemRegistry;

public class IronHoppersMod implements ModInitializer {
    public static final String MOD_ID = "ironhoppersmod";
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "maintab"),
            () -> new ItemStack(IronHopper.ironhopper));
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        BlockRegistry.register();
        BlockEntityRegistry.register();
        ItemRegistry.register();
        GeneralScreenHandlerRegistry.register();
    }
}
