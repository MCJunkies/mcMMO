package com.gmail.nossr50;

import org.bukkit.entity.Player;

public class mcPermissions {
    private static volatile mcPermissions instance;

    public boolean permission(Player player, String perm) {
        return player.hasPermission(perm);
    }

    public boolean admin(Player player) {
        return player.hasPermission("mcmmo.admin");
    }

    public boolean mcrefresh(Player player) {
        return player.hasPermission("mcmmo.tools.mcrefresh");
    }

    public boolean mcremove(Player player) {
        return player.hasPermission("mcmmo.tools.mcremove");
    }

    public boolean mmoedit(Player player) {
        return player.hasPermission("mcmmo.tools.mmoedit");
    }

    public boolean herbalismAbility(Player player) {
        return player.hasPermission("mcmmo.ability.herbalism");
    }

    public boolean excavationAbility(Player player) {
        return player.hasPermission("mcmmo.ability.excavation");
    }

    public boolean unarmedAbility(Player player) {
        return player.hasPermission("mcmmo.ability.unarmed");
    }

    public boolean chimaeraWing(Player player) {
        return player.hasPermission("mcmmo.item.chimaerawing");
    }

    public boolean miningAbility(Player player) {
        return player.hasPermission("mcmmo.ability.mining");
    }

    public boolean axesAbility(Player player) {
        return player.hasPermission("mcmmo.ability.axes");
    }

    public boolean swordsAbility(Player player) {
        return player.hasPermission("mcmmo.ability.swords");
    }

    public boolean woodCuttingAbility(Player player) {
        return player.hasPermission("mcmmo.ability.woodcutting");
    }

    public boolean mcgod(Player player) {
        return player.hasPermission("mcmmo.tools.mcgod");
    }

    public boolean motd(Player player) {
        return player.hasPermission("mcmmo.motd");
    }

    public boolean mcAbility(Player player) {
        return player.hasPermission("mcmmo.commands.ability");
    }

    public boolean partyChat(Player player) {
        return player.hasPermission("mcmmo.chat.partychat");
    }

    public boolean partyLock(Player player) {
        return player.hasPermission("mcmmo.chat.partylock");
    }

    public boolean partyTeleport(Player player) {
        return player.hasPermission("mcmmo.commands.ptp");
    }

    public boolean inspect(Player player) {
        return player.hasPermission("mcmmo.commands.inspect");
    }

    public boolean party(Player player) {
        return player.hasPermission("mcmmo.commands.party");
    }

    public boolean adminChat(Player player) {
        return player.hasPermission("mcmmo.chat.adminchat");
    }

    public static mcPermissions getInstance() {
        if (instance == null) {
            instance = new mcPermissions();
        }

        return instance;
    }

    public boolean taming(Player player) {
        return player.hasPermission("mcmmo.skills.taming");
    }

    public boolean mining(Player player) {
        return player.hasPermission("mcmmo.skills.mining");
    }

    public boolean blastMining(Player player) {
        return player.hasPermission("mcmmo.skills.blastmining");
    }

    public boolean fishing(Player player) {
        return player.hasPermission("mcmmo.skills.fishing");
    }

    public boolean woodcutting(Player player) {
        return player.hasPermission("mcmmo.skills.woodcutting");
    }

    public boolean repair(Player player) {
        return player.hasPermission("mcmmo.skills.repair");
    }

    public boolean repairArcaneBypass(Player player) {
        return player.hasPermission(("mcmmo.skills.repair.arcanebypass"));
    }

    public boolean unarmed(Player player) {
        return player.hasPermission("mcmmo.skills.unarmed");
    }

    public boolean archery(Player player) {
        return player.hasPermission("mcmmo.skills.archery");
    }

    public boolean herbalism(Player player) {
        return player.hasPermission("mcmmo.skills.herbalism");
    }

    public boolean excavation(Player player) {
        return player.hasPermission("mcmmo.skills.excavation");
    }

    public boolean swords(Player player) {
        return player.hasPermission("mcmmo.skills.swords");
    }

    public boolean axes(Player player) {
        return player.hasPermission("mcmmo.skills.axes");
    }

    public boolean acrobatics(Player player) {
        return player.hasPermission("mcmmo.skills.acrobatics");
    }

    public boolean hoes(Player player) {
        return player.hasPermission("mcmmo.skills.hoes");
    }

}
