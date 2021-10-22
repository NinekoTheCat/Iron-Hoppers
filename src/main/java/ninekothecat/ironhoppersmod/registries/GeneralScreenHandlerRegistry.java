package ninekothecat.ironhoppersmod.registries;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import ninekothecat.ironhoppersmod.blocks.ironhopper.IronHopper;
import ninekothecat.ironhoppersmod.blocks.ironhopper.IronHopperScreenHandler;

public class GeneralScreenHandlerRegistry {
    public static void register() {
        IronHopperScreenHandler.ironHopperScreenHandlerScreenHandlerType = ScreenHandlerRegistry.registerSimple(IronHopper.identifier, IronHopperScreenHandler::new);
    }

}
