package vip.prestigeclient.mixin;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vip.prestigeclient.gui.PrestigeClickGUI;

@Mixin(Keyboard.class)
public class MixinKeyboardInput {

    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    private void onKeyInputPass(long window, int key, int scancode, int action, int mods, CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (action == 1 && mc.player != null) {
            PrestigeClickGUI.handleKeyPressEvent(key);
        }
    }
}
