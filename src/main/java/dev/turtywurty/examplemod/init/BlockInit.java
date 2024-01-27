package dev.turtywurty.examplemod.init;

import dev.turtywurty.examplemod.ExampleMod;
import dev.turtywurty.examplemod.block.CustomGrassBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MOD_ID);

    public static final RegistryObject<CustomGrassBlock> CUSTOM_GRASS_BLOCK = BLOCKS.register("custom_grass_block",
                    () -> new CustomGrassBlock(Block.Properties.copy(Blocks.GRASS_BLOCK)));
}
