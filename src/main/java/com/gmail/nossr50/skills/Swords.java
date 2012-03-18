package com.gmail.nossr50.skills;

import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import com.gmail.nossr50.Combat;
import com.gmail.nossr50.ItemChecks;
import com.gmail.nossr50.Users;
import com.gmail.nossr50.m;
import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.mcPermissions;
import com.gmail.nossr50.datatypes.PlayerProfile;
import com.gmail.nossr50.datatypes.SkillType;
import com.gmail.nossr50.locale.mcLocale;
import com.gmail.nossr50.party.Party;

public class Swords {
    /**
     * Check for Bleed effect.
     *
     * @param attacker The attacking player
     * @param entity The defending entity
     * @param plugin mcMMO plugin instance
     */
    public static void bleedCheck(Player attacker, LivingEntity entity, mcMMO plugin) {

        if (entity instanceof Wolf) {
            Wolf wolf = (Wolf) entity;

            if (wolf.isTamed()) {
                AnimalTamer tamer = wolf.getOwner();

                if (tamer instanceof Player) {
                    Player owner = (Player) tamer;

                    if (owner == attacker || Party.getInstance().inSameParty(attacker, owner)) {
                        return;
                    }
                }
            }
        }

        final int MAX_BONUS_LEVEL = 750;

        PlayerProfile PPa = Users.getProfile(attacker);
        int skillLevel = PPa.getSkillLevel(SkillType.SWORDS);
        int skillCheck = m.skillCheck(skillLevel, MAX_BONUS_LEVEL);

        if (Math.random() * 1000 <= skillCheck && !entity.isDead()) {
            if (entity instanceof Player) {
                Player target = (Player) entity;
                int bleedTicks;

                if (skillLevel >= 750) {
                    bleedTicks = 3;
                } else {
                    bleedTicks = 2;
                }

                Users.getProfile(target).addBleedTicks(bleedTicks);
            } else {
                plugin.misc.addToBleedQue(entity);
            }
            attacker.sendMessage(mcLocale.getString("Swords.EnemyBleeding"));
        }
    }

    /**
     * Counter-attack entities.
     *
     * @param event The event to modify
     */
    public static void counterAttackChecks(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();

        if (!(attacker instanceof LivingEntity)) {
            return;
        }

        Entity target = event.getEntity();

        if (target instanceof Player) {
            Player defender = (Player) target;
            PlayerProfile PPd = Users.getProfile(defender);

            if (ItemChecks.isSword(defender.getItemInHand()) && mcPermissions.getInstance().swords(defender)) {
                final int MAX_BONUS_LEVEL = 600;
                final int COUNTER_ATTACK_MODIFIER = 2;

                int skillLevel = PPd.getSkillLevel(SkillType.SWORDS);
                int skillCheck = m.skillCheck(skillLevel, MAX_BONUS_LEVEL);

                if (Math.random() * 2000 <= skillCheck) {
                    Combat.dealDamage((LivingEntity) attacker, event.getDamage() / COUNTER_ATTACK_MODIFIER);
                    defender.sendMessage(mcLocale.getString("Swords.CounterAttacked"));

                    if (attacker instanceof Player) {
                        ((Player) attacker).sendMessage(mcLocale.getString("Swords.HitByCounterAttack"));
                    }
                }
            }
        }
    }
    
    /**
     * Checks for heroic strike.
     * 
     * @param attacker The attacker.
     */
    public static void heroicStrike(Player attacker) {
        if (Math.random() * 100.0 <= 20.0) {
            attacker.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10, 0));
            attacker.sendMessage(mcLocale.getString("Swords.HeroicStrike"));
        }
    }

    /**
     * Simulate a bleed.
     *
     * @param plugin mcMMO plugin instance
     */
    public static void bleedSimulate(mcMMO plugin) {
        //Add items from Que list to BleedTrack list

        for (LivingEntity x : plugin.misc.bleedQue) {
            plugin.misc.bleedTracker.add(x);
        }

        //Clear list
        plugin.misc.bleedQue = new LivingEntity[plugin.misc.bleedQue.length];
        plugin.misc.bleedQuePos = 0;

        //Cleanup any dead entities from the list
        for (LivingEntity x : plugin.misc.bleedRemovalQue) {
            plugin.misc.bleedTracker.remove(x);
        }

        //Clear bleed removal list
        plugin.misc.bleedRemovalQue = new LivingEntity[plugin.misc.bleedRemovalQue.length];
        plugin.misc.bleedRemovalQuePos = 0;

        //Bleed monsters/animals
        for (LivingEntity x : plugin.misc.bleedTracker) {
            if (x == null || x.isDead()) {
                plugin.misc.addToBleedRemovalQue(x);
                continue;
            } else {
                Combat.dealDamage(x, 2);
            }
        }

        /*
         * Clear the BleedQue list
         */
        plugin.misc.bleedQue = new LivingEntity[plugin.misc.bleedQue.length];
        plugin.misc.bleedQuePos = 0;

        /*
         * Cleanup any dead entities from the list
         */
        for (LivingEntity entity : plugin.misc.bleedRemovalQue) {
            plugin.misc.bleedTracker.remove(entity);
        }

        /*
         * Clear bleed removal list
         */
        plugin.misc.bleedRemovalQue = new LivingEntity[plugin.misc.bleedRemovalQue.length];
        plugin.misc.bleedRemovalQuePos = 0;

        /*
         * Bleed monsters/animals
         */
        for (LivingEntity entity : plugin.misc.bleedTracker) {
            if (entity == null || entity.isDead()) {
                plugin.misc.addToBleedRemovalQue(entity);
                continue;
            } else {
                Combat.dealDamage(entity, 2);
            }
        }
    }

}
