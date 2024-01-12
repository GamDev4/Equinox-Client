package org.equinox.modules;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.equinox.EquinoxClient;

public class FlySpeed {
    public static Boolean isEnable = false;

    public static void module(){
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("flyspeed")
                .executes(context -> {
                    ClientPlayerEntity player = context.getSource().getPlayer();
                    isEnable = !isEnable;

                    if (isEnable == true){
                        player.getAbilities().setFlySpeed(2);
                    } else {
                        player.getAbilities().setFlySpeed(1);
                    }

                    context.getSource().sendFeedback(Text.literal(EquinoxClient.PREFIX + "FlySpeed: " + player.getAbilities().getFlySpeed()));
                    return 1;
                })
        )));
    }

}
