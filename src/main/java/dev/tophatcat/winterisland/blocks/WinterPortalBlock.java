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
package dev.tophatcat.winterisland.blocks;

import dev.tophatcat.winterisland.WinterIsland;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.worldgen.dimension.api.QuiltDimensions;

public class WinterPortalBlock extends BlockWithEntity {

    public WinterPortalBlock() {
        super(Settings.of(Material.PORTAL)
            .sounds(BlockSoundGroup.POWDER_SNOW)
            .noCollision()
            .luminance(lightLevel -> 15)
            .strength(-1.0F, 3600000.0F)
            .dropsNothing());
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WinterPortalBlockEntity(pos, state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0.0D, 6.0D, 0.0D,
            16.0D, 12.0D, 16.0D);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState state, World level, BlockPos pos, Entity entity) {
        if (entity.world.isClient()) {
            return;
        }

        ServerWorld winterIsland = level.getServer().getWorld(WinterIsland.WINTER_ISLAND);
        ServerWorld overworld = level.getServer().getWorld(World.OVERWORLD);

        if (level == winterIsland) {
            //TODO Return players to the overworld portal they entered from and offset safely so they don't land in it.
            QuiltDimensions.teleport(entity, overworld, new TeleportTarget(new Vec3d(0.5, 70, 0.5),
                entity.getVelocity(), entity.prevYaw, entity.prevPitch));
        } else {
            //TODO Properly setup offsets when landing in the Winter Island dimension,
            //portal should always spawn centered on 0 ~ 0
            QuiltDimensions.teleport(entity, winterIsland, new TeleportTarget(new Vec3d(0.5, 70, 0.5),
                entity.getVelocity(), entity.prevYaw, entity.prevPitch));
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World level, BlockPos pos, RandomGenerator random) {
        double d0 = (double) pos.getX() + random.nextDouble();
        double d1 = (double) pos.getY() + 0.8D;
        double d2 = (double) pos.getZ() + random.nextDouble();
        level.addParticle(ParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canBucketPlace(BlockState state, Fluid fluid) {
        return false;
    }
}
