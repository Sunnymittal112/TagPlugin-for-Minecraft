package net.fetal.tagplugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

public class TagPlugin extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        if (getCommand("tag") != null) getCommand("tag").setExecutor(this);
        getLogger().info("TagPlugin enabled!");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        
        if (label.equalsIgnoreCase("tag")) {
            // Permission Check
            if (!sender.hasPermission("tagplugin.admin") && !sender.isOp()) {
                sender.sendMessage(color("&cPermission nahi hai bro!"));
                return true;
            }

            // Usage Check
            if (args.length < 2) {
                sender.sendMessage(color("&eUsage: /tag <player> <text>"));
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(color("&cPlayer nahi mila!"));
                return true;
            }

            // Saare words ko jodna
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i]).append(" ");
            }
            String tagText = sb.toString().trim(); // Example: "Owner" ya "Super Admin"

            // Tag Lagana
            applyStyledTag(target, tagText);
            
            sender.sendMessage(color("&aTag set ho gaya: &r" + tagText));
            return true;
        }
        return false;
    }

    // --- MAIN MAGIC YAHAN HAI ---
    private void applyStyledTag(Player player, String rawText) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        
        String teamName = "tag_" + player.getName();
        if (teamName.length() > 16) teamName = teamName.substring(0, 16);

        Team team = scoreboard.getTeam(teamName);
        if (team == null) {
            team = scoreboard.registerNewTeam(teamName);
        }

        // AUTO DESIGN LOGIC:
        // &8 = Dark Gray Brackets [ ]
        // &6 = Gold Color
        // &l = BOLD Text
        // Structure:  [  BOLD_COLOR + Text  ]
        
        String design = "&8[&6&l" + rawText + "&8] "; 
        
        // Agar tumhe Red color chahiye Owner ke liye, toh &6 ki jagah &c kar dena upar wali line mein.

        team.prefix(color(design));
        team.addEntry(player.getName());
    }

    private Component color(String msg) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(msg);
    }
}