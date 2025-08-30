package net.rayways303.chainmail_overhaul;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import java.util.List;

@Mod.EventBusSubscriber(modid = net.rayways303.chainmail_overhaul.ChainmailOverhaul.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChainmailConfig {

    public static final ForgeConfigSpec COMMON_CONFIG;
    public static final ForgeConfigSpec CLIENT_CONFIG;
    public static final ForgeConfigSpec SERVER_CONFIG;
    public static final Common COMMON;
    public static final Client CLIENT;
    public static final Server SERVER;
    static
    {
        ForgeConfigSpec.Builder commonBuilder = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder clientBuilder = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder serverBuilder = new ForgeConfigSpec.Builder();

        COMMON = new Common(commonBuilder);
        COMMON_CONFIG = commonBuilder.build();

        CLIENT = new Client(clientBuilder);
        CLIENT_CONFIG = clientBuilder.build();

        SERVER = new Server(serverBuilder);
        SERVER_CONFIG = serverBuilder.build();
    }

    public static class Common {

        public final ForgeConfigSpec.BooleanValue doChainmailMeleeResistance;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> chainmailPierceWhitelist;

        public final ForgeConfigSpec.BooleanValue doEntityChainmailModifier;
        public final ForgeConfigSpec.DoubleValue baseEntityChainmailModifier;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> chainmailEntityModWhitelist;


        public Common(ForgeConfigSpec.Builder BUILDER) {
            BUILDER.push("Chainmail Weapon Interactions");
            doChainmailMeleeResistance = BUILDER
                    .comment("Allows for chainmail armor to better resist melee.",
                            "Caution: Main feature of the mod.")
                            .define("doChainmailMeleeResistance", true);
            chainmailPierceWhitelist = BUILDER
                    .comment("List of weapon IDs that are affected by this chainmail modifier.",
                            "Example: [\"minecraft:trident\", \"minecraft:diamond_sword\"]",
                            "Use the full resource location (namespace:weapon).")
                    .defineList("chainmailPierceWhitelist",
                            List.of("minecraft:trident",
                                    "basicweapons:wood_dagger",
                                    "basicweapons:stone_dagger",
                                    "basicweapons:iron_dagger",
                                    "basicweapons:gold_dagger",
                                    "basicweapons:diamond_dagger",
                                    "basicweapons:netherite_dagger",
                                    "basicweapons:wood_spear",
                                    "basicweapons:stone_spear",
                                    "basicweapons:iron_spear",
                                    "basicweapons:gold_spear",
                                    "basicweapons:diamond_spear",
                                    "basicweapons:netherite_spear",

                                    "spartanweaponry:wooden_dagger",
                                    "spartanweaponry:stone_dagger",
                                    "spartanweaponry:copper_dagger",
                                    "spartanweaponry:iron_dagger",
                                    "spartanweaponry:golden_dagger",
                                    "spartanweaponry:diamond_dagger",
                                    "spartanweaponry:netherite_dagger",
                                    "spartanweaponry:tin_dagger",
                                    "spartanweaponry:bronze_dagger",
                                    "spartanweaponry:steel_dagger",
                                    "spartanweaponry:silver_dagger",
                                    "spartanweaponry:electrum_dagger",
                                    "spartanweaponry:lead_dagger",
                                    "spartanweaponry:nickel_dagger",
                                    "spartanweaponry:invar_dagger",
                                    "spartanweaponry:constantan_dagger",
                                    "spartanweaponry:platinum_dagger",
                                    "spartanweaponry:aluminum_dagger",
                                    "spartanweaponry:custom_dagger",
                                    "spartanweaponry:wooden_rapier",
                                    "spartanweaponry:stone_rapier",
                                    "spartanweaponry:copper_rapier",
                                    "spartanweaponry:iron_rapier",
                                    "spartanweaponry:golden_rapier",
                                    "spartanweaponry:diamond_rapier",
                                    "spartanweaponry:netherite_rapier",
                                    "spartanweaponry:tin_rapier",
                                    "spartanweaponry:bronze_rapier",
                                    "spartanweaponry:steel_rapier",
                                    "spartanweaponry:silver_rapier",
                                    "spartanweaponry:electrum_rapier",
                                    "spartanweaponry:lead_rapier",
                                    "spartanweaponry:nickel_rapier",
                                    "spartanweaponry:invar_rapier",
                                    "spartanweaponry:constantan_rapier",
                                    "spartanweaponry:platinum_rapier",
                                    "spartanweaponry:aluminum_rapier",
                                    "spartanweaponry:custom_rapier",
                                    "spartanweaponry:wooden_spear",
                                    "spartanweaponry:stone_spear",
                                    "spartanweaponry:copper_spear",
                                    "spartanweaponry:iron_spear",
                                    "spartanweaponry:golden_spear",
                                    "spartanweaponry:diamond_spear",
                                    "spartanweaponry:netherite_spear",
                                    "spartanweaponry:tin_spear",
                                    "spartanweaponry:bronze_spear",
                                    "spartanweaponry:steel_spear",
                                    "spartanweaponry:silver_spear",
                                    "spartanweaponry:electrum_spear",
                                    "spartanweaponry:lead_spear",
                                    "spartanweaponry:nickel_spear",
                                    "spartanweaponry:invar_spear",
                                    "spartanweaponry:constantan_spear",
                                    "spartanweaponry:platinum_spear",
                                    "spartanweaponry:aluminum_spear",
                                    "spartanweaponry:custom_spear",

                                    "item.tconstruct.dagger",

                                    "item.epicfight.wooden_dagger",
                                    "item.epicfight.stone_dagger",
                                    "item.epicfight.golden_dagger",
                                    "item.epicfight.iron_dagger",
                                    "item.epicfight.diamond_dagger",
                                    "item.epicfight.netherite_dagger",
                                    "item.epicfight.wooden_spear",
                                    "item.epicfight.stone_spear",
                                    "item.epicfight.iron_spear",
                                    "item.epicfight.golden_spear",
                                    "item.epicfight.diamond_spear",
                                    "item.epicfight.netherite_spear"
                            ),
                            obj -> obj instanceof String);

            BUILDER.pop();
            BUILDER.push("Entity Chainmail Modifiers");

            doEntityChainmailModifier = BUILDER
                    .comment("Modifies zombies to be more likely to spawn with full set of chainmail")
                    .define("doEntityChainmailModifier", true);
            baseEntityChainmailModifier = BUILDER
                    .comment("Sets the base chance for zombies to spawn for all difficulties.",
                            "Difficulty Modifiers: (Easy Mode = Base, Normal Mode = Base + 0.1, Hard Mode = Base + 0.2")
                    .defineInRange("baseEntityChainmailModifier", 0.0, 0.0, 1.0);
            chainmailEntityModWhitelist = BUILDER
                    .comment(
                            "List of entity IDs that are affected by this chainmail modifier.",
                            "Example: [\"minecraft:zombie\", \"minecraft:skeleton\"]",
                            "Use the full resource location (namespace:entity)."
                    )
                    .defineList("chainmailEntityModWhitelist",
                            List.of("minecraft:zombie"), // default: only zombies
                            obj -> obj instanceof String);
            BUILDER.pop();
        }
    }

    public static class Client {

        public Client(ForgeConfigSpec.Builder BUILDER) {
        }
    }

    public static class Server {

        public Server(ForgeConfigSpec.Builder BUILDER) {

        }
    }

}

