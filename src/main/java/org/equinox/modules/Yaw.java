package org.equinox.modules;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.equinox.EquinoxClient;

public class Yaw {
    private static boolean isEnable = false;
    private static int delay = 0;

    public static void module(){
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("yaw")
                .executes(context -> {
                    isEnable = !isEnable;

                    context.getSource().sendFeedback(Text.literal(EquinoxClient.PREFIX + "Yaw: " + isEnable));
                    ClientTickEvents.END_CLIENT_TICK.register(Yaw::onClientTick);
                    return 1;
                })
        )));
    }

    private static void onClientTick(MinecraftClient client){
        if (client.player == null){
            return;
        }

        if (!isEnable)
            delay = 0;

        if (delay < 5)
            delay++;

        boolean loop = isEnable && delay < 0;

        if (delay >= 5) {
            delay = -25;
        }

        if (loop) {
            client.player.setYaw(0);
        }

    }

}
