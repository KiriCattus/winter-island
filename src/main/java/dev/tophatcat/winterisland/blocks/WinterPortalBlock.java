package dev.tophatcat.winterisland.blocks;

import dev.tophatcat.winterisland.WinterIsland;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
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
import org.quiltmc.qsl.worldgen.dimension.api.QuiltDimensions;

public class WinterPortalBlock extends Block {

    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 6.0D, 0.0D,
        16.0D, 12.0D, 16.0D);

    public WinterPortalBlock() {
        super(Settings.of(Material.PORTAL)
            .sounds(BlockSoundGroup.POWDER_SNOW)
            .noCollision()
            .luminance(lightLevel -> 15)
            .strength(-1.0F, 3600000.0F)
            .dropsNothing());
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context) {
        return SHAPE;
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
            QuiltDimensions.teleport(entity, overworld, new TeleportTarget(new Vec3d(0.5, 70, 0.5),
                entity.getVelocity(), entity.prevYaw, entity.prevPitch));
        } else {
            QuiltDimensions.teleport(entity, winterIsland, new TeleportTarget(new Vec3d(0.5, 70, 0.5),
                entity.getVelocity(), entity.prevYaw, entity.prevPitch));
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World level, BlockPos pos, RandomGenerator random) {
        double d0 = (double) pos.getX() + random.nextDouble();
        double d1 = (double) pos.getY() + 0.8D;
        double d2 = (double) pos.getZ() + random.nextDouble();
        level.addParticle(ParticleTypes.ITEM_SNOWBALL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public ItemStack getPickStack(BlockView getter, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canBucketPlace(BlockState state, Fluid fluid) {
        return false;
    }
}
