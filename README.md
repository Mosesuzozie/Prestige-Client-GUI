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

📁 Categories

⚔️ Combat

Combat-related modules and utilities.

🔨 Mace

Mace PvP and mace-related modules.

🛠️ Misc

General-purpose client features.

🏃 Movement

Movement and mobility improvements.

🗡️ Spear

Spear-related utilities and features.

👁️ Visual

Visual and rendering-related modules.

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

More modules can be added as the project develops.

🎮 Controls

Right Shift — Open Prestige ClickGUI

Module keybinds can be customized through the GUI.

🛠️ Requirements

- Minecraft 1.21.11
- Fabric Loader 0.18.4+
- Fabric API
- Java 21+
- Fabric Loom 1.17.13

🔨 Building

Clone the repository and run:

./gradlew jar

The compiled ".jar" will be generated in:

build/libs/

📦 Project Structure

Prestige-Client-GUI/
├── .github/
│   └── workflows/
│       └── build.yml
├── src/
│   └── main/
│       ├── java/
│       │   └── vip/
│       │       └── prestigeclient/
│       │           ├── gui/
│       │           │   ├── PrestigeClickGUI.java
│       │           │   ├── PrestigeConfigAndTheme.java
│       │           │   ├── PrestigeFabricInitializer.java
│       │           │   ├── PrestigeModulesRegistry.java
│       │           │   └── PrestigeRenderer.java
│       │           └── mixin/
│       │               └── MixinKeyboardInput.java
│       └── resources/
│           ├── fabric.mod.json
│           └── prestigeclient.mixins.json
├── build.gradle
├── gradle.properties
├── settings.gradle
└── README.md

🚀 GitHub Actions

The project includes a GitHub Actions workflow that automatically builds the client.

Every time the project is pushed, the workflow can compile the project using Gradle.

🎨 Customization

Prestige Client GUI is designed to be customizable.

The project includes support for:

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
