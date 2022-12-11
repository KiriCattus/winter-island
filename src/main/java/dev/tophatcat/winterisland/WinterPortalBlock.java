package dev.tophatcat.winterisland;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class WinterPortalBlock extends Block {

    private static final VoxelShape SHAPE = Block.box(0.0D, 6.0D, 0.0D,
        16.0D, 12.0D, 16.0D);

    public WinterPortalBlock() {
        super(Properties.of(Material.PORTAL)
            .sound(SoundType.POWDER_SNOW)
            .noCollission()
            .lightLevel(lightLevel -> 15)
            .strength(-1.0F, 3600000.0F)
            .noLootTable());
    }

    @NotNull
    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level,
                               @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
    public void entityInside(@NotNull BlockState state, @NotNull Level level,
                             @NotNull BlockPos pos, @NotNull Entity entity) {
        if (entity instanceof ServerPlayer player) {
            int y = level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ());
            ServerLevel WINTER_ISLAND = Objects.requireNonNull(player.getServer()).getLevel(WinterIsland.WINTER_ISLAND);
            ServerLevel OVER_WORLD = player.getServer().getLevel(Level.OVERWORLD);

            if (level.dimension().equals(WinterIsland.WINTER_ISLAND)) {
                if (OVER_WORLD != null) {
                    player.teleportTo(OVER_WORLD, 0.5, y, -2.5, entity.yRotO, entity.xRotO);
                }
            } else {
                if (WINTER_ISLAND != null) {
                    player.teleportTo(WINTER_ISLAND, 0.5, y, -2.5, entity.yRotO, entity.xRotO);
                }
            }
        }
    }

    public void animateTick(@NotNull BlockState state, Level level, BlockPos pos, RandomSource random) {
        double d0 = (double) pos.getX() + random.nextDouble();
        double d1 = (double) pos.getY() + 0.8D;
        double d2 = (double) pos.getZ() + random.nextDouble();
        level.addParticle(ParticleTypes.ITEM_SNOWBALL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @NotNull
    @SuppressWarnings("deprecation")
    public ItemStack getCloneItemStack(@NotNull BlockGetter getter, @NotNull BlockPos pos, @NotNull BlockState state) {
        return ItemStack.EMPTY;
    }

    @SuppressWarnings("deprecation")
    public boolean canBeReplaced(@NotNull BlockState state, @NotNull Fluid fluid) {
        return false;
    }
}
