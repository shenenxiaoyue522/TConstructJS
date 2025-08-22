package com.xiaoyue.tconstruct_js.event;

import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nullable;

public class TinkerToolChangeEventJS extends EventJS {

    private final EquipmentChangeContext context;

    public final LivingEntity entity;
    @Nullable
    public final Player player;
    public final Level world;
    public final EquipmentSlot changeSlot;
    public final boolean hasModifiableArmor;
    public final LazyOptional<TinkerDataCapability.Holder> tinkerData;
    @Nullable
    public final IToolStackView originalTool, replacementTool;
    @Nullable
    public final ItemStack original, replacement;

    public TinkerToolChangeEventJS(EquipmentChangeContext context) {
        this.context = context;
        this.entity = context.getEntity();
        this.player = context.getEntity() instanceof Player p ? p : null;
        this.changeSlot = context.getChangedSlot();
        this.originalTool = context.getOriginalTool();
        this.replacementTool = context.getReplacementTool();
        this.original = context.getOriginal();
        this.replacement = context.getReplacement();
        this.world = context.getLevel();
        this.hasModifiableArmor = context.hasModifiableArmor();
        this.tinkerData = context.getTinkerData();
    }

    public boolean hasModifiableArmor(EquipmentSlot... slots) {
        return this.context.hasModifiableArmor(slots);
    }

    @Nullable
    public IToolStackView getToolInSlot(EquipmentSlot slot) {
        return context.getToolInSlot(slot);
    }
}
