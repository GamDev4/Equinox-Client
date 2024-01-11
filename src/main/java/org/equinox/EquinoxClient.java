package org.equinox;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.equinox.modules.Flight;
import org.equinox.modules.FlySpeed;
import org.equinox.modules.Speed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EquinoxClient implements ModInitializer {
    public static final String MOD_ID = "equinox_client";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final String PREFIX = "§3[Equinox] §f";

    @Override
    public void onInitialize() {
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("equinox")
                .executes(context -> {
                    context.getSource().sendFeedback(Text.literal(EquinoxClient.PREFIX + "Commands: Flight, FlySpeed, Speed"));
                    return 1;
                })
        )));

        Flight.module();
        Speed.module();
        FlySpeed.module();
    }

}
