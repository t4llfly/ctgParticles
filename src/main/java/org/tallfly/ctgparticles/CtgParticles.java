package org.tallfly.ctgparticles;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public final class CtgParticles extends JavaPlugin {
    private int particlesCount = 10;
    private Particle particlesType = Particle.FIREWORKS_SPARK;
    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("setparticles").setExecutor(this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, ()-> {
            for(Player player: Bukkit.getOnlinePlayers()){
                if (player.getWorld().getEnvironment() == World.Environment.THE_END) {
                    createParticles(player.getLocation().add(0, 8, 0), (int)(Math.random() * 50), particlesCount);
                    createParticles(player.getLocation().add(0, 6, 0), (int)(Math.random() * 50), particlesCount);
                    createParticles(player.getLocation().add(0, 4, 0), (int)(Math.random() * 50), particlesCount);
                    createParticles(player.getLocation().add(0, 2, 0), (int)(Math.random() * 50), particlesCount);
                    createParticles(player.getLocation().add(0, 0, 0), (int)(Math.random() * 50), particlesCount);
                    createParticles(player.getLocation().add(0, -2, 0), (int)(Math.random() * 50), particlesCount);
                }
            }
        }, 20,20);

    }

    @Override
    public void onDisable() {
    }

    private void createParticles(Location center, int radius, int particlesAmount) {
        World world = center.getWorld();

        for (int i = 0; i < particlesAmount; i++) {
            double angle = Math.random() * 2 * Math.PI;
            double x = center.getX() + radius * Math.cos(angle);
            double z = center.getZ() + radius * Math.sin(angle);
            double y = center.getY();

            Location particleLocation = new Location(world, x, y, z);
            world.spawnParticle(particlesType, particleLocation, 1, 0,0,0, 0);
        }
    }

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setparticles")) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Использование: /setparticles <тип частиц> <количество>");
                return true;
            }

            if (args.length >= 1) {
                try {
                    particlesType = Particle.valueOf(args[0].toUpperCase());
                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED + "Некорректный тип частиц.");
                    return true;
                }
            }

            if (args.length >= 2) {
                try {
                    particlesCount = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "Некорректное количество частиц.");
                    return true;
                }
            }

            player.sendMessage(ChatColor.GREEN + "Количество частиц " + particlesType + " успешно установлено на " + particlesCount + ".");
            return true;
        }

        return false;
    }
}
