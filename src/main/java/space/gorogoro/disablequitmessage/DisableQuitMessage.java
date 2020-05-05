package space.gorogoro.disablequitmessage;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DisableQuitMessage extends JavaPlugin implements Listener {

  @Override
  public void onEnable(){
    try{
      getLogger().info("The Plugin Has Been Enabled!");

      getServer().getPluginManager().registerEvents(this, this);

    } catch (Exception e){
      logStackTrace(e);
    }
  }

  @Override
  public void onDisable(){
    try{
      getLogger().info("The Plugin Has Been Disabled!");
    } catch (Exception e){
      logStackTrace(e);
    }
  }

  @EventHandler
  public void onPlayerQuitEvent(PlayerQuitEvent e){
    e.setQuitMessage("");
    for(Player p:getServer().getOnlinePlayers()) {
      if(p.isOp()) {
        p.sendMessage(ChatColor.YELLOW + e.getPlayer().getName() + " left the game");
      }
    }
  }

  private void logStackTrace(Exception e) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    pw.flush();
    getLogger().warning(sw.toString());
  }
}
