package com.gmail.nossr50.skills;

import com.gmail.nossr50.ItemChecks;
import com.gmail.nossr50.Users;
import com.gmail.nossr50.datatypes.PlayerProfile;
import com.gmail.nossr50.datatypes.SkillType;
import com.gmail.nossr50.locale.mcLocale;
import com.gmail.nossr50.mcPermissions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Hoes skill.
 */
public class Hoes {
    public static void criticalStrikes(Player attacker, LivingEntity defender, EntityDamageByEntityEvent event) {
        PlayerProfile profile = Users.getProfile(attacker);
        double chance = profile.getSkillLevel(SkillType.HOES) * 0.001;
        if (chance > 0.75) {
            chance = 0.75;
        }

        if (Math.random() < chance) {
            double damage = event.getDamage();
            damage = (defender instanceof Player) ? damage * 2 : damage * 1.65;
            event.setDamage((int) damage);
        }
    }

    public static void slowEffect(Player attacker, LivingEntity defender, EntityDamageByEntityEvent event) {
        if (!(event instanceof EntityDamageByEntityEvent)) {
            return;
        }

        Entity f = ((EntityDamageByEntityEvent) event).getDamager();

        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        PlayerProfile PPd = Users.getProfile(attacker);

        if (!(ItemChecks.isSword(attacker.getItemInHand()) && mcPermissions.getInstance().swords(attacker))) {
            return;
        }

        if (!(Math.random() * 100 > 20)) {
            return;
        }

        // Check to make sure player and don't re-apply slow if already on.
        if (!defender.hasPotionEffect(PotionEffectType.SLOW)) {
            defender.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10, 2));
            if (defender instanceof Player) {
                ((Player) defender).sendMessage(mcLocale.getString("Swords.SlowEffect"));
            }
        }

        if (f instanceof Player && !defender.hasPotionEffect(PotionEffectType.SLOW)) {
            ((Player) f).sendMessage(mcLocale.getString("Swords.HitSlowEffect"));
        }
    }

    public static void performPlague(Player defender) {
        defender.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10, 2));
    }

}
