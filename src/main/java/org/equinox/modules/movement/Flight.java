package org.equinox.modules.movement;

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
import org.lwjgl.glfw.GLFW;

import org.equinox.keybind.*;

public class Flight {
    private static boolean isFlying = false;
    private static int delay = 0;
    public static KeyBinding flightKey;

    public static void module(){
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("flight")
                .executes(context -> {
                    ClientPlayerEntity player = context.getSource().getPlayer();

                    player.getAbilities().allowFlying = !player.getAbilities().allowFlying;
                    player.getAbilities().flying = !player.getAbilities().flying;

                    context.getSource().sendFeedback(Text.literal(EquinoxClient.CLIENT_PREFIX + "Flight: " + player.getAbilities().allowFlying));

                    isFlying = !isFlying;
                    ClientTickEvents.END_CLIENT_TICK.register(Flight::onClientTick);

                    return 1;
                })
        )));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (flightKey.wasPressed()) {
                ClientPlayerEntity player = client.player;

                player.getAbilities().allowFlying = !player.getAbilities().allowFlying;
                player.getAbilities().flying = !player.getAbilities().flying;

                player.sendMessage(Text.literal(EquinoxClient.CLIENT_PREFIX + "Flight: " + player.getAbilities().allowFlying));

                isFlying = !isFlying;
                ClientTickEvents.END_CLIENT_TICK.register(Flight::onClientTick);
            }
        });
    }

    public static void register() {
        flightKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                Utils.KEY_FLIGHT,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_DELETE,
                Utils.KEY_CATEGORY_MODULES_MOVEMENT
        ));

        module();
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
