package de.nwex.elytrafix.mixin;

import de.nwex.elytrafix.interfaces.ILivingEntity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements ILivingEntity
{
    @Shadow
    @Override
    public abstract boolean isFallFlying();

    @Shadow
    @Override
    public abstract boolean isClimbing();
}
