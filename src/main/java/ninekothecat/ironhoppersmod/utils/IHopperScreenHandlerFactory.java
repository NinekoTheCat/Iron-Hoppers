package ninekothecat.ironhoppersmod.utils;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public interface IHopperScreenHandlerFactory {
    ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory);
    default Text getHopperName() {
        return new TranslatableText("container.hopper");
    }
}
