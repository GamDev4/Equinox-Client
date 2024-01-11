package org.equinox.modules;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.equinox.EquinoxClient;

public class Speed {
    public static void module(){
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("speed")
                .executes(context -> {
                    ClientPlayerEntity player = context.getSource().getPlayer();

                    player.getAbilities().setWalkSpeed(2);

                    context.getSource().sendFeedback(Text.literal(EquinoxClient.PREFIX + "Speed: " + player.getAbilities().getWalkSpeed()));
                    return 1;
                })
        )));
    }

}
