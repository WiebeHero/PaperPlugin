package me.WiebeHero.Abilities.Player;

import me.WiebeHero.Main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AbilityHandler implements Listener {

    private Main main;

    public AbilityHandler(Main main){
        this.main = main;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        this.main.getServer().getConsoleSender().sendMessage("Heya!");
        Player player = event.getPlayer();
        if(event.getAction() == Action.LEFT_CLICK_AIR){
            BlazingTornado blazingTornado = new BlazingTornado(this.main, player.getLocation(), 4, 10, 50);
            blazingTornado.activate();
        }
    }

}
