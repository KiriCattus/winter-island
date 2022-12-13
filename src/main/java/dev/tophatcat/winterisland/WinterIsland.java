/*
 * Winter Island - https://github.com/tophatcats-mods/winter-island
 * Copyright (C) 2016-2022 <KiriCattus>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation;
 * Specifically version 2.1 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 * USA
 * https://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 */
package dev.tophatcat.winterisland;

import dev.tophatcat.winterisland.blocks.WinterPortalBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class WinterIsland implements ModInitializer {

    public static final String MOD_ID = "winterisland";
    public static final RegistryKey<World> WINTER_ISLAND = RegistryKey.of(Registry.WORLD_KEY,
        new Identifier(WinterIsland.MOD_ID, "winterisland"));
    public static final Block WINTER_PORTAL_BLOCK = new WinterPortalBlock();
    public static final Block WINTER_PORTAL_FRAME_BLOCK = new Block(AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK)
        .strength(-1.0F, 3600000.0F).dropsNothing());


    public WinterIsland() {
        Registry.register(Registry.BLOCK, new Identifier(WinterIsland.MOD_ID,
            "winter_portal_block"), WINTER_PORTAL_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(WinterIsland.MOD_ID,
            "winter_portal_frame_block"), WINTER_PORTAL_FRAME_BLOCK);
    }

    @Override
    public void onInitialize(ModContainer mod) {
    }
}
