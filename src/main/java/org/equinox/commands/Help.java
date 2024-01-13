package org.equinox.commands;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.server.command.MessageCommand;
import net.minecraft.text.Text;
import org.equinox.EquinoxClient;

public class Help {
    public static void command() {
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("equinox")
                .executes(context -> {
                    context.getSource().sendFeedback(Text.literal(EquinoxClient.CLIENT_PREFIX + "Commands: Flight, FlySpeed, Speed, ElytraFly, Pitch, Yaw, ServerBrand"));
                    return 1;
                })
        )));

        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("client")
                .executes(context -> {
                    context.getSource().sendFeedback(Text.literal(EquinoxClient.CLIENT_PREFIX + "Commands: Flight, FlySpeed, Speed, ElytraFly, Pitch, Yaw, ServerBrand"));
                    return 1;
                })
        )));
    }

}
