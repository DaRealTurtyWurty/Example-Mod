package dev.turtywurty.examplemod.client.renderer;

import dev.turtywurty.examplemod.ExampleMod;
import dev.turtywurty.examplemod.client.model.ExampleEntityModel;
import dev.turtywurty.examplemod.entity.ExampleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ExampleEntityRenderer extends MobRenderer<ExampleEntity, ExampleEntityModel> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(ExampleMod.MOD_ID, "textures/entity/example_entity.png");

    public ExampleEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new ExampleEntityModel(ctx.bakeLayer(ExampleEntityModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ExampleEntity entity) {
        return TEXTURE;
    }
}
