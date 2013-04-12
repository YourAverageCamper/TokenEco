
package me.zeus.TokenEco;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class RemoveCmd implements CommandExecutor {

    private TokenEco plugin;

    public RemoveCmd(TokenEco plugin)
    {
        this.plugin = plugin;
        plugin.getCommand("tokens-remove").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender.hasPermission("NagTokens.Remove"))
        {
            if (args.length >= 2)
            {
                Player target = plugin.getServer().getPlayer(args[0]);
                if (target.isOnline())
                {
                    TokenPlayer tp = plugin.tokenplayers.get(target.getName());
                    try
                    {
                        tp.removeTokens(Integer.parseInt(args[1]));
                        sender.sendMessage(ChatColor.RED + "Removed " + args[1] + " tokens from " + target.getName());
                    } catch (NumberFormatException nfe)
                    {
                        sender.sendMessage(ChatColor.RED + "Invalid number!");
                    }
                }
            }
        } else
        {
            sender.sendMessage(ChatColor.RED + "You don't have permission to remove tokens...");
        }
        return false;
    }
}
