package org.equinox.modules.player;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.equinox.EquinoxClient;
import org.equinox.keybind.Utils;
import org.lwjgl.glfw.GLFW;

public class Yaw {
    private static boolean isEnable = false;
    private static int delay = 0;
    public static KeyBinding yawKey;

    public static void module(){
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("yaw")
                .executes(context -> {
                    isEnable = !isEnable;

                    context.getSource().sendFeedback(Text.literal(EquinoxClient.CLIENT_PREFIX + "Yaw: " + isEnable));
                    ClientTickEvents.END_CLIENT_TICK.register(Yaw::onClientTick);
                    return 1;
                })
        )));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (yawKey.wasPressed()) {
                ClientPlayerEntity player = client.player;
                isEnable = !isEnable;

                player.sendMessage(Text.literal(EquinoxClient.CLIENT_PREFIX + "Yaw: " + isEnable));
                ClientTickEvents.END_CLIENT_TICK.register(Yaw::onClientTick);
            }
        });
    }

    public static void register() {
        yawKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                Utils.KEY_YAW,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_DELETE,
                Utils.KEY_CATEGORY_MODULES_PLAYER
        ));

        module();
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
