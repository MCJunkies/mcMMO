package com.gmail.nossr50.skills;

import com.gmail.nossr50.Users;
import com.gmail.nossr50.datatypes.PlayerProfile;
import com.gmail.nossr50.datatypes.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

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

}
