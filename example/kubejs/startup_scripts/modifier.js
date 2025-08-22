// priority: 0

TConJSEvents.modifierRegistry((event) => {
  event.createEmpty("id");

  event.createNew("give_me_hat", (builder) => {
    builder
      .projectileHitEntity((modifier, modDataNBT, damage, projectile, hitResult, sourceEntity, targetEntity) => {
        targetEntity.block.up.popItem(targetEntity.headArmorItem);
        targetEntity.headArmorItem = "air";
        return false;
      })
      .onAfterMeleeHit((view, damage, context) => {
        context.livingTarget.block.up.popItem(context.livingTarget.headArmorItem);
        context.livingTarget.headArmorItem = "air";
      });
  });

  event.createNew("more_damage", (builder) => {
    builder.addToolStats((view, lvl, statsBuilder) => {
      TinkerToolStats.ATTACK_DAMAGE.multiply(statsBuilder, 1 + lvl * 0.1);
      TinkerToolStats.PROJECTILE_DAMAGE.multiply(statsBuilder, 1 + lvl * 0.1);
    });
  });

  event.createNew("speedy", (builder) => {
    builder.addAttributes((view, lvl, slot, attributes) => {
      attributes.put(
        "minecraft:generic.movement_speed",
        builder.getAttributeModifier("b444bae1-abde-41ed-8688-f75a469fdbf4", "aabb", lvl * 0.1, "multiply_base")
      );
      return attributes;
    });
  });

  event.createNew("blood_speed", (builder) => {
    builder.getBreakSpeed((view, lvl, breakSpeedEvent, direction, canDrop, currentSpeed) => {
      if (breakSpeedEvent.entity.health / breakSpeedEvent.entity.maxHealth < 0.5) {
        breakSpeedEvent.newSpeed = currentSpeed * (1 + lvl * 0.1);
      }
    });
  });

  event.createNew("not_easy_to_broken", (builder) => {
    builder.getToolDamage((view, lvl, damage, entity) =>
      Array(damage)
        .fill()
        .reduce((acc, cur) => acc + entity.random.nextBoolean(), 0)
    );
  });

  event.createNew("i_need_more_powerful", (builder) => {
    builder.onInventoryTick((view, lvl, level, entity, slot, inMainHand, inAvailableSlot, itemStack) => {
      if (inMainHand) {
        entity.potionEffects.add("minecraft:strength", 1, lvl);
      }
    });
  });

  event.createNew("easy_repair", (builder) => {
    builder.getRepairFactor((view, lvl, repaired) => {
      return repaired * (1 + lvl * 0.15);
    });
  });

  event.createNew("randomly_tooltip", (builder) => {
    builder.tooltipSetting((view, lvl, player, tooltip, key, flag) => {
      tooltip.add(Text.of("000000000").obfuscated());
    });
  });

  event.createNew("shiny", (builder) => {
    builder.addVolatileData((context, lvl, data) => {
      data.putBoolean("tconstruct:shiny", true);
    });
  });

  event.createNew("golden_body", (builder) => {
    builder.canBlockAttacked((view, lvl, context, slot, source, damage) => {
      return !!(source.immediate && source.actual && source.immediate.is(source.actual));
    });
  });

  event.createNew("reverse_damage", (builder) => {
    builder.armorTakeAttacked((view, lvl, context, slot, source, damage) => {
      const returned = source.actual || source.immediate;
      if (returned != null) {
        returned.attack(source, damage);
      }
      return true;
    });
  });

  event.createNew("lighter", (builder) => {
    builder.findBowAmmo((view, lvl, living, stack, predicate) => {
      return stack.id == "minecraft:spectral_arrow" ? stack.withCount(stack.count - 1) : Item.empty();
    });
  });

  event.createNew("red_bar", (builder) => {
    builder.getDurabilityRGB((view, lvl) => {
      return 0xff0000;
    });
  });

  event.createNew("tiny_bar", (builder) => {
    builder.getDurabilityWidth((view, lvl) => {
      return 6;
    });
  });

  event.createNew("random_melee", (builder) => {
    builder.getMeleeDamage((view, lvl, context, baseDamage, finalDamage) => {
      return finalDamage * (context.attacker.random.nextInt(1, 20) / 10);
    });
  });

  event.createNew("spyglassable", (builder) => {
    builder.getUseAnim((view, lvl) => {
      return "spyglass";
    });
  });

  event.createNew("fast_draw", (builder) => {
    builder.getUseTime((view, lvl) => {
      return SimpleTCon.castToolStack(view).item.getUseDuration() / (1 + lvl);
    });
  });

  event.createNew("unknown_durability", (builder) => {
    builder.isDurabilityShowBar((view, lvl) => {
      return false;
    });
  });

  event.createNew("single", (builder) => {
    builder.isSingleLevel();
  });

  event.createNew("silver_body", (builder) => {
    builder.modifyDamageTaken((view, lvl, context, slot, source, damage) => {
      return damage - damage * (0.1 * lvl);
    });
  });

  event.createNew("lower_protection", (builder) => {
    builder.modifyProtection((view, lvl, context, slot, source, protection) => {
      return protection - protection * (0.1 * lvl);
    });
  });

  event.createNew("stat_plus", (builder) => {
    builder.conditionalStat((view, lvl, entity, stat, base, magnification) => {
      switch (stat) {
        case TinkerToolStats.ATTACK_DAMAGE:
          return base * (1 + lvl * magnification);
        case TinkerToolStats.PROJECTILE_DAMAGE:
          return base * (1 + lvl * magnification);
        default:
          return base;
      }
    });
  });

  event.createNew("midas_tool", (builder) => {
    builder.onAfterBreak((view, lvl, context) => {
      context.world.getBlock(context.pos).set("minecraft:gold_block");
    });
  });

  event.createNew("slap", (builder) => {
    builder.onBeforeMeleeHit((view, lvl, context, damage, baseKnockback, finalKnockback) => {
      return finalKnockback * (1 + lvl * 0.1);
    });
  });

  event.createNew("my_air", (builder) => {
    builder.onDamageDealt((view, lvl, context, slot, living, source, damage) => {
      const { airSupply } = living;
      const attacker = source.actual || source.immediate;
      if (attacker != null) {
        living.airSupply = attacker.airSupply;
        attacker.airSupply = airSupply;
      }
    });
  });

  event.createNew("hotter", (builder) => {
    builder.onEquip((view, lvl, context) => {
      context.entity.secondsOnFire = 1 * lvl;
    });
  });

  event.createNew("saturation_after_use", (builder) => {
    builder.onFinishUsing((view, lvl, /** @type {Internal.Player_} */ living) => {
      if (view.item.id == "tconstruct:crossbow" && living.player) {
        living.foodData.eat("golden_apple", "golden_apple");
      }
    });
  });

  event.createNew("i_cant_stop", (builder) => {
    builder.onStoppedUsing((view, lvl, living, duration) => {
      if (living.player) {
        living.kick();
      }
    });
  });

  event.createNew("why_unequip_me", (builder) => {
    builder.onUnequip((view, lvl, context) => {
      context.entity.ticksFrozen = 20 * 25;
    });
  });

  event.createNew("show_item", (builder) => {
    builder.onUseTool((view, lvl, player, hand, source) => {
      player.tell(player.getItemInHand(hand).displayName);
      return true;
    });
  });

  event.createNew("shooting_golden_cover", (builder) => {
    builder.onUsingTick((view, lvl, living, duration) => {
      living.potionEffects.add("resistance", 1, 4);
    });
  });

  event.createNew("no_loot", (builder) => {
    builder.processLoot((view, lvl, items, context) => {
      items.clear();
    });
  });

  event.createNew("lit", (builder) => {
    builder.projectileHitBlock((modifier, modData, lvl, projectile, hitResult, living) => {
      const block = projectile.level.getBlock(hitResult.blockPos);
      const { properties } = block;
      if (properties.get("lit")) {
        projectile.lit = projectile.lit == "true" ? "false" : "true";
      }
      block.set(block.id, properties);
    });
  });

  event.createNew("fully_charge", (builder) => {
    builder.projectileLaunch((view, lvl, living, projectile, arrow, modData, arg6) => {
      if (living.player) {
        arrow.baseDamage += living.foodData.foodLevel;
      }
    });
  });

  event.createNew("elytrable", (builder) => {
    builder.setElytraFlight((view, lvl, living, flyDuration) => {
      return true;
    });
  });

  event.createNew("sweeping", (builder) => {
    builder.toolUseAction((view, lvl) => {
      return builder.toolActionOf("sword_sweep");
    });
  });

  event.createNew("multi_armor_loot", (builder) => {
    builder.updateArmorLooting((view, lvl, looting, equipment, slot, originalLvl) => {
      return originalLvl * lvl;
    });
  });

  event.createNew("multi_tool_loot", (builder) => {
    builder.updateToolLooting((view, lvl, looting, originalLvl) => {
      return originalLvl * lvl;
    });
  });

  event.createNew("immediate_broke", (builder) => {
    builder.validateTool((view, lvl) => {
      let toolStack = SimpleTCon.castToolStack(view);
      console.log(toolStack);
      let itemStack = toolStack.createStack();
      console.log(itemStack);
      TinkerDamageHelper.breakTool(itemStack);
      return Text.gold("immediate");
    });
  });
});
