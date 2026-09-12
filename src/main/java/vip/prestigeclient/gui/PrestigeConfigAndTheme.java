package vip.prestigeclient.gui;

import java.io.*;
import java.nio.charset.StandardCharsets;

class ColorTheme {
    public static int redAccent = 110;
    public static int greenAccent = 110;
    public static int blueAccent = 240;
    public static int getClientColorHex() {
        return (255 << 24) | ((redAccent & 0xFF) << 16) | ((greenAccent & 0xFF) << 8) | (blueAccent & 0xFF);
    }
}

class ConfigManager {
    private static final File storageFile = new File("config/PrestigeClient/preferences.cfg");
    private static PrestigeClickGUI contextGUI;

    public static void setContext(PrestigeClickGUI gui) { contextGUI = gui; }

    public static void savePreferences() {
        if (contextGUI == null) return;
        try {
            File folder = storageFile.getParentFile();
            if (!folder.exists()) folder.mkdirs();
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(storageFile), StandardCharsets.UTF_8))) {
                writer.write("THEME_COLOR:" + ColorTheme.redAccent + ":" + ColorTheme.greenAccent + ":" + ColorTheme.blueAccent);
                writer.newLine();
                for (Module mod : contextGUI.getModules()) {
                    writer.write("MODULE:" + mod.getName() + ":" + mod.isEnabled() + ":" + mod.getKeybind());
                    writer.newLine();
                    for (Setting<?> s : mod.getSettings()) {
                        writer.write("SETTING:" + mod.getName() + ":" + s.getName() + ":" + s.getValue());
                        writer.newLine();
                    }
                }
            }
        } catch (IOException ignored) {}
    }
      public static void loadPreferences() {
        if (contextGUI == null || !storageFile.exists()) return;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(storageFile), StandardCharsets.UTF_8))) {
            String row;
            while ((row = reader.readLine()) != null) {
                String[] segs = row.split(":"); if (segs.length < 2) continue;
                if (segs[0].equals("THEME_COLOR") && segs.length >= 4) {
                    ColorTheme.redAccent = Integer.parseInt(segs[1]); ColorTheme.greenAccent = Integer.parseInt(segs[2]); ColorTheme.blueAccent = Integer.parseInt(segs[3]);
                } else if (segs[0].equals("MODULE") && segs.length >= 3) {
                    for (Module m : contextGUI.getModules()) {
                        if (m.getName().equals(segs[1])) {
                            m.setEnabled(Boolean.parseBoolean(segs[2]));
                            if (segs.length >= 4) m.setKeybind(Integer.parseInt(segs[3]));
                        }
                    }
                } else if (segs[0].equals("SETTING") && segs.length >= 4) {
                    for (Module m : contextGUI.getModules()) {
                        if (m.getName().equals(segs[1])) {
                            for (Setting<?> s : m.getSettings()) {
                                if (s.getName().equals(segs[2])) applyLoadedValue(s, segs[3]);
                            }
                        }
                    }
                }
            }
        } catch (IOException ignored) {}
    }

    @SuppressWarnings("unchecked")
    private static void applyLoadedValue(Setting<?> s, String raw) {
        if (s.getType() == SettingType.BOOLEAN) ((Setting<Boolean>) s).setValue(Boolean.parseBoolean(raw));
        else if (s.getType() == SettingType.SLIDER) ((Setting<Double>) s).setValue(Double.parseDouble(raw));
        else if (s.getType() == SettingType.MODE) ((Setting<String>) s).setValue(raw);
    }
}
