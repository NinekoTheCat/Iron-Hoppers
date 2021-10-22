package ninekothecat.ironhoppersmod.blocks.ironhopper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import ninekothecat.ironhoppersmod.IronHoppersMod;
import ninekothecat.ironhoppersmod.utils.GenericHopper;

public class IronHopperScreenHandler extends ScreenHandler {
    public static final Identifier identifier = new Identifier(IronHoppersMod.MOD_ID, "iron_hopper_screen_handler");
    public static ScreenHandlerType<IronHopperScreenHandler> ironHopperScreenHandlerScreenHandlerType;
    private Inventory inventory;

    public IronHopperScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {

        super(ironHopperScreenHandlerScreenHandlerType, syncId);
        inventory.onOpen(playerInventory.player);
        this.inventory = inventory;
        //hopperInventory
        for (int b = 0; b < 2; b++) {
            for (int i = 0; i < inventory.size() / 2; i++) {
                this.addSlot(new Slot(inventory, i + (b * (inventory.size() / 2)), 44 + i * 18, (18 * b) + 16));
            }
        }

        //The player inventory
        int m;
        for (m = 0; m < 3; ++m) {
            int l;
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 74 - 9 + m * 18));
            }
        }
        //The player Hot bar
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 132 - 9));
        }

    }

    public IronHopperScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(10));
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {

            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }
}
