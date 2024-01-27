package dev.turtywurty.examplemod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.IPlantable;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class CustomGrassBlock extends SpreadingSnowyDirtBlock implements BonemealableBlock {
    public CustomGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state, boolean p_50900_) {
        return level.getBlockState(pos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        BlockPos abovePos = pos.above();
        Optional<Holder.Reference<PlacedFeature>> grassFeatures = level.registryAccess()
                .registryOrThrow(Registries.PLACED_FEATURE)
                .getHolder(VegetationPlacements.GRASS_BONEMEAL); // You'd ideally have your own placed feature for this

        checkLoop: for(int checkIndex = 0; checkIndex < 128; ++checkIndex) {
            BlockPos offsetPos = abovePos;

            for (int j = 0; j < checkIndex / 16; ++j) {
                offsetPos = offsetPos.offset(
                        random.nextInt(3) - 1,
                        (random.nextInt(3) - 1) * random.nextInt(3) / 2,
                        random.nextInt(3) - 1);
                if (!level.getBlockState(offsetPos.below()).is(this) || level.getBlockState(offsetPos).isCollisionShapeFullBlock(level, offsetPos)) {
                    continue checkLoop;
                }
            }

            BlockState offsetState = level.getBlockState(offsetPos);
            if (offsetState.is(this) && random.nextInt(10) == 0) {
                performBonemeal(level, random, offsetPos, offsetState);
            }

            if (offsetState.isAir()) {
                Holder<PlacedFeature> featureHolder;
                if (random.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> flowerFeatures = level.getBiome(offsetPos)
                            .value()
                            .getGenerationSettings()
                            .getFlowerFeatures();
                    if (flowerFeatures.isEmpty() || !(flowerFeatures.get(0).config() instanceof RandomPatchConfiguration randomPatchConfiguration))
                        continue;

                    featureHolder = randomPatchConfiguration.feature();
                } else {
                    if (grassFeatures.isEmpty())
                        continue;

                    featureHolder = grassFeatures.get();
                }

                featureHolder.value().place(level, level.getChunkSource().getGenerator(), random, offsetPos);
            }
        }
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        return true;
    }
}
