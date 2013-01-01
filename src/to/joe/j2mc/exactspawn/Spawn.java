package to.joe.j2mc.exactspawn;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import to.joe.j2mc.core.J2MC_Manager;

public class Spawn extends JavaPlugin implements Listener {

    private int id = -1;

    @EventHandler(priority = EventPriority.LOWEST)
    public void join(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {
            final Player player = event.getPlayer();
            this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                public void run() {
                    Location target = player.getWorld().getSpawnLocation();
                    if (Spawn.this.id == 0) {
                        target.setYaw(225);
                    }
                    player.teleport(target);
                }
            });
        }
    }

    @EventHandler
    public void respawn(PlayerRespawnEvent event) {
        if (event.getPlayer().getBedSpawnLocation() == null) {
            event.setRespawnLocation(event.getRespawnLocation().getWorld().getSpawnLocation());
        }
    }

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.id = J2MC_Manager.getServerID();
    }

}
