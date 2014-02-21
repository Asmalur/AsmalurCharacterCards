package com.gildorymrp.gildorymcharactercards;

import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CardCommand
  implements CommandExecutor
{
  private GildorymCharacterCards plugin;

  public CardCommand(GildorymCharacterCards plugin)
  {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (this.plugin.getCharacterCards().get(sender.getName()) == null) {
      this.plugin.getCharacterCards().put(sender.getName(), new CharacterCard(Integer.valueOf(0), Gender.UNKNOWN, "", Race.UNKNOWN));
    }
    sender.sendMessage(ChatColor.BLUE +""+ ChatColor.BOLD + ((Player)sender).getDisplayName() + ChatColor.BLUE + ChatColor.BOLD + "'s character card");
    CharacterCard characterCard = (CharacterCard)this.plugin.getCharacterCards().get(sender.getName());

    sender.sendMessage(ChatColor.GRAY + "Age: " + ChatColor.WHITE + characterCard.getAge());
    sender.sendMessage(ChatColor.GRAY + "Gender: " + ChatColor.WHITE + characterCard.getGender().toString());
    sender.sendMessage(ChatColor.GRAY + "Race: " + ChatColor.WHITE + characterCard.getRace().toString());
    sender.sendMessage(ChatColor.GRAY + "Description: " + ChatColor.WHITE + characterCard.getDescription());
    return true;
  }
}