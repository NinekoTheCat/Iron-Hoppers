package ninekothecat.ironhoppersmod.client;

import net.fabricmc.api.ClientModInitializer;
import ninekothecat.ironhoppersmod.registries.ScreenHandlerRegistry;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class IronHoppersModClient implements ClientModInitializer {

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        ScreenHandlerRegistry.register();
    }
}
