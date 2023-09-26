package dev.turtywurty.examplemod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.turtywurty.examplemod.ExampleMod;
import dev.turtywurty.examplemod.client.animation.ExampleEntityAnimation;
import dev.turtywurty.examplemod.entity.ExampleEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ExampleEntityModel extends HierarchicalModel<ExampleEntity> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ExampleMod.MOD_ID, "example_entity"), "main");

	private final ModelParts parts;

	public ExampleEntityModel(ModelPart root) {
		ModelPart body = root.getChild("body");
		ModelPart head = body.getChild("head");

		this.parts = new ModelParts(body, head);
	}

	public static LayerDefinition createBodyLayer() {
		var meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		PartDefinition body = partDefinition.addOrReplaceChild("body",
				CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F,
								new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 23.0F, 0.0F));

		body.addOrReplaceChild("head",
				CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(-3.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F,
								new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -2.0F, 0.0F));

		return LayerDefinition.create(meshDefinition, 16, 16);
	}

	@Override
	public void setupAnim(@NotNull ExampleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		root().getAllParts().forEach(ModelPart::resetPose);
		animate(entity.idleAnimationState, ExampleEntityAnimation.IDLE, ageInTicks);
		if(!entity.isInWaterOrBubble()) {
			animateWalk(ExampleEntityAnimation.WALK, limbSwing, limbSwingAmount, 1.0F, 2.5F);
		} else {
			// Play swim animation
		}
	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.parts.body().render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public @NotNull ModelPart root() {
		return this.parts.body();
	}

	private record ModelParts(ModelPart body, ModelPart head) {}
}