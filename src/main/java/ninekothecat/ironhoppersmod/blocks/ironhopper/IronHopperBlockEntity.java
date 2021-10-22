package ninekothecat.ironhoppersmod.blocks.ironhopper;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import ninekothecat.ironhoppersmod.registries.BlockEntityRegistry;
import ninekothecat.ironhoppersmod.utils.GenericHopper;
import ninekothecat.ironhoppersmod.utils.IHopperScreenHandlerFactory;

public class IronHopperBlockEntity extends GenericHopper implements IHopperScreenHandlerFactory {
    public static final Identifier identifier = IronHopper.identifier;

    public IronHopperBlockEntity() {
        super(BlockEntityRegistry.IronHopperEntityType,DefaultedList.ofSize(10, ItemStack.EMPTY));
        super.setScreenHandlerFactory(this);
    }

    @Override
    public Text getHopperName() {
        return new TranslatableText("container.ironhoppersmod.ironhopper");
    }

    @Override
    public ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new IronHopperScreenHandler(syncId,playerInventory,this);
    }

}
