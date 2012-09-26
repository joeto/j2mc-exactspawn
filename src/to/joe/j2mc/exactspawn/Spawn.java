package to.joe.j2mc.exactspawn;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Spawn extends JavaPlugin implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void join(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {
            final Player player = event.getPlayer();
            this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                public void run() {
                    player.teleport(player.getWorld().getSpawnLocation());
                }
            });
        }
    }

    @EventHandler
    public void respawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(event.getRespawnLocation().getWorld().getSpawnLocation());
    }

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

}
