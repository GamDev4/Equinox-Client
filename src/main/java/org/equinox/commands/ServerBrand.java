package org.equinox.commands;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Text;
import org.equinox.EquinoxClient;

public class ServerBrand {
    public static void command(){
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("serverbrand")
                .executes(context -> {
                    context.getSource().sendFeedback(Text.literal(EquinoxClient.CLIENT_PREFIX + "ServerBrand: " + context.getSource().getPlayer().getServerBrand()));
                    return 1;
                })
        )));
    }

}
