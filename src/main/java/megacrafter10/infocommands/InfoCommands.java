/*
*
* InfoCommands v1.0
* Author: MegaCrafter10
*
*/

package megacrafter10.infocommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class InfoCommands extends JavaPlugin implements CommandExecutor {

    private final Logger logger = Bukkit.getLogger();
    private String default_info = ChatColor.RED + "No information available for that command";

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getCommand("info").setExecutor(this);
        logger.info("InfoCommands enabled");
    }

    @Override
    public void onDisable() {
        logger.info("InfoCommands disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0){
            sender.sendMessage(ChatColor.RED + "Usage: /info <command>");
            return true;
        }

        String command_t = args[0].replace("/","");

        ConfigurationSection command_section = this.getConfig().getConfigurationSection("commands");
        if (command_section == null){
            this.getConfig().createSection("commands");
        }

        boolean commandExists = false;
        for (String cmd : command_section.getKeys(false)){

            if (cmd.replace("/","").equalsIgnoreCase(command_t)){

                String info = getConfig().getString("commands." + cmd, default_info);
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', info));
                commandExists = true;
                break;
            }
        }

        if (!commandExists){
            sender.sendMessage(default_info);
        }

        return true;
    }
}
