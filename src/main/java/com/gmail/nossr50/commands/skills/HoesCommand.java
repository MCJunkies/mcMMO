package com.gmail.nossr50.commands.skills;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.nossr50.Users;
import com.gmail.nossr50.mcPermissions;
import com.gmail.nossr50.datatypes.PlayerProfile;
import com.gmail.nossr50.datatypes.SkillType;
import com.gmail.nossr50.locale.mcLocale;

public class HoesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command does not support console useage.");
            return true;
        }

        Player player = (Player) sender;
        PlayerProfile PP = Users.getProfile(player);

        String percentage;

        float skillvalue = (float) PP.getSkillLevel(SkillType.HOES);
        if (PP.getSkillLevel(SkillType.HOES) < 750) {
            percentage = String.valueOf((skillvalue * 0.001) * 100);
        } else {
            percentage = "0.0";
        }

//        int ticks = 2;
//        short durDmg = 5;
//        durDmg += Users.getProfile(player).getSkillLevel(SkillType.HOES) / 60;
//        int x = PP.getSkillLevel(SkillType.HOES);
//        while (x >= 50) {
//            x -= 50;
//            ticks++;
//        }
        int ticks = 10;

        player.sendMessage(mcLocale.getString("m.SkillHeader", new Object[]{mcLocale.getString("m.SkillHoes")}));
        player.sendMessage(mcLocale.getString("m.XPGain", new Object[]{mcLocale.getString("m.XPGainHoes")}));

        if (mcPermissions.getInstance().hoes(player)) {
            player.sendMessage(mcLocale.getString("m.LVL", new Object[]{PP.getSkillLevel(SkillType.HOES), PP.getSkillXpLevel(SkillType.HOES), PP.getXpToLevel(SkillType.HOES)}));
        }

        player.sendMessage(mcLocale.getString("m.SkillHeader", new Object[]{mcLocale.getString("m.Effects")}));
        player.sendMessage(mcLocale.getString("m.EffectsTemplate", new Object[]{mcLocale.getString("m.EffectsHoes1_0"), mcLocale.getString("m.EffectsHoes1_1")}));
        player.sendMessage(mcLocale.getString("m.EffectsTemplate", new Object[]{mcLocale.getString("m.EffectsHoes2_0"), mcLocale.getString("m.EffectsHoes2_1")}));
        player.sendMessage(mcLocale.getString("m.EffectsTemplate", new Object[]{mcLocale.getString("m.EffectsHoes3_0"), mcLocale.getString("m.EffectsHoes3_1")}));
        player.sendMessage(mcLocale.getString("m.SkillHeader", new Object[]{mcLocale.getString("m.YourStats")}));
        player.sendMessage(mcLocale.getString("m.HoesCritChance", new Object[]{percentage}));

        player.sendMessage(mcLocale.getString("m.HoesPlagueLength", new Object[]{ticks}));

        return true;
    }

}
