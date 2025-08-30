package net.rayways303.chainmail_overhaul.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorMaterials.class)
public class ArmorMaterialsMixin {

    @Inject(method = "getDefenseForType", at = @At("HEAD"), cancellable = true)
    private void modifyChainDefense(ArmorItem.Type type, CallbackInfoReturnable<Integer> cir) {
        if (((ArmorMaterials)(Object)this).name().equals("CHAIN")) {
            int[] newDefense = {1, 2, 2, 1}; // boots, leggings, chestplate, helmet
            cir.setReturnValue(newDefense[type.ordinal()]);
        }
    }

    @Inject(method = "getDurabilityForType", at = @At("HEAD"), cancellable = true)
    private void modifyChainDurability(ArmorItem.Type type, CallbackInfoReturnable<Integer> cir) {
        if (((ArmorMaterials)(Object)this).name().equals("CHAIN")) {
            int[] baseDurability = {13, 15, 16, 11}; // boots, leggings, chestplate, helmet
            int newMultiplier = 37; // match netherite
            cir.setReturnValue(baseDurability[type.ordinal()] * newMultiplier);
        }
    }
}
