package net.rayways303.chainmail_overhaul.item.chainmail;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.rayways303.chainmail_overhaul.ChainmailConfig;
import net.rayways303.chainmail_overhaul.ChainmailOverhaul;

@Mod.EventBusSubscriber(modid = ChainmailOverhaul.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityChainmailSpawnModifier {

    @SubscribeEvent
    public static void onMobSpawn(MobSpawnEvent.FinalizeSpawn event) {

        boolean doEntityModification = ChainmailConfig.COMMON.doEntityChainmailModifier.get().booleanValue();
        if (!doEntityModification) return;

        Mob mob = event.getEntity();

        ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(mob.getType());
        if (id == null) return;

        if (!ChainmailConfig.COMMON.chainmailEntityModWhitelist.get().contains(id.toString())) return;

        Difficulty difficulty = mob.level().getDifficulty();
        float base = ChainmailConfig.COMMON.baseEntityChainmailModifier.get().floatValue();
        float slotChance = switch (difficulty) {
            case EASY -> base + 0.10f;
            case NORMAL -> base + 0.20f;
            case HARD -> base + 0.30f;
            default -> base;
        };

        float dropChance = difficulty == Difficulty.HARD ? 0.10f : 0.05f;

        // HEAD
        if (mob.getItemBySlot(EquipmentSlot.HEAD).isEmpty()
                && mob.getRandom().nextFloat() < slotChance) {
            mob.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
            mob.setDropChance(EquipmentSlot.HEAD, dropChance);
        }

        // CHEST
        if (mob.getItemBySlot(EquipmentSlot.CHEST).isEmpty()
                && mob.getRandom().nextFloat() < slotChance) {
            mob.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE));
            mob.setDropChance(EquipmentSlot.CHEST, dropChance);
        }

        // LEGS
        if (mob.getItemBySlot(EquipmentSlot.LEGS).isEmpty()
                && mob.getRandom().nextFloat() < slotChance) {
            mob.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.CHAINMAIL_LEGGINGS));
            mob.setDropChance(EquipmentSlot.LEGS, dropChance);
        }

        // FEET
        if (mob.getItemBySlot(EquipmentSlot.FEET).isEmpty()
                && mob.getRandom().nextFloat() < slotChance) {
            mob.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.CHAINMAIL_BOOTS));
            mob.setDropChance(EquipmentSlot.FEET, dropChance);
        }
    }
}