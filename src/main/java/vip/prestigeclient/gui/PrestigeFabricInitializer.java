package vip.prestigeclient.gui;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class PrestigeFabricInitializer implements ClientModInitializer {

    private static KeyBinding openGuiBind;
    private static PrestigeClickGUI guiInstance;

    @Override
    public void onInitializeClient() {

        openGuiBind = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.prestigeclient.opengui",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_RIGHT_SHIFT,
                        KeyBinding.Category.MISC
                )
        );

        guiInstance = new PrestigeClickGUI();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiBind.wasPressed()) {
                System.out.println(
                        "Prestige ClickGUI Toggle Action Triggered!"
                );
            }
        });
    }
}
