# Aki Elytra Trails Reforged - license provenance

Reviewed October 11, 2026. Original project: [Elytra Contrails](https://github.com/dbrighthd/elytratrails) by **dbrighthd**.
NeoForge port and maintenance: **Achilleus (Achilleus-1)**.

## Ownership and redistribution

The mod code is **MPL-2.0**. Preserve the full license, copyright notices,
and applicable third-party exceptions in source and binary distributions.
Renaming or porting the mod does not transfer ownership of upstream work.
Achilleus's copyright notice covers the port's own contributions and original artwork. This branded project is maintained by Achilleus; upstream authors retain ownership of their original code and assets.

## Upstream evidence and asset scope

The upstream [1.21.11 revision](https://github.com/dbrighthd/elytratrails/tree/54e27d9189b32b974909a06fb6d1cee18fabdd38) and [1.21.1 revision](https://github.com/dbrighthd/elytratrails/tree/bbb59af6b13abdb5e492c2337044b4e888e50d62) contain the same MPL-2.0 license as this repository. These are audit reference revisions, not a claim that every port change came from those exact commits. Twenty-five bundled trail textures match the 1.21.11 revision byte-for-byte.

The port and modifications to covered files remain MPL-2.0. When distributing a compiled JAR, make the corresponding preferred source form, including modifications and build files, reasonably available under MPL-2.0 and tell recipients where to obtain it. Publish a matching source tag and retain it; an unrelated upstream link or inaccessible/private repository is insufficient. Do not impose restrictions that remove recipients' MPL rights. See [MPL sections 3.1-3.4](https://www.mozilla.org/en-US/MPL/2.0/).

`EasingUtil.java` retains dbrighthd's attribution to the [Desmos easing graph](https://www.desmos.com/calculator/m8myals511). The graph is referenced for equations; no Desmos software or graph asset is bundled.

Cloth Config is an optional, separately installed library, declared `compileOnly`. Its implementation is not bundled. Compatibility hooks do not convey ownership of Iris, EMF, or other mods.

## Branding and dependencies

Original cover artwork and exports are by Achilleus. Existing artwork is unchanged.

Optional mods and Minecraft itself are obtained separately. References to their
APIs or runtime assets do not license those projects for copying or redistribution.
The Gradle wrapper has its own license in `gradle-wrapper.jar!/META-INF/LICENSE`,
and the wrapper scripts retain their Apache-2.0 headers.

## Source access

Corresponding source for this version (the matching tag is published with the release): [https://github.com/Achilleus-1/Akis-ElytraTrails-Reforged/tree/v1.4.9-neoforge.6](https://github.com/Achilleus-1/Akis-ElytraTrails-Reforged/tree/v1.4.9-neoforge.6).
For future releases, publish and retain the exact matching source revision and
update release links. A successful build does not certify provenance of new files.

Unofficial Minecraft mod; not approved by or affiliated with Mojang or Microsoft.
