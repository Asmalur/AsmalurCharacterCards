package com.gildorymrp.gildorymcharactercards;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GildorymCharacterCards extends JavaPlugin
{
  private Map<String, CharacterCard> characterCards = new HashMap();

  public Map<String, CharacterCard> getCharacterCards()
  {
    return this.characterCards;
  }

  public void onEnable() {
    SaveDataManager.loadData(this);
    getServer().getPluginManager().registerEvents(new PlayerInteractEntityListener(this), this);

    getCommand("setage").setExecutor(new SetAgeCommand(this));
    getCommand("setgender").setExecutor(new SetGenderCommand(this));
    getCommand("setrace").setExecutor(new SetRaceCommand(this));
    getCommand("setinfo").setExecutor(new SetInfoCommand(this));
    getCommand("addinfo").setExecutor(new AddInfoCommand(this));
    getCommand("card").setExecutor(new CardCommand(this));
  }

  public void onDisable() {
    SaveDataManager.saveData(this);
  }
}