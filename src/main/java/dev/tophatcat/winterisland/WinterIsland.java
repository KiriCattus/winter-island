/*
 * MIT License
 *
 * Copyright (C) 2016-2022 <KiriCattus>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.tophatcat.winterisland;

import dev.tophatcat.winterisland.blocks.WinterPortalBlock;
import dev.tophatcat.winterisland.blocks.WinterPortalBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;

public class WinterIsland implements ModInitializer {

    //TODO
    //Portal: Save portal location the player entered the Winter Island dimension from so they can be returned to it.
    //The Workshop: Structure with portal in the top floor, multi-floor layout to fight your way up.
    //Saint Nick: Mini boss, half as hard as the wither to kill but can only be summoned.
    //Evil Elves: Elves that will try to attack the player once inside The Workshop
    //Portal Generation: Portals should generate in the overworld on the surface in cold biomes only.

    //FIXME Sleep not passing time with a mixin (Up's suggestion, Up can suggest help xD)
    //FIXME Weather never changes unless time is not fixed and cannot be changed with vanilla commands.
    //NOTE: THESE ARE VANILLA BUGS! We may be able to fix them with mixins...

    public static final String MOD_ID = "winterisland";
    public static final RegistryKey<World> WINTER_ISLAND = RegistryKey.of(Registry.WORLD_KEY,
        new Identifier(WinterIsland.MOD_ID, "winterisland"));

    public static final Block WINTER_PORTAL_BLOCK = new WinterPortalBlock();
    public static final Block WINTER_PORTAL_FRAME_BLOCK = new Block(AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK)
        .strength(-1.0F, 3600000.0F).dropsNothing());
    public static final BlockEntityType<WinterPortalBlockEntity> WINTER_PORTAL_BLOCK_ENTITY_TYPE
        = QuiltBlockEntityTypeBuilder.create(WinterPortalBlockEntity::new, WINTER_PORTAL_BLOCK).build();
    public static final Item WINTER_PORTAL_ITEM = new BlockItem(WINTER_PORTAL_BLOCK,
        new Item.Settings().group(ItemGroup.TRANSPORTATION));
    public static final Item WINTER_PORTAL_FRAME_ITEM = new BlockItem(WINTER_PORTAL_FRAME_BLOCK,
        new Item.Settings().group(ItemGroup.TRANSPORTATION));

    @Override
    public void onInitialize(ModContainer mod) {
        Registry.register(Registry.BLOCK, new Identifier(WinterIsland.MOD_ID,
            "winter_portal_block"), WINTER_PORTAL_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(WinterIsland.MOD_ID,
            "winter_portal_frame_block"), WINTER_PORTAL_FRAME_BLOCK);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(WinterIsland.MOD_ID,
            "winter_portal_block_entity_type"), WINTER_PORTAL_BLOCK_ENTITY_TYPE);
        Registry.register(Registry.ITEM, new Identifier(WinterIsland.MOD_ID,
            "winter_portal_item"), WINTER_PORTAL_ITEM);
        Registry.register(Registry.ITEM, new Identifier(WinterIsland.MOD_ID,
            "winter_portal_frame_item"), WINTER_PORTAL_FRAME_ITEM);
    }
}
