package com.gildorymrp.gildorymcharactercards;

import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntityListener
  implements Listener
{
  private GildorymCharacterCards plugin;

  public PlayerInteractEntityListener(GildorymCharacterCards plugin)
  {
    this.plugin = plugin;
  }

  @EventHandler
  public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
    Player player = event.getPlayer();

    if ((player.isSneaking()) && 
      ((event.getRightClicked() instanceof Player))) {
      Player character = (Player)event.getRightClicked();
      if (this.plugin.getCharacterCards().get(character.getName()) == null) {
        this.plugin.getCharacterCards().put(character.getName(), new CharacterCard(Integer.valueOf(0), Gender.UNKNOWN, "", Race.UNKNOWN));
      }
      event.getPlayer().sendMessage(ChatColor.BLUE +""+ ChatColor.BOLD + character.getDisplayName() + ChatColor.BLUE + ChatColor.BOLD + "'s character card");
      CharacterCard characterCard = (CharacterCard)this.plugin.getCharacterCards().get(character.getName());

      player.sendMessage(ChatColor.GRAY + "Age: " + ChatColor.WHITE + characterCard.getAge());
      player.sendMessage(ChatColor.GRAY + "Gender: " + ChatColor.WHITE + characterCard.getGender().toString());
      player.sendMessage(ChatColor.GRAY + "Race: " + ChatColor.WHITE + characterCard.getRace().toString());
      player.sendMessage(ChatColor.GRAY + "Description: " + ChatColor.WHITE + characterCard.getDescription());
    }
  }
}