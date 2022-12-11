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

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(WinterIsland.MOD_ID)
public class WinterIsland {

    public static final String MOD_ID = "winterisland";

    private static final DeferredRegister<Block> BLOCKS
        = DeferredRegister.create(ForgeRegistries.BLOCKS, WinterIsland.MOD_ID);

    public static final RegistryObject<Block> WINTER_PORTAL_BLOCK
        = BLOCKS.register("winter_portal_block", WinterPortalBlock::new);

    public static final ResourceKey<Level> WINTER_ISLAND = ResourceKey.create(Registry.DIMENSION_REGISTRY,
        new ResourceLocation(WinterIsland.MOD_ID, "winterisland"));

    public WinterIsland() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
