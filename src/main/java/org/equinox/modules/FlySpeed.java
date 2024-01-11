package org.equinox.modules;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.equinox.EquinoxClient;

public class FlySpeed {
    public static void module(){
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("flyspeed")
                .executes(context -> {
                    ClientPlayerEntity player = context.getSource().getPlayer();

                    player.getAbilities().setFlySpeed(2);

                    context.getSource().sendFeedback(Text.literal(EquinoxClient.PREFIX + "FlySpeed: " + player.getAbilities().getFlySpeed()));
                    return 1;
                })
        )));
    }

}
