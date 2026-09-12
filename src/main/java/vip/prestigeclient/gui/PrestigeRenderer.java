package vip.prestigeclient.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;

public class PrestigeRenderer {
    public static void drawFilledRectangle(MatrixStack matrices, int x, int y, int width, int height, int hexColor) {
        float alpha = (float) (hexColor >> 24 & 255) / 255.0F;
        float red   = (float) (hexColor >> 16 & 255) / 255.0F;
        float green = (float) (hexColor >> 8 & 255) / 255.0F;
        float blue  = (float) (hexColor & 255) / 255.0F;

        Matrix4f positionMatrix = matrices.peek().getPositionMatrix();
        BufferBuilder builder = Tessellator.getInstance().getBuffer();
        
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
        builder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        
        builder.vertex(positionMatrix, (float) x, (float) (y + height), 0.0F).color(red, green, blue, alpha).next();
        builder.vertex(positionMatrix, (float) (x + width), (float) (y + height), 0.0F).color(red, green, blue, alpha).next();
        builder.vertex(positionMatrix, (float) (x + width), (float) y, 0.0F).color(red, green, blue, alpha).next();
        builder.vertex(positionMatrix, (float) x, (float) y, 0.0F).color(red, green, blue, alpha).next();
        
        BufferRenderer.drawWithGlobalProgram(builder.end());
        RenderSystem.disableBlend();
    }

    public static void drawTextString(MatrixStack matrices, String text, int x, int y, int hexColor) {
        net.minecraft.client.MinecraftClient mc = net.minecraft.client.MinecraftClient.getInstance();
        if (mc.textRenderer != null) {
            mc.textRenderer.draw(matrices, text, (float) x, (float) y, hexColor);
        }
    }
}
