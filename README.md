# Leaf Example Mod

This is an example mod for leaf to help you get setup with (hopefully) little struggle. Please read this README in its
entirety to prevent you having to ask questions that could've been answered here. If you have any other questions, you
should try asking in the Project Zomboid Modding Community discord server!

Leaf is a fork of FabricMC's toolchain (mostly known as 'fabric' used in Minecraft modding). Since the internal
structure is the same, most of their guides you can actually use. This will of course exclude anything from Fabric API
since leaf currently doesn't have one or need one. Guides that revolve around Minecraft-specific java code and systems
will not work for Zomboid.

It is **strongly recommended** that you are informed about Java and Mixin. For Java, this should be at least a beginner
level, and for Mixin you should have at least a basic understanding of how it works. Information on learning Java can
be found widely across the internet, and Mixin has good information at [Mixin Wiki][MixinWiki]. The information on the
official Mixin wiki is very dense, so you might want to also check out
[Fabric Wiki - Mixin Examples](https://wiki.fabricmc.net/tutorial:mixin_examples) to see how it works in action!

## Development Setup

1. Clone the repository or use the template to create a standalone repository with the code from this one.
2. Open the project in your IDE (preferrably JetBrains IDEA) and let Gradle sync. If you have your game installed to a
   different location than Steam's default path, or just want to use a separate installation, make sure you set the
   environment variables `LEAF_CLIENT_GAME_PATH` and or `LEAF_SERVER_GAME_PATH` to the **absolute path** of your PZ
   game installation. Restarting your IDE is required after changing environment variables to ensure they update.
   Depending on your OS, setting environment variables will be different, so you can google
   "How to set system environment variables on XYZ operating system".
3. Generate decompiled game sources by running the `genSources` Gradle task. Vineflower is the default and recommended
   decompiler.
4. Make sure the project is set to a compatible JDK version to that used by the game. For build 42, this is Java 25.
   [Eclipse Adoptium](https://adoptium.net/) or OpenJDK/similar JDKs are recommended.
5. Ensure the game version is up to date, alongside the loom and loader versions. These can be changed in the
   `gradle/libs.versions.toml` file. To find the latest loom and loader versions, you can simply go to the respective
   repository and look at the latest tagged release.
6. Run your mod via the newly created Gradle run configurations. If these don't show, an IDE restart may be required.
   If you are in an environment where these aren't available, running the Gradle tasks `runClient` and `runServer`
   directly will also run the game in the same way.
7. Mod away! Some small examples are included in the mod.

## Loading your mod in production

If you want to load your mod locally in a production environment for testing, you can do a few things:
- Use a Gradle task to copy the mod JAR to a location of your choice
- Place your mod JAR into your mod folder in the cachedir
- Use the `leaf.addMods` JVM property, for example `-Dleaf.addMods=absolute/path/to/mod.jar;wow/another/mod.jar`

There are technically some other ways you can do it like traditional file links, but they wont be detailed here.

## Publishing to the Steam Workshop

Publishing your leaf mod to the workshop is similar to publishing any other Project Zomboid mod. Simply put your built
mod JAR into the `YourModId/Contents/mods/YourModId/<version>/media/java` folder, replacing `<version>` with the proper
game version you want your Java mod to load in. If you are not sure, you can use `common` as the loader will also
ensure your mod jar will not be loaded if the version in the LMJ (leaf mod json) is not compatible.

## Known Issues

Some known issues are explained in the [FAQ](#faq), while others may be lised as issues in the respective org projects.
You can view the main LeafPZ org project [here][LeafPZProject].

## Issue Reporting

If you are having issues with leaf that aren't explained in the [FAQ](#faq), feel free to create a discussion topic on
this repository.

## Useful Resources

Since we can use most of the resources fabric provides (assuming we are using ones that are game-agnostic), here are
some that you can follow! Keep in mind that not everything you can do 1:1 purely because Project Zomboid is not
Minecraft.

- [FabricMC Docs - New and official documentation](https://docs.fabricmc.net/)
- [Mixin Wiki - Home][MixinWiki]
- [MixinExtras Wiki - Using MixinExtras][MixinExtrasWiki]
- [FabricMC Wiki - Mixin Introduction][FabricWikiMixins]

# FAQ

#### Why does my mod not show up on the in-game mod list?

Leaf itself essentially wraps the original game code, meaning it can modify the game's Java code at runtime. To the
game, it doesn't even know that leaf mods exist - they look like regular Java code. Because of this, leaf mods are not
required to have any component to them that follows traditional mod structure defined by the game.

#### The game crashed, how can I see what failed?

If the loader does not show a UI with any exception information (it should!!), you should check the `leafloader.log`
file that is created in the cachedir (by default at `USER_FOLDER/Zomboid`). If you are in a production environment, and
you have installed the loader proxy, you may also check the `.leaf/proxy.log` file located where the game is installed.

#### The game instantly crashes when I start it, and I am using custom launch options!

Make sure the launch options follow the format specified in the first few paragraphs
of [Startup parameters][StartupParameters]. If these are correct, it may be a bug in the loader. Please create an issue
on the [leaf-loader][LeafLoader] repository.

#### I am on Windows and my user folder has a space in the name - why does Gradle not work?

Gradle does not like spaces in the user folder and it can lead to many other things breaking. A workaround for this is
to set the `GRADLE_USER_HOME` user environment variable to the absolute path of the `%USERPROFILE%/.gradle` folder.

# License

This template is available under the CC0 license.
Feel free to learn from it and incorporate it in your own projects.

[FabricWikiMixins]: https://wiki.fabricmc.net/tutorial:mixin_introduction
[LeafLoader]: https://github.com/aoqia194/leaf-loader
[LeafPZProject]: https://github.com/orgs/LeafPZ/projects/2
[MixinExtrasWiki]: https://github.com/LlamaLad7/MixinExtras/wiki
[MixinWiki]: https://github.com/SpongePowered/Mixin/wiki
[StartupParameters]: https://pzwiki.net/wiki/Startup_parameters
