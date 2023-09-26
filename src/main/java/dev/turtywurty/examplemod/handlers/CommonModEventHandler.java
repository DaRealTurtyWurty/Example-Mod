package dev.turtywurty.examplemod.handlers;

import dev.turtywurty.examplemod.ExampleMod;
import dev.turtywurty.examplemod.entity.ExampleEntity;
import dev.turtywurty.examplemod.init.EntityTypeInit;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEventHandler {
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityTypeInit.EXAMPLE_ENTITY.get(), ExampleEntity.createAttributes().build());
    }
}
