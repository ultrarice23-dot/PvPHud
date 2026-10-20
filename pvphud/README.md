# PvP HUD (Fabric, client-side) — built for Minecraft 1.21.1 (Yarn mappings)

Build:  `gradle wrapper --gradle-version 8.10.2` (once), then `./gradlew build`  -> `build/libs/pvphud-1.0.0.jar`
Needs JDK 21 and Fabric API. Drop the jar in `.minecraft/mods`.

Keys (rebindable in Controls > PvP HUD)
  \  settings + HUD editor      [  start / reset (locks crosshair target if any)
  ]  pause + hide (stats kept)  '  resume paused stats without reset

Config file: .minecraft/config/pvphud.json

Custom fonts: set Font = Custom and give a font id (default pvphud:custom). Ship a resource pack
with assets/<ns>/font/<name>.json (any vanilla font provider format) to use any TTF.

Porting to 1.21.11 touches only: PvpHudClient (KeyBinding.Category + HudElementRegistry instead of
HudRenderCallback), HudRenderer (Matrix3x2fStack push/pop, drawGuiTexture with RenderPipelines),
SettingsScreen (Click/KeyInput event records), CombatTracker (enchantment/registry lookups).
