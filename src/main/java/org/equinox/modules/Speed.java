package org.equinox.modules;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.equinox.EquinoxClient;

public class Speed {
    public static Boolean isEnable = false;

    public static void module(){
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("speed")
                .executes(context -> {
                    ClientPlayerEntity player = context.getSource().getPlayer();
                    isEnable = !isEnable;

                    if (isEnable == true){
                        player.getAbilities().setWalkSpeed(10);
                    } else {
                        player.getAbilities().setWalkSpeed(5);
                    }

                    context.getSource().sendFeedback(Text.literal(EquinoxClient.PREFIX + "Speed: " + player.getMovementSpeed()));
                    return 1;
                })
        )));
    }

}
