package de.nwex.elytrafix.mixin;

import de.nwex.elytrafix.interfaces.IPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity implements IPlayerEntity
{
    @Shadow
    public PlayerAbilities abilities;

    @Shadow
    @Override
    public abstract ItemStack getEquippedStack(EquipmentSlot equipmentSlot);

    @Shadow
    @Override
    public abstract void method_23669();
}
