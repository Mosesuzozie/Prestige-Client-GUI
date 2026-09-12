# Prestige Client GUI

A premium, customizable Minecraft Fabric client GUI designed for Minecraft 1.21.11.

Prestige Client GUI provides a clean category-based interface for managing client modules, configurations, themes, and keybinds.

✨ Features

- 🎨 Premium ClickGUI interface
- 📂 Organized module categories
- 🔍 Module search
- ⌨️ Customizable module keybinds
- ⚔️ Combat modules
- 🔨 Mace modules
- 🏃 Movement modules
- 👁️ Visual modules
- 🛠️ Miscellaneous modules
- 🗡️ Spear modules
- 🎨 Custom themes and colors
- 💾 Configuration/preferences support
- ⚡ Lightweight client-side design

📂 Categories

- ⚔️ Combat
- 🔨 Mace
- 🛠️ Misc
- 🏃 Movement
- 🗡️ Spear
- 👁️ Visual

🧩 Modules

Prestige Client GUI currently includes modules such as:

- Aim Assist
- Triggerbot
- Auto Hit Crystal
- Anchor Macro
- NethPot Assist
- UHC Assist
- Auto Pearl Catch
- Auto Mace
- Auto Jump Reset
- Safe Walk
- Auto Spear Swap
- Jump Circles
- Name Tags
- No Bounce
- Ore Simulation
- Pearl Trajectory
- Render Optimizer

🎮 Controls

Right Shift — Open Prestige ClickGUI

Module keybinds can be customized through the GUI.

📦 Dependencies

Required to Run

Dependency| Version
Minecraft| "1.21.11"
Fabric Loader| "0.18.4+"
Fabric API| "0.141.1+1.21.11"

Required to Build

Dependency| Version
Fabric Loom| "1.17.13"
Yarn Mappings| "1.21.11+build.6"

«Note: Fabric Loom and Yarn mappings are build-time dependencies. They are not required for players to run the finished mod ".jar".»

📁 Project Structure

Prestige-Client-GUI/
├── .github/
│   └── workflows/
│       └── build.yml
│
├── src/
│   └── main/
│       ├── java/
│       │   └── vip/
│       │       └── prestigeclient/
│       │           ├── PrestigeClient.java
│       │           ├── gui/
│       │           │   ├── PrestigeClickGUI.java
│       │           │   └── PrestigeRenderer.java
│       │           ├── modules/
│       │           │   └── PrestigeModulesRegistry.java
│       │           ├── config/
│       │           │   └── PrestigeConfigAndTheme.java
│       │           └── mixin/
│       │               └── MixinKeyboardInput.java
│       │
│       └── resources/
│           ├── fabric.mod.json
│           └── prestigeclient.mixins.json
│
├── build.gradle
├── gradle.properties
├── settings.gradle
├── gradlew
├── gradlew.bat
├── gradle/
│   └── wrapper/
│
└── README.md

🚀 GitHub Actions

The project includes a GitHub Actions workflow that automatically builds the client.

Every time the project is pushed, the workflow can compile the project using Gradle.

🎨 Customization

Prestige Client GUI supports:

- Custom colors
- Themes
- Module configuration
- Keybind configuration
- Searchable modules
- Category organization

📌 Project Status

In Development 🚧

Prestige Client GUI is actively being developed. Features, modules, and the GUI are subject to change.

📜 License

This project is licensed under the MIT License.

---

👑 Prestige Client GUI

Built for Minecraft 1.21.11

Fast. Clean. Customizable.
