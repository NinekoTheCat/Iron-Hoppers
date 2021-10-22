package ninekothecat.ironhoppersmod.blocks.ironhopper;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import ninekothecat.ironhoppersmod.IronHoppersMod;
import org.jetbrains.annotations.Nullable;

public class IronHopper extends HopperBlock {
    public static final Identifier identifier = new Identifier(IronHoppersMod.MOD_ID, "ironhopper");
    public static final IronHopper ironhopper = new IronHopper(FabricBlockSettings.of(Material.METAL).strength(4.0f));

    public IronHopper(Settings settings) {
        super(settings);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof IronHopperBlockEntity) {
                ItemScatterer.spawn(world, pos, (IronHopperBlockEntity) blockEntity);
                // update comparators
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity == null) {
                return ActionResult.FAIL;
            }

            if (blockEntity instanceof IronHopperBlockEntity) {
                player.openHandledScreen((IronHopperBlockEntity) blockEntity);
                player.incrementStat(Stats.INSPECT_HOPPER);
            }

            return ActionResult.CONSUME;
        }
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new IronHopperBlockEntity();
    }
}
