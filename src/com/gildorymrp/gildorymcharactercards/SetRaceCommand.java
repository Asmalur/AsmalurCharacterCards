package com.gildorymrp.gildorymcharactercards;

import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetRaceCommand
  implements CommandExecutor
{
  private GildorymCharacterCards plugin;

  public SetRaceCommand(GildorymCharacterCards plugin)
  {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    Player player = null;
    if (args.length < 1) {
      sender.sendMessage(ChatColor.RED + 
        "You need to specify a race!");
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
      if (!sender.hasPermission("gildorym.setraceother")) {
        sender.sendMessage(ChatColor.RED + 
          "You do not have permission to change another player's race!");
      }
      player = sender.getServer().getPlayer(args[1]);
      if (player == null) {
        sender.sendMessage(ChatColor.RED + 
          "That player does not exist!");
      }
    }

    if (this.plugin.getCharacterCards().get(player.getName()) == null)
      this.plugin.getCharacterCards().put(player.getName(), new CharacterCard(Integer.valueOf(0), Gender.UNKNOWN, "", Race.UNKNOWN));
    else if ((((CharacterCard)this.plugin.getCharacterCards().get(player.getName())).getRace() != Race.UNKNOWN) && 
      (!sender.hasPermission("gildorym.setraceother"))) {
      sender.sendMessage(ChatColor.RED + 
        "You have already set your race!");
    }

    try
    {
      ((CharacterCard)this.plugin.getCharacterCards().get(player.getName())).setRace(Race.valueOf(args[0].toUpperCase()));
      sender.sendMessage(ChatColor.GREEN + "Set race to " + Race.valueOf(args[0].toUpperCase()).toString());
    } catch (IllegalArgumentException exception) {
      sender.sendMessage(ChatColor.RED + "That race does not exist!");
      sender.sendMessage(ChatColor.YELLOW + "If the race is a subrace, you may want to choose the main race.");
      sender.sendMessage(ChatColor.YELLOW + "If the race is a special case, such as an event, you may want to choose OTHER or UNKNOWN.");
    }
    return true;
  }
}