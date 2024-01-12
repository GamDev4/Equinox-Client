package org.equinox.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;

public class Client implements ClientModInitializer {
    public static MinecraftClient client = MinecraftClient.getInstance();
    @Override
    public void onInitializeClient() {
        /*
            TextRenderer textRenderer = client.textRenderer;
            int x = 0;
            int y = 0;

            String text = "Equinox Client";
            textRenderer.draw(text, x, y, 0x00FFFF);
         */
    }

}
