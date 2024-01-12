package org.equinox.modules;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.equinox.EquinoxClient;

public class Flight {
    private static boolean isFlying = false;
    private static int delay = 0;

    public static void module(){
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("flight")
                .executes(context -> {
                    ClientPlayerEntity player = context.getSource().getPlayer();

                    player.getAbilities().allowFlying = !player.getAbilities().allowFlying;
                    player.getAbilities().flying = !player.getAbilities().flying;

                    context.getSource().sendFeedback(Text.literal(EquinoxClient.PREFIX + "Flight: " + player.getAbilities().allowFlying));

                    isFlying = !isFlying;
                    ClientTickEvents.END_CLIENT_TICK.register(Flight::onClientTick);

                    return 1;
                })
        )));
    }

    private static void onClientTick(MinecraftClient client){
        if (client.player == null){
            return;
        }

        if (!isFlying)
            delay = 0;

        if (delay < 5)
            delay++;

        boolean noVanillaKick = isFlying && delay < 0;

        if (delay >= 5) {
            delay = -25;
        }

        if (noVanillaKick) {

            client.player.getAbilities().allowFlying = true;
            client.player.getAbilities().flying = true;
        } else {
            client.player.getAbilities().allowFlying = false;
            client.player.getAbilities().flying = false;
        }

    }

}
