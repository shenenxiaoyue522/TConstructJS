package com.xiaoyue.tconstruct_js.content.material;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.rhino.mod.util.JsonUtils;

import java.util.Map;

public class MaterialStatsBuilder {
    private final JsonObject stats = new JsonObject();

    public MaterialStatsBuilder binding() {
        stats.add("tconstruct:binding", new JsonObject());
        return this;
    }

    public MaterialStatsBuilder maille() {
        stats.add("tconstruct:maille", new JsonObject());
        return this;
    }

    public MaterialStatsBuilder head(int durability, float meleeAttack, float miningSpeed, String miningTier) {
        JsonObject head = new JsonObject();
        head.addProperty("durability", durability);
        head.addProperty("melee_attack", meleeAttack);
        head.addProperty("mining_speed", miningSpeed);
        head.addProperty("mining_tier", miningTier);
        stats.add("tconstruct:head", head);
        return this;
    }

    public MaterialStatsBuilder handle(float durability, float meleeDamage, float meleeSpeed, float miningSpeed) {
        JsonObject handle = new JsonObject();
        handle.addProperty("durability", durability);
        handle.addProperty("melee_damage", meleeDamage);
        handle.addProperty("melee_speed", meleeSpeed);
        handle.addProperty("mining_speed", miningSpeed);
        stats.add("tconstruct:handle", handle);
        return this;
    }

    public MaterialStatsBuilder grip(float accuracy, float durability, float meleeDamage) {
        JsonObject grip = new JsonObject();
        grip.addProperty("accuracy", accuracy);
        grip.addProperty("durability", durability);
        grip.addProperty("melee_damage", meleeDamage);
        stats.add("tconstruct:grip", grip);
        return this;
    }

    public MaterialStatsBuilder limb(float accuracy, float drawSpeed, int durability, float velocity) {
        JsonObject limb = new JsonObject();
        limb.addProperty("accuracy", accuracy);
        limb.addProperty("draw_speed", drawSpeed);
        limb.addProperty("durability", durability);
        limb.addProperty("velocity", velocity);
        stats.add("tconstruct:limb", limb);
        return this;
    }

    public MaterialStatsBuilder platingHelmet(float armor, int durability, float toughness) {
        JsonObject helmet = new JsonObject();
        helmet.addProperty("armor", armor);
        helmet.addProperty("durability", durability);
        helmet.addProperty("toughness", toughness);
        stats.add("tconstruct:plating_helmet", helmet);
        return this;
    }

    public MaterialStatsBuilder platingChestplate(float armor, int durability, float toughness) {
        JsonObject chestplate = new JsonObject();
        chestplate.addProperty("armor", armor);
        chestplate.addProperty("durability", durability);
        chestplate.addProperty("toughness", toughness);
        stats.add("tconstruct:plating_chestplate", chestplate);
        return this;
    }

    public MaterialStatsBuilder platingLeggings(float armor, int durability, float toughness) {
        JsonObject leggings = new JsonObject();
        leggings.addProperty("armor", armor);
        leggings.addProperty("durability", durability);
        leggings.addProperty("toughness", toughness);
        stats.add("tconstruct:plating_leggings", leggings);
        return this;
    }

    public MaterialStatsBuilder platingBoots(float armor, int durability, float toughness) {
        JsonObject boots = new JsonObject();
        boots.addProperty("armor", armor);
        boots.addProperty("durability", durability);
        boots.addProperty("toughness", toughness);
        stats.add("tconstruct:plating_boots", boots);
        return this;
    }

    public MaterialStatsBuilder platingShield(int durability, float toughness) {
        JsonObject shield = new JsonObject();
        shield.addProperty("durability", durability);
        shield.addProperty("toughness", toughness);
        stats.add("tconstruct:plating_shield", shield);
        return this;
    }

    public MaterialStatsBuilder fullArmor(float armor, int durability, float toughness) {
        platingHelmet(armor, durability, toughness);
        platingChestplate(armor * 3.5f, (int)(durability * 1.23f), toughness);
        platingLeggings(armor * 2.5f, (int)(durability * 1.15f), toughness);
        platingBoots(armor, durability, toughness);
        platingShield((int)(durability * 1.38f), toughness);
        return this;
    }

    public MaterialStatsBuilder part(String partId, JsonObject data) {
        stats.add(partId, data);
        return this;
    }

    public MaterialStatsBuilder part(String partId, Map<String, Object> data) {
        JsonObject obj = new JsonObject();
        data.forEach((key, value) -> {
            if (value instanceof Number n) {
                obj.addProperty(key, n);
            } else if (value instanceof String s) {
                obj.addProperty(key, s);
            } else if (value instanceof Boolean b) {
                obj.addProperty(key, b);
            } else {
                obj.add(key, JsonUtils.of(value.toString()));
            }
        });
        stats.add(partId, obj);
        return this;
    }

    public MaterialStatsBuilder statsRaw(JsonObject rawStats) {
        rawStats.entrySet().forEach(entry -> {
            stats.add(entry.getKey(), entry.getValue());
        });
        return this;
    }

    public JsonElement build() {
        JsonObject root = new JsonObject();
        root.add("stats", stats);
        return root;
    }

    public String buildString() {
        return build().toString();
    }
}
