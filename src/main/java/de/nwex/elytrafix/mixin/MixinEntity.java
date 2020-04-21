package de.nwex.elytrafix.mixin;

import de.nwex.elytrafix.interfaces.IEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public abstract class MixinEntity implements IEntity
{
    @Accessor("onGround")
    @Override
    public abstract boolean onGround();

    @Accessor("touchingWater")
    @Override
    public abstract boolean isTouchingWater();

    @Shadow
    @Override
    public abstract boolean hasVehicle();
}