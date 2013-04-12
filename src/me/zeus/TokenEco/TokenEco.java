
package me.zeus.TokenEco;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;



public class TokenEco extends JavaPlugin {

    /*
     *          TOKEN ECO, CREATED BY @TheGreenGamerHD
     *          You do not have permission to edit, copy from, or redistribute! All rights reserved!
     * 
     */

    public Map<String, TokenPlayer> tokenplayers = new HashMap<String, TokenPlayer>();

    @Override
    public void onEnable()
    {
        new PlayerListener(this);
        new SetCmd(this);
        new AddCmd(this);
        new RemoveCmd(this);
        new TokensCmd(this);
        File f = new File("plugins/NagTokens/stats/");
        if (!f.exists())
        {
            f.mkdirs();
        }

        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
        {

            @Override
            public void run()
            {
                for (Player p : getServer().getOnlinePlayers())
                {
                    File fk = new File("plugins/NagTokens/stats/" + p.getName() + ".yml");
                    if (fk.exists())
                    {
                        TokenPlayer tokenplayer = TokenPlayer.load(fk);
                        tokenplayers.put(p.getName(), tokenplayer);
                    }
                }
            }
        }, 20L);
    }

    public void onDisable()
    {
        for (TokenPlayer tp : tokenplayers.values())
        {
            tp.save();
        }
    }

}
