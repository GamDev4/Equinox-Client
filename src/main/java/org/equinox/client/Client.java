package org.equinox.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

import org.equinox.modules.movement.*;
import org.equinox.modules.player.*;

public class Client implements ClientModInitializer {
    public static MinecraftClient client = MinecraftClient.getInstance();
    @Override
    public void onInitializeClient() {
        Flight.register();
        ElytraFly.register();
        FlySpeed.register();
        Speed.register();
        Pitch.register();
        Yaw.register();
    }

}
