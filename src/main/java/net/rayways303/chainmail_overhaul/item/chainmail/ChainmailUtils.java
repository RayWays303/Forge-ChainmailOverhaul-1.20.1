package net.rayways303.chainmail_overhaul.item.chainmail;

import net.minecraft.core.particles.ParticleTypes;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;
import net.rayways303.chainmail_overhaul.ChainmailConfig;

public class ChainmailUtils {
    public static final double HELMET_REDUCTION  = 0.10;
    public static final double CHEST_REDUCTION   = 0.24;
    public static final double LEGS_REDUCTION    = 0.18;
    public static final double BOOTS_REDUCTION   = 0.10;

    public static final double SWEEP_NEGATE_THRESHOLD = 0.20;

    public static final SimpleParticleType particleType = ParticleTypes.SMOKE;
    public static double getChainmailReduction(LivingEntity entity) {
        double totalReduction = 0.0;

        if (entity.getItemBySlot(EquipmentSlot.HEAD).is(Items.CHAINMAIL_HELMET))
            totalReduction += HELMET_REDUCTION;
        if (entity.getItemBySlot(EquipmentSlot.CHEST).is(Items.CHAINMAIL_CHESTPLATE))
            totalReduction += CHEST_REDUCTION;
        if (entity.getItemBySlot(EquipmentSlot.LEGS).is(Items.CHAINMAIL_LEGGINGS))
            totalReduction += LEGS_REDUCTION;
        if (entity.getItemBySlot(EquipmentSlot.FEET).is(Items.CHAINMAIL_BOOTS))
            totalReduction += BOOTS_REDUCTION;

        return Math.min(totalReduction, 0.9); // cap at 90%
    }

    public static double getChainmailKnockback(LivingEntity entity) {
        double reduction = 0.0;

        if (entity.getItemBySlot(EquipmentSlot.HEAD).is(Items.CHAINMAIL_HELMET)) reduction += 0.02;
        if (entity.getItemBySlot(EquipmentSlot.CHEST).is(Items.CHAINMAIL_CHESTPLATE)) reduction += 0.03;
        if (entity.getItemBySlot(EquipmentSlot.LEGS).is(Items.CHAINMAIL_LEGGINGS)) reduction += 0.02;
        if (entity.getItemBySlot(EquipmentSlot.FEET).is(Items.CHAINMAIL_BOOTS)) reduction += 0.01;

        return Math.min(reduction, 0.08); // cap for full set
    }

    public static boolean canPierce(ItemStack weapon, DamageSource source, Entity attacker) {

        ResourceLocation id = ForgeRegistries.ITEMS.getKey(weapon.getItem());
        if (id != null && ChainmailConfig.COMMON.chainmailPierceWhitelist.get().contains(id.toString())) {
            return true;
        }

        if (attacker instanceof LivingEntity &&
                (source.is(DamageTypes.MOB_ATTACK) || source.is(DamageTypes.PLAYER_ATTACK))) {
            return false;
        }

        // Projectiles/magic etc. always pierce
        return true;
    }


}
