package ninekothecat.ironhoppersmod.registries;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import ninekothecat.ironhoppersmod.blocks.ironhopper.IronHopper;
import ninekothecat.ironhoppersmod.blocks.ironhopper.IronHopperBlockEntity;

public class BlockEntityRegistry {
    public static BlockEntityType<IronHopperBlockEntity> IronHopperEntityType;

    public static void register() {
        IronHopperEntityType = BlockEntityType.Builder.create(IronHopperBlockEntity::new, IronHopper.ironhopper).build(null);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, IronHopperBlockEntity.identifier, IronHopperEntityType);
    }
}
