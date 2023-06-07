package org.tallfly.ctgparticles;

import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public final class CtgParticles extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, ()-> {
            for(Player sans: Bukkit.getOnlinePlayers()){
                createParticles(sans.getLocation().add(0,8,0), 10, 3);
                createParticles(sans.getLocation().add(0,6,0), 25, 5);
                createParticles(sans.getLocation().add(0,4,0), 20, 4);
                createParticles(sans.getLocation().add(0,2,0), 15, 6);
                createParticles(sans.getLocation().add(0,0,0), 15, 2);
                createParticles(sans.getLocation().add(0,-2,0), 10, 4);
            }
        }, 20,30);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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
}
