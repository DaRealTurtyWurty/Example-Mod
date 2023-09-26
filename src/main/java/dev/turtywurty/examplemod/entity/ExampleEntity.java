package dev.turtywurty.examplemod.entity;

import dev.turtywurty.examplemod.init.EntityTypeInit;
import dev.turtywurty.examplemod.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;

public class ExampleEntity extends Animal {
    public AnimationState idleAnimationState = new AnimationState();

    public ExampleEntity(EntityType<ExampleEntity> type, Level level) {
        super(type, level);
    }

    public ExampleEntity(Level level, double x, double y, double z) {
        super(EntityTypeInit.EXAMPLE_ENTITY.get(), level);
        this.setPos(x, y, z);
    }

    public ExampleEntity(Level level, BlockPos pos) {
        this(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(ItemInit.EXAMPLE_ITEM.get()), false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 1.0D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        var child = new ExampleEntity(level, getX(), getY(), getZ());
        child.setBaby(true);
        return child;
    }

    @Override
    public void tick() {
        if(level().isClientSide()) {
            this.idleAnimationState.animateWhen(!isInWaterOrBubble() && !this.walkAnimation.isMoving(), this.tickCount);
        }

        super.tick();
    }
}
