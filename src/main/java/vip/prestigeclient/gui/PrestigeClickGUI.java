package vip.prestigeclient.gui;

import java.util.ArrayList;
import java.util.List;

enum Category {
    COMBAT("Combat", 39),
    MACE("Mace", 11),
    MISC("Misc", 20),
    MOVEMENT("Movement", 7),
    SPEAR("Spear", 4),
    VISUAL("Visual", 24);

    private final String name;
    private final int count;

    Category(String n, int c) {
        this.name = n;
        this.count = c;
    }

    public String getName() {
        return name;
    }

    public int getModuleCount() {
        return count;
    }
}

enum GeneralSection {
    SETTINGS("Settings"),
    THEME("Theme"),
    CONFIGS("Configs"),
    SOCIALS("Socials"),
    KEYBINDS("Keybinds");

    private final String name;

    GeneralSection(String n) {
        this.name = n;
    }

    public String getName() {
        return name;
    }
}

enum SettingType {
    BOOLEAN,
    SLIDER,
    MODE
}

class Setting<T> {
    private final String name;
    private final SettingType type;
    private final T defaultVal;
    private T value;

    private double min;
    private double max;
    private double inc;

    private String[] modes;
    private int modeIndex;

    public Setting(String n, T d) {
        this.name = n;
        this.type = SettingType.BOOLEAN;
        this.defaultVal = d;
        this.value = d;
    }

    public Setting(String n, T d, double mi, double mx, double in) {
        this.name = n;
        this.type = SettingType.SLIDER;
        this.defaultVal = d;
        this.value = d;
        this.min = mi;
        this.max = mx;
        this.inc = in;
    }

    @SuppressWarnings("unchecked")
    public Setting(String n, String[] m, int i) {
        this.name = n;
        this.type = SettingType.MODE;
        this.modes = m;
        this.modeIndex = i;
        this.defaultVal = (T) m[i];
        this.value = (T) m[i];
    }

    public String getName() {
        return name;
    }

    public SettingType getType() {
        return type;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T v) {
        this.value = v;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getInc() {
        return inc;
    }

    public String[] getModes() {
        return modes;
    }

    @SuppressWarnings("unchecked")
    public void cycleMode() {
        if (type == SettingType.MODE) {
            modeIndex = (modeIndex + 1) % modes.length;
            this.value = (T) modes[modeIndex];
        }
    }
}

public class PrestigeClickGUI {

    private final List<Module> modules = new ArrayList<>();

    private Category selectedCategory = Category.VISUAL;
    private GeneralSection selectedGeneralSection = null;

    private boolean isGeneralSectionActive = false;
    private String searchQuery = "";

    private final int windowWidth = 900;
    private final int windowHeight = 520;
    private final int sidebarWidth = 200;

    private Module moduleListeningForBind = null;
    private boolean isListeningForBind = false;

    public PrestigeClickGUI() {
        modules.add(new AimAssist());
        modules.add(new Triggerbot());
        modules.add(new AutoHitCrystal());
        modules.add(new AnchorMacro());
        modules.add(new NethPotAssist());

        modules.add(new UHCAssist());
        modules.add(new AutoPearlCatch());
        modules.add(new AutoMace());
        modules.add(new AutoJumpReset());
        modules.add(new SafeWalk());

        modules.add(new AutoSpearSwap());
        modules.add(new JumpCircles());
        modules.add(new NameTags());
        modules.add(new NoBounce());
        modules.add(new OreSimulation());

        modules.add(new PearlTrajectory());
        modules.add(new RenderOptimizer());

        ConfigManager.setContext(this);
        ConfigManager.loadPreferences();
    }

    public void renderScreen(int mouseX, int mouseY) {
        PrestigeRenderer.drawFilledRectangle(
            null,
            50,
            50,
            windowWidth,
            windowHeight,
            0xFF0B0B16
        );

        PrestigeRenderer.drawFilledRectangle(
            null,
            50,
            50,
            sidebarWidth,
            windowHeight,
            0xFF111122
        );

        PrestigeRenderer.drawTextString(
            null,
            "MODULES",
            65,
            95,
            0xFF4A4A5A
        );

        int tabY = 120;

        for (Category cat : Category.values()) {
            boolean active =
                (!isGeneralSectionActive && cat == selectedCategory);

            int clr = active
                ? ColorTheme.getClientColorHex()
                : 0xFF8A8A9D;

            PrestigeRenderer.drawFilledRectangle(
                null,
                60,
                tabY - 8,
                sidebarWidth - 20,
                30,
                active ? 0x206E6EF0 : 0x00000000
            );

            PrestigeRenderer.drawTextString(
                null,
                cat.getName(),
                75,
                tabY,
                clr
            );

            PrestigeRenderer.drawTextString(
                null,
                String.valueOf(cat.getModuleCount()),
                sidebarWidth - 35,
                tabY,
                0xFF4A4A5A
            );

            tabY += 40;
        }

        tabY += 20;

        PrestigeRenderer.drawTextString(
            null,
            "GENERAL",
            65,
            tabY - 15,
            0xFF4A4A5A
        );

        for (GeneralSection gen : GeneralSection.values()) {
            boolean active =
                (isGeneralSectionActive && gen == selectedGeneralSection);

            int clr = active
                ? ColorTheme.getClientColorHex()
                : 0xFF8A8A9D;

            PrestigeRenderer.drawFilledRectangle(
                null,
                60,
                tabY - 8,
                sidebarWidth - 20,
                30,
                active ? 0x206E6EF0 : 0x00000000
            );

            PrestigeRenderer.drawTextString(
                null,
                gen.getName(),
                75,
                tabY,
                clr
            );

            tabY += 35;
        }

        int panelX = 50 + sidebarWidth + 30;
        int cardW = windowWidth - sidebarWidth - 60;

        if (isGeneralSectionActive &&
            selectedGeneralSection != null) {

            renderGeneralPanel(
                selectedGeneralSection,
                panelX,
                cardW
            );

            return;
        }

        int modY = 140;

        for (Module mod : modules) {
            if (mod.getCategory() != selectedCategory) {
                continue;
            }

            PrestigeRenderer.drawFilledRectangle(
                null,
                panelX,
                modY,
                cardW,
                65,
                0xFF16162D
            );

            PrestigeRenderer.drawTextString(
                null,
                mod.getName(),
                panelX + 20,
                modY + 20,
                mod.isEnabled()
                    ? 0xFFFFFFFF
                    : 0xFF7A7A8C
            );

            String bndStr =
                (isListeningForBind &&
                 moduleListeningForBind == mod)
                    ? "[...]"
                    : "[" + translateKeyIntToString(mod.getKeybind()) + "]";

            PrestigeRenderer.drawTextString(
                null,
                bndStr,
                panelX + cardW - 145,
                modY + 22,
                0xFF9E9EAF
            );

            PrestigeRenderer.drawFilledRectangle(
                null,
                panelX + cardW - 50,
                modY + 20,
                30,
                16,
                mod.isEnabled()
                    ? ColorTheme.getClientColorHex()
                    : 0xFF313144
            );

            modY += 75;
        }
    }

    private void renderGeneralPanel(
        GeneralSection sec,
        int sX,
        int w
    ) {
        if (sec == GeneralSection.THEME) {
            PrestigeRenderer.drawFilledRectangle(
                null,
                sX,
                140,
                w,
                90,
                0xFF141428
            );

            PrestigeRenderer.drawTextString(
                null,
                "Red: " + ColorTheme.redAccent,
                sX + 20,
                178,
                0xFFFF4B4B
            );
        }
    }

    public static void handleKeyPressEvent(int key) {
        // Direct listener link triggered natively via input mixins pass framework
    }

    private String translateKeyIntToString(int k) {
        return k == 344 ? "RSHIFT" : "KEY-" + k;
    }

    public List<Module> getModules() {
        return modules;
    }
}
