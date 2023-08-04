package com.uraneptus.ditr.core.events;

import com.uraneptus.ditr.DiamondInTheRough;
import com.uraneptus.ditr.core.other.DITRBlockTags;
import com.uraneptus.ditr.core.registry.DITRBlocksItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DiamondInTheRough.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DITREntityEvents {

    @SubscribeEvent
    public static void onEntityRemoved(EntityLeaveLevelEvent event) {
        Level level = event.getLevel();
        if (event.getEntity() instanceof DragonFireball fireball && level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
            BlockPos blockPos = fireball.blockPosition();
            int width = level.getRandom().nextIntBetweenInclusive(2, 4);
            float radius = (float)(width * 3) * 0.333F + 0.5F;

            for (BlockPos pos : BlockPos.betweenClosed(blockPos.offset(-width, -width, -width), blockPos.offset(width, width, width))) {
                if (level.getBlockState(pos).is(DITRBlockTags.OBSIDIAN_DIAMOND_ORE_REPLACEABLES) && (pos.distSqr(blockPos) <= (double)(radius * radius)) && yesDiamond(level)) {
                    level.setBlock(pos, DITRBlocksItems.OBSIDIAN_DIAMOND_ORE.get().defaultBlockState(), 3);
                }
            }
        }
    }

    public static boolean yesDiamond(Level level){
        if (level.getGameRules().getInt(DiamondInTheRough.DIAMOND_CONVERSION_PERCENTAGE) > 100){
            return true;
        }
        else if (level.getGameRules().getInt(DiamondInTheRough.DIAMOND_CONVERSION_PERCENTAGE) < 1){
            return false;
        }
        else{
            return (level.random.nextIntBetweenInclusive(1, 100) <= level.getGameRules().getInt(DiamondInTheRough.DIAMOND_CONVERSION_PERCENTAGE));
        }
    }
}
