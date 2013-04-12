
package me.zeus.TokenEco;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class TokensCmd implements CommandExecutor {

    private TokenEco plugin;

    public TokensCmd(TokenEco plugin)
    {
        this.plugin = plugin;
        plugin.getCommand("tokens").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender.hasPermission("NagTokens.Check"))
        {
            if (args.length < 1)
            {
                TokenPlayer tp = plugin.tokenplayers.get(sender.getName());
                sender.sendMessage(ChatColor.GREEN + "Tokens: " + tp.getTokens());
            } else if(args.length >= 1){
                Player p = plugin.getServer().getPlayer(args[0]);
                if(p != null && p.isOnline()){
                    TokenPlayer tp = plugin.tokenplayers.get(p.getName());
                    sender.sendMessage(ChatColor.BLUE + p.getName() + " has  " + tp.getTokens() + " tokens.");
                }
            }
        } else
        {
            sender.sendMessage(ChatColor.RED + "You don't have permission to check your tokens...");
        }
        return false;
    }

}
