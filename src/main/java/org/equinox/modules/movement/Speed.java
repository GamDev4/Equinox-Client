package org.equinox.modules.movement;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.equinox.EquinoxClient;
import org.equinox.keybind.Utils;
import org.lwjgl.glfw.GLFW;

public class Speed {
    public static Boolean isEnable = false;
    public static KeyBinding speedKey;

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

                    context.getSource().sendFeedback(Text.literal(EquinoxClient.CLIENT_PREFIX + "Speed: " + isEnable));
                    return 1;
                })
        )));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (speedKey.wasPressed()) {
                ClientPlayerEntity player = client.player;
                isEnable = !isEnable;

                if (isEnable == true){
                    player.getAbilities().setWalkSpeed(10);
                } else {
                    player.getAbilities().setWalkSpeed(5);
                }

                player.sendMessage(Text.literal(EquinoxClient.CLIENT_PREFIX + "Speed: " + isEnable));
            }
        });
    }

    public static void register() {
        speedKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                Utils.KEY_SPEED,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_DELETE,
                Utils.KEY_CATEGORY_MODULES_MOVEMENT
        ));

        module();
    }

}
