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

public class FlySpeed {
    public static Boolean isEnable = false;
    public static KeyBinding flyspeedKey;

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

                    context.getSource().sendFeedback(Text.literal(EquinoxClient.CLIENT_PREFIX + "FlySpeed: " + isEnable));
                    return 1;
                })
        )));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (flyspeedKey.wasPressed()) {
                ClientPlayerEntity player = client.player;
                isEnable = !isEnable;

                if (isEnable == true){
                    player.getAbilities().setFlySpeed(2);
                } else {
                    player.getAbilities().setFlySpeed(1);
                }

                player.sendMessage(Text.literal(EquinoxClient.CLIENT_PREFIX + "FlySpeed: " + isEnable));
            }
        });
    }

    public static void register() {
        flyspeedKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                Utils.KEY_FLYSPEED,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_DELETE,
                Utils.KEY_CATEGORY_MODULES_MOVEMENT
        ));

        module();
    }

}
