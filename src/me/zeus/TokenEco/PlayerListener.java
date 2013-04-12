
package me.zeus.TokenEco;


import java.io.File;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;



public class PlayerListener implements Listener {

    private TokenEco plugin;

    public PlayerListener(TokenEco plugin)
    {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();

        File f = new File("plugins/NagTokens/stats/" + p.getName() + ".yml");
        if (f.exists())
        {
            TokenPlayer tokenplayer = TokenPlayer.load(f);
            plugin.tokenplayers.put(p.getName(), tokenplayer);
        } else
        {
            TokenPlayer tp = new TokenPlayer(p.getName());
            tp.createFile();
            tp.save();
            plugin.tokenplayers.put(p.getName(), tp);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();
        TokenPlayer tp = plugin.tokenplayers.get(p.getName());
        tp.save();
        plugin.tokenplayers.remove(p.getName());
    }
}
