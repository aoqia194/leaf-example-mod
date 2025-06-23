# Leaf Example Mod

This is an example mod for leaf to help you get setup with (hopefully) little struggle.

Leaf is a fork of FabricMC's toolchain (mostly known as 'fabric' used in Minecraft modding).
Since the internal structure is the same, most if not all of their guides you can actually use.
This will exclude anything from Fabric API since leaf currently doesn't have one or need one.
Guides that revolve around Minecraft-specific java code and systems will not work for Zomboid.

## Setup

1. Clone the repository or use the template feature provided by GitHub.

2. Open the project in your IDE and let Gradle initialise itself.
   Due to the nature of Loom and the game's size, this may take a few minutes.
   If you have your game installed to a different location than Steam's default path,
   make sure you set the path in your `build.gradle` file like so:

   ```gradle
   loom {
       loom_client_install_path = 'D:\\SteamLibrary\\steamapps\\common\\ProjectZomboid'
   }
   ```

   If you are on a bad SSD, this may be 5-10 minutes, or on a HDD this could be 25+ minutes.
   This process will take up **at least double** of the game's original size on your storage medium
   (1 copy of the original game installed in Steam and 1 copy in the loom cache)
   and will be multiplied by how many versions of the game you have cached.
   You can clear this cache manually by running the `clearLoomCache` Gradle task or removing the
   folder at `{userFolder}/.gradle/caches/leaf-loom/`.

3. Generate decompiled game sources by running the `genSources` Gradle task.
   Vineflower is the default, though any can be used with the `genSourcesWith{decompiler}` task.

4. Make sure the project is set to use the JDK version that matches the JDK used by the game.
   For builds `41.78.16` and above, this is Java 17 (others untested).
   [Eclipse Adoptium](https://adoptium.net/) or OpenJDK/similar are all recommended for this purpose.

5. Mod away! Some small examples are included in the mod.

## Publishing to the Steam Workshop

Publishing your leaf mod to the workshop is similar to publishing any other Project Zomboid mod.
Simply put your built mod JAR into the `YourModId/Contents/mods/YourModId/leaf/mods/` folder
(create it if needed)!

## Issues

If you are having issues with leaf, feel free to notify me in Discord (@aoqia) or ask in the
`PZ Modding Community` mod-projects post dedicated to leaf.

## Useful Resources

Since we can use most of the resources fabric provides (assuming we are using ones that are
game-agnostic), here are some that you can follow!
Keep in mind that not everything you can do 1:1 purely because Project Zomboid is not Minecraft.

- [FabricMC Wiki - Mixin Introduction](https://wiki.fabricmc.net/tutorial:mixin_introduction)
- [FabricMC Docs - New and official documentation](https://docs.fabricmc.net/)
- [MixinExtras Wiki - Using MixinExtras](https://github.com/LlamaLad7/MixinExtras/wiki)

---

## License

This template is available under the CC0 license.
Feel free to learn from it and incorporate it in your own projects.
