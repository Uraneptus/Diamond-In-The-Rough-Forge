package com.uraneptus.ditr.core.events;

import com.uraneptus.ditr.DiamondInTheRough;
import com.uraneptus.ditr.core.other.DITRBlockTags;
import com.uraneptus.ditr.core.registry.DITRBlocksItems;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;

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
            Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).getTag(DITRBlockTags.DRAGON_MADE_ORES).forEach(ores::add);

            for (BlockPos pos : BlockPos.betweenClosed(blockPos.offset(-width, -width, -width), blockPos.offset(width, width, width))) {
                if (level.getBlockState(pos).is(DITRBlockTags.OBSIDIAN_DIAMOND_ORE_REPLACEABLES) && (pos.distSqr(blockPos) <= (double)(radius * radius)) && yesDiamond(level)) {
                    level.setBlock(pos, ores.get(random.nextInt(ores.size())).defaultBlockState(), 3);
                }
            }
        }
    }

    private static boolean isCorrectEntity(Entity entity) {
        return !FMLEnvironment.production ? entity instanceof Fireball : entity instanceof DragonFireball;
    }

    public static boolean yesDiamond(Level level) {
        return (level.random.nextIntBetweenInclusive(1, 100) <= level.getGameRules().getInt(DiamondInTheRough.DIAMOND_CONVERSION_PERCENTAGE));
    }
}
