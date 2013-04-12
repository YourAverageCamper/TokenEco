
package me.zeus.TokenEco;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class SetCmd implements CommandExecutor {

    private TokenEco plugin;

    public SetCmd(TokenEco plugin)
    {
        this.plugin = plugin;
        plugin.getCommand("tokens-set").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender.hasPermission("NagTokens.Set"))
        {
            if (args.length >= 2)
            {
                Player target = plugin.getServer().getPlayer(args[0]);
                if (target != null && target.isOnline())
                {
                    TokenPlayer tp = plugin.tokenplayers.get(target.getName());
                    try
                    {
                        tp.setTokens(Integer.parseInt(args[1]));
                        sender.sendMessage(ChatColor.GREEN + "Set " + target.getName() + "'s tokens to: " + Integer.parseInt(args[1]));
                    } catch (NumberFormatException nfe)
                    {
                        sender.sendMessage(ChatColor.RED + "Invalid number!");
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid amount of arguments. (/tokens-set <user> <amt> ");
            }
        } else
        {
            sender.sendMessage(ChatColor.RED + "You don't have permission to set tokens...");
        }
        return false;
    }
}
