package ninekothecat.ironhoppersmod.registries;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import ninekothecat.ironhoppersmod.blocks.ironhopper.IronHopperScreen;
import ninekothecat.ironhoppersmod.blocks.ironhopper.IronHopperScreenHandler;

public class ScreenHandlerRegistry {
    public static void register() {
        ScreenRegistry.register(IronHopperScreenHandler.ironHopperScreenHandlerScreenHandlerType, IronHopperScreen::new);
    }
}
