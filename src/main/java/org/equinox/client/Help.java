package org.equinox.client;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Text;
import org.equinox.EquinoxClient;

public class Help {
    public static void help(){
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("equinox")
                .executes(context -> {
                    context.getSource().sendFeedback(Text.literal(EquinoxClient.PREFIX + "Commands: Flight, FlySpeed, Speed, ElytraFly, Pitch, Yaw, ServerBrand"));
                    return 1;
                })
        )));

        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("client")
                .executes(context -> {
                    context.getSource().sendFeedback(Text.literal(EquinoxClient.PREFIX + "Commands: Flight, FlySpeed, Speed, ElytraFly, Pitch, Yaw, ServerBrand"));
                    return 1;
                })
        )));

    }

}
