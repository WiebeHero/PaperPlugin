package me.WiebeHero.Main;

import me.WiebeHero.Abilities.Player.AbilityHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private AbilityHandler abilityHandler;

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("This plugin is now loaded! Let's test!");
        this.abilityHandler = new AbilityHandler(this);
        getServer().getPluginManager().registerEvents(this.abilityHandler, this);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("This plugin has shut down!");
    }
}
