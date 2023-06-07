package org.tallfly.ctgparticles;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import java.util.Objects;

public final class CtgParticles extends JavaPlugin {
    private int particlesCount = 10;
    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("setparticles").setExecutor(this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, ()-> {
            for(Player player: Bukkit.getOnlinePlayers()){
                if (player.getWorld().getEnvironment() == World.Environment.THE_END) {
                    createParticles(player.getLocation().add(0, 8, 0), 10, particlesCount);
                    createParticles(player.getLocation().add(0, 6, 0), 25, particlesCount);
                    createParticles(player.getLocation().add(0, 4, 0), 20, particlesCount);
                    createParticles(player.getLocation().add(0, 2, 0), 15, particlesCount);
                    createParticles(player.getLocation().add(0, 0, 0), 15, particlesCount);
                    createParticles(player.getLocation().add(0, -2, 0), 10, particlesCount);
                }
            }
        }, 20,20);

    }

    @Override
    public void onDisable() {
    }

    private void createParticles(Location center, int radius, int particles) {
        World world = center.getWorld();

        for (int i = 0; i < particles; i++) {
            double angle = Math.random() * 2 * Math.PI;
            double x = center.getX() + radius * Math.cos(angle);
            double z = center.getZ() + radius * Math.sin(angle);
            double y = center.getY();

            Location particleLocation = new Location(world, x, y, z);
            world.spawnParticle(Particle.FIREWORKS_SPARK, particleLocation, 1, 0,0,0, 0);
        }
    }

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setparticles")) {
            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "Использование: /setparticles <количество>");
                return true;
            }

            int particles;
            try {
                particles = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                player.sendMessage(ChatColor.RED + "Некорректное значение для количества частиц.");
                return true;
            }

            particlesCount = particles;

            player.sendMessage(ChatColor.GREEN + "Количество частиц успешно установлено на " + particles + ".");
            return true;
        }

        return false;
    }
}
