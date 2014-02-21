package com.gildorymrp.gildorymcharactercards;

import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetAgeCommand
  implements CommandExecutor
{
  private GildorymCharacterCards plugin;

  public SetAgeCommand(GildorymCharacterCards plugin)
  {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (this.plugin.getCharacterCards().get(sender.getName()) == null) {
      this.plugin.getCharacterCards().put(sender.getName(), new CharacterCard(Integer.valueOf(0), Gender.UNKNOWN, "", Race.UNKNOWN));
    }
    if (args.length >= 1)
      try {
        ((CharacterCard)this.plugin.getCharacterCards().get(sender.getName())).setAge(Integer.valueOf(Integer.parseInt(args[0])));
        sender.sendMessage(ChatColor.GREEN + "Set age to " + args[0]);
      } catch (NumberFormatException exception) {
        sender.sendMessage(ChatColor.RED + "You need to specify a number!");
      }
    else {
      sender.sendMessage(ChatColor.RED + "You need to specify an age!");
    }
    return true;
  }
}