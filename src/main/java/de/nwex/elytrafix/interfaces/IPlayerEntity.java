package de.nwex.elytrafix.interfaces;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.item.ItemStack;

public interface IPlayerEntity
{
    PlayerAbilities abilities = new PlayerAbilities();

    ItemStack getEquippedStack(EquipmentSlot equipmentSlot);

    void method_23669();
}
