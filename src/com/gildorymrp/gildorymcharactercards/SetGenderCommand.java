package com.gildorymrp.gildorymcharactercards;

import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetGenderCommand
  implements CommandExecutor
{
  private GildorymCharacterCards plugin;

  public SetGenderCommand(GildorymCharacterCards plugin)
  {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player player = null;
    if (args.length < 1) {
      sender.sendMessage(ChatColor.RED + 
        "You need to specify a gender!");
      return true;
    }if (args.length == 1) {
      if ((sender instanceof Player)) {
        player = (Player)sender;
      } else {
        sender.sendMessage(ChatColor.RED + 
          "Only a player can perform this command!");
        return true;
      }
    } else {
      if (!sender.hasPermission("gildorym.setgenderother")) {
        sender.sendMessage(ChatColor.RED + 
          "You do not have permission to change another player's race!");
      }
      player = sender.getServer().getPlayer(args[1]);
      if (player == null) {
        sender.sendMessage(ChatColor.RED + 
          "That player does not exist!");
      }
    }

    if (this.plugin.getCharacterCards().get(sender.getName()) == null) {
      this.plugin.getCharacterCards().put(sender.getName(), new CharacterCard(Integer.valueOf(0), Gender.UNKNOWN, "", Race.UNKNOWN));
    }
    if (args.length >= 1)
      try {
        ((CharacterCard)this.plugin.getCharacterCards().get(sender.getName())).setGender(Gender.valueOf(args[0].toUpperCase()));
        sender.sendMessage(ChatColor.GREEN + "Set gender to " + Gender.valueOf(args[0].toUpperCase()).toString());
      } catch (IllegalArgumentException exception) {
        sender.sendMessage(ChatColor.RED + "That gender does not exist!");
        sender.sendMessage(ChatColor.YELLOW + "If the gender is not standard, i.e. MALE or FEMALE, you may want to choose OTHER or UNKNOWN.");
      }
    else {
      sender.sendMessage(ChatColor.RED + "You need to specify a gender!");
    }
    return true;
  }
}