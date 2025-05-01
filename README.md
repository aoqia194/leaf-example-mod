# Leaf Example Mod

This is an example mod for leaf to help you get setup with (hopefully) little struggle.

Leaf is a fork of FabricMC's toolchain (mostly known as 'fabric' used in Minecraft modding).
Since the internal structure is the same, most if not all of their guides you can actually use.
This will exclude anything from Fabric API since leaf currently doesn't have one or need one.
Guides that revolve around Minecraft-specific java code and systems will not work for Zomboid.

---

## Setup

- Clone the repository or use the template feature provided by GitHub.
- Open the project in your IDE and let Gradle initialise itself.
  Due to the nature of Loom and the game's size, this may take a few minutes.
  If you are on a bad SSD, this may be 5-10 minutes, or on a HDD this could be 25 minutes.
  This process will take up **at least double** of the game's original size on your storage medium
  and will be multiplied by how many versions of the game you have cached.
  You can clear this cache manually by running the `clearLoomCache` Gradle task.
- Generate decompiled game sources by running the `genSourcesWith{decompiler}` Gradle task.
  Vineflower is recommended, though any can be used or the default `genSources` task.
- Make sure the project is set to use the JDK version that matches the JDK used by the game.
  For builds `41.78.16` to `42.7` (others not tested), this is Java 17.
  Eclipse Adoptium or OpenJDK/similar are all recommended for this purpose.
- Mod away! Some small examples are included in the example.

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
