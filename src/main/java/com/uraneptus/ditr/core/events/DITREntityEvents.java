package com.uraneptus.ditr.core.events;

import com.uraneptus.ditr.DiamondInTheRough;
import com.uraneptus.ditr.core.other.DITRBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = DiamondInTheRough.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DITREntityEvents {

    @SubscribeEvent
    public static void onEntityRemoved(EntityLeaveLevelEvent event) {
        Level level = event.getLevel();
        Entity entity = event.getEntity();
        if (isCorrectEntity(entity) && level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING) && !(level.getGameRules().getInt(DiamondInTheRough.DIAMOND_CONVERSION_PERCENTAGE) < 1)) {
            RandomSource random = level.getRandom();
            BlockPos blockPos = entity.blockPosition();

            int width = random.nextIntBetweenInclusive(2, 4);
            float radius = (float)(width * 3) * 0.333F + 0.5F;
            List<Block> ores = new ArrayList<>();
            BuiltInRegistries.BLOCK.getTagOrEmpty(DITRBlockTags.DRAGON_MADE_ORES).forEach(blockHolder -> ores.add(blockHolder.value()));

            if (!ores.isEmpty()) {
                for (BlockPos pos : BlockPos.betweenClosed(blockPos.offset(-width, -width, -width), blockPos.offset(width, width, width))) {
                    if (level.getBlockState(pos).is(DITRBlockTags.OBSIDIAN_ORE_REPLACEABLES) && (pos.distSqr(blockPos) <= (double)(radius * radius)) && yesDiamond(level)) {
                        level.setBlock(pos, ores.get(random.nextInt(ores.size())).defaultBlockState(), Block.UPDATE_ALL);
                    }
                }
            } else {
                DiamondInTheRough.LOGGER.info("[" + DiamondInTheRough.MOD_ID + "]: List of obsidian ores is empty!");
            }

        }
    }

    private static boolean isCorrectEntity(Entity entity) {
        return !FMLEnvironment.production ? entity instanceof Fireball : entity instanceof DragonFireball;
    }

    public static boolean yesDiamond(Level level) {
        return level.getGameRules().getInt(DiamondInTheRough.DIAMOND_CONVERSION_PERCENTAGE) >= level.random.nextIntBetweenInclusive(1, 100);
    }
}
