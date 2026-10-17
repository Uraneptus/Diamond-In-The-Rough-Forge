package com.uraneptus.ditr.core.other;

import com.uraneptus.ditr.DiamondInTheRough;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class DITREntityTypeTags {
    public static final TagKey<EntityType<?>> DRAGON_FIREBALLS = TagKey.create(Registries.ENTITY_TYPE, DiamondInTheRough.modPrefix("dragon_fireballs"));
}
