package vip.prestigeclient.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class Module {
    private final String name; private final String description; private final Category category;
    private final List<Setting<?>> settings = new ArrayList<>(); private boolean enabled = false; private boolean dropdownOpen = false; private int keybind = 0;

    public Module(String n, String d, Category c) { this.name = n; this.description = d; this.category = c; }
    public void addSetting(Setting<?> s) { this.settings.add(s); } public List<Setting<?>> getSettings() { return settings; }
    public String getName() { return name; } public String getDescription() { return description; } public Category getCategory() { return category; }
    public boolean isEnabled() { return enabled; } public boolean isDropdownOpen() { return dropdownOpen; } public void toggleDropdown() { this.dropdownOpen = !this.dropdownOpen; }
    public int getKeybind() { return keybind; } public void setKeybind(int k) { this.keybind = k; ConfigManager.savePreferences(); }
    public void setEnabled(boolean e) { this.enabled = e; if (e) onEnable(); else onDisable(); ConfigManager.savePreferences(); }
    public void toggle() { setEnabled(!this.enabled); }
    protected abstract void onEnable(); protected abstract void onDisable(); public abstract void onUpdate();
}
class AimAssist extends Module {
    private final Random random = new Random();
    public AimAssist() {
        super("Aim Assist", "Smooth crosshair guidance curves targeting closest entities natively.", Category.COMBAT);
        addSetting(new Setting<>("Horizontal Speed", 4.2, 1.0, 10.0, 0.1)); addSetting(new Setting<>("Vertical Speed", 2.5, 1.0, 10.0, 0.1)); addSetting(new Setting<>("Humanized Target Reaction", true));
    }
    @Override protected void onEnable() {} @Override protected void onDisable() {}
    @Override public void onUpdate() {
        if (!isEnabled()) return;
        double horiz = (Double) getSettings().get(0).getValue();
        if ((Boolean) getSettings().get(2).getValue()) { horiz += random.nextGaussian() * 0.35; }
    }
}
class Triggerbot extends Module {
    private final Random random = new Random(); private long lastClick = 0; private long currentDelay = 0;
    public Triggerbot() {
        super("Triggerbot", "Attacks targets sitting natively beneath your center crosshair vectors.", Category.COMBAT);
        addSetting(new Setting<>("Min CPS", 12.0, 1.0, 20.0, 0.5)); addSetting(new Setting<>("Max CPS", 16.5, 1.0, 20.0, 0.5));
    }
    @Override protected void onEnable() {} @Override protected void onDisable() {}
    @Override public void onUpdate() {
        if (!isEnabled()) return; long now = System.currentTimeMillis();
        if (now - lastClick >= currentDelay) {
            lastClick = now; double min = (Double) getSettings().get(0).getValue(); double max = (Double) getSettings().get(1).getValue();
            long base = (long) (1000.0 / (min + (random.nextDouble() * (max - min))));
            this.currentDelay = Math.max(25, base + (long)(random.nextGaussian() * 14));
        }
    }
}
class AutoHitCrystal extends Module { public AutoHitCrystal() { super("Auto Hit Crystal", "Customizable crystal delays.", Category.COMBAT); } @Override protected void onEnable() {} @Override protected void onDisable() {} @Override public void onUpdate() {} }
class AnchorMacro extends Module { public AnchorMacro() { super("Anchor Macro", "Automates anchors.", Category.COMBAT); } @Override protected void onEnable() {} @Override protected void onDisable() {} @Override public void onUpdate() {} }
class NethPotAssist extends Module { public NethPotAssist() { super("NethPot Assist", "Potion patching loops.", Category.COMBAT); } @Override protected void onEnable() {} @Override protected void onDisable() {} @Override public void onUpdate() {} }
class UHCAssist extends Module { public UHCAssist() { super("UHC Assist", "Auto healing loops.", Category.MISC); } @Override protected void onEnable() {} @Override protected void onDisable() {} @Override public void onUpdate() {} }
class AutoPearlCatch extends Module { public AutoPearlCatch() { super("Auto Pearl Catch", "Throws items.", Category.MACE); } @Override protected void onEnable() {} @Override protected void onDisable() {} @Override public void onUpdate() {} }
class AutoMace extends Module { public AutoMace() { super("Auto Mace", "Mace smash timing.", Category.MACE); } @Override protected void onEnable() {} @Override protected void onDisable() {} @Override public void onUpdate() {} }
class AutoJumpReset extends Module { public AutoJumpReset() { super("Auto Jump Reset", "Knockback logic.", Category.MOVEMENT); } @Override protected void onEnable() {} @Override protected void onDisable() {} @Override public void onUpdate() {} }
class SafeWalk extends Module { public SafeWalk() { super("Safe Walk", "Ledge cliff blocking.", Category.MOVEMENT); } @Override protected void onEnable() {} @Override protected void onDisable() {} @Override public void onUpdate() {} }
class AutoSpearSwap extends Module { public AutoSpearSwap() { super("Auto Spear Swap", "Hotbar swapping frames.", Category.SPEAR); } @Override protected void onEnable() {} @Override protected void onDisable() {} @Override public void onUpdate() {} }
class JumpCircles extends Module { public JumpCircles() { super("Jump Circles", "Expanding rings.", Category.VISUAL); } @Override protected void onEnable() {} @Override protected void onDisable() {} @Override public void onUpdate() {} }
class NameTags extends Module { public NameTags() { super("Name Tags", "Renders custom indicators.", Category.VISUAL); } @Override protected void onEnable() {} @Override protected void onDisable() {} @Override public void onUpdate() {} }
class NoBounce extends Module { public NoBounce() { super("No Bounce", "Removes animations.", Category.VISUAL); } @Override protected void onEnable() {} @Override protected void onDisable() {} @Override public void onUpdate() {} }
class OreSimulation extends Module { public OreSimulation() { super("Ore Simulation", "Precomputes Block seeds.", Category.VISUAL); } @Override protected void onEnable() {} @Override protected void onDisable() {} @Override public void onUpdate() {} }
class PearlTrajectory extends Module { public PearlTrajectory() { super("Pearl Trajectory", "Flight paths markers.", Category.VISUAL); } @Override protected void onEnable() {} @Override protected void onDisable() {} @Override public void onUpdate() {} }
class RenderOptimizer extends Module { public RenderOptimizer() { super("Render Optimizer", "Removes particle items.", Category.VISUAL); } @Override protected void onEnable() {} @Override protected void onDisable() {} @Override public void onUpdate() {} }
