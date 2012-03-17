package com.gmail.nossr50.skills;

import com.gmail.nossr50.Users;
import com.gmail.nossr50.datatypes.PlayerProfile;
import com.gmail.nossr50.datatypes.SkillType;
import org.bukkit.entity.Player;

/**
 * Hoes skill.
 */
public class Hoes {
    public static void criticalStrikes(Player attacker) {
        PlayerProfile profile = Users.getProfile(attacker);
        profile.getSkillLevel(SkillType.HOES);
    }
}
