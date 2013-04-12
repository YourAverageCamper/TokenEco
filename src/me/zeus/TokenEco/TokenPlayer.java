
package me.zeus.TokenEco;


import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;



public class TokenPlayer {

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * */

    String username;
    int tokens;

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public TokenPlayer(String name)
    {
        this.username = name;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public String getName()
    {
        return username;
    }

    public int getTokens()
    {
        return tokens;
    }

    public static TokenPlayer load(File f)
    {
        if (f != null && f.getName().contains(".yml"))
        {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
            TokenPlayer tp = new TokenPlayer(fc.getString("Name"));
            tp.setTokens(fc.getInt("Tokens"));
            return tp;
        }
        return null;
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public void setTokens(int amount)
    {
        this.tokens = amount;
    }

    public void addTokens(int amount)
    {
        this.tokens += amount;
    }

    public void removeTokens(int amount)
    {
        this.tokens -= amount;
    }

    public void createFile()
    {
        File f = new File("plugins/NagTokens/stats/" + this.username + ".yml");
        if (!f.exists())
        {
            try
            {
                f.createNewFile();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
        fc.set("Name", username);
        fc.set("Tokens", 0);
        try
        {
            fc.save(f);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void save()
    {
        File f = new File("plugins/NagTokens/stats/" + this.username + ".yml");
        FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
        fc.set("Tokens", tokens);
        try
        {
            fc.save(f);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * */

}
