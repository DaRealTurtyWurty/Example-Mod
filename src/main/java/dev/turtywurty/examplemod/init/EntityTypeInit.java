package dev.turtywurty.examplemod.init;

import dev.turtywurty.examplemod.ExampleMod;
import dev.turtywurty.examplemod.entity.ExampleEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypeInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ExampleMod.MOD_ID);

    public static final RegistryObject<EntityType<ExampleEntity>> EXAMPLE_ENTITY = ENTITY_TYPES.register("example_entity",
            () -> EntityType.Builder.<ExampleEntity>of(ExampleEntity::new, MobCategory.CREATURE)
                    .sized(0.5F, 0.5F)
                    .build(new ResourceLocation(ExampleMod.MOD_ID, "example_entity").toString()));
}
