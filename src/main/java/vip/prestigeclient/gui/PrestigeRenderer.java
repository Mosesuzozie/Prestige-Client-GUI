package vip.prestigeclient.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class PrestigeRenderer {

    public static void drawFilledRectangle(
            DrawContext context,
            int x,
            int y,
            int width,
            int height,
            int hexColor
    ) {
        if (context == null) {
            return;
        }

        context.fill(
                x,
                y,
                x + width,
                y + height,
                hexColor
        );
    }

    public static void drawTextString(
            DrawContext context,
            String text,
            int x,
            int y,
            int hexColor
    ) {
        if (context == null) {
            return;
        }

        MinecraftClient mc = MinecraftClient.getInstance();

        if (mc.textRenderer != null) {
            context.drawTextWithShadow(
                    mc.textRenderer,
                    text,
                    x,
                    y,
                    hexColor
            );
        }
    }
}
