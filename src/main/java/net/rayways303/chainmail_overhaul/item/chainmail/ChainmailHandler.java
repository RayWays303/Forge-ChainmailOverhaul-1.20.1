package net.rayways303.chainmail_overhaul.item.chainmail;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rayways303.chainmail_overhaul.ChainmailOverhaul;
@Mod.EventBusSubscriber(modid = ChainmailOverhaul.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChainmailHandler {

    private static final double HELMET_REDUCTION  = 0.12;
    private static final double CHEST_REDUCTION   = 0.28;
    private static final double LEGS_REDUCTION    = 0.20;
    private static final double BOOTS_REDUCTION   = 0.12;

    @SubscribeEvent
    static void onLivingHurt(LivingHurtEvent event) {
        DamageSource src = event.getSource();
        Entity attacker = src.getEntity();

        if (!(attacker instanceof LivingEntity living)) return;

        ItemStack weapon = living.getMainHandItem();
        if (ChainmailUtils.canPierce(weapon,src,attacker)) return;

        LivingEntity target = event.getEntity();
        double totalReduction = ChainmailUtils.getChainmailReduction(target);

        if (totalReduction > 0) {
            if (target.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ChainmailUtils.particleType,
                        target.getX(), target.getY() + target.getBbHeight() * 0.5, target.getZ(),
                        4,
                        0.3, 0.5, 0.3,
                        0.1
                );
            }
            event.setAmount((float)(event.getAmount() * (1.0 - totalReduction)));
        }
    }


    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        // Check if it's any chainmail piece
        if (stack.is(Items.CHAINMAIL_HELMET) ||
                stack.is(Items.CHAINMAIL_CHESTPLATE) ||
                stack.is(Items.CHAINMAIL_LEGGINGS) ||
                stack.is(Items.CHAINMAIL_BOOTS)) {

            event.getToolTip().add(Component.translatable("tooltip.chainmail_overhaul.melee_buff").setStyle(Style.EMPTY).withStyle(ChatFormatting.BLUE));
            event.getToolTip().add(Component.translatable("tooltip.chainmail_overhaul.sweep_negation").setStyle(Style.EMPTY).withStyle(ChatFormatting.BLUE));
            event.getToolTip().add(Component.translatable("tooltip.chainmail_overhaul.knockback_resistance").setStyle(Style.EMPTY).withStyle(ChatFormatting.BLUE));
        }
    }

    @SubscribeEvent
    public static void onLivingKnockBack(LivingKnockBackEvent event) {
        LivingEntity entity = event.getEntity();

        // Get chainmail reduction (e.g., 0.08 for full set)
        double chainmailKnockbackReduction = ChainmailUtils.getChainmailKnockback(entity);

        if (chainmailKnockbackReduction > 0) {
            event.setStrength((float)(event.getStrength() * (1.0 - chainmailKnockbackReduction)));
        }
    }

}
