package com.gildorymrp.gildorymcharactercards;

import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AddInfoCommand
  implements CommandExecutor
{
  private GildorymCharacterCards plugin;

  public AddInfoCommand(GildorymCharacterCards plugin)
  {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    if (this.plugin.getCharacterCards().get(sender.getName()) == null) {
      this.plugin.getCharacterCards().put(sender.getName(), new CharacterCard(Integer.valueOf(0), Gender.UNKNOWN, "", Race.UNKNOWN));
    }
    if (args.length >= 1) {
      String info = "";
      for (String arg : args) {
        info = info + arg + " ";
      }
      ((CharacterCard)this.plugin.getCharacterCards().get(sender.getName())).setDescription(((CharacterCard)this.plugin.getCharacterCards().get(sender.getName())).getDescription() + info);
      sender.sendMessage(ChatColor.GREEN + "Added to description.");
    } else {
      sender.sendMessage(ChatColor.RED + "You need to specify some information!");
    }
    return true;
  }
}