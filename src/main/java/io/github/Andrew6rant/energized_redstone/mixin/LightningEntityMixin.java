package io.github.Andrew6rant.energized_redstone.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.Andrew6rant.energized_redstone.EnergizedRedstone.energize;

@Mixin(LightningEntity.class)
public abstract class LightningEntityMixin extends Entity {
    @Shadow private int ambientTick;

    @Shadow
    private BlockPos getAffectedBlockPos() {
        Vec3d vec3d = this.getPos();
        return new BlockPos(vec3d.x, vec3d.y - 1.0E-6, vec3d.z);
    }

    public LightningEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void injectRedstoneHolder(CallbackInfo ci) {
        if (ambientTick == 2 && !world.isClient()) {
            energize(this.world, this.getAffectedBlockPos());
        }
    }
}
