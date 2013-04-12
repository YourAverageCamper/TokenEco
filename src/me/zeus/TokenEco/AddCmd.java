
package me.zeus.TokenEco;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class AddCmd implements CommandExecutor {

    private TokenEco plugin;

    public AddCmd(TokenEco plugin)
    {
        this.plugin = plugin;
        plugin.getCommand("tokens-add").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender.hasPermission("NagTokens.Add"))
        {
            if (args.length >= 2)
            {
                Player target = plugin.getServer().getPlayer(args[0]);
                if (target.isOnline())
                {
                    TokenPlayer tp = plugin.tokenplayers.get(target.getName());
                    try
                    {
                        tp.addTokens(Integer.parseInt(args[1]));
                        sender.sendMessage(ChatColor.GREEN + "Gave " + target.getName() + " " + args[1] + " tokens!");
                    } catch (NumberFormatException nfe)
                    {
                        sender.sendMessage(ChatColor.RED + "Invalid number!");
                    }
                }
            }
        } else
        {
            sender.sendMessage(ChatColor.RED + "You don't have permission to add tokens...");
        }
        return false;
    }
}
