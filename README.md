# Leaf Example Mod

This is an example mod for leaf to help you get setup with (hopefully) little struggle. Please read this README in its entirety to prevent you having to ask a multitude of questions that could've been answered here. If you have any small questions, try looking at the [FAQ](#faq)

Leaf is a fork of FabricMC's toolchain (mostly known as 'fabric' used in Minecraft modding). Since the internal structure is the same, most of their guides you can actually use. This will of course exclude anything from Fabric API since leaf currently doesn't have one or need one. Guides that revolve around Minecraft-specific java code and systems will not work for Zomboid.

It is **strongly recommended** that you are informed about Java and Mixin. For Java, this should be a beginner-intermediate level, and for Mixin you should have at least a basic understanding of how it works. Information on learning Java can be found widely spread across the internet, and Mixin has good information at [Home · SpongePowered/Mixin Wiki · GitHub](). The information on the official Mixin wiki is very dense, so you might want to also check out some [Mixin Examples [Fabric Wiki]](https://wiki.fabricmc.net/tutorial:mixin_examples) to see how it works in action!

## Development Setup

1. Clone the repository or use the template to create a standalone repository with the code from this one.

2. Open the project in your IDE and let Gradle initialise itself. Due to the nature of Loom and the game's size, this may take a few minutes.
   If you have your game installed to a different location than Steam's default path, or just want to use a separate installation, make sure you set the environment variable `LEAF_CLIENT_GAME_PATH` to the **absolute path** to your PZ game installation. Restarting your IDE is required after changing environment variables to ensure they update.  Depending on your OS, setting environment variables will be different, so you can google "How to set system environment variables on XYZ operating system".
   
   If you are on a bad SSD, this may take 5-10 minutes, or a HDD, this could be 25+ minutes.
   This process will take up **at least double** of the game's original size on your storage medium
   (1 copy of the original game installed in Steam and 1 copy in the loom cache)
   and will be multiplied by how many versions of the game you have cached.
   You can clear this cache manually for any reason by running the `clearLoomCache` Gradle task or removing the
   folder at `{userFolder}/.gradle/caches/leaf-loom/`.

3. Generate decompiled game sources by running the `genSources` Gradle task.
   Vineflower is the default and recommended decompiler, though any can be used with the `genSourcesWith{decompiler}` task.

4. Make sure the project is set to use the JDK version that matches the JDK used by the game. For builds `41.78.16` and above, this is Java 17.
   [Eclipse Adoptium](https://adoptium.net/) or OpenJDK/similar JDKs are recommended.

5. Ensure the game version is up to date, alongside the loom and loader versions. These can be changed in the `gradle/libs.versions.toml` file. To find the latest loom and loader versions, you can simply go to the respective repository and look at the latest release.

6. Run your mod via the newly created IDE run configurations, if these don't show up then an IDE restart may be required. If you are in an environment where these aren't available, running the Gradle tasks `runClient` and `runServer` will also run the game.

7. Mod away! Some small examples are included in the mod.

## Loading your mod in Production

If you want to load your mod locally in a production environment for testing (similarly to how a user would do it), make sure that [leaf-installer](https://github.com/aoqia194/leaf-installer) is used to install leaf to the game folder. Then, you can place your mod into the `cachedir/mods/YourModId/leaf/mods/` folder. Alternatively, you can load mods via the JVM launch option `-Dleaf.addMods=C:\absolute\path\to\mod.jar;C:\another\mod.jar` though this isn't recommended for normal use (various reasons).

## Publishing to the Steam Workshop

Publishing your leaf mod to the workshop is similar to publishing any other Project Zomboid mod.
Simply put your built mod JAR into the `YourModId/Contents/mods/YourModId/leaf/mods/` folder
(create it if needed)!

## Known Issues

The known documented issues are explained in the [FAQ](#faq).

## Issue Reporting

If you are having issues with leaf that aren't explained in the [FAQ](#faq), feel free to create a discussion topic on this repository.

## Useful Resources

Since we can use most of the resources fabric provides (assuming we are using ones that are
game-agnostic), here are some that you can follow!
Keep in mind that not everything you can do 1:1 purely because Project Zomboid is not Minecraft.

- [FabricMC Docs - New and official documentation](https://docs.fabricmc.net/)
- [Mixin Wiki - Home](https://github.com/spongepowered/mixin/wiki)
- [MixinExtras Wiki - Using MixinExtras](https://github.com/LlamaLad7/MixinExtras/wiki)
- [FabricMC Wiki - Mixin Introduction](https://wiki.fabricmc.net/tutorial:mixin_introduction)

---

# FAQ

#### Why does my mod not show up on the in-game mod list?

Leaf (and subsequent mods) is essentially wrapping around the original game code, meaning that it modifies the game's Java code at runtime. To the game, it doesn't even know that leaf mods exist, as they look like regular Java code.

#### Why does installing leaf to the server fail using leaf-installer?

Server specific environment isn't yet a full feature of leaf, though it is planned and will be implemented relatively soon.

#### The game crashed, how can I see what failed?

If the loader does not show a UI with any exception information, you should check the `leafloader.log` file that gets created in the game folder.

#### The game instantly crashes when I start it, and I am using custom launch options!

Make sure the launch options follow the format specified in the first few paragraphs of [Startup parameters](https://pzwiki.net/wiki/Startup_parameters). If these are correct, it may be a loader bug. Please create a issue/bug report on the [leaf-loader](https://github.com/aoqia194/leaf-loader) repository.

#### I am on Windows, and my user folder has a space in the name. Why does Gradle not work?

Gradle (and other applications) will NOT like a space in the user folder, and it can lead to many other things breaking. The workaround for this is to set the `GRADLE_USER_HOME` user environment variable to the absolute path of the `%USERPROFILE%/.gradle` folder.

---

## License

This template is available under the CC0 license.
Feel free to learn from it and incorporate it in your own projects.
